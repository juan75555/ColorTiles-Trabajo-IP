import java.util.Scanner;
public class Main {
	public static void Juego(Tablero t,int[][] tablero) {
		Scanner teclado = new Scanner(System.in);
		System.out.print("\nMaximos Puntos Posibles: "+t.MaximasJugadas()+"\n");
		while(t.getVidas()>0 && t.getPuntos()<t.MaximasJugadas()) {
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
		if (t.getPuntos() >=t.MaximasJugadas()) {
			System.out.printf("\n\033[32m %s WIN!!! :)",t.getName());
			teclado.close();
		}
	}
	public static void main(String[] args) {
		Tablero t = new Tablero();
		Scanner teclado = new Scanner(System.in);
		System.out.print("Deseas jugar como invitado? (S/N): ");
		String op = teclado.next();
		while(!(op.equals("S") || op.equals("N"))) {
			System.out.print("Deseas jugar como invitado? (S/N): ");
			op = teclado.next();
		}
		if(op.equals("N")) {
			System.out.print("Introduce tu nombre: ");
			String name = teclado.next();
			t.setName(name);
		}
		int[][] tablero = t.GeneraTablero();
		t.ImprimeTablero(tablero);
		Juego(t,tablero);
		teclado.close();
	}

}
