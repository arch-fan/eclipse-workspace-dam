package practica_POO03;

public enum Coche implements Material {
	TIPO1(500),
	TIPO2(1000);
	
	private final double precio;
	
	private Coche(double precio) {
		this.precio = precio;
	}
	
	public double getPrecio() {
		return precio;
	}
}
