package Practica4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Practica {

	// Constante que define los kilometros que tiene cada etapa.
	private static final double[] etapas = { 74.12, 63.89, 67.37, 84.03 };

	// Hecho por ?
	public static void main(String[] args) {
		ArrayList<String[]> equipos = new ArrayList<>();
		ArrayList<double[]> tiempos = new ArrayList<>();

		Scanner sc = new Scanner(System.in);

		boolean masEquipos = true;
		int nEquipo = 1;
		while (masEquipos) {

			String[] equipo = new String[3];
			double[] tiempoEquipo = new double[etapas.length];

			System.out.print("Introduce el nombre del equipo " + nEquipo + ": ");
			equipo[0] = sc.nextLine();
			System.out.print("Introduce el nombre del primer componente: ");
			equipo[1] = sc.nextLine();
			System.out.print("Introduce el nombre del segundo componente: ");
			equipo[2] = sc.nextLine();

			for (int i = 0; i < etapas.length; i++) {
				boolean estaBien = false;
				do {
					System.out.print("Introduce el tiempo de la etapa " + (i + 1) + ": ");
					try {
						double valor = Double.parseDouble(sc.nextLine());
						tiempoEquipo[i] = valor;
						estaBien = true;
					} catch (NumberFormatException e) {
						System.out.println("Introduce un valor correcto!!");
					}
				} while (!estaBien);
			}

			equipos.add(equipo);
			tiempos.add(tiempoEquipo);
			nEquipo++;
			System.out.print("Quieres introducir otro equipo? (y/n): ");

			if (sc.nextLine().toLowerCase().equals("n"))
				masEquipos = false;
		}

		ArrayList<String[]> equiposClasificados = clasificarEquipos(equipos, tiempos);

		System.out.println("Los 3 primeros equipos son: ");
		{
			int equiposSize = equiposClasificados.size();
			int max = equiposSize < 3 ? equiposSize : 3;

			for (int i = 0; i < max; i++) {
				String[] equipo = equiposClasificados.get(i);
				double kmh = calcularKmhEquipo(tiempos.get(equipos.indexOf(equipo)));
				System.out.println("El equipo en la posicion " + (i + 1) + " es " + equipo[0]
						+ " con una velocidad media de " + redondearDecimales(kmh, 2) + "km/h");
			}
		}

		int[] indices = corredorMasRapidoPorEtapa(tiempos);
		for (int i = 0; i < indices.length; i++) {
			System.out.println(
					"El corredor más rápido en la etapa " + (i + 1) + " es " + equipos.get(indices[i])[i % 2 + 1]
							+ " del equipo " + equipos.get(indices[i])[0] + " con una velocidad media de "
							+ redondearDecimales(calcularKmh(etapas[i], tiempos.get(indices[i])[i]), 2) + " km/h.");
		}

		sc.close();
	}

	// Datos de ejemplo
	/*
	 * public static void apuntarComponentes(ArrayList<String[]> equipos,
	 * ArrayList<double[]> tiempos) { equipos.add(new String[] { "Gustavo Runners",
	 * "Marta Diaz", "Peter" }); tiempos.add(new double[] { 5.50, 4.30, 4.50, 6.30
	 * });
	 *
	 * equipos.add(new String[] { "Luisa Speedsters", "Carlos Perez", "Anna" });
	 * tiempos.add(new double[] { 6.20, 5.10, 4.90, 7.00 });
	 *
	 * equipos.add(new String[] { "Juan Sprinters", "Elena Rodriguez", "Carlos" });
	 * tiempos.add(new double[] { 5.80, 4.70, 4.60, 6.50 });
	 *
	 * equipos.add(new String[] { "Laura Racers", "David Gomez", "Emma" });
	 * tiempos.add(new double[] { 5.90, 4.60, 4.70, 6.70 });
	 *
	 * equipos.add(new String[] { "Roberto Blazers", "Sara Gonzalez", "Michael" });
	 * tiempos.add(new double[] { 5.70, 4.40, 4.80, 6.90 });
	 *
	 * equipos.add(new String[] { "Diego Sprinters", "Olivia Smith", "Lucas" });
	 * tiempos.add(new double[] { 6.00, 4.50, 4.60, 6.80 });
	 *
	 * equipos.add(new String[] { "Maria Rushers", "Juan Martinez", "Sophia" });
	 * tiempos.add(new double[] { 5.60, 4.80, 4.90, 6.60 });
	 *
	 * equipos.add(new String[] { "Daniel Racers", "Paula Ruiz", "Liam" });
	 * tiempos.add(new double[] { 5.50, 4.90, 4.60, 6.40 });
	 *
	 * equipos.add(new String[] { "Natalia Blazers", "Hector Sanchez", "Isabella"
	 * }); tiempos.add(new double[] { 5.80, 4.70, 4.70, 6.30 });
	 *
	 * equipos.add(new String[] { "Pablo Speedsters", "Eva Hernandez", "Noah" });
	 * tiempos.add(new double[] { 6.10, 5.00, 4.80, 6.20 }); // }
	 */

	// Devuelve la lista de todos los equipos de manera ordenada por tiempo.
	// Hecho por Juan
	private static ArrayList<String[]> clasificarEquipos(ArrayList<String[]> equipos, ArrayList<double[]> tiempos) {
		// Primero, clonamos el ArrayLisy de los equipos, ya que lo vamos a mutar.
		ArrayList<String[]> equiposOrdenados = new ArrayList<>(equipos);
		/*
		 * Aquí utilizamos el metodo sort, el cual utiliza el algoritmo Quicksort para
		 * ordenar elementos. https://es.wikipedia.org/wiki/Quicksort
		 * 
		 * El metodo de implementacion que tiene Java para este metodo es la posibilidad
		 * de pasar una lambda (funcion anonima) al metodo. Esta funcion lamda recibe 2
		 * parametros, siendo el primero un elemento a comparar con el segundo para
		 * determinar a que lado del pivote se pone (esto se explica en como el
		 * algoritmo quicksort funciona).
		 * 
		 * En base a lo que devuelva esta funcion lambda, determina en que lado del
		 * pivote se pone. Si devuelve un valor mayor a uno, se pone en el lado derecho
		 * del pivote, indicando que es un valor mayor al pivote; Si devuelve un valor
		 * menor a uno, se queda en el lado izquierdo, indicando que es un valor menor
		 * al pivote. Si lo que se devuelve es 0, el valor se queda en su posicion.
		 * 
		 * Aqui se escribe el metodo de una manera más simplificada:
		 * 
		 * equiposOrdenados.sort((e1, e2) -> {
		 * 
		 * int indiceE1 = equipos.indexOf(e1); int indiceE2 = equipos.indexOf(e2);
		 * 
		 * double[] tiemposE1 = tiempos.get(indiceE1); double[] tiemposE2 =
		 * tiempos.get(indiceE2);
		 * 
		 * 
		 * double mediaE1 = calcularMedia(tiemposE1); double mediaE2 =
		 * calcularMedia(tiemposE2);
		 * 
		 * return Double.compare(mediaE1, mediaE2); });
		 * 
		 */
		equiposOrdenados.sort((e1, e2) -> Double.compare(calcularMedia(tiempos.get(equipos.indexOf(e1))),
				calcularMedia(tiempos.get(equipos.indexOf(e2)))));
		return equiposOrdenados;
	}

	// Hecho por ?
	private static double calcularKmh(double k, double h) {
		return k / h;
	}

	// Hecho por ?
	private static double calcularKmhEquipo(double[] tiempoEquipo) {
		double kmhTotal = Arrays.stream(etapas).sum();
		double tiempoTotal = Arrays.stream(tiempoEquipo).sum();
		return calcularKmh(kmhTotal, tiempoTotal);
	}

	// Hecho por ?
	private static double redondearDecimales(double n, int cantidad) {
		double decimales = Math.pow(10.0, cantidad);

		return Math.round(n * decimales) / decimales;
	}

	// Hecho por ?
	public static int[] corredorMasRapidoPorEtapa(ArrayList<double[]> tiempos) {
		int[] indices = new int[etapas.length];
		for (int i = 0; i < etapas.length; i++) {
			double minTiempo = Double.MAX_VALUE;
			int minIndex = -1;
			for (int j = 0; j < tiempos.size(); j++) {
				if (tiempos.get(j)[i] < minTiempo) {
					minTiempo = tiempos.get(j)[i];
					minIndex = j;
				}
			}
			indices[i] = minIndex;
		}
		return indices;
	}

	// Hecho por ?
	private static double calcularMedia(double[] tiempos) {
		double sum = 0;
		for (double tiempo : tiempos) {
			sum += tiempo;
		}
		return sum / tiempos.length;
	}
}
