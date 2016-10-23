package juego.jugador;

public enum EstadoJugador {
	ELEGIR_NACION(0, "Elegir Nacion");
	
	private int estado;
	private String nombre;
	
	private EstadoJugador(int estado, String nombre) {
		this.estado = estado;
		this.nombre = nombre;
	}
	
	public int getEstado() {
		return estado;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
}
