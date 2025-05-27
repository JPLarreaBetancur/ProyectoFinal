package model;

import java.util.ArrayList;

public class Cliente {
    private String nombre;
    private String cedula;
    private String telefono;
    private String correo;
    private ArrayList<Vehiculo> vehiculos;

    public Cliente(String nombre, String cedula, String telefono, String correo) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.correo = correo;
        this.vehiculos = new ArrayList<>();
    }

    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }  // Añadido setter para cédula
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public ArrayList<Vehiculo> getVehiculos() { return vehiculos; }
    public void setVehiculos(ArrayList<Vehiculo> vehiculos) { this.vehiculos = vehiculos; }  // Nuevo setter

    
    public void agregarVehiculo(Vehiculo v) {
        if (buscarVehiculoPorPlaca(v.getPlaca()) == null) {
            vehiculos.add(v);
        } else {
            System.out.println("¡Vehículo con placa " + v.getPlaca() + " ya existe!");
        }
    }

    public Vehiculo buscarVehiculoPorPlaca(String placa) {
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }

    public boolean eliminarVehiculo(String placa) {  
        Vehiculo v = buscarVehiculoPorPlaca(placa);
        if (v != null) {
            vehiculos.remove(v);
            return true;
        }
        return false;
    }

    public boolean actualizarVehiculo(String placa, String nuevoColor, String nuevoModelo) {
        Vehiculo v = buscarVehiculoPorPlaca(placa);
        if (v != null) {
            v.setColor(nuevoColor);
            v.setModelo(nuevoModelo);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(nombre)
          .append(", Cédula: ").append(cedula)
          .append(", Tel: ").append(telefono)
          .append(", Correo: ").append(correo)
          .append("\nVehículos registrados:");
        
        if (vehiculos.isEmpty()) {
            sb.append(" Ninguno");
        } else {
            for (Vehiculo v : vehiculos) {
                sb.append("\n- ").append(v);
            }
        }
        return sb.toString();
    }
}
