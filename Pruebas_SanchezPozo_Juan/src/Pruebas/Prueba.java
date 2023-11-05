package Pruebas;

import java.util.ArrayList;

public class Prueba {

	public static void main(String[] args) {
		ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(5);
        numeros.add(2);
        numeros.add(8);
        numeros.add(1);
        numeros.add(3);

        System.out.println(numeros);
        numeros.sort((a, b) -> a + b);
        System.out.println(numeros);
        
	}

}
