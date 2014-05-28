package xdrozd07.dt.junit;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import xdrozd07.dt.connection.SessionFactoryUtil;
import xdrozd07.dt.domain.*;
import xdrozd07.dt.domain.metric.*;

public class InsertTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		
		
		Treshold[] tresholds = new Treshold[2];
		MetricValue[] values = new MetricValue[2];




		//Treshold t1 = new Treshold(1.2, listeners);
		Treshold t1 = new Treshold();
		t1.setValue(1.1);
		/*t1.addListener(new Listener("adresa lsitenru", 55));
		t1.addListener(new Listener("adresa lsitenru 2", 58));*/
		
		
		
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
		
		
		Availability a = new Availability("popis", m);
		
		

		//session.save(m);
		session.save(a);
		
		session.getTransaction().commit();
		
		
	}

}
