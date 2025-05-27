package model;

import java.time.LocalDate;

public class Membresia {
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private int duracionMeses;
    private double monto;

    public Membresia(Vehiculo vehiculo, LocalDate fechaInicio, int duracionMeses, double monto) {
        this.vehiculo = vehiculo;
        this.fechaInicio = fechaInicio;
        this.duracionMeses = duracionMeses;
        this.monto = monto;
    }

    public Vehiculo getVehiculo() { return vehiculo; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public int getDuracionMeses() { return duracionMeses; }
    public double getMonto() { return monto; }

    public LocalDate getFechaFin() {
        return fechaInicio.plusMonths(duracionMeses);
    }

    public boolean estaActiva() {
        return LocalDate.now().isBefore(getFechaFin());
    }

    @Override
    public String toString() {
        return vehiculo + ", Inicio: " + fechaInicio + ", Fin: " + getFechaFin() + ", Monto: $" + monto;
    }
}