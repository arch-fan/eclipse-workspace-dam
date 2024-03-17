package practica8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Empleado {
    private final String nombre;
    private final String cargo;
    private final int jornada;
    private static final String dataPath = "data/empleados.csv";
    private static final List<String> cargos = Arrays.asList("oficial", "ayudante");

    public Empleado(String nombre, String cargo) {
        if (!cargos.contains(cargo)) {
            throw new IllegalArgumentException("El cargo debe ser oficial o ayudante.");
        }
        this.nombre = nombre;
        this.cargo = cargo;
        this.jornada = 8;
    }

    private static Empleado parseLine(String line) {
        String[] parsedLine = line.split(";");

        return new Empleado(parsedLine[0], parsedLine[1]);
    }

    public static List<Empleado> loadFromCsv() {
        try (Stream<String> lineas = Files.lines(Path.of(dataPath))) {
            return lineas.map(Empleado::parseLine).toList();
        } catch (IOException e) {
            System.err.println("Error reading " + dataPath + ": " + e.getMessage());
            return null;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public int getJornada() {
        return jornada;
    }
}
