package practica_POO03;

import java.util.EnumMap;
import java.util.List;

public class Empleado {
	private String nombre;
	private String apellidos;
	private int edad; // Opcional
	private String direccion; // Opcional
	private double sueldoBruto;
	private double irpf; // Calcular en el constructor
	private CategoriasEmpleado categoria = CategoriasEmpleado.JUNIOR; // Opcional
	private double sueldoNeto; // Se calcula, opcional en constructor
	private int antiguedad = 0; // Opcional
	private int numeroDeHijos = 0; // Opcional
	private int vacaciones; // Opcional
	private List<Material> material;
	
	private static final EnumMap<CategoriasEmpleado, Integer> stock = new EnumMap<>(CategoriasEmpleado.class);

    static {
        stock.put(CategoriasEmpleado.JUNIOR, 4);
        stock.put(CategoriasEmpleado.SENIOR, 3);
        stock.put(CategoriasEmpleado.ARQUITECTO, 2);
        stock.put(CategoriasEmpleado.MANAGER, 1);
    }

	public Empleado(String nombre, String apellidos, double sueldoBruto) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.sueldoBruto = sueldoBruto;

		this.irpf = calcularIrpf(sueldoBruto);
		this.sueldoNeto = calcularSueldoNeto(this.sueldoBruto, this.irpf, this.categoria, this.antiguedad,
				this.numeroDeHijos);
		
		if(stock.get(categoria) <= 0) {
			this.material = categoria.getListaMateriales();
			restarStock(categoria);
		}
	}

	public Empleado(String nombre, String apellidos, double sueldoBruto, int edad, String direccion,
			CategoriasEmpleado categoria, int antiguedad, int numeroDeHijos, int vacaciones) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.sueldoBruto = sueldoBruto;
		this.edad = edad;
		this.direccion = direccion;
		this.categoria = categoria;
		this.antiguedad = antiguedad;
		this.numeroDeHijos = numeroDeHijos;
		this.vacaciones = vacaciones;

		this.irpf = calcularIrpf(sueldoBruto);
		this.sueldoNeto = calcularSueldoNeto(this.sueldoBruto, this.irpf, this.categoria, this.antiguedad,
				this.numeroDeHijos);
		
		if(stock.get(categoria) <= 0) {
			this.material = categoria.getListaMateriales();
			restarStock(categoria);
		}
		
	}

	/**
	 * @return Devuelve el porcentaje en formato decimal (10% = 0.1)
	 */
	private static double calcularIrpf(double sueldoBruto) {
		double calculoBase = sueldoBruto / 15000;
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

	private static double calcularSueldoNeto(double sueldoBruto, double irpf, CategoriasEmpleado categoria,
			int antiguedad, int numeroDeHijos) {

		double sumaAntiguedad = antiguedad * 20 * 12;
		double sumaHijos = numeroDeHijos * 10 * 12;

		double sueldoBrutoTotal = sueldoBruto + (sueldoBruto * categoria.getBonificacion()) + sumaAntiguedad
				+ sumaHijos;

		return sueldoBrutoTotal - (sueldoBrutoTotal * irpf);
	}

	private static void restarStock(CategoriasEmpleado categoria) {
        stock.merge(categoria, -1, Integer::sum);
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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setSueldoBruto(double sueldoBruto) {
		this.sueldoBruto = sueldoBruto;
	}

	public void setIrpf(Double irpf) {
		this.irpf = irpf;
	}

	public void setSueldoNeto(Double sueldoNeto) {
		this.sueldoNeto = sueldoNeto;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public void setNumeroDeHijos(Integer numeroDeHijos) {
		this.numeroDeHijos = numeroDeHijos;
	}

	public void setVacaciones(Integer vacaciones) {
		this.vacaciones = vacaciones;
	}
}
