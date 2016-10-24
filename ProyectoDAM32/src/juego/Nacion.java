package juego;

public enum Nacion {
	AMERICA(0, "America", "Theodore Roosevelt"),
	ARABIA(1, "Arabia", "Saladin"),
	CHINA(2, "China", "Quin Shi Huang"),
	INGLATERRA(3, "Inglaterra", "Reina Victoria"),
	ALEMANIA(4, "Alemania", "Frederick Barbarossa");
	
	private int id;
	private String nombre;
	private String lider;
	
	private Nacion(int id, String nombre, String lider) {
		this.id = id;
		this.nombre = nombre;
		this.lider = lider;
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
	
	@Override
	public String toString() {
		return nombre+": "+lider;
	}
}
