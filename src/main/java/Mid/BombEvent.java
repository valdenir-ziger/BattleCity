package Mid;

import java.awt.Component;
import java.awt.event.ComponentEvent;

@SuppressWarnings("serial")
class BombEvent extends ComponentEvent{
	
	private int w;
	private int h;
	
	public BombEvent(Component o, int id, int w, int h){
		super(o,id);
		this.w=w;
		this.h=h;
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
	
}