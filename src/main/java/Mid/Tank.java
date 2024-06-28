package Mid;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Tank extends JLabel {
	private int x,y;
	private Image tank;
	private static final int STEP_SIZE = 4;
	private static final int MAX_X = 800;
	private static final int MAX_Y = 640;

	public Tank(int x, int y, Board board){
		this.y = y;
		this.x = x;
		loadImage("src/main/java/tank");
		x = 1;
		y = 1;
	}
	
	public void loadImage(String img) {
		ImageIcon usFlag = new ImageIcon(img+".png");
		tank = usFlag.getImage();
	}
	
	public void goRight(){
		if(x < MAX_X){
			loadImage("src/main/java/R");
			x+=STEP_SIZE;
		}
	}

	public void goLeft(){
		if(x>0){
			loadImage("src/main/java/L");
			x-=STEP_SIZE;	
		}
	}

	public void goUp(){
		if(y>0){
			loadImage("src/main/java/U"); 
			y-=STEP_SIZE;	
		}
	}

	public void goDown(){
		if(y<MAX_Y){
			loadImage("src/main/java/D"); 
			y+=STEP_SIZE;	
		}
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

}