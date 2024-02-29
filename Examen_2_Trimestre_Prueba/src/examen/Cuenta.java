package examen;

public class Cuenta {
	private static int cont = 1;
	
	private int id;
	private String numeroCuenta;
	private Cliente titular;
	private double dinero = 0;
	private String estado = "activa";
	private String moneda;
	
	public Cuenta(String numeroCuenta, Cliente titular, String moneda) {
		this.id = cont;
		this.numeroCuenta = numeroCuenta;
		this.titular = titular;
		this.moneda = moneda;
		
		System.out.println("Se ha creado la cuenta N " + cont + " asignada a " + titular.getNombreCompleto());
		
		cont++;
	}
	
	public void agregarDinero(double importe) {
		this.dinero += importe;
	}

	public int getId() {
		return id;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public Cliente getTitular() {
		return titular;
	}

	public double getDinero() {
		return dinero;
	}

	public String getEstado() {
		return estado;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
