import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Parte {
    private final int id;
    private final String nombre;
    private final List<Mantenimiento> mantenimientos;
    private static final String dataPath = "data/partes.csv";

    public Parte(int id, String nombre, List<Mantenimiento> mantenimientos) {
        this.id = id;
        this.nombre = nombre;
        this.mantenimientos = mantenimientos;
    }

    private static Parte parseLine(String line) {
        String[] parsedLine = line.split(";");

        List<Integer> mantenimientosId = Arrays.stream(parsedLine[2].split(","))
                .map(Integer::parseInt)
                .toList();

        List<Mantenimiento> mantenimientosJoin = Mantenimiento.loadFromCsv()
                .stream()
                .filter(mantenimiento -> mantenimientosId.contains(mantenimiento.getId()))
                .toList();

        return new Parte(Integer.parseInt(parsedLine[0]), parsedLine[1], mantenimientosJoin);
    }

    public static List<Parte> loadFromCsv() {
        try (Stream<String> lineas = Files.lines(Path.of(dataPath))) {
            return lineas.map(Parte::parseLine).toList();
        } catch (IOException e) {
            System.err.println("Error reading " + dataPath + ": " + e.getMessage());
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Mantenimiento> getMantenimientos() {
        return mantenimientos;
    }
}
