package co.edu.unbosque.model.persistence;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.unbosque.model.Llamada;

public interface DAO {
	
	void save(Llamada entidad) throws IOException;
	ArrayList<Llamada> mostrarLlamadas () throws IOException;

}
