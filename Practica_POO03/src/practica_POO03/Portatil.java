package practica_POO03;

public enum Portatil implements Material {
	TIPO1(500.0),
	TIPO2(800.0),
	TIPO3(1000.0),
	TIPO4(2000.0);
	
	private final double precio;
	
	private Portatil(double precio) {
		this.precio = precio;
	}
	
	public double getPrecio() {
		return precio;
	}
}
