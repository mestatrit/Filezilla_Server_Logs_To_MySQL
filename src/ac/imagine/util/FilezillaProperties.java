package ac.imagine.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Load the properties file
 * @author matleses
 * @version 1
 */
public class FilezillaProperties {
	
	/**
	 * Object Logger of log4j PACKAGE, generates a log file
	 */
	private static Logger logger = Logger.getLogger(FilezillaProperties.class);

	/**
	 * retrieve a property file
	 * @return the properties
	 */
	public static Properties getProperties(){
		Properties prop = new Properties();
		InputStream inputProp = null;
		try{
			inputProp = new FileInputStream("FilezillaLogs_ToMySQL.properties");
			// load a properties file
			prop.load(inputProp);
		} catch (IOException e) {
			logger.error(" Error during the load of the properties : " + e.getMessage());
		} finally {
			if (inputProp != null) {
				try {
					inputProp.close();
				} catch (IOException e) {
					logger.error(" Error during closing of the input Stream of the properties file : " + e.getMessage());
				}
			}
		}
		return prop;
	}
}
