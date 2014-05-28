package xdrozd07.dt.domain;

/**
 * Peristent class
 * 
 * @author Radek Drozd
 *
 */

//import java.util.Arrays;

import xdrozd07.dt.domain.metric.Metric;


public class Performance {


	private int id;
	private String description;
	private Metric[] metrics;
	
	/**
	 * peristent constructor
	 */
	public Performance(){
		
	}
	
	/**
	 * Perfromance constructor
	 * @param description
	 * @param metrics
	 */
	public Performance(String description, Metric[] metrics){
		this.description = description;
		this.metrics = metrics;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Metric[] getMetrics() {
		return metrics;
	}
	public void setMetrics(Metric[] metrics) {
		this.metrics = metrics;
	}
	
	/**
	 * adds metric to the end of metrics array
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
