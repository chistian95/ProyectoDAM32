package juego;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import pantalla.pintar.TextoPintable;

public enum Nacion {
	EEUU(0, "EEUU", "Donald Trump", "eeuu.png", new String[] {
			"Washington", "New York", "Boston", "Philadelphia", "Atlanta",
			"Chicago", "Seatle", "San Francisco", "Los Angeles", "Houston",
			"Protland", "St Louis", "Miami", "Buffalo", "Detroit", "New Orleans",
			"Baltimore", "Denver", "Cincinnati", "Dallas", "Memphis", "Cleveland",
			"Kansas City", "San Diego", "Richmond", "Las Vegas", "Phoenix", 
			"Albuquerque", "Minneapolis", "Oakland"
	}),
	RUSIA(1, "Rusia", "Vladimir Putin", "rusia.png", new String[] {
		"Moscú", "St. Petersburgo", "Novgorod", "Rostov", "Yaroslavl", "Yekaterinburgo",
		"Yakutsk", "Vladivostok", "Smolensk", "Orenburgo", "Krasnoyarsk", "Khabarovsk",
		"Bryanks", "Tver", "Nvosibirsk", "Magadan", "Murmansk", "Chita", "Samara",
		"Arkhangelsk", "Chelyabinsk", "Tobolsk", "Vologda", "Omsk", "Astrakhan",
		"Kursk", "Saratov", "Tula", "Vladimir"
	}),
	COREA(2, "Corea", "Kim Jong-Un", "corea.png", new String[] {
			"Seul", "Busan", "Jenjou", "Daegu", "Pyongyang", "Kaesong", "Suwon",
			"Gwangju", "Gangneung", "Hamhung", "Wonju", "Ulsan", "Changwon", "Andong",
			"Gongju", "Haeju", "Cheongju", "Mokpo", "Dongducheon", "Geoje", "Suncheon",
			"Jinju", "Sangju", "Rason", "Gyeongju", "Chungju", "Sacheon", "Gimje", "Anju"
	}),
	ESPAÑA(3, "España", "Francisco Franco", "españa.png", new String[] {
			"Bilbao", "Madrid", "Barcelona", "San Sebastian", "Sevilla", "Córdoba", "Toledo", 
			"Santiago", "Salamanca", "Murcia", "Valencia", "Zaragoza", "Pamplona", "Vitoria",
			"Santander", "Oviedo", "Jaen", "Logroño", "Valladolid", "Teruel", "Almería", "León",
			"Zamora", "La Coruña", "Lugo", "Alicante", "Cádiz", "Elche", "Burgos", "Vigo"
	}),
	ALEMANIA(4, "Alemania", "Adolf Hitler", "alemania.png", new String[] {
			"Berlín", "Hamburgo", "Munich", "Cologne", "Frankfurt", "Essen", "Dortmund", "Stuttgart",
			"Düsseldorf", "Bremen", "Hannover", "Duisburg", "Leipzig", "Dresden", "Bonn", "Bochum",
			"Bielefeld", "Karlsruhe", "Gelsenkirchen", "Wiesbaden", "Münster", "Rostock", "Chemnitz",
			"Braunschweig", "Halle", "Mönchengladbach", "Kiel", "Wuppertal", "Freiburg", "Hagen"
	});
	
	private int id;
	private String nombre;
	private String lider;
	private BufferedImage textura;
	private String[] ciudades;
	private List<TextoPintable> ventajas;
	
	private Nacion(int id, String nombre, String lider, String archivo, String[] ciudades) {
		this.id = id;
		this.nombre = nombre;
		this.lider = lider;
		this.ciudades = ciudades;
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
	
	public String[] getCiudades() {
		return ciudades;
	}
	
	public List<TextoPintable> getVentajas() {
		return ventajas;
	}
	
	@Override
	public String toString() {
		return nombre+": "+lider;
	}
}
