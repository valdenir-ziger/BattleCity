package Mid;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Menu extends JDialog implements ActionListener{

	private JLabel name;
	private Image logoo;
	private Image logooo;
	private ImageIcon logo;
	private int numOfTanks;
	private String playerName;
	private JLabel tankNumber;
	private String tanksNumber;
	private MenuButton exitButton;
	private MenuButton startButton;
	private JLabel chooseDifficualty;
	private JComboBox<String> difficualty;
	private JTextField nameField, tankNumberField;
	
	public Menu() {
		setTitle("Tank War");
		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		
		name = new JLabel("Name: ");
		name.setSize(100,25);
		name.setLocation(50, 20);
		getContentPane().add(name);
		
		nameField = new JTextField();
		nameField.setSize(80, 25);
		nameField.setLocation(180,20);
		nameField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				name.setForeground(Color.BLACK);
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				name.setForeground(Color.GRAY);
					
			}
		});
		getContentPane().add(nameField);

		tankNumber = new JLabel("tank number: ");
		tankNumber.setSize(100,25);
		tankNumber.setLocation(50, 50);
		getContentPane().add(tankNumber);
		
		tankNumberField = new JTextField();
		tankNumberField.setSize(80, 25);
		tankNumberField.setLocation(180,50);
		tanksNumber = tankNumberField.getText();
		tankNumberField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				tankNumber.setForeground(Color.BLACK);
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				tankNumber.setForeground(Color.GRAY);
			}
		});
		getContentPane().add(tankNumberField);

		chooseDifficualty = new JLabel("Difficualty: ");
		chooseDifficualty.setSize(100,25);
		chooseDifficualty.setLocation(50, 80);
		getContentPane().add(chooseDifficualty);
		
		difficualty = new JComboBox<String>();
		difficualty.setSize(80, 25);
		difficualty.setLocation(180,80);
		difficualty.addItem("Easy");
		difficualty.addItem("Meduim");
		difficualty.addItem("Hard");
		difficualty.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				chooseDifficualty.setForeground(Color.BLACK);
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				chooseDifficualty.setForeground(Color.GRAY);
			}
		});
		getContentPane().add(difficualty);
		logo = new ImageIcon("logo.png");
		logoo = logo.getImage();
		logooo = logoo.getScaledInstance(150, 100, Image.SCALE_DEFAULT);
		
		startButton = new MenuButton("Start", this);
		startButton.setSize(80,25);
		startButton.setLocation(150,400);
		getContentPane().add(startButton);
		
		exitButton = new MenuButton("Exit", this);
		exitButton.setSize(80,25);
		exitButton.setLocation(270,400);
		getContentPane().add(exitButton);
		
		setVisible(true);
	}

	public String getPlayerName() {
		return playerName;
	}
	
	public String getNameField(){
		return nameField.getText();
	}
	
	public String getTanksNumberField(){
		return tankNumberField.getText();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(logoo, 15, 200, null);
		g.drawImage(logooo, 50, 50, null);
	}
	
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public String getTanksNumber() {
		return tanksNumber;
	}

	public void setTanksNumber(String tanksNumber) {
		this.tanksNumber = tanksNumber;
	}

	public int getNumOfTanks() {
		return numOfTanks;
	}

	public void setNumOfTanks(int numOfTanks) {
		this.numOfTanks = numOfTanks;
	}
	
	@Override
	protected void processEvent(AWTEvent arg0) {
		if(arg0.getID() == Massages.PLAY){
			new GamePanel(60*14, 60*14, this);
		}	
		super.processEvent(arg0);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
}
