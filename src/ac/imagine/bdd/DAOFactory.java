package ac.imagine.bdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import ac.imagine.exception.*;
import ac.imagine.util.FilezillaProperties;

/**
 * Class responsible for the instantiation of different DAO
 * @author matleses
 * @version 1
 */
public class DAOFactory {
	
	/**
	 * Object Logger of log4j PACKAGE, generates a log file
	 */
	private static Logger logger = Logger.getLogger(DAOFactory.class);

	BoneCP connectionPool = null ;

	/**
	 * Constructor
	 * @param connectionPool
	 * 			Objet BoneCP of the connection Pool
	 */
	DAOFactory( BoneCP connectionPool){
		this.connectionPool = connectionPool;
	}

	/**
	 * connects the configuration information from the properties file
	 * and loads the JDBC driver 
	 * @return an instance of DAOFactory
	 * @throws DAOConfigurationException
	 */
	public static DAOFactory getInstance() throws DAOConfigurationException {
		Properties prop = FilezillaProperties.getProperties();
		String url = prop.getProperty("Url");
		String driver = "com.mysql.jdbc.Driver";
		String userName = prop.getProperty("User");
		String password = prop.getProperty("Password");
		BoneCP connectionPool = null;

		try {
			Class.forName( driver );
		} catch ( ClassNotFoundException e ) {
			logger.error(" The driver is not found in the classpath " + e.getMessage());
			throw new DAOConfigurationException( " The driver is not found in the classpath ", e ); 
		}
		try {
			// Creating a connection pool configuration via the BoneCPConfig object and the various associated setters.
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(url);
			config.setUsername(userName);
			config.setPassword(password);
			// Creation of the pool from the configuration via the object BoneCP
			connectionPool = new BoneCP( config );
		} catch ( SQLException e ) {
			logger.error(" Connection pool configuration error " + e.getMessage());
			throw new DAOConfigurationException( " Connection pool configuration error ", e );
		}
		// Saving the pool created in an instance variable via a call to the constructor of the DAOFactory
		DAOFactory instance = new DAOFactory(connectionPool);
		return instance; 
	}

	/**
	 *  Method responsible for providing a connection to the database 
	 * @return The connection to the database
	 * @throws SQLException
	 */
	Connection getConnection() throws SQLException { 
		return connectionPool.getConnection();
	}
}

