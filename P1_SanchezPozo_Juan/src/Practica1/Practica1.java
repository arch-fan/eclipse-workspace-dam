package Practica1;

import java.util.Scanner;

public class Practica1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		sc.close();
	}

	static void esPar(int number) {
		if (number % 2 == 0) {
			System.out.println("El numero " + number + " es par");
		} else {
			System.out.println("El numero " + number + " es impar");
		}
	}

	static <T> void diaDeLaSemana(T dia) {

	    if (dia instanceof String) {
	        String diaString = (String)dia;
	        switch (diaString.toLowerCase()) {
	            case "lunes":
	                System.out.println("1");
	                break;
	            case "martes":
	                System.out.println("2");
	                break;
	            case "miercoles":
	                System.out.println("3");
	                break;
	            case "jueves":
	                System.out.println("4");
	                break;
	            case "viernes":
	                System.out.println("5");
	                break;
	            case "sabado":
	                System.out.println("6");
	                break;
	            case "domingo":
	                System.out.println("7");
	                break;
	            default:
	                System.out.println("Día no reconocido");
	        }
	    } else if (dia instanceof Number) {
	        int diaInt = ((Number)dia).intValue();
	        switch (diaInt) {
	            case 1:
	                System.out.println("Lunes");
	                break;
	            case 2: 
	                System.out.println("Martes");
	                break;
	            case 3:
	                System.out.println("Miercoles");
	                break;
	            case 4:
	                System.out.println("Jueves");
	                break;
	            case 5:
	                System.out.println("Viernes");
	                break;
	            case 6:
	                System.out.println("Sabado");
	                break;
	            case 7:
	                System.out.println("Domingo");
	                break;
	            default:
	                System.out.println("Valor no válido para el día de la semana.");
	        }
	    } else {
	        System.out.println("Tipo de dato no reconocido");
	    }

	}

}
