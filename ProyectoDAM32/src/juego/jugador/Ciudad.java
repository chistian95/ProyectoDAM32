package juego.jugador;

import pantalla.pintar.CiudadPintable;

public class Ciudad {
	private Jugador jugador;
	private int x;
	private int y;
	private boolean capital;
	private CiudadPintable ciudadPintable;
	
	public Ciudad(Jugador jugador, int x, int y) {
		this.jugador = jugador;
		this.x = x;
		this.y = y;
		capital = false;
		ciudadPintable = new CiudadPintable(this);
	}
	
	public void centrarCamara() {
		new AnimacionCiudad(this);
	}
	
	public Jugador getJugador() {
		return jugador;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isCapital() {
		return capital;
	}
	
	public void setCapital(boolean capital) {
		this.capital = capital;
	}
	
	public CiudadPintable getCiudadPintable() {
		return ciudadPintable;
	}
}
