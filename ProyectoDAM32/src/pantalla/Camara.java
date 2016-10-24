package pantalla;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import juego.Juego;
import juego.mapa.Casilla;
import juego.mapa.TipoCasilla;

public class Camara implements KeyListener, MouseWheelListener {
	private static final int ZOOM_TEXTURAS = 50;
	private static final double DELTA_ZOOM = 5;
	private static final double VEL_CAMARA = 0.012;
	
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
					this.y= (int) (zoom/2);
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
		moverCamara();
	}
	
	private void moverCamara() {
		double dMov = VEL_CAMARA*zoom;
		if(dMov < 1) {
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
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
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

	
}
