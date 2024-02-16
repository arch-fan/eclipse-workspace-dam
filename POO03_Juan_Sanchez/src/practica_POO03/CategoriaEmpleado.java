package practica_POO03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Clase para guardar cada categoria, con su bonificacion
public class CategoriaEmpleado {
	private final String nombre;
	private final double bonificacion;

	// Lista de categorias disponibles
	private static final List<String> categorias = Arrays.asList("junior", "senior", "manager", "arquitecto");
	// Map en el que guardamos la categoria y su correspondiente bonificacion
	private static final Map<String, Double> bonificaciones = new HashMap<>();
	// Map en el que guardamos la categoria y su stock disponible de material
	private static final Map<String, Integer> stockPorCategoria = new HashMap<>();
	
	static {
		bonificaciones.put(categorias.get(0), 0.05);
		bonificaciones.put(categorias.get(1), 0.1);
		bonificaciones.put(categorias.get(2), 0.12);
		bonificaciones.put(categorias.get(3), 0.25);
		
		stockPorCategoria.put(categorias.get(0), 5);
		stockPorCategoria.put(categorias.get(1), 5);
		stockPorCategoria.put(categorias.get(2), 5);
		stockPorCategoria.put(categorias.get(3), 5);
	}

	public CategoriaEmpleado(String nombre) throws IllegalArgumentException {
		if(!comprobarCategoria(nombre)) {
			throw new IllegalArgumentException("Categoria indicada no valida.");
		}
		
		this.nombre = nombre;
		this.bonificacion = bonificaciones.get(nombre.toLowerCase());
	}

	public String getNombre() {
		return nombre;
	}

	public double getBonificacion() {
		return bonificacion;
	}
	
	public static List<String> getCategorias() {
		return categorias;
	}
	
	// Metodo para restar stock a una categoria especificada
	public static void restartStockPorCategoria(String categoria) throws IllegalArgumentException {
		if(comprobarCategoria(categoria)) {
			stockPorCategoria.merge(categoria, -1, Integer::sum);
		} else {
			throw new IllegalArgumentException("Categoria indicada no valida.");
		}
	}
	
	public static boolean comprobarCategoria(String categoria) {
		return categorias.contains(categoria.toLowerCase());
	}


	public static Map<String, Integer> getStockPorCategoria() {
		return stockPorCategoria;
	}

	// Generamos un kit de materiales en base a la categoria indicada
	public static List<Material> generateKit(String categoria) {
		// recuperamos el stock disponible de la categoria indicada
		int currentStock = stockPorCategoria.get(categoria);
		
		// Dependiendo de la categoria, le damos un kit u otro, y restamos uno al stock de la categoria correspondiente
		if(categoria.equals(categorias.get(0)) && currentStock > 0) {
			List<Material> kit = Arrays.asList(
					new Material("Portatil", genIdByCategory(categoria), "Dell", 500)
				);
			
			restartStockPorCategoria(categoria);
			
			return kit;
		} else if(categoria.equals(categorias.get(1)) && currentStock > 0) {
			List<Material> kit = Arrays.asList(
					new Material("Portatil", genIdByCategory(categoria), "HP", 800)
				);
			
			restartStockPorCategoria(categoria);
			
			return kit;
		} else if(categoria.equals(categorias.get(2)) && currentStock > 0) {
			List<Material> kit = Arrays.asList(
					new Material("Portatil", genIdByCategory(categoria), "Asus", 1000),
					new Material("Telefono", genIdByCategory(categoria), "Samsung S10", 300),
					new Material("Coche", genIdByCategory(categoria), "Seat Leon", 500)
				);
			
			restartStockPorCategoria(categoria);
			
			return kit;
		} else if (categoria.equals(categorias.get(3)) && currentStock > 0) {
			List<Material> kit = Arrays.asList(
					new Material("Portatil", genIdByCategory(categoria), "MacBook", 2000),
					new Material("Telefono", genIdByCategory(categoria), "Aifon 16 Ultra", 1000),
					new Material("Coche", genIdByCategory(categoria), "Ferrari La Ferrari", 1000)
				);
			
			restartStockPorCategoria(categoria);
			
			return kit;
		} else {
			// Si no queda stock, devuelve un array vacio
			return new ArrayList<Material>(0);
		}
	}
	
	// Metodo para generar un id aleatorio en base al stock disponible
	private static int genIdByCategory(String categoria) {
		int base = stockPorCategoria.get(categoria);
		
		return Math.abs(base - stockPorCategoria.size());
	}
}
