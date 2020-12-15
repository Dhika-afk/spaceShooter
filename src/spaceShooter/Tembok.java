package spaceShooter;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Tembok extends AbstrakObjek {
	
	private Rectangle bentuk;
	
	public Tembok(int x, int y, int lebar, int tinggi) {
		super(x, y, 0, 0);
		id = 5;
		bentuk = new Rectangle(x, y, lebar, tinggi);
	}
	
	public void render(Graphics g) {
		
	}
	
	public Rectangle getBounds() {
		return bentuk;
	}
	
}
