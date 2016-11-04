package juego;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BundleCliente implements Serializable {
	private static final long serialVersionUID = 4716753560272925340L;
	
	private Juego juego;
	private int x;
	private int y;

	public BundleCliente(Juego juego) {
		this.juego = juego;
	}
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		System.out.println("Escribir "+x+", "+y);
		try {
			x = juego.getJugador().getUnidadSel().getX();
			y = juego.getJugador().getUnidadSel().getY();
		} catch(Exception e) {
			System.out.println("Error al refrescar el bundle: "+e.getMessage());
		}
		
		oos.writeInt(x);
		oos.writeInt(y);
	}
	
	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		x = ois.readInt();
		y = ois.readInt();
		System.out.println("Leer "+x+", "+y);
	}
	
	@Override
	public String toString() {
		return "X: "+x+", Y: "+y;
	}
}
