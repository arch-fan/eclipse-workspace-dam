package ExamenPrueba;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Examen {

	public static Scanner sc = new Scanner(System.in);
	public static String cadena = "";
	public static int digito = -1;
	public static String direccion = "";

	public static void main(String[] args) {

		System.out.print("Introduzca un texto a cifrar: ");

//		recuperarDatos();
		datosEjemplo();

		String cadenaCifrada = cifrarTexto(cadena, digito);

		System.out.println("El texto inicial es " + cadena);
		System.out.println("El texto resultante es " + cadenaCifrada);

		double precioCifradoEuros = precioTextoEuros(cadenaCifrada);
		System.out.println("Lo que cuesta en euros es " + precioCifradoEuros);
		System.out.println("Lo que cobramos a la embajada Rusa es " + euroToRublo(precioCifradoEuros));
		System.out.println("Lo que cobramos a la embajada Ucraniana es " + euroToGrivnas(precioCifradoEuros));

		System.out.print("Introduce el destinatario: ");
		boolean esAlcala = viveEnAlcala(direccion);
		System.out.println("El destinatario " + (esAlcala ? "si" : "no") + " vive en Alcala");

	}

	public static void recuperarDatos() {
		cadena = sc.nextLine();

		digito = -1;
		{
			boolean isString = true;
			while (isString) {
				System.out.print("Introduzca un valor de cifrado: ");
				try {
					digito = Integer.parseInt(sc.nextLine());
					isString = false;
				} catch (NumberFormatException e) {
					System.out.println("Introduce un valor correcto! Debe ser un numero entero");
				}
			}
		}

		System.out.print("Introduce el destinatario: ");
		direccion = sc.nextLine();
	}

	public static void datosEjemplo() {
		cadena = "Hoy es martes, Tenemos examen";
		digito = 3;
		direccion = "C/ Rio Tajuña nº 2 CP 28801 Alcalá de Henares";
	}

	public static String cifrarTexto(String texto, int valor) {

		ArrayList<Character> abecedario = new ArrayList<>();
		String textoCifrado = "";
		char[] abc = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ".toCharArray();

		for (char c : abc) {
			abecedario.add(c);
		}

		String parsedText = texto.replaceAll(" ", "").toUpperCase();

		for (char letter : parsedText.toCharArray()) {

			if (!abecedario.contains(letter)) {
				textoCifrado += "#";
				continue;
			}

			int letterPos = abecedario.indexOf(letter);

			int valorAsumar = valor >= abc.length ? valor % abc.length : valor;

			int nextLetterPos = letterPos + valorAsumar >= abc.length ? (letterPos + valorAsumar) - abc.length
					: letterPos + valorAsumar;

			textoCifrado += abecedario.get(nextLetterPos);
		}

		return textoCifrado;
	}

	public static double precioTextoEuros(String texto) {
		return texto.length() * 0.25;
	}

	public static double euroToRublo(double euro) {
		return euro / 0.015;
	}

	public static double euroToGrivnas(double euro) {
		return euro / 0.026;
	}

	public static boolean viveEnAlcala(String destinatario) {
		Pattern p = Pattern.compile("CP [28801-28808]");
		Matcher m = p.matcher(destinatario.trim());
		return m.find();
	}

}
