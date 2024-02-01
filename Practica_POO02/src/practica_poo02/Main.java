package practica_poo02;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		// Anadir desperfectos de la tabla.
		List<Desperfecto> desperfectos = Arrays.asList(
				new Luminaria("Downlight Led", 20, 25),
				new ServicioGeneral("Comida Congelada", 150, 1),
				new ServicioGeneral("Verduras", 20, 1),
				new ServicioGeneral("Lavadora", 450, 1),
				new Informatico("Switch programable", 250, 1, 3),
				new Informatico("Switch programable", 240, 1, 4),
				new Informatico("Switch programable", 300, 1, 2),
				new Informatico("Etapa de potencia Teatro", 800, 1, 6)
		);
		
		// Creamos la instancia para guardar los desperfectos, junto al precio de cada factura.
		Factura factura = new Factura(desperfectos, 3200, 2347, 48);
		
		System.out.println("--- Total ---");
	
		System.out.println("El precio a pagar es " + factura.getaPagar() + "€ y te devuelven " + roundNumber(factura.getaDevolver(), 2) + "€");
		
		System.out.print("\n");
		
		System.out.println("--- Devolucion por tipo de desperfecto ---");
		
		// Agrupamos por el nombre de la clase junto al valor cubierto.
		Map<String, Double> datosDesperfectos = desperfectos
				.stream()
			    .collect(Collectors.groupingBy(v -> 
			    	v.getClass().getSimpleName(), 
			    	Collectors.summingDouble(Desperfecto::getValorCubiertoTotal)));
		
		// Mostramos los datos de cada tipo de desperfecto.
		datosDesperfectos.forEach((k, v) -> {
			System.out.println(k + ": " + roundNumber(v, 2) + "€");
		});
		
		System.out.println("------------");

	}
	
	// Metodo para redondear especificando la cantidad de decimales deseados
	private static double roundNumber(double number, int pos) {
	    double factor = Math.pow(10, pos);
	    return Math.round(number * factor) / factor;
	}


}
