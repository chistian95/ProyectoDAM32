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
import juego.mapa.Generador;

public class Pantalla extends JFrame implements KeyListener, MouseWheelListener {	
	private static final long serialVersionUID = 1L;	
	
	public final int WIDTH = 720;
	public final int HEIGHT = 720;
	
	private Renderizador render;
	private Generador generador;
	private BufferedImage bf;
	private EstadoJuego estadoJuego;
	
	public Pantalla() {
		estadoJuego = EstadoJuego.PRECARGA; 
		generador = new Generador(this);
		render = new Renderizador(this);
		bf = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		setUndecorated(true);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        
        addKeyListener(this);
        addMouseWheelListener(this);
        
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
		bff.setColor(Color.WHITE);
		bff.fillRect(0, 0, WIDTH, HEIGHT);
		
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
	
	public EstadoJuego getEstadoJuego() {
		return estadoJuego;
	}
	
	public void setEstadoJuego(EstadoJuego estadoJuego) {
		this.estadoJuego = estadoJuego;
	}
	
	public Renderizador getRender() {
		return render;
	}
	
	public Generador getGenerador() {
		return generador;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		switch(estadoJuego) {
		case VISTA_MUNDO:
			render.getCamara().keyPressed(e);
			break;
		default:
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(estadoJuego) {
		case VISTA_MUNDO:
			render.getCamara().keyReleased(e);
			break;
		default:
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		switch(estadoJuego) {
		case VISTA_MUNDO:
			render.getCamara().mouseWheelMoved(e);
			break;
		default:
		}
	}
}
