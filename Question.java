
public class Question {
	//"store users answers in a question object"
	private static String questions;
	private static String[] answers;
	private int correctAnswers;
	private int nAttempts;
	private int nCorrect;
	
	public Question(String q, String[] ans, int cAns, int attempts, int correct){
		questions = q;
		answers = ans;
		correctAnswers = cAns;
		nAttempts = attempts;
		nCorrect = correct;
		
	}
	
	public Question(){
		
	}
	
	public static String printQuestion(Question q){
		
		return questions;
	}
	
	public static void printAnswers(Question q){
		
		for(int i = 0; i < answers.length; i++){
			System.out.println(i + ": " + answers[i]);
		}
	}
	
	public static int nAnswers(Question q){
		int increment = 0;
		for(int i = 0; i < answers.length - 1; i++){
			increment++;
		}
		return increment;
	}
}
