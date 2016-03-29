import java.io.*;
import java.util.*;
//import Assignments.Player; Eclipse import, not necessary 
 
public class Quiz {
    static String quiz = "quiz.txt";
    static File f = new File(quiz);
    static ArrayList<Question> questions = new ArrayList<Question>();
 
    public static void main(String[] args) throws IOException{
        args[0] = "quiz.txt";
        Scanner answer = new Scanner(System.in);
        String userAnswer = "";
        int userAnswerConv = 0;
        int[] qAns = new int[5];
        int[] qAnsTries = new int[5];
        int[] qAnsCorrect = new int[5];
        int qCounter = 0;
        int pCorrectTag = 0; //mark what question was easiest
        int hCorrectTag = 0; //mark what question was hardest
        double attempts = 0;
        double nCorrect = 0;
        double timesRight = 0;
        double timesWrong = 0;
        double pCorrect = 0; //used for easiest question
        double hCorrect = 100; //used for hardest question
        boolean testing = true;
        boolean sameProb = true;
        boolean results = true;
 
        ////////////////////////////////////////////////////////////////
 
        System.out.println();
        System.out.println("Welcome to Quiz by Alan Munirji");
        System.out.println();
        System.out.println("Answer using one of the numbers provided next to each choice.");

        readQuiz();
        //printQuiz(); debugging
 
        while(testing){ //this section of code is for taking the quiz
            sameProb = true;
            if(qCounter < questions.size()){
            	System.out.println("");
                System.out.println("Question #" + qCounter);
                System.out.println(questions.get(qCounter).printQuestion());
            }
            else{
                testing = false;
                System.out.println("");
                System.out.println("That concludes all of the questions.");
                break;
            }
            System.out.println("Answers: ");
            questions.get(qCounter).printAnswers(); //modified from Question.printAnswers(questions.get(qCounter))
            while(sameProb){
                userAnswer = answer.nextLine();
                try{
                    userAnswerConv = Integer.parseInt(userAnswer);
                    if(userAnswerConv < 0 || userAnswerConv > questions.get(qCounter).nAnswers()){ //changed Question.nAnswers(...) to questions.get(qCounter).nAnswers()
                        System.out.println("Inappropriate response. Please enter an appropriate number");
                    }
                    else{
                        System.out.println("Your response has been recorded.");
                        qAns[qCounter] = userAnswerConv;
                        qCounter++;
                        sameProb = false;
                    }
                }
                catch(NumberFormatException NFE){
                    System.out.println("Inappropriate response. Please enter an appropriate number");
                }
            }
        }
        qCounter = 0;
        System.out.println("Displaying answers: ");
        
        while(results){ //this section of code is for displaying the results back
            
            System.out.println("Question #" + qCounter + ":");
            System.out.printf("Correct answer was: %.0f%n", questions.get(qCounter).printCorrect());
            
            System.out.println("You answered: ");
            System.out.println(qAns[qCounter]);
            
            double cAnswer = questions.get(qCounter).printCorrect();
            if(cAnswer == (qAns[qCounter])){
            	System.out.println("Congratulations you got it right!");
            	System.out.println("");
            	qAnsTries[qCounter]++;
            	qAnsCorrect[qCounter]++;
            	qCounter++;
            	timesRight++;
 
            	if(qCounter == 5){
            		results = false;
            		break;
            	}
            }
            else{
            	System.out.println("You were wrong!");
            	System.out.println("");
            	qAnsTries[qCounter]++;
            	qCounter++;
            	timesWrong++;
            	if(qCounter == 5){
            		results = false;
            		break;
            	}
            }
        }
        System.out.println("");
        System.out.println("Overall Performance: ");
        System.out.printf("Number of times correct: %.0f%n", timesRight);
        System.out.printf("Number of times incorrect: %.0f%n", timesWrong);
        System.out.printf("Percentage: %.0f%n", ((timesRight / (timesRight + timesWrong)) * 100));
        System.out.println("");
        System.out.println("Cumulative Statistics: ");
        qCounter = 0;
        
        wipeQuiz(); //wipe the old quiz and replace it with itself, but updated cumulative results
        
        while(qCounter < 5){
        	System.out.println("");
        	System.out.println("Question: " + questions.get(qCounter).printQuestion());
        	attempts = qAnsTries[qCounter] + questions.get(qCounter).nAttempts();
        	nCorrect = qAnsCorrect[qCounter] + questions.get(qCounter).nCorrect();
        	updateQuiz(attempts, nCorrect, questions, qCounter);
        	System.out.printf("Times tried: %.0f%n", attempts);
            System.out.printf("Times correct: %.0f%n", nCorrect);
        	if(pCorrect < ((nCorrect / attempts) * 100)){
        		pCorrect = ((nCorrect / attempts) * 100);
        		pCorrectTag = qCounter;
        	}
        	if(hCorrect > ((nCorrect / attempts) * 100)){
        		hCorrect = ((nCorrect / attempts) * 100);
        		hCorrectTag = qCounter;
        	}
        	System.out.printf("Percent correct: %.0f%n", ((nCorrect / attempts) * 100));
        	qCounter++;
        }
        
        System.out.println("");
        System.out.println("Easiest Question: ");
        System.out.println("Question: " + questions.get(pCorrectTag).printQuestion());
        attempts = qAnsTries[pCorrectTag] + questions.get(pCorrectTag).nAttempts();
        nCorrect = qAnsCorrect[pCorrectTag] + questions.get(pCorrectTag).nCorrect();
        System.out.printf("Times tried: %.0f%n", attempts);
        System.out.printf("Times correct: %.0f%n", nCorrect);
        System.out.printf("Percent correct: %.0f%n", pCorrect);
    	
    	System.out.println("");
    	System.out.println("Hardest Question: ");
        System.out.println("Question: " + questions.get(hCorrectTag).printQuestion());
        attempts = qAnsTries[hCorrectTag] + questions.get(hCorrectTag).nAttempts();
        nCorrect = qAnsCorrect[hCorrectTag] + questions.get(hCorrectTag).nCorrect();
        System.out.printf("Times tried: %.0f%n", attempts);
        System.out.printf("Times correct: %.0f%n", nCorrect);
        System.out.printf("Percent correct: %.0f%n", (nCorrect / attempts) * 100);
    }
    
