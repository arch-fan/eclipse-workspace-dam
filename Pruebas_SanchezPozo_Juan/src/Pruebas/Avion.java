package Pruebas;

public class Avion {
	private String modelo;
	private String nSerie;

	public Avion(String modelo, String nSerie) {
		this.modelo = modelo;
		this.nSerie = nSerie;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getnSerie() {
		return nSerie;
	}

	public void setnSerie(String nSerie) {
		this.nSerie = nSerie;
	}
}
