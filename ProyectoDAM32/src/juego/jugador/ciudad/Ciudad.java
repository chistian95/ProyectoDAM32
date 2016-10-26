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

public class Ciudad implements Pintable {
	private Jugador jugador;
	private int x;
	private int y;
	private boolean capital;
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
	}
	
	public void centrarCamara() {
		new AnimacionCiudad(this);
	}
	
	@Override
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
		
		String nombreNacion = jugador.getNacion().getNombre();
		Font fuente = new Font("Times new roman", Font.BOLD, tamX/2);
		FontMetrics metrics = g.getFontMetrics(fuente);
		int textoX = (2*x+tamX - metrics.stringWidth(nombreNacion))/2;
		int textoY = y+tamY/4 - metrics.getAscent();
		
		int fondoX = (int) (textoX - metrics.stringWidth(nombreNacion) * 0.1);
		int fondoY = (int) (textoY - metrics.getAscent() - metrics.getAscent() * 0.025);
		int tamFondoX = (int) (metrics.stringWidth(nombreNacion) + 2 * metrics.stringWidth(nombreNacion) * 0.1);
		int tamFondoY = (int) (metrics.getAscent() + 2 * metrics.getAscent() * 0.2);
		int radio = (int) (tamFondoX * 0.2);
		g.setColor(new Color(0, 0, 0, 127));
		g.fillRoundRect(fondoX, fondoY, tamFondoX, tamFondoY, radio, radio);
		
		g.setFont(fuente);
		g.setColor(Color.WHITE);
		g.drawString(nombreNacion, textoX, textoY);
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
}
