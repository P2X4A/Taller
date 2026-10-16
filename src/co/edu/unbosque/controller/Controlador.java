package co.edu.unbosque.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import co.edu.unbosque.model.LineaCorporativa;
import co.edu.unbosque.model.LineaLocal;
import co.edu.unbosque.model.LineaNacional;
import co.edu.unbosque.model.LineaTelefonica;
import co.edu.unbosque.model.Llamada;
import co.edu.unbosque.model.TipoLlamada;
import co.edu.unbosque.model.TipoLlamadaNoPermitidaException;
import co.edu.unbosque.model.persistence.DAO;
import co.edu.unbosque.model.persistence.LlamadaDAOImpl;
import co.edu.unbosque.view.Consola;

/**
 * Controlador del taller: gestiona las lineas telefonicas, el ciclo de vida
 * de las llamadas y su persistencia en archivo plano.
 */
public class Controlador {

	private final Consola vista;
	private final DAO llamadaDAO;
	private final Map<String, LineaTelefonica> lineas;
	private Llamada llamadaEnCurso;

	public Controlador() {
		this.vista = new Consola();
		this.llamadaDAO = new LlamadaDAOImpl();
		this.lineas = new LinkedHashMap<>();
	}

	/**
	 * Bucle principal de la aplicacion.
	 */
	public void run() {
		int opcion;
		vista.mostrarMensaje("Bienvenido al taller de lineas telefonicas.");
		do {
			opcion = vista.mostrarMenu();
			switch (opcion) {
				case 1 -> crearLinea(new LineaLocal());
				case 2 -> crearLinea(new LineaNacional());
				case 3 -> crearLinea(new LineaCorporativa());
				case 4 -> registrarLlamada();
				case 5 -> finalizarLlamada();
				case 6 -> mostrarHistorialDeLinea();
				case 7 -> mostrarLlamadasGuardadas();
				case 0 -> vista.mostrarMensaje("Saliendo del programa.");
				default -> vista.mostrarMensaje("Opcion no valida, intente de nuevo.");
			}
		} while (opcion != 0);
	}

	/**
	 * Registra una nueva linea con su numero asignado.
	 */
	private void crearLinea(LineaTelefonica linea) {
		String numero = vista.pedirNombreLinea();
		linea.setNumeroLinea(numero);
		lineas.put(numero, linea);
		vista.mostrarLinea(linea.getNombreLinea(), numero, linea.getMinutosDisponibles());
	}

	/**
	 * Abre una llamada desde la linea indicada, validando el tipo permitido.
	 */
	private void registrarLlamada() {
		if (lineas.isEmpty()) {
			vista.mostrarMensaje("Primero debe crear al menos una linea.");
			return;
		}
		String numeroOrigen = vista.pedirLineaOrigen();
		LineaTelefonica origen = lineas.get(numeroOrigen);
		if (origen == null) {
			vista.mostrarMensaje("La linea " + numeroOrigen + " no existe.");
			return;
		}
		try {
			TipoLlamada tipo = TipoLlamada.valueOf(vista.pedirTipoLlamada().trim().toUpperCase());
			String destino = vista.pedirNumeroDestino();
			llamadaEnCurso = origen.iniciarLlamada(tipo, destino);
			vista.mostrarMensaje("Llamada iniciada. Use la opcion 5 para finalizarla.");
		} catch (IllegalArgumentException e) {
			vista.mostrarMensaje("Tipo de llamada no valido. Use LOCAL, LARGA_DISTANCIA o CELULAR.");
		} catch (TipoLlamadaNoPermitidaException e) {
			vista.mostrarMensaje(e.getMessage());
		}
	}

	/**
	 * Cierra la llamada en curso, la guarda y muestra su costo.
	 */
	private void finalizarLlamada() {
		if (llamadaEnCurso == null) {
			vista.mostrarMensaje("No hay ninguna llamada en curso.");
			return;
		}
		LineaTelefonica origen = llamadaEnCurso.getLineaOrigen();
		origen.finalizarLlamada(llamadaEnCurso);
		double costo = origen.calcularCostoLlamada(llamadaEnCurso);
		vista.mostrarMensaje("Llamada finalizada. Duracion: " + llamadaEnCurso.getDuracionLlamada()
				+ " min | Costo: " + costo);
		try {
			llamadaDAO.save(llamadaEnCurso);
			vista.mostrarMensaje("Llamada guardada en el archivo de persistencia.");
		} catch (IOException e) {
			vista.mostrarMensaje("No se pudo guardar la llamada: " + e.getMessage());
		}
		llamadaEnCurso = null;
	}

	/**
	 * Muestra el historial de llamadas de una linea creada en la sesion.
	 */
	private void mostrarHistorialDeLinea() {
		String numero = vista.pedirLineaOrigen();
		LineaTelefonica linea = lineas.get(numero);
		if (linea == null) {
			vista.mostrarMensaje("La linea " + numero + " no existe.");
			return;
		}
		List<Llamada> historial = linea.getLlamadas();
		vista.mostrarLlamadas("Historial de la linea " + linea.getNombreLinea() + ":", historial);
	}

	/**
	 * Muestra todas las llamadas leidas del archivo de persistencia.
	 */
	private void mostrarLlamadasGuardadas() {
		try {
			ArrayList<Llamada> guardadas = llamadaDAO.mostrarLlamadas();
			vista.mostrarLlamadas("Llamadas guardadas en disco:", guardadas);
		} catch (IOException e) {
			vista.mostrarMensaje("No se pudo leer el archivo: " + e.getMessage());
		}
	}

	public Map<String, LineaTelefonica> getLineas() {
		return lineas;
	}
}