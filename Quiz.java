import java.io.*;
import java.util.*;
import Assignments.Player;

public class Quiz {
	static String quiz = "quiz.txt";
	static File f = new File(quiz);
	static ArrayList<Question> questions = new ArrayList<Question>(4);
	
	public static void main(String[] args) throws IOException{
		args[0] = "quiz.txt";
		Scanner answer = new Scanner(System.in);
		String userAnswer = "";
		int userAnswerConv = 0;
		String[] qAns = new String[3];
		int qCounter = 0;
		boolean testing = true;
		boolean sameProb = true;
		boolean results = true;
		
		////////////////////////////////////////////////////////////////
		
		System.out.println();
		System.out.println("Welcome to Quiz by Alan Munirji");
		System.out.println();
		
		readQuiz();
		printQuiz();
		
		while(testing){ //this section of code is for taking the quiz
			sameProb = true;
			if(qCounter < questions.size()){ 
				System.out.println("Question #" + qCounter);
				System.out.println(Question.printQuestion(questions.get(qCounter)));
			}
			else{
				testing = false;
				System.out.println("That concludes all of the questions.");
				break;
			}
			System.out.println("Answers: ");
			Question.printAnswers(questions.get(qCounter));
			while(sameProb){
				userAnswer = answer.nextLine();
				try{
					userAnswerConv = Integer.parseInt(userAnswer);
					if(userAnswerConv < 0 || userAnswerConv > Question.nAnswers(questions.get(qCounter))){
						System.out.println("Inappropriate response. Please enter an appropriate number");
					}
					else{
						System.out.println("Your response has been recorded.");
						qCounter++;
						sameProb = false;
					}
				}
				catch(NumberFormatException NFE){
					System.out.println("Inappropriate response. Please enter an appropriate number");
				}
			}
		}
		while(results){ //this section of code is for displaying the results back
			break;
		}
		
	}
	
	public static void updateQuiz() throws IOException{ //enter stuff into a quiz file
		f.createNewFile();
	
		PrintWriter log = new PrintWriter("quiz.txt");
		log.println("test");
		log.println();
		log.close();
			
	}
	
	public static void printQuiz() throws FileNotFoundException{ //print quiz file out
		f = new File("quiz.txt");
		Scanner read = new Scanner(f);
		boolean more = true;
		
		System.out.println("Quiz File Contents:");
			
		while(more){
			try{
				System.out.println(read.nextLine());
			}
			catch(Exception e){
				more = false;
			}
		
		}
		
		read.close();
		
	} 
	
	public static void readQuiz() throws FileNotFoundException{ //read whats on the file, we want to insert it into question objects
		f = new File("quiz.txt");
		Scanner read = new Scanner(f);
		String line;
		String question;
		int nAns = 0;
		int cAns = 0;
		int attempts = 0;
		int nCorrect = 0;
		
		while(read.hasNextLine()){
			//if(read.nextLine().contains("?")){
				question = read.nextLine();
				System.out.println("DEBUG ONLY Question being added: " + question);
				nAns = Integer.parseInt(read.nextLine());
				String[] ans = new String[nAns];
				for(int i = 0; i < nAns; i++){
					ans[i] = read.nextLine();
				}
				cAns = Integer.parseInt(read.nextLine());
				attempts = Integer.parseInt(read.nextLine());
				nCorrect = Integer.parseInt(read.nextLine());
				
				questions.add(new Question(question, ans, cAns, attempts, nCorrect));
				System.out.println(Question.printQuestion(questions.get(0)));  //wtf??? its ONLY index 0

			//}
		}
		read.close();
		
	} 
}
