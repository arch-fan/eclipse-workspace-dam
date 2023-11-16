package Practica4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Practica {

	// Constante que define los kilometros que tiene cada etapa.
	private static final double[] etapas = { 74.12, 63.89, 67.37, 84.03 };
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// Variables en las que guardamos tanto los tiempos como los equipos, guiandonos
		// por la posicion.
		ArrayList<String[]> equipos = new ArrayList<>();
		ArrayList<double[]> tiempos = new ArrayList<>();
		ArrayList<String[]> registroEquiposEliminados = new ArrayList<>();

//		apuntarComponentesEjemplo(equipos, tiempos);
		apuntarComponentes(equipos, tiempos);

		{
			ArrayList<Integer[]> mejoresCorredores = corredorMasRapidoPorEtapa(equipos, tiempos);
			agregarTiempoPorBicisMontana(mejoresCorredores, tiempos, 10);
		}

		System.out.print("\n");

		{
			ArrayList<Integer[]> equiposLentos = identificarCorredoresLentos(equipos, tiempos);
			if (equiposLentos.size() >= 1)
				eliminarEquipos(equiposLentos, equipos, tiempos, registroEquiposEliminados);
		}

		// Invocamos el metodo para ordenar los equipos por la clasificacion.
		ArrayList<String[]> equiposClasificados = clasificarEquipos(equipos, tiempos);
		// Creamos un array list para guardar el tiempo medio de los 3 ganadores, ya que
		// lo usaremos despues
		ArrayList<Double> tiempoMedioPorEquipo = new ArrayList<>();

		System.out.println("\nLos 3 primeros equipos son: ");
		{
			int equiposSize = equiposClasificados.size();
			// Utilizamos un operador ternario para decidir cuantas posiciones se van a
			// mostrar, ya que si hay menos de 3 equipos, nos daria error el programa.
			int max = equiposSize < 3 ? equiposSize : 3;

			for (int i = 0; i < max; i++) {
				String[] equipo = equiposClasificados.get(i);
				double kmh = calcularKmhEquipo(tiempos.get(equipos.indexOf(equipo)));
				tiempoMedioPorEquipo.add(kmh);
				// Muestra los tres primeros clasificados
				System.out.println("  - El equipo en la posicion " + (i + 1) + " es " + equipo[0]
						+ " con una velocidad media de " + redondearDecimales(kmh, 2) + " km/h");
			}
		}

		// Llamamos al metodo para imprimir el corredor mas rapido por etapa
		System.out.println("\nLos participantes mas rapidos por etapas son: ");
		ArrayList<Integer[]> corredoresMasRapidos = corredorMasRapidoPorEtapa(equipos, tiempos);

		for (int i = 0; i < corredoresMasRapidos.size(); i++) {
			Integer[] corredorMasRapido = corredoresMasRapidos.get(i);
			int iEquipoCorredor = corredorMasRapido[0];
			int iCorredorRapido = corredorMasRapido[1];
			double tiempoCorredor = tiempos.get(iEquipoCorredor).length > etapas.length
					? tiempos.get(iEquipoCorredor)[(iCorredorRapido - 1) * etapas.length + i]
					: tiempos.get(iEquipoCorredor)[i];

			System.out.println("  - Etapa " + (i + 1) + ": el participante mas rapido es "
					+ equipos.get(iEquipoCorredor)[iCorredorRapido] + " con una velocidad media de "
					+ redondearDecimales(calcularKmh(etapas[i], tiempoCorredor), 2) + " km/h");
		}

		int iEquipoMasRapido = 0;

		{
			double maxN = 0;
			for (int i = 0; i < tiempoMedioPorEquipo.size(); i++) {
				double tiempoLoop = tiempoMedioPorEquipo.get(i);
				if (tiempoLoop > maxN) {
					maxN = tiempoLoop;
					iEquipoMasRapido = i;
				}
			}
		}

		boolean correoCorrecto = false;
		// Para pedir el correo, verificaremos que el correo sea correcto repitiendo el
		// bucle
		while (!correoCorrecto) {
			System.out
					.println("Felicidades " + equipos.get(equipos.indexOf(equiposClasificados.get(iEquipoMasRapido)))[0]
							+ " habeis sido los ganadores.");
			System.out.print("Ahora, introducid vuestro correo: ");
			String email = sc.nextLine();

			// Guardamos el patron del correo con el dominio especificado
			Pattern pattern = Pattern.compile("^.+@salesianosalcala\\.com$");
			// Verificamos el correo con el patron
			Matcher matcher = pattern.matcher(email);

			// Si hace el match, nos quedamos el correo, si no, volvemos a pedirlo
			if (matcher.matches()) {
				System.out.println("Gracias por participar! Nos vemos en la proxima.");
				correoCorrecto = true;
			} else {
				System.out.println(
						email + " no es un correo electrónico valido. Debe pertenecer al dominio salesianosalcala.com");
				continue;
			}
		}
		sc.close();
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
		System.out.println("¡Bienvenido al registro de equipos de ciclismo!");

		boolean masEquipos = true; // Controla si se desea continuar anadiendo equipos.
		int numeroDeEquipo = 1; // Contador para asignar un numero a cada equipo.

		String respuesta; // Variable para almacenar respuestas del usuario.
		while (masEquipos) {

			// Informacion del equipo
			String[] equipo = new String[3]; // Array para almacenar los datos de un equipo.
			System.out.print("\nNombre del equipo " + numeroDeEquipo + ": ");
			equipo[0] = sc.nextLine(); // Almacena el nombre del equipo.

			boolean bicicletasElectricas; // Indica si el equipo usa bicicletas electricas.

			// Verificacion de bicicletas electricas
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
			int cantidadEtapas = bicicletasElectricas ? 4 : 2; // Determina la cantidad de etapas por ciclista segun el
																// tipo de bicicleta con un operador ternario

			double[] tiempoEquipo = new double[cantidadEtapas * 2]; // Array para almacenar tiempos del equipo (dos
																	// ciclistas).

			for (int ciclista = 1; ciclista <= 2; ciclista++) {
				// Registro de los nombres de los ciclistas y sus tiempos en cada etapa.
				System.out.print("\nIntroduce el nombre del " + (ciclista == 1 ? "primer" : "segundo") + " ciclista: ");
				equipo[ciclista] = sc.nextLine(); // Guardamos el nombre del ciclista en la posicion marcada por el
													// bucle

				// Por cada etapa, gu++ardamos el tiempo del ciclista
				for (int etapa = 0; etapa < cantidadEtapas; etapa++) {
					while (true) {
						// Recuperamos y validamos la entrada de tiempo de los usuarios
						try {
							System.out.print("Introduce el tiempo de la etapa " + (etapa + 1) + " para "
									+ equipo[ciclista] + ": ");

							// Si obtenemos un error al intentar transformar un numero, pasamos al bloque
							// catch, el cual hará que se repita el bucle
							double input = Double.parseDouble(sc.nextLine());

							// Quitamos los minutos a la entrada del usuario para poder verificar si lo ha
							// introducido en el formato correcto
							double decimal = input - (int) input;
							if (decimal >= 0.6) {
								System.out.println("Introduce un tiempo valido! (ej: 1.30 es 1 hora 30 minutos)");
								// Si lo ha introducido en el formato erroneo, utilizamos la sentencia continue
								// para volver al while
								continue;
							}

							// Asigna el tiempo del ciclista en formato decimal al array 'tiempoEquipo' en
							// el índice calculado.
							// El índice se calcula usando la fórmula: (ciclista - 1) * cantidadEtapas +
							// etapa.
							// 'ciclista' es el número del ciclista (1 o 2), 'cantidadEtapas' es el total de
							// etapas (2 o 4),
							// y 'etapa' es el número de la etapa actual (0 a 3).
							// 'convertirHoraMinutosADecimal' convierte el tiempo ingresado ('input') de un
							// formato de horas y minutos a decimal.
							tiempoEquipo[(ciclista - 1) * cantidadEtapas + etapa] = convertirHoraMinutosADecimal(input);

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
			numeroDeEquipo++;

			// Pregunta para anadir mas equipos
			if (equipos.size() >= 2) {
				System.out.print("\n¿Quieres introducir otro equipo? (s/n): ");
				masEquipos = sc.nextLine().toLowerCase().equals("s");
			}
		}
	}

	/**
	 * Devuelve la lista de todos los equipos de manera ordenada por tiempo sin
	 * mutarla.
	 * 
	 * @param equipos Array que guarda todos los equipos del programa.
	 * @param tiempos Array que guarda todos los tiempos del programa.
	 * @return La lista de equipos original ordenada por el tiempo medio del equipo.
	 */
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
		equiposOrdenados.sort((e1, e2) -> Double.compare(calcularMediaTiempos(tiempos.get(equipos.indexOf(e1))),
				calcularMediaTiempos(tiempos.get(equipos.indexOf(e2)))));
		return equiposOrdenados;
	}

	/**
	 * Convierte un tiempo expresado en formato tiempo a su equivalente decimal.
	 * 
	 * Este método toma un tiempo en un formato donde las horas y los minutos están
	 * combinados en un único número (por ejemplo, 1.30 representa 1 hora y 30
	 * minutos) y lo convierte a un número decimal, donde los minutos se transforman
	 * en formato tiempo. Por ejemplo, 1.30 se convierte en 1.5.
	 *
	 * @param horasFTiempo El tiempo en formato tiempo, donde la parte entera
	 *                     representa las horas y la parte decimal representa los
	 *                     minutos.
	 * @return El tiempo convertido en formato decimal.
	 */
	private static double convertirHoraMinutosADecimal(double horasFTiempo) {
		int horas = (int) horasFTiempo; // Extraer la parte entera de horasFTiempo (las horas)
		double minutos = (horasFTiempo - horas) * 100; // Extraer la parte decimal y convertirla a minutos
		return horas + (minutos / 60); // Convertir los minutos a una fracción decimal de una hora y sumar a las horas
	}

	/**
	 * Convierte dos parametros de kilometros y horas en Km/h
	 *
	 * @param k El recorrido en kilometros (ej: 170km)
	 * @param h Las horas que tarda en recorrer los kilometros (ej: 1.5 es 1h 30m)
	 * @return
	 */
	private static double calcularKmh(double k, double h) {
		// Dividimos los km entre las horas que hayamos seleccionado
		return k / h;
	}

	/**
	 * Calcula los Km/h de un equipo. Un equipo es un array de tiempo de 4 u 8
	 * posiciones.
	 *
	 * @param tiempoEquipo Array de tiempo de 4 u 8 posiciones con las marcas de
	 *                     tiempo que representan a un equipo
	 * @return El tiempo en formato Km/h
	 */
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

	/**
	 * Metodo generico para redondear cualquier decimal a las posiciones deseadas.
	 * 
	 * @param n        Numero a redondear
	 * @param cantidad Cantidad de decimales a los que se quiere redondear
	 * @return El numero redondeado
	 */
	private static double redondearDecimales(double n, int cantidad) {
		// Calculamos la cantidad de posiciones que se tiene que desplazar la coma a la
		// derecha del numero
		double decimales = Math.pow(10.0, cantidad);
		// Redondeamos moviendo la coma la cantidad de veces especificadas en decimales
		// y le volvemos a poner la coma
		return Math.round(n * decimales) / decimales;
	}

	/**
	 * Metodo para recuperar al corredor mas rapido de cada etapa
	 * 
	 * @param equipos Array de equipos pertenecientes a la competicion
	 * @param tiempos Array de tiempos pertenecientes a cada equipo
	 * @return Un ArrayList de Arrays de numeros que por posicion guarda al corredor
	 *         mas rapido de la etapa marcada por la posicion, en los que dentro de
	 *         cada array de numeros, la posicion 0 marca el indice del equipo con
	 *         el corredor mas rapido y la posicion 1 marca la posicion del corredor
	 *         mas rapido del equipo
	 */
	public static ArrayList<Integer[]> corredorMasRapidoPorEtapa(ArrayList<String[]> equipos,
			ArrayList<double[]> tiempos) {

		// Posicion 0 del Integer marca el indice del equipo
		// Posicion 1 del Integer marca la posicion del corredor mas rapido (1 o 2)
		ArrayList<Integer[]> corredoresMasRapidos = new ArrayList<>();

		// Iteramos cada etapa
		for (int i = 0; i < etapas.length; i++) {
			// Guardamos el mayor numero existente en double
			double mejorTiempo = Double.MAX_VALUE;
			// Guardamos el nombre del mejor participante de la etapa
			int iMejorEquipo = 0;
			int iMejorCorredor = 0;

			// Iteramos todos los registros de tiempo, ubicandonos con g
			for (int g = 0; g < tiempos.size(); g++) {
				// Entramos al tiempo de la iteracion
				double[] tiempo = tiempos.get(g);

				// Comprobamos si son bicis electricas
				if (tiempo.length > etapas.length) {
					// Verificamos si el tiempo iterado es menor al mejor tiempo registrado
					if (tiempo[i] < mejorTiempo) {
						mejorTiempo = tiempo[i];
						iMejorEquipo = g;
						iMejorCorredor = 1;
					}
					// Aqui comprobamos al segundo participante
					if (tiempo[i + etapas.length] < mejorTiempo) {
						mejorTiempo = tiempo[i + etapas.length];
						iMejorEquipo = g;
						iMejorCorredor = 2;
					}
				} else {
					// Aqui las bicis son de montana
					if (tiempo[i] < mejorTiempo) {
						mejorTiempo = tiempo[i];
						iMejorEquipo = g;
						iMejorCorredor = i % 2 + 1;
					}
				}
			}

			// Guardamos el indice del mejor equipo de la etapa y el indice del mejor
			// corredor de la etapa.
			corredoresMasRapidos.add(new Integer[] { iMejorEquipo, iMejorCorredor });
		}
		return corredoresMasRapidos;
	}

	/**
	 * Calcula la media de un array de numeros de tipo double.
	 * 
	 * @param tiempos Array de numeros a calcular la media
	 * @return La media de los numeros de la lista pasada como parametro
	 */
	private static double calcularMediaTiempos(double[] tiempos) {
		double sum = 0; // Variable en la que iremos guardando la suma de los numeros

		// Iteramos el array para extraer todos los numeros y sumarlos a la variable sum
		for (double tiempo : tiempos) {
			sum += tiempo;
		}

		// Devolvemos la suma de todos los numeros y la dividimos por la cantidad de
		// numeros que haya
		return sum / tiempos.length;
	}

	/**
	 * Metodo para recuperar al corredor mas lento de cada etapa
	 * 
	 * @param equipos Array de equipos pertenecientes a la competicion
	 * @param tiempos Array de tiempos pertenecientes a cada equipo
	 * @return Un ArrayList de Arrays de numeros que por posicion guarda al corredor
	 *         mas lento de la etapa marcada por la posicion, en los que dentro de
	 *         cada array de numeros, la posicion 0 marca el indice del equipo con
	 *         el corredor mas rapido y la posicion 1 marca la posicion del corredor
	 *         mas rapido del equipo
	 */
	public static ArrayList<Integer[]> identificarCorredoresLentos(ArrayList<String[]> equipos,
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

	/**
	 * Elimina equipos de la competicion dados los parametros. Para ser eliminados,
	 * debe estar duplicado el array dentro del parametro equiposAeliminar
	 * 
	 * @param equiposAeliminar          ArrayList de Arrays de numeros en los que
	 *                                  dentro de cada uno contiene: Pos 0 indice
	 *                                  del equipo a eliminar, Pos 1 indice del
	 *                                  miembro dentro del equipo a eliminar
	 * @param equipos                   Array de equipos pertenecientes a la
	 *                                  competicion
	 * @param tiempos                   Array de tiempos pertenecientes a cada
	 *                                  equipo
	 * @param registroEquiposEliminados Array en el que guardamos el registro de los
	 *                                  equipos eliminados
	 */
	public static void eliminarEquipos(ArrayList<Integer[]> equiposAeliminar, ArrayList<String[]> equipos,
			ArrayList<double[]> tiempos, ArrayList<String[]> registroEquiposEliminados) {

		// Set en el que guardaremos los indices de los equipos que vayamos a eliminar
		HashSet<Integer> indicesDuplicados = new HashSet<>();

		// Iteramos el array con los equipos a eliminar y lo volvemos a iterar con la
		// siguiente posicion para comprobar si hay duplicados, y si esta duplicado lo
		// añadimos al set para eliminarlo despues
		for (int i = 0; i < equiposAeliminar.size(); i++) {
			for (int j = i + 1; j < equiposAeliminar.size(); j++) {
				if (Arrays.equals(equiposAeliminar.get(i), equiposAeliminar.get(j))) {
					indicesDuplicados.add(equiposAeliminar.get(i)[0]);
				}
			}
		}

		// Convertir el Set a un ArrayList y ordenarla de mayor a menor
		ArrayList<Integer> indicesOrdenados = new ArrayList<>(indicesDuplicados);
		// Ordenamos de mayor a menor con el algoritmo quicksort explicado anteriormente
		indicesOrdenados.sort((a, b) -> b - a);

		// Iterar sobre los índices ordenados y eliminar los equipos
		for (int indiceAeliminar : indicesOrdenados) {
			String[] equipoAeliminar = equipos.get(indiceAeliminar);

			System.out.println(
					"Se ha eliminado al equipo " + indiceAeliminar + ", " + equipoAeliminar[0] + ", por lentos");

			registroEquiposEliminados.add(equipoAeliminar);
			equipos.remove(indiceAeliminar);
			tiempos.remove(indiceAeliminar);
		}

	}

	/**
	 * Metodo para penalizar y/o agregar 10 minutos a los corredores que no han
	 * quedado primeros en su etapa
	 * 
	 * @param mejoresCorredores ArrayList de Arrays de numeros en los que dentro de
	 *                          cada uno contiene: Pos 0 indice del equipo a
	 *                          eliminar, Pos 1 indice del miembro dentro del equipo
	 *                          que ha quedado primero
	 * 
	 * @param tiempos           Array de tiempos pertenecientes a cada equipo
	 * @param minutosExtra      Cantidad de tiempo a penalizar (en minutos)
	 * 
	 * @return Un ArrayList de Arrays de numeros que por posicion guarda al corredor
	 *         mas lento de la etapa marcada por la posicion, en los que dentro de
	 *         cada array de numeros, la posicion 0 marca el indice del equipo con
	 *         el corredor mas rapido y la posicion 1 marca la posicion del corredor
	 *         mas rapido del equipo
	 */
	public static void agregarTiempoPorBicisMontana(ArrayList<Integer[]> mejoresCorredores, ArrayList<double[]> tiempos,
			double minutosExtra) {
		// Convertir minutos en formato decimal
		double duracionExtra = minutosExtra / 60;

		// Recorrer todas las etapas
		for (int i = 0; i < etapas.length; i++) {
			Integer[] corredorMasRapido = mejoresCorredores.get(i);
			int indiceEquipoCorredorMasRapido = corredorMasRapido[0];
			double[] tiemposDelEquipoCorredorMasRapido = tiempos.get(indiceEquipoCorredorMasRapido);

			// Comprobar si el corredor más rápido de la etapa usa bici de montaña
			if (tiemposDelEquipoCorredorMasRapido.length == etapas.length) {
				// Si usa bici de montaña, agregar duración extra a todos los corredores de esa
				// etapa, excepto al corredor más rápido
				for (int j = 0; j < tiempos.size(); j++) {
					// Evitamos sumar 10 minutos al corredor que ha quedado mejor
					if (j != indiceEquipoCorredorMasRapido) {

						// Agregar duración extra al primer corredor de cada equipo
						tiempos.get(j)[i] += duracionExtra;
						// Verificar si es un equipo con bicis eléctricas
						if (tiempos.get(j).length > etapas.length) {
							// Agregar duración extra al segundo corredor
							tiempos.get(j)[i + etapas.length] += duracionExtra;
						}
					}
				}
			}
		}
	}
}
