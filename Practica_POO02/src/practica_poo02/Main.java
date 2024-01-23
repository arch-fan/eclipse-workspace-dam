package practica_poo02;

import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
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
		
		Factura factura = new Factura(desperfectos, 3200, 2347);
		
		System.out.println("El precio a pagar es " + factura.getaPagar() + " y te devuelven " + Math.round(factura.getaDevolver() * 100) / 100.0 );
	}

}
