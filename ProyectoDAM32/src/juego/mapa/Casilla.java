package juego.mapa;

public class Casilla {
	private TipoCasilla tipo;
	
	public Casilla(TipoCasilla tipo) {
		this.tipo = tipo;
	}
	
	public TipoCasilla getTipo() {
		return tipo;
	}
}
