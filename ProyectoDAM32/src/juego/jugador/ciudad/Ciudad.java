package juego.jugador.ciudad;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import juego.jugador.Jugador;
import pantalla.pintar.CiudadPintable;

public class Ciudad {
	private Jugador jugador;
	private int x;
	private int y;
	private boolean capital;
	private CiudadPintable ciudadPintable;
	private BufferedImage textura;
	
	public Ciudad(Jugador jugador, int x, int y) {
		this.jugador = jugador;
		this.x = x;
		this.y = y;
		capital = false;
		try {
			textura = ImageIO.read(new File("src/res/ciudades/ciudad_antigua.png"));
		} catch (IOException e) {
			System.out.println("Error al cargar textura de ciudad");
		}
		ciudadPintable = new CiudadPintable(this);
	}
	
	public void centrarCamara() {
		new AnimacionCiudad(this);
	}
	
	public Jugador getJugador() {
		return jugador;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isCapital() {
		return capital;
	}
	
	public void setCapital(boolean capital) {
		this.capital = capital;
	}
	
	public BufferedImage getTextura() {
		return textura;
	}
	
	public CiudadPintable getCiudadPintable() {
		return ciudadPintable;
	}
}
