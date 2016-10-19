package juego.mapa.raster;

import java.util.List;

import juego.mapa.Casilla;
import juego.mapa.Generador;
import juego.mapa.TipoCasilla;

public class Paso1 extends Rasterizador {
	public final int TAM_PASO1 = 50;
	public final double PROB_ISLA = 1.25;
		
	public Paso1(Generador generador) {
		super(generador);
	}
	
	@Override
	public void generar() {
		this.getGenerador().setFase(1);
		List<Casilla> seed = this.getGenerador().getSeed().getCasillas();
		
		int div = TAM_PASO1 / this.getGenerador().getSeed().TAM_SEED;
		for(Casilla cas : seed) {
			for(int y=0; y<div; y++) {
				for(int x=0; x<div; x++) {
					int posX = cas.getX()*div + x;
					int posY = cas.getY()*div + y;
					Casilla casP1 = new Casilla(cas.getTipo(), posX, posY);
					this.getCasillas().add(casP1);
				}
			}
		}
		
		for(Casilla cas : this.getCasillas()) {
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
				
				cas.setTipo(tipoRnd);
				continue;
			}
			cas.setTipo(getTipo(this.getCasillas(), cas));
			
			try {
				Thread.sleep(1);
			} catch(Exception e) {
				
			}
		}
	}	
}
