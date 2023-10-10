package SegundaPractica;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Practica {

    private static void print(String text) {
        System.out.println(text);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        print("Introduce el texto: ");
        String texto = sc.nextLine();

        print("El texto contiene " + contarPalabras(texto) + " palabras, " + contarCaracteres(texto) + " caracteres, "
                + contarDigitos(texto) + " son numeros");

        String palabraMasLarga = encontrarPalabraMasLarga(texto);
        print("La palabra mas larga es " + palabraMasLarga + " y tiene " + contarCaracteres(palabraMasLarga)
                + " caracteres");

        String tieneNaves = estaNaves(texto) ? "Si" : "No";
        print(tieneNaves + " aparece la palabra Naves");

        print("No aparecen las siguientes letras del abecedario: " + letraQueNoEstaDelAbc(texto));

        cuantasVecesApareceCadaNumero(texto).forEach((clave, valor) -> {
            System.out.println("El numero " + clave + " aparece " + valor + " veces");
        });

        print("El texto al reves es: " + invertirTexto(texto));

        print("El texto con solo i: " + cambiarVocalesPorI(texto));

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

    private static int contarLetras(String texto) {

        int contador = 0;

        for (char c : texto.toCharArray()) {
            if (!Character.isDigit(c)) {
                contador++;
            }
        }

        return contador;
    }

    // Tercer punto
    private static boolean estaNaves(String texto) {
        return texto.toLowerCase().contains("naves");
    }

    // Cuarto punto
    private static Set<Character> letraQueNoEstaDelAbc(String texto) {
        char[] abecedario = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        Set<Character> noEsta = new HashSet<>();

        for (Character c : abecedario) {
            if (!texto.contains(c.toString())) {
                noEsta.add(c);
            }
        }

        return noEsta;
    }

    // Quinto Punto
    private static HashMap<Character, Integer> cuantasVecesApareceCadaNumero(String texto) {

        HashMap<Character, Integer> cadaNumero = new HashMap<>();

        for (char c : texto.toCharArray()) {
            if (Character.isDigit(c)) {
                if (!cadaNumero.containsKey(c)) {
                    cadaNumero.put(c, 1);
                } else {
                    Integer valor = cadaNumero.get(c);
                    Integer nuevoValor = valor + 1;
                    cadaNumero.put(c, nuevoValor);
                }
            }
        }

        return cadaNumero;

    }

    // Sexto punto
    private static String invertirTexto(String texto) {
        StringBuilder textoInvertido = new StringBuilder();

        textoInvertido.append(texto);
        textoInvertido.reverse();

        return textoInvertido.toString();
    }

    // Séptimo punto
    private static String cambiarVocalesPorI(String texto) {
        char[] vocales = { 'a', 'e', 'o', 'u', 'A', 'E', 'O', 'U' };

        String vocalesCambiadas = texto;

        for (Character v : vocales) {
            if (Character.isUpperCase(v)) {
                vocalesCambiadas = vocalesCambiadas.replace(v, 'I');
            } else {
                vocalesCambiadas = vocalesCambiadas.replace(v, 'i');
            }

        }

        return vocalesCambiadas;

    }
}