import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class VideoJuego8 {
	static Soldado[][] tabla = new Soldado[10][10];
	static Scanner sc = new Scanner(System.in);

	private static ArrayList<ArrayList<Soldado>> ejercitos = new ArrayList<ArrayList<Soldado>>();

	public static void main(String[] args) {
		ejercitos.add(new ArrayList<Soldado>());
		ejercitos.add(new ArrayList<Soldado>());

		limpiar();
		char[] simbolosEjercitos = { 'A', 'B' };
		llenarEjercitos(simbolosEjercitos);
		mostrarEjercito(ejercitos.get(0));
		System.out.println("-------------");
		mostrarEjercito(ejercitos.get(1));

		mostrarMenu();
	}

	public static void mostrarMenu() {
		System.out.println("Escoja un numero");
		System.out.println("1.- Empezar a jugar\n2.- Gestionar Ejercito\n3.- Salir");
		System.out.print("=> : ");

		int opcion = sc.nextInt();

		if (opcion == 1) {
			inicioJuego();
		} else if (opcion == 2) {
			mostrarSubMenu();
		} else {
			System.out.println("Gracias por ingresar al juego ...");
			System.exit(1);
		}

	}

	public static void mostrarSubMenu() {
		System.out.println("Escoja un numero");
		System.out.println(
				"1.- Crear Soldado\n2.- Eliminar Soldao\n3.- Clonar Soldado\n4.- Modificar Soldado \n5.- Comparar Soldaos\n6.- Intercambiar Soldados\n7.- Ver Soldado\n8.- Ver Ejercito\n9.- Sumar Niveles\n10.- Jugar\n11.- Volver");
		System.out.print("=> : ");

		int opcion = sc.nextInt();
		switch (opcion) {
			case 1: {
				crearSoldado();
				break;
			}
			case 2: {
				eliminarSoldado();
				break;
			}
			case 3: {
				clonarSoldado();
				break;
			}
			case 10: {
				inicioJuego();
				break;
			}

			case 11: {
				mostrarMenu();
				break;
			}

			default:
				System.out.println("Opcion no valida, vuelve a intentarlo");
				mostrarSubMenu();

		}
	}

	public static void crearSoldado() {

		System.out.print("A que ejercito ira el soldado (A/B): ");
		String simboloEjercito = sc.next();

		if (simboloEjercito.equalsIgnoreCase("A")) {
			procesoCreaSoldado(0, simboloEjercito);
		} else {
			procesoCreaSoldado(1, simboloEjercito);
		}

		mostrarSubMenu();

	}

	public static void procesoCreaSoldado(int num, String simboloEjercito) {
		if (ejercitos.get(num).size() >= 10) {
			System.out.println("Lo siento, NO PUEDES AGREGAR, sobrepasas las unidades por ejercito");
		} else {
			ejercitos.get(num).add(crearNuevoSoldado(simboloEjercito));
			actualizarTabla();
			System.out.println("Soldado añadido con EXITO");

		}
	}

	public static Soldado crearNuevoSoldado(String simbolo) {
		System.out.print("Ingrese el nombre para el soldado: \n");
		String name = sc.next();
		System.out.print("Ingrese VIDA ACTUAL: ");
		int vidaActual = sc.nextInt();
		System.out.print("Ingrese NIVEL DE ATAQUE : ");
		int nivelAtaque = sc.nextInt();
		System.out.print("Ingrese NIVEL DE DEFENSA: ");
		int nivelDefensa = sc.nextInt();
		Soldado sol = new Soldado(name, vidaActual, nivelAtaque, nivelDefensa, simbolo.charAt(0));
		System.out.println("Creando soldado ... ");

		return sol;
	}

	public static void eliminarSoldado() {

		System.out.print("A que ejercito ira el soldado (A/B): ");
		String simboloEjercito = sc.next();
		if (simboloEjercito.equalsIgnoreCase("A")) {
			procesoEliminar(0, simboloEjercito);

		} else {
			procesoEliminar(1, simboloEjercito);
		}
		mostrarSubMenu();
	}

	public static void procesoEliminar(int num, String simboloEjercito) {
		if (ejercitos.get(num).size() <= 1) {
			System.out.println("Lo siento, no puedes eliminar, TE QUEDARIAS SIN EJERCITO");

		} else {
			System.out.println("Debera ingresar el ID del soldado en el ejercito");
			System.out.print("Desea ver el ejercito " + simboloEjercito + " (S/N): ");
			char verEjercito = sc.next().toUpperCase().charAt(0);
			if (verEjercito == 'S') {
				mostrarEjercito(ejercitos.get(num));
			}
			System.out.print("Ingrese el ID del soldado: ");

			int id = sc.nextInt();

			int indice = obtenerIndice(id, ejercitos.get(num));

			if (indice == -1) {
				System.out.println("ID no encontrado");

			} else {

				ejercitos.get(num).remove(indice);
				actualizarTabla();
				System.out.println("Soldado BORRADO CON EXITO");
			}
		}

	}

	public static int obtenerIndice(int id, ArrayList<Soldado> ejercito) {
		for (int i = 0; i < ejercito.size(); i++) {

			if (ejercito.get(i).getId() == id)
				return i;
		}
		return -1;
	}

	public static void clonarSoldado() {
		System.out.print("De que ejercito clanara el soldado(A/B): ");
		String simboloEjercito = sc.next();
		if (simboloEjercito.equalsIgnoreCase("A")) {
			procesoClonar(0, simboloEjercito);

		} else {
			procesoClonar(1, simboloEjercito);
		}
		mostrarSubMenu();
	}

	public static void procesoClonar(int num, String simboloEjercito) {
		Random rd = new Random();
		if (ejercitos.get(num).size() >= 10) {
			System.out.println("Lo siento, NO PUEDES CLONAR, sobrepasas las unidades por ejercito");

		} else {
			System.out.println("Debera ingresar el ID del soldado en el ejercito");
			System.out.print("Desea ver el ejercito " + simboloEjercito + " (S/N): ");
			char verEjercito = sc.next().toUpperCase().charAt(0);
			if (verEjercito == 'S') {
				mostrarEjercito(ejercitos.get(num));
			}
			System.out.print("Ingrese el ID del soldado: ");

			int id = sc.nextInt();

			int indice = obtenerIndice(id, ejercitos.get(num));

			if (indice == -1) {
				System.out.println("ID no encontrado");

			} else {
				int fila;
				int columna;
				do {
					fila = rd.nextInt(10);
					columna = rd.nextInt(10);
				} while (!(comprobarEspacio(fila, columna))); // Vemos si hay un objeto acupando el lugar

				Soldado clonado = ejercitos.get(num).get(indice).clonar(fila, columna);

				ejercitos.get(num).add(clonado);
				System.out.println("Soldado CLONADO CON EXITO");
			}
		}

	}

	public static void actualizarTabla(Soldado escogido, Soldado ganador, int[] valores) {
		tabla[escogido.getFila()][escogido.getColumna()] = null;
		tabla[valores[0]][valores[1]] = ganador;
		int fila = valores[0];
		int columna = valores[1];
		ganador.setFila(fila);
		ganador.setColumna(columna);
	}
	// ------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------------------------------

	public static void inicioJuego() {
		mostrarEjercito(ejercitos.get(0));
		System.out.println("B-------------");
		mostrarEjercito(ejercitos.get(1));
		mostrarTabla();

		char turno = 'A';

		while (continuar()) {
			System.out.println("Turno del ejercito " + turno);

			Soldado escogido = escogerSoldado(turno);
			Soldado ganador = escogido;
			Soldado perdedor = null;

			int[] valores = preguntarPosicion(turno); // fila, columna, (0=libre, 1 = lucha)

			if (valores[2] == 1) {// Batalla
				ganador = Soldado.batallarGanador(escogido, tabla[valores[0]][valores[1]]);
				perdedor = Soldado.batallarPerdedor(escogido, tabla[valores[0]][valores[1]], ganador);

			}

			int actual = ganador.getVidaActual();

			ganador.setVidaActual(actual + 1);
			actualizarEjercitos(perdedor);

			actualizarTabla(escogido, ganador, valores);

			mostrarTabla();

			turno = cambiarTurno(turno);

		}

		mostrarGanador();

	}

	public static void actualizarEjercitos(Soldado perdedor) {
		if (perdedor != null) {
			if (perdedor.getSimbolo() == 'A') {
				ejercitos.get(0).remove(perdedor);
			} else {
				ejercitos.get(1).remove(perdedor);
			}
		}
	}

	public static void actualizarTabla() {
		limpiarTabla();

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < ejercitos.get(i).size(); j++) {
				Soldado tem = ejercitos.get(i).get(j);
				tabla[tem.getFila()][tem.getColumna()] = tem;
			}
		}

	}

	public static int[] preguntarPosicion(char turno) {

		System.out.println("Ingrese la coordenada para mover al soldado: ");
		System.out.print("fila: ");
		int fila = sc.nextInt() - 1;
		System.out.print("Columna: ");
		int columna = sc.nextInt() - 1;

		if ((fila < 0 || fila > 9) || (columna < 0 || columna > 9)) {
			System.out.println("Posicion fuera del tablero");
		} else {
			if (tabla[fila][columna] == null) {
				int[] posiciones = { fila, columna, 0 };
				return posiciones;
			} else {
				if (tabla[fila][columna].getSimbolo() == turno) {
					System.out.println("la posicion esta ocupada por uno de tus soldados");
				} else {
					int[] posiciones = { fila, columna, 1 };
					return posiciones;
				}
			}
		}
		System.out.println("intentelo nuevamente");

		return preguntarPosicion(turno);
	}

	public static boolean preguntar() {
		System.out.print("\nDesea simulr otra batalla (s/n) : ");
		String respuesta = sc.next();

		if (respuesta.equalsIgnoreCase("n")) {
			return false;
		}
		return true;
	}

	public static boolean continuar() {

		if (ejercitos.get(0).size() == 0 || ejercitos.get(1).size() == 0) {
			return false;
		}
		return true;

	}

	public static void mostrarGanador() {
		System.out.println("FIN DE LA BATALLA");
		if (ejercitos.get(0).size() == 0) {
			System.out.println("El ganador en el ejercito " + ejercitos.get(1).get(0).getSimbolo());
		} else {
			System.out.println("El ganador en el ejercito " + ejercitos.get(0).get(0).getSimbolo());
		}
	}

	public static Soldado escogerSoldado(char turno) {

		System.out.println("Ingrese la coordenada del soldado que desea mover: ");
		System.out.print("fila: ");
		int fila = sc.nextInt() - 1;
		System.out.print("Columna: ");
		int columna = sc.nextInt() - 1;

		if ((fila < 0 || fila > 9) || (columna < 0 || columna > 9)) {
			System.out.println("Posicion fuera del tablero");
		} else {
			if (tabla[fila][columna] == null) {
				System.out.println("No hay un soldado en esa posicion");
			} else if (tabla[fila][columna].getSimbolo() == turno) {
				return tabla[fila][columna];
			} else {
				System.out.println("El soldado no pertenece a su ejercito");
			}
		}
		System.out.println("Intente nuevamente");
		return escogerSoldado(turno);
	}

	public static char cambiarTurno(char turno) {
		if (turno == 'A') {
			return 'B';
		}
		return 'A';
	}

	public static void llenarEjercitos(char[] simbolos) {
		Random rd = new Random();

		for (int i = 0; i < 2; i++) {
			int fila, columna;
			int num = rd.nextInt(10) + 1;

			for (int j = 0; j < num; j++) {
				do {
					fila = rd.nextInt(10);
					columna = rd.nextInt(10);
				} while (!(comprobarEspacio(fila, columna))); // Vemos si hay un objeto acupando el lugar

				String name = "Soldado" + i + j;
				int vida = rd.nextInt(5) + 1;

				Soldado sol = new Soldado(name, vida, fila, columna, simbolos[i]);
				ejercitos.get(i).add(sol);
				tabla[fila][columna] = sol; // LLenamos en la tabla
			}
		}

	}

	public static boolean comprobarEspacio(int fila, int columna) {
		return (tabla[fila][columna] == null);
	}

	public static void mostrarTabla() {
		System.out.println();

		for (int j = 1; j <= tabla.length; j++) {
			System.out.print(" " + j);
		}
		System.out.println();

		for (int j = 0; j < tabla.length; j++) {
			System.out.print(" _");
		}
		System.out.println();

		for (int i = 0; i < tabla.length; i++) {
			for (int j = 0; j < tabla[i].length; j++) {
				System.out.print("|");
				if (tabla[i][j] == null) {
					System.out.print("_");
				} else {
					System.out.print(tabla[i][j].getSimbolo());
				}
			}

			System.out.println("| " + (i + 1));
		}
	}

	public static void limpiar() {
		limpiarTabla();
		limpiarEjercitos();
	}

	public static void limpiarTabla() {
		for (int i = 0; i < tabla.length; i++) {
			for (int j = 0; j < tabla[i].length; j++) {
				tabla[i][j] = null;
			}
		}

	}

	public static void limpiarEjercitos() {
		ejercitos.get(0).clear();
		ejercitos.get(1).clear();
	}

	// Metodos de lab anteriores-----------------------------------------------
	public static Soldado mayorVida(ArrayList<Soldado> ejercito) {
		int mayor = 0;
		Soldado tem = null;
		for (Soldado sol : ejercito) {

			if (sol.getNivelVida() > mayor) {
				mayor = sol.getNivelVida();
				tem = sol;
			}
		}

		return tem;
	}

	public static double promedioVida(ArrayList<Soldado> ejercito) {
		int suma = 0;

		for (Soldado sol : ejercito) {
			suma += sol.getNivelVida();

		}

		return Math.round(((double) (suma) / ejercito.size()) * 10) / 10.0;

	}

	// Metodos de ordenamineto

	public static void mostrarEjercito(ArrayList<Soldado> ejercito) {
		for (Soldado item : ejercito) {
			System.out.println(item);
		}
	}

	// Ganador definido por en promedio de vida del ejercito
	public static void mostrarGanorBatalla() {

		double promedio1 = promedioVida(ejercitos.get(0));
		double promedio2 = promedioVida(ejercitos.get(1));
		if (promedio1 > promedio2) {
			System.out.println("\nEl ganador en el ejercito 1, con un promedio de vida de: " + promedio1);
		} else if (promedio1 < promedio2) {
			System.out.println("\nEl ganador en el ejercito 2, con un promedio de vida de: " + promedio2);
		} else {
			System.out.println("\nHay un empate entre los ejercitos, con un promedio de vida de: " + promedio1);
		}

	}

}
