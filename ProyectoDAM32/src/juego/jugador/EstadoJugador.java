package juego.jugador;

public enum EstadoJugador {
	ELEGIR_NACION(0, "Elegir Naci�n"),
	ANIMACION_NACION(1, "Animaci�n Naci�n"),
	MOVER_UNIDAD(2, "Mover Unidad"),
	ESPERA(3, "Espera");
	
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
