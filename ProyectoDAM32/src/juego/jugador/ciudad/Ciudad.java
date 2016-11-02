package juego.jugador.ciudad;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import juego.jugador.Jugador;
import pantalla.Camara;
import pantalla.pintar.Pintable;

/**
 * La ciudad de un jugador (Se puede pintar en pantalla)
 * @author dam32-Corral
 *
 */
public class Ciudad implements Pintable {
	private Jugador jugador;
	private String nombre;
	private int x;
	private int y;
	private boolean capital;
	private BufferedImage textura;
	
	/**
	 * Crea la ciudad generando un nombre y carga su textura
	 * @param jugador Jugador dueño de la ciudad
	 * @param x Posición X en el mapa
	 * @param y Posición Y en el mapa
	 * @see #generarNombre()
	 */
	public Ciudad(Jugador jugador, int x, int y) {
		this.jugador = jugador;
		this.x = x;
		this.y = y;
		capital = false;
		
		generarNombre();
		
		try {
			textura = ImageIO.read(new File("src/res/ciudades/ciudad_antigua.png"));
		} catch (IOException e) {
			System.out.println("Error al cargar textura de ciudad");
		}
	}
	
	/**
	 * Mueve la camara a la ciudad con una animación
	 * @see AnimacionCiudad
	 */
	public void centrarCamara() {
		new AnimacionCiudad(this);
	}
	
	private void generarNombre() {
		String[] listaCiudades = jugador.getNacion().getCiudades();
		int nCiudades = jugador.getCiudades().size() % listaCiudades.length;
		
		this.nombre = "";
		if(jugador.getCiudades().size() >= listaCiudades.length) {
			this.nombre = "Nueva "; 
		}
		this.nombre += listaCiudades[nCiudades];
	}
	
	@Override
	/**
	 * Pinta la ciudad en la pantalla
	 */
	public void pintar(Graphics2D g, int x, int y, int tamX, int tamY) {
		double zoom = jugador.getJuego().getRender().getCamara().getZoom();
		if(zoom > Camara.ZOOM_TEXTURAS) {
			g.setColor(Color.RED);
			if(capital) {
				g.setColor(Color.PINK);
			}
			g.fillRect(x, y, tamX, tamY);
		} else {					
			g.drawImage(textura, x, y, tamX, tamY, null);
							
			int dZoom = (int) (zoom-Camara.ZOOM_TEXTURAS/2)*4;
			int alfa = (int) (255*(dZoom/100.0));
			if(alfa > 255) {
				alfa = 255;
			}
			if(alfa < 0) {
				alfa = 0;
			}
			
			Color color = Color.RED;
			if(capital) {
				color = Color.PINK;
			}
			Color colorTrans = new Color(color.getRed(), color.getGreen(), color.getBlue(), alfa);
			g.setColor(colorTrans);
			g.fillRect(x, y, tamX, tamY);
		}	
		
		int fondoX = (int) (x + tamX * 0.1);
		int fondoY = (int) (y - tamY * 0.3);
		int fondoTamX = (int) (tamX - tamX * 0.2);
		int fondoTamY = (int) (tamY * 0.2);
		int radio = (int) (fondoTamX * 0.2);
		g.setColor(new Color(0, 0, 0, 127));
		g.fillRoundRect(fondoX, fondoY, fondoTamX, fondoTamY, radio, radio);
		
		Font fuente = new Font("Times new roman", Font.BOLD, fondoTamX/6);
		FontMetrics metrics = g.getFontMetrics(fuente);
		int textoX = (int) ((2.0 * fondoX + fondoTamX - metrics.stringWidth(nombre)) / 2.0);
		int textoY = (int) (fondoY + fondoTamY - metrics.getAscent() / 2.0);
		g.setFont(fuente);
		g.setColor(Color.WHITE);
		g.drawString(nombre, textoX, textoY);
	}
	
	/**
	 * 
	 * @return Jugador dueño de la ciudad
	 */
	public Jugador getJugador() {
		return jugador;
	}
	
	/**
	 * 
	 * @return Posición X en el mapa
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * 
	 * @return Posición Y en el mapa
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * 
	 * @return Devuelve 'true' si la ciudad es la capital del jugador
	 */
	public boolean isCapital() {
		return capital;
	}
	
	/**
	 * Convertir la ciudad en capital (Se debe comprobar que no haya capital)
	 * @param capital 'true' si se quiere convertir esta ciudad en capital
	 */
	public void setCapital(boolean capital) {
		if(capital) {
			for(Ciudad c : jugador.getCiudades()) {
				c.setCapital(false);
			}
		}
		this.capital = capital;
	}
	
	/**
	 * 
	 * @return Nombre de la ciudad
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * 
	 * @return Textura de la ciudad
	 */
	public BufferedImage getTextura() {
		return textura;
	}
}
