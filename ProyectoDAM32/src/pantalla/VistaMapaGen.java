package pantalla;

import java.awt.Graphics2D;
import java.util.List;

import juego.mapa.Casilla;
import juego.mapa.Generador;

public class VistaMapaGen {
	private Pantalla pantalla;
	private Generador generador;
	private int tam_seed;
	private int tam_paso1;
	private int tam_paso2;
	
	public VistaMapaGen(Pantalla pantalla) {
		this.pantalla = pantalla;
		this.generador = pantalla.getGenerador();
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
