package pantalla;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.Timer;

import juego.mapa.Generador;

public class Pantalla extends JFrame {	
	private static final long serialVersionUID = 1L;	
	
	public final int WIDTH = 800;
	public final int HEIGHT = 800;
	
	private Generador generador;
	private Renderizador render;
	private BufferedImage bf;
	
	public Pantalla(Generador generador) {
		this.generador = generador;
		render = new Renderizador(this);
		bf = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		setUndecorated(true);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        
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
		
		switch(generador.getFase()) {
		case 0:
			render.pintarSeed(bff);
			break;
		case 1:
			render.pintarPaso1(bff);
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
	
	public Generador getGenerador() {
		return generador;
	}
}
