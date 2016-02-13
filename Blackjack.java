import java.io.*;
import java.util.*;

public class Blackjack {
	public static void main(String[] args) throws IOException { //*******what i'm working on so far: file system + check movie class from 0007 to see how objects and classes work in a real example
		Scanner usersName = new Scanner(System.in); //what is meant by how random generated cards are stored?
		Scanner yn = new Scanner(System.in);
		Scanner betamt = new Scanner(System.in);
		String responsePlay = "";
		String name = "";
		String responseBet = "";
		double responseBetConverted; //Jan 13th: most recent problem is getting it to make a new file if I didnt have a Blerg.txt to begin with etc
		double money = 100.00;
		int handsPlayed = 0;
		int handsWon = 0;
		boolean newP = true;
		boolean r2p = true;
		boolean bet = true;
		boolean goodBet = false;
		Random rnd = new Random();
		
		System.out.println("I N F I N I T E - B L A C K J A C K  ©2016");
		
		while(newP){
			System.out.println("Enter your name to begin!");
			name = usersName.nextLine();
			Player user = new Player(name, money, handsPlayed, handsWon);
			
			if(isAlphabet(name)){
				newP = false; //after this line I probably want another if/try where it checks for an old user
				System.out.println("Thanks!");
				if(!Player.f.exists()){  
					Player.writePlayer(user);
				}
				Player.printPlayer(user);
				while(r2p){
					System.out.println("Ready to play? Enter Y/N");
					responsePlay = yn.nextLine();
					if(responsePlay.equals("Y") || responsePlay.equals("y")){
						r2p = false;
						while(bet){
							System.out.println("How much will you be betting?");
							responseBet = betamt.nextLine();
							if(isNumber(responseBet)){
								bet = false;
								responseBetConverted = Double.parseDouble(responseBet);
								Player.money = (money - responseBetConverted);
								Player.printPlayer(user);
							}
							else{
								System.out.println("That's not an appropriate monetary amount!");
							}
						}
							
					}
					else if(responsePlay.equals("N") || responsePlay.equals("n")){
						System.out.println("See you next time!");
						System.exit(0);
					}
				}
			}
			else{
				System.out.println("Enter an actual name please...");
			}
			
		}
	}
	
	public static boolean isAlphabet(String name) { //make sure a string is only letters
	    char[] chars = name.toCharArray();

	    for (char c : chars) {
	        if(!Character.isLetter(c) && !Character.isWhitespace(c)) { //modified to also allow spaces
	            return false;
	        }
	    }

	    return true;
	}
	
	public static boolean isNumber(String name) { //make sure a string is only numbers
		try{
	    	Double test;
	    	test = Double.parseDouble(name);
	    	if(test > 0){
	    		return true;
	    	}
	    	else{
	    		return false;
	    	}    	
	    }
	    catch (NumberFormatException e){ //try catch will toss any garbage input out and only accept above 0 with or without decimals
	    	return false;
	    }
	}
}
