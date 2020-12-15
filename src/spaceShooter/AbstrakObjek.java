package spaceShooter;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class AbstrakObjek implements InterfaceDisplay {
	
	protected int x;
	protected int y;
	protected int velX;
	protected int velY;
	protected int id; // 1=PesawatPemain, 2=PesawatMusuh, 3=Asteroid, 4=PeluruPemain, 5=PeluruMusuh
	protected boolean collided;
	
	public AbstrakObjek(int x, int y, int velX, int velY) {
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
	}

	public void update() {
		x = x + velX;
		y = y + velY;
	}
	
	public void collision(LinkedList<AbstrakObjek> objekLawan) {
		for(int i=0;i<objekLawan.size();i++) {
			AbstrakObjek objek = objekLawan.get(i);
			if(getBounds().intersects(objek.getBounds())) {
				collided = true;
			}
		}
	}
	
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getVelX() {
		return velX;
	}
	public int getVelY() {
		return velY;
	}
	public int getId() {
		return id;
	}
	
}
