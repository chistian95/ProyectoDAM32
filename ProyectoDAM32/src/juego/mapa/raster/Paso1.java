package juego.mapa.raster;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	public List<Casilla> generar() {
		List<Casilla> paso1 = new ArrayList<Casilla>();
		List<Casilla> seed = this.getGenerador().getSeed().getCasillas();
		
		int div = TAM_PASO1 / this.getGenerador().getSeed().TAM_SEED;
		for(Casilla cas : seed) {
			for(int y=0; y<div; y++) {
				for(int x=0; x<div; x++) {
					int posX = cas.getX()*div + x;
					int posY = cas.getY()*div + y;
					Casilla casP1 = new Casilla(cas.getTipo(), posX, posY);
					paso1.add(casP1);
				}
			}
		}
		
		for(Casilla cas : paso1) {
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
			cas.setTipo(getTipo(paso1, cas));
		}
		
		return paso1;
	}
	
	private TipoCasilla getTipo(List<Casilla> casillas, Casilla cas) {			
		Casilla cas_up = this.buscarCasilla(casillas, cas.getX(), cas.getY()-1);
		Casilla cas_down = this.buscarCasilla(casillas, cas.getX(), cas.getY()+1);
		Casilla cas_left = this.buscarCasilla(casillas, cas.getX()-1, cas.getY());
		Casilla cas_right = this.buscarCasilla(casillas, cas.getX()+1, cas.getY());
		
		//agua, llanura, bosque, desierto
		HashMap<TipoCasilla, Integer> tipos = new HashMap<TipoCasilla, Integer>();
		sumarTipos(tipos, cas);
		
		sumarTipos(tipos, cas_up);
		sumarTipos(tipos, cas_down);
		sumarTipos(tipos, cas_left);
		sumarTipos(tipos, cas_right);
		
		double pesoTotal = 0.0;
		for(int i=0; i<tipos.size(); i++) {
			pesoTotal += (int) tipos.values().toArray()[i];
		}
		Double rnd = Math.random()*pesoTotal;
		Double pesoCont = 0.0;
		for(int i=0; i<tipos.size(); i++) {
			pesoCont += (int) tipos.values().toArray()[i];
			if(rnd < pesoCont) {	
				return (TipoCasilla) tipos.keySet().toArray()[i];
			}
		}
		
		return null;
	}
	
	private void sumarTipos(HashMap<TipoCasilla, Integer> tipos, Casilla cas) {
		if(cas == null) {
			return;
		}
		int peso = 1;
		if(tipos.containsKey(cas.getTipo())) {
			peso += tipos.get(cas.getTipo());
		}		
		tipos.put(cas.getTipo(), peso);
	}
}
