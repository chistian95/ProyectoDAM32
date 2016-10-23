package juego;

import juego.mapa.Generador;
import pantalla.Pantalla;
import pantalla.Renderizador;

public class Juego extends Thread {
	private Pantalla pantalla;
	private Renderizador render;
	private Generador generador;
	private EstadoJuego estadoJuego;
	
	public Juego() {
		start();
	}
	
	public void run() {
		estadoJuego = EstadoJuego.PRECARGA;
		pantalla = new Pantalla(this);
		generador = new Generador(this);
		render = new Renderizador(this);
	}
	
	public EstadoJuego getEstadoJuego() {
		return estadoJuego;
	}
	
	public void setEstadoJuego(EstadoJuego estadoJuego) {
		this.estadoJuego = estadoJuego;
	}
	
	public Pantalla getPantalla() {
		return pantalla;
	}
	
	public Renderizador getRender() {
		return render;
	}
	
	public Generador getGenerador() {
		return generador;
	}
}
