package practica8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Avion {
    private final String nombre;
    private final List<Parte> partes;
    private static final String dataPath = "data/aviones.csv";

    private static Avion parseLine(String line) {
        String[] parsedLine = line.split(";");

        List<Integer> partesId = Arrays.stream(parsedLine[1].split(","))
                .map(Integer::parseInt)
                .toList();

        List<Parte> partesJoin = Parte.loadFromCsv()
                .stream()
                .filter(parte -> partesId.contains(parte.getId()))
                .toList();

        return new Avion(parsedLine[0], partesJoin);
    }

    public static List<Avion> loadFromCsv() {
        try (Stream<String> lineas = Files.lines(Path.of(dataPath))) {
            return lineas.map(Avion::parseLine).toList();
        } catch (IOException e) {
            System.err.println("Error reading " + dataPath + ": " + e.getMessage());
            return null;
        }
    }

    public Avion(String nombre, List<Parte> partes) {
        this.nombre = nombre;
        this.partes = partes;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Parte> getPartes() {
        return partes;
    }


}
