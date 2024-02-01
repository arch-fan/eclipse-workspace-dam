package practica_poo02;

// Clase para guardar los desperfectos informaticos
public class Informatico extends Desperfecto {
	private int ano;

	public Informatico(String nombre, double precio, int cantidad, int ano) {
		super(nombre, precio, cantidad, calcularSeguro(precio, ano));
		this.ano = ano;
	}

	public int getAno() {
		return ano;
	}

	// Calculamos lo que nos devuelve el seguro
	private static double calcularSeguro(double precio, int ano) {
		return precio * (0.15 * ano);
	}
}
