package service;

import model.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ParqueaderoService implements IParqueaderoService {
    private Parqueadero parqueadero;

    public ParqueaderoService(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }

    @Override
    public void agregarCliente(Cliente cliente) {
        parqueadero.agregarCliente(cliente);
        System.out.println("Cliente agregado correctamente.");
    }

    @Override
    public Cliente buscarClientePorCedula(String cedula) {
        return parqueadero.buscarClientePorCedula(cedula);
    }

    @Override
    public Cliente buscarClientePorNombre(String nombre) {
        for (Cliente c : parqueadero.getClientes()) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public Cliente buscarClientePorTelefono(String telefono) {
        for (Cliente c : parqueadero.getClientes()) {
            if (c.getTelefono().equals(telefono)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void eliminarCliente(String cedula) {
        parqueadero.eliminarCliente(cedula);
        System.out.println("Cliente eliminado (si existía).");
    }

    @Override
    public void mostrarClientes() {
        for (Cliente c : parqueadero.getClientes()) {
            System.out.println(c);
        }
    }

    @Override
    public void actualizarCliente(String cedula, String nuevoNombre, String nuevoTelefono, String nuevoCorreo) {
        Cliente cliente = buscarClientePorCedula(cedula);
        if (cliente != null) {
            cliente.setNombre(nuevoNombre);
            cliente.setTelefono(nuevoTelefono);
            cliente.setCorreo(nuevoCorreo);
            System.out.println("Cliente actualizado correctamente.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    @Override
    public void registrarVehiculo(String cedulaCliente, Vehiculo vehiculo) {
        Cliente cliente = buscarClientePorCedula(cedulaCliente);
        if (cliente != null) {
            cliente.agregarVehiculo(vehiculo);
            System.out.println("Vehículo registrado al cliente.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    @Override
    public Vehiculo buscarVehiculoPorPlaca(String placa) {
        for (Cliente c : parqueadero.getClientes()) {
            for (Vehiculo v : c.getVehiculos()) {
                if (v.getPlaca().equalsIgnoreCase(placa)) {
                    return v;
                }
            }
        }
        return null;
    }

    @Override
    public void mostrarVehiculosDeCliente(String cedula) {
        Cliente cliente = buscarClientePorCedula(cedula);
        if (cliente != null) {
            for (Vehiculo v : cliente.getVehiculos()) {
                System.out.println(v);
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    @Override
    public void actualizarVehiculo(String cedulaCliente, String placa, String nuevoColor, String nuevoModelo) {
        Cliente cliente = buscarClientePorCedula(cedulaCliente);
        if (cliente != null && cliente.actualizarVehiculo(placa, nuevoColor, nuevoModelo)) {
            System.out.println("Vehículo actualizado correctamente.");
        } else {
            System.out.println("Vehículo o cliente no encontrado.");
        }
    }

    @Override
    public void crearMembresia(String placa, int meses) {
        Vehiculo v = buscarVehiculoPorPlaca(placa);
        if (v != null) {
            if (!parqueadero.hayEspacioDisponible(v.getTipo())) {
                System.out.println("No hay espacio disponible para este tipo de vehículo.");
                return;
            }
            double tarifa = parqueadero.getTarifa(v.getTipo());
            Membresia m = new Membresia(v, LocalDate.now(), meses, tarifa * meses);
            parqueadero.agregarMembresia(m);
            parqueadero.registrarPago(new Pago(v.getPlaca(), tarifa * meses, LocalDateTime.now(), "Membresía"));
            System.out.println("Membresía creada correctamente.");
        } else {
            System.out.println("Vehículo no encontrado.");
        }
    }

    @Override
    public void mostrarMembresias() {
        for (Membresia m : parqueadero.getMembresias()) {
            System.out.println(m);
        }
    }

    @Override
    public void configurarTarifa(String tipo, double valor) {
        parqueadero.setTarifa(tipo, valor);
        System.out.println("Tarifa configurada para " + tipo);
    }

    @Override
    public void configurarEspacios(int motos, int autos, int camiones) {
        parqueadero.setEspaciosMotos(motos);
        parqueadero.setEspaciosAutos(autos);
        parqueadero.setEspaciosCamiones(camiones);
        System.out.println("Espacios configurados correctamente.");
    }

    @Override
    public void actualizarDatosParqueadero(String nombre, String direccion, String representante, String telefono, String correo) {
        parqueadero.setNombre(nombre);
        parqueadero.setDireccion(direccion);
        parqueadero.setRepresentante(representante);
        parqueadero.setTelefono(telefono);
        parqueadero.setCorreo(correo);
        System.out.println("Datos del parqueadero actualizados correctamente.");
    }
    
    @Override
    public void registrarIngresoTemporal(Vehiculo vehiculo) {
        if (!parqueadero.hayEspacioDisponible(vehiculo.getTipo())) {
            System.out.println("No hay espacio disponible para este tipo de vehículo.");
            return;
        }
        IngresoTemporal ingreso = new IngresoTemporal(vehiculo, LocalDateTime.now());
        parqueadero.agregarIngresoTemporal(ingreso);
        System.out.println("Ingreso temporal registrado.");
    }

    @Override
    public void registrarSalidaTemporal(String placa) {
        for (IngresoTemporal ingreso : parqueadero.getIngresosTemporales()) {
            if (ingreso.getVehiculo().getPlaca().equalsIgnoreCase(placa) && ingreso.getFechaSalida() == null) {
                ingreso.setFechaSalida(LocalDateTime.now());

                long minutos = Duration.between(ingreso.getFechaIngreso(), ingreso.getFechaSalida()).toMinutes();
                long horas = (long) Math.ceil(minutos / 60.0);
                double tarifa = parqueadero.getTarifa(ingreso.getVehiculo().getTipo());
                double monto = tarifa * horas;

                ingreso.setMontoPagado(monto);
                parqueadero.registrarPago(new Pago(placa, monto, LocalDateTime.now(), "Temporal"));
                parqueadero.liberarEspacio(ingreso.getVehiculo().getTipo());

                System.out.println("Salida registrada. Monto a pagar: $" + monto);
                return;
            }
        }
        System.out.println("Vehículo no encontrado o ya salió.");
    }

    @Override
    public void mostrarIngresosTemporales() {
        for (IngresoTemporal ingreso : parqueadero.getIngresosTemporales()) {
            System.out.println(ingreso);
        }
    }

    @Override
    public void mostrarHistorialPagos() {
        for (Pago p : parqueadero.getHistorialPagos()) {
            System.out.println(p);
        }
    }

    @Override
    public double calcularTotalIngresos() {
        double total = 0;
        for (Pago p : parqueadero.getHistorialPagos()) {
            total += p.getMonto();
        }
        return total;
    }

    @Override
    public double calcularIngresosPorPeriodo(String periodo) {
        double total = 0;
        LocalDateTime ahora = LocalDateTime.now();
        for (Pago p : parqueadero.getHistorialPagos()) {
            if (periodo.equalsIgnoreCase("dia") && p.getFecha().toLocalDate().isEqual(ahora.toLocalDate())) {
                total += p.getMonto();
            } else if (periodo.equalsIgnoreCase("mes") && p.getFecha().getMonth().equals(ahora.getMonth()) && p.getFecha().getYear() == ahora.getYear()) {
                total += p.getMonto();
            } else if (periodo.equalsIgnoreCase("año") && p.getFecha().getYear() == ahora.getYear()) {
                total += p.getMonto();
            }
        }
        return total;
    }

    @Override
    public void generarFacturaTemporal(String placa) {
        for (IngresoTemporal ingreso : parqueadero.getIngresosTemporales()) {
            if (ingreso.getVehiculo().getPlaca().equalsIgnoreCase(placa) && ingreso.getFechaSalida() != null) {
                System.out.println("----- FACTURA TEMPORAL -----");
                System.out.println(parqueadero);
                System.out.println("Vehículo: " + ingreso.getVehiculo());
                System.out.println("Ingreso: " + ingreso.getFechaIngreso());
                System.out.println("Salida: " + ingreso.getFechaSalida());
                System.out.println("Monto pagado: $" + ingreso.getMontoPagado());
                return;
            }
        }
        System.out.println("No se encontró ingreso temporal finalizado para esta placa.");
    }

    @Override
    public void generarFacturaMembresia(String placa) {
        Membresia m = parqueadero.buscarMembresiaPorPlaca(placa);
        if (m != null) {
            System.out.println("----- FACTURA MEMBRESÍA -----");
            System.out.println(parqueadero);
            System.out.println("Vehículo: " + m.getVehiculo());
            System.out.println("Fecha inicio: " + m.getFechaInicio());
            System.out.println("Fecha fin: " + m.getFechaFin());
            System.out.println("Monto pagado: $" + m.getMonto());
        } else {
            System.out.println("No se encontró membresía para esta placa.");
        }
    }

    @Override
    public void mostrarVehiculosActuales() {
        List<Vehiculo> vehiculos = parqueadero.obtenerVehiculosActuales();
        for (Vehiculo v : vehiculos) {
            System.out.println(v);
        }
    }

    @Override
    public void mostrarClientesConMembresiasActivas() {
        List<Cliente> clientes = parqueadero.obtenerClientesConMembresiaActiva();
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }
}

