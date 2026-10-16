package co.edu.unbosque.model.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Acceso a archivos de texto plano para la persistencia.
 */
public class FileHandler {

	public static final String ARCHIVO_LLAMADAS = "Llamadas.txt";

	/**
	 * Agrega una linea al final del archivo. Si el archivo no existe lo crea.
	 *
	 * @param nombreArchivo nombre del archivo destino
	 * @param data contenido a escribir
	 * @throws IOException si ocurre un error de entrada/salida
	 */
	public static void escribirArchivo(String nombreArchivo, String data) throws IOException {
		File archivo = new File(nombreArchivo);
		try (FileWriter fw = new FileWriter(archivo, true);
			 BufferedWriter bw = new BufferedWriter(fw)) {
			bw.write(data);
			bw.newLine();
		}
	}

	/**
	 * Lee todas las lineas del archivo. Si el archivo no existe devuelve una
	 * lista vacia en lugar de fallar.
	 *
	 * @param nombreArchivo nombre del archivo origen
	 * @return lista con el contenido de cada linea
	 * @throws IOException si ocurre un error de entrada/salida
	 */
	public static ArrayList<String> leerArchivo(String nombreArchivo) throws IOException {
		ArrayList<String> lista = new ArrayList<String>();
		File archivo = new File(nombreArchivo);
		if (!archivo.exists()) {
			return lista;
		}
		try (FileReader fr = new FileReader(archivo);
			 BufferedReader br = new BufferedReader(fr)) {
			String linea = br.readLine();
			while (linea != null) {
				if (!linea.isBlank()) {
					lista.add(linea);
				}
				linea = br.readLine();
			}
		}
		return lista;
	}

}