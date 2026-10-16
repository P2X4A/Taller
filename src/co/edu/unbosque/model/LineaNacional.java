package co.edu.unbosque.model;

public class LineaNacional extends LineaTelefonica {

	public static final double VALOR_MINUTO = 90;
	public static final int MINUTOS_PLAN_NACIONAL = 50;

	public LineaNacional() {
		setNombreLinea("NACIONAL");
		setMinutosDisponibles(MINUTOS_PLAN_NACIONAL);
	}

	/** La linea nacional permite llamadas locales, de larga distancia y a celular. */
	@Override
	public boolean permitirLlamada(TipoLlamada llamada) {
		return llamada != null;
	}

	@Override
	public String getNombreLinea() {
		return "NACIONAL";
	}

	@Override
	public double calcularCostoLlamada(Llamada llamada) {
		TipoLlamada tipo = llamada.getTipoLlamada();
		if (tipo == null) {
			throw new IllegalArgumentException("La llamada no tiene tipo asignado");
		}
		return switch (tipo) {
			case LOCAL -> llamada.getDuracionLlamada() * VALOR_MINUTO;
			case LARGA_DISTANCIA -> llamada.getDuracionLlamada() * (VALOR_MINUTO * 1.5);
			case CELULAR -> llamada.getDuracionLlamada() * (VALOR_MINUTO * 2);
		};
	}

}