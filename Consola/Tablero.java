public class Tablero {
    private int filas,columnas,vidas,puntos;
    private double dificultad;
    private String name;
    private int[][] matriz = new int[filas][columnas];
    public static final String ANSI_BLACK = "\033[30m";
    public static final String ANSI_RED = "\033[31m";
    public static final String ANSI_GREEN = "\033[32m";
    public static final String ANSI_YELLOW = "\033[33m";
    public static final String ANSI_BLUE = "\033[34m";
    public static final String ANSI_PURPLE = "\033[35m";
    public static final String ANSI_CYAN = "\033[36m";
    public static final String ANSI_PINK = "\033[38;2;255;0;162m"; //ROSY PINK
    public static final String ANSI_ORANGE = "\033[38;2;225;153;0m"; //ORANGE
    public static final String ANSI_WHITE = "\033[37m";
    public static final String ANSI_RESET = "\u001B[0m";
    Tablero() {
        //El constructor debe tener los parámetros oportunos 
        //para inicializar el tablero y el juego
        this.setName("YOU");
        this.setFilas(6);
        this.setColumnas(6);
        this.setDificultad(0.5);
        this.setVidas(3);
        this.setPuntos(0);
        matriz = new int[getFilas()][getColumnas()];
        this.setMatriz(GeneraTablero());
    }
    Tablero(String name,int filas, int columnas,double dificultad) {
        this.setName(name);
        this.setFilas(filas);
        this.setColumnas(columnas);
        this.setDificultad(dificultad);
        this.setVidas(3);
        this.setPuntos(0);
        matriz = new int[getFilas()][getColumnas()];
        this.setMatriz(GeneraTablero());
    }
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
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
        if(dificultad<1 && dificultad>0) this.dificultad = dificultad;
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
    public int DificultadCorrecta() {
        int cont = 0;
        for(int i = 0; i<getFilas(); i++) {
            for(int j = 0; j<getColumnas();j++) {
                if(matriz[i][j]==0) cont++;
            }
        }
        return cont;
    }
    public int[][] GeneraTablero() {
        for(int i = 0; i<getFilas(); i++) {
            for(int j = 0; j<getColumnas();j++) {
                matriz[i][j] = (int) (Math.random()*7);
            }
        }
        if(((getColumnas()*getFilas())*getDificultad())%2==0){
            while(DificultadCorrecta()!=(getColumnas()*getFilas())*getDificultad()){
                int i = (int) (Math.random()*getFilas());
                int j = (int) (Math.random()*getColumnas());
                if(DificultadCorrecta()<(getColumnas()*getFilas())*getDificultad()) {
                    if(matriz[i][j]!=0) matriz[i][j] = 0;
                }
                if(DificultadCorrecta()>(getColumnas()*getFilas())*getDificultad()) {
                    if(matriz[i][j]==0) matriz[i][j] = (int) (Math.random()*6)+1;
                }
            }
        }
        else {
            while(DificultadCorrecta()<(getColumnas()*getFilas())*getDificultad()){
                int i = (int) (Math.random()*getFilas());
                int j = (int) (Math.random()*getColumnas());
                if(matriz[i][j]!=0) matriz[i][j] = 0;
            }
        }
        System.out.printf("Contador blancos = %d \n",DificultadCorrecta());
        return matriz;
    }
    public void ColoresTablero(int numero) {
        if(numero==0) {
            System.out.print(ANSI_WHITE+"- ");
        }
        else if(numero==1) {
            System.out.print(ANSI_RED+"R ");
        }
        else if (numero==2) {
            System.out.print(ANSI_BLUE+"B ");
        }
        else if (numero==3) {
            System.out.print(ANSI_GREEN+"G ");
        }
        else if(numero==4) {
            System.out.print(ANSI_ORANGE+"O ");
        }
        else if (numero==5) {
            System.out.print(ANSI_YELLOW+"Y ");
        }
        else {
            System.out.print(ANSI_PINK+"P ");
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
            System.out.print(i+" ");
        }
        System.out.println("");
        for(int i = 0; i<getColumnas();i++) {
            System.out.print("---");
        }
        System.out.println();
        System.out.printf("\nVidas: %d Puntos: %d",getVidas(),getPuntos());
    }  
  public int MaximasJugadas() {
  int sum = 0;
  int max = 0;
  for(int i = 0; i<matriz.length; i++) {
      for(int j = 0; j<matriz[i].length;j++) {
    	  if ((j+1)<matriz[i].length&&(i+1)<matriz.length&&i>0&&j>0) {
	    		  if(matriz[i][j+1] == matriz[i+1][j] && matriz[i][j+1] == matriz[i-1][j] && matriz[i][j+1] == matriz[i][j-1] && matriz[i][j] == 0 && matriz[i][j+1]!=0) {
	                  max += 10;
	                  sum = 10;
	                  System.out.printf("\nJugada: %d %d Puntos: %d", i,j,sum);
	              }
				  else if(matriz[i][j+1] == matriz[i+1][j] && matriz[i-1][j] == matriz[i][j-1] && matriz[i][j] == 0 && matriz[i][j+1]!=0 && matriz[i-1][j]!=0) {
	                  max += 10;
	                  sum = 10;
	                  System.out.printf("\nJugada: %d %d Puntos: %d", i,j,sum);
	              }
				  else if(matriz[i][j+1] == matriz[i-1][j] && matriz[i+1][j] == matriz[i][j-1] && matriz[i][j] == 0 && matriz[i][j+1]!=0 && matriz[i-1][j]!=0) {
	                  max += 10;
	                  sum = 10;
	                  System.out.printf("\nJugada: %d %d Puntos: %d", i,j,sum);
	              }
    	  }
    	  if ((j+1)<matriz[i].length&&(i+1)<matriz.length&&i>0) {
	    		  if(matriz[i][j+1] == matriz[i+1][j] && matriz[i][j+1] == matriz[i-1][j] && matriz[i][j] == 0 && matriz[i][j+1]!=0) {
	                  max += 5;
	                  sum = 5;
	                  System.out.printf("\nJugada: %d %d Puntos: %d", i,j,sum);
	              }
    	  }
    	  if ((j+1)<matriz[i].length&&(i+1)<matriz.length) {
	    		  if(matriz[i][j+1] == matriz[i+1][j] && matriz[i][j] == 0 && matriz[i][j+1]!=0) {
	                  max += 2;
	                  sum = 2;
	                  System.out.printf("\nJugada: %d %d Puntos: %d", i,j,sum);
	              }
    	  }
    	  
    	  if ((j+1)<matriz[i].length&&i>0) {
    			 if(matriz[i][j+1] == matriz[i-1][j] && matriz[i][j] == 0 && matriz[i][j+1]!=0) {
                     max += 2;
                     sum = 2;
	                 System.out.printf("\nJugada: %d %d Puntos: %d", i,j,sum);
                 } 
    	  }
    	  if ((j+1)<matriz[i].length&&j>0) {
	    		  if(matriz[i][j-1] == matriz[i][j+1] && matriz[i][j-1]!=0 && matriz[i][j] == 0) {
	                  max += 2;
	                  sum = 2;
	                  System.out.printf("\nJugada: %d %d Puntos: %d", i,j,sum);
	              }
 	     }
    	 if ((i+1)<matriz.length&&j>0&&i>0) {  
	    		  if(matriz[i][j-1] == matriz[i+1][j] && matriz[i][j-1] == matriz[i-1][j] && matriz[i][j] == 0 && matriz[i][j-1]!=0) {
	                  max += 5;
	                  sum = 5;
	                  System.out.printf("\nJugada: %d %d Puntos: %d", i,j,sum);
	              }
    	  }
    	  
    	  if ((i+1)<matriz.length&&i>0) {  
	    		  if(matriz[i+1][j] == matriz[i-1][j] && matriz[i+1][j]!=0 && matriz[i][j] == 0) {
	                  max += 2;
	                  sum = 2;
	                  System.out.printf("\nJugada: %d %d Puntos: %d", i,j,sum);
	              }
    	  }
    	  if ((i+1)<matriz.length&&j>0) {  
	    		  if(matriz[i][j-1] == matriz[i+1][j] && matriz[i][j] == 0 && matriz[i][j-1]!=0 ) {
	                  max += 2;
	                  sum = 2;
	                  System.out.printf("\nJugada: %d %d Puntos: %d", i,j,sum);
	              }
    	  }
    	 if (j>0&&i>0) {
	    		   if(matriz[i][j-1] == matriz[i-1][j] && matriz[i][j] == 0 && matriz[i][j-1]!=0) {
	                  max += 2;
	                  sum = 2;
	                  System.out.printf("\nJugada: %d %d Puntos: %d", i,j,sum);
	              }
    	  } 
      	}
      }
  return max;
}
  public int[][] Jugada(int jf,int jc){
	  if(jf==0 && jc==0) { //Casilla 0 0
		  if(matriz[jf][jc+1] == matriz[jf+1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc+1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf][jc+1] = 0;
				matriz[jf+1][jc] = 0;
			}
		  else {
				int vidas = getVidas();
				setVidas(vidas-1);
			}
	  }
	  else if(jf==matriz.length && jc==0) { //Casilla ffil 0
		  if(matriz[jf][jc+1] == matriz[jf-1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc+1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf][jc+1] = 0;
				matriz[jf-1][jc] = 0;
			}
			else {
				int vidas = getVidas();
				setVidas(vidas-1);
			}
	  }
	  else if(jf<matriz.length && jf>0 && jc==0) { //Casillas entre ffil y 0
		  if(matriz[jf][jc+1] == matriz[jf+1][jc] && matriz[jf][jc+1] == matriz[jf-1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc+1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+5);
				matriz[jf][jc+1] = 0;
				matriz[jf+1][jc] = 0;
				matriz[jf-1][jc] = 0;
			}
		  else if(matriz[jf-1][jc] == matriz[jf+1][jc] && matriz[jf][jc] == 0 && matriz[jf-1][jc]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf-1][jc] = 0;
				matriz[jf+1][jc] = 0;
			}
		  else if(matriz[jf][jc+1] == matriz[jf+1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc+1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf][jc+1] = 0;
				matriz[jf+1][jc] = 0;
			}
		  else if(matriz[jf][jc+1] == matriz[jf+1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc+1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf][jc+1] = 0;
				matriz[jf+1][jc] = 0;
			}
		  else if(matriz[jf][jc+1] == matriz[jf-1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc+1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf][jc+1] = 0;
				matriz[jf-1][jc] = 0;
			}
		  else {
				int vidas = getVidas();
				setVidas(vidas-1);
			}
	  }
	  else if(jf==0 && jc==matriz[0].length) { //Casilla 0 fjc
		  if(matriz[jf][jc-1] == matriz[jf-1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc-1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf][jc-1]=0;
				matriz[jf-1][jc]=0;
			}
		  else {
				int vidas = getVidas();
				setVidas(vidas-1);
			}
	  }
	  else if(jf==0 && jc>0 && jc<matriz[0].length) { //Casillas entre 0 0 y 0 fcol
		  if(matriz[jf+1][jc]==matriz[jf][jc-1] && matriz[jf+1][jc]==matriz[jf][jc+1] && matriz[jf][jc]==0 &&matriz[jf+1][jc]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+5);
				matriz[jf+1][jc]=0;
				matriz[jf][jc-1]=0;
				matriz[jf][jc+1]=0;
			}
		  else if(matriz[jf][jc-1] == matriz[jf][jc+1] && matriz[jf][jc] == 0 && matriz[jf][jc-1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf][jc-1] = 0;
				matriz[jf][jc+1] = 0;
			}
		  else if(matriz[jf][jc-1] == matriz[jf+1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc-1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf][jc-1]=0;
				matriz[jf+1][jc]=0;
			}
		  else if(matriz[jf][jc+1] == matriz[jf+1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc+1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf][jc+1] = 0;
				matriz[jf+1][jc] = 0;
			}
		  else {
				int vidas = getVidas();
				setVidas(vidas-1);
			}
	  }
	  else if(jf==matriz.length && jc== matriz[0].length) { //Casilla ffil fcol
		  if(matriz[jf][jc-1] == matriz[jf-1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc-1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf][jc-1]=0;
				matriz[jf-1][jc]=0;
			}
		  else {
				int vidas = getVidas();
				setVidas(vidas-1);
			}
	  }
	  else if(jf==matriz.length-1 && jc>0 && jc<matriz[0].length) { //Casillas entre ffil 0 y ffil fcol
		  if(matriz[jf-1][jc]==matriz[jf][jc-1] && matriz[jf-1][jc]==matriz[jf][jc+1] && matriz[jf][jc]==0 &&matriz[jf-1][jc]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+5);
				matriz[jf-1][jc]=0;
				matriz[jf][jc-1]=0;
				matriz[jf][jc+1]=0;
			}
		  else if(matriz[jf][jc-1] == matriz[jf][jc+1] && matriz[jf][jc] == 0 && matriz[jf][jc-1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf][jc-1] = 0;
				matriz[jf][jc+1] = 0;
			}
		  else if(matriz[jf][jc-1] == matriz[jf-1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc-1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf][jc-1]=0;
				matriz[jf-1][jc]=0;
			}
		  else if(matriz[jf][jc+1] == matriz[jf-1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc+1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf][jc+1] = 0;
				matriz[jf-1][jc] = 0;
			}
			else {
				int vidas = getVidas();
				setVidas(vidas-1);
			}
	  }
	  else if(jf>0 && jf<matriz.length & jc==matriz[0].length-1) { //Casillas entre 0 fcol y ffil fcol
		  if(matriz[jf][jc-1] == matriz[jf+1][jc] && matriz[jf][jc-1] == matriz[jf-1][jc]  && matriz[jf][jc] == 0 && matriz[jf][jc-1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+5);
				matriz[jf][jc-1]=0;
				matriz[jf+1][jc]=0;
				matriz[jf-1][jc]=0;
			}
		  else if(matriz[jf-1][jc] == matriz[jf+1][jc] && matriz[jf][jc] == 0 && matriz[jf-1][jc]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf-1][jc] = 0;
				matriz[jf+1][jc] = 0;
			}
		  else if(matriz[jf][jc-1] == matriz[jf+1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc-1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf][jc-1]=0;
				matriz[jf+1][jc]=0;
			}
		  else if(matriz[jf][jc-1] == matriz[jf-1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc-1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf][jc-1]=0;
				matriz[jf-1][jc]=0;
			}
		  else {
				int vidas = getVidas();
				setVidas(vidas-1);
			}
	  }
	  else { //Resto de tablero
		  if(matriz[jf][jc-1] == matriz[jf+1][jc] && matriz[jf][jc-1] == matriz[jf-1][jc] && matriz[jf][jc-1] == matriz[jf][jc+1]  && matriz[jf][jc] == 0 && matriz[jf][jc-1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+10);
				matriz[jf][jc-1] = 0;
				matriz[jf+1][jc] = 0;
				matriz[jf-1][jc] = 0;
				matriz[jf][jc+1] = 0;
			}
			else if(matriz[jf][jc-1] == matriz[jf+1][jc] && matriz[jf][jc+1] == matriz[jf+1][jc] && matriz[jf][jc-1]!=0 && matriz[jf][jc+1]!=0 && matriz[jf][jc] == 0) {
				int puntos=getPuntos();
				setPuntos(puntos+10);
				matriz[jf][jc-1] = 0;
				matriz[jf+1][jc] = 0;
				matriz[jf-1][jc] = 0;
				matriz[jf][jc+1] = 0;
			}
			else if(matriz[jf][jc-1] == matriz[jf][jc+1] && matriz[jf+1][jc] == matriz[jf-1][jc] && matriz[jf][jc-1]!=0 && matriz[jf+1][jc]!=0 && matriz[jf][jc] == 0) {
				int puntos=getPuntos();
				setPuntos(puntos+10);
				matriz[jf][jc-1] = 0;
				matriz[jf+1][jc] = 0;
				matriz[jf-1][jc] = 0;
				matriz[jf][jc+1] = 0;
			}
			else if(matriz[jf][jc-1] == matriz[jf+1][jc] && matriz[jf][jc-1] == matriz[jf-1][jc]  && matriz[jf][jc] == 0 && matriz[jf][jc-1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+5);
				matriz[jf][jc-1]=0;
				matriz[jf+1][jc]=0;
				matriz[jf-1][jc]=0;
			}
			else if(matriz[jf][jc+1] == matriz[jf+1][jc] && matriz[jf][jc+1] == matriz[jf-1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc+1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+5);
				matriz[jf][jc+1] = 0;
				matriz[jf+1][jc] = 0;
				matriz[jf-1][jc] = 0;
			}
			else if(matriz[jf-1][jc]==matriz[jf][jc-1] && matriz[jf-1][jc]==matriz[jf][jc+1] && matriz[jf][jc]==0 &&matriz[jf-1][jc]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+5);
				matriz[jf-1][jc]=0;
				matriz[jf][jc-1]=0;
				matriz[jf][jc+1]=0;
			}
			else if(matriz[jf+1][jc]==matriz[jf][jc-1] && matriz[jf+1][jc]==matriz[jf][jc+1] && matriz[jf][jc]==0 &&matriz[jf+1][jc]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+5);
				matriz[jf+1][jc]=0;
				matriz[jf][jc-1]=0;
				matriz[jf][jc+1]=0;
			}
			else if(matriz[jf][jc-1] == matriz[jf][jc+1] && matriz[jf][jc] == 0 && matriz[jf][jc-1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf][jc-1] = 0;
				matriz[jf][jc+1] = 0;
			}
			else if(matriz[jf-1][jc] == matriz[jf+1][jc] && matriz[jf][jc] == 0 && matriz[jf-1][jc]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf-1][jc] = 0;
				matriz[jf+1][jc] = 0;
			}
			else if(matriz[jf][jc-1] == matriz[jf+1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc-1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf][jc-1]=0;
				matriz[jf+1][jc]=0;
			}
			else if(matriz[jf][jc-1] == matriz[jf-1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc-1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf][jc-1]=0;
				matriz[jf-1][jc]=0;
			}
			else if(matriz[jf][jc+1] == matriz[jf+1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc+1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf][jc+1] = 0;
				matriz[jf+1][jc] = 0;
			}
			else if(matriz[jf][jc+1] == matriz[jf-1][jc] && matriz[jf][jc] == 0 && matriz[jf][jc+1]!=0) {
				int puntos=getPuntos();
				setPuntos(puntos+2);
				matriz[jf][jc+1] = 0;
				matriz[jf-1][jc] = 0;
			}
			else {
				int vidas = getVidas();
				setVidas(vidas-1);
			}
	  }
		return matriz;
	}
}
