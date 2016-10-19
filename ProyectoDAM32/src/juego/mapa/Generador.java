package juego.mapa;

public class Generador {	
	private Seed seed;
	private Paso1 paso1;
	private int fase;
	
	public Generador() {	
		seed = new Seed();
		paso1 = new Paso1(this);			
		fase = 0;	
	}
	
	public Seed getSeed() {
		return seed;
	}
	
	public Paso1 getPaso1() {
		return paso1;
	}
	
	public int getFase() {
		return fase;
	}
	
	public void setFase(int fase) {
		this.fase = fase;
	}
}
