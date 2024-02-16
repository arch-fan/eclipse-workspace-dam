package practica_POO03;

import java.util.Arrays;
import java.util.List;

public class Material {
	private final String nombre;
	private final String categoria;
	private final String modelo;
	private final double precio;
	
	private static final List<String> categorias = Arrays.asList("portatil", "telefono", "coche");
	
	public Material(String categoria, int id, String modelo, double precio) {
		
		if(!comprobarCategoria(categoria)) {
			throw new IllegalArgumentException("Categoria indicada no valida.");
		}
		
		this.nombre = categoria + " " + id;
		this.categoria = categoria.toLowerCase();
		this.modelo = modelo;
		this.precio = precio;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getModelo() {
		return modelo;
	}

	public double getPrecio() {
		return precio;
	}

	public String getNombre() {
		return nombre;
	}
	
	public static List<String> getCategorias() {
		return categorias;
	}

	// Metodo para comprobar si la categoria enviada al metodo es valida
	public static boolean comprobarCategoria(String categoria) {
		return categorias.contains(categoria.toLowerCase());
	}
}
