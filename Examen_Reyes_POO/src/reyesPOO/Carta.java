package reyesPOO;

import java.util.ArrayList;

public class Carta {
	private String nombreNino;
	private ReyMago reyMago;
	private ArrayList<Juguete> juguetes;

	public Carta(String nombreNino, ReyMago reyMago, ArrayList<Juguete> juguetes) {
		this.nombreNino = nombreNino;
		this.reyMago = reyMago;
		this.juguetes = juguetes;
	}

	public String getNombreNino() {
		return nombreNino;
	}

	public void setNombreNino(String nombreNino) {
		this.nombreNino = nombreNino;
	}

	public ReyMago getReyMago() {
		return reyMago;
	}

	public void setReyMago(ReyMago reyMago) {
		this.reyMago = reyMago;
	}

	public ArrayList<Juguete> getJuguetes() {
		return juguetes;
	}

	public void setJuguetes(ArrayList<Juguete> juguetes) {
		this.juguetes = juguetes;
	}

}
