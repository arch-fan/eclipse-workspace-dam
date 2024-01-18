package reyesPOO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Carta {
	private String nombreNino;
	private ReyMago reyMago;
	private List<Juguete> juguetes;
	private static final String[] libros = { "Donde viven los monstruos (Maurice Sendak, 1963)",
			"Las aventuras de Alicia en el país de las maravillas (Lewis Carroll, 1865)",
			"Pippi Calzaslargas (Astrid Lindgren, 1945)", "El principito (Antoine de Saint-Exupéry, 1943)",
			"El hobbit (JRR Tolkien, 1937)", "Luces del norte (Philip Pullman, 1995)",
			"El león, la bruja y el armario (CS Lewis, 1950)", "Winnie-the-Pooh (AA Milne y EH Shepard, 1926)",
			"La telaraña de Charlotte (EB White y Garth Williams, 1952)",
			"Matilda (Roald Dahl y Quentin Blake, 1988)" };

	public Carta(String nombreNino, ReyMago reyMago, List<Juguete> juguetes) {
		this.nombreNino = nombreNino;
		this.reyMago = reyMago;

		this.juguetes = new ArrayList<>(
				Arrays.asList(new Juguete("Pelota futbol", "Deportivo"), new Juguete("Acuarelas", "Educativo")));

		int cantidadVideojuegos = 0;
		for (Juguete juguete : juguetes) {
			if (juguete.getCategoria().equals("videojuegos")) {
				if (cantidadVideojuegos >= libros.length)
					cantidadVideojuegos = 0;

				String libro = libros[cantidadVideojuegos];
				this.juguetes.add(new Juguete(libro, "Educativo"));
				cantidadVideojuegos++;
			} else {
				this.juguetes.add(juguete);
			}
		}
	}

	public String getNombreNino() {
		return nombreNino;
	}

	public void setNombreNino(String nombreNino) {
		this.nombreNino = nombreNino;
	}

	public ReyMago getReyMago() {
		return reyMago;
	}

	public void setReyMago(ReyMago reyMago) {
		this.reyMago = reyMago;
	}

	public List<Juguete> getJuguetes() {
		return juguetes;
	}

	public void setJuguetes(ArrayList<Juguete> juguetes) {
		this.juguetes = juguetes;
	}

	public static boolean comprobarNinoRepetido(List<Carta> cartas, String nomberNino) {
		for (Carta carta : cartas) {
			if (carta.getNombreNino().equals(nomberNino)) {
				return true;
			}
		}
		return false;
	}

	public static List<Carta> cartasExample() {
		return Arrays.asList(
				new Carta("Oliver", new ReyMago("Melchor"),
						Arrays.asList(new Juguete("Play 5", "Videojuegos"), new Juguete("Vaqueros", "Ocio"),
								new Juguete("Libro Pintura", "Educativo"), new Juguete("Pelota", "Deportivo"))),
				new Carta("Rodrigo", new ReyMago("Gaspar"),
						Arrays.asList(new Juguete("Cosa", "Ocio"), new Juguete("Play 4", "Videojuegos")))

		);
	}

	public Map<String, Long> getCategoryStatistics() {
		return this.juguetes.stream().collect(Collectors.groupingBy(Juguete::getCategoria, Collectors.counting()));
	}

	public static Map<String, Long> totalEstadisticaCategoria(List<Carta> cartas) {
		Map<String, Long> estadisticasTotales = new HashMap<>();
		for (Carta carta : cartas) {
			Map<String, Long> estadisticas = carta.getCategoryStatistics();
			for (Map.Entry<String, Long> entrada : estadisticas.entrySet()) {
				estadisticasTotales.merge(entrada.getKey(), entrada.getValue(), Long::sum);
			}
		}

		return estadisticasTotales;
	}
}
