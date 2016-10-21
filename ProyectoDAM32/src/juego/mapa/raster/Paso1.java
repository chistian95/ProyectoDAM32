package juego.mapa.raster;

import juego.mapa.Casilla;
import juego.mapa.Generador;
import juego.mapa.TipoCasilla;

public class Paso1 extends Rasterizador {
	public final static int TAM_PASO1 = 5;
	public final static int TAM_HIELO = 3;
	
	private static final int DELAY_GEN = 15; //15
	
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
					TipoCasilla tipoRnd = TipoCasilla.OCEANO;
					
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
				Thread.sleep(DELAY_GEN);
			} catch(Exception e) {
			
			}
		}
		
		for(int y=0; y<getCasillas().length; y++) {
			getCasillas()[0][y].setTipo(TipoCasilla.OCEANO);
			getCasillas()[1][y].setTipo(TipoCasilla.OCEANO);
			getCasillas()[getCasillas().length-1][y].setTipo(TipoCasilla.OCEANO);
			getCasillas()[getCasillas().length-2][y].setTipo(TipoCasilla.OCEANO);
			
			double rnd = Math.random()*100;
			if(rnd < 25) {
				getCasillas()[2][y].setTipo(TipoCasilla.OCEANO);
			}
			rnd = Math.random()*100;
			if(rnd < 25) {
				getCasillas()[getCasillas().length-3][y].setTipo(TipoCasilla.OCEANO);
			}			
			
			try {
				Thread.sleep(DELAY_GEN/2);
			} catch(Exception e) {
			
			}
		}
		
		for(int x=0; x<getCasillas().length; x++) {
			for(int y=0; y<TAM_HIELO; y++) {
				if(y <= 1 || y >= getCasillas().length-2) {
					getCasillas()[x][y].setTipo(TipoCasilla.HIELO);
					continue;
				}
				if(getCasillas()[x][y].getTipo().equals(TipoCasilla.OCEANO)) {
					continue;
				}
				getCasillas()[x][y].setTipo(TipoCasilla.TUNDRA);
			}
			
			for(int y=getCasillas().length-1; y>=getCasillas().length-1-TAM_HIELO; y--) {
				if(y <= 1 || y >= getCasillas().length-2) {
					getCasillas()[x][y].setTipo(TipoCasilla.HIELO);
					continue;
				}
				if(getCasillas()[x][y].getTipo().equals(TipoCasilla.OCEANO)) {
					continue;
				}
				getCasillas()[x][y].setTipo(TipoCasilla.TUNDRA);
			}
			
			try {
				Thread.sleep(DELAY_GEN/2);
			} catch(Exception e) {
			
			}
		}
		
	}
}	
