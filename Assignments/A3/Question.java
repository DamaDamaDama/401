public class Question {
    //"store users answers in a question object"
    private String questions; 
    private String[] answers; 
    private double correctAnswer;
    private double nAttempts;
    private double nCorrect;
 
    public Question(String q, String[] ans, double cAns, double attempts, double correct){
        questions = q;
        answers = ans;
        correctAnswer = cAns;
        nAttempts = attempts;
        nCorrect = correct;
 
    }
 
    public Question(){
 
    }
 
    public String printQuestion(){ //Removed the Question q parameter
        return questions;
    }
    
    public double printCorrect(){ //yields the correct answer
    	return correctAnswer;
    }
 
    public void printAnswers(){  //prints actual answers
 
        for(int i = 0; i < answers.length; i++){
            System.out.println(i + ": " + answers[i]);
        }
    }
    
	public String[] returnAnswers(){  //returns actual answers
        return answers;
    }
 
    public double nCorrect(){ //yields number of correct answers from before
    	return nCorrect;
    }
    
    public double nAttempts(){ //yields number of attempted answers from before
    	return nAttempts;
    }
    
    public int nAnswers(){ //tell how many total answers
        int increment = 0;
        for(int i = 0; i < answers.length - 1; i++){
            increment++;
        }
        return increment;
    }
}
