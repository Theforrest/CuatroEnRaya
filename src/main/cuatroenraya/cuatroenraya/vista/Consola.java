package cuatroenraya.vista;

import org.iesalandalus.programacion.utilidades.Entrada;

import cuatroenraya.modelo.Ficha;
import cuatroenraya.modelo.Jugador;


public class Consola {
	private Consola() {
		
	}
	
	public static String leerNombre() {
		String nombre;
		do {
			System.out.printf("%nIntroduce el nobre del jugador: ");
			nombre = Entrada.cadena();
		} while (nombre.trim().isEmpty());
		return nombre;
	}
	public static Ficha elegirColorFichas() {
		int ficha;
		do {
			System.out.printf("Elige el color de tus fichas (0-AZUL, 1-VERDE): ");
			ficha = Entrada.entero();
		} while (ficha < 0 || ficha > 1);
		return Ficha.values()[ficha];
	}
	public static Jugador leerJugador() {
		Jugador jugador;
		System.out.printf("%nIntroduce los datos del PRIMER jugador");
		jugador = new Jugador(leerNombre(), elegirColorFichas());
		return jugador;
	}
	public static Jugador leerJugador(Ficha ficha) {
		if (ficha == null) {
			throw new NullPointerException("ERROR: La ficha no puede ser nula.");
		}
		Jugador jugador;
		System.out.printf("Introduce los datos del SEGUNDO jugador");
		jugador = new Jugador(leerNombre(), ficha);
		return jugador;
	}
	public static Integer leerColumna(Jugador jugador) {
		if (jugador == null) {
			throw new NullPointerException("ERROR: El jugador no puede ser nulo.");
		}
		int columna;
		do {
			System.out.printf("%n%s, introduce la columna en la que deseas introducir la ficha (0 - 6): ", jugador.getNombre());
			columna = Entrada.entero();
		} while (columna < 0 || columna > 6);
		return columna;
	}
}
