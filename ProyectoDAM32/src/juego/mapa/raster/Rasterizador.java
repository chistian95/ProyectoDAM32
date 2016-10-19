package juego.mapa.raster;

import java.util.List;

import juego.mapa.Casilla;
import juego.mapa.Generador;

public abstract class Rasterizador {
	private List<Casilla> casillas;
	private Generador generador;
	
	public Rasterizador(Generador generador) {
		this.generador = generador;
		this.casillas = generar();
	}
	
	public Casilla buscarCasilla(List<Casilla> casillas, int x, int y) {
		for(Casilla cas : casillas) {
			if(cas.getX() == x && cas.getY() == y) {
				return cas;
			}
		}
		return null;
	}
	
	public abstract List<Casilla> generar();
	
	public List<Casilla> getCasillas() {
		return casillas;
	}
	
	public Generador getGenerador() {
		return generador;
	}
}
