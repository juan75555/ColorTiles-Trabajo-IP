import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tablero extends JPanel  {

	private int filas,columnas,vidas,puntos;
	private int[][] matriz = new int[filas][columnas];
	private String[] colores = {"rojo","verde","azul","amarillo","rosa","naranja"};
	private String color;

	//Constructores
	Tablero() {
		//El constructor debe tener los parámetros oportunos 
		//para inicializar el tablero y el juego
		this.setFilas(6);
		this.setColumnas(6);
		this.setVidas(3);
		this.setPuntos(0);
		for(int i = 0; i<matriz.length; i++) {
			for(int j = 0; j<matriz[i].length;j++) {
				matriz[i][j] = (int) (Math.random()*10);
			}
		}
		// Añadimos el 'escuchador' de ratón
		addMouseListener(new MouseHandler());
	}
	
	//Métodos de la clase que implementan el juego: básicamente hacer una
	//jugada, dibujar el estado del tablero y comprobar si la partida se acabó

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		for(int i = 0; i<colores.length; i++) {
			if(colores[i].equals(this.color)) this.color = color;
		}
	}

	//Método paint
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//Aquí iría el código para pintar el estado del tablero

	}	

	//Clase privada para capturar los eventos del ratón
	private class MouseHandler extends MouseAdapter {
		public void mouseClicked (MouseEvent e) {
			//Mostramos un diálogo con la posición del ratón 
			//para ver un ejemplo de cómo se obtienen las coordenadas
			//donde se produjo el click
			JOptionPane.showMessageDialog(null, String.format("Ratón %d,%d \n",e.getX(),e.getY()));

			//Aquí irían las instrucciones para comprobar si el 
			//click del ratón se produjo en una posición correcta
			//y hacer la jugada correspondiente
			
			//Se pueden llamar a los métodos públicos de la clase

			//Seguramente habrá que repintar el tablero si se realizó
			//una jugada válida
			repaint();
		}
	}
}
