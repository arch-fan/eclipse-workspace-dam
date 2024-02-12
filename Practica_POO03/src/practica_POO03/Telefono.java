package practica_POO03;

public enum Telefono implements Material {
	TIPO1(300.0),
	TIPO2(1000.0);
	
	private final double precio;
	
	private Telefono(double precio) {
		this.precio = precio;
	}
	
	public double getPrecio() {
		return precio;
	}
}
