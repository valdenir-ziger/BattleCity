package Mid;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener{

	
	public Main() {
		initGUI();
	}
	
	public void initGUI(){
		new Menu();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
	    String comStr = arg0.getActionCommand();
	    System.out.println(comStr + " Selected");
		
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
	
}
