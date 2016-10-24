package juego.jugador;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import juego.Juego;
import juego.Nacion;
import pantalla.Ventanas;
import pantalla.pintar.NacionPintable;

public class JugadorHumano extends Jugador {
	private Juego juego;
	private List<NacionPintable> listaNaciones;
	private EstadoJugador estadoJugador;
	
	public JugadorHumano(Juego juego) {
		super(juego, null);
		this.juego = juego;
		estadoJugador = EstadoJugador.ELEGIR_NACION;
		
		listaNaciones = new ArrayList<NacionPintable>();
		for(Nacion nacion : Nacion.values()) {
			listaNaciones.add(new NacionPintable(juego.getPantalla(), nacion));
		}
	}
	
	public void pintar(Graphics2D g) {		
		Ventanas.pintarVentanaPrincipal(g, juego.getPantalla(), listaNaciones);
	}
	
	public void comenzarPartida(Nacion nacion) {
		this.setNacion(nacion);
		for(NacionPintable np : listaNaciones) {
			juego.getPantalla().removeMouseListener(np);
			juego.getPantalla().removeMouseMotionListener(np);
		}
		listaNaciones.clear();
		
		estadoJugador = EstadoJugador.ANIMACION_NACION;
		
		juego.crearNaciones();
	}
	
	public Juego getJuego() {
		return juego;
	}
	
	public List<NacionPintable> getListaNaciones() {
		return listaNaciones;
	}
	
	public EstadoJugador getEstadoJugador() {
		return estadoJugador;
	}
	
	public void setEstadoJugador(EstadoJugador estadoJugador) {
		this.estadoJugador = estadoJugador;
	}
}
