package spaceShooter;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	public LinkedList<AbstrakObjek> objekTim;
	public LinkedList<AbstrakObjek> objekLawan;
	
	public Handler(LinkedList<AbstrakObjek> objekTim, LinkedList<AbstrakObjek> objekLawan) {
		this.objekTim = objekTim;
		this.objekLawan = objekLawan;
	}
	
	public void update() {
		for(int i=0;i<objekTim.size();i++) {
			AbstrakObjek objek = objekTim.get(i);
			objek.update();
		}
	}
	
	public void collision() {
		for(int i=0;i<objekTim.size();i++) {
			AbstrakObjek objek = objekTim.get(i);
			if(objek.getId() != 5) {
				objek.collision(objekLawan);
			}
		}
	}
	
	public void destroyCollided() {
		for(int i=0;i<objekTim.size();i++) {
			AbstrakObjek objek = objekTim.get(i);
			if(objek.collided) {
				removeObjek(objek);
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i=0;i<objekTim.size();i++) {
			AbstrakObjek objek = objekTim.get(i);
			objek.render(g);
		}
	}
	
	public void addObjek(AbstrakObjek objek) {
		objekTim.add(objek);
	}
	
	public void removeObjek(AbstrakObjek objek) {
		objekTim.remove(objek);
	}
	
}
