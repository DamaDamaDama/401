import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;
/*
public class Ballot extends JPanel{
	String id;
	String bName;
	String bName2;
	String[] opSplit;
	public int counter = 1;
	
	public Ballot(String id, String bName, String[] opSplit){
		id = this.id;
		bName = this.bName;
		opSplit = this.opSplit;
	}
	
	public Ballot(){
		
	}
	
	public static void gui(){
		JFrame window = new JFrame("vot.er");
    	window.setLocation(new Point(600, 350));
    	window.setSize(WIDTH, HEIGHT);
    	window.getContentPane().setBackground(Color.PINK);
    	window.setForeground(Color.PINK);
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	GridBagLayout gridBagLayout = new GridBagLayout();
    	gridBagLayout.columnWidths = new int[]{177, 0, 52, 95, 113, 0, 127, 0};
    	gridBagLayout.rowHeights = new int[]{40, 38, 33, 29, 29, 29, 29, 0};
    	gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    	window.getContentPane().setLayout(gridBagLayout);
    	
    	JLabel mostPopular = new JLabel("MOST POPULAR");
    	mostPopular.setForeground(Color.DARK_GRAY);
    	mostPopular.setFont(new Font("Impact", Font.PLAIN, 30));
    	
    	GridBagConstraints gbc_mostPopular = new GridBagConstraints();
    	gbc_mostPopular.insets = new Insets(0, 0, 5, 5);
    	gbc_mostPopular.gridx = 1;
    	gbc_mostPopular.gridy = 1;
    	window.getContentPane().add(mostPopular, gbc_mostPopular);
    	
    	JLabel esport = new JLabel("ESPORT");
    	esport.setHorizontalAlignment(SwingConstants.CENTER);
    	esport.setForeground(Color.DARK_GRAY);
    	esport.setFont(new Font("Impact", Font.PLAIN, 24));
    	
    	GridBagConstraints gbc_esport = new GridBagConstraints();
    	gbc_esport.insets = new Insets(0, 0, 5, 5);
    	gbc_esport.gridx = 1;
    	gbc_esport.gridy = 2;
    	window.getContentPane().add(esport, gbc_esport);
    	
    	JLabel favorite = new JLabel("FAVORITE");
    	favorite.setForeground(Color.DARK_GRAY);
    	favorite.setHorizontalAlignment(SwingConstants.CENTER);
    	favorite.setFont(new Font("Impact", Font.PLAIN, 24));
    	
    	GridBagConstraints gbc_favorite = new GridBagConstraints();
    	gbc_favorite.anchor = GridBagConstraints.SOUTH;
    	gbc_favorite.insets = new Insets(0, 0, 5, 5);
    	gbc_favorite.gridx = 3;
    	gbc_favorite.gridy = 1;
    	window.getContentPane().add(favorite, gbc_favorite);
    	
    	JLabel food = new JLabel("FOOD");
    	food.setForeground(Color.DARK_GRAY);
    	food.setFont(new Font("Impact", Font.PLAIN, 24));
    	
    	GridBagConstraints gbc_food = new GridBagConstraints();
    	gbc_food.anchor = GridBagConstraints.NORTH;
    	gbc_food.insets = new Insets(0, 0, 5, 5);
    	gbc_food.gridx = 3;
    	gbc_food.gridy = 2;
    	window.getContentPane().add(food, gbc_food);
    	
    	int testCounter = 1;
    	
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
    	
    	
    	GridBagConstraints gbc_btnTF2 = new GridBagConstraints();
    	gbc_btnTF2.anchor = GridBagConstraints.NORTHWEST;
    	gbc_btnTF2.insets = new Insets(0, 0, 5, 5);
    	gbc_btnTF2.gridx = 1;
    	gbc_btnTF2.gridy = 3;
    	window.getContentPane().add(btnTF2, gbc_btnTF2);
    	
    	JButton btnPasta = new JButton("   Pasta  ");
    	btnPasta.setEnabled(false);
    	btnPasta.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.out.println("voting button pressed");
    			btnPasta.setForeground(Color.RED);
    		}
    	});
    	
    	GridBagConstraints gbc_btnPasta = new GridBagConstraints();
    	gbc_btnPasta.anchor = GridBagConstraints.NORTHWEST;
    	gbc_btnPasta.insets = new Insets(0, 0, 5, 5);
    	gbc_btnPasta.gridx = 3;
    	gbc_btnPasta.gridy = 3;
    	window.getContentPane().add(btnPasta, gbc_btnPasta);
    	
    	JToggleButton btnCSGO = new JToggleButton("         CSGO         ");
    	btnCSGO.setEnabled(false);
    	btnCSGO.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.out.println("voting button pressed");
    			btnCSGO.setForeground(Color.RED);
    		}
    	});
    	
    	GridBagConstraints gbc_btnCSGO = new GridBagConstraints();
    	gbc_btnCSGO.anchor = GridBagConstraints.NORTHWEST;
    	gbc_btnCSGO.insets = new Insets(0, 0, 5, 5);
    	gbc_btnCSGO.gridx = 1;
    	gbc_btnCSGO.gridy = 4;
    	window.getContentPane().add(btnCSGO, gbc_btnCSGO);
    	
    	JButton btnSteak = new JButton("   Steak  ");
    	btnSteak.setEnabled(false);
    	btnSteak.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.out.println("voting button pressed");
    			btnSteak.setForeground(Color.RED);
    		}
    	});
    	
    	GridBagConstraints gbc_btnSteak = new GridBagConstraints();
    	gbc_btnSteak.anchor = GridBagConstraints.NORTHWEST;
    	gbc_btnSteak.insets = new Insets(0, 0, 5, 5);
    	gbc_btnSteak.gridx = 3;
    	gbc_btnSteak.gridy = 4;
    	window.getContentPane().add(btnSteak, gbc_btnSteak);
    	
    	JButton btnLOL = new JButton("League of Legends");
    	btnLOL.setEnabled(false);
    	btnLOL.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.out.println("voting button pressed");
    			btnLOL.setForeground(Color.RED);
    		}
    	});
    	
    	GridBagConstraints gbc_btnLOL = new GridBagConstraints();
    	gbc_btnLOL.anchor = GridBagConstraints.NORTHWEST;
    	gbc_btnLOL.insets = new Insets(0, 0, 5, 5);
    	gbc_btnLOL.gridx = 1;
    	gbc_btnLOL.gridy = 5;
    	window.getContentPane().add(btnLOL, gbc_btnLOL);
    	
    	JButton btnPizza = new JButton("   Pizza  ");
    	btnPizza.setEnabled(false);
    	btnPizza.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.out.println("voting button pressed");
    			btnPizza.setForeground(Color.RED);
    		}
    	});
    	
    	GridBagConstraints gbc_btnPizza = new GridBagConstraints();
    	gbc_btnPizza.anchor = GridBagConstraints.NORTHWEST;
    	gbc_btnPizza.insets = new Insets(0, 0, 5, 5);
    	gbc_btnPizza.gridx = 3;
    	gbc_btnPizza.gridy = 5;
    	window.getContentPane().add(btnPizza, gbc_btnPizza);
    	
    	JButton castVote = new JButton("CAST VOTE");
    	castVote.setEnabled(false);
    	castVote.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.out.println("votes CAST! button pressed");
    		}
    	});
    	
    	castVote.setFont(new Font("Arial", Font.PLAIN, 16));
    	GridBagConstraints gbc_castVote = new GridBagConstraints();
    	gbc_castVote.anchor = GridBagConstraints.WEST;
    	gbc_castVote.insets = new Insets(0, 0, 5, 5);
    	gbc_castVote.gridx = 5;
    	gbc_castVote.gridy = 5;
    	window.getContentPane().add(castVote, gbc_castVote);
    	
    	JButton btnDOTA = new JButton("       DOTA 2        ");
    	btnDOTA.setEnabled(false);
    	btnDOTA.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.out.println("voting button pressed");
    			btnDOTA.setForeground(Color.RED);
    		}
    	});
    	
    	GridBagConstraints gbc_btnDOTA = new GridBagConstraints();
    	gbc_btnDOTA.anchor = GridBagConstraints.NORTHWEST;
    	gbc_btnDOTA.insets = new Insets(0, 0, 0, 5);
    	gbc_btnDOTA.gridx = 1;
    	gbc_btnDOTA.gridy = 6;
    	window.getContentPane().add(btnDOTA, gbc_btnDOTA);
    	
    	JButton btnBurger = new JButton("  Burger ");
    	btnBurger.setEnabled(false);
    	btnBurger.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.out.println("voting button pressed");
    			btnBurger.setForeground(Color.RED);
    		}
    	});
    	
    	GridBagConstraints gbc_btnBurger = new GridBagConstraints();
    	gbc_btnBurger.anchor = GridBagConstraints.NORTHWEST;
    	gbc_btnBurger.insets = new Insets(0, 0, 0, 5);
    	gbc_btnBurger.gridx = 3;
    	gbc_btnBurger.gridy = 6;
    	window.getContentPane().add(btnBurger, gbc_btnBurger);
    	
    	JButton btnLogin = new JButton("LOGIN HERE");
    	btnLogin.setFont(new Font("Arial", Font.PLAIN, 16));
    	btnLogin.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.out.println("login button pressed");
    			
    			JOptionPane welcome = new JOptionPane();
    			JDialog dialog = welcome.createDialog("vot.er");
    			String inputValue = JOptionPane.showInputDialog("Please enter your voter ID");
    			try {
    				String name = Voter.readVoters(Voter.voters, inputValue);
					if(!name.equals("")){
						welcome.setMessage("Welcome, " + name);
						dialog.show();
						btnTF2.setEnabled(true);
						btnPasta.setEnabled(true);
						btnDOTA.setEnabled(true);
						btnCSGO.setEnabled(true);
						btnSteak.setEnabled(true);
						btnPizza.setEnabled(true);
						btnLOL.setEnabled(true);
						btnBurger.setEnabled(true);
					}
					else if(name.equalsIgnoreCase("voted")){
						welcome.setMessage("Our records show that this ID has already participated in voting. No further voting is allowed.");
						dialog.show();
					}
					else{
						welcome.setMessage("No such voter ID found.");
						dialog.show();
					}
				} 
    			catch (FileNotFoundException fnfe) {
					System.out.println("file related error");
					fnfe.printStackTrace();
				}
    			
    		}
    	});
    	
    	GridBagConstraints gbc_btnLogin = new GridBagConstraints();
    	gbc_btnLogin.anchor = GridBagConstraints.SOUTHWEST;
    	gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
    	gbc_btnLogin.gridx = 5;
    	gbc_btnLogin.gridy = 2;
    	window.getContentPane().add(btnLogin, gbc_btnLogin);
    	
    	window.setVisible(true);
	}
	
	public String returnID(String id){
		return id;
	}
	
	public String returnBName(){
		return bName;
	}
	
	public String returnBName2(){
		return bName2;
	}
	
	public String[] returnOptions(){
		return opSplit;
	}
	
}
*/