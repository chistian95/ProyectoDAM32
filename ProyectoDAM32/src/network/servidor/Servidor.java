package network.servidor;

import java.io.IOException;
import java.net.ServerSocket;

import juego.Juego;

public class Servidor extends Thread {
	private Juego juego;
	private ServerSocket server;
	
	public Servidor(Juego juego) {
		this.juego = juego;
		start();		
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Iniciando servidor en puerto 5000");
			server = new ServerSocket(5000);
			while(true) {
				System.out.println("Esperando un cliente...");
				new ComsCliente(this, server.accept());
			}
		} catch (IOException e) {
			System.out.println("Error al iniciar el servidor: "+e.getMessage());
		}
	}

	public Juego getJuego() {
		return juego;
	}
}
