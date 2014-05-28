package xdrozd07.dt.domain;

//import java.util.Arrays;


/**
 * Involved Party
 * persistent class for ORM mapping and WS transfer
 * @author Radek Drozd
 */

public class InvolvedParty {
	

	private int id;
	private String name;
	private String role;

	private Contact contact;

	private Signatory[] signatories;
	
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public Signatory[] getSignatories() {
		return signatories;
	}
	public void setSignatories(Signatory[] signatories) {
		this.signatories = signatories;
	}
	
	/**
	 * Adds signratory to the list of signatories
	 * @param signatory
	 
	public void addSignatory(Signatory signatory){
		if(this.signatories != null){
			Signatory[] tmp = Arrays.copyOf(this.signatories, this.signatories.length + 1);
			this.signatories = tmp;
		}else{
			this.signatories = new Signatory[1];
		}
		this.signatories[this.signatories.length-1] = signatory;
	}
	*/
}
