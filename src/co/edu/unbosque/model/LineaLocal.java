package co.edu.unbosque.model;

public class LineaLocal extends LineaTelefonica {

	public static final double VALOR_MINUTO = 35;
	public static final int MINUTOS_PLAN_LOCAL = 100;

	public LineaLocal() {
		setNombreLinea("LOCAL");
		setMinutosDisponibles(MINUTOS_PLAN_LOCAL);
	}

	@Override
	public boolean permitirLlamada(TipoLlamada llamada) {
		return TipoLlamada.LOCAL.equals(llamada);
	}

	@Override
	public String getNombreLinea() {
		return "LOCAL";
	}

	@Override
	public double calcularCostoLlamada(Llamada llamada) {
		if (TipoLlamada.LOCAL.equals(llamada.getTipoLlamada())) {
			return llamada.getDuracionLlamada() * VALOR_MINUTO;
		}
		throw new IllegalArgumentException("Una linea local solo permite llamadas locales");
	}

}