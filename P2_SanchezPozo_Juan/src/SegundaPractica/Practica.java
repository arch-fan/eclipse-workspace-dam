package SegundaPractica;

import java.util.Scanner;

public class Practica {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String texto = "";

        do {
            System.out.println("Introduce el texto: ");
            texto = sc.nextLine();
        } while (texto.isBlank());

        System.out.println("El texto contiene " + contarPalabras(texto) + " palabras, " + contarCaracteres(texto)
                + " caracteres, " + contarDigitos(texto) + " son numeros");

        String palabraMasLarga = encontrarPalabraMasLarga(texto);
        System.out.println("La palabra mas larga es " + palabraMasLarga + " y tiene "
                + contarCaracteres(palabraMasLarga) + " caracteres");

        String tieneNaves = estaNaves(texto) ? "Si" : "No";
        System.out.println(tieneNaves + " aparece la palabra Naves");

        String letrasQueNoAparecen = String.valueOf(letraQueNoEstaDelAbc(texto)).replace(" ", "");
        System.out.println("No aparecen las siguientes letras del abecedario: "
                + String.join(", ", letrasQueNoAparecen.split("")));

        int index = 0;
        for (int i : cuantasVecesApareceCadaNumero(texto)) {
            if (i > 0) {
                System.out.println("El numero " + index + " aparece " + i + " veces");
            }
            index++;
        }

        System.out.println("El texto al reves es: " + invertirTexto(texto));

        System.out.println("El texto con solo i: " + cambiarVocalesPorI(texto));

        sc.close();
    }

    // Primer punto
    private static int contarPalabras(String texto) {
        return texto.split(" ").length;
    }

    private static int contarCaracteres(String texto) {
        return texto.length();
    }

    private static int contarDigitos(String texto) {

        int contador = 0;

        for (char c : texto.toCharArray()) {
            if (Character.isDigit(c)) {
                contador++;
            }
        }

        return contador;
    }

    // Segundo punto
    private static String encontrarPalabraMasLarga(String texto) {

        String palabraMasLarga = "";

        for (String palabra : texto.split(" ")) {
            if (palabraMasLarga.length() < palabra.length()) {
                palabraMasLarga = palabra;
            }
        }

        return palabraMasLarga;
    }

    // Tercer punto
    private static boolean estaNaves(String texto) {
        return texto.toLowerCase().contains("naves");
    }

    // Cuarto punto
    private static char[] letraQueNoEstaDelAbc(String texto) {
        char[] abecedario = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

        for (Character c : abecedario) {
            if (texto.contains(c.toString())) {
                abecedario[c - 'a'] = ' ';
            }
        }

        return abecedario;
    }

    private static int[] cuantasVecesApareceCadaNumero(String texto) {
        int[] digitos = new int[10];

        for (char c : texto.toCharArray()) {
            if (Character.isDigit(c)) {
                int index = Character.getNumericValue(c);
                digitos[index]++;
            }
        }

        return digitos;
    }

    // Sexto punto
    private static String invertirTexto(String texto) {
        return new StringBuilder(texto).reverse().toString();
    }

    // Séptimo punto
    private static String cambiarVocalesPorI(String texto) {
        return texto.replaceAll("[aeou]", "i").replaceAll("[AEOU]", "I");
    }
}