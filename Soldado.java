
import java.util.Random;

public class Soldado {

	private String nombre;
	private int nivelAtaque;
	private int nivelDefensa;
	private int nivelVida = 5;
	private int vidaActual;
	private int velocidad;
	private char actitud; // Tres posibles casos , puede ser char (D,O,H)
	private boolean vive = true;

	// atributos que concidero yo
	private static int cantidad = 0;
	private int id;
	private boolean atacar;
	private int fila;
	private int columna;
	private char simbolo;

	public Soldado(String nombre, int fila, int columna, char simbolo) {
		this.nombre = nombre;
		this.columna = columna;
		this.simbolo = simbolo;
		this.actitud = 'd';
		vidaActual = (int) (Math.random() * 6);
		this.id = cantidad;
		cantidad++;
	}

	public Soldado(String name, int vidaActual, int fila, int columna, char simbolo) {
		nombre = name;
		setVidaActual(vidaActual);
		this.fila = fila;
		this.columna = columna;
		this.actitud = 'd';
		this.simbolo = simbolo;
		this.id = cantidad;
		cantidad++;
	}

	public Soldado(String nombre, int nivelAtaque, int nivelDefensa, int nivelVida, boolean vive) {
		this.nombre = nombre;
		this.nivelAtaque = nivelAtaque;
		this.nivelDefensa = nivelDefensa;
		this.nivelVida = nivelVida;
		this.vive = vive;
		this.actitud = 'd';
		this.id = cantidad;
		cantidad++;
	}

	public Soldado(String nombre, int nivelAtaque, int nivelDefensa, int nivelVida, int vidaActual, int velocidad,
			char actitud, boolean vive, int id, boolean atacar, int fila, int columna, char simbolo) {

		this.nombre = nombre;
		this.nivelAtaque = nivelAtaque;
		this.nivelDefensa = nivelDefensa;
		this.nivelVida = nivelVida;
		this.vidaActual = vidaActual;
		this.velocidad = velocidad;
		this.actitud = actitud;
		this.vive = vive;
		this.id = id;
		this.atacar = atacar;
		this.fila = fila;
		this.columna = columna;
		this.simbolo = simbolo;
	}

	public Soldado clonar(int fila, int columna) {
		String nombre = this.nombre;
		int nivelAtaque = this.nivelAtaque;
		int nivelDefensa = this.nivelDefensa;
		int nivelVida = 5;
		int vidaActual = this.vidaActual;
		int velocidad = this.velocidad;
		char actitud = this.actitud;
		boolean vive = true;
		cantidad++;
		int id = cantidad;
		boolean atacar = this.atacar;
		char simbolo = this.simbolo;
		Soldado sol = new Soldado(nombre, nivelAtaque, nivelDefensa, nivelVida, vidaActual, velocidad, actitud, vive,
				id, atacar, fila, columna, simbolo);
		return sol;
	}

	public static boolean comparar(Soldado s1, Soldado s2) {
		boolean v1 = (s1.getNombre().equalsIgnoreCase(s2.getNombre()));
		boolean v2 = (s1.getNivelAtaque() == s2.getNivelAtaque());
		boolean v3 = (s1.getNivelDefensa() == s2.getNivelDefensa());
		boolean v4 = (s1.getVidaActual() == s2.getVidaActual());
		boolean v5 = (s1.getVive() && s2.getVive());
		return (v1 && v2 && v3 && v4 && v5);
	}

	public static void mostrarAtributos() {
		System.out.println(
				"1.- Nombre\n2.- nivel de Ataque\n3.- Nivel de defensa\n4.- Vida actual\n5.- Ejercito\n6.- Volver");
	}

	public String toString() {
		return "ID : " + id + ", Nombre: " + nombre + ", Nivel Vida Actual: " + vidaActual + ", Ejercito: " + simbolo;
	}

	public void atacar() {
		setActitud('o');
		avanzar(1);

		setAtacar(true);

	}

	public void defender() {
		setActitud('d');
		setVelocidad(0);

		setAtacar(false);
	}

	public void avanzar(int valor) { // podemos ponder un parametro
		velocidad += valor;
	}

	public void retroceder() {
		if (velocidad > 0) {
			setVelocidad(0);
			setActitud('d');
		} else {
			velocidad--; // retrocede con -1
		}
	}

	public void serAtacado(int ataque) { // pude contener parametro
		vidaActual -= ataque;
		if (vidaActual <= 0) {
			morir();
		}

	}

	public void hiur() {
		avanzar(2);
	}

	public void morir() {
		vive = false;
	}

	public void mover(int fila, int columna, int accion, int[][] tabla) {
		this.fila = fila;
		this.columna = columna;

		if (atacar) {

		} else {

		}

	}

	public static Soldado batallarGanador(Soldado s1, Soldado s2) {
		Random rd = new Random();
		int total = s1.getVidaActual() + s2.getVidaActual();
		Soldado[] todo = new Soldado[total];

		for (int i = 0; i < s1.getVidaActual(); i++) {
			todo[i] = s1;
		}
		for (int i = s1.getVidaActual(); i < todo.length; i++) {
			todo[i] = s2;
		}
		int ale = rd.nextInt(todo.length);

		System.out.println("El ganador es el ejercito " + todo[ale].getSimbolo());
		return todo[ale];

	}

	public static Soldado batallarPerdedor(Soldado s1, Soldado s2, Soldado ganador) {
		if (ganador == s1) {
			return s2;
		} else {
			return s1;
		}
	}

	// ---------------- get and set --------------------------
	public int getId() {
		return id;
	}

	public char getSimbolo() {
		return simbolo;
	}

	public boolean isVive() {
		return vive;
	}

	public void setVive(boolean vive) {
		this.vive = vive;
	}

	public static int getCantidad() {
		return cantidad;
	}

	public static void setCantidad(int cantidad) {
		Soldado.cantidad = cantidad;
	}

	public int getNivelAtaque() {
		return nivelAtaque;
	}

	public int getNivelDefensa() {
		return nivelDefensa;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public char getActitud() {
		return actitud;
	}

	public boolean isAtacar() {
		return atacar;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNivelVida() {
		return nivelVida;
	}

	public void setNivelVida(int nivelVida) {
		this.nivelVida = nivelVida;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public void setNivelAtaque(int nivelAtaque) {
		this.nivelAtaque = nivelAtaque;
	}

	public void setNivelDefensa(int nivelDefensa) {
		this.nivelDefensa = nivelDefensa;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}

	public void setActitud(char actitud) {
		this.actitud = actitud;
	}

	// use
	public void setAtacar(boolean atacar) {
		this.atacar = atacar;
	}

	public int getVidaActual() {
		return vidaActual;
	}

	public String getNombre() {
		return nombre;
	}

	public void setVidaActual(int vidaActual) {
		if (vidaActual <= nivelVida) {
			this.vidaActual = vidaActual;
		} else {
			System.out.println("Vida actual maxima");
			this.vidaActual = nivelVida;
		}
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public boolean getVive() {
		return vive;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

}
