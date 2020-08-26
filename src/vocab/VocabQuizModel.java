package vocab;

import java.util.List;
import java.util.Random;

public class VocabQuizModel {
	
	private List<Word> words;
	private int answer;
	private String question;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private int choice;
	
	public List<Word> getWords() {
		return words;
	}

	public int getAnswer() {
		return answer;
	}
	
	public String getAnswer1() {
		return answer1;
	}
	
	public String getAnswer2() {
		return answer2;
	}
	
	public String getAnswer3() {
		return answer3;
	}
	
	public String getAnswer4() {
		return answer4;
	}

	public String getQuestion() {
		return question;
	}
	
	public void setChoice(int choice) {
		this.choice = choice; 
	}
	
	public boolean isCorrect() {
		return this.choice == this.answer;
	}

	public void getNewQuiz() {
		WordList wl = new WordList();
		words = wl.get4RandomWord();
		Random randomGenerator = new Random();
		answer = randomGenerator.nextInt(4) + 1;
		choice = -1;
		question = words.get(answer - 1).getFirstDefinition();
		answer1 = words.get(0).getWord();
		answer2 = words.get(1).getWord();
		answer3 = words.get(2).getWord();
		answer4 = words.get(3).getWord();
	}
	
	public static void main(String args[]) {
		VocabQuizModel quiz = new VocabQuizModel();
		quiz.getNewQuiz();
		System.out.println(quiz.getWords());
		System.out.println(quiz.getAnswer());
		System.out.println(quiz.getQuestion());
		System.out.println();
	}
}
