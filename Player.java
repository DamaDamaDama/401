import java.io.*;
import java.util.*;

public class Player {
	
	static String name = "";
	static double money = 100.00;
	static int handsPlayed = 0;
	static int handsWon = 0;
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
	
	public static void printPlayer(Player user) throws FileNotFoundException{ //print player info to them
		f = new File("C:\\Users\\Alan\\GitHub\\401\\" + name + ".txt");
		Scanner read = new Scanner(f);
		
		
		System.out.println("Your player information, " + name + ":");
		
		
			name = read.nextLine();
			System.out.println("Name: " + name);
			money = Double.parseDouble(read.nextLine());
			System.out.println("Total Funds: $" + money);
			handsPlayed = Integer.parseInt(read.nextLine());
			System.out.println("Total Hands Played: " + handsPlayed);
			handsWon = Integer.parseInt(read.nextLine());
			System.out.println("Total Hands Won: " + handsWon);
		
		read.close();
		
	} 
	public static void writePlayer (Player user) throws IOException{ //write a new player in, only for making a new profile
		f.createNewFile();
	
		PrintWriter log = new PrintWriter("C:\\Users\\Alan\\GitHub\\401\\" + name + ".txt");
		log.println(name);
		log.println(money);
		log.println(handsPlayed);
		log.println(handsWon);
		log.close();
			
	}
}
