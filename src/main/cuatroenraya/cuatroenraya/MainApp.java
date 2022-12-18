package cuatroenraya;

import javax.naming.OperationNotSupportedException;

import cuatroenraya.modelo.Ficha;
import cuatroenraya.modelo.Jugador;
import cuatroenraya.vista.Consola;

public class MainApp {

	
	public static void main(String[] args) throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		Jugador jugador1;
		Jugador jugador2;
		
		jugador1 = Consola.leerJugador();
		if (jugador1.getColorFichas() == Ficha.AZUL) {
			jugador2 = Consola.leerJugador(Ficha.VERDE);
		} else {
			jugador2 = Consola.leerJugador(Ficha.AZUL);

		}
		CuatroEnRaya cuatroEnRaya = new CuatroEnRaya(jugador1, jugador2);
		cuatroEnRaya.jugar();
	}

}
