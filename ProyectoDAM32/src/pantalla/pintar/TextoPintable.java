package pantalla.pintar;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class TextoPintable implements Pintable {
	private String texto;
	
	public TextoPintable(String texto) {
		this.texto = texto;
	}

	@Override
	public void pintar(Graphics2D g, int x, int y, int tamX, int tamY) {
		Font fuente = new Font("Times new roman", Font.BOLD, 14);
		FontMetrics metrics = g.getFontMetrics(fuente);
		int textoX = (2*x+tamX - metrics.stringWidth(texto))/2;
		int textoY = ((2*y+tamY - metrics.getHeight())/2) + metrics.getAscent();
		
		g.setFont(fuente);
		g.setColor(Color.WHITE);
		g.drawString(texto, textoX, textoY);
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}

}
