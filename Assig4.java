import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;

public class Assig4 {
	
	static final int HEIGHT = 400;
	static final int WIDTH = 700;
	static File f;
	static String inputValue;
	static ArrayList<Ballot> ballots = new ArrayList<Ballot>();
	static ArrayList<JLabel> labels = new ArrayList<JLabel>();
	static ArrayList<JPanel> panels = new ArrayList<JPanel>();
	static final String voters = "voters.txt";
	
	public static void tempFile(){
		
	}
	
	public static void writeToBallot(String ballotID, String[] options, String votedFor) throws IOException{ //FIX THIS SO THAT IT DOESNT VOTE FOR ALL OPTIONS,
			
			try{
				String individualBallots = ballotID + ".txt";
				File bID = new File(individualBallots);
				Scanner read = new Scanner(bID);
				String choice;
				File t = new File("tempvoters.txt");
				PrintWriter log = new PrintWriter(t);
				t.delete();
				t.createNewFile();
				
				while(read.hasNextLine()){
					choice = read.nextLine();
					for(int i = 0; i < 4; i++){
						String[] parts = choice.split(":");
						if(votedFor == parts[0]){
							log.println(options[i] + ":" + (Integer.parseInt(parts[1]) + 1));
						}
					}
				}
				log.close();
				read.close();
				bID.delete();
				t.renameTo(bID); //safe file system
				
			} 
			catch(Exception e){
				return;
			}
			
        
	}
	
	public static void writeToVoter(String v, String id){
		try{
			ArrayList<String[]> parts = new ArrayList<String[]>();
			File f = new File(v);
			File t = new File("tempvoters.txt");
			t.delete();
			t.createNewFile();
			PrintWriter log = new PrintWriter(t);
			
			Scanner read = new Scanner(f);
			while(read.hasNextLine()){
				String[] line = null;
				if(read.hasNextLine()){
					line = read.nextLine().trim().split(":");
				}
			
				if(line[0].equals(id)){
					log.print(id + ":");
					log.print(line[1] + ":");
					log.println("true");
				} 
				else{
					log.print(line[0] + ":");
					log.print(line[1] + ":");
					log.println(line[2]);
				}
			}
			log.close();
			read.close();
			f.delete();
			t.renameTo(f); //safe file system
			
		} catch(Exception e){
			return;
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
	
	public static void makeLabels(JFrame window, JPanel ballotPanel){ //generates labels for ballot titles
		String ballotName;
		
		for(int i = 0; i < ballots.size(); i++){
    		ballotName = ballots.get(i).returnBName();	
    		labels.add(new JLabel(ballotName));
    	}
		
		for(int k = 0; k < ballots.size(); k++){
			labels.get(k).setText(ballots.get(k).bName);
			labels.get(k).setForeground(Color.DARK_GRAY);
			labels.get(k).setHorizontalAlignment(SwingConstants.CENTER);
			labels.get(k).setFont(new Font("Impact", Font.PLAIN, 24));
	    	window.add(labels.get(k));
		}
	}
	
    public static void main(String[] args) throws IOException, FileNotFoundException{ //majority of GUI here
    	String ballot = args[0];
    	String voters = args[1];
    	
        readFrom(ballot);
        
        for(int i = 0; i < Assig4.ballots.size(); i++){ //generate arraylist of ballot specific buttons
    		Ballot.totalButtons.add(new JToggleButton[ballots.get(0).returnOptions().length]); //buttons per ballot, FIX THE ZERO
    	}
        
        JFrame window = new JFrame("vot.er");
    	window.setLocation(new Point(600, 350));
    	window.setSize(WIDTH, HEIGHT);
    	window.getContentPane().setBackground(Color.CYAN);
    	window.setForeground(Color.GREEN);
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	window.setVisible(true);
    	
    	FlowLayout flowLayout = new FlowLayout();
    	window.getContentPane().setLayout(flowLayout);
    	
    	for(int i = 0; i < ballots.size(); i++){
    		panels.add(new JPanel());
    		panels.get(i).setLayout(new BoxLayout(panels.get(i), BoxLayout.Y_AXIS));
    		panels.get(i).setVisible(true);
    	}
    	
    	for(int i = 0; i < ballots.size(); i++){
        	makeLabels(window, panels.get(i));	
    	}
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
    	Ballot.gui(ballots); //button related GUI
    	
		for(int j = 0; j < Ballot.totalButtons.size(); j++){
			for(int i = 0; i < Ballot.totalButtons.get(j).length; i++){
				panels.get(j).add(Ballot.totalButtons.get(j)[i]);
				window.add(Ballot.totalButtons.get(j)[i]);
			}
		}
    	JButton btnLogin = new JButton("LOGIN HERE");
		JButton castVote = new JButton("CAST VOTE");
    	castVote.setEnabled(false);
    	castVote.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.out.println("votes CAST! button pressed");
    			int voteit = JOptionPane.showConfirmDialog(castVote, "Are you sure you want to vote? There is no going back.");
    			
    			if(voteit == 0){
    				
						writeToVoter(voters, inputValue); 
    				
    				for(int i = 0; i < ballots.size(); i++){
    					try {
							writeToBallot(ballots.get(i).returnID(), ballots.get(i).returnOptions(), Ballot.buttonClicked);
							
						} 
    					catch (IOException e1) {
							e1.printStackTrace();
						}
    				}
    				castVote.setEnabled(false);
    				btnLogin.setEnabled(true);
    				for(int j = 0; j < Ballot.totalButtons.size(); j++){
						for(int i = 0; i < Ballot.totalButtons.get(j).length; i++){
							Ballot.totalButtons.get(j)[i].setEnabled(false);
							Ballot.totalButtons.get(j)[i].setSelected(false);
						}
					}
    			}
    		}
    	});
    	window.getContentPane().add(castVote);
    	
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
						castVote.setEnabled(true);
						for(int j = 0; j < Ballot.totalButtons.size(); j++){
							for(int i = 0; i < Ballot.totalButtons.get(j).length; i++){
								Ballot.totalButtons.get(j)[i].setEnabled(true);
								Ballot.totalButtons.get(j)[i].setSelected(false);
								Ballot.totalButtons.get(j)[i].setForeground(Color.BLACK);
							}
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
    	
    	window.setVisible(true);
    	
    }
}