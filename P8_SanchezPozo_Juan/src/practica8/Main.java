package practica8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Avion> aviones = Avion.loadFromCsv();
        List<Empleado> empleados = Empleado.loadFromCsv();
        List<Pieza> stockPiezas = Pieza.loadFromCsv();

        int totalHorasOficiales = empleados
                .stream()
                .filter(empleado -> empleado.getCargo().equals("oficial"))
                .mapToInt(Empleado::getJornada)
                .sum();

        int totalHorasAyudante = empleados
                .stream()
                .filter(empleado -> empleado.getCargo().equals("ayudante"))
                .mapToInt(Empleado::getJornada)
                .sum();

        AtomicInteger mutableHorasOficial = new AtomicInteger(totalHorasOficiales);
        AtomicInteger mutableHorasAyudante = new AtomicInteger(totalHorasAyudante);

        List<Mantenimiento> mantenimientosCompletados = new ArrayList<>();

        for(Avion avion : aviones) {
            for(Parte parte : avion.getPartes()) {
                for(Mantenimiento mantenimiento : parte.getMantenimientos()) {

                    if(mantenimiento.canBeDone(stockPiezas, mutableHorasOficial.get(), mutableHorasAyudante.get())) {
                        System.out.println(
                                "Se ha realizado la tarea "
                                        + capitalize(mantenimiento.getNombre())
                                        + " a la parte " + parte.getNombre()
                                        + " del avion " + avion.getNombre()
                        );
                        mantenimiento.consumirMantenimiento(stockPiezas, mutableHorasOficial, mutableHorasAyudante);
                        mantenimientosCompletados.add(mantenimiento);
                    }

                }
            }
        }

        Map<String, Long> herramientasUsadas = mantenimientosCompletados
                .stream()
                .flatMap(m -> m.getHerramientasNecesarias().stream())
                .collect(Collectors.groupingBy(
                        Herramienta::getNombre,
                        Collectors.counting()
                ));

        System.out.println(
                " ----------------------------------------\n" +
                "Se han usado las siguientes herramientas:"
        );

        herramientasUsadas.forEach((k, v) -> {
            System.out.println("  - " + capitalize(k) + ": " + v);
        });

        System.out.println(
                " ----------------------------------------\n" +
                "Horas restantes de los empleados:\n" +
                "  - Horas restantes de oficiales: " + mutableHorasOficial.get() +
                "\n  - Horas restantes de ayudantes: " + mutableHorasOficial.get()
        );
    }

    private static String capitalize(String t) {
        return t.substring(0, 1).toUpperCase() + t.substring(1);
    }
}