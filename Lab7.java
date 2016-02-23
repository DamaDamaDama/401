import java.util.*;
import java.io.*;

public class Lab7 {
	public static void main(String[] args) throws FileNotFoundException, IOException{
		int size = 0;
		char bNum = '0';
		int bNumCount = 0;
		char cNum = '1';
		int cNumCount = 0;
		char aNum = '2';
		int aNumCount = 0;
		char nuthin = '.';
		char carrot = '^';
		Scanner input = new Scanner(System.in);
		String response = "";
		boolean initQ = true;
		File f = new File("universe.txt");
		
		System.out.println("What size world do you want to invoke?");
		char[] universe = new char[generateUniverse(size)];
		for(int r = 0; r < universe.length; r++){
			universe[r] = nuthin;
		}
		for(int t = 5; t < universe.length - 1; t += 5){
			universe[t] = carrot;
		}
		for(int y = 0; y < universe.length - 1; y += 7){
			universe[y] = bNum;
		}
		
		
		while(initQ){
			System.out.println("[Q]uit, [A]dvance world, [S]ave world?");
			response = input.nextLine();
			if(response.equalsIgnoreCase("Q") || response.equalsIgnoreCase("QUIT")){
				System.out.println("See you next time.");
				System.exit(0);
			}
			else if(response.equalsIgnoreCase("A") || response.equalsIgnoreCase("ADVANCE")){
				for(int i : universe){
					if(universe[i] == bNum){
						universe[i] = cNum;
					}
					else if(universe[i] == cNum){
						universe[i] = aNum;
					}
					else if(universe[i] == aNum && universe[i+1] != aNum && universe[i+1] != carrot){
						universe[i+1] = universe[i];
						universe[i] = nuthin;
					}
					else if(universe[i] == aNum && universe[i+1] != aNum && universe[i+1] == carrot){
						universe[i+1] = bNum;
					}
				}
				System.out.println(universe);
			}
			else if(response.equalsIgnoreCase("S") || response.equalsIgnoreCase("SAVE")){
				f.createNewFile();
				
				PrintWriter log = new PrintWriter("universe.txt");
				for(int o = 0; o < universe.length - 1; o++){
					if(universe[o] == bNum){
						bNumCount++;
					}
					else if (universe[o] == cNum){
						cNumCount++;
					}
					else if(universe[o] == aNum){
						aNumCount++;
					}
				}
					
				log.println(universe);
				log.println("Babies: " + bNumCount);
				log.println("Children: " + cNumCount);
				log.println("Adults: " + aNumCount);
				log.close();
				
			}
			else{
				System.out.println("Enter a valid input please.");
			}
		}
	}
	public static int generateUniverse(int s){
		Scanner unisize = new Scanner(System.in);
		s = unisize.nextInt();
		return s;
	}
}
