package Practica3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.List;

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
        Paciente pacienteMasBajo = listaPaciente.get(0);

        for (Paciente paciente : listaPaciente) {
            if (paciente.getAltura() < pacienteMasBajo.getAltura()) {
                pacienteMasBajo = paciente;
            }
        }

        return pacienteMasBajo;
    }

    private static Paciente getPacienteMasPesado(ArrayList<Paciente> listaPaciente) {
        Paciente pacienteMasPesado = listaPaciente.get(0);

        for (Paciente paciente : listaPaciente) {
            if (paciente.getPeso() > pacienteMasPesado.getPeso()) {
                pacienteMasPesado = paciente;
            }
        }

        return pacienteMasPesado;
    }

    private static Paciente getPacienteIMCMasAlto(ArrayList<Paciente> listaPaciente) {
        Paciente pacienteIMCMasAlto = listaPaciente.get(0);

        for (Paciente paciente : listaPaciente) {
            if (paciente.calcularIMC() > pacienteIMCMasAlto.calcularIMC()) {
                pacienteIMCMasAlto = paciente;
            }
        }

        return pacienteIMCMasAlto;
    }

    private static Paciente getPacienteIMCMasBajo(ArrayList<Paciente> listaPaciente) {
        Paciente pacienteIMCMasBajo = listaPaciente.get(0);

        for (Paciente paciente : listaPaciente) {
            if (paciente.calcularIMC() < pacienteIMCMasBajo.calcularIMC()) {
                pacienteIMCMasBajo = paciente;
            }
        }

        return pacienteIMCMasBajo;
    }

    private static double getAlturaMedia(ArrayList<Paciente> listaPaciente) {
        double alturasSumadas = 0;

        for (Paciente paciente : listaPaciente) {
            alturasSumadas += paciente.getAltura();
        }

        return alturasSumadas / listaPaciente.size();
    }

    private static double getPesoMedio(ArrayList<Paciente> listaPaciente) {
        double pesosSumados = 0;

        for (Paciente paciente : listaPaciente) {
            pesosSumados += paciente.getPeso();
        }

        return pesosSumados / listaPaciente.size();
    }

    private static double calcularMediana(ArrayList<Double> datos) {
        int longitudDatos = datos.size();
        double mediana;

        if (longitudDatos % 2 == 0) {
            Double valor1 = datos.get(longitudDatos / 2 - 1); // El primer valor en el medio
            Double valor2 = datos.get(longitudDatos / 2); // El segundo valor en el medio
            mediana = (valor1 + valor2) / 2;
        } else {
            mediana = datos.get(longitudDatos / 2); // Si es impar, solo hay un valor en el medio
        }

        return mediana;
    }

    private static double getMedianaAltura(ArrayList<Paciente> listaPaciente) {
        ArrayList<Double> alturaPacientes = new ArrayList<>();

        for (Paciente paciente : listaPaciente) {
            alturaPacientes.add(paciente.getAltura());
        }

        return calcularMediana(alturaPacientes);
    }

    private static double getMedianaPeso(ArrayList<Paciente> listaPaciente) {
        ArrayList<Double> pesoPacientes = new ArrayList<>();

        for (Paciente paciente : listaPaciente) {
            pesoPacientes.add(paciente.getAltura());
        }

        return calcularMediana(pesoPacientes);
    }

    public static Map<String, Integer> clasificarPacientes(ArrayList<Paciente> listaPaciente) {
        Map<String, Integer> clasificacion = new HashMap<>();
        int pesoBajo = 0;
        int pesoNormal = 0;
        int sobrepeso = 0;
        int obesidad = 0;
        
       for(Paciente paciente : listaPaciente) {
           double imc = paciente.calcularIMC();
           
           if(imc < 18.5) {
               pesoBajo++;
           } else if(imc < 25.0) {
               pesoNormal++;
           } else if(imc < 40.0) {
               sobrepeso++;
           } else {
               obesidad++;
           }
       }
       
       clasificacion.put("Peso Bajo", pesoBajo);
       clasificacion.put("Peso Normal", pesoNormal);
       clasificacion.put("Sobrepeso", sobrepeso);
       clasificacion.put("Obesidad", obesidad);
       
       return clasificacion;
        
    }
 
    public static Map<String, String> getListaDietas(ArrayList<Paciente> listaPaciente) {
        
        Map<Paciente, String> listaDietas = new HashMap<>();
        
        for(Paciente paciente : listaPacientes) {
            String dieta = "";
            double imc = paciente.calcularIMC();
            
            if(imc < 18.5) {
                pesoBajo++;
            } else if(imc < 25.0) {
                pesoNormal++;
            } else if(imc < 40.0) {
                sobrepeso++;
            } else {
                obesidad++;
            }
            
            
            
            listaDietas.put(paciente.getNombre(), dieta);
        }
    }

    
}
