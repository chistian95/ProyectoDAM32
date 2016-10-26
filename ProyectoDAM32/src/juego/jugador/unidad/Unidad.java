package juego.jugador.unidad;

import java.awt.Color;
import java.awt.Graphics2D;

import juego.jugador.Jugador;
import juego.mapa.Casilla;
import pantalla.pintar.Pintable;

public class Unidad implements Pintable {
	protected Jugador jugador;
	protected String nombre;
	protected TipoUnidad tipo;
	protected int vida;
	protected int movimientos;
	protected int x;
	protected int y;
	
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
	}

	public String getNombre() {
		return nombre;
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
