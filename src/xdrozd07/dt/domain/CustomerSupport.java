package xdrozd07.dt.domain;

/**
 * Customer Support
 * persistent class for ORM mapping and WS transfer
 * @author Radek Drozd
 */

//import java.util.Arrays;


public class CustomerSupport {
	

	private int id;
	private String description;
	private String note;
	private java.util.Date responseTime;
	private String response;
	private Contact contact;
	
	private AccessTime[] accessTimes;

	
	
	/**
	 * Persistent construction
	 */
	public CustomerSupport(){
		
	}

	/**
	 * Complete Customer support Constructor
	 * @param description Customer support description
	 * @param note
	 * @param responseTime
	 * @param response response definition
	 * @param contact support contact
	 */
	public CustomerSupport(String description, String note, java.util.Date responseTime, String response, Contact contact){
		this.description = description;
		this.note = note;
		this.responseTime = responseTime;
		this.response = response;
		this.contact = contact;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public java.util.Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(java.util.Date responseTime) {
		this.responseTime = responseTime;
	}

	public AccessTime[] getAccessTimes() {
		return accessTimes;
	}

	public void setAccessTimes(AccessTime[] accessTimes) {
		this.accessTimes = accessTimes;
	}
	
	/**
	 * Adds access time to an array of access times
	 * @param accessTime
	 
	public void addAccessTime(AccessTime accessTime){
		if(this.accessTimes != null){
			AccessTime[] tmp = Arrays.copyOf(this.accessTimes, this.accessTimes.length + 1);
			this.accessTimes = tmp;
		}else{
			this.accessTimes = new AccessTime[1];
		}
		this.accessTimes[this.accessTimes.length-1] = accessTime;
	}
	*/
}
