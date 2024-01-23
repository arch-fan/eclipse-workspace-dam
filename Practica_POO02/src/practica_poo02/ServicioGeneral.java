/**
 * 
 */
package practica_poo02;

/**
 * 
 */
public class ServicioGeneral extends Desperfecto {

	/**
	 * @param nombre
	 * @param precio
	 * @param cantidad
	 */
	public ServicioGeneral(String nombre, double precio, int cantidad) {
		super(nombre, precio, cantidad, calcularSeguro(precio));
		
	}
	
	private static double calcularSeguro(double precio) {
		return precio - (precio * 0.7) + 20;
	}

}
