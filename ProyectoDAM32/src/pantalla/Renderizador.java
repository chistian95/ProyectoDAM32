package pantalla;

public class Renderizador {	
	private VistaMapaGen vistaMapaGen;
	private Camara camara;
	
	public Renderizador(Pantalla pantalla) {
		vistaMapaGen = new VistaMapaGen(pantalla);
		camara = new Camara(pantalla);
	}
	
	public VistaMapaGen getVistaMapaGen() {
		return vistaMapaGen;
	}
	
	public Camara getCamara() {
		return camara;
	}
}
