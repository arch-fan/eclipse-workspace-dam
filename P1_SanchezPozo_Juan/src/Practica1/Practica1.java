package Practica1;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Arrays;

public class Practica1 {

    // Metodo print para facilitar la verbosidad de System.out.print
    private static void print(String message) {
        System.out.print(message);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        print("¡Bienvenido al ejercicio! Elije una de las 4 funcionalidades.");

        // Bucle principal del programa
        while (true) {

            print("""


                        1) Verifica si un numero es par
                        2) Escribe un numero del 1-7 o un dia de la semana para recibir el numero o el dia.
                        3) Da una calificacion alfabetica y recibe el equivalente numerico
                        4) Convierte tu factura en bruto a neto
                        5) Salir del ejercicio

                    Elije tu opcion: """);

            // Comprobamos que el usuario ha introducido un numero
            if (!sc.hasNextByte()) {
                print("Elige una opcion valida!");
                sc.next();
                continue;
            }

            // Almacenamos la opcion elegida por el usuario
            byte siguienteOpcion = sc.nextByte();

            /*
             * Ejecutamos el ejercicio dependiendo la opcion elejida. Ademas, comrobamos que
             * no haya fallo a la hora de pasar el tipo de dato ya que el metodo solamente
             * se encarga de ejecutar el ejercicio.
             */
            switch (siguienteOpcion) {
            case 1:
                print("\nIntroduce el numero: ");
                if (!sc.hasNextInt()) {
                    print("\nIntroduce un numero valido!");
                    /*
                     * En matematicas, un numero par es un numero entero que es divisible entre 2
                     * https://es.wikipedia.org/wiki/N%C3%BAmeros_pares_e_impares
                     */
                    print("\nRecuerda que los numeros pares e impares solamente pueden ser numeros enteros");
                    sc.next();
                    continue;
                }
                esPar(sc.nextInt());
                break;
            case 2:

                print("""

                        Debes indicar un numero si quieres el dia de la semana, o viceversa.
                        Por ejemplo, si pones 1, recibes lunes, y si pones viernes, recibes 5.

                        Introduce el numero o el dia de la semana: """);

                try {
                    byte numeroDia = sc.nextByte();
                    diaDeLaSemana(numeroDia);
                } catch (InputMismatchException error) {
                    diaDeLaSemana(sc.nextLine());
                }
                break;
            case 3:

                print("""

                        Debes introducir una nota y se te devolvera un caracter.
                        Por ejemplo, E devuelve 15, la nota mas alta. Puedes introducir (A, B, C, D o E)

                        Introduce la nota en caracter: """);

                String charInput = sc.next();
                if (charInput.length() > 1) {
                    print("Introduce una letra (calificacion) valida!");
                    continue;
                }
                calificacionPorLetra(charInput.toLowerCase().charAt(0));
                break;
            case 4:
                print("""

                        Al introducir la factura, ten en cuenta que si tienes que poner decimales debes ponerlos con coma.
                        Por ejemplo: 1399,95

                        Introduce la factura en bruto: """);

                if (!sc.hasNextDouble()) {
                    print("Introduce un valor valido!");
                    sc.next();
                    continue;
                }
                calcularFactura(sc.nextDouble());
                break;
            case 5:
                sc.close();
                System.exit(0);
                break;
            // Si ninguna de las 5 opciones ha sido elegida, le volvemos a mostrar el ejercicio
            default:
                print("Introduce una opcion valida!");
                break;
            }

        }

    }

    // A partir de aqui se declaran los metodos para ejecutar cada ejercicio.

    /*
     * Los ejercicios siguen la regla de no devolver el valor, ya que ejecutan el
     * ejercicio entero. Solamente hay que asegurarse de que en el bucle principal reciba
     * el tipo de dato correcto.
     */

    // Ejercicio 1: Mostrar si el numero recibido es par o impar.
    static void esPar(int number) {
        // Utilizando el operador % conseguimos el resto de la division de los 2
        // operandos.
        if (number % 2 == 0) {
            print("\nEl numero " + number + " es par");
        } else {
            print("\nEl numero " + number + " es impar");
        }
    }

    // Ejercicio 2: Mostrar dia de la semana mediante numero, o viceversa.
    // Utilizamos un generico para aceptar numeros y string
    static <T> void diaDeLaSemana(T dia) {
        String[] nombresDias = { "lunes", "martes", "miercoles", "jueves", "viernes", "sabado", "domingo" };

        // Comprobamos el tipo de dato que es, para mostrar una u otra salida.
        if (dia instanceof String) {
            // Recuperamos el dia en minuscula
            String diaString = ((String) dia).toLowerCase();
            // Recuperamos el indice correspondiente al dia en el array y mostramos si hay
            // indice. Si no hay, no.
            int indice = Arrays.asList(nombresDias).indexOf(diaString);
            if (indice != -1) {
                print("\nEl " + diaString + " corresponde al dia " + (indice + 1));
            } else {
                print("\nDia no reconocido");
            }
        } else {
            int diaInt = ((Number) dia).intValue();
            if (diaInt >= 1 && diaInt <= 7) {
                print("\nEl dia numero " + diaInt + " corresponde al " + nombresDias[diaInt - 1]);
            } else {
                print("\nValor no valido para el dia de la semana.");
            }
        }
    }

    // Metodo para no repetir codigo en el ejercicio 3 a la hora de imprimir la
    // calificacion
    private static void imprimirCalificacion(int valor) {
        print("La calificacion es " + valor);
    }

    // Ejercicio 3: Imprimir la calificacion mediante el valor alfabetico.
    static void calificacionPorLetra(char letraCalificacion) {
        char[] calificaciones = { 'a', 'b', 'c', 'd', 'e' };

        /*
         * Nos aprovechamos de que Java detecta los caracteres como carateres unicode,
         * por lo que si restamos 'a' - letra podemos recibir la diferencia que hay
         * entre cada uno, y solamente sumar 11 para recibir el valor.
         */

        int index = letraCalificacion - 'a';

        if (index >= 0 && index < calificaciones.length) {
            imprimirCalificacion(11 + index);
        } else {
            print("No has introducido la nota correcta.");
        }
    }

    // Ejercicio 4: Calcular la factura mediante el importe bruto, aplicando un 21%
    // de IVA
    private static final int IMPUESTO = 21; // Introducimos el valor en porcentaje
    static void calcularFactura(double importeBruto) {
        double importeNeto = importeBruto * ((double)IMPUESTO / 100 + 1);
        print(String.format("El importe neto es %.2f aplicando el %d%% de impuesto", importeNeto, IMPUESTO));
    }
}
