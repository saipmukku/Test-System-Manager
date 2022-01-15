package onlineTest;

import java.util.HashMap;

public class StudentAnswerSheet {
	
	private HashMap<Integer, Answer> studentsAnswers; // Each answer object corresponds to the answer for that respective question on the exam
	
	public StudentAnswerSheet() {
		
		studentsAnswers = new HashMap<Integer, Answer>();
		
	}
	
	public void answer(int qNum, Answer ans) {
		
		studentsAnswers.put(qNum, ans);
		
	}
	
	public Answer getStudentAnswer(int qNum) {
		
		return studentsAnswers.get(qNum);
		
	}

}
