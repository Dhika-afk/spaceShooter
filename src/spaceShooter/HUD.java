package spaceShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.time.Duration;

public class HUD implements InterfaceDisplay {
	
	private Duration waktu;
	private long menit;
	private long detik;
	private String waktuString;
	
	public void update() {
		waktu = Duration.ofNanos(System.nanoTime() - Game.startTime);
		menit = waktu.toMinutes();
		detik = waktu.minusMinutes(menit).toSeconds();
		waktuString = String.format("%02d:%02d", menit, detik);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawString("Waktu Bertahan = " + waktuString, 10, 20);
	}
	
	public Duration getWaktu() {
		return waktu;
	}
	
}
