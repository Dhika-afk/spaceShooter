package spaceShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.time.Duration;
import java.util.LinkedList;

public class Pemain extends AbstrakObjek {
	
	public Pemain(int x, int y) {
		super(x, y, 0, 0);
		id = 1;
	}
	
	public void update() {
		x = clamp(x + velX, 0, Game.WIDTH-48);
		y = clamp(y + velY, 0, Game.HEIGHT-55);;
	}
	
	public void collision(LinkedList<AbstrakObjek> objekLawan) {
		for(int i=0;i<objekLawan.size();i++) {
			AbstrakObjek objek = objekLawan.get(i);
			if(getBounds().intersects(objek.getBounds())) {
				collided = true;
				Duration waktu = Game.hud.getWaktu();
				long menit = waktu.toMinutes();
				long detik = waktu.minusMinutes(menit).toSeconds();
				Menu.waktuBertahan = String.format("%02d:%02d", menit, detik);
				Game.gameState = Game.STATE.GameOver;
				Database.save(Game.namaPemain, detik, Game.difficulty);
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 16);
	}
	
	public void addPeluru() {
		Game.handlerPemain.addObjek(new Peluru(x+10, y+4, 3, 0, Color.white));
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 16);
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	private int clamp(int val, int min, int max) {
		if(val < min) return min;
		else if(val > max) return max;
		else return val;
	}
	
}
