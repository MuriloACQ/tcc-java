package exception;

public class InconsistentDataException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InconsistentDataException() {
	}

	public InconsistentDataException(String message) {
		super(message);
	}

	public InconsistentDataException(Throwable cause) {
		super(cause);
	}

	public InconsistentDataException(String message, Throwable cause) {
		super(message, cause);
	}
}
