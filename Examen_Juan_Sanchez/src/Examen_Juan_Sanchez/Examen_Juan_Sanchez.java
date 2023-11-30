package Examen_Juan_Sanchez;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Examen_Juan_Sanchez {

	private static Scanner sc = new Scanner(System.in);
	private static final List<String> nombresMagos = Arrays.asList("melchor", "gaspar", "baltasar");
	private static final List<String> categoriasJuguetes = Arrays.asList("ocio tradicional", "videojuego", "educativo",
			"deportivo");
	private static final String[] listaLibros = new String[] { "Donde viven los monstruos (Maurice Sendak, 1963)",
			"Las aventuras de Alicia en el país de las maravillas (Lewis Carroll, 1865)",
			"Pippi Calzaslargas (Astrid Lindgren, 1945)", "El principito (Antoine de Saint-Exupéry, 1943)",
			"El hobbit (JRR Tolkien, 1937)", "Luces del norte (Philip Pullman, 1995)",
			"El león, la bruja y el armario (CS Lewis, 1950)", "Winnie-the-Pooh (AA Milne y EH Shepard, 1926)",
			"La telaraña de Charlotte (EB White y Garth Williams, 1952)",
			"Matilda (Roald Dahl y Quentin Blake, 1988)" };
	private static HashMap<String[], HashMap<String, String>> listaCartas = new HashMap<>();

	public static void main(String[] args) {
		System.out.println("Bienvenido al creador de cartas para los reyes magos!");

		boolean otroNino = true;
		while (otroNino) {
			try {
				cartaFactory();
//				String[] datosNino = apuntarNino();
			} catch (Error e) {
				System.out.println(e.getMessage());
				continue;
			}

			boolean verificarInput = true;

			while (verificarInput) {
				System.out.print("Quieres introducir otra carta? (s/n): ");
				String input = sc.nextLine().toLowerCase();
				if (input.equals("s")) {
					verificarInput = false;
					break;
				} else if (input.equals("n")) {
					otroNino = false;
					verificarInput = false;
				} else {
					System.out.println("Introduce s/n!");
				}
			}

		}
		
		mostrarEstadisticasCategorias();
		mostrarEstadisticasReyes();
		correrRegex();

	}

	// Metodo para crear cartas, utilizando diferentes metodos
	private static void cartaFactory() {
		String[] apunteNino = apuntarNino();
		HashMap<String, String> listaJuguetes = apuntarListaJuguetes();
		listaCartas.put(apunteNino, listaJuguetes);
		System.out.println("\nDatos introducidos:");
		System.out.println("Nombre: " + apunteNino[0]);
		System.out.println("Rey Mago: " + apunteNino[1]);
		System.out.println("Lista de juguetes: ");
		listaJuguetes.forEach((k, v) -> {
			System.out.println("Juguete: " + k + ", categoria: " + v);
		});
	}

	// Metodo para apuntar los datos del nino, lo que viene a ser su nombre y a quien va dirigida la carta
	// String[] pos 0: nombre nino; pos 1: nombre rey
	private static String[] apuntarNino() {
		System.out.print("Introduce tu nombre: ");
		String nombreNino = sc.nextLine();

		listaCartas.forEach((k, v) -> {
			if (k[0].equals(nombreNino))
				throw new Error("No puedes hacer dos cartas del mismo niño!");
		});

		String reyMago = "";

		while (true) {
			System.out.print("Introduce el nombre del rey mago: ");
			reyMago = sc.nextLine().toLowerCase();
			if (nombresMagos.contains(reyMago)) {
				break;
			} else {
				System.out.println("Introduce un rey correcto! (Melchor, Gaspar o Baltasar)");
			}
		}
		return new String[] { nombreNino, reyMago };
	}

	// Metodo para apuntar la lista de juguetes recogiendo el input del usuario
	// El hashmap es:     Juguete, categoria
	private static HashMap<String, String> apuntarListaJuguetes() {
		HashMap<String, String> listaJuguetes = new HashMap<>();
		listaJuguetes.put("Acuarelas", "educativo");
		listaJuguetes.put("Botas de Cristiano Ronaldo", "deportivo");

		boolean otroJuguete = true;
		while (otroJuguete) {
			System.out.print("Introduce un juguete: ");
			String juguete = sc.nextLine();
			String categoria = "";

			int numeroVideojuegos = 0;

			boolean categoriaCorrecta = false;
			while (!categoriaCorrecta) {
				System.out.println("Introduce su categoría (Ocio Tradicional, Videojuego, Educativo o Deportivo): ");
				categoria = sc.nextLine().toLowerCase();
				if (categoriasJuguetes.contains(categoria)) {
					if (categoria.equals("videojuego")) {
						juguete = listaLibros[numeroVideojuegos];
						categoria = "educativo";
						numeroVideojuegos++;
					}
					categoriaCorrecta = true;
				} else {
					System.out.println("Introduce una categoría correcta!");
				}
			}
			listaJuguetes.put(juguete, categoria);

			System.out.print("Quieres agregar otro juguete a la lista (s/n): ");
			boolean verificarInput = true;

			while (verificarInput) {
				String input = sc.nextLine().toLowerCase();
				if (input.equals("s")) {
					verificarInput = false;
				} else if (input.equals("n")) {
					otroJuguete = false;
					verificarInput = false;
				} else {
					System.out.println("Introduce s/n!");
				}
			}
		}
		return listaJuguetes;
	}

	// Metodo para mostrar las estadisticas de las categorias, en porcentaje
	private static void mostrarEstadisticasCategorias() {

		int ocio = 0;
		int videojuego = 0;
		int educativo = 0;
		int deportivo = 0;
		
		for(HashMap<String, String> listaJuguetes : listaCartas.values()) {
			for(String categoria : listaJuguetes.values()) {
				if(categoria.equals("ocio tradicional")) {
					ocio++;
				} else if (categoria.equals("videojuego")) {
					videojuego++;
				} else if(categoria.equals("educativo")) {
					educativo++;
				} else if (categoria.equals("deportivo")) {
					deportivo++;
				}

			}
		}
		
		int total = ocio + videojuego + educativo + deportivo;
		
		double mediaOcio = (double)((double)ocio/(double)total) * 100;
		double mediaVideojuego = (double)((double)videojuego/(double)total) * 100;
		double mediaEducativo = (double)((double)educativo/(double)total) * 100;
		double mediaDeportivo = (double)((double)deportivo/(double)total) * 100;
		
		System.out.println("Juguetes de ocio tradicional: " + mediaOcio + "%");
		System.out.println("Juguetes de videojuegos: " + mediaVideojuego + "%");
		System.out.println("Juguetes educativos: " + mediaEducativo + "%");
		System.out.println("Juguetes deportivos: " + mediaDeportivo + "%");

	}

	// Metodo para mostrar las estadisticas de los reyes, en porcentaje
	private static void mostrarEstadisticasReyes() {

		int melchor = 0;
		int gaspar = 0;
		int baltasar = 0;
		
		for(String[] ninoCarta : listaCartas.keySet()) {
				if(ninoCarta[1].equals("melchor")) {
					melchor++;
				} else if (ninoCarta[1].equals("gaspar")) {
					gaspar++;
				} else if(ninoCarta[1].equals("baltasar")) {
					baltasar++;
				}
		}
		
		int total = melchor + gaspar + baltasar;
		
		double mediaMelchor = (double)((double)melchor/(double)total) * 100;
		double mediaGaspar = (double)((double)gaspar/(double)total) * 100;
		double mediaBaltasar = (double)((double)baltasar/(double)total) * 100;
		
		System.out.println("Melchor ha sido elejido por el: " + mediaMelchor + "%");
		System.out.println("Gaspar ha sido elejido por el: " + mediaGaspar + "%");
		System.out.println("Baltasar ha sido elejido por el: " + mediaBaltasar + "%");

	}
	
	// Metodo para imprimir en pantalla la regex
	public static void correrRegex() {
		ArrayList<String> listaRegalos = new ArrayList<>();
		listaCartas.forEach((k, v) -> {
			v.forEach((r, j) -> {
				listaRegalos.add(r);
			});
		});
		
		int azCount = 0;
		int steamCount = 0;
		int jugueteCount = 0;
		
		
		for(String regalo : listaRegalos) {
			Pattern pAz = Pattern.compile("[^a-zA-Z]");
			Matcher mAz = pAz.matcher(regalo);
			
			if(mAz.find()) {
				azCount++;
			}
			
			Pattern pSteam = Pattern.compile("steam");
			Matcher mSteam = pSteam.matcher(regalo);
			
			if(mSteam.find()) {
				steamCount++;
			}
			
			Pattern pJuguete = Pattern.compile("steam");
			Matcher mJuguete = pJuguete.matcher(regalo);
			
			if(mJuguete.find()) {
				jugueteCount++;
			}
		}
		
		System.out.println("Hay " + azCount + " caracteres diferentes a a-z en las peticiones");
		System.out.println("Aparece " + steamCount + " veces la palabra steam en las peticiones");
		System.out.println("Aparece " + jugueteCount + " veces la palabra juguete en las peticiones");
	}
}
