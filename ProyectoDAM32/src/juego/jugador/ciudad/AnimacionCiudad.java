package juego.jugador.ciudad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import pantalla.Camara;

/**
 * Va moviendo la camara y el zoom de esta hacia la ciudad indicada
 * 
 * @author dam32-Corral *
 */
public class AnimacionCiudad extends Thread {
	private static final int ZOOM_ANIM = 5;

	private Ciudad ciudad;
	private Timer timer;
	private double deltaX;
	private double deltaY;
	
	/**
	 * Inicia en un hilo a parte la animación hacia la ciudad
	 * @param ciudad Ciudad a la que se hace la animación
	 */
	public AnimacionCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
		
		Camara camara = ciudad.getJugador().getJuego().getRender().getCamara();
		double startX = camara.getX();
		double startY = camara.getY();
		deltaX = (ciudad.getX() - startX) / (camara.getZoom()/2 - ZOOM_ANIM);
		deltaY = (ciudad.getY() - startY) / (camara.getZoom()/2 - ZOOM_ANIM);
		
		start();
	}
	
	/**
	 * Cada 10 milisegundos llama a la función animar (En un hilo a parte)
	 * @see AnimacionCiudad#animar()
	 */
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
		
		if(camara.getZoom() > ZOOM_ANIM) {
			camara.setZoom(camara.getZoom()-2);
		}
		
		if(timer != null && camara.getZoom() <= ZOOM_ANIM && xCam == ciudad.getX() && (int) yCam == ciudad.getY()) {
			timer.stop();
		}
	}
}
