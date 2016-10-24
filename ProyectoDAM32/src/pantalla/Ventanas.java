package pantalla;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import pantalla.pintar.Pintable;

public class Ventanas {
	private static final Color COLOR_FONDO = new Color(200, 171, 131);
	private static final Color COLOR_BORDE = new Color(205, 104, 48);
	private static final int ANCHO_BORDE = 4;	
	
	public static void pintarVentana(Graphics2D g, Pantalla pantalla, int x, int y, int tamX, int tamY, List<? extends Pintable> objetos) {	
		g.setColor(COLOR_FONDO);
		g.fillRect(x, y, tamX, tamY);
		
		for(int i=0; i<objetos.size(); i++) {
			int sTamY = tamY/objetos.size();
			int sY = y+sTamY*i;
			
			Pintable obj = objetos.get(i);
			obj.pintar(g, x, sY, tamX, sTamY);
		}
		
		g.setStroke(new BasicStroke(ANCHO_BORDE));
		g.setColor(COLOR_BORDE);
		g.drawRect(x, y, tamX, tamY);
	}
	
	public static void pintarVentanaPrincipal(Graphics2D g, Pantalla pantalla, List<? extends Pintable> objetos) {
		int tamX = (int) (pantalla.WIDTH * 0.5);
		int tamY = (int) (pantalla.HEIGHT * 0.75);
		int x = (int) (pantalla.WIDTH/2.0 - tamX/2.0 - ANCHO_BORDE/2.0);
		int y = (int) (pantalla.HEIGHT/2.0 - tamY/2.0 - ANCHO_BORDE/2.0);
		
		pintarVentana(g, pantalla, x, y, tamX, tamY, objetos);
	}
	
	public static void pintarVentanaInferior(Graphics2D g, Pantalla pantalla, List<? extends Pintable> objetos) {
		int tamX = (int) (pantalla.WIDTH * 0.5);
		int tamY = (int) (tamX * 0.25);		
		int x = (int) (pantalla.WIDTH/2.0 - tamX/2.0 - ANCHO_BORDE/2.0);
		int y = (int) (pantalla.HEIGHT - tamY - ANCHO_BORDE/2.0);
		
		pintarVentana(g, pantalla, x, y, tamX, tamY, objetos);
	}
}