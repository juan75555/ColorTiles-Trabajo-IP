package juego;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

public class Main {

	/**º
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String name=
							JOptionPane.showInputDialog("Introduce tu nombre: ");
					String ndificultad=
							JOptionPane.showInputDialog("Introduce la dificultad (0.1-0.9):  ");
					double dificultad = Double.parseDouble(ndificultad);
					while(dificultad<=0 || dificultad>=1) {
						ndificultad=
								JOptionPane.showInputDialog("Introduce la dificultad (0.1-0.9):  ");
						dificultad = Double.parseDouble(ndificultad);
					}
					String nfilas=
							JOptionPane.showInputDialog("Introduce el nuevo numero de filas:  ");
					int filas = Integer.parseInt(nfilas);
					String ncol=
							JOptionPane.showInputDialog("Introduce el nuevo numero de columnas:  ");
					int columnas = Integer.parseInt(ncol);
					while(filas<1) {
						nfilas=
								JOptionPane.showInputDialog("Introduce el nuevo numero de filas:  ");
						filas = Integer.parseInt(nfilas);
					}
					while(columnas<1) {
						ncol=
								JOptionPane.showInputDialog("Introduce el nuevo numero de columnas:  ");
						columnas = Integer.parseInt(ncol);
					}
					Juego frame = new Juego(name,dificultad,filas,columnas);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
