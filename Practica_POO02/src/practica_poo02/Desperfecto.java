package practica_poo02;

public class Desperfecto {
	private String nombre;
	private double precioUnidad;
	private double precioTotal;
	private int cantidad;
	private double valorCubierto;

	public Desperfecto(String nombre, double precioUnidad, int cantidad, double valorCubierto) {
		this.nombre = nombre;
		this.precioUnidad = precioUnidad;
		this.precioTotal = precioUnidad * cantidad;
		this.cantidad = cantidad;
		this.valorCubierto = valorCubierto;
	}

	public String getNombre() {
		return nombre;
	}

	public double getPrecioUnidad() {
		return precioUnidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public double getValorCubierto() {
		return valorCubierto;
	}
	
	public double getPrecioTotal() {
		return precioTotal;
	}
}
