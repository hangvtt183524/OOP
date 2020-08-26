package listening;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LessonListening {
	private String audio;
	private String transcript;
	
	public LessonListening(String nameDB, String nameTable, int numbericalOrder)
	{
		getInfoFromDB(nameDB, nameTable, numbericalOrder);
	}
	
	public String getAudio()
	{
		return this.audio;
	}
	
	public String getTranscript()
	{
		return this.transcript;
	}
	
	private void getInfoFromDB(String nameDB, String nameTable, int numbericalOrder)
	{
		Connection c = null;
	    Statement stmt = null;
		String s = new String();
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:"+nameDB);;
	         
	         Statement stat = c.createStatement();
	         ResultSet rs = stat.executeQuery("SELECT * FROM "+nameTable+" WHERE numbericalOrder LIKE '%"+numbericalOrder+"%';");
	         while (rs.next()) {
	        	 this.audio = rs.getString("audio");
	        	 this.transcript = rs.getString("transcript");
	         }
	         rs.close();
	         c.close();
	      }
	      catch ( Exception e ) {
	    	  e.printStackTrace();
	      }
	}
}
