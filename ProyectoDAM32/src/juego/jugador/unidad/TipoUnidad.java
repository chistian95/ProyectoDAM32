package juego.jugador.unidad;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum TipoUnidad {
	GUERRERO("Guerrero", 5, 2, "guerrero");
	
	private String nombre;
	private int fuerza;
	private int movimiento;
	private BufferedImage textura;
	
	private TipoUnidad(String nombre, int fuerza, int movimiento, String ruta) {
		this.nombre = nombre;
		this.fuerza = fuerza;
		this.movimiento = movimiento;
		
		try {
			textura = ImageIO.read(new File("src/res/unidades/"+ruta+".png"));
		} catch (IOException e) {
			System.out.println("Error al cargar textura de unidad "+ruta);
		}
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getFuerza() {
		return fuerza;
	}
	
	public int getMovimiento() {
		return movimiento;
	}
	
	public BufferedImage getTextura() {
		return textura;
	}
}
