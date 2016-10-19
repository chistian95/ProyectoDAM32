package pantalla;

import java.awt.Graphics2D;

import juego.mapa.Casilla;
import juego.mapa.Generador;

public class Renderizador {
	private Generador generador;
	
	public Renderizador(Pantalla pantalla) {
		generador = pantalla.getGenerador();
	}
	
	public void pintarSeed(Graphics2D g) {
		Casilla[][] seed = generador.getSeed();
		
		for(int y=0; y<seed.length; y++) {
			for(int x=0; x<seed[y].length; x++) {
				
			}
		}
	}
}
