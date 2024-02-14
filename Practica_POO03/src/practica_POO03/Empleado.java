package practica_POO03;

import java.util.ArrayList;
import java.util.List;

public class Empleado {
	private String nombre;
	private String apellidos;
	private int edad; // Opcional
	private String direccion; // Opcional
	private double sueldoBruto;
	private double sueldoBrutoTotal;
	private double irpf; // Calcular en el constructor
	private CategoriaEmpleado categoria = new CategoriaEmpleado("junior"); // Opcional
	private double sueldoNeto; // Se calcula, opcional en constructor
	private int antiguedad = 0; // Opcional
	private int numeroDeHijos = 0; // Opcional
	private int vacaciones; // Opcional, en semanas
	private List<Material> material = new ArrayList<>();

	public Empleado(String nombre, String apellidos, double sueldoBruto) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.sueldoBruto = sueldoBruto;
		this.sueldoBrutoTotal = calcularBrutoTotal(sueldoBruto, this.categoria);
				
		this.irpf = calcularIrpf(this.sueldoBrutoTotal);
		this.sueldoNeto = calcularSueldoNeto(this.sueldoBrutoTotal, this.irpf);

		this.material.addAll(CategoriaEmpleado.generateKit(this.categoria.getNombre()));
	}

	public Empleado(String nombre, String apellidos, double sueldoBruto, int edad, String direccion,
			String categoria, int antiguedad, int numeroDeHijos, int vacaciones) {
		
		try {
			this.categoria = new CategoriaEmpleado(categoria);
		} catch (IllegalArgumentException e) {
			throw e;
		}
		
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.sueldoBruto = sueldoBruto;
		this.sueldoBrutoTotal = calcularBrutoTotal(sueldoBruto, antiguedad, numeroDeHijos, this.categoria);
		this.edad = edad;
		this.direccion = direccion;
		this.antiguedad = antiguedad;
		this.numeroDeHijos = numeroDeHijos;
		this.vacaciones = vacaciones;

		this.irpf = calcularIrpf(this.sueldoBrutoTotal);
		this.sueldoNeto = calcularSueldoNeto(this.sueldoBrutoTotal, this.irpf);

		this.material.addAll(CategoriaEmpleado.generateKit(this.categoria.getNombre()));
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

	private static double calcularBrutoTotal(double sueldoBruto, int antiguedad, int numeroDeHijos, CategoriaEmpleado categoria) {
		double sumaAntiguedad = antiguedad * 20 * 12;
		double sumaHijos = numeroDeHijos * 10 * 12;
		double bonoCategoria = sueldoBruto * categoria.getBonificacion();
		
		return sueldoBruto + bonoCategoria + sumaAntiguedad + sumaHijos;
	}
	
	private static double calcularBrutoTotal(double sueldoBruto, CategoriaEmpleado categoria) {
		double bonoCategoria = sueldoBruto * categoria.getBonificacion();
		
		return sueldoBruto + bonoCategoria;
	}

	public double gastoAnual() {
		double gastoAnual = this.sueldoBrutoTotal;
				
		if(!this.material.isEmpty()) {
			gastoAnual += this.material
				.stream()
				.mapToDouble(material -> {
					if(material.getCategoria().equals("coche")) {
						switch (this.categoria.getNombre()) {
						case "manager":
							return material.getPrecio() * (52 - this.vacaciones);
						case "arquitecto":
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

	public CategoriaEmpleado getCategoria() {
		return categoria;
	}
}
