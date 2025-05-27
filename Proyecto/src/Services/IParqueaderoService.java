package Services;

import Model.*;
import java.util.List;


public interface IParqueaderoService {
    void agregarCliente(Cliente cliente);
    Cliente buscarClientePorCedula(String cedula);
    Cliente buscarClientePorNombre(String nombre);
    Cliente buscarClientePorTelefono(String telefono);
    void eliminarCliente(String cedula);
    void mostrarClientes();
    void actualizarCliente(String cedula, String nuevoNombre, String nuevoTelefono, String nuevoCorreo);

    void registrarVehiculo(String cedulaCliente, Vehiculo vehiculo);
    Vehiculo buscarVehiculoPorPlaca(String placa);
    List<Vehiculo> mostrarVehiculosDeCliente(String cedula);
    void actualizarVehiculo(String cedulaCliente, String placa, String nuevoColor, String nuevoModelo);

    void crearMembresia(String placa, int meses);
    List<Membresia> mostrarMembresias();

    void configurarTarifa(String tipo, double valor);
    void configurarEspacios(int motos, int autos, int camiones);
    void actualizarDatosParqueadero(String nombre, String direccion, String representante, String telefono, String correo);

    void registrarIngresoTemporal(Vehiculo vehiculo);
    void registrarSalidaTemporal(String placa);
    List<IngresoTemporal> mostrarIngresosTemporales();


    List<Pago> mostrarHistorialPagos();
    double calcularTotalIngresos();
    double calcularIngresosPorPeriodo(String periodo);

    String generarFacturaTemporal(String placa);
    String generarFacturaMembresia(String placa);

    List<Vehiculo> mostrarVehiculosActuales();
    List<Cliente> mostrarClientesConMembresiasActivas();

}