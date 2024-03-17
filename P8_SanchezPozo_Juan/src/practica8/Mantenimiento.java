package practica8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Mantenimiento {
    private final int id;
    private final String nombre;
    private final List<Pieza> piezasNecesarias;
    private final List<Herramienta> herramientasNecesarias;
    private final int horasOficial;
    private final int horasAyudante;
    private static final String dataPath = "data/mantenimientos.csv";

    public Mantenimiento(
            int id,
            String nombre,
            List<Pieza> piezas,
            List<Herramienta> herramientas,
            int horasOficial,
            int horasAyudante
    ) {
        this.id = id;
        this.nombre = nombre;
        this.piezasNecesarias = piezas;
        this.herramientasNecesarias = herramientas;
        this.horasOficial = horasOficial;
        this.horasAyudante = horasAyudante;
    }

    private static Mantenimiento parseLine(String line) {
        String[] parsedLine = line.split(";");

        List<Pieza> piezas = Arrays.stream(parsedLine[2].split(","))
                .map(unparsedPieza -> {
                    String[] parsedPieza = unparsedPieza.split(":");
                    return new Pieza(parsedPieza[0], Integer.parseInt(parsedPieza[1]));
                })
                .toList();

        List<Herramienta> herramientas = Arrays.stream(parsedLine[3].split(","))
                .map(Herramienta::new)
                .toList();

        return new Mantenimiento(
                Integer.parseInt(parsedLine[0]),
                parsedLine[1],
                piezas,
                herramientas,
                Integer.parseInt(parsedLine[4]),
                Integer.parseInt(parsedLine[5])
        );
    }

    public static List<Mantenimiento> loadFromCsv() {
        try (Stream<String> lineas = Files.lines(Path.of(dataPath))) {
            return lineas.map(Mantenimiento::parseLine).toList();
        } catch (IOException e) {
            System.err.println("Error reading " + dataPath + ": " + e.getMessage());
            return null;
        }
    }

    public boolean canBeDone(List<Pieza> storePiezas, int horasOficialesTotales, int horasAyudantesTotales) {
        if (horasAyudante > horasAyudantesTotales || horasOficial > horasOficialesTotales) {
            return false;
        }

        for (Pieza pieza : piezasNecesarias) {
            Pieza piezaCorrespondiente = storePiezas
                    .stream()
                    .filter(p -> p.getNombre().equals(pieza.getNombre()))
                    .findFirst()
                    .orElse(null);

            if (piezaCorrespondiente == null) {
                return false;
            }

            if (piezaCorrespondiente.getCantidad() - pieza.getCantidad() < 0) {
                return false;
            }
        }

        return true;
    }

    public void consumirMantenimiento(List<Pieza> storePiezas, AtomicInteger horasOficialesTotales, AtomicInteger horasAyudantesTotales) {
        horasOficialesTotales.getAndAdd(-horasOficial);
        horasAyudantesTotales.getAndAdd(-horasAyudante);

        for (Pieza pieza : piezasNecesarias) {
            Pieza piezaFromStore = storePiezas
                    .stream()
                    .filter(p -> p.getNombre().equals(pieza.getNombre()))
                    .findFirst()
                    .get();

            piezaFromStore.setCantidad(piezaFromStore.getCantidad() - pieza.getCantidad());
        }
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pieza> getPiezasNecesarias() {
        return piezasNecesarias;
    }

    public List<Herramienta> getHerramientasNecesarias() {
        return herramientasNecesarias;
    }

    public int getHorasOficial() {
        return horasOficial;
    }

    public int getHorasAyudante() {
        return horasAyudante;
    }
}
