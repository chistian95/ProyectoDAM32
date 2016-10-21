package juego.mapa;

import juego.EstadoJuego;
import juego.mapa.raster.Paso1;
import juego.mapa.raster.Paso2;
import juego.mapa.raster.Seed;
import pantalla.Pantalla;

public class Generador extends Thread {	
	private Seed seed;
	private Paso1 paso1;
	private Paso2 paso2;
	private Pantalla pantalla;
	private int fase;
	
	public Generador(Pantalla pantalla) {
		this.pantalla = pantalla;
		pantalla.setEstadoJuego(EstadoJuego.GENERANDO);
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
		} catch(Exception e) {
			e.printStackTrace();
		}

		pantalla.setEstadoJuego(EstadoJuego.VISTA_MUNDO);		
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
