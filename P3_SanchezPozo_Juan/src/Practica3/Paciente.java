package Practica3;

import java.util.ArrayList;

//Creamos una clase Paciente para representar a una persona con atributos como nombre, peso, altura y tipo de dieta. Esta clase nos permite tener sus propios métodos, asi podemos manejarlos de una manera mas simple.
public class Paciente {

	private String nombre; // Nombre del paciente.
	private double peso; // Peso del paciente en kilogramos.
	private double altura; // Altura del paciente en metros.
	private TipoDieta dieta; // Tipo de dieta del paciente.

	private static final double IMC_PESO_BAJO = 18.5; // Constante para el IMC de bajo peso. Es el tope de IMC para peso
														// bajo.
	private static final double IMC_PESO_NORMAL = 25.0; // Constante para el IMC de peso normal. Es el tope de IMC para
														// peso normal.
	private static final double IMC_SOBREPESO = 40.0; // Constante para el IMC de sobrepeso. Es el tope de IMC para
														// sobrepeso.

	/**
	 * Constructor de la clase Paciente.
	 * 
	 * @param nombre Nombre del paciente.
	 * @param peso   Peso del paciente en kilogramos.
	 * @param altura Altura del paciente en metros.
	 */
	public Paciente(String nombre, double peso, double altura) {
		
		if (peso < 0) {
	        throw new IllegalArgumentException("El peso no puede ser menor a 0");
	    }
		
		this.nombre = nombre;
		this.peso = peso;
		this.altura = altura;

		double imc = calcularIMC();

		if (imc < IMC_PESO_BAJO) {
			this.dieta = TipoDieta.BAJO_PESO;
		} else if (imc < IMC_PESO_NORMAL) {
			this.dieta = TipoDieta.PESO_NORMAL;
		} else if (imc < IMC_SOBREPESO) {
			this.dieta = TipoDieta.SOBREPESO;
		} else {
			this.dieta = TipoDieta.OBESIDAD;
		}
	}

