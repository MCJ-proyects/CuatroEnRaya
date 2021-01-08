package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

public class Casilla {
	
	private Ficha ficha;
	
	public Casilla() {
		super();
		ficha = null;
	}

	public Ficha getFicha() {
		return this.ficha;
	}

	public void setFicha(Ficha ficha) throws OperationNotSupportedException {
		if (ficha == null) {
			throw new NullPointerException("ERROR: No se puede poner una ficha nula.");
		}
		
		if(this.ficha != null) {
			throw new OperationNotSupportedException("ERROR: Ya contengo una ficha.");
		} else {
			this.ficha = ficha;
		}
	}
	
	public boolean estaOcupada() {
		if(getFicha() != null) {
			return true;
		} else { 
			return false;
		}
	}

	public String toString() {

				if(getFicha() != null) {
					return String.format("%.1s", ficha);
				} else {
					return " ";
				}

	}

}
