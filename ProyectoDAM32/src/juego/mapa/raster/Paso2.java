package juego.mapa.raster;

import java.util.ArrayList;
import java.util.List;

import juego.mapa.Casilla;
import juego.mapa.Direccion;
import juego.mapa.Generador;
import juego.mapa.TipoCasilla;

public class Paso2 extends Rasterizador {
	public static final int TAM_PASO2 = 4;
	
	private static final int DELAY_GEN = 5; //5
	private static final int DELAY_RIOS = 1; //1
	
	public final double PROB_MONTE = 0.5;
	public final double PROB_RIO = 0.15;

	public Paso2(Generador generador) {
		super(generador, Seed.TAM_SEED*Paso1.TAM_PASO1*TAM_PASO2);
		generador.setFase(0);
	}

	@Override
	public void generar() {	
		this.getGenerador().setFase(2);
		Casilla[][] paso1 = getGenerador().getPaso1().getCasillas();	
		
		for(int yPaso1=0; yPaso1<paso1.length; yPaso1++) {
			for(int y=0; y<TAM_PASO2; y++) {
				for(int xPaso1=0; xPaso1<paso1.length; xPaso1++) {
					for(int x=0; x<TAM_PASO2; x++) {
						int posX = xPaso1*TAM_PASO2 + x;
						int posY = yPaso1*TAM_PASO2 + y;
						Casilla cas = new Casilla(paso1[xPaso1][yPaso1].getTipo(), posX, posY);
						getCasillas()[posX][posY] = cas;
					}
				}
			}
		}
		
		for(int y=0; y<getCasillas().length; y++) {
			for(int x=0; x<getCasillas().length; x++) {
				Casilla cas = getCasillas()[x][y];
				if(!cas.getTipo().equals(TipoCasilla.AGUA)) {	
					double rnd = Math.random()*100;
					if(rnd <= PROB_MONTE) {
						cas.setTipo(TipoCasilla.MONTE);
						continue;
					}				
				}
				cas.setTipo(getTipo(getCasillas(), x, y));
			}
			
			try {
				Thread.sleep(DELAY_GEN);
			} catch(Exception e) {
				
			}
		}

		for(int y=0; y<getCasillas().length; y++) {
			for(int x=0; x<getCasillas().length; x++) {
				Casilla cas = getCasillas()[x][y];
				if(cas.getTipo().isMoldeable()) {
					double rnd = Math.random()*100;
					if(rnd <= PROB_RIO) {
						int dirRnd = (int) (Math.random()*Direccion.values().length);
						generarRio(x, y, Direccion.values()[dirRnd]);
						continue;
					}	
				}
			}
		}
	}
	
	private void generarRio(int x, int y, Direccion dir) {	
		
		Casilla[][] casillas = getCasillas();		
		casillas[x][y].setTipo(TipoCasilla.RIO);
		
		List<Casilla> posiciones = new ArrayList<Casilla>();
		
		for(int i=0; i<Direccion.values().length; i++) {
			if(validarRio(x, y, Direccion.values()[i])) {
				switch(Direccion.values()[i]) {
				case NORTE:
					posiciones.add(casillas[x][y-1]);
					if(dir.equals(Direccion.NORTE)) {
						posiciones.add(casillas[x][y-1]);
						posiciones.add(casillas[x][y-1]);
					}
					break;
				case SUR:
					posiciones.add(casillas[x][y+1]);
					if(dir.equals(Direccion.SUR)) {
						posiciones.add(casillas[x][y+1]);
						posiciones.add(casillas[x][y+1]);
					}
					break;
				case ESTE:
					posiciones.add(casillas[x+1][y]);
					if(dir.equals(Direccion.ESTE)) {
						posiciones.add(casillas[x+1][y]);
						posiciones.add(casillas[x+1][y]);
					}
					break;
				case OESTE:
					posiciones.add(casillas[x-1][y]);
					if(dir.equals(Direccion.OESTE)) {
						posiciones.add(casillas[x-1][y]);
						posiciones.add(casillas[x-1][y]);
					}
					break;
				}
			}
		}
		
		if(posiciones.size() == 0) {
			return;
		}
		int rnd = (int) (Math.random()*posiciones.size());
		Casilla cas = posiciones.get(rnd);
		
		if(cas.getTipo().equals(TipoCasilla.AGUA)) {
			return;
		}
		
		try {
			Thread.sleep(DELAY_RIOS);
		} catch(Exception e) {
			
		}
		
		generarRio(cas.getX(), cas.getY(), dir);
	}

	private boolean validarRio(int x, int y, Direccion dir) {
		Casilla[][] casillas = getCasillas();
		
		switch(dir) {
		case NORTE:
			y--;
			break;
		case SUR:
			y++;
			break;
		case ESTE:
			x++;
			break;
		case OESTE:
			x--;
			break;
		}
		
		if(x < 0 || x >= casillas.length || y < 0 || y >= casillas.length) {
			return false;
		}		
		
		if(!casillas[x][y].getTipo().isMoldeable() && !casillas[x][y].getTipo().equals(TipoCasilla.AGUA)) {
			return false;
		}
		
		int numRios = 0;
		if(x-1 >= 0 && casillas[x-1][y].getTipo().equals(TipoCasilla.RIO)) {
			numRios++;
		}
		if(x+1 < casillas.length && casillas[x+1][y].getTipo().equals(TipoCasilla.RIO)) {
			numRios++;
		}
		if(y-1 >= 0 && casillas[x][y-1].getTipo().equals(TipoCasilla.RIO)) {
			numRios++;
		}
		if(y+1 < casillas.length && casillas[x][y+1].getTipo().equals(TipoCasilla.RIO)) {
			numRios++;
		}
		
		if(numRios > 1) {
			return false;
		}
		
		return true;
	}
}
