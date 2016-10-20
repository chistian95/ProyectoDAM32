package juego.mapa.raster;

import java.util.List;

import juego.mapa.Casilla;
import juego.mapa.Generador;
import juego.mapa.TipoCasilla;

public class Paso2 extends Rasterizador {
	public final int TAM_PASO2 = 4;
	public final double PROB_MONTE = 0.5;

	public Paso2(Generador generador) {
		super(generador);
		generador.setFase(0);
	}

	@Override
	public void generar() {	
		this.getGenerador().setFase(2);
		List<Casilla> pasof1 = this.getGenerador().getPaso1().getCasillas();	
		
		for(Casilla cas : pasof1) {
			for(int y=0; y<TAM_PASO2; y++) {
				Casilla casAnt = null;
				for(int x=0; x<TAM_PASO2; x++) {
					int posX = cas.getX()*TAM_PASO2 + x;
					int posY = cas.getY()*TAM_PASO2 + y;
					Casilla casP1 = new Casilla(cas.getTipo(), posX, posY);
					
					if(casAnt != null) {
						casAnt.setCasRight(casP1);
						casP1.setCasLeft(casAnt);
					}
					casAnt = casP1;
					
					this.getCasillas().add(casP1);					
				}
			}
		}
		
		for(Casilla cas : this.getCasillas()) {			
			if(!cas.getTipo().equals(TipoCasilla.AGUA)) {	
				double rnd = Math.random()*100;
				if(rnd <= PROB_MONTE) {
					cas.setTipo(TipoCasilla.MONTE);
					continue;
				}				
			}
			cas.setTipo(getTipo(this.getCasillas(), cas));
		}
	}

}
