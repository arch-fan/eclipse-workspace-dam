package practica_poo02;

public class Desperfecto {
	// Clase maestra donde designamos los datos que debe tener cada tipo de desperfecto.
	private String nombre;
	private double precioUnidad;
	private double precioTotal;
	private int cantidad;
	private double valorCubiertoTotal;
	private double valorCubiertoUnidad;

	public Desperfecto(String nombre, double precioUnidad, int cantidad, double valorCubierto) {
		this.nombre = nombre;
		this.precioUnidad = precioUnidad;
		this.precioTotal = precioUnidad * cantidad;
		this.cantidad = cantidad;
		this.valorCubiertoUnidad = valorCubierto;
		this.valorCubiertoTotal = valorCubierto * cantidad;
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

	public double getValorCubiertoTotal() {
		return valorCubiertoTotal;
	}
	
	public double getValorCubiertoUnidad() {
		return valorCubiertoUnidad;
	}
	
	public double getPrecioTotal() {
		return precioTotal;
	}
}
