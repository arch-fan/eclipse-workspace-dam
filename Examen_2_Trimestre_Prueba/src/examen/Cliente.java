package examen;

public class Cliente {
	private static int cont = 1;

	private int id;
	private String nombreCompleto;
	private String dni;
	private int codigo;

	public Cliente(String nombreCompleto, String dni, int codigo) {
		this.id = cont;
		this.nombreCompleto = nombreCompleto;
		this.dni = dni;
		this.codigo = codigo;

		System.out.println("Se ha creado el cliente número " + cont + " con DNI " + dni + ", el código secreto de "
				+ nombreCompleto + " es " + codigo);

		cont++;
	}

	public int getId() {
		return id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public String getDni() {
		return dni;
	}

	public int getCodigo() {
		return codigo;
	}
}
