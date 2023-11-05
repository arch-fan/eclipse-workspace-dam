package Practica4;

import java.util.ArrayList;

public class Practica {

	private static final double[] etapas = { 74.12, 63.89, 67.37, 84.03 };

	public static void main(String[] args) {
		ArrayList<String[]> equipos = new ArrayList<>();
		ArrayList<double[]> tiempos = new ArrayList<>();
		apuntarComponentes(equipos, tiempos);

		ArrayList<String[]> equiposClasificados = clasificarEquipos(equipos, tiempos);

		System.out.println("Los 3 primeros equipos son: ");
		for (int i = 0; i < 3; i++) {
			String[] equipo = equiposClasificados.get(i);
			double kmh = calcularKmhEquipo(tiempos.get(equipos.indexOf(equipo)));
			System.out.println("El equipo en la posicion " + (i + 1) + " es " + equipo[0]
					+ " con una velocidad media de " + redondearDecimales(kmh, 2) + "km/h");
		}

		int[] indices = corredorMasRapidoPorEtapa(tiempos);
		for (int i = 0; i < indices.length; i++) {
			System.out.println(
					"El corredor más rápido en la etapa " + (i + 1) + " es " + equipos.get(indices[i])[i % 2 + 1]
							+ " del equipo " + equipos.get(indices[i])[0] + " con una velocidad media de "
							+ redondearDecimales(calcularKmh(etapas[i], tiempos.get(indices[i])[i]), 2) + " km/h.");
		}
	}

	public static void apuntarComponentes(ArrayList<String[]> equipos, ArrayList<double[]> tiempos) {
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

		equipos.add(new String[] { "Daniel Racers", "Paula Ruiz", "Liam" });
		tiempos.add(new double[] { 5.50, 4.90, 4.60, 6.40 });

		equipos.add(new String[] { "Natalia Blazers", "Hector Sanchez", "Isabella" });
		tiempos.add(new double[] { 5.80, 4.70, 4.70, 6.30 });

		equipos.add(new String[] { "Pablo Speedsters", "Eva Hernandez", "Noah" });
		tiempos.add(new double[] { 6.10, 5.00, 4.80, 6.20 });
	}

	private static ArrayList<String[]> clasificarEquipos(ArrayList<String[]> equipos, ArrayList<double[]> tiempos) {
		ArrayList<String[]> equiposOrdenados = new ArrayList<>(equipos);
		equiposOrdenados.sort((e1, e2) -> Double.compare(calcularMedia(tiempos.get(equipos.indexOf(e1))),
				calcularMedia(tiempos.get(equipos.indexOf(e2)))));
		return equiposOrdenados;
	}

	private static double calcularKmh(double k, double h) {
		return k / h;
	}

	private static double calcularKmhEquipo(double[] tiempoEquipo) {
		double sumaKmh = 0;
		for (int i = 0; i < tiempoEquipo.length; i++) {
			sumaKmh += calcularKmh(etapas[i], tiempoEquipo[i]);
		}
		return sumaKmh / tiempoEquipo.length;
	}

	private static double redondearDecimales(double n, int cantidad) {
		double decimales = Math.pow(10.0, cantidad);

		return Math.round(n * decimales) / decimales;
	}

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

	private static double calcularMedia(double[] tiempos) {
		double sum = 0;
		for (double tiempo : tiempos) {
			sum += tiempo;
		}
		return sum / tiempos.length;
	}
}
