package Practica3;

import java.util.ArrayList;
import java.util.Scanner;

public class Practica {

    private static ArrayList<Paciente> listaPacientes = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("¿Cuantos pacientes vas a introducir?: ");
        int nPacientes = sc.nextInt();

        for (int i = 1; i <= nPacientes; i++) {
            System.out.println("Introduce el nombre del paciente " + i + ": ");
            sc.nextLine();
            String nombre = sc.nextLine();

            System.out.println("Introduce el peso del paciente (en kg) " + i + ": ");
            double peso = sc.nextDouble();

            System.out.println("Introduce la altura del paciente (en metros) " + i + ": ");
            double altura = sc.nextDouble();

            listaPacientes.add(new Paciente(nombre, peso, altura));

        }

        sc.close();
    }

    private static Paciente getPacienteMasBajo(ArrayList<Paciente> listaPaciente) {
        Paciente pacienteMasBajo = listaPaciente.get(0); // Asumimos que el primer paciente es el más bajo inicialmente

        for (Paciente paciente : listaPaciente) {
            if (paciente.getAltura() < pacienteMasBajo.getAltura()) {
                pacienteMasBajo = paciente; // Encontramos un paciente más bajo
            }
        }

        return pacienteMasBajo;
    }

}
