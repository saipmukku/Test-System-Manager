package onlineTest;

public class MCFITBAnswer extends Answer {
	
	private String[] studentAnswer;
	
	public MCFITBAnswer(String[] studentAnswerArray) {
		
		super(studentAnswerArray);
		studentAnswer = studentAnswerArray;
		
	}
	
	public String[] getAnswer() {
		
		return studentAnswer;
		
	}

	public boolean equals(MCFITBAnswer check) {
		
		String thisAnswer = "";
		String methodAnswer = "";
		
		for(String i : studentAnswer) {
			
			thisAnswer += i;
			
		}
		
		for(String a : check.getAnswer()) {
			
			methodAnswer += a;
			
		}
		
		if(thisAnswer.equals(methodAnswer)) {
			
			return true;
			
		}
		
		return false;
		
	}

}
