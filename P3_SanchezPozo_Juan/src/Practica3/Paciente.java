package Practica3;

public class Paciente {
    private String nombre;
    private double peso;
    private double altura;

    public Paciente(String nombre, double peso, double altura) {
        this.nombre = nombre;
        this.peso = peso;
        this.altura = altura;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPeso() {
        return peso;
    }

    public double getAltura() {
        return altura;
    }

    public double calcularIMC() {
        return peso / Math.pow(altura, 2);
    }
}

