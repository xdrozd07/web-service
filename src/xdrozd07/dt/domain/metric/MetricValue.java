package xdrozd07.dt.domain.metric;


/**
 * Metric Values
 * persistent class
 * 
 * @author Radek Drozd
 */



public class MetricValue {
	private int id;
	private java.util.Date captured;
	private double value;
	
	/**
	 * persistent constructor
	 */
	public MetricValue(){
		
	}
	
	/**
	 * Full constructor
	 * @param captured
	 * @param value
	 */
	public MetricValue(java.util.Date captured, double value){
		this.captured = captured;
		this.value = value;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public java.util.Date getCaptured() {
		return captured;
	}
	public void setCaptured(java.util.Date capruterd) {
		this.captured = capruterd;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
}
