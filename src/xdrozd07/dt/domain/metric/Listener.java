package xdrozd07.dt.domain.metric;



/**
 * Metric listener
 * persistent  class
 * @author Radek Drozd
 *
 */
public class Listener {
	private int id;
	private String address;
	private int port;
	
	/**
	 * Persistent constructor
	 */
	public Listener(){
		
	}
	
	/**
	 * Complete constructor
	 * @param address
	 * @param port
	 */
	public Listener(String address, int port){
		this.address = address;
		this.port = port;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
}
