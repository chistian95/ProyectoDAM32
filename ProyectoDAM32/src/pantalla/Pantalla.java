package pantalla;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.Timer;

import juego.EstadoJuego;
import juego.Juego;
import juego.jugador.Jugador;

public class Pantalla extends JFrame implements KeyListener, MouseWheelListener {	
	private static final long serialVersionUID = 1L;	
	
	public final int WIDTH = 720;
	public final int HEIGHT = 720;
	
	private BufferedImage bf;
	private Juego juego;
	
	public Pantalla(Juego juego) {
		this.juego = juego;
		bf = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		setUndecorated(true);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        
        addKeyListener(this);
        
        addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		dispose();
        		System.exit(0);
        	}
        });
        
        comenzar();
	}
	
	public void paint(Graphics g) {
		Graphics2D bff = (Graphics2D) bf.getGraphics();
		EstadoJuego estadoJuego = juego.getEstadoJuego();
		Renderizador render = juego.getRender();
		Jugador jugador = juego.getJugador();
		bff.setColor(Color.WHITE);
		bff.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(render != null) {
			switch(estadoJuego) {
			case GENERANDO:
				render.getVistaMapaGen().pintar(bff);
				break;
			case VISTA_MUNDO:
				render.getCamara().pintar(bff);
				break;
			case PRECARGA:
				break;
			}
		}	
		
		if(jugador != null) {
			switch(jugador.getEstadoJugador()) {
			case ELEGIR_NACION:
				jugador.pintar(bff);
				break;
			default:
			}
		}
		
		g.drawImage(bf, 0, 0, null);
	}
	
	public void comenzar() {
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaint();   
			}
		};
		Timer timer = new Timer(20, listener);
		timer.setRepeats(true);
		timer.start();
	}
	
	public Juego getJuego() {
		return juego;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
	}
}
