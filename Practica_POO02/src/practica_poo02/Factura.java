package practica_poo02;

import java.util.List;

public class Factura {

	// Datos de la factura.
	private List<Desperfecto> desperfectos;
	private double facturaGenerador;
	private double facturaElectricista;
	private double aDevolver;
	private double aPagar;

	public Factura(List<Desperfecto> desperfectos, double facturaGenerador, double facturaElectricista, int horasSinLuz) {
		this.desperfectos = desperfectos;
		this.facturaGenerador = facturaGenerador;
		this.facturaElectricista = facturaElectricista;

		// Calculo del total de los desperfectos
		double devolucionDesperfectos = desperfectos.stream().mapToDouble(Desperfecto::getValorCubiertoTotal).sum();
		
		// Calculos sin IVA de cada factura
		double devolucionesGenerador = facturaGenerador / 1.21;
		double devolucionElectricista = facturaElectricista / 1.21;

		// Suma de todo el dinero a devolver por el seguro
		this.aDevolver = devolucionDesperfectos + devolucionesGenerador + devolucionElectricista + (horasSinLuz * 30);

		// Suma de todo el dinero a pagar
		this.aPagar = desperfectos.stream().mapToDouble(Desperfecto::getPrecioTotal).sum() + facturaGenerador
				+ facturaElectricista;
	}

	public List<Desperfecto> getDesperfectos() {
		return desperfectos;
	}

	public double getFacturaGenerador() {
		return facturaGenerador;
	}

	public double getFacturaElectricista() {
		return facturaElectricista;
	}

	public double getaDevolver() {
		return aDevolver;
	}
	
	public double getaPagar() {
		return aPagar;
	}
}
