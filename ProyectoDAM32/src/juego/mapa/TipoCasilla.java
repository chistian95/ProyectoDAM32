package juego.mapa;

import java.awt.Color;

public enum TipoCasilla {
	AGUA(0, "Mar", false, new Color(65, 105, 225)), 
	LLANURA(1, "Llanura", true, new Color(144, 238, 144)), 
	BOSQUE(2, "Bosque", true, new Color(0, 100, 0)), 
	DESIERTO(3, "Desierto", true, new Color(255, 222, 173)), 
	MONTE(4, "Monte", false, new Color(220, 220, 220)),
	RIO(4, "Río", false, new Color(65, 163, 255));
	
	private int tipo;
	private Color color;
	private String nombre;
	private boolean moldeable;
	
	private TipoCasilla(int tipo, String nombre, boolean moldeable, Color color) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.moldeable = moldeable;
		this.color = color;
	}
	
	public int getTipo() {
		return tipo;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean isMoldeable() {
		return moldeable;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
}
