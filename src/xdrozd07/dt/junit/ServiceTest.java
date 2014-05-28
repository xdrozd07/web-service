package xdrozd07.dt.junit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import xdrozd07.dt.domain.Agreement;
import xdrozd07.dt.domain.Report;
import xdrozd07.dt.service.SlaManagement;

public class ServiceTest {
	
	String author = "pp";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	@Test
	public void addTest(){
		Agreement agreement = new Agreement();
		agreement.setAuthor(author);
		agreement.setName("smlouva ze služby");
		
		Report r = new Report("jmeno reportu", "popis reportu", "daily", 2, 1);
		
		//agreement.addReport(r);

		SlaManagement s = new SlaManagement();
		s.addAgreement(agreement);
		
	}
	
	
	
	@Test
	public void selectTest() {
		SlaManagement s = new SlaManagement();
		
		Agreement[] agreements= s.getAgreements(); 
		
		System.out.println("All:");
		for (Agreement a : agreements) {
			System.out.println(a.getId()+": "+a.getAuthor());
		}
		
		System.out.println("\nOnly one user:");
		Agreement[] agreements2 = s.getAuthorsAgreements("Rada"); 
		for (Agreement a : agreements2) {
			System.out.println(a.getId()+": "+a.getAuthor());
		}
	}

	@Test
	public void updateTest(){
		SlaManagement s = new SlaManagement();
		Agreement[] agreements = s.getAuthorsAgreements(this.author);
		
		
		System.out.println("Update test\n velikost: "+agreements.length);
		

		if(agreements.length > 0){
			System.out.println("za podminkou");
			Agreement a = agreements[agreements.length - 1];

			System.out.println("za prirazenim");

			System.out.println("ID: "+a.getId());
			
			a.setName("Upravene jmeno smlouvy");
			
			s.updateAgreement(a);
		}
	}
	
	@Test
	public void deleteTest(){
		SlaManagement s = new SlaManagement();
		
		Agreement[] agreements= s.getAgreements(); 
		
		System.out.println("All:");
		for (Agreement a : agreements) {
			System.out.println(a.getId()+": "+a.getAuthor());
		}
		
		s.deleteAgreement(agreements[0]);
		
		System.out.println("\nOnly one user:");
		Agreement[] agreements2 = s.getAgreements(); 
		for (Agreement a : agreements2) {
			System.out.println(a.getId()+": "+a.getAuthor());
		}
	}
	
}
