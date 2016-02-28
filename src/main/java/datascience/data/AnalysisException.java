package datascience.data;

public class AnalysisException extends RuntimeException{
	private String message;

	public AnalysisException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}	
	

}
