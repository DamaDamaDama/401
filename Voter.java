import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

public class Voter {
	
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	 
    public static void main(String[] args) throws IOException{
    	//String voter = args[0]; //accept the text file that has voter information
        //File f = new File(voter);
        
        JFrame window = new JFrame("TEST JFrame");
    	window.setSize(WIDTH, HEIGHT);
    	
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //make sure program shuts down when its closed out
    	
    	window.setVisible(true); //default is false
    	
    	JLabel msg = new JLabel("TEST");
    	msg.setFont(new Font("TimesRoman", Font.BOLD, 36));
    	msg.setForeground(Color.BLACK);
    	window.add(msg);
    	
    	window.setVisible(true);
    }
}
