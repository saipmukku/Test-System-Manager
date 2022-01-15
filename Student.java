package onlineTest;

import java.util.HashMap;
import java.util.TreeMap;

public class Student {
	
	private HashMap<Integer, StudentAnswerSheet> studentAnswers; // Keys map to an exam
	private TreeMap<Integer, Exam> studentExams;
	private String name;
	
	public Student(String n) {
		
		studentAnswers = new HashMap<Integer, StudentAnswerSheet>();
		name = n;
		
	}
	
	public void setExamReference(TreeMap<Integer, Exam> exams) {
		
		studentExams = exams;
		
	}
	
	public void generateAnswerSheets() {
		
		for(Integer i : studentExams.keySet()) {
			
			if(!(studentAnswers.containsKey(i))) {
			
				studentAnswers.put(i, new StudentAnswerSheet());
				
			}
			
		}
		
	}
	
	public void answer(int aId, int qId, Answer ans) {
		
		studentAnswers.get(aId).answer(qId, ans);
		
	}

	public double getQuestionGrade(int examId, int qId) {

		Answer studentAns = studentAnswers.get(examId).getStudentAnswer(qId);
		Answer correctAns = studentExams.get(examId).getQuestion(qId).getRawAnswer();
		int numOfCorrectAnswers = correctAns instanceof MCFITBAnswer ? ((MCFITBAnswer) correctAns).getAnswer().length : 1;
		double eachRightChoiceWorth = studentExams.get(examId).getQuestion(qId).getPoints() / numOfCorrectAnswers;
		double pointsEarned = 0;
		
		if(correctAns instanceof MCFITBAnswer) {
			
			if(studentExams.get(examId).getQuestion(qId) instanceof FillInTheBlank) {
			
				for(String a : ((MCFITBAnswer) correctAns).getAnswer()) {
				
					for(String i : ((MCFITBAnswer) studentAns).getAnswer()) {
					
						if(i.equals(a)) {
						
							pointsEarned += eachRightChoiceWorth;
						
						}
					
					}
				
				}
			
			} else {
				
				if(((MCFITBAnswer) studentAns).getAnswer().length > ((MCFITBAnswer) correctAns).getAnswer().length) {
					
					return 0;
					
				}
				
				for(String i : ((MCFITBAnswer) studentAns).getAnswer()) {
					
					for(String a : ((MCFITBAnswer) correctAns).getAnswer()) {
					
						if(i.equals(a)) {
						
							pointsEarned += eachRightChoiceWorth;
						
						}
					
					}
				
				}
				
				if(pointsEarned == (eachRightChoiceWorth * numOfCorrectAnswers)) {
					
					return pointsEarned;
					
				}
				
				return 0;
				
			}
			
			return pointsEarned;
			
		} else {
			
			if(((TrueFalseAnswer) studentAns).getRawAnswer().equals(((TrueFalseAnswer) correctAns).getRawAnswer())) {
				
				return ((double) numOfCorrectAnswers) * eachRightChoiceWorth;
				
			}
			
		}
		
		return 0.0;
		
	}
	
	public Question getQuestion(int eId, int qId) {
		
		return studentExams.get(eId).getQuestion(qId);
		
	}
	
	public double gradeExam(int eId) {
		
		if(studentAnswers.get(eId) == null) {
			
			return 0;
			
		}

		double points = 0;
		
		for(Integer i : studentExams.get(eId).getSetOfQuestions()) {
			
			points += getQuestionGrade(eId, i);
			
		}
		
		return points;
		
	}
	
	public double getStudentCourseGrade() {
		
		double percent = 0;
		double allGrades = 0;
		
		for(Integer i : studentExams.keySet()) {
			
			allGrades += gradeExam(i);
			percent += allGrades / studentExams.get(i).getTotalPoints();
			allGrades = 0;
			
		}
		
		percent /= studentExams.keySet().size();
		
		return percent * 100;
		
	}

	public String getGradeReport(int eId) {
		
		Exam chosenExam = studentExams.get(eId);
		
		if(chosenExam == null) return null;
		
		String output = "";
		
		for(int i = 1; i < chosenExam.getSetOfQuestions().size() + 1; i++) {
			
			output += "Question #" + i + " " + getQuestionGrade(eId, i) + " points out of " + getQuestion(eId, i).getPoints();
			
			if(i != chosenExam.getSetOfQuestions().size()) {
				
				output += "\n";
				
			}
			
		}
		
		return output + "\nFinal Score: " + gradeExam(eId) + " out of " + chosenExam.getTotalPoints();
		
	}
	
	public String getName() {
		
		return name;
		
	}

}