	/**
	 * Genera una lista de pacientes de prueba.
	 * 
	 * @return Una lista de pacientes con datos de ejemplo.
	 */
	public static ArrayList<Paciente> generarPacientesPrueba() {
		ArrayList<Paciente> pacientes = new ArrayList<>();

		pacientes.add(new Paciente("Paco", 70.5, 1.75));
		pacientes.add(new Paciente("María", 65.2, 1.68));
		pacientes.add(new Paciente("Carlos", 80.0, 1.80));
		pacientes.add(new Paciente("Laura", 55.7, 1.60));
		pacientes.add(new Paciente("Pedro", 90.3, 1.90));
		pacientes.add(new Paciente("Luisa", 75.1, 1.72));
		pacientes.add(new Paciente("Ana", 60.8, 1.65));
		pacientes.add(new Paciente("Miguel", 85.6, 1.78));
		pacientes.add(new Paciente("Sofía", 58.9, 1.63));
		pacientes.add(new Paciente("David", 77.4, 1.76));
		pacientes.add(new Paciente("Javier", 72.3, 1.79));
		pacientes.add(new Paciente("Isabel", 67.5, 1.70));
		pacientes.add(new Paciente("Juan", 75.8, 1.81));
		pacientes.add(new Paciente("Elena", 60.2, 1.68));
		pacientes.add(new Paciente("Francisco", 88.0, 1.85));
		pacientes.add(new Paciente("Carmen", 70.7, 1.73));
		pacientes.add(new Paciente("José", 62.4, 1.66));
		pacientes.add(new Paciente("Raquel", 68.9, 1.76));
		pacientes.add(new Paciente("Pablo", 82.3, 1.82));
		pacientes.add(new Paciente("Lucía", 56.5, 1.63));
		pacientes.add(new Paciente("Andrés", 79.6, 1.77));
		pacientes.add(new Paciente("Natalia", 63.8, 1.69));
		pacientes.add(new Paciente("Fernando", 85.1, 1.84));
		pacientes.add(new Paciente("Marta", 66.0, 1.71));
		pacientes.add(new Paciente("Jorge", 73.7, 1.78));
		pacientes.add(new Paciente("Patricia", 59.3, 1.65));
		pacientes.add(new Paciente("Alberto", 76.2, 1.80));
		pacientes.add(new Paciente("Sara", 64.7, 1.67));
		pacientes.add(new Paciente("Antonio", 90.5, 1.88));
		pacientes.add(new Paciente("Beatriz", 131.2, 1.66));
		pacientes.add(new Paciente("Manuel", 74.9, 1.75));
		pacientes.add(new Paciente("Lorena", 128.2, 1.62));
		pacientes.add(new Paciente("Roberto", 83.3, 1.83));
		pacientes.add(new Paciente("Paula", 61.6, 1.64));
		pacientes.add(new Paciente("Alberto", 78.4, 1.79));
		pacientes.add(new Paciente("Eva", 65.9, 1.75));
		pacientes.add(new Paciente("Ricardo", 87.7, 1.86));
		pacientes.add(new Paciente("Luis", 71.2, 1.74));
		pacientes.add(new Paciente("Clara", 58.1, 1.61));
		pacientes.add(new Paciente("Óscar", 81.6, 1.81));
		pacientes.add(new Paciente("Marina", 68.3, 1.68));
		pacientes.add(new Paciente("Daniel", 75.6, 1.77));
		pacientes.add(new Paciente("Verónica", 62.9, 1.70));
		pacientes.add(new Paciente("Eduardo", 80.8, 1.82));
		pacientes.add(new Paciente("Nuria", 66.4, 1.71));
		pacientes.add(new Paciente("Gonzalo", 73.1, 1.76));
		pacientes.add(new Paciente("Laura", 55.7, 1.60));
		pacientes.add(new Paciente("Marcos", 122.4, 1.85));
		pacientes.add(new Paciente("Cristina", 70.0, 1.73));
		pacientes.add(new Paciente("Alejandro", 77.9, 1.78));
		pacientes.add(new Paciente("Silvia", 59.8, 1.66));
		pacientes.add(new Paciente("Gabriel", 84.4, 1.84));
		pacientes.add(new Paciente("Rosa", 67.3, 1.69));
		pacientes.add(new Paciente("Joaquín", 89.1, 1.87));
		pacientes.add(new Paciente("Elena", 63.6, 1.68));
		pacientes.add(new Paciente("Adrián", 72.8, 1.80));
		pacientes.add(new Paciente("Nerea", 61.2, 1.64));
		pacientes.add(new Paciente("José María", 79.5, 1.79));
		pacientes.add(new Paciente("Lucía", 65.0, 1.72));
		pacientes.add(new Paciente("Fernando", 74.3, 1.75));

		return pacientes;
	}

	/**
	 * Obtiene el nombre del paciente.
	 * 
	 * @return El nombre del paciente.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Obtiene el peso del paciente.
	 * 
	 * @return El peso del paciente en kilogramos.
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * Obtiene la altura del paciente.
	 * 
	 * @return La altura del paciente en metros.
	 */
	public double getAltura() {
		return altura;
	}

	/**
	 * Obtiene el tipo de dieta del paciente.
	 * 
	 * @return El tipo de dieta del paciente.
	 */
	public TipoDieta getDieta() {
		return dieta;
	}

	/**
	 * Establece el tipo de dieta del paciente.
	 * 
	 * @param dieta El nuevo tipo de dieta del paciente.
	 */
	public void setDieta(TipoDieta dieta) {
		this.dieta = dieta;
	}

	/**
	 * Calcula el Índice de Masa Corporal (IMC) del paciente.
	 * 
	 * @return El IMC del paciente.
	 */
	public double calcularIMC() {
		return peso / Math.pow(altura, 2);
	}
}
