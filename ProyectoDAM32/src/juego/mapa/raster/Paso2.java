package juego.mapa.raster;

import java.util.ArrayList;
import java.util.HashMap;
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
	public List<Casilla> generar() {
		List<Casilla> paso2 = new ArrayList<Casilla>();
		List<Casilla> pasof1 = this.getGenerador().getPaso1().getCasillas();		
		
		int div = TAM_PASO2 / this.getGenerador().getPaso1().TAM_PASO1;
		for(Casilla cas : pasof1) {
			for(int y=0; y<div; y++) {
				for(int x=0; x<div; x++) {
					int posX = cas.getX()*div + x;
					int posY = cas.getY()*div + y;
					Casilla casP1 = new Casilla(cas.getTipo(), posX, posY);
					paso2.add(casP1);
				}
			}
		}
		
		for(Casilla cas : paso2) {
			double rnd = Math.random()*100;
			if(!cas.getTipo().equals(TipoCasilla.AGUA) && rnd <= PROB_MONTE) {				
				cas.setTipo(TipoCasilla.MONTE);
				continue;
			}
			cas.setTipo(getTipo(paso2, cas));
		}
		
		return paso2;
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
