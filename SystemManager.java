package onlineTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.TreeMap;

public class SystemManager implements Manager, Serializable {

	private static final long serialVersionUID = 1L;
	private TreeMap<String, Student> students;
	private TreeMap<Integer, Exam> exams;
	private String[] letGrades;
	private double[] gradeCutoffs;
	
	public SystemManager() {
		
		students = new TreeMap<String, Student>();
		exams = new TreeMap<Integer, Exam>();
		
	}

	public boolean addExam(int examId, String title) {

		if(exams.containsKey(examId)) {
			
			return false;
			
		}
		
		exams.put(examId, new Exam(title));
		
		return true;
		
	}
	
	public void addTrueFalseQuestion(int examId, int questionNumber, String text, double points, boolean answer) {
		
		if(exams.get(examId) == null) {
			
			return;
			
		}
		
		exams.get(examId).addQuestion(new Question(new TrueFalseAnswer(answer), text, points, questionNumber));
		
	}
	
	public void addMultipleChoiceQuestion(int examId, int questionNumber, String text, double points, String[] answer) {

		if(exams.get(examId) == null) {
			
			return;
			
		}
		
		exams.get(examId).addQuestion(new MultipleChoice(new MCFITBAnswer(answer), text, points, questionNumber));
		
	}
	
	public void addFillInTheBlanksQuestion(int examId, int questionNumber, String text, double points, String[] answer) {
		
		if(exams.get(examId) == null) {
			
			return;
			
		}
		
		exams.get(examId).addQuestion(new FillInTheBlank(new MCFITBAnswer(answer), text, points, questionNumber));
		
	}

	public String getKey(int examId) {

		if(exams.containsKey(examId)) {
			
			return exams.get(examId).getKey();
			
		}
		
		return "Exam not found";
		
	}
	
	public boolean addStudent(String name) {
		
		if(students.containsKey(name)) {
			
			return false;
			
		}
		
		students.put(name, new Student(name));
		students.get(name).setExamReference(exams);
		students.get(name).generateAnswerSheets();
		
		return true;

	}
	
	public void answerTrueFalseQuestion(String studentName, int examId, int questionNumber, boolean answer) {
		
		students.get(studentName).generateAnswerSheets();
		students.get(studentName).answer(examId, questionNumber, new TrueFalseAnswer(answer));

	}
	
	public void answerMultipleChoiceQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		
		students.get(studentName).generateAnswerSheets();
		students.get(studentName).answer(examId, questionNumber, new MCFITBAnswer(answer));

	}
	
	public void answerFillInTheBlanksQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		
		students.get(studentName).generateAnswerSheets();
		students.get(studentName).answer(examId, questionNumber, new MCFITBAnswer(answer));

	}
	
	public double getExamScore(String studentName, int examId) {
		
		return students.get(studentName).gradeExam(examId);
		
	}
	
	public String getGradingReport(String studentName, int examId) {
		
		return students.get(studentName).getGradeReport(examId);
		
	}
	
	public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs) {
		
		letGrades = letterGrades;
		gradeCutoffs = cutoffs;
		
	}
	
	public double getCourseNumericGrade(String studentName) {

		return students.get(studentName).getStudentCourseGrade();
		
	}
	
	public String getCourseLetterGrade(String studentName) {
		
		double studentGrade = getCourseNumericGrade(studentName);
		
		for(int i = 0; i < gradeCutoffs.length; i++) {
			
			if(studentGrade >= gradeCutoffs[i]) {
				
				return letGrades[i];
				
			}
			
		}
		
		return letGrades[letGrades.length - 1];
		
	}

	public String getCourseGrades() {
		
		String answer = "";
		
		for(String i : students.keySet()) {
			
			answer += students.get(i).getName() + " " + getCourseNumericGrade(i) + " " + getCourseLetterGrade(i) + "\n";
			
		}
		
		return answer;
		
	}
	
	public double getMaxScore(int examId) {
		
		double highestScore = -1;
		
		for(Student i : students.values()) {
			
			if(i.gradeExam(examId) > highestScore) {
				
				highestScore = i.gradeExam(examId);
				
			}
			
		}
		
		return highestScore;
		
	}
	
	public double getMinScore(int examId) {

		double lowestScore = 101;
		
		for(Student i : students.values()) {
			
			if(i.gradeExam(examId) < lowestScore) {
				
				lowestScore = i.gradeExam(examId);
				
			}
			
		}
		
		return lowestScore;
		
	}
	
	public double getAverageScore(int examId) {
		
		double average = 0;
		
		for(Student i : students.values()) {
			
			average += i.gradeExam(examId);
			
		}
		
		return average / students.keySet().size();
		
	}
	
	public void saveManager(Manager manager, String fileName) {

		File file = new File(fileName);
		ObjectOutputStream output;
		try {
			output = new ObjectOutputStream(new FileOutputStream(file));
			output.writeObject(manager);
			output.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public Manager restoreManager(String fileName) {
		
		File file = new File(fileName);
		
		if(!(file.exists())) {
			
			return new SystemManager();
			
		} else {
			
			ObjectInputStream input;
			SystemManager manager;
			try {
				input = new ObjectInputStream(new FileInputStream(file));
				manager = (SystemManager) input.readObject();
				input.close();
				return manager;
			} catch (FileNotFoundException e) {
			
				e.printStackTrace();
				
			} catch (IOException e) {
			
				e.printStackTrace();
				
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
				
			}
			
			return this;
			
		}
		
	}

}