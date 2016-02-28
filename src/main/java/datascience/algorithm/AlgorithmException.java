package datascience.algorithm;

public class AlgorithmException extends RuntimeException {
	private String message;

	public AlgorithmException(String message) {
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
