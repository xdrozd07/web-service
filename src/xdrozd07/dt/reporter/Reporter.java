package xdrozd07.dt.reporter;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.TimerTask;

import javax.servlet.UnavailableException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.mail.EmailException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import org.json.JSONArray;
import org.json.JSONObject;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import xdrozd07.dt.connection.SessionFactoryUtil;
import xdrozd07.dt.domain.Contact;
import xdrozd07.dt.domain.Report;
import xdrozd07.dt.domain.metric.Listener;
import xdrozd07.dt.domain.metric.Metric;
import xdrozd07.dt.domain.metric.MetricValue;
import xdrozd07.dt.domain.metric.Treshold;
import xdrozd07.dt.exceptions.InvalidDataException;
import xdrozd07.dt.logger.LoggerUtil;



public class Reporter extends TimerTask{
	
	private int dayOfWeek;
	private int dayInMonth;
	private int hourOfDay;
	private String csvSeparator = ";";
	private int daysInMonthLimit = 31;
	private int timeMultiplication = 1000; // java need a time in a milliseconds, no one knows why
	
	private List<Report> reports;
	private List<Metric> performanceMetrics;
	private Session session;
	DateFormat allDay = new SimpleDateFormat("y/M/d k:mm");
	
	/**
	 * creates new instance of Reporter
	 */
	public Reporter(){
		this.refreshDates();
	}

	
	
	/**
	 * Periodically runs code in this class
	 */
	@Override
	public void run() {
		this.makeReports();
		
	}
	
	/**
	 * makes reports which belongs to this hour
	 */
	public void makeReports(){
		System.out.println("reportuju");
		
		// update dates to actual time
		this.refreshDates();
		
		// get all reports to work out now
		this.getActualReports();

		

		// walk thru reports and.. 
		if(this.reports != null){
			for(Report r: this.reports){
				
			
				System.out.println(r.getName());
				
				// walks thru metrics
				if(r != null && r.getMetrics() != null){
					// actualize metric values and contacts listeners if necessary
					for(Metric m: r.getMetrics()){
						this.actualizeMetric(m);
					}
				}
			}
		}
		
		
		// save new data in reports
		this.saveReports();
		
		// generate report
		this.generateReports();
		

		
	}
	
	
	/**
	 * Saves actual reports
	 */
	private void saveReports(){
		this.before();
		
		
		Transaction tx = this.session.beginTransaction();
		try{
			
			for(Report r: this.reports){
				this.session.saveOrUpdate(r);
			}
			
			
			// finalize
			tx.commit();
		}catch(Exception e){
			LoggerUtil.log(e);
		}
	}
	
	/**
	 * refresh data by using actual time
	 */
	private void refreshDates(){
		DateFormat dayInMouthFormat = new SimpleDateFormat("d");
		DateFormat dayOfWeekFormat = new SimpleDateFormat("F");
		DateFormat hourOfDayFormat = new SimpleDateFormat("H");
		Date now = new Date();

		this.dayOfWeek = Integer.parseInt(dayOfWeekFormat.format(now));
		this.dayInMonth = Integer.parseInt(dayInMouthFormat.format(now));
		this.hourOfDay = Integer.parseInt(hourOfDayFormat.format(now));
	}
	
	
	/**
	 * generates reports for a specified period
	 */
	private void generateReports(){

		// actualize performance metrics to be checked later
		this.actualizePerformanceMetric();
		
		if(this.reports != null){
			
			for(Report r: this.reports){
				

				String reportMessage = "----------------------------------------------------------------\n";
				reportMessage += "REPORT: " + r.getName();
				reportMessage += "\n----------------------------------------------------------------\n\n";
				
				if(r.getMetrics() != null){
					for(Metric m: r.getMetrics()){
						
						this.before();
						
						Transaction tx = this.session.beginTransaction();
						List<MetricValue> values = null;
						try{
							String hql = "FROM MetricValue MV WHERE metric_id = :metricId AND ( MV.captured > :start AND MV.captured < :end)";
							Query query = session.createQuery(hql).setParameter("metricId", m.getId());
							
							Date end = new Date();
							Date start = this.getStart(r);

							query.setParameter("start", start);
							query.setParameter("end", end);

							values = new ArrayList<MetricValue>(query.list());
							
							tx.commit();
						}catch(Exception e){
							LoggerUtil.log(e);
						}
						
						
						// get report part for every threshold in metric

						String metricMessage = "METRIC: " + m.getName()+" \n";
						
						if(this.isPerfromanceMetric(m)){
							if(m.getTresholds() != null){
								for(Treshold t : m.getTresholds()){
									metricMessage += this.makeTresholdMessage(t, values);
								}
							}
						}else{
							// it is availability metric
							metricMessage = this.getAvailabilityMetricmessage(r, m, values);
						}
						
						
						reportMessage += metricMessage;
					}
				} // if metrics
				
				
				this.sendReportMessage(r, reportMessage);
				
			}
			
		}
	}
	
