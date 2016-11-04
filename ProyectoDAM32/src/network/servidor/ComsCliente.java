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
	private Timer timer;
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
						System.out.println("Escribir objeto");
						oos.writeUnshared(servidor.getJuego().getBundleCliente());
					} catch (IOException e1) {
						System.out.println("Error al enviar el objeto: "+e1.getMessage());
						timer.stop();
					}  
				}
			};
			timer = new Timer(2000, listener);
			timer.setRepeats(true);
			timer.start();
		} catch (IOException e) {
			System.out.println("Error con el servidor: "+e.getMessage());
		}
	}
} 
