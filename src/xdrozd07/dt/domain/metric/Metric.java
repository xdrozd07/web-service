package xdrozd07.dt.domain.metric;

/**
 * Metric
 * persistent class
 */

//import java.util.Arrays;


public class Metric {
	private int id;
	private String name;
	private String inputAddress;
	private String inputType;
	private String inputFormat;
	private Treshold[] tresholds;
	private MetricValue[] values;
	
	
	

	/**
	 * persistent constructor
	 */
	public Metric(){
		
	}
	
	/**
	 * new metric constructor
	 * @param name
	 * @param inputAddress
	 * @param inputType enum('csv', 'xml', 'json')
	 * @param inputFormat
	 * @param tresholds
	 * @param values
	 */
	public Metric(String name, String inputAddress, String inputType, String inputFormat, Treshold[] tresholds, MetricValue[] values){
		this.name = name;
		this.inputAddress = inputAddress;
		this.inputFormat = inputFormat;
		this.inputType = inputType;
		this.tresholds = tresholds;
		this.values = values;
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
	public String getInputAddress() {
		return inputAddress;
	}
	public void setInputAddress(String inputAddress) {
		this.inputAddress = inputAddress;
	}
	public String getInputType() {
		return inputType;
	}
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
	public String getInputFormat() {
		return inputFormat;
	}
	public void setInputFormat(String inputFormat) {
		this.inputFormat = inputFormat;
	}
	public Treshold[] getTresholds() {
		return tresholds;
	}
	public void setTresholds(Treshold[] tresholds) {
		this.tresholds = tresholds;
	}
	public MetricValue[] getValues() {
		return values;
	}
	public void setValues(MetricValue[] values) {
		this.values = values;
	}
	
	/**
	 * Adds treshold to the end of tresholds array
	 * @param treshold
	 
	public void addTreshold(Treshold treshold){
		if(this.tresholds != null){
			Treshold[] tmp = Arrays.copyOf(this.tresholds, this.tresholds.length + 1);
			this.tresholds = tmp;
		}else{
			this.tresholds = new Treshold[1];
		}
		this.tresholds[this.tresholds.length-1] = treshold;
	}
	*/
	/**
	 * Adds Metric values to the end of metric values array
	 * @param value
	 
	public void addMetricValue(MetricValue value){
		if(this.values != null){
			MetricValue[] tmp = Arrays.copyOf(this.values, this.values.length + 1);
			this.values = tmp;
		}else{
			this.values = new MetricValue[1];
		}
		this.values[this.values.length-1] = value;
	}
	*/
}
