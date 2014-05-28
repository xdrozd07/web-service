package xdrozd07.dt.domain;
/**
 * Agreement
 * persistent class for ORM mapping and WS transfer
 * @author Radek Drozd
 */

//import java.util.Arrays;

import xdrozd07.dt.domain.glossary.Glossary;


public class Agreement {

	private int id;
	private String author;
	private String name;
	private String type;
	

	private String serviceDescription;
	private String slaDescription;
	private String securityDescription;
	private java.util.Date validFrom;
	private java.util.Date validTo;
	
	private ServiceHours serviceHours;
	private InvolvedParty[] involvedParties;	
	private Glossary[] glossaries;
	private ChangeManagement changemanagement;
	private CustomerSupport customerSupport;
	private Performance performance;
	private Availability[] availability;
	private Report[] reports;
	private Agreement predecessor;
	
	/**
	 * persistent constructor
	 */
	public Agreement(){
	}
	
	
	
	public CustomerSupport getCustomerSupport() {
		return customerSupport;
	}

	public void setCustomerSupport(CustomerSupport customerSupport) {
		this.customerSupport = customerSupport;
	}
	
	public Agreement getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Agreement predecessor) {
		this.predecessor = predecessor;
	}

	public ChangeManagement getChangemanagement() {
		return changemanagement;
	}

	public void setChangemanagement(ChangeManagement changemanagement) {
		this.changemanagement = changemanagement;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public String getSecurityDescription() {
		return securityDescription;
	}

	public void setSecurityDescription(String securityDescription) {
		this.securityDescription = securityDescription;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getServiceDescritption() {
		return serviceDescription;
	}
	public void setServiceDescritption(String serviceDescritption) {
		this.serviceDescription = serviceDescritption;
	}
	public String getSlaDescription() {
		return slaDescription;
	}
	public void setSlaDescription(String slaDescription) {
		this.slaDescription = slaDescription;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Glossary[] getGlossaries() {
		return glossaries;
	}
	public void setGlossaries(Glossary[] glossaries) {
		this.glossaries = glossaries;
	}
	public InvolvedParty[] getInvolvedParties() {
		return involvedParties;
	}
	public void setInvolvedParties(InvolvedParty[] involvedParties) {
		this.involvedParties = involvedParties;
	}
	public java.util.Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(java.util.Date validFrom) {
		this.validFrom = validFrom;
	}

	public java.util.Date getValidTo() {
		return validTo;
	}

	public void setValidTo(java.util.Date validTo) {
		this.validTo = validTo;
	}
	public ServiceHours getServiceHours() {
		return serviceHours;
	}

	public void setServiceHours(ServiceHours serviceHours) {
		this.serviceHours = serviceHours;
	}
	public void setPerformance(Performance p){
		this.performance = p;
	}
	public Performance getPerformance(){
		return this.performance;
	}
	public void setAvailability(Availability[] a){
		this.availability = a;
	}
	public Availability[] getAvailability(){
		return this.availability;
	}
	public Report[] getReports() {
		return reports;
	}
	public void setReports(Report[] reports) {
		this.reports = reports;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Adds Involved party to an involved parties array
	 * @param involvedParty to be added
	 
	public void addInvolvesParty(InvolvedParty involvedParty){
		if(this.involvedParties != null){
			InvolvedParty[] tmp = Arrays.copyOf(this.involvedParties, this.involvedParties.length + 1);
			this.involvedParties = tmp;
		}else{
			this.involvedParties = new InvolvedParty[1];
		}
		this.involvedParties[this.involvedParties.length-1] = involvedParty;
	}
	*/
	/**
	 * Adds glossary to a glossaries array
	 * @param involvedParty
	 
	public void addGlossary(Glossary glossary){
		if(this.glossaries != null){
			Glossary[] tmp = Arrays.copyOf(this.glossaries, this.glossaries.length + 1);
			this.glossaries = tmp;
		}else{
			this.glossaries = new Glossary[1];
		}
		this.glossaries[this.glossaries.length-1] = glossary;
	}
	*/
	/**
	 * Adds availability to an availability array
	 * @param availability
	 
	public void addAvailability(Availability availability){
		if(this.availability != null){
			Availability[] tmp = Arrays.copyOf(this.availability, this.availability.length + 1);
			this.availability = tmp;
		}else{
			this.availability = new Availability[1];
		}
		this.availability[this.availability.length-1] = availability;
	}
	 */
	/**
	 * Adds availability to an availability array
	 * @param availability
	
	public void addReport(Report report){
		if(this.reports != null){
			Report[] tmp = Arrays.copyOf(this.reports, this.reports.length + 1);
			this.reports = tmp;
		}else{
			this.reports = new Report[1];
		}
		this.reports[this.reports.length-1] = report;
	}
	*/
	
}
