package Model;

import java.time.LocalDateTime;

public class Pago {
    private String placaVehiculo;
    private double monto;
    private LocalDateTime fecha;
    private String tipoPago;

    public Pago(String placaVehiculo, double monto, LocalDateTime fecha, String tipoPago) {
        this.placaVehiculo = placaVehiculo;
        this.monto = monto;
        this.fecha = fecha;
        this.tipoPago = tipoPago;
    }

    public String getPlacaVehiculo() { return placaVehiculo; }
    public double getMonto() { return monto; }
    public LocalDateTime getFecha() { return fecha; }
    public String getTipoPago() { return tipoPago; }

    @Override
    public String toString() {
        return "Pago - Placa: " + placaVehiculo + ", Monto: $" + monto + ", Fecha: " + fecha + ", Tipo: " + tipoPago;
    }
}
