package spaceShooter;

import java.util.Random;

public class Spawner {
	
	private Random rand = new Random();
	private long timerAsteroid = System.currentTimeMillis();
	private long periodeAsteroid = 1000/Game.difficulty;
	private long timerPesawat = System.currentTimeMillis();
	private long periodePesawat = 2500/Game.difficulty;
	private long timerPeluruPemain = System.currentTimeMillis();
	private long periodePeluruPemain = 500;
	private long timerPeluruMusuh = System.currentTimeMillis();
	private long periodePeluruMusuh = 1000/Game.difficulty;
	
	public void spawn() {
		if(System.currentTimeMillis() - timerAsteroid > periodeAsteroid) {
			timerAsteroid += periodeAsteroid;
			Game.handlerMusuh.addObjek(new Asteroid(Game.WIDTH, rand.nextInt(Game.HEIGHT), rand.nextInt(5)-5, rand.nextInt(3)-1));
		}
		if(System.currentTimeMillis() - timerPesawat > periodePesawat) {
			timerPesawat += periodePesawat;
			Game.handlerMusuh.addObjek(new PesawatMusuh(Game.WIDTH, rand.nextInt(Game.HEIGHT), rand.nextInt(2)-2, 0));
		}
		if(System.currentTimeMillis() - timerPeluruPemain > periodePeluruPemain) {
			timerPeluruPemain += periodePeluruPemain;
			for(int i=0;i<Game.objekPemain.size();i++) {
				AbstrakObjek objek = Game.objekPemain.get(i);
				if(objek.getId() == 1) {
					((Pemain) objek).addPeluru();
				}
			}
		}
		if(System.currentTimeMillis() - timerPeluruMusuh > periodePeluruMusuh) {
			timerPeluruMusuh += periodePeluruMusuh;
			for(int i=0;i<Game.objekMusuh.size();i++) {
				AbstrakObjek objek = Game.objekMusuh.get(i);
				if(objek.getId() == 2) {
					((PesawatMusuh) objek).addPeluru();
				}
			}
		}
	}
	
}
