package juego.mapa;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum TipoCasilla {
	OCEANO(0, "Oceano", false, true, new Color(45, 85, 205)), 
	LLANURA(1, "Llanura", new Color(144, 238, 144)), 
	BOSQUE(2, "Bosque", new Color(0, 100, 0)), 
	DESIERTO(3, "Desierto", new Color(239, 232, 134)),
	MONTE(4, "Monte", false, new Color(220, 220, 220)),
	RIO(5, "Rio", false, true, new Color(65, 163, 255)),
	TUNDRA(6, "Tundra", new Color(227, 229, 232)),
	HIELO(7, "Hielo", false, new Color(220, 220, 220)),
	MAR(8, "Mar", false, true, new Color(65, 105, 225));
	
	private int tipo;
	private String nombre;
	private BufferedImage textura;
	private Color color;
	private boolean moldeable;
	private boolean liquido;
	
	private TipoCasilla(int tipo, String nombre, Color color) {
		this(tipo, nombre, true, false, color);
	}
	
	private TipoCasilla(int tipo, String nombre, boolean moldeable, Color color) {
		this(tipo, nombre, moldeable, false, color);
	}
	
	private TipoCasilla(int tipo, String nombre, boolean moldeable, boolean liquido, Color color) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.color = color;
		this.moldeable = moldeable;
		this.liquido = liquido;
		
		textura = null;
		try {
			textura = ImageIO.read(new File("src/res/texturas/"+nombre+".png"));
		} catch(IOException e) {
			System.out.println("Error al cargar textura "+nombre);
		}
	}
	
	public int getTipo() {
		return tipo;
	}
	
	public Color getColor() {
		return color;
	}
	
	public BufferedImage getTextura() {
		return textura;
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
