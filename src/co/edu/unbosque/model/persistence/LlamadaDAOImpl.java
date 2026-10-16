package co.edu.unbosque.model.persistence;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.unbosque.model.Llamada;

public class LlamadaDAOImpl implements DAO {

	@Override
	public void save(Llamada entidad) throws IOException {
		if (entidad == null) {
			throw new IllegalArgumentException("La llamada no puede ser nula");
		}
		LlamadaDTO dto = DataMapper.convertirEntitytoDTO(entidad);
		FileHandler.escribirArchivo(FileHandler.ARCHIVO_LLAMADAS, dto.toString());
	}

	@Override
	public ArrayList<Llamada> mostrarLlamadas() throws IOException {
		ArrayList<Llamada> llamadas = new ArrayList<Llamada>();
		ArrayList<String> lineas = FileHandler.leerArchivo(FileHandler.ARCHIVO_LLAMADAS);
		for (String registro : lineas) {
			try {
				llamadas.add(DataMapper.parseLlamada(registro));
			} catch (RuntimeException e) {
				// Se omite el registro corrupto en lugar de detener toda la lectura
				System.err.println("Registro omitido por formato invalido: " + registro);
			}
		}
		return llamadas;
	}

}