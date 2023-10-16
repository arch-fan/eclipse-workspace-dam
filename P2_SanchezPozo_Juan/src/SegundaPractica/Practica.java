package SegundaPractica;

import java.util.Scanner;

public class Practica {

    private static void print(String text) {
        System.out.println(text);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String texto = "";

        do {
            print("Introduce el texto: ");
            texto = sc.nextLine();
        } while (texto.isBlank());

        print("El texto contiene " + contarPalabras(texto) + " palabras, " + contarCaracteres(texto) + " caracteres, "
                + contarDigitos(texto) + " son numeros");

        String palabraMasLarga = encontrarPalabraMasLarga(texto);
        print("La palabra mas larga es " + palabraMasLarga + " y tiene " + contarCaracteres(palabraMasLarga)
                + " caracteres");

        String tieneNaves = estaNaves(texto) ? "Si" : "No";
        print(tieneNaves + " aparece la palabra Naves");
        
        String letrasQueNoAparecen = String.valueOf(letraQueNoEstaDelAbc(texto)).replace(" ", "");
        print("No aparecen las siguientes letras del abecedario: " + String.join(", ", letrasQueNoAparecen.split("")));
        
        int index = 0;
        for(int i : cuantasVecesApareceCadaNumeroArray(texto)) {
            if(i > 0) {
                print("El numero " + index + " aparece " + i + " veces");
            }
            index++;
        }

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
    private static char[] letraQueNoEstaDelAbc(String texto) {
        char[] abecedario = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        for (Character c : abecedario) {
            if (texto.contains(c.toString())) {
                abecedario[c - 'a'] = ' ';
            }
        }
        
        return abecedario;
    }
    
    private static int[] cuantasVecesApareceCadaNumeroArray(String texto) {
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