package reyesPOO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Carta {
	private String nombreNino;
	private ReyMago reyMago;
	private List<Juguete> juguetes;

	public Carta(String nombreNino, ReyMago reyMago, List<Juguete> juguetes) {
		this.nombreNino = nombreNino;
		this.reyMago = reyMago;
		this.juguetes = juguetes;
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

	public static List<Carta> cartasExample() {
		return Arrays.asList(
				new Carta("Oliver", new ReyMago("Melchor"),
						Arrays.asList(new Juguete("Play 5", "Videojuegos"), new Juguete("Vaqueros", "Ocio"),
								new Juguete("Libro Pintura", "Educativo"), new Juguete("Pelota", "Deportivo"))),
				new Carta("Rodrigo", new ReyMago("Gaspar"),
						Arrays.asList(new Juguete("Cosa", "Ocio"), new Juguete("Play 4", "Videojuegos")))

		);
	}

}
