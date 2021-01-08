package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Casilla {
	
	private Ficha ficha;
	
	public Casilla() {
		super();
		this.ficha = null;
	}

	public Ficha getFicha() {
		return ficha;
	}

	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}
	
	public boolean estaOcupada() {
		if(ficha != null) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		
		if(estaOcupada()) {
			return String.format("%.1s", ficha);
		} else {
			return " ";
		}
	}

}
