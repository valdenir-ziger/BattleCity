package Mid;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel implements Runnable{
	
	private Tank t;
	private int dir;
	private Bomb bomb;
	private int bombDir;
	private Rectangle tempp;
	private boolean one = true;
	private FileInputStream fi;
	private boolean end = false;
	private Rectangle myTankRect;
	private boolean isBomb = false;
	private boolean oneTime = false;
	private Vector <Bomb> bombVector;
	private Random random = new Random();
	private int [][] b = new int [14][14];
	private Vector <Bomb> enemyBombVector;
	private Image [] bombImage = new Image[4];
	private final Image [] img = new Image[2];
	private Vector <Rectangle> bombRectVector;
	private Vector <EnemyTank> enemyTankVector;
	private Vector <Rectangle> blockRectVector;
	private File mapFile = new File("map.txt");
	private Vector <Rectangle> enemyTankRectVector;
	private Vector <Rectangle> enemyTankRectVectorTemp;
	
	public Board(Menu m){

		bombVector = new Vector <Bomb> ();
		enemyBombVector = new Vector<Bomb>();
		bombRectVector = new Vector<Rectangle>();
		enemyTankVector = new Vector <EnemyTank> ();
		blockRectVector = new Vector <Rectangle> ();
		enemyTankRectVector = new Vector <Rectangle>();
		enemyTankRectVectorTemp = new Vector<Rectangle>();
		
		img[0]= new ImageIcon("black.png").getImage();
		img[1]= new ImageIcon("red.png").getImage();

		bombImage[0] = new ImageIcon("RM.png").getImage();
		bombImage[1] = new ImageIcon("LM.png").getImage();
		bombImage[2] = new ImageIcon("UM.png").getImage();
		bombImage[3] = new ImageIcon("DM.png").getImage();
		
		dir = 4;
		setSize(60*14,60*14);
		setLayout(null);
		
		t = new Tank(0, 0, this);
		
		try {
			fi = new FileInputStream(mapFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		byte[] bByte = new byte[16];
		for(int i=0;i<14;i++){
			try {
				fi.read(bByte);
			} catch (IOException e) {
				e.printStackTrace();
			}
			for(int j=0;j<14;j++){
				b[i][j]=bByte[j]-48;
			}
		}
		
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 14; j++) {
				if(b[i][j] == 1){
					blockRectVector.add(new Rectangle(j*60, i*60, 60, 60));
				}
			}
		}
		Vector<Rectangle> tr = new Vector<>();
		for (int i = 0; i < m.getNumOfTanks(); i++) {
			int x = random.nextInt(60*13) + 1;
			int y = random.nextInt(60*13) + 1;
			Rectangle temp = new Rectangle(x, y, 80, 80);
			for (int j = 0; j < blockRectVector.size(); j++) {
				while (blockRectVector.get(j).intersects(temp)) {
					x = random.nextInt(60*13) + 1;
					y = random.nextInt(60*13) + 1;
					temp = new Rectangle(x, y, 80, 80);					
				}
			}
			Rectangle temprect = new Rectangle(x, y, 80, 80);
			
			for (int j = 0; j < tr.size(); j++) {
				while(tr.get(j).intersects(temprect)){
					x = random.nextInt(60*13) + 1;
					y = random.nextInt(60*13) + 1;
					temprect = new Rectangle(x, y, 80, 80);	
				}
			}
			tr.add(temprect);
			EnemyTank etTemp = new EnemyTank(x, y, this);			
			enemyTankVector.add(etTemp);
			Thread thTemp;
			thTemp = new Thread(etTemp);
			thTemp.start();
		}
		setVisible(true);
		Thread th = new Thread(this);
		th.start();
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);

		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 14; j++) {
				g.drawImage(img[b[i][j]], j*60, i*60, null);
				if(b[i][j] == 1 && !oneTime){
					blockRectVector.add(new Rectangle(j*60, i*60, 60, 60));
					g.drawRect(j*60, i*60, 60, 60);
				}
			}
		}
		oneTime = true;
		
		g.drawImage(t.getTank(), t.getX(), t.getY(), null);
		myTankRect = new Rectangle(t.getX(), t.getY(), 80, 80);
		
		for (int i = 0; i < enemyTankVector.size(); i++) {
			g.drawImage(enemyTankVector.get(i).getTank(), enemyTankVector.get(i).getX(), enemyTankVector.get(i).getY(), null);
		}
		
		if(!isBomb) bombDir = dir;
		
		if(one && bombVector.size() > 0){
			for (int i = 0; i < bombVector.size(); i++) {
				bombRectVector.add(new Rectangle(bombVector.get(i).getX()+70, bombVector.get(i).getY()+25, 32, 32));
				bombRectVector.add(new Rectangle(bombVector.get(i).getX()-12, bombVector.get(i).getY()+24, 32, 32));
				bombRectVector.add(new Rectangle(bombVector.get(i).getX()+24, bombVector.get(i).getY()-12, 32, 32));
				bombRectVector.add(new Rectangle(bombVector.get(i).getX()+24, bombVector.get(i).getY()+68, 32, 32));	
			}
		}
		tempp = new Rectangle(24, 68, 32, 32);
		for(int i = 0; i < bombVector.size(); i++){
			switch(bombDir) {
			case 1 :
				g.drawImage(bombImage[0], bomb.getX()+70, bomb.getY()+25, null);
				tempp = new Rectangle(bombVector.get(i).getX()+70, bombVector.get(i).getY()+25, 32, 32);
				break;
			case 2 :
				g.drawImage(bombImage[1], bomb.getX()-12, bomb.getY()+24, null);
				tempp = new Rectangle(bombVector.get(i).getX()-12, bombVector.get(i).getY()+24, 32, 32);
				break;
			case 3 :
				g.drawImage(bombImage[2], bomb.getX()+24, bomb.getY()-12, null);
				tempp = new Rectangle(bombVector.get(i).getX()+24, bombVector.get(i).getY()-12, 32, 32);
				break;
			case 4 :
				g.drawImage(bombImage[3], bomb.getX()+24, bomb.getY()+68, null);
				tempp = new Rectangle(bombVector.get(i).getX()+24, bombVector.get(i).getY()+68, 32, 32);
				break;
			}
		}
		
		for (int i = 0; i < enemyTankRectVector.size(); i++) {
			if(enemyTankRectVector.get(i).intersects(myTankRect)){
				enemyTankVector.get(i).setMyTankAccidemt(true);		
			}	
		}
		
