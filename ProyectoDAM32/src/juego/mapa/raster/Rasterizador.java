package juego.mapa.raster;

import java.util.HashMap;

import juego.mapa.Casilla;
import juego.mapa.Generador;
import juego.mapa.TipoCasilla;

public abstract class Rasterizador extends Thread {
	private Casilla[][] casillas;
	private Generador generador;
	private String textoGen;
	private int tam;
	
	public Rasterizador(Generador generador, int tam) {
		this.generador = generador;
		this.tam = tam;
		textoGen = "Generando mundo...";
		casillas = new Casilla[tam][tam];
		start();
	}
	
	public void run() {
		generar();
	}
	
	public TipoCasilla getTipo(Casilla[][] casillas, int x, int y) {
		TipoCasilla tipoCasilla = casillas[x][y].getTipo();
		
		int yNeg = y-1;
		if(yNeg < 0) {
			yNeg = casillas[0].length - 1;
		}
		int xNeg = x-1;
		if(xNeg < 0) {
			xNeg = casillas.length - 1;
		}
		int yPos = y+1;
		if(yPos >= casillas[0].length) {
			yPos = 0;
		}
		int xPos = x+1;
		if(xPos >= casillas.length) {
			xPos = 0;
		}
		
		Casilla cas = casillas[x][y];
		
		Casilla cas_up = casillas[x][yNeg];
		Casilla cas_down = casillas[x][yPos];
		Casilla cas_left = casillas[xNeg][y];
		Casilla cas_right = casillas[xPos][y];
		
		Casilla cas_up_left = casillas[xNeg][yNeg];
		Casilla cas_up_right = casillas[xPos][yNeg];
		Casilla cas_down_left = casillas[xNeg][yPos];
		Casilla cas_down_right = casillas[xPos][yPos];
		
		boolean iguales = false;
		if(cas.getTipo().equals(cas_up.getTipo()) && cas.getTipo().equals(cas_down.getTipo())
				&& cas.getTipo().equals(cas_left.getTipo()) && cas.getTipo().equals(cas_right.getTipo())
				&& cas.getTipo().equals(cas_up_left.getTipo()) && cas.getTipo().equals(cas_up_right.getTipo())
				&& cas.getTipo().equals(cas_down_left.getTipo()) && cas.getTipo().equals(cas_down_right.getTipo())) {
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
			
			sumarTipos(tipos, cas_up_left);
			sumarTipos(tipos, cas_up_right);
			sumarTipos(tipos, cas_down_left);
			sumarTipos(tipos, cas_down_right);
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
	
	public String getTextoGen() {
		return textoGen;
	}
	
	public void setTextoGen(String textoGen) {
		this.textoGen = textoGen;
	}
	
	public int getTam() {
		return tam;
	}
}
