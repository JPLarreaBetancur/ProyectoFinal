package Model;

public class Moto extends Vehiculo {
    public Moto(String placa, String color, String modelo) {
        super(placa, color, modelo);
    }

    @Override
    public String getTipo() {
        return "Moto";
    }
}
