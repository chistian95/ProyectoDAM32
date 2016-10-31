package pantalla;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import juego.Juego;
import juego.jugador.EstadoJugador;
import juego.jugador.Jugador;
import juego.jugador.ciudad.Ciudad;
import juego.jugador.unidad.Unidad;
import juego.mapa.Casilla;
import juego.mapa.TipoCasilla;

public class Camara implements KeyListener, MouseWheelListener {
	public static final int ZOOM_TEXTURAS = 50;
	
	private static final double DELTA_ZOOM = 5;
	private static final double VEL_CAMARA = 0.05;
	
	private Juego juego;
	private double x;
	private double y;
	private double zoom;
	
	private boolean mover_izq;
	private boolean mover_drc;
	private boolean mover_arriba;
	private boolean mover_abajo;
	
	public Camara(Juego juego) {
		this.juego = juego;		
		this.zoom = 240.0;		
		this.x = 0;
		this.y = 0;
		
		juego.getPantalla().addKeyListener(this);
		juego.getPantalla().addMouseWheelListener(this);
		
		precargarTexturas();
	}
	
	public void pintar(Graphics2D g) {				
		pintarMundo(g);		
		pintarGUI(g);		
		pintarEntidades(g);
		
		moverCamara();
	}
	
	private void pintarMundo(Graphics2D g) {
		Pantalla pantalla = juego.getPantalla();
		Casilla[][] casillas = juego.getGenerador().getPaso2().getCasillas();	
		
		int xIni = (int) (x-zoom/2);
		int yIni = (int) (y-zoom/2);		
		double xTam = pantalla.WIDTH / zoom;
		double yTam = pantalla.HEIGHT / zoom;
		
		for(int y=0; y<zoom; y++) {
			for(int x=0; x<zoom; x++) {
				int xPintar = x+xIni;
				int yPintar = y+yIni;
				
				if(xPintar >= casillas.length) {
					xPintar = xPintar - casillas.length;
				}
				if(xPintar < 0) {
					xPintar = casillas.length + xPintar;
				}
				
				if(yPintar >= casillas[0].length) {
					this.y = casillas[0].length - (int) (zoom/2);
					pintar(g);
					return;
				}
				if(yPintar < 0) {
					this.y = (int) (zoom/2);
					pintar(g);
					return;
				}				
				
				Casilla cas = casillas[xPintar][yPintar];
				if(cas == null) {
					g.setColor(TipoCasilla.OCEANO.getColor());
					g.fillRect((int) Math.floor(x*xTam), (int) Math.floor(y*yTam), (int) Math.ceil(xTam), (int) Math.ceil(yTam));
				} else if(zoom > ZOOM_TEXTURAS) {
					g.setColor(cas.getTipo().getColor());
					g.fillRect((int) Math.floor(x*xTam), (int) Math.floor(y*yTam), (int) Math.ceil(xTam), (int) Math.ceil(yTam));
				} else {					
					g.drawImage(cas.getTipo().getTextura(), (int) Math.floor(x*xTam), (int) Math.floor(y*yTam), (int) Math.ceil(xTam), (int) Math.ceil(yTam), null);
									
					int dZoom = (int) (zoom-ZOOM_TEXTURAS/2)*4;
					int alfa = (int) (255*(dZoom/100.0));
					if(alfa > 255) {
						alfa = 255;
					}
					if(alfa < 0) {
						alfa = 0;
					}
					
					Color color = cas.getTipo().getColor();
					Color colorTrans = new Color(color.getRed(), color.getGreen(), color.getBlue(), alfa);
					g.setColor(colorTrans);
					g.fillRect((int) Math.floor(x*xTam), (int) Math.floor(y*yTam), (int) Math.ceil(xTam), (int) Math.ceil(yTam));
				}
			}
		}
	}
	
	private void pintarEntidades(Graphics2D g) {
		Pantalla pantalla = juego.getPantalla();
		Casilla[][] casillas = juego.getGenerador().getPaso2().getCasillas();	
		
		int xIni = (int) (x-zoom/2);
		int yIni = (int) (y-zoom/2);		
		double xTam = pantalla.WIDTH / zoom;
		double yTam = pantalla.HEIGHT / zoom;
		
		for(int y=0; y<zoom; y++) {
			for(int x=0; x<zoom; x++) {
				int xPintar = x+xIni;
				int yPintar = y+yIni;
				
				if(xPintar >= casillas.length) {
					xPintar = xPintar - casillas.length;
				}
				if(xPintar < 0) {
					xPintar = casillas.length + xPintar;
				}
				
				if(yPintar >= casillas[0].length) {
					this.y = casillas[0].length - (int) (zoom/2);
					pintar(g);
					return;
				}
				if(yPintar < 0) {
					this.y = (int) (zoom/2);
					pintar(g);
					return;
				}
				
				if(juego.getJugadores() != null && juego.getJugadores().size() > 0) {
					for(Jugador jg : juego.getJugadores()) {
						if(jg.getCiudades() != null && jg.getCiudades().size() > 0) {
							for(Ciudad c : jg.getCiudades()) {
								if(c.getX() != xPintar || c.getY() != yPintar) {
									continue;
								}
								c.pintar(g, (int) Math.floor(x*xTam), (int) Math.floor(y*yTam), (int) Math.ceil(xTam), (int) Math.ceil(yTam));
							}
						}
						
						if(jg.getUnidades() != null && jg.getUnidades().size() > 0) {
							for(Unidad u : jg.getUnidades()) {
								if(u.getX() != xPintar || u.getY() != yPintar) {
									continue;
								}
								u.pintar(g, (int) Math.floor(x*xTam), (int) Math.floor(y*yTam), (int) Math.ceil(xTam), (int) Math.ceil(yTam));
							}
						}
					}
				}
			}
		}
	}
	
