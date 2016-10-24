package pantalla;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import juego.Juego;
import juego.mapa.Casilla;
import juego.mapa.Generador;
import juego.mapa.TipoCasilla;
import pantalla.pintar.TextoPintable;

public class VistaMapaGen {
	private Pantalla pantalla;
	private Generador generador;
	
	public VistaMapaGen(Juego juego) {
		this.pantalla = juego.getPantalla();
		this.generador = juego.getGenerador();
	}
	
	public void pintar(Graphics2D g) {
		Casilla[][] seed = null;
		String textoGen = "";
		switch(generador.getFase()) {
		case 0:
			if(generador.getSeed() == null) {
				break;
			}
			seed = generador.getSeed().getCasillas();
			textoGen = generador.getSeed().getTextoGen();
			break;
		case 1:
			if(generador.getPaso1() == null) {
				break;
			}
			seed = generador.getPaso1().getCasillas();
			textoGen = generador.getPaso1().getTextoGen();
			break;
		case 2:
			if(generador.getPaso2() == null) {
				break;
			}
			seed = generador.getPaso2().getCasillas();
			textoGen = generador.getPaso2().getTextoGen();
			break;
		}
		
		if(seed == null) {
			return;
		}
		
		int tam = pantalla.WIDTH / seed.length;	
		
		for(int y=0; y<seed.length; y++) {
			for(int x=0; x<seed.length; x++) {
				Casilla cas = seed[x][y];
				if(cas != null) {
					g.setColor(cas.getTipo().getColor());
				} else {
					g.setColor(TipoCasilla.OCEANO.getColor());
				}
				g.fillRect(x*tam, y*tam, tam, tam);
			}
		}
		
		List<TextoPintable> lista = new ArrayList<TextoPintable>();
		lista.add(new TextoPintable(textoGen));
		Ventanas.pintarVentanaInferior(g, pantalla, lista);
	}
}
