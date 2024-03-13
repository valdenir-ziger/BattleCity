package Mid;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import Mid.Massages;
import Mid.BombEvent;

@SuppressWarnings("serial")
public class MenuButton extends JButton implements ActionListener, KeyListener{

	Menu m;
	static int numberOfTanks;
	public MenuButton(String s, Menu m) {
		super(s);
		this.m=m;
		addActionListener(this);
		addKeyListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if("Exit".equals(getText()))
			System.exit(0);
		if("Start".equals(getText())){
			m.setPlayerName(m.getNameField());
			m.setTanksNumber(m.getTanksNumberField());
			
			if(m.getPlayerName().equals("")){
				JOptionPane.showMessageDialog(null,"Enter your name bro!" , "ERORR",JOptionPane.PLAIN_MESSAGE);
			}
			else if (m.getTanksNumber().equals("")){
				JOptionPane.showMessageDialog(null,"Enter tanks number bro!" , "ERORR",JOptionPane.PLAIN_MESSAGE);
			}
			else{
				m.setNumOfTanks(Integer.parseInt(m.getTanksNumber()));
				m.dispatchEvent(new BombEvent(this, Massages.PLAY, 800, 600));	
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if("Exit".equals(getText()))
				System.exit(0);
			if("Start".equals(getText())){
				m.setPlayerName(m.getNameField());
				m.setTanksNumber(m.getTanksNumberField());
				
				if(m.getPlayerName().equals("")){
					JOptionPane.showMessageDialog(null,"Enter your name bro!" , "ERORR",JOptionPane.PLAIN_MESSAGE);
				}
				else if (m.getTanksNumber().equals("")){
					JOptionPane.showMessageDialog(null,"Enter tanks number bro!" , "ERORR",JOptionPane.PLAIN_MESSAGE);
				}
				else{
					m.setNumOfTanks(Integer.parseInt(m.getTanksNumber()));
					m.dispatchEvent(new BombEvent(this, Massages.PLAY, 800, 600));	
				}
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

