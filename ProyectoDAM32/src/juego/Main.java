package juego;

import juego.mapa.Generador;
import pantalla.Pantalla;

public class Main {

	public static void main(String[] args) {
		Generador gen = new Generador();
		new Pantalla(gen);
	}

}
