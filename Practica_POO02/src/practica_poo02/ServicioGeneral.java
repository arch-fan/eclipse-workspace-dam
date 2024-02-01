/**
 * 
 */
package practica_poo02;

// Clase para guardar los desperfectos de servicio general
public class ServicioGeneral extends Desperfecto {

	public ServicioGeneral(String nombre, double precio, int cantidad) {
		super(nombre, precio, cantidad, calcularSeguro(precio));
		
	}
	
	// Calculo de seguro de servicio general
	private static double calcularSeguro(double precio) {
		return precio * 0.7 + 20;
	}

}
