package xdrozd07.dt.domain;

/**
 * AccessTime
 * persistent class for ORM mapping and WS transfer
 * @author Radek Drozd
 */



public class AccessTime {
	
	private int id;
	private String day;
	private java.util.Date open;
	private java.util.Date close;
	
	/**
	 * persistent constructor
	 */
	public AccessTime(){
		
	}
	
	/**
	 * 
	 * @param day enum ('mon','tue','wed','thu','fri','sat','sun')
	 * @param open opening date
	 * @param close closing date
	 */
	public AccessTime(String day, java.util.Date open, java.util.Date close){
		this.day = day;
		this.open = open;
		this.close = close;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
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

	public String toString(){
		return "AccessTime: ID: "+this.id+", DAY: "+this.day+", OPEN: "+this.open.toString()+" CLOSE: "+this.close.toString();
	}
}
