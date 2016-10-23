package pantalla;

import juego.Juego;

public class Renderizador {	
	private VistaMapaGen vistaMapaGen;
	private Camara camara;
	
	public Renderizador(Juego juego) {
		vistaMapaGen = new VistaMapaGen(juego);
		camara = new Camara(juego);
	}
	
	public VistaMapaGen getVistaMapaGen() {
		return vistaMapaGen;
	}
	
	public Camara getCamara() {
		return camara;
	}
}
