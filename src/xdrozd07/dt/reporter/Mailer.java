/**
 * This class sends new email
 */
package xdrozd07.dt.reporter;

import java.io.FileNotFoundException;

import org.apache.commons.mail.*;

import xdrozd07.dt.domain.Contact;



public class Mailer {

	MailerConfiguration mc;
	
	public static void send(String name, String message, Contact c) throws EmailException, FileNotFoundException{
		
		MailerConfiguration mc = MailerConfiguration.getConfigration();
		
		Email email = new SimpleEmail();
		email.setHostName(mc.getHostName());
		email.setSmtpPort(mc.getSmtpPort());
		email.setAuthenticator(new DefaultAuthenticator(mc.getUserName(), mc.getPassword()));
		email.setSSLOnConnect(true);
		email.setFrom(mc.getUserName());
		email.setSubject(name);
		email.setMsg(message);
		email.addTo(c.getEmail());
		email.send();

	}
	
}
