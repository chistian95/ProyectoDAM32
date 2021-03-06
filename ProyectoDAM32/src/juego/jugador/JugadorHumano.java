package juego.jugador;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import juego.Juego;
import juego.Nacion;
import juego.jugador.ciudad.Ciudad;
import juego.jugador.unidad.Unidad;
import juego.mapa.Casilla;
import pantalla.Camara;
import pantalla.Pantalla;
import pantalla.Ventanas;
import pantalla.pintar.NacionPintable;

public class JugadorHumano extends Jugador implements KeyListener, MouseListener {
	private Juego juego;
	private List<NacionPintable> listaNaciones;
	private Unidad unidadSel;
	private EstadoJugador estadoJugador;
	private boolean clickDerecho;
	
	public JugadorHumano(Juego juego) {
		super(juego, null);
		this.juego = juego;
		this.unidadSel = null;
		estadoJugador = EstadoJugador.ELEGIR_NACION;
		clickDerecho = false;
		
		listaNaciones = new ArrayList<NacionPintable>();
		for(Nacion nacion : Nacion.values()) {
			listaNaciones.add(new NacionPintable(juego.getPantalla(), nacion));
		}
		
		juego.getPantalla().addKeyListener(this);
		juego.getPantalla().addMouseListener(this);
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
					un.reiniciarMovimientos();
					juego.getRender().getCamara().setPos(un.getX(), un.getY());
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
	
	public boolean isClickDerecho() {
		return clickDerecho;
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

	@Override
	public void mouseClicked(MouseEvent e) {
		Pantalla pantalla = juego.getPantalla();
		Camara camara = juego.getRender().getCamara();
		double x = camara.getX();
		double y = camara.getY();
		double zoom = camara.getZoom();
		
		Point raton = MouseInfo.getPointerInfo().getLocation();
		Point pantallaPos = pantalla.getLocationOnScreen();
		
		double ratonX = raton.getX() - pantallaPos.getX();
		double ratonY = raton.getY() - pantallaPos.getY();
		ratonX = ratonX < 0 ? 0 : ratonX;
		ratonX = ratonX > pantalla.WIDTH ? juego.getPantalla().WIDTH : ratonX;
		ratonY = ratonY < 0 ? 0 : ratonY;
		ratonY = ratonY > pantalla.HEIGHT ? juego.getPantalla().HEIGHT : ratonY;		
		
		int xIni = (int) (x-zoom/2);
		int yIni = (int) (y-zoom/2);
		double xTam = pantalla.WIDTH / zoom;
		double yTam = pantalla.HEIGHT / zoom;
		
		int casillaX = (int) (xIni + ratonX / xTam);
		int casillaY = (int) (yIni + ratonY / yTam);
		casillaX = casillaX >= juego.getGenerador().getPaso2().getCasillas().length ? 0 : casillaX;
		casillaX = casillaX < 0 ? juego.getGenerador().getPaso2().getCasillas().length - 1 : casillaX;
		casillaY = casillaY >= juego.getGenerador().getPaso2().getCasillas()[0].length ? 0 : casillaY;
		casillaY = casillaY < 0 ? juego.getGenerador().getPaso2().getCasillas()[0].length - 1 : casillaY;
		
		Point puntoCasilla = new Point(casillaX, casillaY);		
		
		if(e.getButton() == MouseEvent.BUTTON3 && estadoJugador.equals(EstadoJugador.MOVER_UNIDAD)) {
			int dist = unidadSel.calcularDistancia(puntoCasilla);
			System.out.println("Distancia: "+dist);
			
			if(unidadSel.getJugador().equals(this)) {				
				Casilla casilla = juego.getGenerador().getPaso2().getCasillas()[casillaX][casillaY];
				
				if(unidadSel.puedeMoverse(casilla)) {
					unidadSel.setPos(casillaX, casillaY);
					unidadSel.setMovimientos(unidadSel.getMovimientos() - dist);
				}
			}
		}
		
		if(e.getButton() == MouseEvent.BUTTON1) {
			for(Jugador jg : juego.getJugadores()) {
				for(Unidad un : jg.getUnidades()) {
					if(un.getX() == casillaX && un.getY() == casillaY) {
						if(juego.getJugador().getUnidadSel().equals(un)) {
							for(Ciudad ci : jg.getCiudades()) {
								if(ci.getX() == casillaX && ci.getY() == casillaY) {
									System.out.println("Click ciudad "+ci.getNombre());
									return;
								}
							}
						}
						System.out.println("Click unidad "+un.getNombre());
						juego.getJugador().setUnidadSel(un);					
						return;
					}
				}
				for(Ciudad ci : jg.getCiudades()) {
					if(ci.getX() == casillaX && ci.getY() == casillaY) {
						System.out.println("Click ciudad "+ci.getNombre());
						return;
					}
				}
			}
			Casilla[][] casillas = juego.getGenerador().getPaso2().getCasillas();
			Casilla cas = casillas[casillaX][casillaY];
			System.out.println("Click casilla: "+cas.getTipo());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
