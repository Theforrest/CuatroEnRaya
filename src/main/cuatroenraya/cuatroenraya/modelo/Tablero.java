package cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

public class Tablero {
	public static final int FILAS = 6;
	public static final int COLUMNAS = 7;
	public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;
	private Casilla[][] casillas;

	public Tablero() {
		casillas = new Casilla[FILAS][COLUMNAS];
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				casillas[i][j] = new Casilla();
				;
			}
		}
	}

	private boolean columnaVacia(int columna) {
		comprobarColumna(columna);
		boolean columnaVacia = true;
		for (int i = 0; i < casillas.length; i++) {
			if (casillas[i][columna].estaOcupada()) {
				columnaVacia = false;
			}
		}
		return columnaVacia;
	}

	public boolean estaVacio() {
		boolean tableroVacio = true;

		for (int i = 0; i < casillas[0].length; i++) {
			if (!columnaVacia(i)) {
				tableroVacio = false;
			}
		}
		return tableroVacio;
	}

	private boolean columnaLlena(int columna) {
		comprobarColumna(columna);
		boolean columnaLlena = true;
		for (int i = 0; i < casillas.length; i++) {
			if (!casillas[i][columna].estaOcupada()) {
				columnaLlena = false;
			}
		}
		return columnaLlena;
	}

	public boolean estaLleno() {
		boolean tableroLleno = true;

		for (int i = 0; i < casillas[0].length; i++) {
			if (!columnaLlena(i)) {
				tableroLleno = false;
			}
		}
		return tableroLleno;
	}

	private void comprobarFicha(Ficha ficha) {
		if (ficha == null) {
			throw new NullPointerException("ERROR: La ficha no puede ser nula.");
		}
	}

	private void comprobarColumna(int columna) {
		if (columna < 0 || columna >= COLUMNAS) {
			throw new IllegalArgumentException("ERROR: Columna incorrecta.");
		}
	}

	private int getPrimeraFilaVacia(int columna) {
		comprobarColumna(columna);
		int filaVacia = 0;

		for (int i = 0; i < casillas.length; i++) {
			if (!casillas[FILAS - i - 1][columna].estaOcupada()) {

				return FILAS - i - 1;
			}
		}
		return filaVacia;
	}

	private boolean objetivoAlcanzado(int fichasIgualesConsecutivas) {
		if (fichasIgualesConsecutivas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
			return true;
		}
		return false;
	}

	private boolean comprobarHorizontal(int fila, Ficha ficha) {
		int seguidas = 0;
		boolean objetivoAlcanzado = false;

		for (int i = 0; i < casillas.length; i++) {
			if (casillas[fila][i].getFicha() == ficha) {
				seguidas++;
			} else {
				seguidas = 0;
			}
			if (objetivoAlcanzado(seguidas)) {
				objetivoAlcanzado = true;
			}
		}
		return objetivoAlcanzado;
	}

	private boolean comprobarVertical(int columna, Ficha ficha) {
		int seguidas = 0;
		boolean objetivoAlcanzado = false;

		for (int i = 0; i < casillas.length; i++) {
			if (casillas[i][columna].getFicha() == ficha) {
				seguidas++;
			} else {
				seguidas = 0;
			}
			if (objetivoAlcanzado(seguidas)) {
				objetivoAlcanzado = true;
			}
		}
		return objetivoAlcanzado;
	}

	private int menor(int fila, int columna) {
		if (fila < columna) {
			return fila;
		} else {
			return columna;
		}
	}

	private boolean comprobarDiagonalNE(int filaSemilla, int columnaSemilla, Ficha ficha) {
		int seguidas = 0;
		int desplazamiento = menor(filaSemilla, columnaSemilla);
		int filaInicial = filaSemilla - desplazamiento;
		int columnaInicial = columnaSemilla - desplazamiento;
		boolean objetivoAlcanzado = false;

		do {
			if (casillas[filaInicial][columnaInicial].getFicha() == ficha) {
				seguidas++;
			} else {
				seguidas = 0;
			}
			if (objetivoAlcanzado(seguidas)) {
				objetivoAlcanzado = true;
			}
			filaInicial++;
			columnaInicial++;
		} while (filaInicial < FILAS && columnaInicial < COLUMNAS);

		return objetivoAlcanzado;
	}

	private boolean comprobarDiagonalNO(int filaSemilla, int columnaSemilla, Ficha ficha) {
		int seguidas = 0;
		int desplazamiento = menor(filaSemilla, COLUMNAS - 1 - columnaSemilla);
		int filaInicial = filaSemilla - desplazamiento;
		int columnaInicial = columnaSemilla + desplazamiento;
		boolean objetivoAlcanzado = false;

		do {
			if (casillas[filaInicial][columnaInicial].getFicha() == ficha) {
				seguidas++;
			} else {
				seguidas = 0;
			}
			if (objetivoAlcanzado(seguidas)) {
				objetivoAlcanzado = true;
			}
			filaInicial++;
			columnaInicial--;
		} while (filaInicial < FILAS && columnaInicial > 0);

		return objetivoAlcanzado;
	}

	private boolean comprobarTirada(int fila, int columna, Ficha ficha) {
		boolean objetivoAlcanzado = false;
		if (comprobarHorizontal(fila, ficha) || comprobarVertical(columna, ficha)
				|| comprobarDiagonalNE(fila, columna, ficha) || comprobarDiagonalNO(fila, columna, ficha)) {
			objetivoAlcanzado = true;
		}
		return objetivoAlcanzado;
	}

	public boolean introducirFicha(int columna, Ficha ficha) throws OperationNotSupportedException {
		comprobarColumna(columna);
		comprobarFicha(ficha);
		if (ficha == null) {
			throw new NullPointerException("ERROR: La ficha no puede ser nula.");
		}
		if (columnaLlena(columna)) {
			throw new OperationNotSupportedException("ERROR: Columna llena.");
		}

		int fila = getPrimeraFilaVacia(columna);
		casillas[fila][columna].setFicha(ficha);

		return comprobarTirada(fila, columna, ficha);
	}

	@Override
	public String toString() {
		StringBuilder cadenaTablero = new StringBuilder();
		for (int i = 0; i < FILAS; i++) {
			cadenaTablero.append('|');
			for (int j = 0; j < COLUMNAS; j++) {
				cadenaTablero.append(casillas[i][j]);
			}
			cadenaTablero.append("|\n");
		}
		cadenaTablero.append(' ');
		for (int j = 0; j < COLUMNAS; j++) {
			cadenaTablero.append('-');
		}
		cadenaTablero.append('\n');
		return cadenaTablero.toString();
	}

}
