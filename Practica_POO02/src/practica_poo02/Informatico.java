package practica_poo02;

public class Informatico extends Desperfecto {
	private int ano;

	public Informatico(String nombre, double precio, int cantidad, int ano) {
		super(nombre, precio, cantidad, calcularSeguro(precio, ano));
		this.ano = ano;
	}

	public int getAno() {
		return ano;
	}

	private static double calcularSeguro(double precio, int ano) {
		return precio - precio * (0.15 * ano);
	}
}
