package practica7;

import java.util.Map;
import java.util.TreeMap;

public class Estadisticas {
	private int nConsonantes = 0;
	private final Map<Character, Integer> estadisticaVocales = new TreeMap<>();
	private int nLineas = 0;
	private int nLineasBlanco = 0;
	private int nNumeros = 0;
	private final StringBuilder textoCambiado = new StringBuilder();
	
	/**
	 * Metodo para sumar las estadisticas de la linea indicada
	 * @param line Linea de texto a procesar
	 * */
	public void generarEstadisticas(String line) {
        if (line.trim().isEmpty()) {
            nLineasBlanco++;
        } else {
            nLineas++;
            for (char c : line.toCharArray()) {
            	char lChar = Character.toLowerCase(c);
                if (Character.isDigit(c)) {
                    nNumeros++;
                } else if (esVocal(lChar)) {
                    estadisticaVocales.merge(lChar, 1, Integer::sum);
                } else if (esConsonante(lChar)) {
                    nConsonantes++;
                }
                // Cambio de mayúsculas a minúsculas y viceversa
                if (Character.isUpperCase(c)) {
                    textoCambiado.append(Character.toLowerCase(c));
                } else if (Character.isLowerCase(c)) {
                    textoCambiado.append(Character.toUpperCase(c));
                } else {
                    textoCambiado.append(c);
                }
            }
        }
        textoCambiado.append("\n");
    }
    
    private static boolean esVocal(char c) {
        return "aeiouAEIOU".indexOf(c) != -1;
    }
    
    private static boolean esConsonante(char c) {
        return "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ".indexOf(c) != -1;
    }
    
    /**
     * Metodo para generar el texto de las estadisticas.
     * @return String con todas las estadisticas
     * */
    public String resultado() {
        StringBuilder resultado = new StringBuilder();
        
        int totalVocales = estadisticaVocales.values().stream().mapToInt(Integer::intValue).sum();
        
        resultado.append("Numero de consonantes = ").append(nConsonantes).append("\n");
        resultado.append("------------------------------\n");
        resultado.append("Numero de vocales = ").append(totalVocales).append("\n");
        
        estadisticaVocales.forEach((character, quantity) -> {
            double porcentaje = totalVocales > 0 ? 100.0 * quantity / totalVocales : 0;
            resultado.append("Porcentaje de '").append(character).append("': ")
                      .append(String.format("%.2f", porcentaje)).append("%\n");
        });

        resultado.append("------------------------------\n");
        
        resultado.append("Numero de lineas = ").append(nLineas).append("\n");
        resultado.append("Numero de lineas en blanco = ").append(nLineasBlanco).append("\n");
        resultado.append("Numero de numeros = ").append(nNumeros).append("\n");
        resultado.append("------------------------------\n");
        resultado.append("El texto cambiado es:\n").append(textoCambiado);
        resultado.append("------------------------------");
        
        return resultado.toString();
    }

	public int getnConsonantes() {
		return nConsonantes;
	}

	public int getnVocales() {
		return estadisticaVocales.values().stream().mapToInt(Integer::intValue).sum();
	}

	public Map<Character, Integer> getEstadisticaVocales() {
		return estadisticaVocales;
	}

	public int getnLineas() {
		return nLineas;
	}

	public int getnLineasBlanco() {
		return nLineasBlanco;
	}

	public int getnNumeros() {
		return nNumeros;
	}

	public StringBuilder getTextoCambiado() {
		return textoCambiado;
	}
}
