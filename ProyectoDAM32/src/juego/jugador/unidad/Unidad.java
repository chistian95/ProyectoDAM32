package juego.jugador.unidad;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import juego.jugador.EstadoJugador;
import juego.jugador.Jugador;
import juego.jugador.JugadorHumano;
import juego.mapa.Casilla;
import pantalla.pintar.Pintable;

public class Unidad implements Pintable {
	private Jugador jugador;
	private TipoUnidad tipo;
	private int vida;
	private int movimientos;
	private int x;
	private int y;
	
	public Unidad(Jugador jugador, TipoUnidad tipo, int x, int y) {
		this.jugador = jugador;
		this.tipo = tipo;
		vida = 100;
		movimientos = tipo.getMovimiento();
		setPos(x, y);
	}
	
	public void setPos(int x, int y) {
		Casilla[][] casillas = jugador.getJuego().getGenerador().getPaso2().getCasillas();
		if(x < 0) {
			x = casillas.length;
		}
		if(x >= casillas.length) {
			x = 0;
		}
		if(y < 0) {
			y = casillas[0].length;
		}
		if(y >= casillas[0].length) {
			y = 0;
		}
		
		this.x = x;
		this.y = y;
	}
	
	public void reiniciarMovimientos() {
		movimientos = tipo.getMovimiento();
	}
	
	@Override
	public void pintar(Graphics2D g, int x, int y, int tamX, int tamY) {
		int banderaX = (int) (x + (tamX * 0.4));
		int banderaY = (int) (y - tamY * 0.2);
		int banderaTamX = (int) (tamX - 2 * (tamX * 0.4));
		int banderaTamY = (int) (tamY - 2 * (tamY * 0.4));
		
		g.drawImage(jugador.getNacion().getTextura(), banderaX, banderaY, banderaTamX, banderaTamY, null);
		
		int vidaX = (int) (x + (tamX * 0.2));
		int vidaY = (int) (y + (tamY * 0.05));
		int vidaTamX = (int) (tamX - (tamX * 0.4));
		int vidaTamXAct = (int) (vidaTamX * (vida / 100.0));
		int vidaTamY = (int) (tamY * 0.05);
		
		int canalRojo = (int) (255 - 127 * (vida / 100.0));
		int canalVerde = (int) (127 + 127 * (vida / 100.0));
		Color colorVida = new Color(canalRojo, canalVerde, 0);
		
		g.setColor(Color.GRAY);
		g.fillRect(vidaX, vidaY, vidaTamX, vidaTamY);
		g.setColor(colorVida);
		g.fillRect(vidaX, vidaY, vidaTamXAct, vidaTamY);
		g.setColor(Color.GREEN.darker());
		g.drawRect(vidaX, vidaY, vidaTamX, vidaTamY);
		
		g.drawImage(tipo.getTextura(), x, y, tamX, tamY, null);
		
		if(jugador instanceof JugadorHumano) {
			JugadorHumano jh = (JugadorHumano) jugador;
			if(jh.getEstadoJugador().equals(EstadoJugador.MOVER_UNIDAD) && jh.getUnidadSel() != null && jh.getUnidadSel().equals(this)) {
				int alfa = (int) (getJugador().getJuego().getPantalla().getNumFrame() % 64);
				alfa = (int) (Math.abs(alfa * 4 - 127) / 1.5);
				alfa = alfa > 255 ? 255 : alfa;
				
				g.setColor(new Color(255, 255, 255, alfa));
				g.fillRect(x, y, tamX, tamY);
				g.setColor(new Color(0, 0, 0, alfa));
				g.setStroke(new BasicStroke(1));
				g.drawRect(x, y, tamX, tamY);
			}
		}
	}

	public String getNombre() {
		return tipo.getNombre();
	}

	public Jugador getJugador() {
		return jugador;
	}

	public TipoUnidad getTipo() {
		return tipo;
	}

	public int getVida() {
		return vida;
	}
	
	public int getMovimientos() {
		return movimientos;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
