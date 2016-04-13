import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Assignments.A3.Question;

import java.util.*;

public class Voter {
	
	static final int HEIGHT = 400;
	static final int WIDTH = 700;
	static String inputValue;
	static ArrayList<JButton> buttonsPer = new ArrayList<JButton>(); //buttons per ballot
	static ArrayList<Ballot> ballots = new ArrayList<Ballot>();
	static ArrayList<JLabel> labels = new ArrayList<JLabel>();
	static final String voters = "voters.txt";
	
	public static void writeToBallot(String ballotID, String[] options){ //FIX THIS SO THAT IT DOESNT VOTE FOR ALL OPTIONS,
		FileOutputStream fos;                                            // AND ONLY INCREMENTS THE BUTTONS ACTUALLY SELECTED
		try {
			fos = new FileOutputStream(ballotID, true);
			PrintWriter log = new PrintWriter(fos);
			File bID = new File(ballotID);
			Scanner read = new Scanner(bID);
			String choice;
			
			for(int i = 0; i < options.length; i++){
				choice = read.nextLine();
				String[] parts = choice.split(":");
				log.println(options[i] + ":" + (parts[1] + 1));
			}
			
		} 
		catch (FileNotFoundException fnfe) {
			System.out.println("No such file found.");
		}
        
	}
	
	public static void writeToVoter(String v, String id){
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(v, true);
			PrintWriter log = new PrintWriter(fos);
			
			File f = new File(v);
			Scanner read = new Scanner(f);
			String voted = "voted";
			
			
			while(read.hasNextLine()){
				String test = read.nextLine();
				String[] parts = test.split(":");
				
				if(parts[0].equalsIgnoreCase(id)){
					log.println(parts[0] + ":" + parts[1] + ":" + "true");
				}
			}
			
		} 
		catch (FileNotFoundException fnfe) {
			System.out.println("No such file found.");
		}
        
	}
	
	public static void readFrom(String b) throws FileNotFoundException{
		File f = new File(b);
		Scanner read = new Scanner(f);
		int i = 0;
		String ballit;
		int nBallots;
		String id;
		String bName = "";
		
		nBallots = Integer.parseInt(read.nextLine());
		while(i < nBallots){
            ballit = read.nextLine();
            String[] ballot = ballit.split(":");
            
            id = ballot[0];
            bName = ballot[1];
            String[] opSplit = ballot[2].split(",");

            ballots.add(new Ballot(id, bName, opSplit));
            i++;
		}
		read.close();
		
	}
	
	public static String readVoters(String v, String id) throws FileNotFoundException{
		File f = new File(v);
		Scanner read = new Scanner(f);
		String voted = "voted";
		
		
		while(read.hasNextLine()){
			String test = read.nextLine();
			String[] parts = test.split(":");
			
			if(parts[0].equalsIgnoreCase(id)){
				if(parts[2].equalsIgnoreCase("true")){
					read.close();
					return voted;
				}
				else if(parts[2].equalsIgnoreCase("false")){
					read.close();
					return parts[1];
				}
			}
		}
		read.close();
		return "n0id"; //no id, with 0 because no ones gonna have that as a name
		
	}
	
	public static void makeLabels(JFrame window){ //generates labels for ballot titles
		String ballotName;
		
		for(int i = 0; i < ballots.size(); i++){
    		ballotName = ballots.get(i).returnBName();	
    		labels.add(new JLabel(ballotName));
    	}
		
		for(int k = 0; k < labels.size(); k++){
			labels.get(k).setText(ballots.get(k).bName);
			labels.get(k).setForeground(Color.DARK_GRAY);
			labels.get(k).setHorizontalAlignment(SwingConstants.CENTER);
			labels.get(k).setFont(new Font("Impact", Font.PLAIN, 24));
	    	window.getContentPane().add(labels.get(k));
		}
	}
	
    public static void main(String[] args) throws IOException, FileNotFoundException{ //majority of GUI here
    	String ballot = args[0];
    	String voters = args[1];
        File f = new File(ballot); //not necessary, just have a file method accept String voter and use that as filename
    	
        readFrom(ballot);
    	
        JFrame window = new JFrame("vot.er");
    	window.setLocation(new Point(600, 350));
    	window.setSize(WIDTH, HEIGHT);
    	window.getContentPane().setBackground(Color.PINK);
    	window.setForeground(Color.PINK);
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	window.setVisible(true);
    	
    	FlowLayout flowLayout = new FlowLayout();
    	window.getContentPane().setLayout(flowLayout);
    	
    	makeLabels(window);
    	/*
    	JLabel mostPopular = new JLabel("MOST POPULAR");
    	mostPopular.setForeground(Color.DARK_GRAY);
    	mostPopular.setHorizontalAlignment(SwingConstants.CENTER);
    	mostPopular.setFont(new Font("Impact", Font.PLAIN, 24));
    	window.getContentPane().add(mostPopular);
    	
    	JLabel favorite = new JLabel("FAVORITE");
    	favorite.setForeground(Color.DARK_GRAY);
    	favorite.setHorizontalAlignment(SwingConstants.CENTER);
    	favorite.setFont(new Font("Impact", Font.PLAIN, 24));
    	window.getContentPane().add(favorite);
    	*/
    	Ballot.gui(ballots, buttonsPer); //button related GUI
    	
		for(int j = 0; j < Ballot.totalButtons.size(); j++){
			for(int i = 0; i < Ballot.totalButtons.get(j).size(); i++){
				window.getContentPane().add(buttonsPer.get(i));
			}
		}
    	
    	
    	JButton btnLogin = new JButton("LOGIN HERE");
    	btnLogin.setFont(new Font("Arial", Font.PLAIN, 16));
    	btnLogin.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.out.println("login button pressed");
    			
    			JOptionPane welcome = new JOptionPane();
    			JDialog dialog = welcome.createDialog("vot.er");
    			inputValue = JOptionPane.showInputDialog("Please enter your voter ID");
    			try {
    				String name = readVoters(voters, inputValue);
					if(!name.equals("n0id") && !name.equalsIgnoreCase("voted")){
						welcome.setMessage("Welcome, " + name);
						dialog.setSize(300, 150);
						dialog.show();
						btnLogin.setEnabled(false);
						
						for(int i = 0; i < buttonsPer.size(); i++){
							buttonsPer.get(i).setEnabled(true);
						}
					}
					else if(name.equalsIgnoreCase("voted")){
						welcome.setMessage("Our records show that this ID has already participated in voting. No further voting is allowed.");
						dialog.setSize(550, 150);
						dialog.show();
					}
					else if(name != null && name.equalsIgnoreCase("n0id")){
						welcome.setMessage("No such voter ID found.");
						dialog.setSize(300, 150);
						dialog.show();
					}
					else{
						
					}
				} 
    			catch (FileNotFoundException fnfe) {
					System.out.println("file related error");
					fnfe.printStackTrace();
				}
    			
    		}
    	});
    	window.getContentPane().add(btnLogin);
    	
    	JButton castVote = new JButton("CAST VOTE");
    	castVote.setEnabled(false);
    	castVote.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.out.println("votes CAST! button pressed");
    			int voteit = JOptionPane.showConfirmDialog(castVote, "Are you sure you want to vote? There is no going back.");
    			
    			if(voteit == 0){
    				writeToVoter(voters, inputValue);
    				for(int i = 0; i < ballots.size(); i++){
    					writeToBallot(ballots.get(i).returnID(), ballots.get(i).returnOptions());
    				}
    			}
    		}
    	});
    	window.getContentPane().add(castVote);
    	
    	window.setVisible(true);
    	
    }
}
