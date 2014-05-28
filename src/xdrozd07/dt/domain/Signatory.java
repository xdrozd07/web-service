package xdrozd07.dt.domain;
/**
 * Signatory
 * SLA signature
 * persistent class for ORM mapping and WS transfer
 * @author Radek Drozd
 */



public class Signatory {

	private int id;
	private String name;
	private java.util.Date date;
	
	public Signatory(){
		
	}
	public Signatory(String name, java.util.Date date){
		this.name = name;
		this.date = date;
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
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
		
}
