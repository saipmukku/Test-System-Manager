package onlineTest;

import java.util.Set;
import java.util.TreeMap;

public class Exam {
	
	private TreeMap<Integer, Question> questions; // Keys map to question on the exam, which ALSO stores the answer to the question
	private String title;
	private int numOfQuestions = 0; 
	
	public Exam(String tit) {
		
		questions = new TreeMap<Integer, Question>();
		title = tit;
		
	}
	
	public Exam(Exam e) {
		
		questions = e.getMapOfQuestions();
		title = e.getTitle();
		
	}
	
	public void addQuestion(Question q) {
		
		numOfQuestions++;
		questions.put(numOfQuestions, q);
		
	}
	
	public Question getQuestion(int qId) {
		
		if(questions.containsKey(qId)) {
			
			return questions.get(qId);
			
		}
		
		return null;
		
	}
	
	public String getKey() {
		
		String answer = "";
		
		for(Integer i : questions.keySet()) {
			
			answer += questions.get(i).getQuestionInfo() + "\n";
			
		}
		
		return answer;
		
	}
	
	public String getTitle() {
		
		return title;
		
	}
	
	public TreeMap<Integer, Question> getMapOfQuestions(){
		
		return questions;
		
	}
	
	public double getTotalPoints() {
		
		Set<Integer> numbers = questions.keySet();
		double points = 0;
		
		for(Integer i : numbers) {
			
			points += questions.get(i).getPoints();
			
		}
		
		return points;
		
	}
	
	public Set<Integer> getSetOfQuestions() {
		
		return questions.keySet();
		
	}
	
}
