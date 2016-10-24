package pantalla.pintar;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import juego.Nacion;
import pantalla.Pantalla;
import pantalla.Ventanas;

public class NacionPintable extends JComponent implements Pintable, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	
	private Pantalla pantalla;
	private Nacion nacion;
	private int x;
	private int y;
	private int tamX;
	private int tamY;
	private boolean dentro;
	
	public NacionPintable(Pantalla pantalla, Nacion nacion) {
		this.pantalla = pantalla;
		this.nacion = nacion;
		x = 0;
		y = 0;
		tamX = 0;
		tamY = 0;
		dentro = false;
		
		pantalla.addMouseListener(this);
		pantalla.addMouseMotionListener(this);
	}

	@Override
	public void pintar(Graphics2D g, int x, int y, int tamX, int tamY) {
		this.x = x;
		this.y = y;
		this.tamX = tamX;
		this.tamY = tamY;
		
		if(dentro) {
			g.setColor(Ventanas.COLOR_SELECCIONADO);
			g.fillRect(x, y, tamX, tamY);
		}
		
		int xBandera = x;
		int yBandera = y;
		int tamBanderaX = (int) (tamX * 0.25);
		int tamBanderaY = tamY;
		pintarBandera(g, xBandera, yBandera, tamBanderaX, tamBanderaY);
		
		int xMeta = x + tamBanderaX;
		int yMeta = y;
		int tamMetaX = (int) (tamX * 0.75);
		int tamMetaY = tamY;
		pintarMeta(g, xMeta, yMeta, tamMetaX, tamMetaY);
		
		g.setStroke(new BasicStroke(1));
		g.setColor(Ventanas.COLOR_BORDE);
		g.drawRect(x, y, tamX, tamY);
	}
	
	public void pintarBandera(Graphics2D g, int x, int y, int tamX, int tamY) {		
		int bandX = x + tamX/4;
		g.drawImage(nacion.getTextura(), bandX, (int) (y+tamY*0.1), tamX/2, tamX/2, null);
		
		Font fuente = new Font("Times new roman", Font.BOLD, 14);
		FontMetrics metrics = g.getFontMetrics(fuente);
		int textoX = (2*x+tamX - metrics.stringWidth(nacion.getNombre()))/2;
		int textoY = y+tamY - metrics.getAscent();
		
		g.setFont(fuente);
		g.setColor(Color.WHITE);
		g.drawString(nacion.getNombre(), textoX, textoY);
		
		g.setStroke(new BasicStroke(1));
		g.setColor(Ventanas.COLOR_BORDE);
		g.drawRect(x, y, tamX, tamY);
	}
	
	public void pintarMeta(Graphics2D g, int x, int y, int tamX, int tamY) {
		List<TextoPintable> desc = new ArrayList<TextoPintable>();
		desc.add(new TextoPintable("Líder: "+nacion.getLider()));
		desc.add(new TextoPintable("Ventajas: "));
		
		for(TextoPintable t : nacion.getVentajas()) {
			desc.add(t);
		}
		
		for(int i=0; i<desc.size(); i++) {
			int sTamY = tamY/desc.size();
			int sY = y+sTamY*i;
			
			Pintable obj = desc.get(i);
			obj.pintar(g, x, sY, tamX, sTamY);
		}
		
		g.setStroke(new BasicStroke(1));
		g.setColor(Ventanas.COLOR_BORDE);
		g.drawRect(x, y, tamX, tamY);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Rectangle rec = new Rectangle(x, y, tamX, tamY);
		if(rec.contains(e.getX(), e.getY())) {
			pantalla.getJuego().getJugador().comenzarPartida(nacion);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Rectangle rec = new Rectangle(x, y, tamX, tamY);
		if(rec.contains(e.getX(), e.getY())) {
			dentro = true;
		} else {
			dentro = false;
		}
	}

}
