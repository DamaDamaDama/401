public class Question {
    //"store users answers in a question object"
    private String questions; //removed static
    private String[] answers; //removed static
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
 
    public String printQuestion(){ //Removed the Question q parameter
        return questions;
        //return questions;
    }
 
    public void printAnswers(){ //Removed the Question q parameter
 
        for(int i = 0; i < answers.length; i++){
            System.out.println(i + ": " + answers[i]);
        }
    }
 
    public int nAnswers(){ //Removed the Question q parameter
        int increment = 0;
        for(int i = 0; i < answers.length - 1; i++){
            increment++;
        }
        return increment;
    }
}
