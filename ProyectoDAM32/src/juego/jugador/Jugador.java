package juego.jugador;

import java.util.ArrayList;
import java.util.List;

import juego.Juego;
import juego.Nacion;
import juego.mapa.Casilla;

public class Jugador {
	private Nacion nacion;
	private Juego juego;
	private List<Ciudad> ciudades;
	private boolean IA;
	
	public Jugador(Juego juego, Nacion nacion) {
		this.juego = juego;
		this.nacion = nacion;
		ciudades = new ArrayList<Ciudad>();
		IA = false;
	}
	
	public void ponerEnMundo() {
		Casilla[][] casillas = juego.getGenerador().getPaso2().getCasillas();
		while(true) {
			int x = (int) (Math.random()*casillas.length);
			int y = (int) (Math.random()*casillas[0].length);
			
			if(casillas[x][y].getTipo().isMoldeable()) {
				Ciudad c = new Ciudad(this, x, y);
				c.setCapital(true);
				ciudades.add(c);
				break;
			}
		}
	}
	
	public Ciudad getCapital() {
		for(Ciudad c : ciudades) {
			if(c.isCapital()) {
				return c;
			}
		}
		return null;
	}
	
	public Juego getJuego() {
		return juego;
	}
	
	public Nacion getNacion() {
		return nacion;
	}
	
	public void setNacion(Nacion nacion) {
		this.nacion = nacion;
	}
	
	public boolean isIA() {
		return IA;
	}
	
	public void setIA(boolean IA) {
		this.IA = IA;
	}
}
