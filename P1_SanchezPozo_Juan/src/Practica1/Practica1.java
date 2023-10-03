package Practica1;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Arrays;

public class Practica1 {

    // Método print para facilitar la verbosidad de System.out.print
    private static void print(String message) {
        System.out.print(message);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        print("¡Bienvenido al ejercicio! Elije una de las 4 funcionalidades.");

        // Bucle principal del programa
        while (true) {

            print("""


                        1) Verifica si un número es par
                        2) Escribe un numero del 1-7 o un dia de la semana para recibir el número o el día.
                        3) Da una calificación alfabética y recibe el equivalente numérico
                        4) Convierte tu salario bruto a neto
                        5) Salir del ejercicio

                    Elije tu opción: """);

            // Comprobamos que el usuario ha introducido un número
            if (!sc.hasNextByte()) {
                print("Elige una opción válida!");
                sc.next();
                continue;
            }

            // Almacenamos la opción elegida por el usuario
            byte siguienteOpcion = sc.nextByte();

            /*
             * Ejecutamos el ejercicio dependiendo la opcion elejida. Además, comrobamos que
             * no haya fallo a la hora de pasar el tipo de dato ya que el método solamente
             * se encarga de ejecutar el ejercicio.
             */
            switch (siguienteOpcion) {
            case 1:
                print("\nIntroduce el número: ");
                if (!sc.hasNextInt()) {
                    print("\nIntroduce un número válido!");
                    sc.next();
                    continue;
                }
                esPar(sc.nextInt());
                break;
            case 2:

                print("""

                        Debes indicar un número si quieres el día de la semana, o viceversa.
                        Por ejemplo, si pones 1, recibes lunes, y si pones viernes, recibes 5.

                        Introduce el número o el dia de la semana: """);

                try {
                    byte numeroDia = sc.nextByte();
                    diaDeLaSemana(numeroDia);
                } catch (InputMismatchException error) {
                    diaDeLaSemana(sc.nextLine());
                }
                break;
            case 3:

                print("""

                        Debes introducir una nota y se te devolverá un caracter.
                        Por ejemplo, A devuelve 15, la nota más alta. Puedes introducir (A, B, C, D o E)

                        Introduce la nota en caracter: """);

                String charInput = sc.next();
                if (charInput.length() > 1) {
                    print("Introduce una letra (calificación) válida!");
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
                    print("Introduce un valor válido!");
                    sc.next();
                    continue;
                }
                calcularFactura(sc.nextDouble());
                break;
            case 5:
                sc.close();
                System.exit(0);
                break;
            // Si ninguna de las 5 opciones ha sido elegida, le volvemos a mostrar el
            // ejercicio
            default:
                print("Introduce una opcion válida!");
                break;
            }

        }

    }

    // A partir de aquí se declaran los métodos para ejecutar cada ejercicio.

    /*
     * Los ejercicios siguen la regla de no devolver el valor, ya que ejecutan el
     * ejercicio entero. Solamente hay que asegurarse en el bucle principal reciba
     * el tipo de dato correcto.
     */

    // Ejercicio 1: Mostrar si el número recibido es par o impar.
    static void esPar(int number) {
        // Utilizando el operador % conseguimos el resto de la division de los 2
        // operandos.
        if (number % 2 == 0) {
            print("\nEl numero " + number + " es par");
        } else {
            print("\nEl numero " + number + " es impar");
        }
    }

    // Ejercicio 2: Mostrar día de la semana mediante número, o viceversa.
    // Utilizamos un genérico para aceptar numeros y string
    static <T> void diaDeLaSemana(T dia) {
        String[] nombresDias = { "lunes", "martes", "miércoles", "jueves", "viernes", "sábado", "domingo" };

        // Comprobamos el tipo de dato que es, para mostrar una u otra salida.
        if (dia instanceof String) {
            // Recuperamos el día en minúscula
            String diaString = ((String) dia).toLowerCase();
            // Recuperamos el índice correspondiente al día en el array y mostramos si hay
            // indice. Si no hay, no.
            int indice = Arrays.asList(nombresDias).indexOf(diaString);
            if (indice != -1) {
                print("\nEl " + diaString + " corresponde al día " + (indice + 1));
            } else {
                print("\nDía no reconocido");
            }
        } else {
            int diaInt = ((Number) dia).intValue();
            if (diaInt >= 1 && diaInt <= 7) {
                print("\nEl día número " + diaInt + " corresponde al " + nombresDias[diaInt - 1]);
            } else {
                print("\nValor no válido para el día de la semana.");
            }
        }
    }

    // Método para no repetir código en el ejercicio 3 a la hora de imprimir la
    // calificación
    private static void imprimirCalificacion(int valor) {
        print("La calificación es " + valor);
    }

    // Ejercicio 3: Imprimir la calificación mediante el valor alfabético.
    static void calificacionPorLetra(char letraCalificacion) {
        char[] calificaciones = { 'e', 'd', 'c', 'b', 'a' };

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
    static void calcularFactura(double importeBruto) {
        double importeNeto = importeBruto * 1.21;
        print(String.format("El importe neto es %.2f", importeNeto));
    }
}
