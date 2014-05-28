package xdrozd07.dt.junit;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import xdrozd07.dt.connection.SessionFactoryUtil;
import xdrozd07.dt.domain.AccessTime;
import xdrozd07.dt.domain.Agreement;
import xdrozd07.dt.domain.glossary.Glossary;
import xdrozd07.dt.domain.glossary.Term;

public class UpdateTest {

	private static Session session;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		session = SessionFactoryUtil.getSessionFactory().getCurrentSession();		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {	
		
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
		
			AccessTime at = new AccessTime();
			at.setDay("mon");
			
			Date open = new Date();
			Date close = new Date();
			
			open.setHours(6);
			open.setMinutes(0);
			open.setSeconds(0);
			
			close.setHours(18);
			close.setMinutes(0);
			close.setSeconds(0);
			
			at.setClose(close);
			at.setOpen(open);
			
			session.save(at);
			
			tx.commit();
		
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
		}finally {
			
		}
	}
	
	@Test
	public void Test2(){
		session = SessionFactoryUtil.getSessionFactory().getCurrentSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Agreement a = (Agreement) session.get(Agreement.class, 11);
			
			System.out.println(a.getName());
			
			session.delete(a);
			
			
			tx.commit();
			//List <Term> terms  = agreements.get(0);
		
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
		}finally {
			
		}
	}

}
