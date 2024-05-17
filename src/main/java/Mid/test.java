package Mid;

import java.awt.Component;
import java.awt.event.ComponentEvent;

public class test extends ComponentEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bomb b;
	
	public test(Component arg0, int arg1, Bomb b) {
		
		super(arg0, arg1);
		this.b=b;
	}

	public Bomb getB() {
		return b;
	}
}
