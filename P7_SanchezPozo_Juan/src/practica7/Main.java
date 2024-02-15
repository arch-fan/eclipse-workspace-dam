package practica7;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("---- Bienvenido al generador de estadisticas ----");
		String nombreFichero;
		Fichero lector;
		Analizador analizar;
		
		while (true) {
			System.out.print("Introduce el nombre del fichero: ");
			nombreFichero = sc.nextLine().trim();
			
			if(!nombreFichero.endsWith(".txt")) {
				System.out.println("El fichero debe ser en formato txt!");
			}
			
			try {
				lector = new Fichero(nombreFichero);
				analizar = new Analizador(lector);
				
				break;
			} catch (IOException e) {
				System.out.println("El fichero introducido no existe");
			}
		}
		
		 
		
		System.out.println("\n---- Estadisticas ----");
		
		String textoEstadisticas = analizar.getEstadisticas().resultado();
		System.out.println(textoEstadisticas);
		
		try {
			String nombreNuevoFichero = "resultado-" + nombreFichero;
			
			Fichero.escribirFichero(nombreNuevoFichero, textoEstadisticas);
			
			System.out.println("El resultado ha sido guardado en " + nombreNuevoFichero);
		} catch (IOException e) {
			System.out.println("Hubo un error al escribir en el fichero: " + e.getMessage());
		}
		

	}

}
