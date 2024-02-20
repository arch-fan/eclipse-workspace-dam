package practica7;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        System.out.println("---- Bienvenido al generador de estadísticas ----");
        // Clase estadisticas en la que guardaremos las estadisticas calculadas
        Estadisticas estadisticas = new Estadisticas();
        
        // Usando try-with-resources podemos usar un try catch que cierra los recursos
        try (Scanner sc = new Scanner(System.in)) {
            String nombreFichero = readFileName(sc);
            processFile(nombreFichero, estadisticas);
            System.out.println(estadisticas.resultado());
            writeInFile(estadisticas.resultado(), nombreFichero);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metodo para pedir al usuario un nombre de fichero hasta que termine por .txt
     * @param sc Escaner principal
     * @return nombre del fichero
     * */ 
    private static String readFileName(Scanner sc) {
        String nombreFichero;
        while (true) {
            System.out.print("Introduce el nombre del fichero: ");
            nombreFichero = sc.nextLine().trim();

            if (nombreFichero.endsWith(".txt")) {
                return nombreFichero;
            } else {
                System.out.println("El fichero debe ser en formato txt!");
            }
        }
    }

    /**
     * Metodo para generar las estadisticas a partir del nombre del fichero
     * @param nombreFichero nombre del fichero a procesar
     * @param estadisticas Instancia de la clase estadisticas en la que se guardara
     * */
    private static void processFile(String nombreFichero, Estadisticas estadisticas) {
        try (Stream<String> stream = Files.lines(Paths.get(nombreFichero))) {
            stream.forEach(line -> estadisticas.generarEstadisticas(line));
        } catch (IOException e) {
            System.out.println("El fichero introducido no existe");
        }
    }

    /**
     * Metodo para escribir un string en el fichero indicado
     * @param texto Texto a escribir en el fichero
     * @param nombreFichero Nombre del fichero en el que escribir
     * */
    private static void writeInFile(String texto, String nombreFichero) {
        String nombreNuevoFichero = "resultado-" + nombreFichero;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreNuevoFichero))) {
            bw.write(texto);
            System.out.println("El texto ha sido guardado en " + nombreNuevoFichero);
        } catch (IOException e) {
            System.out.println("Hubo un error al escribir en el fichero: " + e.getMessage());
        }
    }
}
