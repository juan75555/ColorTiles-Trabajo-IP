
public class Tablero {
	private int filas,columnas,vidas,puntos;
	private double dificultad;
	private int[][] matriz = new int[filas][columnas];
	public static final String ANSI_BLACK = "\033[30m";
	public static final String ANSI_RED = "\033[31m";
	public static final String ANSI_GREEN = "\033[32m";
	public static final String ANSI_YELLOW = "\033[33m";
	public static final String ANSI_BLUE = "\033[34m";
	public static final String ANSI_PURPLE = "\033[35m";
	public static final String ANSI_CYAN = "\033[36m";
	public static final String ANSI_WHITE = "\033[37m";
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
	public void ColoresTablero(int numero) {
		if(numero==0) {
			System.out.print(ANSI_WHITE+numero+" ");
		}
		else if(numero==1) {
			System.out.print(ANSI_RED+numero+" ");
		}
		else if (numero==2) {
			System.out.print(ANSI_BLUE+numero+" ");
		}
		else if (numero==3) {
			System.out.print(ANSI_GREEN+numero+" ");
		}
		else if(numero==4) {
			System.out.print(ANSI_PURPLE+numero+" ");
		}
		else if (numero==5) {
			System.out.print(ANSI_YELLOW+numero+" ");
		}
		else {
			System.out.print(ANSI_CYAN+numero+" ");
		}
		System.out.print(ANSI_RESET);
	}
	public void ImprimeTablero(int[][] matriz) {
		for(int i = 0; i<getColumnas();i++) {
			System.out.print("---");
		}
		System.out.println("");
		for(int i = 0; i<matriz.length; i++) {
			for(int j = 0; j<matriz.length; j++) {
				ColoresTablero(matriz[i][j]);
			}
			System.out.println("    |"+i);
		}
		System.out.println("");
		for(int i = 0; i<getColumnas();i++) {
			System.out.print("---");
		}
		System.out.println();
		for(int i = 0; i<getColumnas();i++) {
			System.out.print(i+" ");
		}
		System.out.printf("\nVidas: %d Puntos: %d",getVidas(),getPuntos());
	}
	public int MaximasJugadas() {
		int max = 0;
		for(int i = 0; i<matriz.length; i++) {
			for(int j = 0; j<matriz[i].length;j++) {
				try {
					if(matriz[i][j-1] == matriz[i+1][j] && matriz[i][j] == 0 && matriz[i][j-1]!=0 ) {
						max += 5;
					}
					else if(matriz[i][j-1] == matriz[i-1][j] && matriz[i][j] == 0 && matriz[i][j-1]!=0) {
						max += 5;
					}
					else if(matriz[i][j+1] == matriz[i+1][j] && matriz[i][j] == 0 && matriz[i][j+1]!=0) {
						max += 5;
					}
					else if(matriz[i][j+1] == matriz[i-1][j] && matriz[i][j] == 0 && matriz[i][j+1]!=0) {
						max += 5;
					}
				}
				catch(Exception e) {
					try {
						if(matriz[i][j-1] == matriz[i-1][j] && matriz[i][j] == 0 && matriz[i][j-1]!=0) {
							max += 5;
						}
						else if(matriz[i][j+1] == matriz[i+1][j] && matriz[i][j] == 0 && matriz[i][j+1]!=0) {
							max += 5;
						}
						else if(matriz[i][j+1] == matriz[i-1][j] && matriz[i][j] == 0 && matriz[i][j+1]!=0) {
							max += 5;
						}
					}
					catch(Exception x) {
						try {
							if(matriz[i][j+1] == matriz[i+1][j] && matriz[i][j] == 0 && matriz[i][j+1]!=0) {
								max += 5;
							}
							else if(matriz[i][j+1] == matriz[i-1][j] && matriz[i][j] == 0 && matriz[i][j+1]!=0) {
								max += 5;
							}
						}
						catch(Exception y) {
							try {
								if(matriz[i][j+1] == matriz[i-1][j] && matriz[i][j] == 0 && matriz[i][j+1]!=0) {
									max += 5;
								}
							}
							catch(Exception z) {
								j++;
							}
						}
					}
				}
			}
		}
		return max;
	}
	public int[][] Jugada(int jf,int jc){
		try {
			if(matriz[jf][jc-1] == matriz[jf+1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc-1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+5);
				matriz[jf][jc-1]=0;
				matriz[jf+1][jc]=0;
			}
			else if(matriz[jf][jc-1] == matriz[jf-1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc-1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+5);
				matriz[jf][jc-1]=0;
				matriz[jf-1][jc]=0;
			}
			else if(matriz[jf][jc+1] == matriz[jf+1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc+1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+5);
				matriz[jf][jc+1] = 0;
				matriz[jf+1][jc] = 0;
			}
			else if(matriz[jf][jc+1] == matriz[jf-1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc+1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+5);
				matriz[jf][jc+1] = 0;
				matriz[jf-1][jc] = 0;
			}
			else {
				int vidas = getVidas();
				setVidas(vidas-1);
			}
		}
		catch(Exception e) {
			try {
				if(matriz[jf][jc-1] == matriz[jf-1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc-1]!=0) {
					int puntos=getPuntos();
					setPuntos(puntos+5);
					matriz[jf][jc-1]=0;
					matriz[jf-1][jc]=0;
				}
				else if(matriz[jf][jc+1] == matriz[jf+1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc+1]!=0) {
					int puntos=getPuntos();
					setPuntos(puntos+5);
					matriz[jf][jc+1] = 0;
					matriz[jf+1][jc] = 0;
				}
				else if(matriz[jf][jc+1] == matriz[jf-1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc+1]!=0) {
					int puntos=getPuntos();
					setPuntos(puntos+5);
					matriz[jf][jc+1] = 0;
					matriz[jf-1][jc] = 0;
				}
				else {
					int vidas = getVidas();
					setVidas(vidas-1);
				}
			}
			catch(Exception x) {
				try {
					if(matriz[jf][jc+1] == matriz[jf+1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc+1]!=0) {
						int puntos=getPuntos();
						setPuntos(puntos+5);
						matriz[jf][jc+1] = 0;
						matriz[jf+1][jc] = 0;
					}
					else if(matriz[jf][jc+1] == matriz[jf-1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc+1]!=0) {
						int puntos=getPuntos();
						setPuntos(puntos+5);
						matriz[jf][jc+1] = 0;
						matriz[jf-1][jc] = 0;
					}
					else {
						int vidas = getVidas();
						setVidas(vidas-1);
					}
				}
				catch(Exception y) {
					try {
						if(matriz[jf][jc+1] == matriz[jf-1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc+1]!=0) {
							int puntos=getPuntos();
							setPuntos(puntos+5);
							matriz[jf][jc+1] = 0;
							matriz[jf-1][jc] = 0;
						}
						else {
							int vidas = getVidas();
							setVidas(vidas-1);
						}
					}
					catch(Exception z) {
						int vidas = getVidas();
						setVidas(vidas-1);
					}
				}
			}
		}
		return matriz;
	}
	
}