	private void pintarGUI(Graphics2D g) {
		if(juego.getJugador() == null) {
			return;
		}
			
		Pantalla pantalla = juego.getPantalla();
		Casilla[][] casillas = juego.getGenerador().getPaso2().getCasillas();
		
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
		casillaX = casillaX >= casillas.length ? casillas.length - 1 : casillaX;
		casillaY = casillaY >= casillas[0].length ? casillas[0].length - 1 : casillaY;
		
		int casillaRelX = (int) (ratonX / xTam);
		int casillaRelY = (int) (ratonY / yTam);
		
		g.setColor(new Color(255, 255, 255, 127));
		if(juego.getJugador().getEstadoJugador().equals(EstadoJugador.MOVER_UNIDAD) && juego.getJugador().getUnidadSel().getJugador().equals(juego.getJugador())) {
			Casilla cas = casillas[casillaX][casillaY];
			
			g.setColor(new Color(127, 0, 0, 127));
			if(juego.getJugador().getUnidadSel().puedeMoverse(cas)) {
				g.setColor(new Color(0, 127, 0, 127));				
			}
		}
		
		g.fillRect((int) (casillaRelX * xTam), (int) (casillaRelY * yTam), (int) xTam, (int) yTam); 
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(1));
		g.drawRect((int) (casillaRelX * xTam), (int) (casillaRelY * yTam), (int) xTam, (int) yTam);
		
		g.setColor(Color.BLACK);
		g.drawString(ratonX+", "+ratonY, 100, 100);
		g.drawString(casillaX+", "+casillaY, 100, 120);
		g.drawString(casillaRelX+", "+casillaRelY, 100, 140);
	}
	
	private void moverCamara() {
		double dMov = VEL_CAMARA*zoom;
		if(dMov < 0) {
			dMov = 1;
		}
		
		if(mover_izq) {
			x -= dMov;
			if(x < 0) {
				x += juego.getGenerador().getPaso2().getCasillas().length;
			}
		}
		if(mover_drc) {
			x += dMov;
			if(x >= juego.getGenerador().getPaso2().getCasillas().length) {
				x -= juego.getGenerador().getPaso2().getCasillas().length;
			}
		}
		if(mover_arriba) {
			y -= dMov;
			if(y-zoom/2 < 0) {
				y += dMov;
			}
		}
		if(mover_abajo) {
			y += dMov;
			if(y+zoom/2 >= juego.getGenerador().getPaso2().getCasillas()[0].length) {
				y -= dMov;
			}
		}
	}
	
	public void setPos(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A) {
			mover_izq = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			mover_drc = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			mover_arriba = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			mover_abajo = true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A) {
			mover_izq = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			mover_drc = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			mover_arriba = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			mover_abajo = false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(juego.getGenerador().getPaso2() == null) {
			return;
		}
		if(e.getWheelRotation() < 0) {
			zoom -= DELTA_ZOOM;
			if(zoom < 1) {
				zoom = 1;
			}
		} else {
			zoom += DELTA_ZOOM;
			if(zoom >= juego.getGenerador().getPaso2().getCasillas().length) {
				zoom = juego.getGenerador().getPaso2().getCasillas().length - 1;
			}
		}
	}
	
	public void precargarTexturas() {
		Thread carga = new Thread() {
			public void run() {
				for(TipoCasilla tipo : TipoCasilla.values()) {
					tipo.getTextura();
				}
			}
		};
		
		carga.start();
	}
	
	public double getZoom() {
		return zoom;
	}

	public void setZoom(double zoom) {
		if(zoom < 1) {
			zoom = 1;
		}
		if(zoom >= juego.getGenerador().getPaso2().getCasillas().length) {
			zoom = juego.getGenerador().getPaso2().getCasillas().length - 1;
		}
		this.zoom = zoom;
	}
}
