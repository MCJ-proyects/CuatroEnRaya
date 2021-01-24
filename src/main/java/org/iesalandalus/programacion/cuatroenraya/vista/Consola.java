package org.iesalandalus.programacion.cuatroenraya.vista;

import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.utilidades.Entrada;


public class Consola {
.
	private Consola() {
		super();
	}

	public static String leerNombre() {
		boolean salir = false;
		String nombre = null;
		while (!salir) {
			System.out.println("Introduce el nombre del jugador: ");
			nombre = Entrada.cadena();
			if(nombre != null && nombre != "") {
				salir = true;
			}
		}
		
		return nombre;
	}
	
	public static Ficha elegirColorFichas() {
		Ficha ficha = null;
		int opcionFicha = 0;
		boolean salir = false;
		while(!salir) {
			System.out.println("Elige el color de tus fichas (0-AZUL, 1-VERDE): ");
			opcionFicha = Entrada.entero();
			if (opcionFicha == 0 || opcionFicha == 1) {
				salir = true;
			}
		}
		
		switch (opcionFicha) {
			case 0:
				ficha = Ficha.AZUL;
				break;
			case 1:
				ficha = Ficha.VERDE;
				break;
		}
		
		return ficha;
		
	}
	
	public static Jugador leerJugador() {
		System.out.println("Introduce los datos del PRIMER jugador");
		String nombre = leerNombre();
		Ficha ficha = elegirColorFichas();
		Jugador jugador = new Jugador(nombre, ficha);
		return jugador;
	}
	
	public static Jugador leerJugador(Ficha ficha) {
		System.out.println("Introduce los datos del SEGUNDO jugador");
		String nombre = leerNombre();
		Jugador jugador = new Jugador(nombre, ficha);
		return jugador;
	}
	
	public static int leerColumna (Jugador jugador) {
		int columna = 0;
		boolean salir = false;
		while(!salir) {
			System.out.println(jugador.getNombre() + ", introduce la columna en la que deseas introducir la ficha (0 - 6): ");
			columna = Entrada.entero();
			if(columna >= 0 && columna <= 6) {
				salir = true;
			}
		}
		
		return columna;
	}
	
}