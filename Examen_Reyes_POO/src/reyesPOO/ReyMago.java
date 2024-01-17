package reyesPOO;

import java.util.List;

public class ReyMago {
	public static final List<String> nombresReyesMagos = List.of("melchor", "gaspar", "baltasar");
	private String name;

	public ReyMago(String name) {

		if (comprobarRey(name.toLowerCase())) {
			this.name = name.toLowerCase();
		} else {
			throw new IllegalArgumentException("Nombre del rey no válido: " + name);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static boolean comprobarRey(String nombre) {
		if (nombresReyesMagos.contains(nombre)) {
			return true;
		}
		return false;
	}

}
