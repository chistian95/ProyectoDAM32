package juego;

import java.util.ArrayList;
import java.util.List;

import juego.jugador.Jugador;
import juego.jugador.JugadorHumano;
import juego.mapa.Generador;
import pantalla.Pantalla;
import pantalla.Renderizador;

public class Juego extends Thread {
	private Pantalla pantalla;
	private Renderizador render;
	private Generador generador;
	private JugadorHumano jugador;
	private EstadoJuego estadoJuego;
	private List<Jugador> jugadores;
	
	public Juego() {
		start();
	}
	
	public void run() {
		estadoJuego = EstadoJuego.PRECARGA;
		pantalla = new Pantalla(this);
		generador = new Generador(this);
		render = new Renderizador(this);
		
		try {
			generador.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		estadoJuego = EstadoJuego.VISTA_MUNDO;
		jugadores = new ArrayList<Jugador>();
		jugador = new JugadorHumano(this);
		jugadores.add(jugador);
	}
	
	public void crearNaciones() {
		for(Nacion na : Nacion.values()) {
			if(!jugador.getNacion().equals(na)) {
				Jugador jg = new Jugador(this, na);
				jugadores.add(jg);
				
				jg.setIA(true);
				jg.ponerEnMundo();
			}
		}
		jugador.ponerEnMundo();
		jugador.getCapital().centrarCamara();
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
	
	public JugadorHumano getJugador() {
		return jugador;
	}
	
	public List<Jugador> getJugadores() {
		return jugadores;
	}
}
