package Practica3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class Practica {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ArrayList<Paciente> listaPacientes;

		System.out.print("¿Quieres utilizar los pacientes de prueba? (s/n): ");

		if (sc.nextLine().toLowerCase().equals("n")) {
			listaPacientes = new ArrayList<>();
			boolean otroPacienteMas = true;

			do {
				
				String nombre;
				do {
					System.out.print("Introduce el nombre del paciente: ");
					nombre = sc.nextLine();
				} while (nombre.isEmpty());

				Double peso = null;
				do {
					System.out.print("Introduce el peso del paciente (en kilos): ");
					String input = sc.nextLine();
					try {
						peso = Double.parseDouble(input);
					} catch (NumberFormatException e) {
						System.out.println("Introduce un numero valido!");
						peso = null;
					}
				} while (peso == null);

				Double altura = null;
				do {
					System.out.print("Introduce la altura del paciente (en metros): ");
					String input = sc.nextLine();
					try {
						altura = Double.parseDouble(input);
					} catch (NumberFormatException e) {
						System.out.println("Introduce un numero valido!");
						altura = null;
					}
				} while (altura == null);
				
				listaPacientes.add(new Paciente(nombre, peso, altura));
				
				System.out.print("¿Quieres añadir otro paciente? (s/n): ");
				if (sc.nextLine().toLowerCase().equals("n")) otroPacienteMas = false;
				
			} while (otroPacienteMas);

		} else {
			listaPacientes = Paciente.generarPacientesPrueba();
		}

		System.out.println("\nResultados del programa: \n");

		System.out.println("El paciente mas bajo es " + getPacienteMasBajo(listaPacientes).getNombre());
		System.out.println("El paciente con más peso es " + getPacienteMasPesado(listaPacientes).getNombre());

		Paciente pacienteIMCmasAlto = getPacienteIMCMasAlto(listaPacientes);
		Paciente pacienteIMCmasBajo = getPacienteIMCMasBajo(listaPacientes);

		System.out.println("\nEl paciente con el IMC mas alto es " + pacienteIMCmasAlto.getNombre() + " con un IMC de "
				+ redondearDecimales(pacienteIMCmasAlto.calcularIMC()));
		System.out.println("El paciente con el IMC mas bajo es " + pacienteIMCmasBajo.getNombre() + " con un IMC de "
				+ redondearDecimales(pacienteIMCmasBajo.calcularIMC()));

		System.out.println("\nLa media de altura es " + getMediaAltura(listaPacientes) + " y la mediana es "
				+ getMedianaAltura(listaPacientes));
		System.out.println("La media de peso es " + getMediaPeso(listaPacientes) + " y la mediana es "
				+ getMedianaPeso(listaPacientes));

		System.out.println();

		getClasificacionPacientes(listaPacientes).forEach((peso, cantidad) -> {
			System.out.println("Hay " + cantidad + " con " + peso);
		});

		System.out.println();

		listaPacientes.forEach(paciente -> {
			System.out.println(paciente.getNombre() + " debe cumplir con una dieta de "
					+ paciente.getDieta().getCalorias() + "kcal");
		});

		sc.close();
	}

	private static double redondearDecimales(double n) {
		return Math.round(n * 100.0) / 100.0;
	}

	private static double calcularMediana(ArrayList<Double> datos) {
		Collections.sort(datos);
		int longitudDatos = datos.size();
		double mediana;

		if (longitudDatos % 2 == 0) {
			Double valor1 = datos.get(longitudDatos / 2 - 1); // El primer valor en el medio
			Double valor2 = datos.get(longitudDatos / 2); // El segundo valor en el medio
			mediana = (valor1 + valor2) / 2;
		} else {
			mediana = datos.get(longitudDatos / 2); // Si es impar, solo hay un valor en el medio
		}

		return mediana;
	}

	public static Paciente getPacienteMasBajo(ArrayList<Paciente> listaPacientes) {
		Paciente pacienteMasBajo = listaPacientes.get(0);

		for (Paciente paciente : listaPacientes) {
			if (paciente.getAltura() < pacienteMasBajo.getAltura()) {
				pacienteMasBajo = paciente;
			}
		}

		return pacienteMasBajo;
	}

	public static Paciente getPacienteMasPesado(ArrayList<Paciente> listaPacientes) {
		Paciente pacienteMasPesado = listaPacientes.get(0);

		for (Paciente paciente : listaPacientes) {
			if (paciente.getPeso() > pacienteMasPesado.getPeso()) {
				pacienteMasPesado = paciente;
			}
		}

		return pacienteMasPesado;
	}

	public static Paciente getPacienteIMCMasAlto(ArrayList<Paciente> listaPacientes) {
		Paciente pacienteIMCMasAlto = listaPacientes.get(0);

		for (Paciente paciente : listaPacientes) {
			if (paciente.calcularIMC() > pacienteIMCMasAlto.calcularIMC()) {
				pacienteIMCMasAlto = paciente;
			}
		}

		return pacienteIMCMasAlto;
	}

	public static Paciente getPacienteIMCMasBajo(ArrayList<Paciente> listaPacientes) {
		Paciente pacienteIMCMasBajo = listaPacientes.get(0);

		for (Paciente paciente : listaPacientes) {
			if (paciente.calcularIMC() < pacienteIMCMasBajo.calcularIMC()) {
				pacienteIMCMasBajo = paciente;
			}
		}

		return pacienteIMCMasBajo;
	}

	public static double getMediaAltura(ArrayList<Paciente> listaPacientes) {
		double alturasSumadas = 0;

		for (Paciente paciente : listaPacientes) {
			alturasSumadas += paciente.getAltura();
		}

		return redondearDecimales(alturasSumadas / listaPacientes.size());
	}

	public static double getMediaPeso(ArrayList<Paciente> listaPacientes) {
		double pesosSumados = 0;

		for (Paciente paciente : listaPacientes) {
			pesosSumados += paciente.getPeso();
		}

		return redondearDecimales(pesosSumados / listaPacientes.size());
	}

	public static double getMedianaAltura(ArrayList<Paciente> listaPacientes) {
		ArrayList<Double> alturaPacientes = new ArrayList<>();

		for (Paciente paciente : listaPacientes) {
			alturaPacientes.add(paciente.getAltura());
		}

		return redondearDecimales(calcularMediana(alturaPacientes));
	}

	public static double getMedianaPeso(ArrayList<Paciente> listaPacientes) {
		ArrayList<Double> pesoPacientes = new ArrayList<>();

		for (Paciente paciente : listaPacientes) {
			pesoPacientes.add(paciente.getPeso());
		}

		return redondearDecimales(calcularMediana(pesoPacientes));
	}

	public static Map<String, Integer> getClasificacionPacientes(ArrayList<Paciente> listaPaciente) {
		Map<String, Integer> clasificacion = new HashMap<>();
		int pesoBajo = 0;
		int pesoNormal = 0;
		int sobrepeso = 0;
		int obesidad = 0;

		for (Paciente paciente : listaPaciente) {
			switch (paciente.getDieta()) {
			case BAJO_PESO:
				pesoBajo++;
				break;
			case PESO_NORMAL:
				pesoNormal++;
				break;
			case SOBREPESO:
				sobrepeso++;
			case OBESIDAD:
				obesidad++;
				break;
			}
		}

		clasificacion.put("Peso Bajo", pesoBajo);
		clasificacion.put("Peso Normal", pesoNormal);
		clasificacion.put("Sobrepeso", sobrepeso);
		clasificacion.put("Obesidad", obesidad);

		return clasificacion;

	}
}