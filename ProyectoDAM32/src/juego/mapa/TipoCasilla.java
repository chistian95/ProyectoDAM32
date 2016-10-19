package juego.mapa;

import java.awt.Color;

public enum TipoCasilla {
	AGUA(0, new Color(65, 105, 225)), 
	LLANURA(1, new Color(144, 238, 144)), 
	BOSQUE(2, new Color(0, 100, 0)), 
	DESIERTO(3, new Color(255, 222, 173)), 
	MONTE(4, new Color(220, 220, 220));
	
	private int tipo;
	private Color color;
	
	private TipoCasilla(int tipo, Color color) {
		this.tipo = tipo;
		this.color = color;
	}
	
	public int getTipo() {
		return tipo;
	}
	
	public Color getColor() {
		return color;
	}
	
	@Override
	public String toString() {
		String res = "";
		
		switch(this) {
		case AGUA:
			res = "Agua";
			break;
		case LLANURA:
			res = "Llanura";
			break;
		case BOSQUE:
			res = "Bosque";
			break;
		case DESIERTO:
			res = "Desierto";
			break;
		case MONTE:
			res = "Monte";
			break;
		}
		
		return res;
	}
}
