package spaceShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Peluru extends AbstrakObjek {
	
	private Color color;
	
	public Peluru(int x, int y, int velX, int velY, Color color) {
		super(x, y, velX*Game.difficulty, velY);
		id = 4;
		this.color = color;
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, 8, 8);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 8, 8);
	}
	
}
