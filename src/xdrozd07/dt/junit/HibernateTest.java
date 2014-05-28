package xdrozd07.dt.junit;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.hibernate.Query;
import org.hibernate.Session;

import xdrozd07.dt.connection.SessionFactoryUtil;
import xdrozd07.dt.domain.*;
import xdrozd07.dt.domain.glossary.Glossary;
import xdrozd07.dt.domain.glossary.Term;
import xdrozd07.dt.domain.metric.Listener;
import xdrozd07.dt.domain.metric.Metric;
import xdrozd07.dt.domain.metric.MetricValue;
import xdrozd07.dt.domain.metric.Treshold;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class HibernateTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
/*
	@Test
	public void test() {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Person person = new Person();
		person.setName("Radek");
		person.setSurname("Drozd");    
		session.save(person);
		
		session.getTransaction().commit();
	}
	*/
	
	@Test
	public void test2() {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		
		
		session.beginTransaction();
	

		Agreement agreement = new Agreement();
		
		agreement.setAuthor("Rada");
		agreement.setName("Smlouva s validnim changem");
		agreement.setType("sla");
		
		agreement.setServiceDescritption("popis služby");
		agreement.setSlaDescription("popis dohody");
		
		Glossary g = new Glossary();
		g.setDescription("nejnovejsi ");
		g.setName("nazev slovniku");
		
		Glossary g2 = new Glossary();
		g2.setDescription("nejnovejsi 2");
		g2.setName("nazev slovniku 2");
		
		
		/*g.addTerm(new Term("novy", "vysvìtlení pojmu abeceda"));
		g.addTerm(new Term("novy2", "nìco hezkého o pepovi"));*/
		
		
		//agreement.addGlossary(g);
		//agreement.addGlossary(g2);
		
		Contact c1 = new Contact();
		c1.setCountry("Czech republic");
		c1.setStreet("tady");
	
		Contact c2 = new Contact();
		c2.setCountry("Maïarostan");
		c2.setStreet("tam");
	
		
		InvolvedParty party = new InvolvedParty();
		party.setName("poskytovatel");
		party.setRole("provider");
		/*party.addSignatory(new Signatory("Radek", new Date(75*365*3600)));*/
		party.setContact(c1);
		
		InvolvedParty party2 = new InvolvedParty();
		party2.setName("zakaznik");
		party2.setRole("customer");
		party2.setContact(c2);
		/*party2.addSignatory(new Signatory("pepa", new Date()));*/
		
		//agreement.addInvolvesParty(party);
		//agreement.addInvolvesParty(party2);
		
		
		ChangeManagement chm = new ChangeManagement("popis");
		Contact c = new Contact();
		c.setWeb("www.rockoveskoly.cz");
		
		chm.setContact(c);
		agreement.setChangemanagement(chm);

		
		Date responseTime = new Date();
		
		responseTime.setHours(6);
		responseTime.setMinutes(0);
		responseTime.setSeconds(0);
		
		Date open = new Date();
		Date close = new Date();
		
		open.setHours(6);
		open.setMinutes(0);
		open.setSeconds(0);
		
		close.setHours(18);
		close.setMinutes(0);
		close.setSeconds(0);
		

		

		
		CustomerSupport csup = new CustomerSupport();
		csup.setContact(c);
		csup.setDescription("pospis supposrtu");
		csup.setNote("poznamecka");
		csup.setResponse("response");
		csup.setResponseTime(responseTime);
		
		AccessTime at = new AccessTime("mon", open, close);
		System.out.println(at.toString());

		/*
		csup.addAccessTime(new AccessTime("mon", open, close));
		csup.addAccessTime(new AccessTime("tue", open, close));
		csup.addAccessTime(new AccessTime("wed", open, close));
		csup.addAccessTime(new AccessTime("thu", open, close));
		*/
		agreement.setCustomerSupport(csup);

		
		ServiceHours sh = new ServiceHours();
		sh.setDescription("popis oteviracich hodin");
		/*
		sh.addAccessTime(new AccessTime("mon", open, close));
		sh.addAccessTime(new AccessTime("tue", open, close));
		sh.addAccessTime(new AccessTime("wed", open, close));
		sh.addAccessTime(new AccessTime("thu", open, close));

		sh.addEvent(new Event(open, close, "other", "s indexem"));
		sh.addEvent(new Event(open, close, "shutdown", "druha s indexem"));
		*/
		agreement.setServiceHours(sh);
		
		agreement.setValidFrom(open);
		agreement.setValidTo(close);
		


		Treshold t1 = new Treshold();
		t1.setValue(1.2);
		/*
		t1.addListener(new Listener("adresa lsitenru", 55));
		t1.addListener(new Listener("adresa lsitenru 2", 58));
		*/
		

		
		//Metric m = new Metric("metrika", "adresa", "csv","", tresholds, values);
		Metric m = new Metric();
		m.setInputAddress("adresa");
		m.setName("jmeno");
		m.setInputAddress("aa");
		m.setInputFormat("csv");
		m.setInputType("csv");		
		
		
		Date d = new Date();
		/*m.addMetricValue(new MetricValue(d, (float) 1.88));
		m.addMetricValue(new MetricValue(new Date(d.getTime()+3600), (float) 1.98));
		
		m.addTreshold(t1);*/
		
		
		Performance p = new Performance();
		p.setDescription("popis");
		/*p.addMetric(m);*/

		agreement.setPerformance(p);
		
		Availability a = new Availability("novááááá", m);
		
		//agreement.addAvailability(a);
		
		
		Report r = new Report("novy report", "popis reportu", "weekly", 20, 1);
		/*r.addContact(c1);
		r.addMetric(m);
		*/
		//agreement.addReport(r);
		
		
		session.save(agreement);

		session.getTransaction().commit();
	}

}
