package pantalla;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.List;

public class Ventanas {
	private static final Color COLOR_FONDO = new Color(200, 171, 131);
	private static final Color COLOR_BORDE = new Color(205, 104, 48);
	private static final int ANCHO_BORDE = 4;	
	
	public static void pintarVentana(Graphics2D g, Pantalla pantalla, int x, int y, int tamX, int tamY, String texto) {	
		g.setColor(COLOR_FONDO);
		g.fillRect(x, y, tamX, tamY);
		
		Font fuente = new Font("Times new roman", Font.BOLD, 14);
		FontMetrics metrics = g.getFontMetrics(fuente);
		int textoX = (2*x+tamX - metrics.stringWidth(texto))/2;
		int textoY = ((2*y+tamY - metrics.getHeight())/2) + metrics.getAscent();
		
		g.setFont(fuente);
		g.setColor(Color.WHITE);
		g.drawString(texto, textoX, textoY);
		
		g.setStroke(new BasicStroke(ANCHO_BORDE));
		g.setColor(COLOR_BORDE);
		g.drawRect(x, y, tamX, tamY);
	}
	
	public static void pintarVentanaPrincipal(Graphics2D g, Pantalla pantalla, List<?> objetos) {
		int tamX = (int) (pantalla.WIDTH * 0.5);
		int tamY = (int) (pantalla.HEIGHT * 0.75);
		int x = (int) (pantalla.WIDTH/2.0 - tamX/2.0 - ANCHO_BORDE/2.0);
		int y = (int) (pantalla.HEIGHT/2.0 - tamY/2.0 - ANCHO_BORDE/2.0);
		
		pintarVentana(g, pantalla, x, y, tamX, tamY, "test");
	}
	
	public static void pintarVentanaInferior(Graphics2D g, Pantalla pantalla, String texto) {
		int tamX = (int) (pantalla.WIDTH * 0.5);
		int tamY = (int) (tamX * 0.25);		
		int x = (int) (pantalla.WIDTH/2.0 - tamX/2.0 - ANCHO_BORDE/2.0);
		int y = (int) (pantalla.HEIGHT - tamY - ANCHO_BORDE/2.0);
		
		pintarVentana(g, pantalla, x, y, tamX, tamY, texto);
	}
}
