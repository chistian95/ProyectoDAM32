package juego.mapa;

import java.awt.Color;

public enum TipoCasilla {
	OCEANO(0, "Oceano", false, true, new Color(45, 85, 205)), 
	LLANURA(1, "Llanura", true, false, new Color(144, 238, 144)), 
	BOSQUE(2, "Bosque", true, false, new Color(0, 100, 0)), 
	DESIERTO(3, "Desierto", true, false, new Color(239, 232, 134)),
	MONTE(4, "Monte", false, false, new Color(220, 220, 220)),
	RIO(5, "Río", false, true, new Color(65, 163, 255)),
	TUNDRA(6, "Tundra", true, false, new Color(227, 229, 232)),
	HIELO(7, "Hielo", false, false, new Color(220, 220, 220)),
	MAR(8, "Mar", false, true, new Color(65, 105, 225));
	
	private int tipo;
	private Color color;
	private String nombre;
	private boolean moldeable;
	private boolean liquido;
	
	private TipoCasilla(int tipo, String nombre, boolean moldeable, boolean liquido, Color color) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.moldeable = moldeable;
		this.liquido = liquido;
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
	
	public boolean isLiquido() {
		return liquido;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
}
