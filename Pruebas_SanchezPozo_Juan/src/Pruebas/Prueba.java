package Pruebas;

import java.util.ArrayList;
import java.util.Arrays;

public class Prueba {

	public static void main(String[] args) {
		ArrayList<Avion> aviones = new ArrayList<>(
				Arrays.asList(new Avion("Boing747", "WWW000"), new Avion("Airbus370", "LKMASD")));

		Piloto piloto = new Piloto("Paco", 01222244, "Tipo 1", 35, "12-01-2009", aviones);
		
		System.out.println(piloto);

	}

}
