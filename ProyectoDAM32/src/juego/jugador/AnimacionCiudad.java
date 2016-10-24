package juego.jugador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import pantalla.Camara;

public class AnimacionCiudad extends Thread {
	private Ciudad ciudad;
	private Timer timer;
	private double deltaX;
	private double deltaY;
	
	public AnimacionCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
		
		Camara camara = ciudad.getJugador().getJuego().getRender().getCamara();
		double startX = camara.getX();
		double startY = camara.getY();
		deltaX = (ciudad.getX() - startX) / (camara.getZoom()/2 - 15);
		deltaY = (ciudad.getY() - startY) / (camara.getZoom()/2 - 15);
		
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
		Camara camara = ciudad.getJugador().getJuego().getRender().getCamara();
		
		double x = camara.getX();
		int xCam = (int) Math.floor(x);
		if(xCam != ciudad.getX()) {
			x += deltaX;
		}
		double y = camara.getY();
		int yCam = (int) Math.floor(y);
		if(yCam != ciudad.getY()) {
			y += deltaY;
		}
		camara.setPos(x, y);
		
		if(camara.getZoom() > 15) {
			camara.setZoom(camara.getZoom()-2);
		}
		
		if(timer != null && camara.getZoom() <= 15 && xCam == ciudad.getX() && (int) yCam == ciudad.getY()) {
			timer.stop();
		}
	}
}
