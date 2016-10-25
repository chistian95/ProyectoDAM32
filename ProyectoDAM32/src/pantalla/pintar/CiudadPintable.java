package pantalla.pintar;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import juego.jugador.Ciudad;

public class CiudadPintable implements Pintable {
	private Ciudad ciudad;
	
	public CiudadPintable(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public void pintar(Graphics2D g, int x, int y, int tamX, int tamY) {
		g.setColor(Color.PINK);
		if(ciudad.isCapital()) {
			g.setColor(Color.RED);
		}		
		g.fillRect(x, y, tamX, tamY);
		
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