    public static void wipeQuiz() throws IOException {
    	f = new File("quiz.txt");
    	if(f.delete()){
    	    f.createNewFile();
    	}
    	else{
    	    System.out.println("um");
    	}
    }
 
    public static void updateQuiz(double attempts, double nCorrect, ArrayList<Question> questions, int qCounter) throws IOException{ //enter stuff into a quiz file
        
        FileOutputStream fos = new FileOutputStream("quiz.txt", true);
        PrintWriter      log  = new PrintWriter(fos);
        
        log.println(questions.get(qCounter).printQuestion());
        log.println(questions.get(qCounter).nAnswers() + 1);
        for(int i = 0; i < questions.get(qCounter).nAnswers() + 1; i++){
            log.println(questions.get(qCounter).returnAnswers()[i]);
        }
        log.println(questions.get(qCounter).printCorrect());
        log.println(attempts);
        log.println(nCorrect);
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
        String question;
        double nAns = 0;
        double cAns = 0;
        double attempts = 0;
        double nCorrect = 0;
 
        while(read.hasNextLine()){
                question = read.nextLine();
                nAns = Double.parseDouble(read.nextLine());
                String[] ans = new String[(int) nAns];
                for(int i = 0; i < nAns; i++){
                    ans[i] = read.nextLine();
                }
                cAns = Double.parseDouble(read.nextLine());
                attempts = Double.parseDouble(read.nextLine());
                nCorrect = Double.parseDouble(read.nextLine());
 
                questions.add(new Question(question, ans, cAns, attempts, nCorrect));
                
 
        }
        read.close();
 
    }
}
