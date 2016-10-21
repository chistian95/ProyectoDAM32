package pantalla;

import java.awt.Graphics2D;

import juego.mapa.Casilla;
import juego.mapa.Generador;
import juego.mapa.TipoCasilla;

public class VistaMapaGen {
	private Pantalla pantalla;
	private Generador generador;
	
	public VistaMapaGen(Pantalla pantalla) {
		this.pantalla = pantalla;
		this.generador = pantalla.getGenerador();
	}
	
	public void pintar(Graphics2D g) {
		Casilla[][] seed = null;
		switch(generador.getFase()) {
		case 0:
			seed = generador.getSeed().getCasillas();
			break;
		case 1:
			seed = generador.getPaso1().getCasillas();
			break;
		case 2:
			seed = generador.getPaso2().getCasillas();
			break;
		}
		
		if(seed == null) {
			return;
		}
		
		int tam = pantalla.WIDTH / seed.length;	
		
		for(int y=0; y<seed.length; y++) {
			for(int x=0; x<seed.length; x++) {
				Casilla cas = seed[x][y];
				if(cas != null) {
					g.setColor(cas.getTipo().getColor());
				} else {
					g.setColor(TipoCasilla.OCEANO.getColor());
				}
				g.fillRect(x*tam, y*tam, tam, tam);
			}
		}
	}
}
