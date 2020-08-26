package vocab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Word {
	
	private String word;
	private int proficiency;
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getProficiency() {
		return proficiency;
	}

	public void setProficiency(int proficiency) {
		this.proficiency = proficiency;
	}

	public Word(String word) {
		super();
		this.word = word;
		this.proficiency = 0;
	}
	
	public Word(String word, int proficiency) {
		super();
		this.word = word;
		this.proficiency = proficiency;
	}
	
	public List<String> getDefinitions() {
		return Database.searchDefinitions(this.word);
	}
	
	public String getFirstDefinition() {
		return Database.searchFirstDefinition(this.word);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Word w1 = new Word("find");
		System.out.println(w1.getDefinitions());
		System.out.println(w1.getFirstDefinition());
	}

}
