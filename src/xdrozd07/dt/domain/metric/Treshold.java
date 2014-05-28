package xdrozd07.dt.domain.metric;

//import java.util.Arrays;


/**
 * Treshold
 * Peristent class
 * @author Radek Drozd
 *
 */
public class Treshold {
	private int id;
	private double value;
	private Listener[] listeners;
	
	/**
	 * persistent constructor
	 */
	public Treshold(){
		
	}
	
	/**
	 * @param d
	 * @param listeners
	 */
	public Treshold(double d, Listener[] listeners){
		this.value = d;
		this.setListeners(listeners);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}

	public Listener[] getListeners() {
		return listeners;
	}

	public void setListeners(Listener[] listeners) {
		this.listeners = listeners;
	}
	
	/**
	 * adds listener to the end of listeners array
	 * @param listener
	 
	public void addListener(Listener listener){
		if(this.listeners != null){
			Listener[] tmp = Arrays.copyOf(this.listeners, this.listeners.length + 1);
			this.listeners = tmp;
		}else{
			this.listeners = new Listener[1];
		}
		this.listeners[this.listeners.length-1] = listener;
	}
	*/
}
