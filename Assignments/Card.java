import java.io.*;
import java.util.*;

public class Card {
	//each card will correspond with a number between 1-52, and for example As or 6d will be 13 and 24 respectively, etc
	//each card will have a name like As and a value like 11/1 in that case, or 6d and a value of 6.
	//for aces, always assume that its value is 1 UNLESS you check using an if statement or something, and find that 11 will not
	//screw the player over. if they hit, and they now bust if its 11, then go back to it being its default 1 value. it will only
	//be 11 if it doesn't ruin it for the player.
	
	static int dealtCards = 0; //type of card they got
	static int value = 0;		//value of the current card at play
	static int acesCounter = 0; //I want to track how many aces are in play
	static String card = ""; //cards actual printed name
	static int cardTotal; //aggregate value of cards
	
	public Card(String receivedcard, int valuecard, int x, int aces){
		card = receivedcard;
		value = valuecard;
		dealtCards = x;
		acesCounter = aces;
	}
	
	public Card(){
		
	}
	
	public static void printCards(Card c){
		System.out.println("Card dealt: " + card);
		cardTotal += value;
	}
	
	public static void setCard(String c){
		card = c;
	}
	
	public static void setValue(int v){
		value = v;
	}
	
	public static boolean checkBust(){ //check if they busted by hitting
		if(cardTotal > 21){
			System.out.println("Bust! Lost the round.");
			return true;
		}
		else{
			return false;
		}
	}
	
	public static boolean checkBustHouse(){ //modified the above method to be more fitting for dealer
		if(cardTotal > 21){
			System.out.println("The house busts!");
			return true;
		}
		else{
			return false;
		}
	}
	
	public static void assignRNG(Card newCard){
		switch (dealtCards){
			case 1: card = "Ad"; value = 1; acesCounter++; break;
								
			case 2: card = "As"; value = 1; acesCounter++; break;
				
			case 3: card = "Ah"; value = 1; acesCounter++; break;
			
			case 4: card = "Ac"; value = 1; acesCounter++; break;
			
			case 5: card = "2d"; value = 2; break;
			
			case 6: card = "2s"; value = 2; break;
		
			case 7: card = "2h"; value = 2; break;
				
			case 8: card = "2c"; value = 2; break;
			
			case 9: card = "3d"; value = 3; break;
			
			case 10: card = "3s"; value = 3; break;
			
			case 11: card = "3h"; value = 3; break;
			
			case 12: card = "3c"; value = 3; break;
			
			case 13: card = "4d"; value = 4; break;
			
			case 14: card = "4s"; value = 4; break;
			
			case 15: card = "4h"; value = 4; break;
			
			case 16: card = "4c"; value = 4; break;
			
			case 17: card = "5d"; value = 5; break;
				
			case 18: card = "5s"; value = 5; break;
			
			case 19: card = "5h"; value = 5; break;
			
			case 20: card = "5c"; value = 5; break;
			
			case 21: card = "6d"; value = 6; break;
				
			case 22: card = "6s"; value = 6; break;
				
			case 23: card = "6h"; value = 6; break;
			
			case 24: card = "6c"; value = 6; break;
			
			case 25: card = "7d"; value = 7; break;
			
			case 26: card = "7s"; value = 7; break;
			
			case 27: card = "7h"; value = 7; break;
			
			case 28: card = "7c"; value = 7; break;
			
			case 29: card = "8d"; value = 8; break;
			
			case 30: card = "8s"; value = 8; break;
			
			case 31: card = "8h"; value = 8; break;
			
			case 32: card = "8c"; value = 8; break;
			
			case 33: card = "9d"; value = 9; break;
			
			case 34: card = "9s"; value = 9; break;
			
			case 35: card = "9h"; value = 9; break;
			
			case 36: card = "9c"; value = 9; break;
			
			case 37: card = "Td"; value = 10; break;
			
			case 38: card = "Ts"; value = 10; break;
			
			case 39: card = "Th"; value = 10; break;
			
			case 40: card = "Tc"; value = 10; break;
			
			case 41: card = "Jd"; value = 10; break;
			
			case 42: card = "Js"; value = 10; break;
			
			case 43: card = "Jh"; value = 10; break;
			
			case 44: card = "Jc"; value = 10; break;
			
			case 45: card = "Qd"; value = 10; break;
			
			case 46: card = "Qs"; value = 10; break;
			
			case 47: card = "Qh"; value = 10; break;
			
			case 48: card = "Qc"; value = 10; break;
			
			case 49: card = "Kd"; value = 10; break;
			
			case 50: card = "Ks"; value = 10; break;
			
			case 51: card = "Kh"; value = 10; break;
			
			case 52: card = "Kc"; value = 10; break;
		}
	}
}
