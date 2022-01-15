package onlineTest;

public class TrueFalseAnswer extends Answer {
	
	private Boolean studentChoice;
	
	public TrueFalseAnswer(boolean studentAns) {
		
		super((Boolean) studentAns);
		studentChoice = (Boolean) studentAns;
		
	}
	
	public Boolean getRawAnswer() {
		
		return studentChoice;
		
	}
	
	public String getAnswer() {
		
		if(studentChoice.equals(false)) {
			
			return "False";
			
		}
		
		return "True";
		
	}
	
	public boolean equals(TrueFalseAnswer check) {
		
		if(check.getAnswer().equals(studentChoice)) {
			
			return true;
			
		}
		
		return false;
		
	}

}
