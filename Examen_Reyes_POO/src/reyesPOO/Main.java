package reyesPOO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static List<Carta> cartas = new ArrayList<>();

	public static void main(String[] args) {

		cartas.addAll(Carta.cartasExample());
//		while (true) {
//			try {
//				cartas.add(createCarta());
//			} catch (Error e) {
//				System.out.println(e.getMessage());
//			}
//
//			System.out.println("Quieres crear otra carta? (s/n): ");
//			if (sc.nextLine().trim().toLowerCase().equals("n")) {
//				break;
//			}
//		}

		for (Carta carta : cartas) {
			System.out.println("Carta ID: " + carta.hashCode());
			System.out.println("Nombre niño: " + carta.getNombreNino());
			System.out.println("Dirigido al rey: " + carta.getReyMago().getName());
			System.out.println("Con los siguientes juguetes: ");
			carta.getJuguetes().forEach((juguete) -> {
				System.out.println("  - " + juguete.getNombre() + " | " + juguete.getCategoria());
			});
			System.out.print("\n");
		}

		System.out.println("--- Estadísticas Categorías ---");
		Map<String, Long> estadisticasCategorias = Carta.totalEstadisticaCategoria(cartas);
		Long totalCategorias = estadisticasCategorias.values().stream().mapToLong(Long::longValue).sum();

		estadisticasCategorias.forEach((k, v) -> {
			System.out.println(k + ": " + (((double) v / (double) totalCategorias) * (double) 100) + "%");
		});

		System.out.println("--- Estadísticas Reyes ---");
		Map<String, Long> estadisticasReyes = cartas.stream().map(Carta::getReyMago)
				.collect(Collectors.groupingBy(ReyMago::getName, Collectors.counting()));
		Long totalReyes = estadisticasReyes.values().stream().mapToLong(Long::longValue).sum();

		estadisticasReyes.forEach((k, v) -> {
			System.out.println(k + ": " + (((double) v / (double) totalReyes) * (double) 100) + "%");
		});
		
		correrRegex();

		sc.close();
	}

	public static Carta createCarta() {

		System.out.print("Nombre del niño: ");
		String nombreNino = sc.nextLine();
		if (Carta.comprobarNinoRepetido(cartas, nombreNino)) {
			throw new Error("Niño, no repitas carta!");
		}

		ReyMago reyMago;
		while (true) {
			try {
				System.out.print("Nombre del rey mago: ");
				reyMago = new ReyMago(sc.nextLine());
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		ArrayList<Juguete> juguetes = new ArrayList<>();
		while (true) {
			juguetes.add(createJuguete());
			System.out.println("Quieres añadir otro juguete? (s/n): ");
			if (sc.nextLine().equals("n")) {
				break;
			}
		}

		return new Carta(nombreNino, reyMago, juguetes);
	}

	public static Juguete createJuguete() {
		System.out.print("Nombre del juguete: ");
		String nombreJuguete = sc.nextLine();

		String categoria;
		while (true) {
			try {
				System.out.print("Categoria del juguete: ");
				categoria = sc.nextLine();
				Juguete juguete = new Juguete(nombreJuguete, categoria);
				return juguete;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void correrRegex() {
		ArrayList<String> listaRegalos = cartas.stream()
				.flatMap(carta -> carta.getJuguetes().stream())
				.map(Juguete::getNombre)
				.collect(Collectors.toCollection(ArrayList::new));

		int azCount = 0;
		int steamCount = 0;
		int jugueteCount = 0;

		for (String regalo : listaRegalos) {
			Pattern pAz = Pattern.compile("[^a-zA-Z]");
			Matcher mAz = pAz.matcher(regalo);

			if (mAz.find()) {
				azCount++;
			}

			Pattern pSteam = Pattern.compile("steam");
			Matcher mSteam = pSteam.matcher(regalo);

			if (mSteam.find()) {
				steamCount++;
			}

			Pattern pJuguete = Pattern.compile("steam");
			Matcher mJuguete = pJuguete.matcher(regalo);

			if (mJuguete.find()) {
				jugueteCount++;
			}
		}

		System.out.println("Hay " + azCount + " caracteres diferentes a a-z en las peticiones");
		System.out.println("Aparece " + steamCount + " veces la palabra steam en las peticiones");
		System.out.println("Aparece " + jugueteCount + " veces la palabra juguete en las peticiones");
	}
}
