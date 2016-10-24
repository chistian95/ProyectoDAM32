package juego.jugador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import juego.Juego;
import pantalla.Camara;

public class AnimacionNacion extends Thread {
	private Juego juego;
	private Timer timer;
	
	public AnimacionNacion(Juego juego) {
		this.juego = juego;
		start();
	}
	
	@Override
	public void run() {
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				animar();   
			}
		};
		timer = new Timer(10, listener);
		timer.setRepeats(true);
		timer.start();
	}
	
	private void animar() {
		Camara camara = juego.getRender().getCamara();
		camara.setZoom(camara.getZoom()-2);
		if(timer != null && camara.getZoom() <= 15) {
			timer.stop();
		}
	}
}
