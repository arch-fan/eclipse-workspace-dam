package Practica4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Practica {

	// Constante que define los kilometros que tiene cada etapa.
	private static final double[] etapas = { 74.12, 63.89, 67.37, 84.03 };

	// Hecho por todos
	public static void main(String[] args) {
		// Variables en las que guardamos tanto los tiempos como los equipos, guiandonos
		// por la posicion.
		ArrayList<String[]> equipos = new ArrayList<>();
		ArrayList<double[]> tiempos = new ArrayList<>();

		Scanner sc = new Scanner(System.in);

		boolean masEquipos = true;
		int nEquipo = 1;
		// Bucle principal para introducir datos
		while (masEquipos) {

			String[] equipo = new String[3];
			double[] tiempoEquipo = new double[etapas.length];

			System.out.print("Introduce el nombre del equipo " + nEquipo + ": ");
			equipo[0] = sc.nextLine();
			System.out.print("Introduce el nombre del primer componente: ");
			equipo[1] = sc.nextLine();
			System.out.print("Introduce el nombre del segundo componente: ");
			equipo[2] = sc.nextLine();

			// Bucle para guaradr el tiempo de cada etapa.
			for (int i = 0; i < etapas.length; i++) {
				boolean estaBien = false;
				do {
					System.out.print("Introduce el tiempo de la etapa " + (i + 1) + ": ");
					// Utilizamos un try catch para asegurarnos de que el valor introducido sea un
					// double.
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

			// Si el usuario introduce n, deja de introducir valores. Si le da enter, por
			// defecto te pide otro equipo.
			if (sc.nextLine().toLowerCase().equals("n"))
				masEquipos = false;
		}

		// Invocamos el metodo para ordenar los equipos por la clasificacion.
		ArrayList<String[]> equiposClasificados = clasificarEquipos(equipos, tiempos);

		System.out.println("Los 3 primeros equipos son: ");
		{
			int equiposSize = equiposClasificados.size();
			// Utilizamos un operador ternario para decidir cuantas posiciones se van a
			// mostrar, ya que si hay menos de 3 equipos, nos daria error el programa.
			int max = equiposSize < 3 ? equiposSize : 3;

			for (int i = 0; i < max; i++) {
				String[] equipo = equiposClasificados.get(i);
				double kmh = calcularKmhEquipo(tiempos.get(equipos.indexOf(equipo)));
				// Muestra los tres primeros clasificados
				System.out.println("El equipo en la posicion " + (i + 1) + " es " + equipo[0]
						+ " con una velocidad media de " + redondearDecimales(kmh, 2) + "km/h");
			}
		}

		// Esta variable invoca al metodo y guarda el indice del equipo ganador en la
		// etapa en la que se encuentra.
		int[] indices = corredorMasRapidoPorEtapa(tiempos);
		for (int i = 0; i < indices.length; i++) {
			System.out.println(
					"El corredor más rápido en la etapa " + (i + 1) + " es " + equipos.get(indices[i])[i % 2 + 1]
							+ " del equipo " + equipos.get(indices[i])[0] + " con una velocidad media de "
							+ redondearDecimales(calcularKmh(etapas[i], tiempos.get(indices[i])[i]), 2) + " km/h.");
		}

		sc.close();
	}

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

	// Hecho por David
	private static double calcularKmh(double k, double h) {
		// Dividimos los km entre las horas que hayamos seleccionado
		return k / h;
	}

	// Hecho por Juan
	private static double calcularKmhEquipo(double[] tiempoEquipo) {
		// Utilizamos stream para iterar todos los elementos del array, y con el metodo
		// sum, sumamos todos los valores en la misma variable
		double kmhTotal = Arrays.stream(etapas).sum();
		double tiempoTotal = Arrays.stream(tiempoEquipo).sum();
		// Devolvemos el metodo para calcular los kmh totales.
		return calcularKmh(kmhTotal, tiempoTotal);
	}

	// Hecho por Pedro
	// Pasamos de primer parametro el numero que queremos devolver redondeado, y de
	// segundo parametro, la cantidad de decimales que queramos mostrar.
	private static double redondearDecimales(double n, int cantidad) {
		// Hace la potencia de 10, elevado a la cantidad de decimales.
		double decimales = Math.pow(10.0, cantidad);
		// Devuelve el redondeo de numeros.
		return Math.round(n * decimales) / decimales;
	}

	// Hecho por David y Jorge
	public static int[] corredorMasRapidoPorEtapa(ArrayList<double[]> tiempos) {
		// Creamos un array con la cantidad de posiciones como etapas haya.
		int[] indices = new int[etapas.length];
		// Iteramos cada etapa
		for (int i = 0; i < etapas.length; i++) {
			// Guardamos el valor maximo de doble, que despues se reemplazara por el menos
			// tiempo de la etapa.
			double minTiempo = Double.MAX_VALUE;
			// Guardamos el indice del equipo con el menor tiempo de la etapa
			int minIndex = -1;
			// Iteramos todos los tiempos guardados.
			for (int j = 0; j < tiempos.size(); j++) {
				// Verificamos si el tiempo de la etapa y de la posicion del tiempo del equipo
				// en esa etapa es menor al minTiempo.
				if (tiempos.get(j)[i] < minTiempo) {
					minTiempo = tiempos.get(j)[i];
					minIndex = j;
				}
			}
			indices[i] = minIndex;
		}
		return indices;
	}

	// Hecho por Jorge y Pedro
	// Calcula la media de un array de numeros, que en este caso utilizamos para
	// calcular la media de los tiempos.
	private static double calcularMedia(double[] tiempos) {
		double sum = 0;
		for (double tiempo : tiempos) {
			sum += tiempo;
		}
		return sum / tiempos.length;
	}
}
