package spaceShooter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Pemain objekPemain;
	
	public KeyInput(Handler handlerPemain) {
		objekPemain = (Pemain) handlerPemain.objekTim.getFirst();
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W) objekPemain.setVelY(-3);
		if(key == KeyEvent.VK_A) objekPemain.setVelX(-3);
		if(key == KeyEvent.VK_S) objekPemain.setVelY(3);
		if(key == KeyEvent.VK_D) objekPemain.setVelX(3);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W) objekPemain.setVelY(0);
		if(key == KeyEvent.VK_A) objekPemain.setVelX(0);
		if(key == KeyEvent.VK_S) objekPemain.setVelY(0);
		if(key == KeyEvent.VK_D) objekPemain.setVelX(0);
	}
	
}
