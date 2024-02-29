package examen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		List<Cliente> clientes = new ArrayList<>();
		List<Cuenta> cuentas = new ArrayList<>();
		List<Transaccion> transacciones = new ArrayList<>();

		boolean salir = false;
		
		while (!salir) {
			System.out.println("Menu: ");
			switch (sc.nextLine()) {
			case "0": {
				datosEjemplo(clientes, cuentas, transacciones);
				break;
			}

			case "1": {
				mostrarLaData(cuentas, transacciones);
				break;
			}

			case "2": {
				System.out.println("Introduce Numero de Cuenta");
				String nCuenta = sc.nextLine();

				Cuenta destinatario = null;

				for (Cuenta cuenta : cuentas) {
					if (cuenta.getNumeroCuenta().equals(nCuenta)) {
						destinatario = cuenta;
						break;
					}
				}

				System.out.println("Introduce codigo secreto");
				int codigo = sc.nextInt();
				sc.nextLine();

				if (codigo != destinatario.getTitular().getCodigo()) {
					break;
				}

				System.out.println("Introduce importe");
				int importe = sc.nextInt();
				sc.nextLine();

				System.out.println("Introduce moneda");
				String moneda = sc.nextLine();

				transacciones.add(new Transaccion(importe, destinatario, moneda));

				break;
			}
			case "3": {
				for (Transaccion transaccion : transacciones) {
					if (transaccion.getImporte() > 5000) {
						transaccion.getDestinatario().setEstado("Suspendida por blanqueo");
					}
				}

				mostrarLaData(cuentas, transacciones);
			}

			case "4": {
				Transaccion.setEurodolar(sc.nextDouble());
				sc.nextLine();
			}
			
			case "5": {
				sc.close();
				salir = true;
				break;
			}
			}
		}

	}

	public static void datosEjemplo(List<Cliente> clientes, List<Cuenta> cuentas, List<Transaccion> transacciones) {
		clientes.addAll(Arrays.asList(new Cliente("Paco Juan", "112233445A", 9001),
				new Cliente("Jose Luis", "199929333B", 2002), new Cliente("Francisco Nadador", "934321434L", 1111),
				new Cliente("Jose Antonio Cabron", "010383848M", 1234)));

		cuentas.addAll(Arrays.asList(new Cuenta("0329UJ0CEW98", clientes.get(0), "euro"),
				new Cuenta("ASDPOI20WSD9", clientes.get(1), "dolar"),
				new Cuenta("ZXCPIMO32222", clientes.get(2), "euro"),
				new Cuenta("ZXCOPIKM3166", clientes.get(3), "dolar")));

		transacciones.addAll(Arrays.asList(new Transaccion(1000, cuentas.get(0), "euro"),
				new Transaccion(2931, cuentas.get(1), "dolar"), new Transaccion(8000, cuentas.get(1), "euro"),
				new Transaccion(1948, cuentas.get(2), "euro"), new Transaccion(6222, cuentas.get(2), "euro"),
				new Transaccion(9999, cuentas.get(3), "dolar")));
	}

	public static void mostrarLaData(List<Cuenta> cuentas, List<Transaccion> transacciones) {
		for (Cuenta cuenta : cuentas) {
			System.out.println("Cuenta " + cuenta.getNumeroCuenta() + ": ");
			System.out.println("	Estado: " + cuenta.getEstado());
			System.out.println("	Saldo actual: " + cuenta.getDinero());

			for (Transaccion transaccion : transacciones) {
				if (transaccion.getDestinatario() == cuenta) {
					System.out.println("Se realiza la transacción " + transaccion.getId() + " el día "
							+ transaccion.getDate() + " con importe " + transaccion.getImporte()
							+ " dejando un saldo disponible de " + transaccion.getSaldoPosterior());
				}
			}

		}
	}

}
