package pantalla.pintar;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

import juego.Nacion;
import pantalla.Pantalla;

public class NacionPintable extends JComponent implements Pintable, MouseListener {
	private static final long serialVersionUID = 1L;
	
	private Nacion nacion;
	
	public NacionPintable(Pantalla pantalla, Nacion nacion) {
		this.nacion = nacion;
		
		pantalla.addMouseListener(this);
	}

	@Override
	public void pintar(Graphics2D g, int x, int y, int tamX, int tamY) {
		Font fuente = new Font("Times new roman", Font.BOLD, 14);
		FontMetrics metrics = g.getFontMetrics(fuente);
		int textoX = (2*x+tamX - metrics.stringWidth(nacion.getNombre()))/2;
		int textoY = ((2*y+tamY - metrics.getHeight())/2) + metrics.getAscent();
		
		g.setFont(fuente);
		g.setColor(Color.WHITE);
		g.drawString(nacion.getNombre(), textoX, textoY);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(nacion.getNombre());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
