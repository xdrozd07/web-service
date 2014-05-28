package xdrozd07.dt.service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.persistence.PersistenceUnit;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import xdrozd07.dt.connection.SessionFactoryUtil;
import xdrozd07.dt.domain.Agreement;
import xdrozd07.dt.domain.Availability;
import xdrozd07.dt.domain.Performance;
import xdrozd07.dt.domain.Report;
import xdrozd07.dt.domain.metric.Metric;
import xdrozd07.dt.logger.LoggerUtil;
import xdrozd07.dt.reporter.Pokus;
import xdrozd07.dt.reporter.Reporter;


/**
 * SLA management service
 * 
 * @author Radek Drozd
 *
 * 
 * provides single point of contact for service usage, all the other classes are
 * hidden behind this class
 * 
 * 
 * service allows user to define SLAs, manage them, define metrics and monitor them
 * It's possible to set up a periodic reports about SLA based metrics usage
 */

public class SlaManagement {
	
	private Session session;
	
	
	private ScheduledExecutorService scheduler = null;
	
	
	
	/**
	 * sets up private fields before service usage
	 */
	private void before(){

		this.session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		if(! this.session.isOpen()){
			this.session = SessionFactoryUtil.getSessionFactory().openSession();
		}
	}
	

	
	/**
	 * Adds agreement do the database
	 * @param agreement to be add
	 */
	public void addAgreement(xdrozd07.dt.domain.Agreement agreement){
		
		this.before();
		
		Transaction tx = this.session.beginTransaction();
		try{
			int predecessorId = -1;
			
			if(agreement.getPredecessor() != null){
				predecessorId = agreement.getPredecessor().getId();
				agreement.setPredecessor(null);
			}
			
			session.save(agreement);
			
			
			// kind of hack to avoid hibernate fail (duplicate entries)
			if(predecessorId != -1){
				int currentId = agreement.getId();
				
				String hql = "UPDATE Agreement a SET predecessor = :predecessorId where a.id = :currentId";
				Query query = session.createQuery(hql).setInteger("predecessorId", predecessorId);
				query.setInteger("currentId", currentId);
				
				query.executeUpdate();
				
			}
			
			
			
			System.out.println("ID VLOZENE: "+agreement.getId());
			tx.commit();
		}catch(Exception e){
			LoggerUtil.log(e);
		}
	}
	
	/**
	 * Loads all agreements from database and return them as a list
	 * @return list of all agreements 
	 */
	public xdrozd07.dt.domain.Agreement[] getAgreements(){
		this.before();
		
		xdrozd07.dt.domain.Agreement[] result = null;
		Transaction tx = this.session.beginTransaction();
		try {
			
			String hql = "FROM Agreement";
			Query query = session.createQuery(hql);
			
			List<xdrozd07.dt.domain.Agreement> resultList = query.list();
			result = new xdrozd07.dt.domain.Agreement[resultList.size()];
			
			resultList.toArray(result);
			
			tx.commit();
			
		}catch(Exception e){
			LoggerUtil.log(e);
		}
		

		return result;
	}
	
	/**
	 * Returns all agreements owned by specified user
	 * @param author specification
	 * @return
	 */
	public 	xdrozd07.dt.domain.Agreement[] getAuthorsAgreements(String author){
		
		xdrozd07.dt.domain.Agreement[] result = null;
		

		this.before();
		Transaction tx = this.session.beginTransaction();
		
		try{

			
			String hql = "FROM Agreement A WHERE A.author = :author";
			Query query = session.createQuery(hql).setParameter("author", author);
			
			List<xdrozd07.dt.domain.Agreement> resultList = query.list();
			result = new xdrozd07.dt.domain.Agreement[resultList.size()];

			tx.commit();
			
			resultList.toArray(result);
		}catch(Exception e){
			tx.rollback();
			LoggerUtil.log(e);
		}
		
		
		return result;
	}
	
