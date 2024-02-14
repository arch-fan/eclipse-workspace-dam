package practica_POO03;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Empleado {
	private String nombre;
	private String apellidos;
	private int edad; // Opcional
	private String direccion; // Opcional
	private double sueldoBruto;
	private double sueldoBrutoTotal;
	private double irpf; // Calcular en el constructor
	private CategoriasEmpleado categoria = CategoriasEmpleado.JUNIOR; // Opcional
	private double sueldoNeto; // Se calcula, opcional en constructor
	private int antiguedad = 0; // Opcional
	private int numeroDeHijos = 0; // Opcional
	private int vacaciones; // Opcional, en semanas
	private List<Material> material = new ArrayList<>();

	private static final Map<CategoriaEmpleado, Integer> stock = new HashMap<>();

	static {
		stock.put(CategoriasEmpleado.JUNIOR, 5);
		stock.put(CategoriasEmpleado.SENIOR, 5);
		stock.put(CategoriasEmpleado.ARQUITECTO, 5);
		stock.put(CategoriasEmpleado.MANAGER, 5);
	}

	public Empleado(String nombre, String apellidos, double sueldoBruto) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.sueldoBruto = sueldoBruto;
		this.sueldoBrutoTotal = calcularBrutoTotal(sueldoBruto, this.categoria);
				
		this.irpf = calcularIrpf(this.sueldoBrutoTotal);
		this.sueldoNeto = calcularSueldoNeto(this.sueldoBrutoTotal, this.irpf);

		if (stock.get(categoria) > 0) {
			this.material = categoria.getListaMateriales();
			restarStock(categoria);
		}
	}

	public Empleado(String nombre, String apellidos, double sueldoBruto, int edad, String direccion,
			CategoriasEmpleado categoria, int antiguedad, int numeroDeHijos, int vacaciones) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.sueldoBruto = sueldoBruto;
		this.sueldoBrutoTotal = calcularBrutoTotal(sueldoBruto, antiguedad, numeroDeHijos, categoria);
		this.edad = edad;
		this.direccion = direccion;
		this.categoria = categoria;
		this.antiguedad = antiguedad;
		this.numeroDeHijos = numeroDeHijos;
		this.vacaciones = vacaciones;

		this.irpf = calcularIrpf(this.sueldoBrutoTotal);
		this.sueldoNeto = calcularSueldoNeto(this.sueldoBrutoTotal, this.irpf);

		if (stock.get(categoria) > 0) {
			this.material = categoria.getListaMateriales();
			restarStock(categoria);
		}

	}

	/**
	 * @return Devuelve el porcentaje en formato decimal (10% = 0.1)
	 */
	private static double calcularIrpf(double sueldoBrutoTotal) {
		double calculoBase = sueldoBrutoTotal - 15000;
		double irpf = 0.1;
		
		if (calculoBase <= 0) {
			return irpf;
		}
		
		int vecesIrpf = (int) calculoBase / 5000;

		for (int i = 0; i < vecesIrpf; i++) {
			irpf += 0.02;
		}

		return irpf;
	}

	private static double calcularSueldoNeto(double sueldoBrutoTotal, double irpf) {
		return sueldoBrutoTotal - (sueldoBrutoTotal * irpf);
	}

	private static double calcularBrutoTotal(double sueldoBruto, int antiguedad, int numeroDeHijos, CategoriasEmpleado categoria) {
		double sumaAntiguedad = antiguedad * 20 * 12;
		double sumaHijos = numeroDeHijos * 10 * 12;
		double bonoCategoria = sueldoBruto * categoria.getBonificacion();
		
		return sueldoBruto + bonoCategoria + sumaAntiguedad + sumaHijos;
	}
	
	private static double calcularBrutoTotal(double sueldoBruto, CategoriasEmpleado categoria) {
		double bonoCategoria = sueldoBruto * categoria.getBonificacion();
		
		return sueldoBruto + bonoCategoria;
	}
	
	private static void restarStock(CategoriasEmpleado categoria) {
		stock.merge(categoria, -1, Integer::sum);
	}

	public double gastoAnual() {
		double gastoAnual = this.sueldoBrutoTotal;
				
		if(!this.material.isEmpty()) {
			gastoAnual += this.material
				.stream()
				.mapToDouble(material -> {
					if(material.getClass().equals(Coche.class)) {
						switch (this.categoria) {
						case MANAGER:
							return material.getPrecio() * (52 - this.vacaciones);
						case ARQUITECTO:
							return material.getPrecio() * 52;
						default:
							throw new Error("Unhandle Case Exception");
						}
					} else {
						return material.getPrecio();
					}
				})
				.sum();
		}
				
		return gastoAnual;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public Integer getEdad() {
		return edad;
	}

	public String getDireccion() {
		return direccion;
	}

	public double getSueldoBruto() {
		return sueldoBruto;
	}

	public Double getIrpf() {
		return irpf;
	}

	public Double getSueldoNeto() {
		return sueldoNeto;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public Integer getNumeroDeHijos() {
		return numeroDeHijos;
	}

	public Integer getVacaciones() {
		return vacaciones;
	}

	public List<Material> getMaterial() {
		return material;
	}

	public CategoriasEmpleado getCategoria() {
		return categoria;
	}
}
