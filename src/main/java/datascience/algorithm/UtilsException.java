package datascience.algorithm;

public class UtilsException extends RuntimeException{
	private String message;

	public UtilsException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
