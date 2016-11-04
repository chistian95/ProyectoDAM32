package network.cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import juego.Juego;

public class Cliente extends Thread {
	public static void main(String[] args) {
		new Juego();
	}
	
	private Juego juego;
	private Socket cliente;
	private ObjectInputStream ois;
	
	public Cliente(Juego juego) {
		this.juego = juego;		
		try {
			cliente = new Socket("localhost", 5000);
			System.out.println("Conectado al servidor!");
			
			ois = new ObjectInputStream(cliente.getInputStream());
			start();
		} catch (IOException e) {
			System.out.println("Error al conectar al servidor: "+e.getMessage());
		}
		
	}
	
	@Override
	public void start() {
		System.out.println("Escuchando...");
		try {
			while(true) {
				Object obj = ois.readObject();
				System.out.println(obj);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Error al leer el objeto: "+e.getMessage());
		} catch (IOException e) {
			System.out.println("Error de conexion: "+e.getMessage());
		}
	}
	
	public Juego getJuego() {
		return juego;
	}
}
