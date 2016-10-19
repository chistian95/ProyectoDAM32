package juego.mapa;

import java.util.ArrayList;
import java.util.List;

public class Paso1 {
	public final int TAM_PASO1 = 50;
	
	private Generador generador;
	private List<Casilla> casillas;
	
	public Paso1(Generador generador) {
		this.generador = generador;
		casillas = generar();
	}
	
	private List<Casilla> generar() {
		List<Casilla> paso1 = new ArrayList<Casilla>();
		List<Casilla> seed = generador.getSeed().getCasillas();
		
		int div = TAM_PASO1 / generador.getSeed().TAM_SEED;
		for(Casilla cas : seed) {
			for(int y=0; y<div; y++) {
				for(int x=0; x<div; x++) {
					TipoCasilla tipo = getTipo(cas);
					int posX = cas.getX()*div + x;
					int posY = cas.getY()*div + y;
					Casilla casP1 = new Casilla(tipo, posX, posY);
					paso1.add(casP1);
				}
			}
		}
		
		return paso1;
	}
	
	private TipoCasilla getTipo(Casilla cas) {
		TipoCasilla tipo = TipoCasilla.AGUA;
		
		Seed seed = generador.getSeed();
		
		Casilla cas_up = seed.buscarCasilla(cas.getX(), cas.getY()-1);
		Casilla cas_down = seed.buscarCasilla(cas.getX(), cas.getY()+1);
		Casilla cas_left = seed.buscarCasilla(cas.getX()-1, cas.getY());
		Casilla cas_right = seed.buscarCasilla(cas.getX()+1, cas.getY());
		
		Casilla cas_up_left = seed.buscarCasilla(cas.getX()-1, cas.getY()-1);
		Casilla cas_up_right = seed.buscarCasilla(cas.getX()+1, cas.getY()-1);
		Casilla cas_down_left = seed.buscarCasilla(cas.getX()-1, cas.getY()+1);
		Casilla cas_down_right = seed.buscarCasilla(cas.getX()+1, cas.getY()+1);
		
		//agua, llanura, bosque, desierto
		int[] tipos = new int[4]; 
		tipos = sumarTipos(tipos, cas);
		
		tipos = sumarTipos(tipos, cas_up);
		tipos = sumarTipos(tipos, cas_down);
		tipos = sumarTipos(tipos, cas_left);
		tipos = sumarTipos(tipos, cas_right);
		
		tipos = sumarTipos(tipos, cas_up_left);
		tipos = sumarTipos(tipos, cas_up_right);
		tipos = sumarTipos(tipos, cas_down_left);
		tipos = sumarTipos(tipos, cas_down_right);
		
		double prob_agua = tipos[0]/9 * 100;
		double prob_llanura = tipos[1]/9 * 100 + prob_agua;
		double prob_bosque = tipos[2]/9 * 100 + prob_agua+ prob_llanura;
		double prob_desierto = tipos[3]/9 * 100 + prob_agua + prob_llanura + prob_bosque;
		
		double rnd = Math.random()*100;
		if(rnd < prob_agua) {
			tipo = TipoCasilla.AGUA;
		} else if(rnd < prob_llanura) {
			tipo = TipoCasilla.LLANURA;
		} else if(rnd < prob_bosque) {
			tipo = TipoCasilla.BOSQUE;
		} else if(rnd < prob_desierto) {
			tipo = TipoCasilla.DESIERTO;
		}
		
		return tipo;
	}
	
	private int[] sumarTipos(int[] tipos, Casilla cas) {
		if(cas == null) {
			return tipos;
		}
		switch(cas.getTipo()) {
		case AGUA:
			tipos[0]++;
			break;
		case LLANURA:
			tipos[1]++;
			break;
		case BOSQUE:
			tipos[2]++;
			break;
		case DESIERTO:
			tipos[3]++;
			break;
		default:
		}
		return tipos;
	}
	
	public List<Casilla> getCasillas() {
		return casillas;
	}
}
