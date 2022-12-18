package cuatroenraya.modelo;

public class Jugador {
	private String nombre;
	private Ficha colorFichas;
	
	public Jugador(String nombre, Ficha colorFichas) {
		
		setNombre(nombre);
		
		setColorFichas(colorFichas);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		}
		if (nombre.trim().isEmpty()) {
			throw new IllegalArgumentException("ERROR: El nombre no puede estar vacío.");
		}
		this.nombre = nombre;
	}
	public Ficha getColorFichas() {
		return colorFichas;
	}
	public void setColorFichas(Ficha colorFichas) {
		if (colorFichas == null) {
			throw new NullPointerException("ERROR: El color de las fichas no puede ser nulo.");
		}
		this.colorFichas = colorFichas;
	}
	public String toString() {
		
		return String.format("%s (%s)", nombre, colorFichas);
	}
	
}
