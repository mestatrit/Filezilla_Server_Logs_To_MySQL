package ac.imagine.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * Bean who represent an event in the Filezilla Server Logs
 * @author matleses
 * @version 1
 */
public class Filezilla  implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * unique identifier in the database
	 */
	private int id ;

	/**
	 * identifier of Filezilla Server
	 */
	private int filezilla_id ;

	/**
	 * date of the event
	 */
	private Date date ;

	/**
	 * account name (user in Filezilla Client)
	 */
	private String account ;

	/**
	 * ip of the host who created the event
	 */
	private String ip ;

	/**
	 * FTP error code 
	 */
	private int code ;

	/**
	 * Name of the file for this event
	 */
	private String fileName ;

	/**
	 * Type of this event : download or upload
	 */
	private String type ;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFilezilla_id() {
		return filezilla_id;
	}

	public void setFilezilla_id(int filezilla_id) {
		this.filezilla_id = filezilla_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Filezilla [filezilla_id=" + filezilla_id + ", date=" + date
				+ ", account=" + account + ", ip=" + ip + ", code=" + code
				+ ", fileName=" + fileName + ", type=" + type + "]";
	}
}
