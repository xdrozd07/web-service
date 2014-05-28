package xdrozd07.dt.domain;



/**
 * Change management
 * persistent class for ORM mapping and WS transfer
 * @author Radek Drozd
 */

public class ChangeManagement {
	

	private int id;
	private String description;	
	private Contact contact;
	
	public ChangeManagement(){
		
	}
	
	/**
	 * Creates new description instance
	 * @param description
	 */
	public ChangeManagement(String description){
		this.description = description;
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
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
