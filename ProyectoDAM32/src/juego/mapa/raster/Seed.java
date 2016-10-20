package juego.mapa.raster;

import juego.mapa.Casilla;
import juego.mapa.Generador;
import juego.mapa.TipoCasilla;

public class Seed extends Rasterizador {
	public static final int TAM_SEED = 12;
	private static final double PROB_AGUA = 50;
	
	public Seed(Generador generador) {
		super(generador, TAM_SEED);
	}
	
	@Override
	public void generar() {
		this.getGenerador().setFase(0);
		
		
		for(int y=0; y<getTam(); y++) {
			for(int x=0; x<getTam(); x++) {
				TipoCasilla tipo = TipoCasilla.AGUA;
				
				double rand = Math.random()*100;
				
				if(rand > PROB_AGUA) {
					int randTierra = (int) (Math.random()*3);
					
					switch(randTierra) {
					case 0:
						tipo = TipoCasilla.LLANURA;
						break;
					case 1:
						tipo = TipoCasilla.BOSQUE;
						break;
					case 2:
						tipo = TipoCasilla.DESIERTO;
						break;
					}
				}
				
				Casilla cas = new Casilla(tipo, x, y);
				getCasillas()[x][y] = cas;
			}
			try {
				Thread.sleep(0);
			} catch(Exception e) {
				
			}
		}
	}
}
