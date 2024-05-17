package Mid;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class EnemyTank extends JLabel implements Runnable {

	private int x, y;
	private Image tank;
	private Board board;
	private Rectangle EnemyTankRect;
	private boolean accident;
	private Vector<Bomb> bombVector;
	private int dir = 1;
	private boolean MyTankAccident = false;

	public boolean isMyTankAccidemt() {
		return MyTankAccident;
	}

	public void setMyTankAccidemt(boolean myTankAccidemt) {
		MyTankAccident = myTankAccidemt;
	}

	public void setAlive(boolean isAlive) {
	}

	public EnemyTank(int x, int y, Board board) {
		this.y = y;
		this.x = x;
		this.board = board;
		bombVector = new Vector<Bomb>();
		loadImage("tank");
	}

	public void loadImage(String img) {
		ImageIcon usFlag = new ImageIcon(img + ".png");
		tank = usFlag.getImage();
	}

	public void goRight() {
		loadImage("R");
		x += 1;
	}

	public void goLeft() {
		loadImage("L");
		x -= 1;
	}

	public void goUp() {
		loadImage("U");
		y -= 1;
	}

	public void goDown() {
		loadImage("D");
		y += 1;
	}
	
	public Vector<Bomb> getBombVector() {
		return bombVector;
	}

	public Image getTank() {
		return tank;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}


	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}


	public void setAccident(boolean accident) {
		this.accident = accident;
	}

	public Rectangle getEnemyTankRect() {
		return EnemyTankRect;
	}
	
	private int changeDir(int dir){
		int dirTemp = dir;
		Random x = new Random();
		dir = x.nextInt(4) + 1;
		while(dirTemp == dir){
			dir = x.nextInt(4) + 1;
		}
		return dir;
	}

	private int accidentChangeDir(int dir){

		switch (dir) {
		case 1: dir = 2; x-=2; break;
		case 2: dir = 1; x+=2; break;
		case 3: dir = 4; y+=2; break;
		case 4: dir = 3; y-=2; break;
		}
		
		return dir;
	}
	
	private boolean blockCheck(int dir , Rectangle a){
		switch (dir) {
		case 1:
			a.x +=2;
			if(board.checkIntersects(a)){
				a.x -= 2;
				return true;
			}
			break;

		case 2:
			a.x -=2;
			if(board.checkIntersects(a)){
				a.x += 2;
				return true;
			}
			break;
		case 3:
			a.y -=2;
			if(board.checkIntersects(a)){
				a.x += 2;
				return true;
			}
			break;
		case 4:
			a.y +=2;
			if(board.checkIntersects(a)){
				a.y -= 2;
				return true;
			}
			break;
			
		default:
			break;
		}
		return false;
	}
	
	
	
	@Override
	public void run() {
		dir = changeDir(dir);
		while (true) {
			for (int i = 0; i < board.getEnemyTankVector().size(); i++) {
//				if(EnemyTankRect.intersects(board.getEnemyTankRectVector().get(i))) tankAccident = true;
			}
//			Random xxx = new Random();
//			int r = xxx.nextInt(15) + 1;
//			if(r == 1){
//				System.out.println(11111111);
//				bomb.setDir(dir);
//				bomb = new Bomb(x,y, board);
//				board.dispatchEvent(new test(this, Massages.PUTENEMYBOMB, bomb));
//				bomb.start();
//			}
			
			new Random();
			
			switch (dir) {
			case 1:
				while (dir == 1) {
					Random xx = new Random();
					int chance = xx.nextInt(1000) + 1;
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					goRight();
					EnemyTankRect = new Rectangle(getX(), getY(), 80, 80);
					try{
						if (x > 760 || chance == 1)
							dir = changeDir(dir);
						else if(blockCheck(dir, EnemyTankRect)){
							dir = changeDir(dir);
						}
						if(accident){
							dir = accidentChangeDir(dir);
						}
						if(MyTankAccident){
							dir = accidentChangeDir(dir);
						}
					}
					finally{
						accident = false;
						MyTankAccident = false;
					}	
				}
				break;

			case 2:
				
				while (dir == 2) {
					Random xx = new Random();
					int chance = xx.nextInt(1000) + 1;
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					goLeft();
					EnemyTankRect = new Rectangle(getX(), getY(), 80, 80);
					
					try{
						if (x < 0 || chance == 1)
							dir = changeDir(dir);
						else if(blockCheck(dir, EnemyTankRect)){
							dir = changeDir(dir);
						}
						if(accident){
							dir = accidentChangeDir(dir);
						}
						if(MyTankAccident){
							dir = accidentChangeDir(dir);
						}
					}
					finally{
						accident = false;
						MyTankAccident = false;
					}
				}
				
				break;
			case 3:
				while (dir == 3) {
					Random xx = new Random();
					int chance = xx.nextInt(1000) + 1;
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					goUp();
					EnemyTankRect = new Rectangle(getX(), getY(), 80, 80);
					
					try{
						if (y < 0 || chance == 1)
							dir = changeDir(dir);
						else if(blockCheck(dir, EnemyTankRect)){
							dir = changeDir(dir);
							
						}
						if(accident){
							dir = accidentChangeDir(dir);
						}
						if(MyTankAccident){
							dir = accidentChangeDir(dir);
						}
					}
					finally{
						accident = false;
						MyTankAccident = false;
					}
				}
				break;
			case 4:
				while (dir == 4) {
					Random xx = new Random();
					int chance = xx.nextInt(1000) + 1;
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					goDown();
					EnemyTankRect = new Rectangle(getX(), getY(), 80, 80);
					
					try{
						if (y > 730 || chance == 1)
							dir = changeDir(dir);
						else if(blockCheck(dir, EnemyTankRect)){
							dir = changeDir(dir);
						}
						if(accident){
							dir = accidentChangeDir(dir);
						}
						if(MyTankAccident){
							dir = accidentChangeDir(dir);
						}
					}
					finally{
						accident = false;
						MyTankAccident = false;
					}
					
				}
				default: 
					break;
			}		
		}
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}
}
