package ac.imagine.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import ac.imagine.beans.Filezilla;
import ac.imagine.exception.DAOException;

/**
 * Class responsible to insert the data of the log in the database
 * @author matleses
 * @version 1
 */
public class UpdateBDD {
	
	/**
	 * Object Logger of log4j PACKAGE, generates a log file
	 */
	private static Logger logger = Logger.getLogger(UpdateBDD.class);

	/**
	 * for either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 */
	private static int status;
	/**
	 * SQL query for insert the datas in the database
	 */
	private static final String SQL_INSERT_EVENT = "INSERT INTO files (filezilla_id, date, account, ip , code, fileName, type) VALUES (?, ?, ?, ?, ?, ?, ?) ";
	
	/**
	 * Insert the list of events into the database
	 * @param daoFactory
	 * @param list
	 * @return status
	 * @throws DAOException
	 */
	public int insertListFiles(DAOFactory daoFactory, ArrayList<Filezilla> list) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatementFile = null; 
		try {
			// Retrieving a connection from the Factory 
			connection = daoFactory.getConnection();
			for(Filezilla o : list) { 
				try{
					preparedStatementFile = initializationPreparedStatement( connection, SQL_INSERT_EVENT, true, o.getFilezilla_id(), o.getDate(), o.getAccount(), o.getIp(), o.getCode(), o.getFileName(), o.getType() );
					status = preparedStatementFile.executeUpdate();
				}catch(SQLException e){
					logger.error(" SQL error when inserting data into the database : " + e.getMessage());
					new DAOException("SQL error when inserting data into the database : " +e.getMessage());
				}
			}
		} catch ( SQLException e ) {
			logger.error(" Error connection : " + e.getMessage());
			new DAOException("Error connection : "+ e.getMessage());
		} finally {
			list = null;
			DAOUtility.silentClosing(preparedStatementFile, connection); 
		}
		return status;
	}

	/**
	 * Initialize the prepared statement
	 * @param connexion
	 * @param sql
	 * @param returnGeneratedKeys
	 * @param objets
	 * @return the prepared statement
	 * @throws SQLException
	 */
	public static PreparedStatement initializationPreparedStatement( Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
		for ( int i = 0; i < objets.length; i++ ) {
			preparedStatement.setObject( i + 1, objets[i] );
		}
		return preparedStatement;
	}
}
