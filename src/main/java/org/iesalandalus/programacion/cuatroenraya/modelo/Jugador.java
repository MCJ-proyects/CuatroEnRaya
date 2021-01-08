package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Jugador {

	private String nombre;
	
	private Ficha colorFichas;

	public Jugador(String nombre, Ficha colorFichas) {
		super();
		if(nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		} else if (nombre == "") {
			throw new IllegalArgumentException("ERROR: El nombre no puede estar vacío.");
		} else if (nombre == "   ") {
			throw new IllegalArgumentException("ERROR: El nombre no puede estar vacío.");
		} else if(colorFichas == null) {
			throw new NullPointerException("ERROR: El color de las fichas no puede ser nulo.");
		} else {
			setColorFichas(colorFichas);
			setNombre(nombre);
		}
		
	}

	public Ficha getColorFichas() {
		return colorFichas;
	}

	private void setColorFichas(Ficha colorFichas) {
		this.colorFichas = colorFichas;
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre + " (" + colorFichas + ")";
	}
	
	
	
}
