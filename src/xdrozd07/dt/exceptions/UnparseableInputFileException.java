/**
 * This exceptions is throw when it is not possible to parse the input file
 */

package xdrozd07.dt.exceptions;

public class UnparseableInputFileException extends Exception{
	
	public UnparseableInputFileException() {
		super();
	}
	
	public UnparseableInputFileException(String message) {
		super(message);
	}
	
	
	public UnparseableInputFileException(String message, Throwable cause) {
		super(message, cause); 
	}
	
	public UnparseableInputFileException(Throwable cause) { 
		super(cause); 
	}
}
