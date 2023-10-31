package Practica3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Map;

public class Practica {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		// Incializamos el Array List de pacientes en los que guardaremos los pacientes
		// con los que trabajaremos durante todo el programa
		ArrayList<Paciente> listaPacientes;

		System.out.print("¿Quieres utilizar los pacientes de prueba? (s/n): ");

		// Si el usuario introduce la n, empezaras a introducir los pacientes
		// manualmente, si no, utilizara los que estan de ejemplo en la clase Paciente
		// con el metido estatico Paciente.generarPacientesPrueba
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
					// En este bloque try catch nos aseguramos de que el valor sea un doble, si no,
					// volvemos a preguntar el valor de nuevo en el catch. Igual con la altura.
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

				System.out.print("¿Quieres anadir otro paciente? (s/n): ");
				if (sc.nextLine().toLowerCase().equals("n"))
					otroPacienteMas = false;

			} while (otroPacienteMas);

		} else {
			listaPacientes = Paciente.generarPacientesPrueba();
		}

		System.out.println("\nResultados del programa: \n");

		System.out.println("El paciente mas bajo es " + getPacienteMasBajo(listaPacientes).getNombre());
		System.out.println("El paciente con mas peso es " + getPacienteMasPesado(listaPacientes).getNombre());

		Paciente pacienteIMCmasAlto = getPacienteIMCMasAlto(listaPacientes);
		Paciente pacienteIMCmasBajo = getPacienteIMCMasBajo(listaPacientes);

		System.out.println("\nEl paciente con el IMC mas alto es " + pacienteIMCmasAlto.getNombre() + " con un IMC de "
				+ redondearDecimales(pacienteIMCmasAlto.calcularIMC(), 2));
		System.out.println("El paciente con el IMC mas bajo es " + pacienteIMCmasBajo.getNombre() + " con un IMC de "
				+ redondearDecimales(pacienteIMCmasBajo.calcularIMC(), 2));

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

	// Metodo reutilizable para redondear a 2 decimales de un doble.
	/**
	 * @param n Numero a redondear
	 * @param i Cantidad de decimales a los que redondear
	 * @return Devuelve un doble redondeado a los decimales especificados
	 */
	private static double redondearDecimales(double n, int i) {
		double decimales = Math.pow(10.0, i);

		return Math.round(n * decimales) / decimales;
	}

	/**
	 * Metodo reutilizable para calcular la mediana dado una lista de nmeros
	 * 
	 * @param datos ArrayList de numeros a ordenar
	 * @return Devuelve la mediana en un doble
	 */
	private static double calcularMediana(ArrayList<Double> datos) {
		// Para calcular la mediana, necesitamos ordenar de menor a mayor los numeros
		// del array con el que vayamos a operar.
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

	// Los 4 siguientes metodos tienen casi la misma funcionalidad, por lo que se
	// explica en el primero y se aplica a los 4 siguientes
	public static Paciente getPacienteMasBajo(ArrayList<Paciente> listaPacientes) {
		// Empezamos asumiendo que el paciente 0 es el mas bajo.
		Paciente pacienteMasBajo = listaPacientes.get(0);

		// Iteramos por cada paciente, comparando la altura del paciente guardado en la
		// variable con el siguiente.
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

	// Los 2 metodos de media funcionan bastante similar, con la primera explicacion
	// se entiende el segundo.
	public static double getMediaAltura(ArrayList<Paciente> listaPacientes) {
		// Sumamos todas las alturas de los pacientes en la variable alturasSumadas
		double alturasSumadas = 0;

		for (Paciente paciente : listaPacientes) {
			alturasSumadas += paciente.getAltura();
		}
		// Devolvemos el valor calculando la media despues de redondearlo
		return redondearDecimales(alturasSumadas / listaPacientes.size(), 2);
	}

	public static double getMediaPeso(ArrayList<Paciente> listaPacientes) {
		double pesosSumados = 0;

		for (Paciente paciente : listaPacientes) {
			pesosSumados += paciente.getPeso();
		}

		return redondearDecimales(pesosSumados / listaPacientes.size(), 2);
	}

	// Los 2 metodos de media funcionan bastante similar, con la primera explicacion
	// se entiende el segundo.
	public static double getMedianaAltura(ArrayList<Paciente> listaPacientes) {
		// Creamos un ArrayList de todas las alturas de los pacientes
		ArrayList<Double> alturaPacientes = new ArrayList<>();

		for (Paciente paciente : listaPacientes) {
			alturaPacientes.add(paciente.getAltura());
		}
		// Devolvemos el resultado de la mediana redondeado
		return redondearDecimales(calcularMediana(alturaPacientes), 2);
	}

	public static double getMedianaPeso(ArrayList<Paciente> listaPacientes) {
		// Guardamos todos los pesos de los pacientes en un array, el cual pasamos a
		// calcularMediana
		ArrayList<Double> pesoPacientes = new ArrayList<>();

		for (Paciente paciente : listaPacientes) {
			pesoPacientes.add(paciente.getPeso());
		}

		return redondearDecimales(calcularMediana(pesoPacientes), 2);
	}

	// Recuperamos la clasificacion de los pacientes en pares clave valor.
	public static Map<String, Integer> getClasificacionPacientes(ArrayList<Paciente> listaPaciente) {
		// Creamos un LinkedHashMap para guardar los pares en orden de introduccion.
		Map<String, Integer> clasificacion = new LinkedHashMap<>();
		// 4 variables en las que guardaremos cuanta gente hay de cada peso.
		int pesoBajo = 0;
		int pesoNormal = 0;
		int sobrepeso = 0;
		int obesidad = 0;

		for (Paciente paciente : listaPaciente) {
			// Por cada paciente, verificamos que tipo de dieta tiene mediante el enum
			// TipoDieta.
			switch (paciente.getDieta()) {
			case BAJO_PESO:
				pesoBajo++;
				break;
			case PESO_NORMAL:
				pesoNormal++;
				break;
			case SOBREPESO:
				sobrepeso++;
				break;
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