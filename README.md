# Filezilla_Server_Logs_To_MySQL
Retrieves Filezilla Server logs in a database.  
Retrieve logged events in the logs Filezilla server and put them in a SQL database.  
The software recovers only the events of FTP error code 150 Opening data channel for file,
because it is interested only send or receive files.   
It recovers the execution log of the previous day.

### Software required :
* Java (TM) SE Runtime Environment 1.8
* MySQL or other database
* FileZilla Server

### FileZilla Server configuration :
* Check the box : the Enable logging to file
* Check the box : Use a different logfile each day
* Check the box : Delete old logfiles after 31 days

This will allow us to separate the logs daily and delete after 30 days in order not to overload the disk space over time.

### Setting the database :
Create a database named filezilla then import the file: Filezilla_Serveur_Logs_To_MySQL.sql

### Settings of the software :
You have to give in the config file "FilezillaLogs_ToMySQL.properties" :

Directory = C:\\Program Files (x86)\\FileZilla Server\\Logs\\   
URL = jdbc: mysql: //127.0.0.1: 3306 / Filezilla   
User = Filezilla  
Password = password  

Double click on the "runFilezillaLogs_ToMySQL.jar" file to start the program.
You can check that everything went well by consulting the log file generated in the same place: "Filezilla_Server_Logs_To_MySQL.log."

### Schedule the execution of the program :
You can use the tasks planner in Windows :

* Program script: C:\Program Files\Java\jre8\bin\java.exe
* Add arguments:  -jar c:\Filezilla_Server_Logs_To_MySQL.jar
* Start in: c:\

