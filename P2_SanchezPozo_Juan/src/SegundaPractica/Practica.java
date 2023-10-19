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
        // Separamos por espacios y contamos la longitud del array que se ha creado con
        // el metodo split.
        return texto.split(" ").length;
    }

    private static int contarCaracteres(String texto) {
        // Contamos la longitud del string a partir del metodo length.
        return texto.length();
    }

    private static int contarDigitos(String texto) {

        int contador = 0;

        // Recorremos el texto caracter por caracter
        for (char c : texto.toCharArray()) {
            // Si el caracter del texto es un dígito, sumamos al contador.
            if (Character.isDigit(c)) {
                contador++;
            }
        }

        return contador;
    }

    // Segundo punto

    private static String encontrarPalabraMasLarga(String texto) {

        String palabraMasLarga = "";

        // Recorremos el texto palabra por palabra, separando por espacios.
        for (String palabra : texto.split(" ")) {
            // Si la palabra guardada en la variable palabraMasLarga es mayor a la siguiente
            // palabra recorrida, la guardamos en la variable.
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
            if (texto.toLowerCase().contains(c.toString())) {
                // Utilizamos la propiedad de los chars de poder restarse entre si para poder
                // conseguir la posicion en la variable abecedario. Y la "borramos".
                abecedario[c - 'a'] = ' ';
            }
        }

        return abecedario;
    }

    // Para obtener de una manera más simple el resultado, he optado por guardar en
    // 10 posiciones de un array la cantidad de veces que sale un numero. La
    // posicion 0 guarda las veces que ha salido 0. La posicion 1 guarda todas las
    // veces que ha salido 1 y asi consecutivamente.
    private static int[] cuantasVecesApareceCadaNumero(String texto) {
        // Nuevo Array de 10 digitos [0-9]
        int[] digitos = new int[10];

        for (char c : texto.toCharArray()) {
            // Si es digito el caracter, sumamos 1 a la posicion del numero.
            if (Character.isDigit(c)) {
                int index = Character.getNumericValue(c);
                digitos[index]++;
            }
        }

        return digitos;
    }

    // Sexto punto
    private static String invertirTexto(String texto) {
        // Explotamos la funcionalidad de la clase StringBuilder para poder invertir el
        // string de una manera más simple.
        return new StringBuilder(texto).reverse().toString();
    }

    // Séptimo punto
    private static String cambiarVocalesPorI(String texto) {
        // Con una RegEx encontramos todas las vocales minusculas y las reemplazamos por
        // 'i' minuscula, y con la otra hacemos lo mismo pero con las mayusculas.
        return texto.replaceAll("[aeou]", "i").replaceAll("[AEOU]", "I");
    }
}