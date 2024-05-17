package Mid;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Bomb extends Thread{
	
	int x,y;
	private Board board;
	int dir;
	public Bomb(int x, int y, Board board){
		this.x=x;
		this.y=y;
		this.board = board;
	}
	
	public int getDir() {
		return dir;
	}

	public void loadImage(String img) {
		ImageIcon usFlag = new ImageIcon(img+".png");
		usFlag.getImage();
	}
	
	public void goRight(){
		loadImage("RM");
	}

	public void goLeft(){
		loadImage("LM");
	}

	public void goUp(){
		loadImage("UM");
	}

	public void goDown(){
		loadImage("DM"); 
	}
	
	public void run(){

		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		
		switch(board.getDir()){
		case 1:
			for(int i=x;i<d.getWidth();i+=5){
				x=i;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case 2:
			for(int i=x;i>0;i-=5){
				x=i;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case 3:
			for(int i=y;i>0;i-=5){
				y=i;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case 4:
			for(int i=y;i<d.getHeight();i+=5){
				y=i;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void setDir(int dir) {
		this.dir = dir;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
