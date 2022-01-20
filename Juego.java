package juego;


import javax.swing.JOptionPane;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import java.awt.Color;

public class Juego extends JFrame {
	//LEEEEEEEEEEEEEEEEEEEEEEEEEEEE!!!!!!!!
	
	
	//PRIMERO PURBA EL JUEGO (ten en cuenta que las coordenadas impresas empiezan en 0)
	
	//FALTA: comprobar el fin que se pare el juego si disparos=0 o no hay mas jugadas
	 //revisar metodos privados, set innecesarios y fila x Columna (usar variables que las almacenen)
	//otros cambios menores (q se parezca mas el codigo etc u otras cosas que prefieras que sean distintas)
	
	//Y CASOS QUE PUEDAN FALTAR YA QUE USE EL PRIMER CODIGO QUE ME PASASTE (al final no me pasate el ultimo jajajaja)
	
	//y si quieres que se redimensione (cambie el numero de filas x columna), cambie la dificultad o algo
	
	
	private static final long serialVersionUID = 1L;//para q no salga el warning de arriba
	
	private JPanel contentPane;//panel principal donde va a ir todo contine el panel de los botnes y lo de vidas y puntos
	private JPanel panel;//panel botones
	private JLabel lbPuntos; //muestra puntos
	private JLabel lbVidas; //muestra vidas

	//tuyos
	private int filas,columnas,vidas,puntos;
    private double dificultad;
	private String name;
    private JButton[][] matriz; //la cree para poder controlar la relaion 
    													//entre las posiciones de botones de la lista y la matriz
    													//ALMACENA LOS BOTONES de ahi se sacara la info de los colores y se realizara el juego
    private List<Integer> listaColores; //lista con los colores en orden respecto a los botones										//de forma que a cada boton le asigno la pos correspondiente de la matriz
	
    
    //gets y sets
 
    public JButton[][] getMatriz() {
        return matriz;
    }
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
    private void setFilas(int filas) {
        this.filas = filas;
    }
    public int getColumnas() {
        return columnas;
    }
    private void setColumnas(int columnas) {
        this.columnas = columnas;
    }
    public int getVidas() {
        return vidas;
    }
    private void setVidas(int vidas) {
        this.vidas = vidas;
    }
    public int getPuntos() {
        return puntos;
    }
    private void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
 
    
    //"parte ventana"
	
