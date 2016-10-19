package juego.mapa.raster;

import juego.mapa.Casilla;
import juego.mapa.Generador;
import juego.mapa.TipoCasilla;

public class Seed extends Rasterizador {
	public final int TAM_SEED = 10;
	
	private static final double PROB_AGUA = 52;
	
	public Seed(Generador generador) {
		super(generador);
	}
	
	@Override
	public void generar() {
		this.getGenerador().setFase(0);
		
		for(int y=0; y<TAM_SEED; y++) {
			for(int x=0; x<TAM_SEED; x++) {
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
				this.getCasillas().add(cas);
				
				try {
					Thread.sleep(10);
				} catch(Exception e) {
					
				}
			}
		}
	}
	
	public Casilla buscarCasilla(int x, int y) {
		for(Casilla cas : this.getCasillas()) {
			if(cas.getX() == x && cas.getY() == y) {
				return cas;
			}
		}
		return null;
	}
}
