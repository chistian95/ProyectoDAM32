package pantalla.pintar;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import juego.jugador.ciudad.Ciudad;
import pantalla.Camara;

public class CiudadPintable implements Pintable {
	private Ciudad ciudad;
	
	public CiudadPintable(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public void pintar(Graphics2D g, int x, int y, int tamX, int tamY) {
		double zoom = ciudad.getJugador().getJuego().getRender().getCamara().getZoom();
		if(zoom > Camara.ZOOM_TEXTURAS) {
			g.setColor(Color.RED);
			if(ciudad.isCapital()) {
				g.setColor(Color.PINK);
			}
			g.fillRect(x, y, tamX, tamY);
		} else {					
			g.drawImage(ciudad.getTextura(), x, y, tamX, tamY, null);
							
			int dZoom = (int) (zoom-Camara.ZOOM_TEXTURAS/2)*4;
			int alfa = (int) (255*(dZoom/100.0));
			if(alfa > 255) {
				alfa = 255;
			}
			if(alfa < 0) {
				alfa = 0;
			}
			
			Color color = Color.RED;
			if(ciudad.isCapital()) {
				color = Color.PINK;
			}
			Color colorTrans = new Color(color.getRed(), color.getGreen(), color.getBlue(), alfa);
			g.setColor(colorTrans);
			g.fillRect(x, y, tamX, tamY);
		}
		
		String nombreNacion = ciudad.getJugador().getNacion().getNombre();
		Font fuente = new Font("Times new roman", Font.BOLD, tamX/2);
		FontMetrics metrics = g.getFontMetrics(fuente);
		int textoX = (2*x+tamX - metrics.stringWidth(nombreNacion))/2;
		int textoY = y+tamY/4 - metrics.getAscent();
		
		g.setFont(fuente);
		g.setColor(Color.WHITE);
		g.drawString(nombreNacion, textoX, textoY);
	}

}
