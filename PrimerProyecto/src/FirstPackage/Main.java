package FirstPackage;

public class Main {
	
	public static void print(Object content) {
		System.out.println(content);
	}
	
	public static void main(String[] args) {
		System.out.println("Hello world");
		System.out.println("Probando Java");
		
		String cambio = "22";
		Integer numero = Integer.parseInt(cambio);
		
		print("hola");
		print(numero);
	}	
}