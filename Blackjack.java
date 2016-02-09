import java.io.*;
import java.util.*;

public class Blackjack {
	public static void main(String[] args){ //*******what i'm working on so far: file system + check movie class from 0007 to see how objects and classes work in a real example
		Scanner name = new Scanner(System.in);
		String usersName = "";
		double usersMoney = 0;
		int handsPlayed = 0;
		int handsWon = 0;
		
		Player user = new Player(usersName, usersMoney, handsPlayed, handsWon);
		
		System.out.println("Enter your name to begin!");
		usersName = name.nextLine();
		
		
	}
}
