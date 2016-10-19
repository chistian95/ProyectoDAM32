package juego.mapa;

import juego.mapa.raster.Paso1;
import juego.mapa.raster.Paso2;
import juego.mapa.raster.Seed;

public class Generador extends Thread {	
	private Seed seed;
	private Paso1 paso1;
	private Paso2 paso2;
	private int fase;
	private int faseGen;
	
	public Generador() {
		fase = -1;
		start();
	}
	
	public void run() {
		seed = new Seed(this);
		paso1 = new Paso1(this);
		paso2 = new Paso2(this);
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
	
	public int getFaseGen() {
		return faseGen;
	}
	
	public void setFaseGen(int faseGen) {
		this.faseGen = faseGen;
	}
	
	public void setFase(int fase) {
		this.fase = fase;
		if(fase == 0) {
			try {
				Thread.sleep(200);
				this.fase++;
				Thread.sleep(200);
				this.fase++;
			} catch(Exception e) {
				e.printStackTrace();
			}			
		}
	}
}
