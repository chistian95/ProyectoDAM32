package juego.mapa;

public enum Direccion {
	NORTE(0, "Norte"),
	SUR(1, "Sur"),
	ESTE(2, "Este"),
	OESTE(3, "Oeste");
	
	private int tipo;
	private String nombre;
	
	private Direccion(int tipo, String nombre) {
		this.tipo = tipo;
		this.nombre = nombre;
	}
	
	public int getTipo() {
		return tipo;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
}
