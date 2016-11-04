package network.servidor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.Timer;

public class ComsCliente extends Thread {
	private Servidor servidor;
	private Socket cliente;
	private ObjectOutputStream oos;
	
	public ComsCliente(Servidor servidor, Socket cliente) {
		System.out.println("Cliente recibido!");
		this.servidor = servidor;
		this.cliente = cliente;
		start();
	}
	
	@Override
	public void run() {
		try {
			oos = new ObjectOutputStream(cliente.getOutputStream());
			ActionListener listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						System.out.println("Enviar estado del juego");
						oos.writeObject(servidor.getJuego());
					} catch (IOException e1) {
						System.out.println("Error al enviar el objeto: "+e1.getMessage());
					}  
				}
			};
			Timer timer = new Timer(100, listener);
			timer.setRepeats(true);
			timer.start();
			while(true) {
				
			}
		} catch (IOException e) {
			System.out.println("Error con el servidor: "+e.getMessage());
		}
	}
} 
