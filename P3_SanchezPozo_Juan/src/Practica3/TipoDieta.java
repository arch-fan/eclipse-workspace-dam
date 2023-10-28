package Practica3;

public enum TipoDieta {
	BAJO_PESO(3000),
	PESO_NORMAL(2500),
    SOBREPESO(2000),
    OBESIDAD(1500);
	
	private final int calorias;
	
	TipoDieta(int calorias) {
		this.calorias = calorias;
	}
	
	public int getCalorias() {
		return calorias;
	}
}
