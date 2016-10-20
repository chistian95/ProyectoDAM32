package juego.mapa;

public class Casilla {
	private TipoCasilla tipo;
	private int x;
	private int y;
	private Casilla casLeft;
	private Casilla casRight;
	private Casilla casUp;
	private Casilla casDown;
	
	public Casilla(TipoCasilla tipo, int x, int y) {
		this.tipo = tipo;
		this.x = x;
		this.y = y;
		this.casLeft = this;
		this.casRight = this;
		this.casUp = this;
		this.casDown = this;
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
	
	public Casilla getCasLeft() {
		return casLeft;
	}
	
	public void setCasLeft(Casilla casLeft) {
		this.casLeft = casLeft;
	}
	
	public Casilla getCasRight() {
		return casRight;
	}
	
	public void setCasRight(Casilla casRight) {
		this.casRight = casRight;
	}
	
	public Casilla getCasUp() {
		return casUp;
	}
	
	public void setCasUp(Casilla casUp) {
		this.casUp = casUp;
	}
	
	public Casilla getCasDown() {
		return casDown;
	}
	
	public void setCasDown(Casilla casDown) {
		this.casDown = casDown;
	}
}
