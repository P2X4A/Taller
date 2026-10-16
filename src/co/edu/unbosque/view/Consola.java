package co.edu.unbosque.view;

import java.util.Scanner;

/**
 * Vista de consola del taller de lineas telefonicas.
 */
public class Consola {

	private final Scanner consola = new Scanner(System.in);

	/**
	 * Muestra el menu principal y devuelve la opcion elegida.
	 */
	public int mostrarMenu() {
		System.out.println("""
				===== TALLER LINEAS TELEFONICAS =====
				1. Crear linea Local
				2. Crear linea Nacional
				3. Crear linea Corporativa
				4. Registrar una llamada
				5. Finalizar la llamada en curso
				6. Mostrar historial de llamadas de una linea
				7. Mostrar todas las llamadas guardadas
				0. Salir
				""");
		return leerEntero("Seleccione una opcion: ");
	}

	/**
	 * Lee un entero reintentando mientras la entrada no sea valida.
	 */
	public int leerEntero(String mensaje) {
		while (true) {
			System.out.print(mensaje);
			if (consola.hasNextInt()) {
				return consola.nextInt();
			}
			consola.next();
			System.out.println("Entrada invalida, intente de nuevo.");
		}
	}

	private String leerTexto(String mensaje) {
		System.out.println(mensaje);
		return consola.nextLine();
	}

	public String pedirNombreLinea() {
		return leerTexto("Ingrese el numero de la linea: ");
	}

	public String pedirTipoLlamada() {
		return leerTexto("Ingrese el tipo de llamada (LOCAL, LARGA_DISTANCIA, CELULAR): ");
	}

	public String pedirNumeroDestino() {
		return leerTexto("Ingrese el numero destino: ");
	}

	public String pedirLineaOrigen() {
		return leerTexto("Ingrese el numero de la linea origen: ");
	}

	public void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}

	public void mostrarLinea(String nombre, String numero, int minutosDisponibles) {
		System.out.println("Linea " + nombre + " (" + numero + ") -> minutos disponibles: " + minutosDisponibles);
	}

	public void mostrarLlamadas(String cabecera, java.util.List<?> llamadas) {
		System.out.println(cabecera);
		if (llamadas.isEmpty()) {
			System.out.println("  (sin registros)");
		}
		for (Object llamada : llamadas) {
			System.out.println("  " + llamada);
		}
	}
}