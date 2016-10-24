package pantalla.pintar;

import java.awt.Graphics2D;

public interface Pintable {
	public abstract void pintar(Graphics2D g, int x, int y, int tamX, int tamY);
}
