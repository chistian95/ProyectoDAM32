package juego.mapa.raster;

import juego.mapa.Casilla;
import juego.mapa.Generador;
import juego.mapa.TipoCasilla;

public class Paso1 extends Rasterizador {
	public final static int TAM_PASO1 = 5;
	public final double PROB_ISLA = 1.25;
		
	public Paso1(Generador generador) {
		super(generador, Seed.TAM_SEED*TAM_PASO1);
	}
	
	@Override
	public void generar() {
		this.getGenerador().setFase(1);
		Casilla[][] seed = getGenerador().getSeed().getCasillas();
		
		for(int ySeed=0; ySeed<seed.length; ySeed++) {
			for(int y=0; y<TAM_PASO1; y++) {
				for(int xSeed=0; xSeed<seed.length; xSeed++) {
					for(int x=0; x<TAM_PASO1; x++) {
						int posX = xSeed*TAM_PASO1 + x;
						int posY = ySeed*TAM_PASO1 + y;
						Casilla cas = new Casilla(seed[xSeed][ySeed].getTipo(), posX, posY);
						this.getCasillas()[posX][posY] = cas;
					}
				}
			}
		}
		
		for(int y=0; y<getCasillas().length; y++) {
			for(int x=0; x<getCasillas().length; x++) {
				double rnd = Math.random()*100;
				if(rnd <= PROB_ISLA) {
					int i = (int) (Math.random()*3);				
					TipoCasilla tipoRnd = TipoCasilla.AGUA;
					
					switch(i) {
					case 0:
						tipoRnd = TipoCasilla.BOSQUE;
						break;
					case 1:
						tipoRnd = TipoCasilla.LLANURA;
						break;
					case 2:
						tipoRnd = TipoCasilla.DESIERTO;
						break;
					}
					
					getCasillas()[x][y].setTipo(tipoRnd);
					continue;
				}
				getCasillas()[x][y].setTipo(getTipo(getCasillas(), x, y));				
			}
			
			try {
				Thread.sleep(0);
			} catch(Exception e) {
			
			}
		}
	}
}	
