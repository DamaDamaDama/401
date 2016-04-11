import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;

public class Ballot extends JPanel{
	String id;
	String bName;
	String bName2;
	static String op1;
	String op2;
	String op3;
	String op4;
	
	public Ballot(String id, String bName, String bName2, String op1, String op2, String op3, String op4){
		id = this.id;
		bName = this.bName;
		bName2 = this.bName2;
		op1 = this.op1;
		op2 = this.op2;
		op3 = this.op3;
		op4 = this.op4;
	}
	
	public Ballot(){
		
	}
	
}
