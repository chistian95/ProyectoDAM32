package juego.mapa.raster;

import juego.mapa.Casilla;
import juego.mapa.Generador;
import juego.mapa.TipoCasilla;

public class Paso2 extends Rasterizador {
	public static final int TAM_PASO2 = 4;
	public final double PROB_MONTE = 0.5;

	public Paso2(Generador generador) {
		super(generador, Seed.TAM_SEED*Paso1.TAM_PASO1*TAM_PASO2);
		generador.setFase(0);
	}

	@Override
	public void generar() {	
		this.getGenerador().setFase(2);
		Casilla[][] paso1 = getGenerador().getPaso1().getCasillas();	
		
		for(int yPaso1=0; yPaso1<paso1.length; yPaso1++) {
			for(int y=0; y<TAM_PASO2; y++) {
				for(int xPaso1=0; xPaso1<paso1.length; xPaso1++) {
					for(int x=0; x<TAM_PASO2; x++) {
						int posX = xPaso1*TAM_PASO2 + x;
						int posY = yPaso1*TAM_PASO2 + y;
						Casilla cas = new Casilla(paso1[xPaso1][yPaso1].getTipo(), posX, posY);
						getCasillas()[posX][posY] = cas;
					}
				}
			}
		}
		
		for(int y=0; y<getCasillas().length; y++) {
			for(int x=0; x<getCasillas().length; x++) {
				Casilla cas = getCasillas()[x][y];
				if(!cas.getTipo().equals(TipoCasilla.AGUA)) {	
					double rnd = Math.random()*100;
					if(rnd <= PROB_MONTE) {
						cas.setTipo(TipoCasilla.MONTE);
						continue;
					}				
				}
				cas.setTipo(getTipo(getCasillas(), x, y));
			}
			
			try {
				Thread.sleep(0);
			} catch(Exception e) {
				
			}
		}
	}

}
