package examen;

import java.util.Date;

public class Transaccion {
	private static int cont = 1;

	private int id;
	private double importe;
	private Cuenta destinatario;
	private String moneda;
	private Date date;
	private double saldoAnterior;
	private double saldoPosterior;

	private static double eurodolar = 1.08;

	public Transaccion(double importe, Cuenta destinatario, String moneda) {

		this.importe = importe;
		this.destinatario = destinatario;
		this.moneda = moneda;
		this.date = new Date();
		this.id = cont;

		this.saldoAnterior = destinatario.getDinero();
		if (moneda.equals("euro") && destinatario.getMoneda().equals(moneda)) {
			destinatario.agregarDinero(importe);
		} else if (moneda.equals("dolar") && destinatario.getMoneda().equals(moneda)) {
			destinatario.agregarDinero(importe);
		} else if (moneda.equals("euro") && destinatario.getMoneda().equals("dolar")) {
			destinatario.agregarDinero(importe * eurodolar);
		} else {
			destinatario.agregarDinero(importe / eurodolar);
		}

		this.saldoPosterior = destinatario.getDinero();

		System.out.println("Se ha creado la transacción " + this.date + " ID " + cont + " En la cuenta "
				+ destinatario.getId() + " Del cliente " + destinatario.getTitular().getNombreCompleto()
				+ " por un importe de " + importe);

		cont++;
	}

	public int getId() {
		return id;
	}

	public double getImporte() {
		return importe;
	}

	public Cuenta getDestinatario() {
		return destinatario;
	}

	public String getMoneda() {
		return moneda;
	}

	public Date getDate() {
		return date;
	}

	public double getSaldoAnterior() {
		return saldoAnterior;
	}

	public double getSaldoPosterior() {
		return saldoPosterior;
	}

	public static void setEurodolar(double valor) {
		eurodolar = valor;
	}
}
