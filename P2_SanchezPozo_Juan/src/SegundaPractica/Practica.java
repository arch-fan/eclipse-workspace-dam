package SegundaPractica;

public class Practica {

    private static void print(String text) {
        System.out.println(text);
    }

    public static void main(String[] args) {
        print("Tiene " + contarPalabras("Este es J"));
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

    private static int encontrarPalabraMasLarga(String texto) {

        String palabraMasLarga = "";

        for (String palabra : texto.split(" ")) {
            if (palabraMasLarga.length() < palabra.length()) {
                palabraMasLarga = palabra;
            }
        }
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
}
