package juego.mapa.raster;

import java.util.List;

import juego.mapa.Casilla;
import juego.mapa.Generador;
import juego.mapa.TipoCasilla;

public class Paso2 extends Rasterizador {
	public final int TAM_PASO2 = 200;
	public final double PROB_MONTE = 0.5;

	public Paso2(Generador generador) {
		super(generador);
		generador.setFase(0);
	}

	@Override
	public void generar() {
		this.getGenerador().setFase(2);
		List<Casilla> pasof1 = this.getGenerador().getPaso1().getCasillas();		
		
		int div = TAM_PASO2 / this.getGenerador().getPaso1().TAM_PASO1;
		for(Casilla cas : pasof1) {
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
			if(!cas.getTipo().equals(TipoCasilla.AGUA) && rnd <= PROB_MONTE) {				
				cas.setTipo(TipoCasilla.MONTE);
				continue;
			}
			cas.setTipo(getTipo(this.getCasillas(), cas));
		}
	}

}
