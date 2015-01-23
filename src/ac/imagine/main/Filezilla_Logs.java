package ac.imagine.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import ac.imagine.bdd.DAOFactory;
import ac.imagine.bdd.UpdateBDD;
import ac.imagine.beans.Filezilla;
import ac.imagine.exception.DAOException;
import ac.imagine.util.FilezillaProperties;

/**
 * Main Class
 * @author matleses
 * @version 1
 */
public class Filezilla_Logs {

	public static DAOFactory daoFactory;
	
	/**
	 * Object Logger of log4j PACKAGE, generates a log file
	 */
	private static Logger logger = Logger.getLogger(Filezilla_Logs.class);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		Properties prop = FilezillaProperties.getProperties();
		UpdateBDD upbdd = new UpdateBDD();
		// Instanciation of the DAOFactory 
		daoFactory = DAOFactory.getInstance();

		ArrayList<Filezilla> filezillaList = new ArrayList<Filezilla>();
		//Parse log files
		//Retrieve the current date and put it on yesterday
		LocalDateTime dateNow = LocalDateTime.now();
		dateNow = dateNow.minusDays(1);
		String dateNowFormat = dateNow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		//reading the text file
		try{
			File file = new File(prop.getProperty("Directory") + "fzs-"+ dateNowFormat +".log");
			InputStream ips = new FileInputStream(file); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			//scans the file
			while ((line=br.readLine())!=null){
				//test if error code is 150
				if(line.contains("150 Opening data channel for file")){
					Filezilla filezilla = new Filezilla();
					//cut the line in items
					filezilla.setCode(150);
					filezilla.setFilezilla_id(Integer.parseInt(line.substring(1, 7))); 
					String dateString = line.substring(9, 28);
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					Date date = sdf.parse(dateString);
					filezilla.setDate(date);
					String lineFinal = line.substring(31);
					if(lineFinal.contains("download")){
						filezilla.setType("download");
					}else filezilla.setType("upload");
					filezilla.setAccount(lineFinal.substring(0, lineFinal.indexOf(" ")));
					filezilla.setIp(lineFinal.substring(lineFinal.indexOf("(") + 1, lineFinal.indexOf(")")));
					filezilla.setFileName(lineFinal.substring(lineFinal.indexOf('"') + 1, lineFinal.lastIndexOf('"')));
					filezillaList.add(filezilla);
				}
			}
			try {
				upbdd.insertListFiles(daoFactory, filezillaList);
				logger.info(" Successful insert of the : " + dateNowFormat);
			} catch (DAOException e) {
				logger.error(" error during events insert " + e.getMessage());
			}finally{
				filezillaList = null;
			}
			br.close(); 
		}		
		catch (Exception e){
			logger.error(" error during the inputStream " + e.getMessage());
		}
		try { 
			Thread.sleep(120); 
		} catch (InterruptedException e) { 
			logger.error(" error during the thread sleep " + e.getMessage());
		} 
		try{
			System.exit(0);
		}catch(SecurityException e){
			logger.error(" error during the exit of this programm " + e.getMessage());
		}
	
	}
}
