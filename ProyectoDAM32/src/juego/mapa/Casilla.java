package juego.mapa;

public class Casilla {
	private TipoCasilla tipo;
	private int x;
	private int y;
	
	public Casilla(TipoCasilla tipo, int x, int y) {
		this.tipo = tipo;
		this.x = x;
		this.y = y;
	}
	
	public TipoCasilla getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoCasilla tipo) {
		this.tipo = tipo;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
