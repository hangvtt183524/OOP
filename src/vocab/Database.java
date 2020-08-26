package vocab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.ext.PorterStemmer;

public class Database {
	/**
	 * Connect to database that contains dictionary and user's words
	 * 
	 * @return the Connection
	 */
	public static Connection connect() {
		Connection conn = null;
		try {
            String url = "jdbc:sqlite:database/Dictionary.db";
            conn = DriverManager.getConnection(url);
            
            //System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
		return conn;
	}

	public static boolean isInDatabase(String word) {
		String sql = "SELECT COUNT(word) FROM entries WHERE word=?";
		Connection conn = null;
		try {
			conn = Database.connect();
			 PreparedStatement pstmt  = conn.prepareStatement(sql);
	            
	            // set the value
	            pstmt.setString(1, word);
	            //
	            ResultSet rs  = pstmt.executeQuery();
	            
	            // loop through the result set
	            rs.next();
	            int count = rs.getInt(1);
	            return count >= 1;
	        } 
		catch (SQLException e) {
	            System.out.println(e.getMessage());
	            return false;
	        }
		finally {
			if (conn != null) {
			    try {
			      conn.close(); 
			    } catch (SQLException e) {
			      /* handle exception */
			    }
			}
		}
	}
	
	public static List<String> searchDefinitions(String word) {
		Connection conn = null;
	      try {
	    	 conn = Database.connect();  	 
	    	 List<String> defs = new ArrayList<String>();
	         Statement stat = conn.createStatement();

	         if (isInDatabase(word)) {
	        	 ResultSet rs = stat.executeQuery("SELECT * FROM entries WHERE word = '" + word + "';");
		         int i = 0;
		         while (rs.next()) {
		        	 i++;
		        	 defs.add(i + ". "+ rs.getString("word") +"("+ rs.getString("wordtype")+ ")" + " : " + rs.getString("definition") + '\n');
		         }
	         }
	         // handle various forms of the word
	         else {
	        	 PorterStemmer stemmer = new PorterStemmer();
	        	 stemmer.setCurrent(word); //set string you need to stem
	        	 stemmer.stem();  //stem the word
	        	 String nword = stemmer.getCurrent();//get the stemmed word
		         ResultSet rs = stat.executeQuery("SELECT * FROM entries WHERE word = '" + nword + "';");
		         int i = 0;
		         while (rs.next()) {
		        	 i++;
		        	 defs.add(i + ". "+ rs.getString("word") +"("+ rs.getString("wordtype")+ ")" + " : " + rs.getString("definition") + '\n');
		         }
		     }
	         return defs;
	      }
	      catch (Exception e) {
	    	  e.printStackTrace();
	    	  return null;
	      }
	      finally {
				if (conn != null) {
				    try {
				      conn.close(); 
				    } catch (SQLException e) {
				      /* handle exception */
				    }
				}
			}
	}
	
	public static String searchFirstDefinition(String word) {
		 Connection conn = null;
	      try {
	    	 conn = Database.connect();  	 
	         Statement stat = conn.createStatement();

	         if (isInDatabase(word)) {
	        	 ResultSet rs = stat.executeQuery("SELECT * FROM entries WHERE word = '" + word + "' LIMIT 1;");
		         rs.next();
		         return rs.getString("definition");
	         }
	         // handle various forms of the word
	         else {
	        	 PorterStemmer stemmer = new PorterStemmer();
	        	 stemmer.setCurrent(word); //set string you need to stem
	        	 stemmer.stem();  //stem the word
	        	 String nword = stemmer.getCurrent();//get the stemmed word
		         ResultSet rs = stat.executeQuery("SELECT * FROM entries WHERE word = '" + nword + "' LIMIT 1;");
		         rs.next();
		         return rs.getString("definition");
		     }
	      }
	      catch (Exception e) {
	    	  e.printStackTrace();
	    	  return null;
	      }
	      finally {
				if (conn != null) {
				    try {
				      conn.close(); 
				    } catch (SQLException e) {
				      /* handle exception */
				    }
				}
			}
	}
	
	public static void main(String args[]) {
		System.out.println(Database.isInDatabase("find"));
		System.out.println(Database.searchDefinitions("finded"));
		System.out.println(Database.searchFirstDefinition("finded"));
	}
}
