package xdrozd07.dt.domain;

import xdrozd07.dt.domain.metric.Metric;


public class Availability {

	private int id;
	private String description;
	private Metric metric;
	
	/**
	 * persistent constructor
	 */
	public Availability(){
		
	}
	
	/**
	 * Availability constructor
	 * 
	 * @param description
	 * @param metric
	 */
	public Availability(String description, Metric metric){
		this.description = description;
		this.metric = metric;
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
	public Metric getMetric() {
		return metric;
	}
	public void setMetric(Metric metric) {
		this.metric = metric;
	}
}
