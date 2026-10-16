package co.edu.unbosque.model.persistence;

import java.time.LocalDateTime;

import co.edu.unbosque.model.LineaTelefonica;
import co.edu.unbosque.model.TipoLlamada;

/**
 * Objeto de transferencia de datos de una llamada.
 * Aporta el formato en texto plano que se persiste en el archivo.
 */
public class LlamadaDTO {

	public static final String SEPARADOR = ";";

    private LocalDateTime inicioLlamada;
    private LocalDateTime finLlamada;
    private String telefonoDestino;
    private TipoLlamada tipoLlamada;
    private LineaTelefonica lineaOrigen;
    private int duracionLlamada;

	public LlamadaDTO(LocalDateTime inicioLlamada, LocalDateTime finLlamada, String telefonoDestino,
			TipoLlamada tipoLlamada, LineaTelefonica lineaOrigen, int duracionLlamada) {
		this.inicioLlamada = inicioLlamada;
		this.finLlamada = finLlamada;
		this.telefonoDestino = telefonoDestino;
		this.tipoLlamada = tipoLlamada;
		this.lineaOrigen = lineaOrigen;
		this.duracionLlamada = duracionLlamada;
	}

	public LocalDateTime getInicioLlamada() {
		return inicioLlamada;
	}

	public LocalDateTime getFinLlamada() {
		return finLlamada;
	}

	public String getTelefonoDestino() {
		return telefonoDestino;
	}

	public TipoLlamada getTipoLlamada() {
		return tipoLlamada;
	}

	public LineaTelefonica getLineaOrigen() {
		return lineaOrigen;
	}

	public int getDuracionLlamada() {
		return duracionLlamada;
	}

	/**
	 * @return la llamada en formato plano, separada por punto y coma
	 */
	@Override
	public String toString() {
		return tipoLlamada + SEPARADOR
				+ telefonoDestino + SEPARADOR
				+ (inicioLlamada == null ? "" : inicioLlamada) + SEPARADOR
				+ (finLlamada == null ? "" : finLlamada) + SEPARADOR
				+ duracionLlamada + SEPARADOR
				+ (lineaOrigen == null ? "" : lineaOrigen.getNombreLinea());
	}

}