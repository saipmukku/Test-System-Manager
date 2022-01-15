package cmdLineInterpreter;

import java.util.*;

import onlineTest.SystemManager;

/**
 * 
 * By running the main method of this class we will be able to
 * execute commands associated with the SystemManager.  This command
 * interpreter is text based. 
 *
 */
public class Interpreter {
	
	private ArrayList<Integer> numOfQuestionsPerExam;
	private Scanner input;

	public static void main(String[] args) {
		
		SystemManager manager = new SystemManager();
		Interpreter newInter = new Interpreter();
		newInter.runInterpreter(manager);

	}
	
	public Interpreter() {
		
		numOfQuestionsPerExam = new ArrayList<Integer>();
		input = new Scanner(System.in);
		
	}
	
	public void runInterpreter(SystemManager manager) {
		
		String menu = "Press the respective number to interact with the SystemManager." + 
		" Enter '6' to quit.";
		menu += "\n1 - Add a student\n2 - Add an exam\n3 - Add a true/false question\n"
				+ "4 - Answer a true/false question\n5 - Get the exam score for a student";
		int numOfExams = 1;
		
		System.out.println(menu);
		
		ask(manager, numOfExams);

		while(true) {
			
			System.out.println(menu);
			
			if(input.nextInt() == 1) {
				
				System.out.println("Enter student's name:");
				String student = input.next();
				manager.addStudent(student);
				
				System.out.println("Student added.");
				
			} else if(input.nextInt() == 2) {
				
				System.out.println("Enter title for the exam:");
				manager.addExam(numOfExams, input.next());
				numOfExams++;
				numOfQuestionsPerExam.add(0);
				System.out.println("Exam added.");
				
			} else if(input.nextInt() == 3) {
				
				System.out.println("Enter exam number that you want to add the question to:");
				int examNum = input.nextInt();
				System.out.println("Enter text for the question:");
				String text = input.next();
				System.out.println("Enter the answer for the question:");
				boolean answer = input.nextBoolean();
				System.out.println("How many points is the question worth?");
				int points = input.nextInt();
				
				manager.addTrueFalseQuestion(examNum, numOfQuestionsPerExam.get(examNum - 1) + 1, text, points, answer);
				
				System.out.println("Question added.");
				
			} else if(input.nextInt() == 4) {
				
				System.out.println("Enter name of the student that's answering the question:");
				String student = input.next();
				System.out.println("Enter which exam the question is on:");
				int exam = input.nextInt();
				System.out.println("Enter which question they are answering (which number):");
				int ques = input.nextInt();
				System.out.println("Enter the student's answer:");
				boolean answer = input.nextBoolean();
				
				manager.answerTrueFalseQuestion(student, exam, ques, answer);
				
				System.out.println("Question answered.");
				
			} else if(input.nextInt() == 5){
				
				System.out.println("Which student do you want to check the exam score for?");
				String student = input.next();
				System.out.println("What exam do you want to check?:");
				int exam = input.nextInt();
				
				System.out.println("Score for exam #" + exam + ": " + manager.getExamScore(student, exam));
				
			} else if(input.nextInt() == 6){
				
				System.out.println("Thank you, closing program...");
				break;
				
			} else {
				
				System.out.println("That was an invalid response.");
				
			}
			
		}
		
	}
	
	private void ask(SystemManager manager, int numOfExams) {
		
		if(input.nextInt() == 1) {
			
			try {
			
				System.out.println("Enter student's name:");
				String student = input.next();
				manager.addStudent(student);
				System.out.println("Student added.");
				
			} catch(NullPointerException e) {
				
				e.getMessage();
				System.out.println("Not possible");
				
			}
			
		} else if(input.nextInt() == 2) {

			try {
				
				System.out.println("Enter title for the exam:");
				manager.addExam(numOfExams, input.next());
				numOfExams++;
				numOfQuestionsPerExam.add(0);
				System.out.println("Exam added.");
				
			} catch(NullPointerException e) {
				
				e.getMessage();
				System.out.println("Not possible");
				
			}
			
		} else if(input.nextInt() == 3) {;
			
			try {
				
				System.out.println("Enter exam number that you want to add the question to:");
				int examNum = input.nextInt();
				System.out.println("Enter text for the question:");
				String text = input.next();
				System.out.println("Enter the answer for the question:");
				boolean answer = input.nextBoolean();
				System.out.println("How many points is the question worth?");
				int points = input.nextInt();
				
				manager.addTrueFalseQuestion(examNum, numOfQuestionsPerExam.get(examNum - 1) + 1, text, points, answer);
				
				System.out.println("Question added.");
				
			} catch(NullPointerException e) {
				
				e.getMessage();
				System.out.println("Not possible");
				
			}
			
		} else if(input.nextInt() == 4) {
			
			try {
				
				System.out.println("Enter name of the student that's answering the question:");
				String student = input.next();
				System.out.println("Enter which exam the question is on:");
				int exam = input.nextInt();
				System.out.println("Enter which question they are answering (which number):");
				int ques = input.nextInt();
				System.out.println("Enter the student's answer:");
				boolean answer = input.nextBoolean();
				
				manager.answerTrueFalseQuestion(student, exam, ques, answer);
				
				System.out.println("Question answered.");
				
			} catch(Exception e) {
				
				e.getMessage();
				System.out.println("Not possible");
				
			}
			
		} else if(input.nextInt() == 5){
			
			try {
				
				System.out.println("Which student do you want to check the exam score for?");
				String student = input.next();
				System.out.println("What exam do you want to check? (Enter number):");
				int exam = input.nextInt();
				
				System.out.println("Score for exam #" + exam + ": " + manager.getExamScore(student, exam));
				
			} catch(Exception e) {
				
				e.getMessage();
				System.out.println("Not possible");
				
			}
			
		} else if(input.nextInt() == 6){
			
			System.out.println("Thank you, closing program...");
			
		} else {
			
			System.out.println("That was an invalid response.");
			
		}
		
	}
	
}