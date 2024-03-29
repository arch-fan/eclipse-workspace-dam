package practica_POO03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		// ArrayList en el que trabajaremos principalmente con todos nuestros empleados
		List<Empleado> empleados = Arrays.asList(
				new Empleado("Pedro", "García", 26926.21),
		        new Empleado("Laura", "Sánchez", 24961.15),
		        new Empleado("José", "Pérez", 28059.87),
		        new Empleado("José", "Gómez", 22787.88),
		        new Empleado("Sofía", "Jiménez", 26939.39),
		        new Empleado("Luis", "Sánchez", 24323.47),
		        new Empleado("Ana", "Fernández", 24545.59),
		        new Empleado("Sofía", "Sánchez", 20462.43),
		        new Empleado("Carlos", "López", 29664.14),
		        new Empleado("Luis", "Sánchez", 27493.68),
		        new Empleado("Carmen", "Fernández", 23458.43, 46, "Calle 70", "senior", 22, 0, 2),
		        new Empleado("Carmen", "García", 27805.46, 24, "Calle 6", "arquitecto", 33, 1, 4),
		        new Empleado("Juan", "Pérez", 20358.27, 64, "Calle 7", "senior", 32, 0, 5),
		        new Empleado("Carlos", "Gómez", 23864.87, 25, "Calle 59", "manager", 29, 4, 5),
		        new Empleado("Carmen", "Sánchez", 23849.0, 54, "Calle 81", "junior", 30, 5, 1),
		        new Empleado("Luis", "Jiménez", 21694.04, 19, "Calle 20", "senior", 24, 0, 1),
		        new Empleado("Pedro", "Hernández", 28268.35, 47, "Calle 39", "senior", 37, 5, 9),
		        new Empleado("Laura", "Gómez", 28378.97, 52, "Calle 77", "arquitecto", 35, 2, 1),
		        new Empleado("Luis", "Gómez", 27673.18, 35, "Calle 45", "manager", 36, 5, 0),
		        new Empleado("María", "López", 21161.42, 53, "Calle 52", "manager", 37, 4, 10)
		);
				
		System.out.println("--- Gasto total de la empresa ---");
		System.out.println(empleados.stream().mapToDouble(Empleado::gastoAnual).sum() + "€");
		
		System.out.println();
		
		System.out.println("--- Sueldo neto medio ---");
		empleados.stream().mapToDouble(Empleado::getSueldoNeto).average().ifPresent(average -> {
			System.out.println(average + "€");
		});
		
		System.out.println();
		
		System.out.println("--- Numero medio de hijos por trabajador ---");
		empleados.stream().mapToInt(Empleado::getNumeroDeHijos).average().ifPresent(average -> {
			System.out.println(average);
		});
		
		System.out.println();
		
		System.out.println("--- Cuantos empleados hay por cada categoría ---");
		empleados.stream()
				.map(Empleado::getCategoria)
				.collect(Collectors.groupingBy(
						// Agrupamos en un Map por nombre de la categoria
							categoria -> categoria.getNombre(),
							Collectors.counting()
						)
				).forEach((categoria, cantidad) -> {
					// Usamos el metodo capitalize para convertir la primera letra a mayuscula
					System.out.println(capitalize(categoria) + ": " + cantidad);
				});
		
		System.out.println();
		
		System.out.println("--- El trabajador que mas cobra ---");
		empleados.stream().max(Comparator.comparingDouble(Empleado::getSueldoNeto)).ifPresent(empleado -> {
			System.out.println(empleado.getNombre() + ": " + empleado.getSueldoNeto() + "€");
		});
		
		System.out.println();
		
		System.out.println("--- Trabajadores que tienen coche y qué coche tienen asignado ---");
		empleados
			.stream()
			.forEach(empleado -> {
				// Iteramos cada material y filtramos por su categoria coche, si lo contiene lo mostramos
					empleado.getMaterial()
						.stream()
						.filter(material -> material.getCategoria().equals("coche"))
						.forEach(material -> 
							System.out.println(empleado.getNombre() + " tiene el " + material.getNombre()));
			});

	}

	public static String capitalize(String txt) {
		return txt.substring(0, 1).toUpperCase() + txt.substring(1, txt.length());
	}

}
