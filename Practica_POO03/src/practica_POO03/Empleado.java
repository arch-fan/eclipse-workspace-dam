package practica_POO03;

public class Empleado {
	private String nombre;
    private String apellidos;
    private Integer edad; // Opcional
    private String direccion; // Opcional
    private double sueldoBruto;
    private Double irpf; // Opcional
    private Double bonus; // Opcional
    private String categoria; // Opcional
    private Double sueldoNeto; // Se calcula, opcional en constructor
    private int antiguedad;
    private Integer numeroDeHijos; // Opcional
    private Integer vacaciones; // Opcional

    private Empleado(Builder builder) {
        this.nombre = builder.nombre;
        this.apellidos = builder.apellidos;
        this.edad = builder.edad;
        this.direccion = builder.direccion;
        this.sueldoBruto = builder.sueldoBruto;
        this.irpf = builder.irpf;
        this.bonus = builder.bonus;
        this.categoria = builder.categoria;
        this.sueldoNeto = calcularSueldoNeto(builder.sueldoBruto, builder.irpf, builder.bonus);
        this.antiguedad = builder.antiguedad;
        this.numeroDeHijos = builder.numeroDeHijos;
        this.vacaciones = builder.vacaciones;
    }

    public static class Builder {
        private String nombre;
        private String apellidos;
        private Integer edad;
        private String direccion;
        private double sueldoBruto;
        private Double irpf;
        private Double bonus;
        private String categoria;
        private Double sueldoNeto;
        private int antiguedad;
        private Integer numeroDeHijos;
        private Integer vacaciones;

        public Builder(String nombre, String apellidos, double sueldoBruto, int antiguedad) {
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.sueldoBruto = sueldoBruto;
            this.antiguedad = antiguedad;
        }

        public Builder edad(int edad) {
            this.edad = edad;
            return this;
        }

        public Empleado build() {
            return new Empleado(this);
        }
    }
    
    public static double calcularSueldoNeto(double sueldoBruto, Double irpf, Double bonus) {
        double irpfCalculado = (irpf != null) ? (sueldoBruto * irpf / 100) : 0;
        double bonusCalculado = (bonus != null) ? (sueldoBruto * bonus / 100) : 0;
        return sueldoBruto - irpfCalculado + bonusCalculado;
    }

}

