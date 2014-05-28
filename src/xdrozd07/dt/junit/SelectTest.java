package xdrozd07.dt.junit;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import xdrozd07.dt.connection.SessionFactoryUtil;
import xdrozd07.dt.domain.Agreement;
import xdrozd07.dt.domain.glossary.Glossary;

public class SelectTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();		
		
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Agreement> agreements =  session.createQuery("FROM Agreement").list();
			
			for (Iterator<Agreement> iterator1 = agreements.iterator(); iterator1.hasNext(); ){
				Agreement agreement = (Agreement) iterator1.next(); 
				System.out.print("Id: " + agreement.getId());
				System.out.print("  Name: " + agreement.getName()); 
				System.out.print("  Author: " + agreement.getAuthor() + "\n"); 
							     
				 Glossary[] glosaries = agreement.getGlossaries();
				 
			     for (Glossary glossary : glosaries){
			    	 System.out.println("Glossary: " + glossary.getId() + " " + glossary.getName()); 
			     }
			}
			tx.commit();
		
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
		}finally {
			
		}
		
	}

}
