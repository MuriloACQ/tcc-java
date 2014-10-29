package exception;

public class UnsupportedSystemTrayException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public UnsupportedSystemTrayException() {
	}

	public UnsupportedSystemTrayException(String message) {
		super(message);
	}

	public UnsupportedSystemTrayException(Throwable cause) {
		super(cause);
	}

	public UnsupportedSystemTrayException(String message, Throwable cause) {
		super(message, cause);
	}
}
