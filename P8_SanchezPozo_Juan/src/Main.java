import java.util.List;

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

        for(Avion avion : aviones) {
            for(Parte parte : avion.getPartes()) {
                for(Mantenimiento mantenimiento : parte.getMantenimientos()) {

                    if(mantenimiento.canBeDone(stockPiezas, totalHorasOficiales, totalHorasAyudante)) {
                        System.out.println(
                                "Se puede realizar la tarea "
                                        + mantenimiento.getNombre()
                                        + " a la parte " + parte.getNombre()
                                        + " del avion " + avion.getNombre()
                        );
                        mantenimiento.consumirMantenimiento(stockPiezas, totalHorasOficiales, totalHorasAyudante);
                    }

                }
            }
        }

    }
}