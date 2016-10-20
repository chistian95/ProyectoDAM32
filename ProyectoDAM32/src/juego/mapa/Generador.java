package juego.mapa;

import juego.mapa.raster.Paso1;
import juego.mapa.raster.Paso2;
import juego.mapa.raster.Seed;

public class Generador extends Thread {	
	private Seed seed;
	private Paso1 paso1;
	private Paso2 paso2;
	private int fase;
	
	public Generador() {
		fase = -1;
		start();
	}
	
	public void run() {
		long tStart = System.currentTimeMillis();
		try {
			seed = new Seed(this);
			seed.join();
			paso1 = new Paso1(this);
			paso1.join();
			paso2 = new Paso2(this);
		} catch(Exception e) {
			e.printStackTrace();
		}
		long tFin = System.currentTimeMillis();
		long tDelta = tFin - tStart;
		double tiempo = tDelta / 1000.0;
		System.out.println("Tiempo: "+tiempo+" s");
	}
	
	public Seed getSeed() {
		return seed;
	}
	
	public Paso1 getPaso1() {
		return paso1;
	}
	
	public Paso2 getPaso2() {
		return paso2;
	}
	
	public int getFase() {
		return fase;
	}
	
	public void setFase(int fase) {
		this.fase = fase;
	}
}
