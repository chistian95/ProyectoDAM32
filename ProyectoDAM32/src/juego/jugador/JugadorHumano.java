package juego.jugador;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import juego.Juego;
import juego.Nacion;
import juego.jugador.unidad.Unidad;
import pantalla.Ventanas;
import pantalla.pintar.NacionPintable;

public class JugadorHumano extends Jugador implements KeyListener {
	private Juego juego;
	private List<NacionPintable> listaNaciones;
	private Unidad unidadSel;
	private EstadoJugador estadoJugador;
	
	public JugadorHumano(Juego juego) {
		super(juego, null);
		this.juego = juego;
		this.unidadSel = null;
		estadoJugador = EstadoJugador.ELEGIR_NACION;
		
		listaNaciones = new ArrayList<NacionPintable>();
		for(Nacion nacion : Nacion.values()) {
			listaNaciones.add(new NacionPintable(juego.getPantalla(), nacion));
		}
		
		juego.getPantalla().addKeyListener(this);
	}
	
	public void pintar(Graphics2D g) {
		switch(estadoJugador) {
		case ELEGIR_NACION:
			Ventanas.pintarVentanaPrincipal(g, juego.getPantalla(), listaNaciones);
			break;
		case MOVER_UNIDAD:
			if(unidadSel != null) {
				Ventanas.pintarUnidadGUI(g, juego.getPantalla(), unidadSel);
			}
			break;
		case ESPERA:
		case ANIMACION_NACION:
			break;		
		}
	}
	
	@Override
	public synchronized void hacerTurno() {		
		try {
			synchronized(this) {
				System.out.println("Turno Jugador");
				estadoJugador = EstadoJugador.MOVER_UNIDAD;
				for(Unidad un : getUnidades()) {
					unidadSel = un;
					juego.getRender().getCamara().setPos(un.getX(), un.getY());
					juego.getRender().getCamara().setZoom(4);
					this.wait();
				}				
				estadoJugador = EstadoJugador.ESPERA;
				System.out.println("Fin Turno Jugador");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
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
	
	public Unidad getUnidadSel() {
		return unidadSel;
	}
	
	public void setUnidadSel(Unidad unidadSel) {
		this.unidadSel = unidadSel;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(estadoJugador.equals(EstadoJugador.MOVER_UNIDAD)) {
				synchronized(this) {
					this.notify();
				}
			}			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
