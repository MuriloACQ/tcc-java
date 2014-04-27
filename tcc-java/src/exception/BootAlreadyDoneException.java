package exception;

public class BootAlreadyDoneException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public BootAlreadyDoneException() {
	}

	public BootAlreadyDoneException(String message) {
		super(message);
	}

	public BootAlreadyDoneException(Throwable cause) {
		super(cause);
	}

	public BootAlreadyDoneException(String message, Throwable cause) {
		super(message, cause);
	}
}
