package pantalla;

import java.awt.Graphics2D;
import java.util.List;

import juego.mapa.Casilla;
import juego.mapa.Generador;

public class Renderizador {	
	private Generador generador;
	private int tam_seed;
	private int tam_paso1;
	
	public Renderizador(Pantalla pantalla) {
		generador = pantalla.getGenerador();
		tam_seed = pantalla.WIDTH / pantalla.getGenerador().getSeed().TAM_SEED;
		tam_paso1 = pantalla.WIDTH / pantalla.getGenerador().getPaso1().TAM_PASO1;
	}
	
	public void pintarSeed(Graphics2D g) {
		List<Casilla> seed = generador.getSeed().getCasillas();
		
		for(Casilla cas : seed) {
			int x = cas.getX()*tam_seed;
			int y = cas.getY()*tam_seed;
			g.setColor(cas.getTipo().getColor());
			g.fillRect(x, y, tam_seed, tam_seed);
		}
	}
	
	public void pintarPaso1(Graphics2D g) {
		List<Casilla> paso1 = generador.getPaso1().getCasillas();
		
		for(Casilla cas : paso1) {
			int x = cas.getX()*tam_paso1;
			int y = cas.getY()*tam_paso1;
			g.setColor(cas.getTipo().getColor());
			g.fillRect(x, y, tam_paso1, tam_paso1);
		}
	}
}
