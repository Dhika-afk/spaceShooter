package spaceShooter;

public class TickThread extends Thread {
	
	private double delta;
	private Handler handler;
	
	TickThread(double delta, Handler handler) {
		this.delta = delta;
		this.handler = handler;
	}
	
	public void run() {
		while(delta >= 1) {
			handler.update();
			delta--;
		}
	}
	
}