	/**
	 * builds string message about threshold breaks
	 * @param t
	 * @param values
	 * @return
	 */
	private String makeTresholdMessage(Treshold t, List<MetricValue> values){
		String message = "";
		
		int brokenTimes = 0;
		
	
		String brokenLines = "";
		for(MetricValue mv : values){
			if(t.getValue() < mv.getValue()){
				brokenLines += "\t" + this.allDay.format(mv.getCaptured()) + ": " + mv.getValue() +"\n";
				brokenTimes++;
			}
		}

		
		
		message += "Threshold value: " + t.getValue() + "\n";
		message += "broken: " + brokenTimes + " time(s)\n";
		
		if(brokenLines.compareTo("") != 0){
			message += brokenLines;
		}

		message += "\n\n";			
		return message;
	}
	
	
	/**
	 * returns a message for metric values
	 * @param values
	 * @return
	 */
	private String getAvailabilityMetricmessage(Report r, Metric m, List<MetricValue> values){

		double sum = 0;
		if(values != null){
			for(MetricValue mv: values){
				sum += mv.getValue();
			}
			
		}

		String message = "METRIC: " + m.getName() + "\n";
		message += "Total uavailable " + sum + "[s]\n\n";
		


		return message;
	}
	
	/**
	 * Actualize performance metric atribute
	 */
	private void actualizePerformanceMetric(){
		this.before();
		
		this.performanceMetrics = null;
		Transaction tx = this.session.beginTransaction();

		try{
			String hql = "SELECT p.metrics FROM Performance p";
		
			
			Query query = session.createQuery(hql);
			
			System.out.println(query);
			

			this.performanceMetrics = new ArrayList<Metric>(query.list());
			
			tx.commit();
		}catch(Exception e){
			LoggerUtil.log(e);
		}
	}
	
	
	/**
	 * method returns start period date depending on reports frequency
	 * @param r
	 * @return
	 * @throws InvalidDataException 
	 */
	private Date getStart(Report r) throws InvalidDataException{
		
		Date referenceDate = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(referenceDate); 
		
		
		switch(r.getFrequency()){
		case "daily":
			c.add(Calendar.HOUR, -24);
			break;
			
		case "weekly":
			c.add(Calendar.HOUR, -24*7);
			break;
			
		case "monthly":
			c.add(Calendar.MONTH, -1);
			break;
			
		default:
			throw new InvalidDataException("Invalid frequency");
		}

		return c.getTime();
	}
	
	
	/**
	 * sends report message 
	 * @param r
	 * @param message
	 */
	private void sendReportMessage(Report r, String message){
		if(r != null){
			for(Contact c : r.getContacts()){
				try {
					Mailer.send(r.getName(), message, c);
				} catch (EmailException | FileNotFoundException e) {
					LoggerUtil.log(e);
				}
			}
		}
	}
	
