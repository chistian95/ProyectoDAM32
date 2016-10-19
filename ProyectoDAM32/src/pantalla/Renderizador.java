package pantalla;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import juego.mapa.Casilla;
import juego.mapa.Generador;

public class Renderizador {	
	private Pantalla pantalla;
	private Generador generador;
	private int tam_seed;
	private int tam_paso1;
	private int tam_paso2;
	private int cont = 0;
	private boolean primera = false;
	private Timer timer;
	
	public Renderizador(Pantalla pantalla) {
		this.pantalla = pantalla;
		generador = pantalla.getGenerador();
		
	}
	
	public void pintarMenu(Graphics2D g) {
		int x = pantalla.WIDTH / 2 - 100;
		int y = pantalla.HEIGHT / 2 - 100;
		
		g.setColor(new Color(244, 167, 66));		
		g.fillRect(x, y, 200, 200);
		
		Font fuente = new Font("Times New Roman", Font.BOLD, 24);
		g.setFont(fuente);
		g.setColor(Color.WHITE);
		
		String puntos = "";
		for(int i=0; i<cont; i++) {
			puntos += ".";
		}
		
		if(!primera) {
			primera = true;
			iniciarTimer();
		}
		
		g.drawString("Cargando"+puntos, x+40, y+40);
		
		fuente = new Font("Times New Roman", Font.ITALIC, 18);
		g.setFont(fuente);
		g.setColor(Color.WHITE);
		g.drawString(generador.getFaseGen()+"", x+40, y+80);
		
		g.setColor(new Color(153, 102, 15));
		g.setStroke(new BasicStroke(15));
		g.drawRect(x, y, 200, 200);
	}
	
	private void iniciarTimer() {
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont++;
				if(cont > 3) {
					cont = 0;
				}
				if(generador.getFase() != -1) {
					timer.stop();
					primera = false;
				}
			}
		};
		timer = new Timer(1000, listener);
		timer.setRepeats(true);
		timer.start();
	}
	
	public void pintarSeed(Graphics2D g) {
		tam_seed = pantalla.WIDTH / pantalla.getGenerador().getSeed().TAM_SEED;
		List<Casilla> seed = generador.getSeed().getCasillas();
		
		for(Casilla cas : seed) {
			int x = cas.getX()*tam_seed;
			int y = cas.getY()*tam_seed;
			g.setColor(cas.getTipo().getColor());
			g.fillRect(x, y, tam_seed, tam_seed);
		}
	}
	
	public void pintarPaso1(Graphics2D g) {
		tam_paso1 = pantalla.WIDTH / pantalla.getGenerador().getPaso1().TAM_PASO1;
		List<Casilla> paso1 = generador.getPaso1().getCasillas();
		
		for(Casilla cas : paso1) {
			int x = cas.getX()*tam_paso1;
			int y = cas.getY()*tam_paso1;
			g.setColor(cas.getTipo().getColor());
			g.fillRect(x, y, tam_paso1, tam_paso1);
		}
	}
	
	public void pintarPaso2(Graphics2D g) {
		tam_paso2 = pantalla.WIDTH / pantalla.getGenerador().getPaso2().TAM_PASO2;
		List<Casilla> paso2 = generador.getPaso2().getCasillas();
		
		for(Casilla cas : paso2) {
			int x = cas.getX()*tam_paso2;
			int y = cas.getY()*tam_paso2;
			g.setColor(cas.getTipo().getColor());
			g.fillRect(x, y, tam_paso2, tam_paso2);
		}
	}
}
