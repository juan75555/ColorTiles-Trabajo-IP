import java.util.Scanner;
public class Main {
	public static void Juego(Tablero t,int[][] tablero) {
		Scanner teclado = new Scanner(System.in);
		System.out.print("\nMaximos Puntos Posibles: "+t.MaximasJugadas()+"\n");
		while(t.getVidas()>0 && t.MaximasJugadas()>0) {
			System.out.print("\nIntroduce tu jugada (fila, columna): ");
			int jf = teclado.nextInt();
			int jc = teclado.nextInt();
			t.Jugada(jf, jc);
			t.ImprimeTablero(tablero);
			System.out.print("\nMaximos Puntos Posibles: "+t.MaximasJugadas()+"\n");
		}
		if (t.getVidas() == 0) {
			System.out.printf("\n\033[31m %s LOSE!!! :(",t.getName());
			teclado.close();
		}
		if (t.MaximasJugadas()==0) {
			System.out.printf("\n\033[32m %s WIN!!! :)",t.getName());
			teclado.close();
		}
	}
	public static void main(String[] args) {
		Tablero t = new Tablero();
		Scanner teclado = new Scanner(System.in);
		String name = "YOU";
		System.out.print("Deseas jugar como invitado? (S/N): ");
		String op = teclado.next();
		while(!(op.equals("S") || op.equals("N"))) {
			System.out.print("Deseas jugar como invitado? (S/N): ");
			op = teclado.next();
		}
		if(op.equals("N")) {
			System.out.print("Introduce tu nombre: ");
			name = teclado.next();
			t.setName(name);
		}
		System.out.printf("El tamaño del tablero actualmente es %d x %d.\n Deseas cambiarlo?(S/N): ", t.getFilas(),t.getColumnas());
		op = teclado.next();
		while(!(op.equals("S") || op.equals("N"))) {
			System.out.print("Deseas jugar como invitado? (S/N): ");
			op = teclado.next();
		}
		if(op.equals("S")) {
			System.out.print("Introduce el nuevo numero de filas: ");
			int filas = teclado.nextInt();
			System.out.print("Introduce el nuevo numero de columnas: ");
			int columnas = teclado.nextInt();
			while(filas<1) {
				System.out.printf("\nFilas introducidas %d debe ser mayor que 0.",filas);
				System.out.print("\nIntroduce el nuevo numero de filas: ");
				filas = teclado.nextInt();
			}
			while(columnas<1) {
				System.out.printf("\nColumnas introducidas %d debe ser mayor que 0.",columnas);
				System.out.print("\nIntroduce el nuevo numero de columnas: ");
				columnas = teclado.nextInt();
			}
			t = new Tablero(name,filas,columnas);
		}
		int[][] tablero = t.GeneraTablero();
		t.ImprimeTablero(tablero);
		Juego(t,tablero);
		teclado.close();
	}

}
