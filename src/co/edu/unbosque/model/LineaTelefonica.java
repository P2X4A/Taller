package co.edu.unbosque.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class LineaTelefonica {
	private String numeroLinea;
	private String nombreLinea;
	private int minutosDisponibles;
	private ArrayList<Llamada> llamadas;

	public LineaTelefonica() {
		llamadas = new ArrayList<>();
	}

	/** Indica si este tipo de linea permite realizar la llamada indicada. */
	public abstract boolean permitirLlamada(TipoLlamada llamada);

	/** Nombre descriptivo del tipo de linea. */
	public abstract String getNombreLinea();

	/** Calcula el costo de una llamada concreta. */
	public abstract double calcularCostoLlamada(Llamada llamada);

	/**
	 * Minutos que quedan disponibles en el plan, restando los ya consumidos.
	 */
	public int getMinutosDisponibles() {
		return minutosDisponibles - duracionTotalLlamada();
	}

	/**
	 * Inicia una llamada desde esta linea. Si el tipo de llamada no esta
	 * permitido lanza TipoLlamadaNoPermitidaException.
	 */
	public Llamada iniciarLlamada(TipoLlamada tipoLlamada, String numeroDestino)
			throws TipoLlamadaNoPermitidaException {
		if (permitirLlamada(tipoLlamada)) {
			return new Llamada(tipoLlamada, numeroDestino, this);
		}
		throw new TipoLlamadaNoPermitidaException("El tipo de llamada " + tipoLlamada
				+ " no es valido para una linea " + getNombreLinea());
	}

	/**
	 * Cierra la llamada, calcula su duracion y la registra en el historial
	 * de la linea.
	 */
	public void finalizarLlamada(Llamada llamada) {
		llamada.setFinLlamada(LocalDateTime.now());
		this.llamadas.add(llamada);
	}

	public int duracionTotalLlamada() {
		int total = 0;
		for (Llamada llamada : llamadas) {
			total += llamada.getDuracionLlamada();
		}
		return total;
	}

	public String getNumeroLinea() {
		return numeroLinea;
	}

	public void setNumeroLinea(String numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	public int getMinutosDelPlan() {
		return minutosDisponibles;
	}

	public void setMinutosDisponibles(int minutosDisponibles) {
		this.minutosDisponibles = minutosDisponibles;
	}

	public ArrayList<Llamada> getLlamadas() {
		return llamadas;
	}

	public void setLlamadas(ArrayList<Llamada> llamadas) {
		this.llamadas = llamadas;
	}

	public void setNombreLinea(String nombreLinea) {
		this.nombreLinea = nombreLinea;
	}

}