import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class testarr {
	public static void main(String args[]){
		
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
