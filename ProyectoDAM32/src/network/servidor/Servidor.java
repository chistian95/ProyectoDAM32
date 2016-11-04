package network.servidor;

import juego.Juego;

public class Servidor {
	private Juego juego;
	
	public Servidor(Juego juego) {
		this.juego = juego;
	}

	public Juego getJuego() {
		return juego;
	}
}
