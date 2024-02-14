package practica_POO03;

import java.util.List;

public class Kit {
	private final List<Material> materials;
	private int stock;
	
	public Kit(List<Material> materials, int stock) {
		super();
		this.materials = materials;
		this.stock = stock;
	}
	
	public void reduceStock() {
		this.stock -= 1;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}
