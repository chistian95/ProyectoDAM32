package juego.jugador.unidad;

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
