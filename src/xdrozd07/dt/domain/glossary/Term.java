package xdrozd07.dt.domain.glossary;

/**
 * Term
 * persistent class for ORM mapping and WS transfer
 * @author Radek Drozd
 */
public class Term {
	private int id;
	private String term;
	private String definition;
	
	public Term(){
		
	}
	public Term(String t, String d){
		this.term = t;
		this.definition = d;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
}
