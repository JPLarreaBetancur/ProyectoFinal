package Model;

import java.time.LocalDateTime;

public class IngresoTemporal {
    private Vehiculo vehiculo;
    private LocalDateTime fechaIngreso;
    private LocalDateTime fechaSalida;
    private double montoPagado;

    public IngresoTemporal(Vehiculo vehiculo, LocalDateTime fechaIngreso) {
        this.vehiculo = vehiculo;
        this.fechaIngreso = fechaIngreso;
    }

    public Vehiculo getVehiculo() { return vehiculo; }
    public LocalDateTime getFechaIngreso() { return fechaIngreso; }
    public LocalDateTime getFechaSalida() { return fechaSalida; }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public double getMontoPagado() { return montoPagado; }
    public void setMontoPagado(double montoPagado) { this.montoPagado = montoPagado; }

    @Override
    public String toString() {
        return "IngresoTemporal - Vehículo: " + vehiculo.getPlaca() +
                ", Ingreso: " + fechaIngreso +
                ", Salida: " + (fechaSalida != null ? fechaSalida : "-") +
                ", Monto: $" + montoPagado;
    }
}
