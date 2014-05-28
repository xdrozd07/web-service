package xdrozd07.dt.exceptions;


/**
 * This exception sets in when some kind of data are not 
 * @author Radek Drozd
 *
 */
public class InvalidDataException extends Exception{
	
	public InvalidDataException() {
		super();
	}
	
	public InvalidDataException(String message) {
		super(message);
	}
	
	
	public InvalidDataException(String message, Throwable cause) {
		super(message, cause); 
	}
	
	public InvalidDataException(Throwable cause) { 
		super(cause); 
	}
}