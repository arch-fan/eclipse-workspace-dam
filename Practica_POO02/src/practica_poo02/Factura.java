package practica_poo02;

import java.util.List;

public class Factura {

	private List<Desperfecto> desperfectos;
	private double facturaGenerador;
	private double facturaElectricista;
	private double aDevolver;
	private double aPagar;

	public Factura(List<Desperfecto> desperfectos, double facturaGenerador, double facturaElectricista) {
		this.desperfectos = desperfectos;
		this.facturaGenerador = facturaGenerador;
		this.facturaElectricista = facturaElectricista;

		double devolucionDesperfectos = desperfectos.stream().mapToDouble(Desperfecto::getValorCubierto).sum();
		double devolucionesGenerador = facturaGenerador / 1.21;
		double devolucionElectricista = facturaGenerador / 1.21;

		this.aDevolver = devolucionDesperfectos + devolucionesGenerador + devolucionElectricista;

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
