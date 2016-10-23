package juego.jugador;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import juego.Juego;
import pantalla.Ventanas;

public class Jugador {
	private Juego juego;
	private EstadoJugador estadoJugador;
	
	public Jugador(Juego juego) {
		this.juego = juego;
		estadoJugador = EstadoJugador.ELEGIR_NACION;
	}
	
	public void pintar(Graphics2D g) {
		List<String> test = new ArrayList<String>();
		test.add("Prueba1");
		test.add("Prueba2");
		test.add("Prueba3");
		test.add("Prueba4");
		
		Ventanas.pintarVentanaPrincipal(g, juego.getPantalla(), test);
	}
	
	public Juego getJuego() {
		return juego;
	}
	
	public EstadoJugador getEstadoJugador() {
		return estadoJugador;
	}
	
	public void setEstadoJugador(EstadoJugador estadoJugador) {
		this.estadoJugador = estadoJugador;
	}
}
