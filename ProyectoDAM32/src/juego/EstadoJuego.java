package juego;

import java.io.Serializable;

public enum EstadoJuego implements Serializable {
	GENERANDO(0, "Generando"),
	VISTA_MUNDO(1, "Vista Mundo"),
	PRECARGA(2, "Cargando");
	
	private int estado;
	private String nombre;
	
	private EstadoJuego(int estado, String nombre) {
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
