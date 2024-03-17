package practica8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Pieza {
    private final String nombre;
    private int cantidad;
    private static final String dataPath = "data/piezas.csv";


    public Pieza(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    private static Pieza parseLine(String line) {
        String[] parsedLine = line.split(";");

        return new Pieza(parsedLine[0], Integer.parseInt(parsedLine[1]));
    }

    public static List<Pieza> loadFromCsv() {
        try (Stream<String> lineas = Files.lines(Path.of(dataPath))) {
            return lineas.map(Pieza::parseLine).toList();
        } catch (IOException e) {
            System.err.println("Error reading " + dataPath + ": " + e.getMessage());
            return null;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
