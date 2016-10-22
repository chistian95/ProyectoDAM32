package pantalla;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;

import juego.mapa.Casilla;
import juego.mapa.TipoCasilla;

public class Camara {
	private static final double dZoom = 5;
	
	private Pantalla pantalla;
	private int x = -10;
	private int y;
	private double zoom;
	
	private boolean mover_izq;
	private boolean mover_drc;
	private boolean mover_arriba;
	private boolean mover_abajo;
	
	public Camara(Pantalla pantalla) {
		this.pantalla = pantalla;		
		this.zoom = 240.0;		
		this.x = 0;
		this.y = 0;
	}
	
	public void pintar(Graphics2D g) {
		Casilla[][] casillas = pantalla.getGenerador().getPaso2().getCasillas();	
		
		int xIni = (int) (x-zoom/2);
		int yIni = (int) (y-zoom/2);
		
		double xTam = pantalla.WIDTH / zoom;
		double yTam = pantalla.HEIGHT / zoom;
		
		for(int y=0; y<zoom; y++) {
			for(int x=0; x<zoom; x++) {
				if(x+xIni >= casillas.length) {
					this.x = casillas.length - (int) (zoom/2);
					pintar(g);
					return;
				}
				if(y+yIni >= casillas[0].length) {
					this.y = casillas[0].length - (int) (zoom/2);
					pintar(g);
					return;
				}				
				if(x+xIni < 0) {
					this.x = (int) (zoom/2); 
					pintar(g);
					return;
				}
				if(y+yIni < 0) {
					this.y= (int) (zoom/2);
					pintar(g);
					return;
				}
				Casilla cas = casillas[x+xIni][y+yIni];
				if(cas != null) {
					g.setColor(cas.getTipo().getColor());
				} else {
					g.setColor(TipoCasilla.OCEANO.getColor());
				}
				g.fillRect((int) Math.floor(x*xTam), (int) Math.floor(y*yTam), (int) Math.ceil(xTam), (int) Math.ceil(yTam));
			}
		}
		moverCamara();
	}
	
	private void moverCamara() {
		if(mover_izq) {
			x--;
			if(x-zoom/2 < 0) {
				x++;
			}
		}
		if(mover_drc) {
			x++;
			if(x+zoom/2 >= pantalla.getGenerador().getPaso2().getCasillas().length) {
				x--;
			}
		}
		if(mover_arriba) {
			y--;
			if(y-zoom/2 < 0) {
				y++;
			}
		}
		if(mover_abajo) {
			y++;
			if(y+zoom/2 >= pantalla.getGenerador().getPaso2().getCasillas()[0].length) {
				y--;
			}
		}
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
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
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation() < 0) {
			zoom -= dZoom;
			if(zoom < 1) {
				zoom = 1;
			}
		} else {
			zoom += dZoom;
			if(zoom >= pantalla.getGenerador().getPaso2().getCasillas().length) {
				zoom = pantalla.getGenerador().getPaso2().getCasillas().length - 1;
			}
		}
	}
}
