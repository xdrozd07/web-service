package xdrozd07.dt.domain;

/**
 * Service hours
 * persistent class
 */

//import java.util.Arrays;



public class ServiceHours {

	private int id;
	private String description;
	private AccessTime[] accessTimes;
	private Event[] events;
	
	
	/**
	 * persistent constructor
	 */
	public ServiceHours(){
	}
	
	/**
	 * Service hours
	 * @param description
	 * @param accessTimes set of service access times
	 * @param events set of service events
	 */
	public ServiceHours(String description, AccessTime[] accessTimes, Event[] events){
		this.description = description;
		this.accessTimes = accessTimes;
		this.events = events;
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
	
	public AccessTime[] getAccessTimes() {
		return accessTimes;
	}
	public void setAccessTimes(AccessTime[] accessTimes) {
		this.accessTimes = accessTimes;
	}
	
	public Event[] getEvents() {
		return events;
	}
	public void setEvents(Event[] events) {
		this.events = events;
	}
	
	/**
	 * adds event to the end of events array
	 * @param event
	 
	public void addEvent(Event event){
		if(this.events != null){
			Event[] tmp = Arrays.copyOf(this.events, this.events.length + 1);
			this.events = tmp;
		}else{
			this.events = new Event[1];
		}
		this.events[this.events.length-1] = event;
	}
	*/
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
