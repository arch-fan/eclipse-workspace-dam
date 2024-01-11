package reyesPOO;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		ArrayList<Carta> cartas = new ArrayList<>();

		while (true) {
			cartas.add(createCarta());
			System.out.println("Quieres crear otra carta? (s/n): ");
			if (sc.nextLine().trim().toLowerCase().equals("n")) {
				break;
			}
		}

		for (Carta carta : cartas) {
			System.out.println("\n");
			System.out.println("Carta ID: " + carta.hashCode());
			System.out.println("Nombre niño: " + carta.getNombreNino());
			System.out.println("Dirigido al rey: " + carta.getReyMago().getName());
			System.out.println("Con los siguientes juguetes: ");
			carta.getJuguetes().forEach((juguete) -> {
				System.out.println("  - " + juguete.getNombre());
			});

		}

		sc.close();
	}

	public static Carta createCarta() {

		System.out.print("Nombre del niño: ");
		String nombreNino = sc.nextLine();

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
}
