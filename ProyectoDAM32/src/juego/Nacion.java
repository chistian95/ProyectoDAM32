package juego;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import pantalla.pintar.TextoPintable;

public enum Nacion {
	EEUU(0, "EEUU", "Donald Trump", "eeuu.png"),
	RUSIA(1, "Rusia", "Vladimir Putin", "rusia.png"),
	COREA(2, "Corea", "Kim Jong-Un", "corea.png"),
	ESPAÑA(3, "España", "Francisco Franco", "españa.png"),
	ALEMANIA(4, "Alemania", "Adolf Hitler", "alemania.png");
	
	private int id;
	private String nombre;
	private String lider;
	private BufferedImage textura;
	private List<TextoPintable> ventajas;
	
	private Nacion(int id, String nombre, String lider, String archivo) {
		this.id = id;
		this.nombre = nombre;
		this.lider = lider;
		try {
			textura = ImageIO.read(new File("src/res/gui/"+archivo));
		} catch (IOException e) {
			System.out.println("Error al cargar la bandera de "+nombre);
		}
		
		ventajas = new ArrayList<TextoPintable>();
		switch(id) {
		case 0:
			ventajas.add(new TextoPintable("+30 Prod. a la generación de infantería"));
			ventajas.add(new TextoPintable("+1 Oro por cada comercio"));
			ventajas.add(new TextoPintable("+4 Fuerza al luchar en territorio amigo"));
			break;
		case 1:
			ventajas.add(new TextoPintable("+1 Petróleo por cada recurso explotado"));
			ventajas.add(new TextoPintable("No hay penalización de movimiento en Tundra"));
			ventajas.add(new TextoPintable("Sin penalización de ataque en unidades dañadas"));
			break;
		case 2:
			ventajas.add(new TextoPintable("+1 Uranio por cada recurso explotado"));
			ventajas.add(new TextoPintable("Sin penalización de felicidad al anexar ciudad"));
			ventajas.add(new TextoPintable("Carro de guerra (Unidad a distancia)"));
			break;
		case 3:
			ventajas.add(new TextoPintable("+20 Carisma a las relaciones con otras naciones"));
			ventajas.add(new TextoPintable("+2 Felicidad por cada recurso de lujo explotado"));
			ventajas.add(new TextoPintable("+50% Oro al saquear"));
			break;
		case 4:
			ventajas.add(new TextoPintable("-1 Oro al mantenimiento de unidades"));
			ventajas.add(new TextoPintable("Panzer (Sustituye al Tanque) +2 Fuerza"));
			ventajas.add(new TextoPintable("+30 Prod. en cada ciudad con Taller"));
			break;
		}
	}
	
	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getLider() {
		return lider;
	}
	
	public BufferedImage getTextura() {
		return textura;
	}
	
	public List<TextoPintable> getVentajas() {
		return ventajas;
	}
	
	@Override
	public String toString() {
		return nombre+": "+lider;
	}
}
