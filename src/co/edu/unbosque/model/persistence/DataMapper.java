package co.edu.unbosque.model.persistence;

import java.time.LocalDateTime;

import co.edu.unbosque.model.LineaCorporativa;
import co.edu.unbosque.model.LineaLocal;
import co.edu.unbosque.model.LineaNacional;
import co.edu.unbosque.model.LineaTelefonica;
import co.edu.unbosque.model.Llamada;
import co.edu.unbosque.model.TipoLlamada;

/**
 * Convierte entre la entidad Llamada y su representacion transferible (DTO),
 * incluyendo la serializacion a texto plano.
 */
public class DataMapper {

	public static LlamadaDTO convertirEntitytoDTO(Llamada llamada) {
		return new LlamadaDTO(
				llamada.getInicioLlamada(),
				llamada.getFinLlamada(),
				llamada.getTelefonoDestino(),
				llamada.getTipoLlamada(),
				llamada.getLineaOrigen(),
				llamada.getDuracionLlamada());
	}

	public static Llamada convertirDTOaEntity(LlamadaDTO dto) {
		return new Llamada(
				dto.getInicioLlamada(),
				dto.getFinLlamada(),
				dto.getTelefonoDestino(),
				dto.getTipoLlamada(),
				dto.getLineaOrigen());
	}

	public static LocalDateTime stringtoFecha(String dato) {
		if (dato == null || dato.isBlank()) {
			return null;
		}
		return LocalDateTime.parse(dato);
	}

	/**
	 * Reconstruye una linea telefonica a partir de su nombre.
	 *
	 * @param nombreLinea nombre de la linea
	 * @return instancia de la linea correspondiente
	 */
	public static LineaTelefonica crearLinea(String nombreLinea) {
		if (nombreLinea == null) {
			return null;
		}
		return switch (nombreLinea.toUpperCase()) {
			case "LOCAL" -> new LineaLocal();
			case "NACIONAL" -> new LineaNacional();
			case "CORPORATIVA" -> new LineaCorporativa();
			default -> null;
		};
	}

	/**
	 * Parsea una linea del archivo plano y devuelve la entidad Llamada.
	 *
	 * @param linea texto con los campos separados por ';'
	 * @return entidad reconstruida
	 */
	public static Llamada parseLlamada(String linea) {
		String[] datos = linea.split(LlamadaDTO.SEPARADOR);
		TipoLlamada tipo = TipoLlamada.valueOf(datos[0].trim());
		String telefonoDestino = datos[1];
		LocalDateTime inicio = stringtoFecha(datos[2]);
		LocalDateTime fin = stringtoFecha(datos[3]);
		LineaTelefonica lineaOrigen = crearLinea(datos.length > 5 ? datos[5] : null);
		return new Llamada(inicio, fin, telefonoDestino, tipo, lineaOrigen);
	}

}