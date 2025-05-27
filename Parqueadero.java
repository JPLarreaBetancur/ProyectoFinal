package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parqueadero {
    private String nombre;
    private String direccion;
    private String representante;
    private String telefono;
    private String correo;

    private int espaciosMotos;
    private int espaciosAutos;
    private int espaciosCamiones;

    private int ocupadosMotos = 0;
    private int ocupadosAutos = 0;
    private int ocupadosCamiones = 0;

    private HashMap<String, Double> tarifas;
    private ArrayList<Cliente> clientes;
    private ArrayList<Membresia> membresias;
    private ArrayList<IngresoTemporal> ingresosTemporales;
    private ArrayList<Pago> historialPagos;
    private List<Vehiculo> vehiculosTemporales = new ArrayList<>();

    public Parqueadero(String nombre, String direccion, String representante, String telefono, String correo) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.representante = representante;
        this.telefono = telefono;
        this.correo = correo;
        this.tarifas = new HashMap<>();
        this.clientes = new ArrayList<>();
        this.membresias = new ArrayList<>();
        this.ingresosTemporales = new ArrayList<>();
        this.historialPagos = new ArrayList<>();
    }

    public Vehiculo buscarVehiculoEnClientes(String placa) {
        for (Cliente cliente : clientes) {
            Vehiculo v = cliente.buscarVehiculoPorPlaca(placa);
            if (v != null) return v;
        }
        return null;
    }

    public boolean registrarVehiculoACliente(String cedulaCliente, Vehiculo vehiculo) {
        Cliente cliente = buscarClientePorCedula(cedulaCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            return false;
        }

        if (buscarVehiculoEnClientes(vehiculo.getPlaca()) != null) {
            System.out.println("Error: La placa " + vehiculo.getPlaca() + " ya está registrada");
            return false;
        }

        cliente.agregarVehiculo(vehiculo);
        return true;
    }

  
    public boolean crearMembresia(String placa, int meses) {
        Vehiculo vehiculo = buscarVehiculoEnClientes(placa);
        if (vehiculo == null) {
            System.out.println("Error: El vehículo no está registrado a ningún cliente");
            return false;
        }

        double tarifa = tarifas.getOrDefault(vehiculo.getTipo().toLowerCase(), 0.0);
        double monto = tarifa * meses * 30; 
        
        Membresia nueva = new Membresia(vehiculo, LocalDate.now(), meses, monto);
        membresias.add(nueva);
        ocuparEspacio(vehiculo.getTipo());
        
        System.out.println("✅ Membresía creada exitosamente");
        return true;
    }

  
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getRepresentante() { return representante; }
    public void setRepresentante(String representante) { this.representante = representante; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public int getEspaciosMotos() { return espaciosMotos; }
    public void setEspaciosMotos(int espaciosMotos) { this.espaciosMotos = espaciosMotos; }
    public int getEspaciosAutos() { return espaciosAutos; }
    public void setEspaciosAutos(int espaciosAutos) { this.espaciosAutos = espaciosAutos; }
    public int getEspaciosCamiones() { return espaciosCamiones; }
    public void setEspaciosCamiones(int espaciosCamiones) { this.espaciosCamiones = espaciosCamiones; }

    public int getOcupadosMotos() { return ocupadosMotos; }
    public int getOcupadosAutos() { return ocupadosAutos; }
    public int getOcupadosCamiones() { return ocupadosCamiones; }

    public ArrayList<Cliente> getClientes() { return clientes; }
    public ArrayList<Membresia> getMembresias() { return membresias; }
    public ArrayList<IngresoTemporal> getIngresosTemporales() { return ingresosTemporales; }
    public ArrayList<Pago> getHistorialPagos() { return historialPagos; }
    public List<Vehiculo> getVehiculosTemporales() { return vehiculosTemporales; }

    public void setTarifa(String tipoVehiculo, double valor) {
        tarifas.put(tipoVehiculo.toLowerCase(), valor);
    }

    public double getTarifa(String tipoVehiculo) {
        return tarifas.getOrDefault(tipoVehiculo.toLowerCase(), 0.0);
    }

    public void agregarCliente(Cliente c) {
        if (buscarClientePorCedula(c.getCedula()) == null) {
            clientes.add(c);
        } else {
            System.out.println("¡Cliente con cédula " + c.getCedula() + " ya existe!");
        }
    }

    public void eliminarCliente(String cedula) {
        clientes.removeIf(c -> c.getCedula().equals(cedula));
    }

    public Cliente buscarClientePorCedula(String cedula) {
        for (Cliente c : clientes) {
            if (c.getCedula().equals(cedula)) return c;
        }
        return null;
    }

    public void agregarMembresia(Membresia m) {
        membresias.add(m);
        ocuparEspacio(m.getVehiculo().getTipo());
    }

    public Membresia buscarMembresiaPorPlaca(String placa) {
        for (Membresia m : membresias) {
            if (m.getVehiculo().getPlaca().equalsIgnoreCase(placa)) {
                return m;
            }
        }
        return null;
    }

    public void agregarIngresoTemporal(IngresoTemporal ingreso) {
        ingresosTemporales.add(ingreso);
        ocuparEspacio(ingreso.getVehiculo().getTipo());
    }

    public void registrarPago(Pago pago) {
        historialPagos.add(pago);
    }

    public void ocuparEspacio(String tipo) {
        tipo = tipo.toLowerCase();
        switch (tipo) {
            case "moto": if (ocupadosMotos < espaciosMotos) ocupadosMotos++; break;
            case "automovil": if (ocupadosAutos < espaciosAutos) ocupadosAutos++; break;
            case "camion": if (ocupadosCamiones < espaciosCamiones) ocupadosCamiones++; break;
        }
    }

    public void liberarEspacio(String tipo) {
        tipo = tipo.toLowerCase();
        switch (tipo) {
            case "moto": if (ocupadosMotos > 0) ocupadosMotos--; break;
            case "automovil": if (ocupadosAutos > 0) ocupadosAutos--; break;
            case "camion": if (ocupadosCamiones > 0) ocupadosCamiones--; break;
        }
    }

    public boolean hayEspacioDisponible(String tipo) {
        tipo = tipo.toLowerCase();
        switch (tipo) {
            case "moto": return ocupadosMotos < espaciosMotos;
            case "automovil": return ocupadosAutos < espaciosAutos;
            case "camion": return ocupadosCamiones < espaciosCamiones;
        }
        return false;
    }

    public List<Vehiculo> obtenerVehiculosActuales() {
        List<Vehiculo> lista = new ArrayList<>();
        for (IngresoTemporal ingreso : ingresosTemporales) {
            if (ingreso.getFechaSalida() == null) {
                lista.add(ingreso.getVehiculo());
            }
        }
        for (Membresia m : membresias) {
            if (m.estaActiva()) {
                lista.add(m.getVehiculo());
            }
        }
        return lista;
    }

    public List<Cliente> obtenerClientesConMembresiaActiva() {
        List<Cliente> resultado = new ArrayList<>();
        for (Cliente c : clientes) {
            for (Vehiculo v : c.getVehiculos()) {
                Membresia m = buscarMembresiaPorPlaca(v.getPlaca());
                if (m != null && m.estaActiva() && !resultado.contains(c)) {
                    resultado.add(c);
                }
            }
        }
        return resultado;
    }

    public void agregarVehiculoTemporal(Vehiculo vehiculo) {
        vehiculosTemporales.add(vehiculo);
    }

    @Override
    public String toString() {
        return "Parqueadero: " + nombre + ", Dirección: " + direccion + ", Teléfono: " + telefono + ", Correo: " + correo;
    }
}