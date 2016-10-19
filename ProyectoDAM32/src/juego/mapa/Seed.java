package juego.mapa;

import java.util.ArrayList;
import java.util.List;

public class Seed {
	public final int TAM_SEED = 10;
	
	private static final double PROB_AGUA = 70;
	
	private List<Casilla> casillas;
	
	public Seed() {
		casillas = generar();
	}
	
	private List<Casilla> generar() {
		List<Casilla> seed = new ArrayList<Casilla>();
		
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
				seed.add(cas);
			}
		}
		
		return seed;
	}
	
	public Casilla buscarCasilla(int x, int y) {
		for(Casilla cas : casillas) {
			if(cas.getX() == x && cas.getY() == y) {
				return cas;
			}
		}
		return null;
	}
	
	public List<Casilla> getCasillas() {
		return casillas;
	}
}
