package juego.mapa.raster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import juego.mapa.Casilla;
import juego.mapa.Generador;
import juego.mapa.TipoCasilla;

public abstract class Rasterizador extends Thread {
	private List<Casilla> casillas;
	private Generador generador;
	
	public Rasterizador(Generador generador) {
		this.generador = generador;
		this.casillas = new ArrayList<Casilla>();
		start();
	}
	
	public void run() {
		generar();
	}
	
	public Casilla buscarCasilla(List<Casilla> casillas, int x, int y) {
		for(Casilla cas : casillas) {
			if(cas.getX() == x && cas.getY() == y) {
				return cas;
			}
		}
		return null;
	}
	
	public TipoCasilla getTipo(List<Casilla> casillas, Casilla cas) {			
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
	
	public abstract void generar();
	
	public List<Casilla> getCasillas() {
		return casillas;
	}
	
	public Generador getGenerador() {
		return generador;
	}
}
