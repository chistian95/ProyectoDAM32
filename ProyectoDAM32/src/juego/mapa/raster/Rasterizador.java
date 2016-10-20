package juego.mapa.raster;

import java.util.HashMap;

import juego.mapa.Casilla;
import juego.mapa.Generador;
import juego.mapa.TipoCasilla;

public abstract class Rasterizador extends Thread {
	private Casilla[][] casillas;
	private Generador generador;
	private int tam;
	
	public Rasterizador(Generador generador, int tam) {
		this.generador = generador;
		this.tam = tam;
		casillas = new Casilla[tam][tam];
		start();
	}
	
	public void run() {
		generar();
	}
	
	public TipoCasilla getTipo(Casilla[][] casillas, int x, int y) {
		TipoCasilla tipoCasilla = casillas[x][y].getTipo();
		
		Casilla cas = casillas[x][y];
		Casilla cas_up = cas;
		if(y-1 >= 0) {
			cas_up = casillas[x][y-1];
		}
		Casilla cas_down = cas;
		if(y+1 < casillas.length) {
			cas_down = casillas[x][y+1];
		}
		Casilla cas_left = cas;
		if(x-1 >= 0) {
			cas_left = casillas[x-1][y];
		}
		Casilla cas_right = cas;
		if(x+1 < casillas.length) {
			cas_right = casillas[x+1][y];
		}		
		
		boolean iguales = false;
		if(cas.getTipo().equals(cas_up.getTipo()) && cas.getTipo().equals(cas_down.getTipo()) && cas.getTipo().equals(cas_left.getTipo()) && cas.getTipo().equals(cas_right.getTipo())) {
			iguales = true;
		}
		
		//agua, llanura, bosque, desierto
		HashMap<TipoCasilla, Integer> tipos = new HashMap<TipoCasilla, Integer>();
		if(!iguales) {			
			sumarTipos(tipos, cas);
			
			sumarTipos(tipos, cas_up);
			sumarTipos(tipos, cas_down);
			sumarTipos(tipos, cas_left);
			sumarTipos(tipos, cas_right);
		}		
		
		if(!iguales) {
			double pesoTotal = 0.0;
			for(int i=0; i<tipos.size(); i++) {
				pesoTotal += (int) tipos.values().toArray()[i];
			}
			
			Double rnd = Math.random()*pesoTotal;
			Double pesoCont = 0.0;
			for(int i=0; i<tipos.size(); i++) {
				pesoCont += (int) tipos.values().toArray()[i];
				if(rnd < pesoCont) {	
					tipoCasilla =  (TipoCasilla) tipos.keySet().toArray()[i];
					break;
				}
			}
		}
		
		return tipoCasilla;
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
	
	public Casilla[][] getCasillas() {
		return casillas;
	}
	
	public Generador getGenerador() {
		return generador;
	}
	
	public int getTam() {
		return tam;
	}
}
