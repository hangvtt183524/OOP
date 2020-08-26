package vocab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class WordList {
	
	private ArrayList<Word> wordList;
	
	public WordList() {
		wordList = new ArrayList<Word>();
		String sql = "SELECT * FROM user";
		Connection conn = null;
		
		try {
			conn = Database.connect();
			Statement stmt  = conn.createStatement();
		
			ResultSet rs  = stmt.executeQuery(sql);
			while (rs.next()) {
				String word = rs.getString("word");
				int proficiency = rs.getInt("proficiency");
				wordList.add(new Word(word, proficiency));
			}
		} 
		catch (SQLException e) {
	            System.out.println(e.getMessage());
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
	
	/**
	 * Get the user's list
	 * 
	 * @return ArrayList<String> This returns the user's word list
	 */
	public ArrayList<Word> getWordList() {
		return wordList;
	}
	
	public ArrayList<Word> getShuffledWordList() {
		ArrayList<Word> shuffledWordList = new ArrayList<Word>(wordList);
		Collections.shuffle(shuffledWordList);
		return shuffledWordList;
	}
	
	/**
	 * Insert a word into this word list
	 * 
	 * @param word A string
	 */
	public static void insertWord(String word) {
		String sql = "INSERT INTO user (word) VALUES(?)";
		Connection conn = null;
		
		try {
			conn = Database.connect();
			 PreparedStatement pstmt  = conn.prepareStatement(sql);
	            
	            pstmt.setString(1, word);
	            
	            pstmt.executeUpdate();
	        } 
		catch (SQLException e) {
	            System.out.println(e.getMessage());
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
	
	/**
	 * Insert all of the words in the array of string into this word list
	 * 
	 * @param words An array of string
	 */
	public static void insertWord(String[] words) {
		String sql = "INSERT INTO user (word) VALUES(?)";
		Connection conn = null;
		
		try {
			conn = Database.connect();
			 PreparedStatement pstmt  = conn.prepareStatement(sql);
	         for (String word : words) {
	            pstmt.setString(1, word);  
	            pstmt.executeUpdate();
	         }
	        } 
		catch (SQLException e) {
	            System.out.println(e.getMessage());
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
	
	/**
	 * 
	 * @return int This returns the number of words in this word list
	 */
	public int numberOfWords() {
		return wordList.size();
	}
	
	public Word getArbitraryWord() {
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(wordList.size());
		return wordList.get(index);
	}
	
	public List<Word> get4RandomWord() {
		List<Word> res = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		int index;
		Random randomGenerator = new Random();
		while (set.size() != 4) {
			index = randomGenerator.nextInt(wordList.size());
			set.add(index);
		}
		for (int i : set) {
			res.add(wordList.get(i));
		}
		return res;
	}
	
	public static boolean hasWord(String word) {
		String sql = "SELECT * FROM user WHERE word=?";
		Connection conn = null;
		
		try {
			conn = Database.connect();
			 PreparedStatement pstmt  = conn.prepareStatement(sql);
	            
	            // set the value
	            pstmt.setString(1, word);
	            //
	            ResultSet rs  = pstmt.executeQuery();
	            
	            // loop through the result set
	            return rs.next();
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
	
	public void updateProficiency() {
		String sql = "UPDATE user SET proficiency=? WHERE word=?";
		Connection conn = null;
		try {
			conn = Database.connect();
			 PreparedStatement pstmt  = conn.prepareStatement(sql);
	            
			for (Word w : this.wordList) {
	            // set the value
	            pstmt.setInt(1, w.getProficiency());
	            pstmt.setString(2, w.getWord());
	         
	            pstmt.executeUpdate();
	        }
	        } 
		catch (SQLException e) {
	            System.out.println(e.getMessage());
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
	
	public static void main(String[] args) {
	}
}