	/**
	 * refresh reports which belong to this hour
	 */
	private void getActualReports(){
		this.reports = null;
		
		this.before();
		
		Transaction tx = this.session.beginTransaction();
		try{

			
			// build a query
			String hql = "FROM Report R WHERE R.hour = :hour AND ( "
					+ "(R.frequency = :dailyFreq)"
					+ " OR "
					+ "(R.frequency = :weeklyFreq AND R.day = :weekDay)"
					+ "OR"
					+ "(R.frequency = :monthlyFreq AND R.day IN (:daysList))"
				+ ")";
			
			
			// if actual month is shorter than 31 days, all reports, which were planned to be made till the end of month
			// will be send at last day in month
			
			int monthLength = this.getMonthLength();
			
			Integer[] days;
			int index = 0;
			
			if(this.dayInMonth == monthLength){
				
				days = new Integer[this.daysInMonthLimit - this.dayInMonth + 1];
				
				for(int i = this.dayInMonth + 1;  i  <= this.daysInMonthLimit ; i++, index++){
					days[index] = i;
				}
			}else{
				days = new Integer[1];
			}
			days[index] = this.dayInMonth;
			
			Query query = session.createQuery(hql).setParameter("hour", this.hourOfDay);
			query.setParameter("dailyFreq", "daily");
			query.setParameter("weeklyFreq", "weekly");
			query.setParameter("monthlyFreq", "monthly");
			query.setParameter("weekDay", this.dayOfWeek);
			query.setParameterList("daysList",days);

			
			// run it
			this.reports = new ArrayList<Report>(query.list());
			
			
			// finalize
			tx.commit();
		}catch(Exception e){
			LoggerUtil.log(e);
		}
	}
	
	/**
	 * returns the length of 
	 * @return
	 */
	private int getMonthLength(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());

		// Get the number of days in that month
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * sets up a database connection before usage
	 */
	private void before(){

		this.session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		if(! this.session.isOpen()){
			this.session = SessionFactoryUtil.getSessionFactory().openSession();
		}
	}

	
	/**
	 * Actualize metric values
	 * @param m
	 */
	private void actualizeMetric(Metric m){
		if(m != null && m.getInputAddress() != null && m.getInputType() != null){
			
			//System.out.println(m.getId()+": "+m.getInputType()+" : "+m.getInputAddress());
			
			
			try{

				// download data
				URL url = new URL(m.getInputAddress());
				Scanner s = new Scanner(url.openStream());
				String inputStreamString = s.useDelimiter("\\A").next();
				
				//System.out.println(inputStreamString);
				
				
				// parse data to metric values
				List<MetricValue> values = null;
				switch(m.getInputType()){
					case "csv":
						System.out.println("CSV");
						values = this.parseCSV(inputStreamString);
						break;
						
					case "xml":
						System.out.println("XML");
						values = this.parseXML(inputStreamString);
						break;
						
					case "json":
						System.out.println("JSON");
						values = this.parseJSON(inputStreamString);
						break;
						
					default:
						System.err.println("Switch metric input type");
						System.err.println(m.getInputType());
				}
				
				


				// check all of metrics threshold and if the were broken,
				// contact listeners
				this.thresholdsCheck(m, values);
				
				// insert new values to a metric
				m.setValues(this.joinValues(m.getValues(), values));
				
				s.close();
				
			}catch(Exception e){
				LoggerUtil.log(e);
			}finally{
			
			}
			
			// save
			
		}
	}
	
	
	/**
	 * Checks if the metric is performance metric
	 * @param m
	 * @return true if the given metric if performance metric, false if given metric is available metric
	 */
	private boolean isPerfromanceMetric(Metric m){
		
		boolean result = false;
		
		if(this.performanceMetrics != null){
			for(Metric pm: this.performanceMetrics){
				if(pm.getId() == m.getId()){
					result = true;
					break;
				}
			}
		}
			
		return result;
	}
	
	/**
	 * parse input CSV string
	 * @param s CSV content
	 * @return list of parsed MetricValues
	 * @throws UnavailableException
	 */
	private List<MetricValue> parseCSV(String s) throws UnavailableException{
		List<MetricValue> l = new ArrayList<MetricValue>();
		
		
		String[] lines = s.split(System.getProperty("line.separator"));
		for(String line: lines){			
			
			String[] cells = line.split(this.csvSeparator);
			if(cells.length != 2){
				throw new UnavailableException("Unable to parse the file");
			}
		
			
			Long time = Long.parseLong(cells[0]) * this.timeMultiplication;
			Date captured = new Date(time);
			
			
			Double value = Double.parseDouble(cells[1]);
			
			l.add(new MetricValue(captured, value));
			
		}
		
		return l;
	}
	
