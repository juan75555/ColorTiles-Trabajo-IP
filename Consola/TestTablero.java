import java.util.Scanner;
public class TestTablero {
	public static void Juego(Tablero t,int[][] tablero) {
		Scanner teclado = new Scanner(System.in);
		while(t.getVidas()>0 && t.getPuntos()<6) {
			System.out.print("\nIntroduce tu jugada (fila, columna): ");
			int jf = teclado.nextInt();
			int jc = teclado.nextInt();
			t.Jugada(jf, jc);
			t.ImprimeTablero(tablero);
		}
		if (t.getVidas() == 0) {
			System.out.print("\n\033[31m YOU LOSE!!! :(");
		}
		if (t.getPuntos() >5) {
			System.out.print("\n\033[32m YOU WIN!!! :)");
		}
	}
	public static void main(String[] args) {
		Tablero t = new Tablero();
		int[][] tablero = t.GeneraTablero();
		t.ImprimeTablero(tablero);
		Juego(t,tablero);
	}

}
