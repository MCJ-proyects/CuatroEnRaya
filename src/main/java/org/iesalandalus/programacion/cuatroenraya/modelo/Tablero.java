package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

public class Tablero {

	//arreglado
	
	public static final int FILAS = 6;
	public static final int COLUMNAS = 7;
	public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;
	
	public Casilla[][] tableroArr;

	public Tablero() {
		super();
		tableroArr = new Casilla[FILAS][COLUMNAS];
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				tableroArr [i][j] = new Casilla();
			}
		}
	} 
	
	public boolean estaVacio() {
		boolean estado = true;
		for (int columna=0; columna<COLUMNAS; columna ++) {
			if (!columnaVacia(columna)) {
				estado = false;
				break;
			}
		}
		return estado;
	}

	private boolean columnaVacia(int columna) {
		boolean estado = true;
		for (int fila=0; fila<FILAS; fila ++) {
			if (this.tableroArr[fila][columna].getFicha() != null) {
				estado = false;
				break;
			}
		}
		return estado;
	}
	
	public boolean estaLleno() {
		boolean estado = true;
		for (int columna=0; columna<COLUMNAS; columna ++) {
			if (!columnaLlena(columna)) {
				estado = false;
				break;
			} 
		}
		return estado;
	}

	private boolean columnaLlena(int columna) {
		boolean estado = true;
		for (int fila=0; fila<FILAS; fila ++) {
			if (this.tableroArr[fila][columna].getFicha() == null) {
				estado = false;
				break;
			}
		}
		return estado;
	}

	public boolean introducirFicha (int columna, Ficha ficha) throws OperationNotSupportedException {
		int fila;
		if(ficha == null) {
			throw new NullPointerException("ERROR: La ficha no puede ser nula.");
		}
		if (columnaLlena(columna))
			throw new OperationNotSupportedException("ERROR: Columna llena.");
		else 
			fila = getPrimeraFilaVacia(columna);
		
		if (columna < 0 || columna > COLUMNAS) {
			throw new IllegalArgumentException("ERROR: Columna incorrecta.");
		}

		tableroArr[fila][columna].setFicha(ficha);		
		
		if (comprobarTirada (fila, columna, ficha)) 
			return true;
		else
			return false;
	}

	public void comprobarFicha (Ficha ficha) {
		
		if (ficha == null) {
			throw new NullPointerException("ERROR: La ficha no puede ser nula.");
		}
		
	}
	
	public void comprobarColumna(int columna){
		
		if(columna > COLUMNAS-1 || columna < 0) { 
			throw new NullPointerException("ERROR: Columna incorrecta.");
		}
		
	}
	
	private int getPrimeraFilaVacia(int columna) {
		int fila;
		boolean seguir = true;
		for (fila=0; fila<FILAS && seguir; fila ++) {
			seguir = this.tableroArr[fila][columna].estaOcupada();
			}
		return fila;
		}
	
	private boolean objetivoAlcanzado (int fichasConsecutivas) {
		if(fichasConsecutivas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean comprobarHorizontal (int fila, Ficha ficha) {
		int contadorAzulH = 0;
		int contadorVerdeH = 0;
		Ficha color;
		
		for (int columna=0; columna<COLUMNAS; columna ++) {
			if (tableroArr[fila][columna].getFicha()!=null) {
				if (tableroArr[fila][columna].estaOcupada()) {
					color = tableroArr[fila][columna].getFicha();
					if (color == Ficha.AZUL) {
						contadorVerdeH = 0;
						contadorAzulH ++;
					} else {
						contadorAzulH = 0;
						contadorVerdeH ++;
					}
					
					if (objetivoAlcanzado(contadorAzulH) || objetivoAlcanzado(contadorVerdeH)) {
						return true;
					}			
				}
			}
		}
		return false;
	}
	
	private boolean comprobarVertical (int columna, Ficha ficha) {
		int contadorAzulV = 0;
		int contadorVerdeV = 0;
		Ficha color;
		
		for (int fila=0; fila<FILAS; fila ++) {
			if (tableroArr[fila][columna].getFicha()!=null) {
				if (tableroArr[fila][columna].estaOcupada()) {
					color = tableroArr[fila][columna].getFicha();
					if (color == Ficha.AZUL) {
						contadorVerdeV = 0;
						contadorAzulV ++;
					} else {
						contadorAzulV = 0;
						contadorVerdeV ++;
					}
					
					if (objetivoAlcanzado(contadorAzulV) || objetivoAlcanzado(contadorVerdeV)) {
						return true;
					}			
				}
			}
		}
		return false;
	}
	
	private boolean comprobarDiagonalNE(int fila, int columna, Ficha ficha) {
		int despInicial = menor (fila, columna);
		int filaInicial = fila - despInicial;
		int colInicial = columna - despInicial;
		int contador = 0;
		int colCnt = colInicial;
		for (int filaCnt = filaInicial; filaCnt<FILAS; filaCnt++) {
			if (tableroArr[filaCnt][colCnt].getFicha()!=null) {
				if(ficha == tableroArr[filaCnt][colCnt].getFicha()) {
					contador++;
				} else {
					contador = 0;
				}
				if (objetivoAlcanzado(contador)) {
					return true;
				}
				if (colCnt<COLUMNAS) {
					colCnt ++;
				} else {
					break;
				}
			}
		}
		return false;
	}
	
	private boolean comprobarDiagonalNO(int fila, int columna, Ficha ficha) {
		int despInicial = menor (fila, COLUMNAS -1 - columna);
		int filaInicial = fila - despInicial;
		int colInicial = columna + despInicial;
		int contador = 0;
		int colCnt = colInicial;
		for (int filaCnt = filaInicial; filaCnt<FILAS; filaCnt++) {
			if (tableroArr[filaCnt][colCnt].getFicha()!=null) {
				if(ficha == tableroArr[filaCnt][colCnt].getFicha()) {
					contador++;
				} else {
					contador = 0;
				}
				if (objetivoAlcanzado(contador)) {
					return true;
				}
				if (colCnt<COLUMNAS) {
					colCnt --;
				} else {
					break;
				}
			}
		}
		return false;
	}	
	
	private boolean comprobarTirada (int fila, int columna, Ficha ficha) {
		if (comprobarDiagonalNE(fila, columna, ficha) || comprobarDiagonalNO(fila, columna, ficha) || comprobarHorizontal (fila, ficha) || comprobarVertical (columna, ficha) ) {
			return true;
		} else {
			return false;
		}
	}
	
	private int menor(int enteroA, int enteroB) {
		if (enteroA<enteroB) 
			return enteroA;
		else 
			return enteroB;
	}

	public String toString() {
		
		//"|       |\n|       |\n|       |\n|       |\n|       |\n|       |\n -------\n"
		
		StringBuilder strTablero = new StringBuilder();
		StringBuilder[] strFilas = new StringBuilder[FILAS];
		for (int fila = 0; fila <FILAS; fila++) {
			strFilas[fila] = new StringBuilder("");
		}
		
		StringBuilder strFilaBase = new StringBuilder();
		
		for (int fila = FILAS-1; fila >=0; fila--) {
			strFilas[fila].append("|");
			for (int columna = 0; columna < COLUMNAS; columna++) {
				strFilas[fila].append(tableroArr[fila][columna].toString());
			}
			strFilas[fila].append("|");
			strFilas[fila].append("\n");
			
			strTablero.append(strFilas[fila]);
		}
		
		strFilaBase.append(" ");
		for (int columna = 0; columna <COLUMNAS; columna++) {
			strFilaBase.append("-");
		}
		strFilaBase.append("\n");
		
		strTablero.append(strFilaBase.toString());
		
		System.out.print(strTablero.toString());
		
		return strTablero.toString();
	}


	
}
