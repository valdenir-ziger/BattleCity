package Mid;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Tank extends JLabel {
	private int x,y;
	private Image tank;

	public Tank(int x, int y, Board board){
		this.y = y;
		this.x = x;
		loadImage("tank");
		x = 0;
		y = 0;
	}
	
	public void loadImage(String img) {
		ImageIcon usFlag = new ImageIcon(img+".png");
		tank = usFlag.getImage();
	}
	
	public void goRight(){
		if(x < 765){
			loadImage("R");
			x+=6;
		}
	}

	public void goLeft(){
		if(x>0){
			loadImage("L");
			x-=6;	
		}
	}

	public void goUp(){
		if(y>0){
			loadImage("U"); 
			y-=6;	
		}
	}

	public void goDown(){
		if(y<730){
			loadImage("D"); 
			y+=6;	
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