//		System.out.println(enemyBombVector.size());
//		for(int i = 0; i < enemyBombVector.size(); i++){
//			bombDir = enemyBombVector.get(i).getDir();
//			switch(bombDir) {
//			case 1 :
//				g.drawRect(enemyBombVector.get(i).getX()+70, enemyBombVector.get(i).getY()+25, 32, 32);
//				g.drawImage(bombImage[0], enemyBombVector.get(i).getX()+70, enemyBombVector.get(i).getY()+25, null);
//				tempp = new Rectangle(bombVector.get(i).getX()+70, bombVector.get(i).getY()+25, 32, 32);
//				break;
//			case 2 :
//				g.drawRect(enemyBombVector.get(i).getX()-12, enemyBombVector.get(i).getY()+24, 32, 32);
//				g.drawImage(bombImage[1], enemyBombVector.get(i).getX()-12, enemyBombVector.get(i).getY()+24, null);
//				tempp = new Rectangle(bombVector.get(i).getX()-12, bombVector.get(i).getY()+24, 32, 32);
//				break;
//			case 3 :
//				g.drawRect(enemyBombVector.get(i).getX()+24, enemyBombVector.get(i).getY()-12, 32, 32);
//				g.drawImage(bombImage[2], enemyBombVector.get(i).getX()+24, enemyBombVector.get(i).getY()-12, null);
//				tempp = new Rectangle(bombVector.get(i).getX()+24, bombVector.get(i).getY()-12, 32, 32);
//				break;
//			case 4 :
//				g.drawRect(enemyBombVector.get(i).getX()+24, enemyBombVector.get(i).getY()+68, 32, 32);
//				g.drawImage(bombImage[3], enemyBombVector.get(i).getX()+24, enemyBombVector.get(i).getY()+68, null);
//				tempp = new Rectangle(bombVector.get(i).getX()+24, bombVector.get(i).getY()+68, 32, 32);
//				break;
//			}
//		}

		for (int i = 0; i < enemyTankVector.size(); i++) {
			enemyTankRectVector.add(new Rectangle(enemyTankVector.get(i).getX(), enemyTankVector.get(i).getY(), 80, 80));
		}

