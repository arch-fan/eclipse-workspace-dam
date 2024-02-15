package practica7;

import java.io.IOException;

public class Analizador {
	private final Fichero lector;
	private final Estadisticas estadisticas;
	
	public Analizador(Fichero lector) throws IOException {
        this.lector = lector;
        this.estadisticas = new Estadisticas();
        
        try {
            this.lector.processFile(linea -> this.estadisticas.generarEstadisticas(linea));
        } catch (IOException e) {
            throw e;
        }
    }

	public Fichero getLector() {
		return lector;
	}

	public Estadisticas getEstadisticas() {
		return estadisticas;
	}	
}
