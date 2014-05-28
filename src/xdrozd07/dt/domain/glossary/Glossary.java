
package xdrozd07.dt.domain.glossary;
/**
 * Glossary
 * persistent class for ORM mapping and WS transfer
 * @author Radek Drozd
 */

//import java.util.Arrays;


public class Glossary {

	private int id;
	private String name;
	private String description;
	private Term[] terms;
	
	public Glossary(){
		
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String descritpion) {
		this.description = descritpion;
	}
	
	public Term[] getTerms() {
		return terms;
	}
	public void setTerms(Term[] terms) {
		this.terms = terms;
	}
}
