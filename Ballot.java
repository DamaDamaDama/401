import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;

public class Ballot extends JPanel{
	String id;
	String bName;
	String[] opSplit;
	static boolean selectedVote;
	static ArrayList<String> buttonClicked = new ArrayList<String>();
	static ArrayList<JToggleButton[]> totalButtons = new ArrayList<JToggleButton[]>(); //arraylists per ballot, in my case this was 2
	
	public Ballot(String id, String bName, String[] opSplit){
		this.id = id;
		this.bName = bName;
		this.opSplit = opSplit;
	}
	
	public Ballot(){
		
	}
	
	public static void gui(ArrayList<Ballot> ballots){
		
    	
    	
    	/* PRESERVED FOR LATER LOOKING
    	JButton btnTF2 = new JButton("Team Fortress 2");
    	btnTF2.setEnabled(false);
    	btnTF2.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.out.println("voting button pressed");
    			if(testCounter % 2 != 0){
    				btnTF2.setForeground(Color.RED);
    				btnTF2.setSelected(true);
    			}
    			else{
    				btnTF2.setForeground(Color.BLACK);
    			}
    			
    		}
    	});
    	*/
		
    	
    	for(int j = 0; j < ballots.size(); j++){ //generate buttons belonging to arraylists that belong to an arraylist
			for(int i = 0; i < 4; i++){
				totalButtons.get(j)[i] = new JToggleButton();
				totalButtons.get(j)[i].setText(ballots.get(j).returnOptions()[i]);
				totalButtons.get(j)[i].setFont(new Font("Arial", Font.PLAIN, 16));
				totalButtons.get(j)[i].setEnabled(false);
				totalButtons.get(j)[i].addActionListener(ButtonListener.buttonListener);
					

				System.out.println(totalButtons.get(j).length);
			}
    	}
    	
	}

	public static class ButtonListener implements ActionListener{
		static final ButtonListener buttonListener = new ButtonListener();
		
		public void actionPerformed(ActionEvent e){
			System.out.println("VOTING RELATED BUTTON HAS BEEN PRESSED!");
			JToggleButton theButton = (JToggleButton) e.getSource();
			
			if(theButton.isSelected()){
				theButton.setForeground(Color.RED);
				selectedVote = true;
				buttonClicked.add(theButton.getText());
				
			} 
			else if(!theButton.isSelected()){
				theButton.setForeground(Color.BLACK);
				selectedVote = false;
				buttonClicked.remove(theButton.getText());
			}
		}
		
	}
	
	public String returnID(){
		return id;
	}
	
	public String returnBName(){
		return bName;
	}
	
	public String[] returnOptions(){
		return opSplit;
	}
}
