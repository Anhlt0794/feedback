package feedback.core.services.exceptions;

/**
 * Created by Hien on 5/19/2015.
 */
public class ManagerExistException extends  RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ManagerExistException() {
    }

    public ManagerExistException(String message) {
        super(message);
    }

    public ManagerExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManagerExistException(Throwable cause) {
        super(cause);
    }

    public ManagerExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
