package onlineTest;

import java.util.Arrays;

public class Question {
	
	protected Answer ans;
	protected String text;
	protected double points;
	protected int questionNum;
	
	public Question(Answer a, String t, double p, int qt) {
		
		ans = a;
		text = t;
		questionNum = qt;
		points = p;
		
	}
	
	public Answer getRawAnswer() {
		
		return ans;
		
	}
	
	public String getQuestionAnswer() {
		
		if(ans instanceof MCFITBAnswer) {
			
			String[] answers = ((MCFITBAnswer) ans).getAnswer();
			String answer = "[";
			Arrays.sort(answers);
			
			for(int i = 0; i < answers.length; i++) {
				
				answer += answers[i];
				
				if(i == answers.length - 1) {
					
					answer += "]";
					
				} else {
					
					answer += ", ";
					
				}
				
			}
			
			return answer;
			
		}
		
		return ((TrueFalseAnswer) ans).getAnswer();
		
	}
	
	public double getPoints() {
		
		return points;
		
	}

	public String getQuestionInfo() {
		
		String answer = "";
		
		answer += "Question Text: " + text + "\nPoints: " + points + "\nCorrect Answer: " + getQuestionAnswer();
		
		return answer;
		
	}
	
}
