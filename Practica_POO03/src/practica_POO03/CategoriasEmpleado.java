package practica_POO03;

import java.util.Arrays;
import java.util.List;

public enum CategoriasEmpleado {
	JUNIOR(0.05, Arrays.asList(Portatil.TIPO1)),
    SENIOR(0.1, Arrays.asList(Portatil.TIPO2)),
    MANAGER(0.12, Arrays.asList(Portatil.TIPO3, Telefono.TIPO1, Coche.TIPO1)),
    ARQUITECTO(0.25, Arrays.asList(Portatil.TIPO4, Telefono.TIPO2, Coche.TIPO2));

	private final double bonificacion;
	private final List<Material> listaMateriales;

	CategoriasEmpleado(double bonificacion, List<Material> listaMateriales) {
        this.bonificacion = bonificacion;
        this.listaMateriales = listaMateriales;
    }

    public double getBonificacion() {
        return bonificacion;
    }
    
    public List<Material> getListaMateriales() {
    	return listaMateriales;
    }
}
