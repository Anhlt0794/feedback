package feedback.core.services.exceptions;

/**
 * Created by Hien on 5/19/2015.
 */
public class ManagerDoesNotExistException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ManagerDoesNotExistException() {
    }

    public ManagerDoesNotExistException(String message) {
        super(message);
    }

    public ManagerDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManagerDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public ManagerDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
