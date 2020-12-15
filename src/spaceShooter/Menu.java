package spaceShooter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Menu extends MouseAdapter {
	
	private Game game;
	public static String waktuBertahan;
	private HasilPemain[] leaderboard;
	
	public Menu(Game game) {
		this.game = game;
		leaderboard = new HasilPemain[10];
		clearLeaderboard();
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		JFrame frame = new JFrame();
		
		if(Game.gameState == Game.STATE.Menu) {
			if(mouseOver(mx, my, 160, 130, 350, 50)) {
				String masukan = JOptionPane.showInputDialog(frame,"Masukkan Nama");
				if(masukan == null);
				else if(!cekNama(masukan)){
					JOptionPane.showMessageDialog(frame,
							"Masukan harus berisi huruf atau digit dengan maks 10 karakter.",
							"Masukan Invalid",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Game.namaPemain = masukan;
				}
			}
			
			if(mouseOver(mx, my, 40, 210, 270, 40)) {
				if(cekNama(Game.namaPemain)) {
					Game.difficulty = 2;
					Game.gameState = Game.STATE.Game;
					game.initialize();
					Game.startTime = System.nanoTime();
				} else {
					String masukan = JOptionPane.showInputDialog(frame,"Masukkan Nama");
					if(masukan == null);
					else if(!cekNama(masukan)){
						JOptionPane.showMessageDialog(frame,
								"Masukan harus berisi huruf atau digit dengan maks 10 karakter.",
								"Masukan Invalid",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Game.namaPemain = masukan;
					}
				}
			}
			
			if(mouseOver(mx, my, 40, 270, 270, 40)) {
				if(cekNama(Game.namaPemain)) {
					Game.difficulty = 3;
					Game.gameState = Game.STATE.Game;
					game.initialize();
					Game.startTime = System.nanoTime();
				} else {
					String masukan = JOptionPane.showInputDialog(frame,"Masukkan Nama");
					if(masukan == null);
					else if(!cekNama(masukan)){
						JOptionPane.showMessageDialog(frame,
								"Masukan harus berisi huruf atau digit dengan maks 10 karakter.",
								"Masukan Invalid",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Game.namaPemain = masukan;
					}
				}
			}
			
			if(mouseOver(mx, my, 361, 210, 270, 40)) {
				clearLeaderboard();
				Database.load(leaderboard);
				Game.gameState = Game.STATE.Leaderboard;
			}
			
			if(mouseOver(mx, my, 361, 270, 270, 40)) {
				System.exit(0);
			}
		}
		
		if(Game.gameState == Game.STATE.GameOver || Game.gameState == Game.STATE.Leaderboard) {
			if(mouseOver(mx, my, -1, -1, 120, 40)) {
				Game.gameState = Game.STATE.Menu;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx >= x && mx <= x+width) {
			if(my >= y && my <= y+height) {
				return true;
			} else return false;
		} else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Font fnt = new Font("arial", 1, 20);
		
		if(Game.gameState == Game.STATE.Menu) {
			g.setColor(Color.white);
			
			g.drawRect(160, 40, 350, 60);
			g.setFont(fnt);
			g.drawString("2D SPACE SHOOTER", 240, 75);
			
			g.drawRect(160, 130, 350, 50);
			g.setFont(fnt);
			g.drawString(Game.namaPemain, 310, 160);
			
			g.drawRect(40, 210, 270, 40);
			g.setFont(fnt);
			g.drawString("Play Normal", 120, 240);
			
			g.drawRect(40, 270, 270, 40);
			g.setFont(fnt);
			g.drawString("Play Hard", 130, 300);
			
			g.drawRect(360, 210, 270, 40);
			g.setFont(fnt);
			g.drawString("Leaderboard", 440, 240);
			
			g.drawRect(360, 270, 270, 40);
			g.setFont(fnt);
			g.drawString("Keluar", 460, 300);
		} else if(Game.gameState == Game.STATE.GameOver) {
			g.setColor(Color.white);
			
			g.drawRect(160, 40, 350, 60);
			g.setFont(fnt);
			g.drawString("Game Over!", 280, 75);
			
			g.drawRect(-1, -1, 120, 40);
			g.setFont(fnt);
			g.drawString("Menu Awal", 8, 25);
			
			g.drawRect(160, 130, 350, 50);
			g.setFont(fnt);
			g.drawString(Game.namaPemain, 310, 160);
			
			g.drawRect(160, 240, 350, 50);
			g.drawString("Waktu Bertahan:", 260, 230);
			g.drawRect(160, 240, 350, 50);
			g.setFont(fnt);
			g.drawString(waktuBertahan, 310, 270);
		} else if(Game.gameState == Game.STATE.Leaderboard) {
			g.setColor(Color.white);
			
			g.drawRect(160, 40, 350, 60);
			g.setFont(fnt);
			g.drawString("Leaderboard", 270, 75);
			
			g.drawRect(-1, -1, 120, 40);
			g.setFont(fnt);
			g.drawString("Menu Awal", 8, 25);
			
			g.drawRect(40, 120, 270, 40);
			g.setFont(fnt);
			g.drawString("Normal", 140, 150);
			for(int i=0;i<5;i++) {
				String nama = leaderboard[i].namaPemain;
				if(nama == null)
					nama = "None";
				g.drawRect(40, 160+30*i, 190, 30);
				g.setFont(fnt);
				g.drawString(nama, 130-5*nama.length(), 183+30*i);
				
				long waktu = leaderboard[i].waktu;
				String waktuString = "None";
				if(waktu != -1) {
					long menit = waktu/60;
					long detik = waktu%60;
					waktuString = String.format("%02d:%02d", menit, detik);
				}
				g.drawRect(230, 160+30*i, 80, 30);
				g.setFont(fnt);
				g.drawString(waktuString, 245, 183+30*i);
			}
			
			g.drawRect(360, 120, 270, 40);
			g.setFont(fnt);
			g.drawString("Hard", 470, 150);
			for(int i=5;i<10;i++) {
				String nama = leaderboard[i].namaPemain;
				if(nama == null)
					nama = "None";
				g.drawRect(360, 160+30*(i-5), 190, 30);
				g.setFont(fnt);
				g.drawString(nama, 450-5*nama.length(), 183+30*(i-5));
				
				long waktu = leaderboard[i].waktu;
				String waktuString = "None";
				if(waktu != -1) {
					long menit = waktu/60;
					long detik = waktu%60;
					waktuString = String.format("%02d:%02d", menit, detik);
				}
				g.drawRect(550, 160+30*(i-5), 80, 30);
				g.setFont(fnt);
				g.drawString(waktuString, 565, 183+30*(i-5));
			}
		}
	}
	
	private boolean cekNama(String nama) {
		if(nama.length() < 1) return false;
		else if(nama.length() > 10) return false;
		else {
			for(int i=0;i<nama.length();i++)
				if(!Character.isLetterOrDigit(nama.charAt(i))) return false;
			return true;
		}
	}
	
	public static class HasilPemain {
		
		public String namaPemain;
		public long waktu;
		
		public HasilPemain(String namaPemain, long waktu) {
			this.namaPemain = namaPemain;
			this.waktu = waktu;
		}
		
	}
	
	private void clearLeaderboard() {
		for(int i=0;i<5;i++)
			leaderboard[i] = new HasilPemain(null, -1);
		for(int i=5;i<10;i++)
			leaderboard[i] = new HasilPemain(null, -1);
	}
	
}
