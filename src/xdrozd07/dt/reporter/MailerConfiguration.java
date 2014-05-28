package xdrozd07.dt.reporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.JSONObject;


public class MailerConfiguration {

	String configFile = "C:\\Users\\Uživatel\\Desktop\\radek\\workspace2\\dip\\mailer-config.json";
	
	private static MailerConfiguration instance;
	
	private  String hostName;
	private  String userName;
	private  String password;
	private  int smtpPort;
	

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

	/**
	 * singleton instance getter
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static MailerConfiguration getConfigration() throws FileNotFoundException{
		if(instance == null){
			instance = new MailerConfiguration();
		}
		return instance;
	}
	
	private MailerConfiguration() throws FileNotFoundException{
		
		File f = new File(this.configFile);
		
		
		String s = new Scanner(f).useDelimiter("\\Z").next();
					
		JSONObject jConf = new JSONObject(s);

		this.hostName = jConf.getString("hostName");
		this.userName = jConf.getString("userName");
		this.password = jConf.getString("password");
		this.smtpPort = jConf.getInt("smtpPort");		
		
		
	}
		
	
	public String toString(){
		String conf = "";
		
		conf += "host name: " + this.hostName + ", ";
		conf += "user name: " + this.userName + ", ";
		conf += "password: " + this.password + ", ";
		conf += "smtp port: " + this.smtpPort;

		return conf;
	}
	
}
