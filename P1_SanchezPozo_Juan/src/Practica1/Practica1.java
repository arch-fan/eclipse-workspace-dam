package Practica1;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Arrays;

public class Practica1 {

	static void print(String message) {
		System.out.print(message);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		print("¡Bienvenido al ejercicio! Elije una de las 4 funcionalidades.");

		while (true) {
			print("""


					    1) Verifica si un número es par
					    2) Escribe un numero del 1-7 o un dia de la semana para recibir el número o el día.
					    3) Da una calificación alfabética y recibe el equivalente numérico
					    4) Convierte tu salario bruto a neto
					    5) Salir del ejercicio

					Elije tu opción: """);

			if (!sc.hasNextByte()) {
				print("Elige una opción válida!");
				sc.next();
				continue;
			}

			byte siguienteOpcion = sc.nextByte();

			switch (siguienteOpcion) {
			case 1:
				print("\nIntroduce el número: ");
				if (!sc.hasNextInt()) {
					print("\nIntroduce un número válido!");
					sc.next();
					continue;
				}
				esPar(sc.nextInt());
				break;
			case 2:
				print("""

						Debes indicar un número si quieres el día de la semana, o viceversa.
						Por ejemplo, si pones 1, recibes lunes, y si pones viernes, recibes 5.

						Introduce el número o el dia de la semana: """);
				try {
					byte numeroDia = sc.nextByte();
					diaDeLaSemana(numeroDia);
				} catch (InputMismatchException error) {
					diaDeLaSemana(sc.nextLine());
				}
				break;
			case 3:
				print("""

						Debes introducir una nota y se te devolverá un caracter.
						Por ejemplo, A devuelve 15, la nota más alta. Puedes introducir (A, B, C, D o E)

						Introduce la nota en caracter: """);
				String charInput = sc.next();
				if (charInput.length() > 1) {
					print("Introduce una letra (calificación) válida!");
					continue;
				}
				calificacionPorLetra(charInput.toLowerCase().charAt(0));
				break;
			case 4:
				print("""

						Al introducir la factura, ten en cuenta que si tienes que poner decimales debes ponerlos con coma.
						Por ejemplo: 1399,95

						Introduce la factura en bruto: """);
				if (!sc.hasNextDouble()) {
					print("Introduce un valor válido!");
					sc.next();
					continue;
				}
				calcularFactura(sc.nextDouble());
				break;
			case 5:
				sc.close();
				System.exit(0);
				break;
			default:
				print("Introduce una opcion válida!");
				break;
			}

		}

	}

	static void esPar(int number) {
		if (number % 2 == 0) {
			print("\nEl numero " + number + " es par");
		} else {
			print("\nEl numero " + number + " es impar");
		}
	}

	static <T> void diaDeLaSemana(T dia) {
	    String[] nombresDias = { "lunes", "martes", "miércoles", "jueves", "viernes", "sábado", "domingo" };

	    if (dia instanceof String) {
	        String diaString = ((String) dia).toLowerCase();
	        int indice = Arrays.asList(nombresDias).indexOf(diaString);
	        if (indice != -1) {
	            System.out.println("\nEl " + diaString + " corresponde al día " + (indice + 1));
	        } else {
	            System.out.println("Día no reconocido");
	        }
	    } else {
	        int diaInt = ((Number) dia).intValue();
	        if (diaInt >= 1 && diaInt <= 7) {
	            System.out.println("\nEl día número " + diaInt + " corresponde al " + nombresDias[diaInt - 1]);
	        } else {
	            System.out.println("\nValor no válido para el día de la semana.");
	        }
	    }
	}

	private static void imprimirCalificacion(int valor) {
		print("La calificación es " + valor);
	}

	static void calificacionPorLetra(char letraCalificacion) {
		char[] calificaciones = { 'e', 'd', 'c', 'b', 'a' };

		int index = letraCalificacion - 'a';

		if (index >= 0 && index < calificaciones.length) {
			imprimirCalificacion(11 + index);
		} else {
			print("No has introducido la nota correcta.");
		}
	}

	static void calcularFactura(double importeBruto) {
		double importeNeto = importeBruto + (importeBruto * 0.21);
		print(String.format("El importe neto es %.2f", importeNeto));
	}
}
