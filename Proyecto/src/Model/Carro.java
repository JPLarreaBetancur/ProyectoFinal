package Model;

public class Carro extends Vehiculo {
    public Carro (String placa, String color, String modelo) {
        super(placa, color, modelo);
    }

    @Override
    public String getTipo() {
        return "Automovil";
    }
}
