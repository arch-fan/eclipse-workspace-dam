package practica_POO03;

public class Material {
	private final String nombre;
	private final String modelo;
	private final double price;
	
	public Material(String nombre, String modelo, double price) {
		super();
		this.nombre = nombre;
		this.modelo = modelo;
		this.price = price;
	}

	public String getNombre() {
		return nombre;
	}

	public String getModelo() {
		return modelo;
	}

	public double getPrice() {
		return price;
	}
}
