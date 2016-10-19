package juego;

import java.util.Scanner;

import juego.mapa.Generador;
import pantalla.Pantalla;

public class Main {
	private static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		Generador gen = new Generador();
		new Pantalla(gen);
		
		while(true) {
			int fase = teclado.nextInt();
			System.out.println("Cambiar a fase "+fase);
			gen.setFase(fase);
		}
	}

}