	/**
	 * saves modified agreement
	 * @param agreement
	 */
	public void updateAgreement(xdrozd07.dt.domain.Agreement agreement){

		this.before();
		
		// kind of hack to avoid hibernate fail (duplicate entries)
		int predecessorId = -1;
		int currentId = agreement.getId();
		if(agreement.getPredecessor() != null){
			predecessorId = agreement.getPredecessor().getId();
			agreement.setPredecessor(null);
		}
		
		this.updateMetrics(agreement);		
		
		Transaction tx = this.session.beginTransaction();
		try{
			

			System.out.println("tady");
			this.session.saveOrUpdate(agreement);
			
			System.out.println("tady");
			
			// kind of hack to avoid hibernate fail (duplicate entries)
			if(predecessorId != -1){
				
				String hql = "UPDATE Agreement a SET predecessor = :predecessorId where a.id = :currentId";
				Query query = session.createQuery(hql).setInteger("predecessorId", predecessorId);
				query.setInteger("currentId", currentId);
				
				query.executeUpdate();
				
			}

			System.out.println("tady");
			
			
			//agreement = (xdrozd07.dt.domain.Agreement) this.session.merge(agreement);
			//this.session.save(agreement);
			
			// dirty hack to delete all orphans who are still in a database after update
			// this is a must because arrays does not fully support update operations
			// and SETs cannot be used, because of WS transpot restrictions
			
			
			// involved party
			String hql = "DELETE FROM InvolvedParty WHERE sla_id is null";
			Query query = session.createQuery(hql);
			query.executeUpdate();
			
			// Signatory
			hql = "DELETE FROM Signatory WHERE involved_party_id is null";
			query = session.createQuery(hql);
			query.executeUpdate();
			
			
			//glossary
			hql = "DELETE FROM Glossary WHERE sla_id is null";
			query = session.createQuery(hql);
			query.executeUpdate();
			
			//term
			hql = "DELETE FROM Term WHERE glossary_id is null";
			query = session.createQuery(hql);
			query.executeUpdate();
			
			//event
			hql = "DELETE FROM Event WHERE servicehours_id is null";
			query = session.createQuery(hql);
			query.executeUpdate();
			
			//threshold
			hql = "DELETE FROM Treshold WHERE metric_id is null";
			query = session.createQuery(hql);
			query.executeUpdate();
			
			//listener
			hql = "DELETE FROM Listener WHERE treshold_id is null";
			query = session.createQuery(hql);
			query.executeUpdate();
			
			//metric value
			hql = "DELETE FROM MetricValue WHERE metric_id is null";
			query = session.createQuery(hql);
			query.executeUpdate();
			
			// performace
			hql = "DELETE FROM Performance p WHERE p NOT IN (SELECT a.performance FROM Agreement a)";
			query = session.createQuery(hql);
			query.executeUpdate();


			// service hours
			hql = "DELETE FROM ServiceHours sh WHERE sh NOT IN (SELECT a.serviceHours FROM Agreement a)";
			query = session.createQuery(hql);
			query.executeUpdate();

			// service hours
			hql = "DELETE FROM CustomerSupport cs WHERE cs NOT IN (SELECT a.customerSupport FROM Agreement a)";
			query = session.createQuery(hql);
			query.executeUpdate();
			

			// service hours
			hql = "DELETE FROM ChangeManagement chm WHERE chm NOT IN (SELECT a.changemanagement FROM Agreement a)";
			query = session.createQuery(hql);
			query.executeUpdate();
			
			hql = "DELETE FROM Report WHERE agreement_id is null";
			query = session.createQuery(hql);
			query.executeUpdate();
			
			hql = "DELETE FROM Availability WHERE sla_id is null";
			query = session.createQuery(hql);
			query.executeUpdate();
			
			tx.commit();
			
		}catch(Exception e){
			LoggerUtil.log(e);
			tx.rollback();
		
		}
	
	}
	
	/**
	 * Deletes given agreement from database
	 * @param agreement
	 */
	public void deleteAgreement(xdrozd07.dt.domain.Agreement agreement){
		this.before();

		
		if(agreement.getPredecessor() != null){
			agreement.setPredecessor(null);
		}
		
		
		Transaction tx = this.session.beginTransaction();
		try {
			this.session.delete(agreement);

			tx.commit();
		}catch(Exception e){
			LoggerUtil.log(e);
		}
	}
	/**
	 * Connection test
	 * says hello to given string
	 * @param name
	 * @return hello
	 */
	public String connectionTest(String name){
		return "Hello "+name+"! Connection is OK";
	}

	
	/**
	 * this procedure walks thru all reports and makes their metric references correct
	 * it's kind a dirty hack because Web Service destroys the references
	 * @param a
	 */
	private void updateMetrics(Agreement a){
		
		// iterate over reports
		
		if(a.getReports() != null){
			for(Report r: a.getReports()){
				
				// iterate over its metrics
				if(r.getMetrics() != null){
					int i = 0;
					int lookupresult;
					for(Metric m: r.getMetrics()){
						
						// look to performance
						// if exists
						if(a.getPerformance() != null){
							lookupresult =  -1;
							
							int y = 0;
							for(Metric pm: a.getPerformance().getMetrics()){
								if(pm.getId() == m.getId()){
									lookupresult = y;
									break;
								}
								y++;
							}
							
							if(lookupresult != -1){
								// there is a metric with a same ID
								// so we will use a reference to it;
								r.getMetrics()[i] = a.getPerformance().getMetrics()[lookupresult];
							}
						}
						
						
						// look to availabilities
						// if exists
						if(a.getAvailability() != null){
							int y = 0;
							for(Availability av : a.getAvailability()){
								if(av.getMetric() != null && av.getMetric().getId() == m.getId()){
									
									r.getMetrics()[i] = a.getAvailability()[y].getMetric();
									break;
								}		
								y++;
							}
						}
						
						i++;
					}
				}
			}
		}
	}
}
