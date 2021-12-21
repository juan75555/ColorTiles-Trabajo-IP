
public class Tablero {
	private int filas,columnas,vidas,puntos;
	private double dificultad;
	private int[][] matriz = new int[filas][columnas];
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	Tablero() {
		//El constructor debe tener los parámetros oportunos 
		//para inicializar el tablero y el juego
		this.setFilas(6);
		this.setColumnas(6);
		this.setDificultad(0.6);
		this.setVidas(3);
		this.setPuntos(0);
		matriz = new int[getFilas()][getColumnas()];
		this.setMatriz(GeneraTablero());
	}
	
	public int[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(int[][] matriz) {
		this.matriz=matriz;
	}

	public double getDificultad() {
		return dificultad;
	}

	public void setDificultad(double dificultad) {
		this.dificultad = dificultad;
	}

	public int getFilas() {
		return filas;
	}
	public void setFilas(int filas) {
		this.filas = filas;
	}
	public int getColumnas() {
		return columnas;
	}
	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}
	public int getVidas() {
		return vidas;
	}
	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public int[][] GeneraTablero() {
		for(int i = 0; i<getFilas(); i++) {
			for(int j = 0; j<getColumnas();j++) {
				matriz[i][j] = (int) (Math.random()*7);
			}
		}
		return matriz;
	}
	public void ImprimeTablero(int[][] matriz) {
		for(int i = 0; i<getColumnas();i++) {
			System.out.print("-");
		}
		for(int i = 0; i<matriz.length; i++) {
			System.out.println();
			for(int j = 0; j<matriz.length; j++) {
				System.out.print(matriz[i][j]+" ");
			}
		}
		System.out.println("");
		for(int i = 0; i<getColumnas();i++) {
			System.out.print("-");
		}
		System.out.printf("\nVidas: %d Puntos: %d",getVidas(),getPuntos());
	}
	
}
