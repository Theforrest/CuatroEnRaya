package cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

public class Casilla {
	private Ficha ficha;

	public Casilla() {
		ficha = null;
	}
	
	public Ficha getFicha() {
		return ficha;
	}

	public void setFicha(Ficha ficha) throws OperationNotSupportedException {
		if (ficha == null) {
			throw new NullPointerException("ERROR: No se puede poner una ficha nula.");
		}
		if (this.ficha != null) {
			throw new OperationNotSupportedException("ERROR: Ya contengo una ficha.");
		}
		this.ficha = ficha;
	}
	
	public boolean estaOcupada() {
		if (ficha == null) {
			return false;
		}
			return true;
	}

	@Override
	public String toString() {
		if (ficha == null) {
			return String.format(" ");
		}
		return String.format("%.1s", ficha);
	}
}
