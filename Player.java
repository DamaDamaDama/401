import java.io.*;
import java.util.*;

public class Player {
	
	static String name;
	static double money;
	static int handsPlayed;
	static int handsWon;
	static File f = new File("C:\\Users\\Alan\\GitHub\\401\\" + name + ".txt");
	
	public Player(String playerName, double playerMoney, int playerHandsPlayed, int playerHandsWon){
		name = playerName;
		money = playerMoney;
		handsPlayed = playerHandsPlayed;
		handsWon = playerHandsWon;
	}
	
	public Player(){
		
	}
	
	public static void setName(String n){
		name = n;
	}
	
	public static void setMoney(double m){
		money = m;
	}
	
	public static void setPlayed(int p){
		handsPlayed = p;
	}
	
	public static void setWon(int w){
		handsWon = w;
	}
	
	public static void printPlayer(Player user) throws FileNotFoundException{
		f = new File("C:\\Users\\Alan\\GitHub\\401\\" + name + ".txt");
		Scanner read = new Scanner(f);
		String profile = "";
		
		System.out.println("Your player information, " + name + ":");
		
		for (int i = 0; i < 4; i++){
			profile = read.nextLine();
			System.out.println(profile);
		}
		read.close();
		
	}
	public static void writePlayer (Player user) throws IOException{
		f = new File("C:\\Users\\Alan\\GitHub\\401\\" + name + ".txt"); //why is this not creating the file at that location?
																		//i have to manually make a Blerg.txt etc so far
		PrintWriter log = new PrintWriter("C:\\Users\\Alan\\GitHub\\401\\" + name + ".txt");
		log.println("Name: " + name);
		log.println("Total Funds: $" + money);
		log.println("Hands Played: " + handsPlayed);
		log.println("Hands Won: " + handsWon);
		log.close();
			
	}
}
