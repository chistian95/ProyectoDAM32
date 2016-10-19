package pantalla;

public class Renderizador {	
	private VistaMapaGen vistaMapaGen;
	
	public Renderizador(Pantalla pantalla) {
		vistaMapaGen = new VistaMapaGen(pantalla);
	}
	
	public VistaMapaGen getVistaMapaGen() {
		return vistaMapaGen;
	}
}
