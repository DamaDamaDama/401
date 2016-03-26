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
		String[] qAns = new String[3];
		int qCounter = 0;
		
		System.out.println();
		System.out.println("Welcome to Quiz by Alan Munirji");
		System.out.println();
		
		readQuiz();
		printQuiz();
		
		System.out.println("Question #" + qCounter);
		System.out.println(Question.printQuestion(questions.get(0)));
		System.out.println("Answers: ");
		Question.printAnswers(questions.get(0));
		
		
		//Question q1 = new Question("qwe", qAns, 2, 2, 3); example of question object I would use for one answered question.
		//question, the answer, the correct one, # tries, # correct
		
		//questions.add(q1); example
	}
	
	public static void updateQuiz() throws IOException{ //enter stuff into a quiz file
		f.createNewFile();
	
		PrintWriter log = new PrintWriter("quiz.txt");
		log.println("test");
		log.println();
		log.println();
		log.println("test with two newlines before");
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
		
		//while(read.hasNextLine()){
			//if(read.nextLine().contains("?")){
				question = read.nextLine();
				nAns = Integer.parseInt(read.nextLine());
				String[] ans = new String[nAns];
				for(int i = 0; i < nAns; i++){
					ans[i] = read.nextLine();
				}
				cAns = read.nextInt();
				attempts = read.nextInt();
				nCorrect = read.nextInt();
				
				questions.add(new Question(question, ans, cAns, attempts, nCorrect));
			//}
		//}
		read.close();
		
	} 
}
