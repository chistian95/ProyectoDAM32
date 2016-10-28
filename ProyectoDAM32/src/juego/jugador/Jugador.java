package juego.jugador;

import java.util.ArrayList;
import java.util.List;

import juego.Juego;
import juego.Nacion;
import juego.jugador.ciudad.Ciudad;
import juego.jugador.unidad.TipoUnidad;
import juego.jugador.unidad.Unidad;
import juego.mapa.Casilla;

public class Jugador {
	private Nacion nacion;
	private Juego juego;
	private List<Ciudad> ciudades;
	private List<Unidad> unidades;
	private boolean IA;
	
	public Jugador(Juego juego, Nacion nacion) {
		this.juego = juego;
		this.nacion = nacion;
		ciudades = new ArrayList<Ciudad>();
		unidades = new ArrayList<Unidad>();
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
				
				Unidad u = new Unidad(this, TipoUnidad.GUERRERO, x, y);
				unidades.add(u);
				break;
			}
		}
	}
	
	public void hacerTurno() {
		System.out.println("Turno IA");
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
	
	public List<Ciudad> getCiudades() {
		return ciudades;
	}
	
	public List<Unidad> getUnidades() {
		return unidades;
	}
}
