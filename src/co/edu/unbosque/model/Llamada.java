package co.edu.unbosque.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Llamada {
    private LocalDateTime inicioLlamada;
    private LocalDateTime finLlamada;
    private String telefonoDestino;
    private TipoLlamada tipoLlamada;
    private LineaTelefonica lineaOrigen;
    private int duracionLlamada;

	public Llamada(LocalDateTime inicioLlamada, LocalDateTime finLlamada, String telefonoDestino,
			TipoLlamada tipoLlamada, LineaTelefonica lineaOrigen) {
		this.inicioLlamada = inicioLlamada;
		this.finLlamada = finLlamada;
		this.telefonoDestino = telefonoDestino;
		this.tipoLlamada = tipoLlamada;
		this.lineaOrigen = lineaOrigen;
		this.duracionLlamada = calcularDuracion();
	}

	/**
	 * Constructor usado al iniciar una llamada: registra el instante de inicio
	 * y deja la llamada abierta hasta invocar finalizarLlamada.
	 */
	public Llamada(TipoLlamada tipoLlamada, String numeroDestino, LineaTelefonica lineaTelefonica) {
		this.inicioLlamada = LocalDateTime.now();
		this.finLlamada = null;
		this.telefonoDestino = numeroDestino;
		this.tipoLlamada = tipoLlamada;
		this.lineaOrigen = lineaTelefonica;
		this.duracionLlamada = 0;
	}

	/**
	 * Calcula la duracion en minutos entre el inicio y el fin de la llamada.
	 * Devuelve 0 si la llamada aun no ha terminado.
	 */
	private int calcularDuracion() {
		if (inicioLlamada == null || finLlamada == null) {
			return 0;
		}
		long segundos = Duration.between(inicioLlamada, finLlamada).getSeconds();
		if (segundos <= 0) {
			return 0;
		}
		return (int) Math.ceil(segundos / 60.0);
	}

	public LocalDateTime getInicioLlamada() {
		return inicioLlamada;
	}

	public void setInicioLlamada(LocalDateTime inicioLlamada) {
		this.inicioLlamada = inicioLlamada;
		this.duracionLlamada = calcularDuracion();
	}

	public LocalDateTime getFinLlamada() {
		return finLlamada;
	}

	public void setFinLlamada(LocalDateTime finLlamada) {
		this.finLlamada = finLlamada;
		this.duracionLlamada = calcularDuracion();
	}

	public String getTelefonoDestino() {
		return telefonoDestino;
	}

	public void setTelefonoDestino(String telefonoDestino) {
		this.telefonoDestino = telefonoDestino;
	}

	public TipoLlamada getTipoLlamada() {
		return tipoLlamada;
	}

	public void setTipoLlamada(TipoLlamada tipoLlamada) {
		this.tipoLlamada = tipoLlamada;
	}

	public LineaTelefonica getLineaOrigen() {
		return lineaOrigen;
	}

	public void setLineaOrigen(LineaTelefonica lineaOrigen) {
		this.lineaOrigen = lineaOrigen;
	}

	public int getDuracionLlamada() {
		return duracionLlamada;
	}

	public void setDuracionLlamada(int duracionLlamada) {
		this.duracionLlamada = duracionLlamada;
	}

	@Override
	public String toString() {
		return tipoLlamada + "--" + telefonoDestino + "--" + inicioLlamada + "--" + finLlamada;
	}

}