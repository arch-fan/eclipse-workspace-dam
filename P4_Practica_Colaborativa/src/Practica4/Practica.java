package Practica4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Practica {

	// Constante que define los kilometros que tiene cada etapa.
	private static final double[] etapas = { 74.12, 63.89, 67.37, 84.03 };

	public static void main(String[] args) {
		// Variables en las que guardamos tanto los tiempos como los equipos, guiandonos
		// por la posicion.
		ArrayList<String[]> equipos = new ArrayList<>();
		ArrayList<double[]> tiempos = new ArrayList<>();
		ArrayList<String[]> registroEquiposEliminados = new ArrayList<>();

		apuntarComponentesEjemplo(equipos, tiempos);
//		apuntarComponentes(equipos, tiempos);

		System.out.print("\n");

		ArrayList<Integer[]> equiposLentos = identificarEquiposLentos(equipos, tiempos);
		eliminarEquipos(equiposLentos, equipos, tiempos, registroEquiposEliminados);

		// Invocamos el metodo para ordenar los equipos por la clasificacion.
		ArrayList<String[]> equiposClasificados = clasificarEquipos(equipos, tiempos);

		System.out.println("\nLos 3 primeros equipos son: ");
		{
			int equiposSize = equiposClasificados.size();
			// Utilizamos un operador ternario para decidir cuantas posiciones se van a
			// mostrar, ya que si hay menos de 3 equipos, nos daria error el programa.
			int max = equiposSize < 3 ? equiposSize : 3;

			for (int i = 0; i < max; i++) {
				String[] equipo = equiposClasificados.get(i);
				double kmh = calcularKmhEquipo(tiempos.get(equipos.indexOf(equipo)));
				// Muestra los tres primeros clasificados
				System.out.println("  - El equipo en la posicion " + (i + 1) + " es " + equipo[0]
						+ " con una velocidad media de " + redondearDecimales(kmh, 2) + " km/h");
			}
		}

		// Llamamos al metodo para imprimir el corredor mas rapido por etapa
		System.out.println("\nLos participantes mas rapidos por etapas son: ");
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

	// Metodo para recoger la entrada del usuario y anadirlos al array de datos
	public static void apuntarComponentes(ArrayList<String[]> equipos, ArrayList<double[]> tiempos) {
		Scanner sc = new Scanner(System.in);
		System.out.println("¡Bienvenido al registro de equipos de ciclismo!");

		boolean masEquipos = true; // Controla si se desea continuar anadiendo equipos.
		int numeroDeEquipo = 1; // Contador para asignar un numero a cada equipo.

		String respuesta; // Variable para almacenar respuestas del usuario.
		while (masEquipos) {
			// Informacion del equipo
			String[] equipo = new String[3]; // Array para almacenar los datos de un equipo.
			System.out.print("\nNombre del equipo " + numeroDeEquipo + ": ");
			equipo[0] = sc.nextLine(); // Almacena el nombre del equipo.

			// Verificacion de bicicletas electricas
			boolean bicicletasElectricas; // Indica si el equipo usa bicicletas electricas.
			do {
				System.out.print("El equipo " + equipo[0] + " utiliza bicicletas electricas? (s/n): ");
				respuesta = sc.nextLine().toLowerCase();
				bicicletasElectricas = respuesta.equals("s");
				// Solicita al usuario confirmar si el equipo usa bicicletas electricas.
				if (!respuesta.equals("s") && !respuesta.equals("n")) {
					System.out.println("Por favor, introduce 's' o 'n'.");
				}
			} while (!respuesta.equals("s") && !respuesta.equals("n"));

			// Calculo del tiempo
			int cantidadEtapas = bicicletasElectricas ? 4 : 2; // Determina la cantidad de etapas segun el tipo de
																// bicicleta.
			double[] tiempoEquipo = new double[cantidadEtapas * 2]; // Array para almacenar tiempos del equipo (dos
																	// ciclistas).

			for (int ciclista = 1; ciclista <= 2; ciclista++) {
				// Registro de los nombres de los ciclistas y sus tiempos en cada etapa.
				System.out.print("\nIntroduce el nombre del " + (ciclista == 1 ? "primer" : "segundo") + " ciclista: ");
				equipo[ciclista] = sc.nextLine();

				for (int etapa = 0; etapa < cantidadEtapas; etapa++) {
					while (true) {
						// Recuperamos y validamos la entrada de tiempo de los usuarios
						try {
							System.out.print("Introduce el tiempo de la etapa " + (etapa + 1) + " para "
									+ equipo[ciclista] + ": ");
							tiempoEquipo[(ciclista - 1) * cantidadEtapas + etapa] = Double.parseDouble(sc.nextLine());
							break; // Sale del bucle una vez se obtiene un valor valido.
						} catch (NumberFormatException e) {
							// Maneja la excepcion en caso de introducir un valor no numerico.
							System.out.println("Introduce un valor correcto!!");
						}
					}
				}
			}

			// Anade los datos del equipo y sus tiempos a las listas correspondientes.
			equipos.add(equipo);
			tiempos.add(tiempoEquipo);
			numeroDeEquipo++; // Incrementa el contador de equipos.

			// Pregunta para anadir mas equipos
			if (equipos.size() >= 2) {
				System.out.print("\n¿Quieres introducir otro equipo? (s/n): ");
				masEquipos = sc.nextLine().toLowerCase().equals("s"); // Determina si se continua con la adicion de
																		// equipos.
			}
		}

		sc.close(); // Cierra el Scanner para liberar recursos.
	}

	// Devuelve la lista de todos los equipos de manera ordenada por tiempo.
	private static ArrayList<String[]> clasificarEquipos(ArrayList<String[]> equipos, ArrayList<double[]> tiempos) {
		// Primero, clonamos el ArrayLisy de los equipos, ya que lo vamos a mutar.
		ArrayList<String[]> equiposOrdenados = new ArrayList<>(equipos);
		/*
		 * Aqui utilizamos el metodo sort, el cual utiliza el algoritmo Quicksort para
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
		 * Aqui se escribe el metodo de una manera mas simplificada:
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

	private static double calcularKmh(double k, double h) {
		// Dividimos los km entre las horas que hayamos seleccionado
		return k / h;
	}

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

	// Pasamos de primer parametro el numero que queremos devolver redondeado, y de
	// segundo parametro, la cantidad de decimales que queramos mostrar.
	private static double redondearDecimales(double n, int cantidad) {
		// Hace la potencia de 10, elevado a la cantidad de decimales.
		double decimales = Math.pow(10.0, cantidad);
		// Devuelve el redondeo de numeros.
		return Math.round(n * decimales) / decimales;
	}

	// Imprimir el corredor mas rapido por etapa
	public static void corredorMasRapidoPorEtapa(ArrayList<String[]> equipos, ArrayList<double[]> tiempos) {
		
		// Posicion 0 del Integer marca el indice del equipo
		// Posicion 1 del Integer marca la posicion del corredor mas lento (1 o 2)
		ArrayList<Integer[]> corredoresMasRapidos = new ArrayList<>();
		
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
					// Aqui las bicis son de montana
					if (tiempo[i] < mejorTiempo) {
						mejorTiempo = tiempo[i];
						mejorParticipante = equipos.get(g)[i % 2 + 1];
					}
				}
			}

			// Guardamos la velocidad media e imprimimos el resultado
			double velocidadMedia = redondearDecimales(calcularKmh(etapas[i], mejorTiempo), 2);
			System.out.println("  - Etapa " + (i + 1) + ": el participante mas rapido es " + mejorParticipante
					+ " con una velocidad media de " + velocidadMedia + " km/h");
		}
	}

	// Calcula la media de un array de numeros, que en este caso utilizamos para
	// calcular la media de los tiempos.
	private static double calcularMedia(double[] tiempos) {
		double sum = 0;
		for (double tiempo : tiempos) {
			sum += tiempo;
		}
		return sum / tiempos.length;
	}

	// Indentifica los equipos con el corredor mas lento por etapa, y lo devuelve en el formato especificado abajo.
	public static ArrayList<Integer[]> identificarEquiposLentos(ArrayList<String[]> equipos,
			ArrayList<double[]> tiempos) {
		// Posicion 0 del Integer marca el indice del equipo
		// Posicion 1 del Integer marca la posicion del corredor mas lento (1 o 2)
		ArrayList<Integer[]> corredoresMasLentos = new ArrayList<>();

		// Recorremos cada etapa para determinar el equipo mas lento.
		for (int i = 0; i < etapas.length; i++) {
			double peorTiempo = 0;
			int equipoLento = 0;
			int posCorredorLento = 0;

			// Iteramos sobre cada equipo para encontrar el mas lento en la etapa actual.
			for (int g = 0; g < tiempos.size(); g++) {
				double[] tiempo = tiempos.get(g);

				// Si el equipo usa bicicletas electricas, comparamos cada tiempo de manera
				// individual.
				if (tiempo.length > etapas.length) {
					if (tiempo[i] > peorTiempo) {
						peorTiempo = tiempo[i];
						equipoLento = g;
						posCorredorLento = 1;
					}
					if (tiempo[i + etapas.length] > peorTiempo) {
						peorTiempo = tiempo[i + etapas.length];
						equipoLento = g;
						posCorredorLento = 2;
					}
				} else {
					// Para equipos con bicicletas convencionales.
					if (tiempo[i] > peorTiempo) {
						peorTiempo = tiempo[i];
						equipoLento = g;
						posCorredorLento = i == 0 || i == 2 ? 1 : 2;
					}
				}
			}

			// Anadimos los 2 valores que hemos guardado al array
			corredoresMasLentos.add(i, new Integer[] { equipoLento, posCorredorLento });
		}
		return corredoresMasLentos;
	}

	public static void eliminarEquipos(ArrayList<Integer[]> equiposAeliminar, ArrayList<String[]> equipos,
			ArrayList<double[]> tiempos, ArrayList<String[]> registroEquiposEliminados) {
		
		// Array en el que guardaremos los indices de los equipos que vayamos a eliminar
		ArrayList<Integer> indicesEquiposAeliminar = new ArrayList<>();
		
		for (int i = 0; i < equiposAeliminar.size(); i++) {
			for (int j = i + 1; j < equiposAeliminar.size(); j++) {
				if (Arrays.equals(equiposAeliminar.get(i), equiposAeliminar.get(j))) {
					// Si en el set de datos guardados es igual, añadimos la posicion 0 que es donde guarda el equipo al array recien creado
					int indiceEquipoAeliminar = equiposAeliminar.get(i)[0];
					indicesEquiposAeliminar.add(indiceEquipoAeliminar);
				}
			}
		}
		
		// Ordenamos el array de mayor a menor para que no afecte a la hora de extraer
		indicesEquiposAeliminar.sort((a, b) -> b - a);
		
		// Iteramos los indices de los equipos a eliminar y los eliminamos
		for(int i = 0; i < indicesEquiposAeliminar.size(); i++) {
			int indiceAeliminar = indicesEquiposAeliminar.get(i);
			String[] equipoAeliminar = equipos.get(indiceAeliminar);
			
			System.out.println("Se ha eliminado al equipo " + indiceAeliminar + ", " + equipoAeliminar[0] + ", por lentos");
			
			registroEquiposEliminados.add(equipoAeliminar);
			equipos.remove(indiceAeliminar);
			tiempos.remove(indiceAeliminar);
		}
	}
}
