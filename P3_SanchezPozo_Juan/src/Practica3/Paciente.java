package Practica3;

import java.util.ArrayList;

public class Paciente {
	private String nombre;
	private double peso;
	private double altura;
	private TipoDieta dieta;

	private static final double IMC_PESO_BAJO = 18.5;
	private static final double IMC_PESO_NORMAL = 25.0;
	private static final double IMC_SOBREPESO = 40.0;

	public Paciente(String nombre, double peso, double altura) {
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

		return pacientes;
	}

	public String getNombre() {
		return nombre;
	}

	public double getPeso() {
		return peso;
	}

	public double getAltura() {
		return altura;
	}

	public TipoDieta getDieta() {
		return dieta;
	}

	public void setDieta(TipoDieta dieta) {
		this.dieta = dieta;
	}

	public double calcularIMC() {
		return peso / Math.pow(altura, 2);
	}
}
