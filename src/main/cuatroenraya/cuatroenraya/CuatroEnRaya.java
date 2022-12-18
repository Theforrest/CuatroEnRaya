package cuatroenraya;

import javax.naming.OperationNotSupportedException;

import cuatroenraya.modelo.Ficha;
import cuatroenraya.modelo.Jugador;
import cuatroenraya.modelo.Tablero;
import cuatroenraya.vista.Consola;

public class CuatroEnRaya {
	private static final int NUMERO_JUGADORES = 2; 
	private Jugador[] jugador;
	private Tablero tablero;
	
	public CuatroEnRaya(Jugador jugador1, Jugador jugador2) {
		jugador = new Jugador[NUMERO_JUGADORES];
		tablero = new Tablero();
		jugador[0] = jugador1;
		jugador[1] = jugador2;
		
		
	}
	private boolean tirar(Jugador jugador) throws OperationNotSupportedException {
		return 	tablero.introducirFicha(Consola.leerColumna(jugador), jugador.getColorFichas());
	}
	public void jugar() throws OperationNotSupportedException {
		boolean objetivoAlcanzado = false;
		int numJugador = 0;
		do {
			objetivoAlcanzado = tirar(jugador[numJugador]);
			System.out.println(tablero);
			numJugador++;
			if (numJugador > 1) {
				numJugador = 0;
			}
		} while(!objetivoAlcanzado && !tablero.estaLleno());
		if (tablero.estaLleno()) {
			System.out.printf("Empate, el tablero está lleno");
		} else {
			System.out.printf("El ganador es: %s", jugador[numJugador]);
		}
		
	}
}
