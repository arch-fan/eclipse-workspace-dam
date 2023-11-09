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

//		apuntarComponentesEjemplo(equipos, tiempos);
		apuntarComponentes(equipos, tiempos);

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

		// Llamamos al metodo para imprimir el corredor mas rapido por etapa
		corredorMasRapidoPorEtapa(equipos, tiempos);

	}

	public static void apuntarComponentesEjemplo(ArrayList<String[]> equipos, ArrayList<double[]> tiempos) {
		equipos.add(new String[] { "Gustavo Runners", "Marta Diaz", "Peter" });
		tiempos.add(new double[] { 5.50, 4.30, 4.50, 6.30 });

		equipos.add(new String[] { "Luisa Speedsters", "Carlos Perez", "Anna" });
		tiempos.add(new double[] { 6.20, 5.10, 4.90, 7.00 });

		equipos.add(new String[] { "Juan Sprinters", "Elena Rodriguez", "Carlos" });
		tiempos.add(new double[] { 5.80, 4.70, 4.60, 6.50 });

		equipos.add(new String[] { "Laura Racers", "David Gomez", "Emma" });
		tiempos.add(new double[] { 5.90, 4.60, 4.70, 6.70 });

		equipos.add(new String[] { "Roberto Blazers", "Sara Gonzalez", "Michael" });
		tiempos.add(new double[] { 5.70, 4.40, 4.80, 6.90 });

		equipos.add(new String[] { "Diego Sprinters", "Olivia Smith", "Lucas" });
		tiempos.add(new double[] { 6.00, 4.50, 4.60, 6.80 });

		equipos.add(new String[] { "Maria Rushers", "Juan Martinez", "Sophia" });
		tiempos.add(new double[] { 5.60, 4.80, 4.90, 6.60 });

		// Equipo con bici electrica
		equipos.add(new String[] { "Daniel Racers", "Paula Ruiz", "Liam" });
		tiempos.add(new double[] { 5.50, 4.90, 4.60, 6.40, 2.00, 4.50, 4.60, 6.80 });

		equipos.add(new String[] { "Natalia Blazers", "Hector Sanchez", "Isabella" });
		tiempos.add(new double[] { 5.80, 1.10, 1.30, 6.30 });

		// Equipo con bici electrica
		equipos.add(new String[] { "Pablo Speedsters", "Eva Hernandez", "Noah" });
		tiempos.add(new double[] { 4.10, 5.00, 4.00, 6.20, 3.50, 1.30, 1.50, 1.0 });

	}

	// Hecho por todos
	// Metodo para recoger la entrada del usuario y añadirlos al array de datos
	public static void apuntarComponentes(ArrayList<String[]> equipos, ArrayList<double[]> tiempos) {

		Scanner sc = new Scanner(System.in);

		System.out.println("¡Bienvenido! Vamos a empezar a introducir datos de los ciclistas.");

		boolean masEquipos = true;
		int nEquipo = 1;
		// Bucle principal para introducir datos
		while (masEquipos) {

			String[] equipo = new String[3];

			System.out.print("Equipo " + (nEquipo) + " ¿Utilizas bicicletas electricas? (y/n): ");
			boolean bElectricas = sc.nextLine().toLowerCase().equals("y") ? true : false;

			int cantidadEtapas = bElectricas ? etapas.length * 2 : etapas.length;

			double[] tiempoEquipo = new double[cantidadEtapas];

			System.out.print("Introduce el nombre del equipo " + nEquipo + ": ");
			equipo[0] = sc.nextLine();
			System.out.print("Introduce el nombre del primer componente: ");
			equipo[1] = sc.nextLine();
			System.out.print("Introduce el nombre del segundo componente: ");
			equipo[2] = sc.nextLine();

			// Bucle para guardar el tiempo de cada etapa.
			for (int i = 0; i < cantidadEtapas; i++) {
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

	// Hecho por Jorge
	private static double calcularKmhEquipo(double[] tiempoEquipo) {
		// Utilizamos stream para iterar todos los elementos del array, y con el metodo
		// sum, sumamos todos los valores en la misma variable
		double kmhTotal = Arrays.stream(etapas).sum();
		double tiempoTotal = Arrays.stream(tiempoEquipo).sum();
		// Devolvemos el metodo para calcular los kmh totales.
		if (tiempoEquipo.length > etapas.length) {
			return calcularKmh(kmhTotal, tiempoTotal / 2);
		} else {
			return calcularKmh(kmhTotal, tiempoTotal);
		}
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

	// Hecho por Juan y David
	// Imprimir el corredor mas rapido por etapa
	public static void corredorMasRapidoPorEtapa(ArrayList<String[]> equipos, ArrayList<double[]> tiempos) {
		// Iteramos cada etapa
		for (int i = 0; i < etapas.length; i++) {
			// Guardamos el mayor numero existente en double
			double mejorTiempo = Double.MAX_VALUE;
			// Aqui guardamos el nombre del mejor participante de la etapa
			String mejorParticipante = "";

			// Iteramos todos los registros de tiempo, ubicandonos con g
			for (int g = 0; g < tiempos.size(); g++) {
				// Entramos al tiempo de la iteracion
				double[] tiempo = tiempos.get(g);

				// Comprobamos si son bicis electricas
				if (tiempo.length > etapas.length) {
					// Verificamos si el tiempo iterado es menor al mejor tiempo registrado
					if (tiempo[i] < mejorTiempo) {
						mejorTiempo = tiempo[i];
						mejorParticipante = equipos.get(g)[1];
					}
					// Aqui comprobamos al segundo participante
					if (tiempo[i + etapas.length] < mejorTiempo) {
						mejorTiempo = tiempo[i + etapas.length];
						mejorParticipante = equipos.get(g)[2];
					}
				} else {
					// Aqui las bicis son de montaña
					if (tiempo[i] < mejorTiempo) {
						mejorTiempo = tiempo[i];
						mejorParticipante = equipos.get(g)[i % 2 + 1];
					}
				}
			}

			// Guardamos la velocidad media e imprimimos el resultado
			double velocidadMedia = redondearDecimales(calcularKmh(etapas[i], mejorTiempo), 2);
			System.out.println("Etapa " + (i + 1) + ": el participante mas rapido es " + mejorParticipante
					+ " con una velocidad media de " + velocidadMedia + " km/h");
		}
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
