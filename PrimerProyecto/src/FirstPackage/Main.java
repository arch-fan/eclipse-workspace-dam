package FirstPackage;

import java.util.Scanner;

public class Main {
	
	public static void print(Object content) {
		System.out.println(content);
	}
	
	public static void main(String[] args) {
		System.out.println("Hello world");
		System.out.println("Probando Java");
	
		Scanner scanner = new Scanner(System.in);
		
		String texto = scanner.nextLine();
		System.out.println("Tu texto es: " + texto);
		
		scanner.close();
	}	
}