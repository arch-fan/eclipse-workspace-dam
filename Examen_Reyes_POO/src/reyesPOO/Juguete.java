package reyesPOO;

import java.util.List;

public class Juguete {
	public static final List<String> categorias = List.of("ocio", "videojuegos", "educativo", "deportivo");
	private String nombre;
	private String categoria;

	public Juguete(String nombre, String categoria) {
		this.nombre = nombre;

		if (comprobarCategoria(categoria.toLowerCase())) {
			this.categoria = categoria.toLowerCase();
		} else {
			throw new IllegalArgumentException("Categoría no válida: " + categoria);
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public static boolean comprobarCategoria(String categoria) {
		if (categorias.contains(categoria)) {
			return true;
		}
		return false;
	}
}
