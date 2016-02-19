import java.io.*;
import java.util.*;

public class Blackjack {
	public static void main(String[] args) throws IOException { 
		Scanner usersName = new Scanner(System.in); //what is meant by how random generated cards are stored?
		Scanner yn = new Scanner(System.in);
		Scanner betamt = new Scanner(System.in);
		String responsePlay = "";
		String responseBet = "";
		double responseBetConverted; 
		boolean newP = true;
		boolean r2p = true;
		boolean bet = true;
		boolean goodBet = false;
		Random rnd = new Random();
		Random card = new Random();
		
		
		System.out.println("I N F I N I T E - B L A C K J A C K  ©2016");
		
		while(newP){
			System.out.println("Enter your name to begin!");
			Player.name = usersName.nextLine();
			Player user = new Player(); //create object
			Player.setName(Player.name);
			Player.setMoney(Player.money);
			Player.setPlayed(Player.handsPlayed);
			Player.setWon(Player.handsWon);
			
			if(isAlphabet(Player.name)){
				newP = false; //after this line I probably want another if/try where it checks for an old user
				System.out.println("Thanks!");
				 
				Player.writePlayer(user); //overwrites old data and creates new player if need be
				Player.printPlayer(user); //prints out player info
				
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
								Player.money = (Player.money - responseBetConverted);
								Player.writePlayer(user);
								Player.printPlayer(user);
								
								System.out.println("YOUR CARDS: ");
								
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
