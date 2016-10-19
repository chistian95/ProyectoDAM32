package juego.mapa;

public enum TipoCasilla {
	AGUA(0), 
	LLANURA(1), 
	BOSQUE(2), 
	DESIERTO(3), 
	MONTE(4);
	
	private int tipo;
	
	private TipoCasilla(int tipo) {
		this.tipo = tipo;
	}
	
	public int getTipo() {
		return tipo;
	}
}
