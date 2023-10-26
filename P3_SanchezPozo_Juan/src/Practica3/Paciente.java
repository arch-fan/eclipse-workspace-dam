package Practica3;

import java.util.ArrayList;

public class Paciente {
    private String nombre;
    private double peso;
    private double altura;
    private int dieta = 0;

    public Paciente(String nombre, double peso, double altura) {
        this.nombre = nombre;
        this.peso = peso;
        this.altura = altura;
    }

    public static ArrayList<Paciente> generarPacientesPrueba() {
        ArrayList<Paciente> pacientes = new ArrayList<>();

        pacientes.add(new Paciente("Paco", 70.5, 1.75));
        pacientes.add(new Paciente("María", 65.2, 1.68));
        pacientes.add(new Paciente("Carlos", 80.0, 1.80));
        pacientes.add(new Paciente("Laura", 55.7, 1.60));
        pacientes.add(new Paciente("Pedro", 90.3, 1.90));
        pacientes.add(new Paciente("Luisa", 75.1, 1.72));
        pacientes.add(new Paciente("Ana", 60.8, 1.65));
        pacientes.add(new Paciente("Miguel", 85.6, 1.78));
        pacientes.add(new Paciente("Sofía", 58.9, 1.63));
        pacientes.add(new Paciente("David", 77.4, 1.76));

        return pacientes;
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

    public int getDieta() {
        return dieta;
    }

    public void setDieta(int dieta) {
        this.dieta = dieta;
    }
}
