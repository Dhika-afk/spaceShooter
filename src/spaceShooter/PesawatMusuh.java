package spaceShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PesawatMusuh extends AbstrakObjek {
	
	public PesawatMusuh(int x, int y, int velX, int velY) {
		super(x, y, velX*Game.difficulty, velY);
		id = 2;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 32, 16);
	}
	
	public void addPeluru() {
		Game.handlerMusuh.addObjek(new Peluru(x-10, y+4, -3, 0, Color.red));
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 16);
	}
	
}