//		for (int i = 0; i < enemyTankVector.size(); i++) {
//			if(enemyTankVector.get(i).getEnemyTankRect().intersects(myTankRect)){
//				System.exit(0);
//			}
//		}

		if(tempp.y<10 || tempp.y> 830 || tempp.x<10 || tempp.x> 830 || checkIntersects(tempp) && isBomb){
			bombVector.remove(bomb);
			isBomb = false;
		}

		for (int i = 0; i < enemyTankRectVector.size(); i++) {
			for (int j = 0; j < enemyTankRectVector.size(); j++) {
				if(enemyTankRectVector.get(i).intersects(enemyTankRectVector.get(j)) && i!=j){
					enemyTankVector.get(i).setAccident(true);
					enemyTankVector.get(j).setAccident(true);
				}
			}
		}

		for (int i = 0; i < enemyTankRectVector.size(); i++) {
				if(enemyTankRectVector.get(i).intersects(myTankRect)){
					enemyTankVector.get(i).setAccident(true);
				}
				else if(!enemyTankRectVector.get(i).intersects(myTankRect)){
				}
		}

		for (int i = 0; i < enemyTankRectVector.size(); i++) {
			if (enemyTankRectVector.get(i).intersects(tempp) && isBomb){
				enemyTankVector.remove(i);
				bombVector.remove(bomb);
				isBomb = false;
//				enemyTankVector.get(i).setAlive(false);
			}
		}
		enemyTankRectVectorTemp.removeAllElements();
		for (int i = 0; i < enemyTankRectVector.size(); i++){
			enemyTankRectVectorTemp.add(enemyTankRectVector.get(i));
		}
		enemyTankRectVector.removeAllElements();

//		if(enemyTankVector.isEmpty() && a == 0){
//			JOptionPane.showMessageDialog(null,"You won :)" , "WON",JOptionPane.PLAIN_MESSAGE);
//			System.exit(0);
//			a++;
//		}

		if(enemyTankVector.size()==0){
			end = true;
		}
	}
	
	public Vector<Rectangle> getEnemyTankRectVector() {
		return enemyTankRectVector;
	}
	
	public Vector<EnemyTank> getEnemyTankVector() {
		return enemyTankVector;
	}
	
	public Rectangle getMyTankRect() {
		return myTankRect;
	}


	@Override
	public void run(){
		while (!end) {
			repaint();
		}
		JOptionPane.showMessageDialog(null, "You won");
		System.exit(0);
	}

	@Override
	protected void processComponentEvent(ComponentEvent arg0) {
		if(arg0.getID()==Massages.PUTMYBOMB) bombVector.add(((test)arg0).getB());
		if(arg0.getID()==Massages.PUTENEMYBOMB){
			System.out.println(222222);
			enemyBombVector.add(((test)arg0).getB());
		}
		super.processComponentEvent(arg0);
	}
	
	public boolean checkIntersects(Rectangle tankRect) {
		for (Rectangle r : blockRectVector) {
			if (r.intersects(tankRect)) return true;
		}
		
//		for (Rectangle r : enemyTankRectVector) {
//			if (r.intersects(tankRect)) return true;
//		}
		return false;
	}

	public boolean checkIntersectsX(Rectangle tankRect) {
		for (int i = 0; i < enemyTankRectVectorTemp.size(); i++) {
			if(enemyTankRectVectorTemp.get(i).intersects(tankRect)){
				return true;
			}
		}
		return false;
	}	
	private boolean blockCheck(int dir , Rectangle a){
		switch (dir) {
		case 1:
			a.x +=8;
			if(checkIntersects(a) || checkIntersectsX(a)){
				a.x -= 8;
				return true;
			}
			break;

		case 2:
			a.x -=8;
			if(checkIntersects(a) || checkIntersectsX(a)){
				a.x += 8;
				return true;
			}
			break;
		case 3:
			a.y -=8;
			if(checkIntersects(a) || checkIntersectsX(a)){
				a.x += 8;
				return true;
			}
			break;
		case 4:
			a.y +=8;
			if(checkIntersects(a) || checkIntersectsX(a)){
				a.y -= 8;
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}

	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT){
			setDir(1);
			if(!blockCheck(getDir(), myTankRect))t.goRight();
		}
		else if (arg0.getKeyCode() == KeyEvent.VK_LEFT){
			setDir(2);
			if(!blockCheck(getDir(), myTankRect)) t.goLeft();		
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_UP ){
			setDir(3);
			if(!blockCheck(getDir(), myTankRect))t.goUp();
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_DOWN){
			setDir(4);
			if(!blockCheck(getDir(), myTankRect))t.goDown();
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_SPACE && !isBomb){
			bomb = new Bomb(t.getX(),t.getY(), this);
			this.dispatchEvent(new test(this, Massages.PUTMYBOMB, bomb));
			bomb.start();
			isBomb = true;
		}
	}

	public Vector<Rectangle> getRect() {
		return blockRectVector;
	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {
		
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}
}
