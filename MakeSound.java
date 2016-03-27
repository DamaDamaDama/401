import java.io.*;
import sun.audio.*;
import java.util.*;

/**
 * A simple music player
 * 
 * @author github.com/DamaDamaDama
 */

//FileUtils.copyURLToFile(URL, File)

public class MakeSound{
  public static void main(String[] args) throws IOException{
	  String songName = "";
	  Scanner songChoice = new Scanner(System.in);
	  String sEnd = "";
	  Scanner songEnd = new Scanner(System.in);
	  boolean invalid = true;
	  boolean songContinue = true;
	  
	  System.out.println("Welcome to MakeSound");
	  while(invalid){
		  songContinue = true;
		  System.out.println("What do you want to listen to? Type (Q)uit at any time to close MakeSound");
		  songName = songChoice.nextLine();
		  if(songName.equalsIgnoreCase("Q") || songName.equalsIgnoreCase("QUIT")){
			  System.out.println("Closing...");
			  System.exit(0);
		  }
		  try{
			  invalid = false;
			  // open the sound file as a Java input stream
			  String audioFile = songName + ".au";
			  InputStream in = new FileInputStream(audioFile);
		 
			  // create an audio stream from the input stream
			  AudioStream audioStream = new AudioStream(in);
		 
			  // play the audio clip with the audio player class
			  AudioPlayer.player.start(audioStream);
			  while(songContinue){
				  System.out.println("Type S or STOP to end the audio.");
				  sEnd = songEnd.nextLine();
				  if(sEnd.equalsIgnoreCase("S") || sEnd.equalsIgnoreCase("STOP")){
					  AudioPlayer.player.stop(audioStream);
					  songContinue = false;
					  invalid = true;
				  }
				  else if(sEnd.equalsIgnoreCase("Q") || sEnd.equalsIgnoreCase("QUIT")){
					  System.out.println("Closing...");
					  System.exit(0);
				  }
				  else{
					  System.out.println("Don't know what that means.");
				  }
			  }
			  
		  }
		  catch(Exception e){
			  invalid = true;
			  System.out.println("Audio file probably doesn't exist or is a wrong format.");
		  }
	  }
	  
  }
}