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
		boolean salir = false;
		do {
			int x = (int) (Math.random()*casillas.length);
			int y = (int) (Math.random()*casillas[0].length);
			
			if(casillas[x][y].getTipo().isMoldeable()) {
				Ciudad c = new Ciudad(this, x, y);
				c.setCapital(true);
				ciudades.add(c);
				
				int uniX = x - 1;
				int uniY = y - 1;
				uniX = uniX < 0 ? 0 : uniX;
				uniY = uniY < 0 ? 0 : uniY;
				
				for(int dY = 0; dY < 3 && !salir; dY++) {
					int uY = dY + uniY;
					uY = uY >= casillas[0].length ? casillas[0].length - uY : uY;
					
					for(int dX = 0; dX < 3 && !salir; dX++) {
						int uX = dX + uniX;
						uX = uX >= casillas.length ? casillas.length - uX : uX;
						
						if(uX == x && uY == y) {
							continue;
						}
						
						Casilla cas = casillas[uX][uY];
						if(!cas.getTipo().isLiquido() && cas.getTipo().isMoldeable()) {
							Unidad u = new Unidad(this, TipoUnidad.GUERRERO, uX, uY);
							unidades.add(u);
							salir = true;
							break;
						}
					}
				}
				
				if(!salir) {
					Unidad u = new Unidad(this, TipoUnidad.GUERRERO, x, y);
					unidades.add(u);
					salir = true;
				}				
			}
		} while(!salir);
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
