package ac.imagine.exception;

/**
 * Class managing exceptions that can be generated during the connection to the database
 * @author matleses
 * @version 1
 */
public class DAOConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DAOConfigurationException(String message) {
		super(message);
	}

	public DAOConfigurationException(Throwable cause) {
		super(cause);
	}

	public DAOConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

}