	/**
	 * parse input XML string
	 * @param s XML content
	 * @return list of parsed MetricValues
	 * @throws UnavailableException
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	private List<MetricValue> parseXML(String s) throws UnavailableException, ParserConfigurationException, SAXException, IOException{
		List<MetricValue> l = new ArrayList<MetricValue>();
		
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(s));
	    
	    // parse XML
	    Document doc =builder.parse(is);
	    
	    // get all values nodes and walks thru
	    NodeList nList = doc.getElementsByTagName("value");
	    for (int temp = 0; temp < nList.getLength(); temp++) {
	    	
	    	// checks all element nodes
	    	Node nNode = nList.item(temp);
	    	if (nNode.getNodeType() == Node.ELEMENT_NODE){
	    		
	    		Element eElement = (Element) nNode;
	    		
	    		Long time = Long.parseLong(eElement.getAttribute("time")) * this.timeMultiplication;
				Date captured = new Date(time);

				Double value = Double.parseDouble(eElement.getTextContent());
				
				l.add(new MetricValue(captured, value));
	    		
	    	}
	    }
		
		return l;
	}
	
	/**
	 * parse input JSON string
	 * @param s JSON content
	 * @return list of parsed MetricValues
	 * @throws UnavailableException
	 */
	private List<MetricValue> parseJSON(String s) throws UnavailableException{
		List<MetricValue> l = new ArrayList<MetricValue>();
		
		
		JSONArray array = new JSONArray(s);
		JSONObject tmp = null;
		
		
		Long time;
		Double value;
		
		
		for(int i = 0; i < array.length() ; i++){
			tmp = array.getJSONObject(i);
			
			time = tmp.getLong("time");
			value = tmp.getDouble("value");
			
			l.add(new MetricValue(new Date(time * this.timeMultiplication), value));
		}
		
		return l;
	}

	
	/**
	 * walks thru given metric values and checks, if they brakes specified thresholds
	 * @param m
	 * @param values
	 */
	private void thresholdsCheck(Metric m, List<MetricValue> values){
		
		// walk thru thresholds
		if(m.getTresholds() != null){			
			for(Treshold t: m.getTresholds()){

				// walk thru listeners
				if(t.getListeners() != null){
					for(Listener l: t.getListeners()){
						
						
						// walk thru all metric values 
						// and take the broken ones
						List<MetricValue> broken = new ArrayList<MetricValue>();
				
						
						for(MetricValue mv: values){
							if(mv.getValue() > t.getValue()){
								broken.add(mv);
							}
						}
						
						
						// make the message
						String message = this.makeListenerMessage(m, t, broken);
						
						// send it
						if(broken.size() > 0){
							try {
								this.sendMessage(l, message);
							} catch (IOException e) {
								LoggerUtil.log(e);
							}
						}
					}
				}
			}
		}
		
	}
	
	
	/**
	 * sends given message to a listener as a PUT HTTP request
	 * @param l
	 * @param message
	 * @throws IOException 
	 */
	private void sendMessage(Listener l, String message) throws IOException{
		
		
		URL url = new URL(l.getAddress());
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("PUT");
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "application/json");
		
	
		OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
		
		
		osw.write(message);
		osw.flush();
		osw.close();
		
	}


	/**
	 * makes a JSON String message to be send to a listener
	 * @param m
	 * @param t
	 * @param brokenValues
	 * @return
	 */
	private String makeListenerMessage(Metric m, Treshold t, List<MetricValue> brokenValues){
		
		JSONObject obj = new JSONObject();
		obj.put("metric_id", m.getId());
		obj.put("threshold_id", t.getId());
		obj.put("threshold", t.getValue());
		
		JSONArray list = new JSONArray();
		for(MetricValue mv : brokenValues){
			JSONObject tmp =  new JSONObject();
			
			tmp.put("time", mv.getCaptured().getTime() / this.timeMultiplication);
			tmp.put("value", mv.getValue());
			
			list.put(tmp);
		}
		
		obj.put("values", list);
		
		
		
		
		return obj.toString();
	}


	/**
	 * Joins two metric values arrays
	 * @param mv1 metric value array
	 * @param mv2 etric valeus List
	 * @return
	 */
	private MetricValue[] joinValues(MetricValue[] mv1, List<MetricValue> mv2){
		
		MetricValue[] res = new MetricValue[mv1.length + mv2.size()];
		
		int i = 0;
		for(MetricValue mv : mv1){
			res[i] = mv;
			i++;
		}
		for(MetricValue mv : mv2){
			res[i] = mv;
			i++;
		}
		
		
		return res;
	}


	
}



