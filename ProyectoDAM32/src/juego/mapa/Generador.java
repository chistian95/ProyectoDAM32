package juego.mapa;

import juego.EstadoJuego;
import juego.Juego;
import juego.mapa.raster.Paso1;
import juego.mapa.raster.Paso2;
import juego.mapa.raster.Seed;

public class Generador extends Thread {	
	private Seed seed;
	private Paso1 paso1;
	private Paso2 paso2;
	private Juego juego;
	private int fase;
	
	public Generador(Juego juego) {
		this.juego = juego;
		juego.setEstadoJuego(EstadoJuego.GENERANDO);
		fase = -1;
		start();
	}
	
	public void run() {
		try {			
			seed = new Seed(this);
			seed.join();
			paso1 = new Paso1(this);
			paso1.join();			
			paso2 = new Paso2(this);
			paso2.join();
			
			Casilla[][] casillas = paso2.getCasillas();
			int x = casillas.length/2;
			int y = casillas[0].length/2;
			juego.getRender().getCamara().setPos(x, y);
		} catch(Exception e) {
			e.printStackTrace();
		}
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
