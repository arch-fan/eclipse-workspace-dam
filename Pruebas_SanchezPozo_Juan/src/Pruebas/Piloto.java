package Pruebas;

import java.util.ArrayList;

public class Piloto {
	private String nombre;
	private String dni;
	private String licencia;
	private int edad;
	private String fechaLicencia;
	private ArrayList<Avion> tiposAviones;
	
	public Piloto(String nombre, Integer dni, String licencia, int edad, String fechaLicencia,
			ArrayList<Avion> tiposAviones) {
		this.nombre = nombre;
		this.dni = dni.toString() + getDniLetter(dni);
		this.licencia = licencia;
		this.edad = edad;
		this.fechaLicencia = fechaLicencia;
		this.tiposAviones = tiposAviones;
	}
	
	private static char getDniLetter(int nDni) {
	    String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
	    return letters.charAt(nDni % 23);
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getLicencia() {
		return licencia;
	}

	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getFechaLicencia() {
		return fechaLicencia;
	}

	public void setFechaLicencia(String fechaLicencia) {
		this.fechaLicencia = fechaLicencia;
	}

	public ArrayList<Avion> getTiposAviones() {
		return tiposAviones;
	}

	public void setTiposAviones(ArrayList<Avion> tiposAviones) {
		this.tiposAviones = tiposAviones;
	}
	
}
