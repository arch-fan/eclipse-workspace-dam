package Practica3;

/**
 * La enumeracion TipoDieta representa los diferentes tipos de dietas y asigna
 * un valor de calorias recomendadas a cada tipo.
 */
public enum TipoDieta {
	BAJO_PESO(3000), PESO_NORMAL(2500), SOBREPESO(2000), OBESIDAD(1500);

	private final int calorias; // Almacena las calorias recomendadas para cada tipo de dieta.

	/**
	 * Constructor de la enumeracion TipoDieta.
	 * 
	 * @param calorias Las calorias recomendadas para el tipo de dieta.
	 */
	TipoDieta(int calorias) {
		this.calorias = calorias;
	}

	/**
	 * Obtiene el valor de calorias recomendadas para el tipo de dieta.
	 * 
	 * @return Las calorias recomendadas.
	 */
	public int getCalorias() {
		return calorias;
	}
}
