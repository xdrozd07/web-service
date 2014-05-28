package xdrozd07.dt.domain;
/**
 * Event
 * persistent class for ORM mapping and WS transfer
 * @author Radek Drozd
 */


public class Event {
	

	private int id;
	private java.util.Date open;
	private java.util.Date close;
	private String type;
	private String description;
	

	/**
	 * Persistent constructor
	 */
	public Event(){
		
	}
	
	/**
	 * New event construction
	 * @param open   date and time of event's start
	 * @param close  date and time of event's end
	 * @param type   enum('maintenance', 'shutdown', 'other', '')	
	 * @param description
	 */
	public Event(java.util.Date open, java.util.Date close, String type, String description){
		this.open = open;
		this.close = close;
		this.type = type;
		this.description = description;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public java.util.Date getOpen() {
		return open;
	}
	public void setOpen(java.util.Date open) {
		this.open = open;
	}
	public java.util.Date getClose() {
		return close;
	}
	public void setClose(java.util.Date close) {
		this.close = close;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
