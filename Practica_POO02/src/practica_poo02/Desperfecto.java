package practica_poo02;

public class Desperfecto {
	private String nombre;
	private double precio;
	private int cantidad;
	private double valorCubierto;

	public Desperfecto(String nombre, double precio, int cantidad, double valorCubierto) {
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
		this.valorCubierto = valorCubierto;
	}

	public String getNombre() {
		return nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public double getValorCubierto() {
		return valorCubierto;
	}
}
