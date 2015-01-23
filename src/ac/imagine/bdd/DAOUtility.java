package ac.imagine.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * Utility class for closure and initialization of the database
 * @author matleses
 * @version 1
 */
public final class DAOUtility {
	
	/**
	 * Object Logger of log4j PACKAGE, generates a log file
	 */
	private static Logger logger = Logger.getLogger(DAOUtility.class);

	/**
	 * Silent closing the resultset
	 * @param resultSet
	 */
	public static void silentClosing( ResultSet resultSet ) {
		if ( resultSet != null ) {
			try {
				resultSet.close();
			} catch ( SQLException e ) {
				logger.error(" Failure to close the ResultSet : " + e.getMessage());
			}
		}
	}

	/**
	 * Silent closing the statement
	 * @param statement
	 */
	public static void silentClosing( Statement statement ) {
		if ( statement != null ) {
			try {
				statement.close();
			} catch ( SQLException e ) {
				logger.error(" Failure to close the Statement : " + e.getMessage());
			}
		}
	}

	/**
	 * Silent closing the connection
	 * @param connection
	 */
	public static void silentClosing( Connection connection ) {
		if ( connection != null ) {
			try {
				connection.close();
			} catch ( SQLException e ) {
				logger.error(" Failure to close the connection : " + e.getMessage());
			}
		}
	}

	/**
	 * Silent closures of the statement and connection
	 * @param statement
	 * @param connection
	 */
	public static void silentClosing( Statement statement, Connection connection ) {
		silentClosing( statement );
		silentClosing( connection );
	}

	/**
	 * Silent closures of the resultset, statement and connexion
	 * @param resultSet
	 * @param statement
	 * @param connection
	 */
	public static void silentClosing( ResultSet resultSet, Statement statement, Connection connection ) {
		silentClosing( resultSet );
		silentClosing( statement );
		silentClosing( connection );
	}
}



