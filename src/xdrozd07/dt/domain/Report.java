package xdrozd07.dt.domain;

//import java.util.Arrays;

import xdrozd07.dt.domain.metric.Metric;

public class Report {

	private int id;
	private String name;
	private String description;
	private String frequency;
	private int hour;
	private int day;
	private Contact[] contacts;
	private Metric[] metrics;
	
	/**
	 * persistent constructor
	 */
	public Report(){
		
	}
	
	/**
	 * New report constructor
	 * @param name	
	 * @param description
	 * @param frequency daily, weekly, monthly is accepted
	 * @param hour 0 to 31 is accepted
	 * @param day 0 to 6 is accepted
	 */ 
	public Report(String name, String description, String frequency, int hour, int day){
		this.name = name;
		this.description = description;
		
		if(frequency == "daily" || frequency == "weekly" || frequency == "monthly")
			this.frequency = frequency;

		if(day < 32 && day > 0)
			this.hour = hour;
		
		if(day < 7 && day >= 0)
			this.day = day;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFrequency() {
		return frequency;
	}
	/**
	 * enumerator "daily", "weekly" or "monthly" accepted
	 * @param frequency
	 */
	public void setFrequency(String frequency) {
		//if(frequency == "daily" || frequency == "weekly" || frequency == "monthly")
			this.frequency = frequency;
	}
	public int getHour() {
		return hour;
	}
	/**
	 * hour of a day, 0-23 is accepted
	 * @param hour
	 */
	public void setHour(int hour) {
		if(hour >=0 && hour < 24)
			this.hour = hour;
	}
	public int getDay() {
		return day;
	}
	/**
	 * which day o
	 * number of the day in week, 1 to 31 is accepted
	 * @param day
	 */
	public void setDay(int day) {
		if(day < 32 && day > 0)
			this.day = day;
	}
	public Contact[] getContacts() {
		return contacts;
	}
	public void setContacts(Contact[] contacts) {
		this.contacts = contacts;
	}
	public Metric[] getMetrics() {
		return metrics;
	}
	public void setMetrics(Metric[] metrics) {
		this.metrics = metrics;
	}
	
	/**
	 * Adds given contact to the end of contacts array
	 * @param contact
	 
	public void addContact(Contact contact){
		if(this.contacts != null){
			Contact[] tmp = Arrays.copyOf(this.contacts, this.contacts.length + 1);
			this.contacts = tmp;
		}else{
			this.contacts = new Contact[1];
		}
		this.contacts[this.contacts.length-1] = contact;
	}
	*/
	/**
	 * Adds given metric to the end of metrics array
	 * @param metric
	 
	public void addMetric(Metric metric){
		if(this.metrics != null){
			Metric[] tmp = Arrays.copyOf(this.metrics, this.metrics.length + 1);
			this.metrics = tmp;
		}else{
			this.metrics = new Metric[1];
		}
		this.metrics[this.metrics.length-1] = metric;
	}
	*/
}