	/**
	 * Create the frame.
	 */
	public Juego(String name,double dificultad,int filas,int columnas) {
		listaColores = new ArrayList<Integer>();
		setFilas(filas); //los valores de ejemplo creo q los puedes cambiar sin problema, es decir se puede cambiar el tamaño del tablero
		setColumnas(columnas);
		matriz = new JButton[filas][columnas];
		setVidas(3);
		setPuntos(0);
		setDificultad(dificultad);
		setResizable(false);//para que no se pueda modificar el tamaño de la ventana
		setName(name);
		setTitle("Color Tiles");
		
		
		
		
		//****se mete por "defecto al crear elementos y el panel, y al añadir elementos al panel"
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 892, 532);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);//fondo que te permite colocar los elementos adentro del panel donde quieras
		
		//añade el panel botones, vidas y puntos
		contentPane.add(getPanel());
		contentPane.add(getLbPuntos());
		contentPane.add(getLbVidas());
		
		
	}
	
	
	/**
	 * panel donde van a ir los bootnes del juego
	 * @return
	 */
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(0, 0, 880, 445);
			panel.setLayout(new GridLayout(1, 0, 0, 0));//fondo que hace los elementos que añades (botones) ocupen cada uno el mismo espacio
			crearBotonesTablero();//pone los botones creados en el tablero
			
			GeneraTablero();//crea la matriz y la rellena de los botones para poder obtneer las coorde nadas de los botones
			
			MaximasJugadas();//muestra las jugadas posibles
			System.out.println("\n");//basicamente para que haya una separacion cada vez que imprime las jugadas
		}
		return panel;
	}
	/**
	 * muestra las vidas actualizadas
	 * @return
	 */
	private JLabel getLbPuntos() {
		if (lbPuntos == null) {
			lbPuntos = new JLabel("Puntos: "+Integer.toString(puntos));
			lbPuntos.setBounds(20, 467, 74, 29);
		}
		return lbPuntos;
	}
	/**
	 * muestra los puntos actualizados
	 * @return
	 */
	private JLabel getLbVidas() {
		if (lbVidas == null) {
			lbVidas = new JLabel("Vidas: "+Integer.toString(vidas));
			lbVidas.setBounds(173, 470, 74, 22);
		}
		return lbVidas;
	}
	
	//LOGICA
	
	
	//metodos nuevos
	
	/**
	 * Crea los botones del tablero
	 * @param filas2
	 * @param columnas2
	 */
	private void crearBotonesTablero() {
		getPanel().setLayout(new GridLayout(filas, columnas, 4, 0));
		getPanel().removeAll();
		for (int i =0; i<(filas*columnas); i++) {//el 8*12 es que en el ejemplo que ponen por ventana en la explicacion de como debe de ser el trabajo 
										// hay 8 filas y 12 columnas. Puedes poner la medida que quieras
			getPanel().add(nuevoBoton(i));//ese i que es la posicion de la lista que ocupa ese boton, me va a servir para sacar la columna y fila que 
											//le corresponde y poder usar tu metodo
											//por ejemplo la fila 2 y columna 2 si sumas 2+2=4, corresponderia con el boton que es el numero 4 
		}
	}
	
	/**
	 * Crea los botones del juego y les asigna un color 
	 * @param i
	 * @return
	 */
	private JButton nuevoBoton(int i) {
		JButton bt = new JButton("");
		bt.setActionCommand(String.valueOf(i));
		
		Random r=new Random();
		int color= r.nextInt(7);//metodo sacar numero aleatorio para esteboton
								//para ponerle color
		
		ColoresTablero(color, bt);//poner metodo que pone el color sacado al azar a ese boton creado 
		
		
		//addActionListener de ese boton creado, "le da la funcionalidad"
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int posicion = Integer.parseInt(bt.getActionCommand());//coge la posicion que ocupa el boton seleccionado por el usuario 
				
				seleccionar(posicion); //recrea el juego
										//por una parte te actualiza el tablero en cada jugada
										//por la otra te actualiza los puntos y todo eso (vidas y jugadas posibles)
			}

			
		});
		return bt;
	}
	
	
	/**metodo que recrea el juego y cambia los colores segun disparo
	 */
	private void seleccionar(int posicion) {
		
		
		
		Jugada(posicion);//te actualiza los puntos y todo eso (vidas y jugadas posibles)
		representaJuego();//te actualiza el tablero en cada jugada
		MaximasJugadas() ; //te vuelve a mostrar actualizado las jugadas que puedes hacer
		System.out.println("\n");
		getPanel().repaint(); //te repinta casillas revisar si no hace falta
		compruebaFin();
		
	}
	
	/**
	 * muestra actualizados puntos y vidas
	 */
	private void representaJuego(){
		lbPuntos.setText("Puntos: "+Integer.toString(puntos));
		lbVidas.setText("Vidas: "+Integer.toString(vidas));
	}
	
	
	
	//metodos tuyos "con cambios"
	
	/**
	 * devuelve el color de fondo del boton
	 * @param numero
	 * @param b
	 */
    public void ColoresTablero(int numero, JButton b) {
    	JButton bt=b;
        if(numero==0) {
        	bt.setBackground(Color.WHITE);
        }
        else if(numero==1) {
        	bt.setBackground(Color.RED);
        }
        else if (numero==2) {
        	bt.setBackground(Color.BLUE);
        }
        else if (numero==3) {
        	bt.setBackground(Color.GREEN);
        }
        else if(numero==4) {
        	bt.setBackground(Color.PINK);
        }
        else if (numero==5) {
        	bt.setBackground(Color.YELLOW);
        }
        else {
        	bt.setBackground(Color.CYAN);
        }
        listaColores.add(numero);
        
    }
    
    
    /**
     * cambios q hice:
     * no hace falta que compruebe si mete un numero negativo o superior al tamaño de la matriz 
     * ya que directamente no lo va a poder seleccionar
     * 
     * recibe la pos de la lista del boton seleccionado y saca las "coordenadas de matriz"
     * @param i
     * @return
     */
    public JButton[][] Jugada(int posBoton){
    	//coge la pos del boton seleccionado
    	int pb=posBoton;
    	 
    	 
    	 int jf=0;
    	 int jc=0;
    	 
    	 //una vez que tengo el boton ahora recorro la matriz para saber la pos (fila x columna) que le corresponde
    	 // getPanel().getComponent(i) significa cogo el botn en la posicion i del panel de botones
    	 for(int i = 0; i<filas; i++) {
             for(int j = 0; j<columnas;j++) {
            	 if ( matriz[i][j].equals(getPanel().getComponent(pb))) {
            		 //cuando coincida ahora ya tengo las posiciones jc y jf de ese boton
            		 jf=i;
                	 jc=j;
            		 }
            	 }
            	
             }
            		 
    	 
    	 
    	//tu tienes para comparar que sea el color blenco un 0
    	 //aqui no puedes comparar asi, si no mirando que el fondo de ese boton sea o no blanco 
       if(jf==0 && jc==0) { //Casilla 0 0
 		  if(matriz[jf][jc+1].getBackground() == matriz[jf+1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc+1].getBackground()!=Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 				matriz[jf+1][jc].setBackground(Color.WHITE);
 			}
 		  else {
 				int vidas = getVidas();
 				setVidas(vidas-1);
 			}
 	  }
 	  else if(jf==matriz.length && jc==0) { //Casilla ffil 0
 		  if(matriz[jf][jc+1].getBackground() == matriz[jf-1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc+1].getBackground()!=Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 			}
 			else {
 				int vidas = getVidas();
 				setVidas(vidas-1);
 			}
 	  }
 	  else if(jf<matriz.length && jf>0 && jc==0) { //Casillas entre ffil y 0
 		  if(matriz[jf][jc+1].getBackground() == matriz[jf+1][jc].getBackground() && matriz[jf][jc+1].getBackground() == matriz[jf-1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc+1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+5);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 				matriz[jf+1][jc].setBackground(Color.WHITE);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 			}
 		  else if(matriz[jf-1][jc].getBackground() == matriz[jf+1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf-1][jc].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 				matriz[jf+1][jc].setBackground(Color.WHITE);
 			}
 		  else if(matriz[jf][jc+1].getBackground() == matriz[jf+1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc+1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 				matriz[jf+1][jc].setBackground(Color.WHITE);
 			}
 		  else if(matriz[jf][jc+1].getBackground() == matriz[jf+1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE&& matriz[jf][jc+1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 				matriz[jf+1][jc].setBackground(Color.WHITE);
 			}
 		  else if(matriz[jf][jc+1].getBackground() == matriz[jf-1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc+1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 			}
 		  else {
 				int vidas = getVidas();
 				setVidas(vidas-1);
 			}
 	  }
 	  else if(jf==0 && jc==matriz[0].length) { //Casilla 0 fjc
 		  if(matriz[jf][jc-1].getBackground() == matriz[jf-1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc-1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf][jc-1].setBackground(Color.WHITE);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 			}
 		  else {
 				int vidas = getVidas();
 				setVidas(vidas-1);
 			}
 	  }
 	  else if(jf==0 && jc>0 && jc<matriz[0].length) { //Casillas entre 0 0 y 0 fcol
 		  if(matriz[jf+1][jc].getBackground()==matriz[jf][jc-1].getBackground() && matriz[jf+1][jc].getBackground()==matriz[jf][jc+1].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE &&matriz[jf+1][jc].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+5);
 				matriz[jf+1][jc].setBackground(Color.WHITE);
 				matriz[jf][jc-1].setBackground(Color.WHITE);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 			}
 		  else if(matriz[jf][jc-1].getBackground() == matriz[jf][jc+1].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc-1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf][jc-1].setBackground(Color.WHITE);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 			}
 		  else if(matriz[jf][jc-1].getBackground() == matriz[jf+1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc-1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf][jc-1].setBackground(Color.WHITE);
 				matriz[jf+1][jc].setBackground(Color.WHITE);
 			}
 		  else if(matriz[jf][jc+1].getBackground() == matriz[jf+1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc+1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 				matriz[jf+1][jc].setBackground(Color.WHITE);
 			}
 		  else {
 				int vidas = getVidas();
 				setVidas(vidas-1);
 			}
 	  }
 	  else if(jf==matriz.length && jc== matriz[0].length) { //Casilla ffil fcol
 		  if(matriz[jf][jc-1].getBackground() == matriz[jf-1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc-1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf][jc-1].setBackground(Color.WHITE);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 			}
 		  else {
 				int vidas = getVidas();
 				setVidas(vidas-1);
 			}
 	  }
 	  else if(jf==matriz.length-1 && jc>0 && jc<matriz[0].length) { //Casillas entre ffil 0 y ffil fcol
 		  if(matriz[jf-1][jc].getBackground()==matriz[jf][jc-1].getBackground() && matriz[jf-1][jc].getBackground()==matriz[jf][jc+1].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE &&matriz[jf-1][jc].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+5);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 				matriz[jf][jc-1].setBackground(Color.WHITE);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 			}
 		  else if(matriz[jf][jc-1].getBackground() == matriz[jf][jc+1].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc-1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf][jc-1].setBackground(Color.WHITE);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 			}
 		  else if(matriz[jf][jc-1].getBackground() == matriz[jf-1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc-1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf][jc-1].setBackground(Color.WHITE);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 			}
 		  else if(matriz[jf][jc+1].getBackground() == matriz[jf-1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc+1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 			}
 			else {
 				int vidas = getVidas();
 				setVidas(vidas-1);
 			}
 	  }
 	  else if(jf>0 && jf<matriz.length & jc==matriz[0].length-1) { //Casillas entre 0 fcol y ffil fcol
 		  if(matriz[jf][jc-1].getBackground() == matriz[jf+1][jc].getBackground() && matriz[jf][jc-1].getBackground() == matriz[jf-1][jc].getBackground()  && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc-1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+5);
 				matriz[jf][jc-1].setBackground(Color.WHITE);
 				matriz[jf+1][jc].setBackground(Color.WHITE);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 			}
 		  else if(matriz[jf-1][jc].getBackground() == matriz[jf+1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf-1][jc].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 				matriz[jf+1][jc].setBackground(Color.WHITE);
 			}
 		  else if(matriz[jf][jc-1].getBackground() == matriz[jf+1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc-1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf][jc-1].setBackground(Color.WHITE);
 				matriz[jf+1][jc].setBackground(Color.WHITE);
 			}
 		  else if(matriz[jf][jc-1].getBackground() == matriz[jf-1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc-1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf][jc-1].setBackground(Color.WHITE);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 			}
 		  else {
 				int vidas = getVidas();
 				setVidas(vidas-1);
 			}
 	  }
 	  else { //Resto de tablero
 		  if(matriz[jf][jc-1].getBackground() == matriz[jf+1][jc].getBackground() && matriz[jf][jc-1].getBackground() == matriz[jf-1][jc].getBackground() && matriz[jf][jc-1].getBackground() == matriz[jf][jc+1].getBackground()  && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc-1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+10);
 				matriz[jf][jc-1].setBackground(Color.WHITE);
 				matriz[jf+1][jc].setBackground(Color.WHITE);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 			}
 			else if(matriz[jf][jc-1].getBackground() == matriz[jf+1][jc].getBackground() && matriz[jf][jc+1].getBackground() == matriz[jf+1][jc].getBackground() && matriz[jf][jc-1].getBackground() != Color.WHITE && matriz[jf][jc+1].getBackground() != Color.WHITE && matriz[jf][jc].getBackground() == Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+10);
 				matriz[jf][jc-1].setBackground(Color.WHITE);
 				matriz[jf+1][jc].setBackground(Color.WHITE);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 			}
 			else if(matriz[jf][jc-1].getBackground() == matriz[jf][jc+1].getBackground() && matriz[jf+1][jc].getBackground() == matriz[jf-1][jc].getBackground() && matriz[jf][jc-1].getBackground() != Color.WHITE && matriz[jf+1][jc].getBackground() != Color.WHITE && matriz[jf][jc].getBackground() == Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+10);
 				matriz[jf][jc-1].setBackground(Color.WHITE);
 				matriz[jf+1][jc].setBackground(Color.WHITE);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 			}
 			else if(matriz[jf][jc-1].getBackground() == matriz[jf+1][jc].getBackground() && matriz[jf][jc-1].getBackground() == matriz[jf-1][jc].getBackground()  && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc-1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+5);
 				matriz[jf][jc-1].setBackground(Color.WHITE);
 				matriz[jf+1][jc].setBackground(Color.WHITE);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 			}
 			else if(matriz[jf][jc+1].getBackground() == matriz[jf+1][jc].getBackground() && matriz[jf][jc+1].getBackground() == matriz[jf-1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc+1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+5);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 				matriz[jf+1][jc].setBackground(Color.WHITE);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 			}
 			else if(matriz[jf-1][jc].getBackground()==matriz[jf][jc-1].getBackground() && matriz[jf-1][jc].getBackground()==matriz[jf][jc+1].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE &&matriz[jf-1][jc].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+5);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 				matriz[jf][jc-1].setBackground(Color.WHITE);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 			}
 			else if(matriz[jf+1][jc].getBackground()==matriz[jf][jc-1].getBackground() && matriz[jf+1][jc].getBackground()==matriz[jf][jc+1].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE &&matriz[jf+1][jc].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+5);
 				matriz[jf+1][jc].setBackground(Color.WHITE);
 				matriz[jf][jc-1].setBackground(Color.WHITE);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 			}
 			else if(matriz[jf][jc-1].getBackground() == matriz[jf][jc+1].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc-1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf][jc-1].setBackground(Color.WHITE);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 			}
 			else if(matriz[jf-1][jc].getBackground() == matriz[jf+1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf-1][jc].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 				matriz[jf+1][jc].setBackground(Color.WHITE);
 			}
 			else if(matriz[jf][jc-1].getBackground() == matriz[jf+1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc-1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf][jc-1].setBackground(Color.WHITE);
 				matriz[jf+1][jc].setBackground(Color.WHITE);
 			}
 			else if(matriz[jf][jc-1].getBackground() == matriz[jf-1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc-1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf][jc-1].setBackground(Color.WHITE);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 			}
 			else if(matriz[jf][jc+1].getBackground() == matriz[jf+1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc+1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 				matriz[jf+1][jc].setBackground(Color.WHITE);
 			}
 			else if(matriz[jf][jc+1].getBackground() == matriz[jf-1][jc].getBackground() && matriz[jf][jc].getBackground() == Color.WHITE && matriz[jf][jc+1].getBackground() != Color.WHITE) {
 				int puntos=getPuntos();
 				setPuntos(puntos+2);
 				matriz[jf][jc+1].setBackground(Color.WHITE);
 				matriz[jf-1][jc].setBackground(Color.WHITE);
 			}
 			else {
 				int vidas = getVidas();
 				setVidas(vidas-1);
 			}
 	  }
        return matriz;
    }  
    	 
    public int DificultadCorrecta() {
        int cont = 0;
        for(int i = 0; i<getFilas(); i++) {
            for(int j = 0; j<getColumnas();j++) {
                if(matriz[i][j].getBackground()==Color.WHITE) cont++;
            }
        }
        return cont;
    }  	 
	
 /**
  * almacenará el boton que corresponda     	 
  * @return
  */
 public JButton[][] GeneraTablero() {
     
	 //lo dicho 8 filas y 12 columnas como en el ejemplo 
	 int posBoton=0;
	 
	 for(int i = 0; i<filas; i++) {
         for(int j = 0; j<columnas;j++) {
        	 
        	 matriz[i][j]=(JButton) getPanel().getComponent(posBoton);
        	 
        	 posBoton++;
         }
         
     }
	 if(((getColumnas()*getFilas())*getDificultad())%2==0){
         while(DificultadCorrecta()!=(getColumnas()*getFilas())*getDificultad()){
             int i = (int) (Math.random()*getFilas());
             int j = (int) (Math.random()*getColumnas());
             if(DificultadCorrecta()<(getColumnas()*getFilas())*getDificultad()) {
                 if(matriz[i][j].getBackground()!=Color.WHITE) matriz[i][j].setBackground(Color.WHITE);
             }
             if(DificultadCorrecta()>(getColumnas()*getFilas())*getDificultad()) {
                 if(matriz[i][j].getBackground()==Color.WHITE) matriz[i][j].setBackground(Color.BLUE);
             }
         }
     }
     else {
         while(DificultadCorrecta()<(getColumnas()*getFilas())*getDificultad()){
             int i = (int) (Math.random()*getFilas());
             int j = (int) (Math.random()*getColumnas());
             if(matriz[i][j].getBackground()!=Color.WHITE) matriz[i][j].setBackground(Color.WHITE);;
         }
     }
     System.out.printf("Contador blancos = %d \n",DificultadCorrecta());

     return matriz;
 }
 
 /**
  * NOTA: lo mismo dicho de antes, tu almacenas nuemros para controlar los colores
  * numero 0= color blanco
  * aqui tienes que comparar con el color de fondo del boton
  * @return
  */
 public int MaximasJugadas() {
	  int max = 0;
	  for(int i = 0; i<matriz.length; i++) {
	      for(int j = 0; j<matriz[i].length;j++) {

	    	  
	    	  if ((j+1)<matriz[i].length&&(i+1)<matriz.length&&i>0&&j>0) {
	    		  
		    		  if(matriz[i][j+1].getBackground()  == matriz[i+1][j] .getBackground() && matriz[i][j+1] .getBackground() == matriz[i-1][j] .getBackground() && matriz[i][j+1].getBackground()  == matriz[i][j-1].getBackground()  && matriz[i][j].getBackground() == Color.WHITE && matriz[i][j+1].getBackground() != Color.WHITE) {
		                  max += 10;
		                  System.out.printf("\nJugada: %d %d", i,j);
		              }
					  else if(matriz[i][j+1] .getBackground() == matriz[i+1][j].getBackground()  && matriz[i-1][j] .getBackground() == matriz[i][j-1] .getBackground() && matriz[i][j].getBackground() == Color.WHITE && matriz[i][j+1].getBackground() != Color.WHITE&& matriz[i-1][j].getBackground() != Color.WHITE) {
		                  max += 10;
		                  System.out.printf("\nJugada: %d %d", i,j);
		              }
					  else if(matriz[i][j+1].getBackground()  == matriz[i-1][j] .getBackground() && matriz[i+1][j].getBackground()  == matriz[i][j-1] .getBackground() && matriz[i][j].getBackground() == Color.WHITE&& matriz[i][j+1].getBackground() != Color.WHITE && matriz[i-1][j].getBackground() != Color.WHITE) {
		                  max += 10;
		                  System.out.printf("\nJugada: %d %d", i,j);
		              }
	    	  }
	    	  if ((j+1)<matriz[i].length&&(i+1)<matriz.length&&i>0) {
		    		  if(matriz[i][j+1] .getBackground() == matriz[i+1][j].getBackground()  && matriz[i][j+1] .getBackground() == matriz[i-1][j] .getBackground() && matriz[i][j] .getBackground() == Color.WHITE&& matriz[i][j+1].getBackground() != Color.WHITE) {
		                  max += 5;
		                  System.out.printf("\nJugada: %d %d", i,j);
		              }
	    	  }
	    	  if ((j+1)<matriz[i].length&&(i+1)<matriz.length) {
		    		  if(matriz[i][j+1] .getBackground() == matriz[i+1][j].getBackground()  && matriz[i][j].getBackground() == Color.WHITE && matriz[i][j+1].getBackground() != Color.WHITE) {
		                  max += 2;
		                  System.out.printf("\nJugada: %d %d", i,j);
		              }
	    	  }
	    	  
	    	  if ((j+1)<matriz[i].length&&i>0) {
	    			 if(matriz[i][j+1].getBackground()  == matriz[i-1][j].getBackground()  && matriz[i][j] .getBackground() == Color.WHITE&& matriz[i][j+1].getBackground() != Color.WHITE) {
	                     max += 2;
	                     System.out.printf("\nJugada: %d %d", i,j);
	                 } 
	    	  }
	    	  if ((j+1)<matriz[i].length&&j>0) {
		    		  if(matriz[i][j-1] .getBackground() == matriz[i][j+1] .getBackground() && matriz[i][j-1].getBackground() != Color.WHITE&& matriz[i][j].getBackground() == Color.WHITE) {
		                  max += 2;
		                  System.out.printf("\nJugada: %d %d", i,j);
		              }
	 	     }
	    	 if ((i+1)<matriz.length&&j>0&&i>0) {  
		    		  if(matriz[i][j-1] .getBackground() == matriz[i+1][j] .getBackground() && matriz[i][j-1].getBackground()  == matriz[i-1][j].getBackground()  && matriz[i][j].getBackground() == Color.WHITE && matriz[i][j-1].getBackground() != Color.WHITE) {
		                  max += 5;
		                  System.out.printf("\nJugada: %d %d", i,j);
		              }
	    	  }
	    	  
	    	  if ((i+1)<matriz.length&&i>0) {  
		    		  if(matriz[i+1][j] .getBackground() == matriz[i-1][j].getBackground()  && matriz[i+1][j].getBackground() != Color.WHITE&& matriz[i][j].getBackground() == Color.WHITE) {
		                  max += 2;
		                  System.out.printf("\nJugada: %d %d", i,j);
		              }
	    	  }
	    	  if ((i+1)<matriz.length&&j>0) {  
		    		  if(matriz[i][j-1].getBackground()  == matriz[i+1][j] .getBackground() && matriz[i][j] .getBackground() == Color.WHITE&& matriz[i][j-1].getBackground() != Color.WHITE ) {
		                  max += 2;
		                  System.out.printf("\nJugada: %d %d", i,j);
		              }
	    	  }
	    	 if (j>0&&i>0) {
		    		   if(matriz[i][j-1].getBackground()  == matriz[i-1][j] .getBackground() && matriz[i][j].getBackground() == Color.WHITE && matriz[i][j-1].getBackground() != Color.WHITE) {
		                  max += 2;
		                  System.out.printf("\nJugada: %d %d", i,j);
		              }
	    	  }
	            
	      }
	      }
	 
	  return max;
	}
	  
 //queda comprobar el fin que se pare el juego si disparos=0 o no hay mas jugadas
 private void compruebaFin() {
     if (vidas==0) {
         System.out.printf("\n %s LOSE!!! :(\n",getName());
         String salida=String.format("%s LOSE!!! :(",getName());
			JOptionPane.showMessageDialog(null, salida);
         System.exit(0);
     }
     else if (MaximasJugadas()==0){
         System.out.printf("\n %s WIN!!! :)\n",getName());
         String salida=String.format("%s WIN!!! :)",getName());
			JOptionPane.showMessageDialog(null, salida);
         System.exit(0);
     }
 }
 
 //revisar metodos privados, set innecesarios y fila x Columna
 
}
