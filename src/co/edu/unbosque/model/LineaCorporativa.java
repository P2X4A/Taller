package co.edu.unbosque.model;

/**
 * Linea corporativa: sin costo por minuto, con minutos ilimitados.
 * Es el tipo de linea mas permisivo y por eso no restringe el tipo de llamada.
 */
public class LineaCorporativa extends LineaTelefonica {

	public static final int MINUTOS_PLAN_CORPORATIVO = 0; // plan ilimitado

	public LineaCorporativa() {
		setNombreLinea("CORPORATIVA");
		setMinutosDisponibles(MINUTOS_PLAN_CORPORATIVO);
	}

	@Override
	public boolean permitirLlamada(TipoLlamada llamada) {
		return llamada != null;
	}

	@Override
	public String getNombreLinea() {
		return "CORPORATIVA";
	}

	@Override
	public double calcularCostoLlamada(Llamada llamada) {
		// La linea corporativa no cobra por minuto consumed
		return 0.0;
	}

	/** Al ser un plan ilimitado siempre quedan minutos disponibles. */
	@Override
	public int getMinutosDisponibles() {
		return Integer.MAX_VALUE;
	}

}