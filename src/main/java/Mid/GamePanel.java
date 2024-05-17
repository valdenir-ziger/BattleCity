package Mid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GamePanel extends JFrame implements KeyListener{
	
	private int w;
	private int h;
	private Board board;
        private Dimension d;
	public GamePanel(int w, int h, Menu m) {
                this.setW(w);
                this.setH(h);
                this.setupJFrame(m);
                this.setupBoard(m);
		addKeyListener(this);
		setVisible(true);
	}

	private void setupJFrame(Menu m){
		setTitle(m.getPlayerName() +"'s game");
		this.d =Toolkit.getDefaultToolkit().getScreenSize();
		setSize(w, h);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setFocusable(true);
	}

	private void setupBoard(Menu m){
		board = new Board(m);
		board.setSize(800, 640);
		board.setLocation((d.width-w)/2,(d.height-h)/2-150);
		board.setBackground(Color.BLACK);
		add(board);
	}
	
	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		board.keyPressed(arg0);
		setFocusable(true);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	
	}
}
