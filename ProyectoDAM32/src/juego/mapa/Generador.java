package juego.mapa;

public class Generador {
	private Casilla[][] seed;
	
	public Generador() {
		seed = new Casilla[10][10];
		
		generarSeed();
	}
	
	private void generarSeed() {
		for(int y=0; y<seed.length; y++) {
			for(int x=0; x<seed[y].length; x++) {
				int rnd = (int) (Math.random()*TipoCasilla.values().length);
				Casilla cas = new Casilla(TipoCasilla.values()[rnd]);
				seed[y][x] = cas;
			}
		}
	}
	
	public Casilla[][] getSeed() {
		return seed;
	}
}
