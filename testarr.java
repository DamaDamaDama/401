import javax.swing.JDialog;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;

public class testarr {
	public static void main(String args[]) throws IOException{
		
		File f = new File("testarr.txt");
		f.createNewFile();
		
		FileOutputStream fos = new FileOutputStream("testarr.txt", true);
	    PrintWriter      log  = new PrintWriter(fos);
	     
	    
	    log.println("FUCKING WORK PELASE");
	    log.close();
		
		JOptionPane welcome = new JOptionPane();
		JDialog dialog = welcome.createDialog("vot.er");
		welcome.setMessage("Welcome, Ken M!");
		dialog.show();
		
		String test = "Blah 8lah bleh";
		
		String[] parts = test.split(" ");
		
		System.out.println(parts[0]);
		
		System.out.println(parts[2]);
		
		System.out.println(parts[1]);
		
		int[] a = new int[5];
		a[1] = 17;
		a[4] = 2;
		try{
		a[5] = 3;
		System.out.println(a[5]);
		}
		catch(ArrayIndexOutOfBoundsException ARIOOBE){
			System.out.println("Doesn't work");
		}
		a = new int[6];
		System.out.println(a[5]);
		System.out.println(a[1]);
	}
}
