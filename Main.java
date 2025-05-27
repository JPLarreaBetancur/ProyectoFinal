package controller;

import model.*;
import service.*;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Main {
    public static void main(String[] args) {
        Parqueadero parqueadero = new Parqueadero("Mi Parqueadero", "Calle 13", "Admin", "3121234567", "contacto@parqueadero.com");
        IParqueaderoService servicio = new ParqueaderoService(parqueadero);

        
        servicio.configurarTarifa("Moto", 1000);
        servicio.configurarTarifa("Carro", 2000);
        servicio.configurarTarifa("Camion", 3000);
        servicio.configurarEspacios(10, 10, 5);

        while (true) {
            String opcion = JOptionPane.showInputDialog(
                "===== MENÚ PRINCIPAL =====\n" +
                "1. Agregar cliente\n" +
                "2. Mostrar clientes\n" +
                "3. Buscar cliente por cédula\n" +
                "4. Buscar cliente por nombre\n" +
                "5. Buscar cliente por teléfono\n" +
                "6. Actualizar cliente\n" +
                "7. Eliminar cliente\n" +
                "8. Registrar vehículo a cliente\n" +
                "9. Mostrar vehículos de cliente\n" +
                "10. Actualizar vehículo\n" +
                "11. Crear membresía\n" +
                "12. Mostrar membresías\n" +
                "13. Registrar ingreso temporal\n" +
                "14. Registrar salida temporal\n" +
                "15. Mostrar ingresos temporales\n" +
                "16. Mostrar historial de pagos\n" +
                "17. Mostrar total de ingresos\n" +
                "18. Mostrar ingresos por periodo\n" +
                "19. Generar factura por ingreso temporal\n" +
                "20. Generar factura por membresía\n" +
                "21. Mostrar vehículos actuales en parqueadero\n" +
                "22. Mostrar clientes con membresías activas\n" +
                "23. Configurar tarifas\n" +
                "24. Configurar espacios disponibles\n" +
                "25. Actualizar datos del parqueadero\n" +
                "26. Mostrar espacios disponibles y ocupados\n" +
                "27. Mostrar datos del parqueadero\n" +
                "0. Salir\n\n" +
                "Seleccione una opción:");

            if (opcion == null || opcion.equals("0")) {
                break;
            }

            switch (opcion) {
                case "1" -> {
                    String nombre = JOptionPane.showInputDialog("Nombre:");
                    String cedula = JOptionPane.showInputDialog("Cédula:");
                    String tel = JOptionPane.showInputDialog("Teléfono:");
                    String correo = JOptionPane.showInputDialog("Correo:");
                    servicio.agregarCliente(new Cliente(nombre, cedula, tel, correo));
                    JOptionPane.showMessageDialog(null, "Cliente agregado exitosamente");
                }
                case "2" -> {
                    StringBuilder clientes = new StringBuilder("=== CLIENTES ===\n");
                    for (Cliente c : parqueadero.getClientes()) {
                        clientes.append(c.toString()).append("\n\n");
                    }
                    mostrarEnScrollPane(clientes.toString(), "Lista de Clientes");
                }
                case "3" -> {
                    String cedula = JOptionPane.showInputDialog("Cédula:");
                    Cliente c = servicio.buscarClientePorCedula(cedula);
                    if (c != null) {
                        JOptionPane.showMessageDialog(null, c.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                    }
                }
                case "4" -> {
                    String nombre = JOptionPane.showInputDialog("Nombre:");
                    Cliente c = servicio.buscarClientePorNombre(nombre);
                    if (c != null) {
                        JOptionPane.showMessageDialog(null, c.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                    }
                }
                case "5" -> {
                    String telefono = JOptionPane.showInputDialog("Teléfono:");
                    Cliente c = servicio.buscarClientePorTelefono(telefono);
                    if (c != null) {
                        JOptionPane.showMessageDialog(null, c.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                    }
                }
                case "6" -> {
                    String cedula = JOptionPane.showInputDialog("Cédula:");
                    String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:");
                    String nuevoTel = JOptionPane.showInputDialog("Nuevo teléfono:");
                    String nuevoCorreo = JOptionPane.showInputDialog("Nuevo correo:");
                    servicio.actualizarCliente(cedula, nuevoNombre, nuevoTel, nuevoCorreo);
                    JOptionPane.showMessageDialog(null, "Cliente actualizado");
                }
                case "7" -> {
                    String cedula = JOptionPane.showInputDialog("Cédula:");
                    servicio.eliminarCliente(cedula);
                    JOptionPane.showMessageDialog(null, "Cliente eliminado");
                }
                case "8" -> {
                    String cedula = JOptionPane.showInputDialog("Cédula cliente:");
                    String tipo = JOptionPane.showInputDialog("Tipo (Moto/Carro/Camion):");
                    String placa = JOptionPane.showInputDialog("Placa:");
                    String color = JOptionPane.showInputDialog("Color:");
                    String modelo = JOptionPane.showInputDialog("Modelo:");
                    
                    Vehiculo v = tipo.equalsIgnoreCase("Moto") ? new Moto(placa, color, modelo)
                            : tipo.equalsIgnoreCase("Carro") ? new Carro(placa, color, modelo)
                            : new Camion(placa, color, modelo);
                    
                    servicio.registrarVehiculo(cedula, v);
                    JOptionPane.showMessageDialog(null, "Vehículo registrado");
                }
                case "9" -> {
                    String cedula = JOptionPane.showInputDialog("Cédula cliente:");
                    List<Vehiculo> vehiculos = servicio.mostrarVehiculosDeCliente(cedula);
                    StringBuilder sb = new StringBuilder("=== VEHÍCULOS ===\n");
                    for (Vehiculo v : vehiculos) {
                        sb.append(v.toString()).append("\n\n");
                    }
                    mostrarEnScrollPane(sb.toString(), "Vehículos del Cliente");
                }
                case "10" -> {
                    String cedula = JOptionPane.showInputDialog("Cédula cliente:");
                    String placa = JOptionPane.showInputDialog("Placa del vehículo:");
                    String color = JOptionPane.showInputDialog("Nuevo color:");
                    String modelo = JOptionPane.showInputDialog("Nuevo modelo:");
                    servicio.actualizarVehiculo(cedula, placa, color, modelo);
                    JOptionPane.showMessageDialog(null, "Vehículo actualizado");
                }
                case "11" -> {
                    String cedulaCliente = JOptionPane.showInputDialog("Cédula del cliente:");
                    Cliente cliente = servicio.buscarClientePorCedula(cedulaCliente);
                    
                    if (cliente == null) {
                        int respuesta = JOptionPane.showConfirmDialog(null, 
                            "Cliente no registrado. ¿Desea registrarlo ahora?", 
                            "Registro de cliente", 
                            JOptionPane.YES_NO_OPTION);
                        
                        if (respuesta == JOptionPane.YES_OPTION) {
                            String nombre = JOptionPane.showInputDialog("Nombre:");
                            String telefono = JOptionPane.showInputDialog("Teléfono:");
                            String correo = JOptionPane.showInputDialog("Correo:");
                            cliente = new Cliente(nombre, cedulaCliente, telefono, correo);
                            servicio.agregarCliente(cliente);
                        } else {
                            continue;
                        }
                    }

                    String[] opcionesVehiculo = {"Vehículo existente", "Nuevo vehículo"};
                    int opcionVehiculo = JOptionPane.showOptionDialog(null,
                        "¿El vehículo está registrado?",
                        "Registro de vehículo",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcionesVehiculo,
                        opcionesVehiculo[0]);
                    
                    Vehiculo vehiculo;
                    if (opcionVehiculo == 0) {
                        String placa = JOptionPane.showInputDialog("Placa del vehículo:");
                        vehiculo = servicio.buscarVehiculoPorPlaca(placa);
                        if (vehiculo == null) {
                            JOptionPane.showMessageDialog(null, "Vehículo no encontrado.");
                            continue;
                        }
                    } else {
                        String tipo = JOptionPane.showInputDialog("Tipo (Moto/Carro/Camion):");
                        String placa = JOptionPane.showInputDialog("Placa:");
                        String color = JOptionPane.showInputDialog("Color:");
                        String modelo = JOptionPane.showInputDialog("Modelo:");
                        
                        vehiculo = tipo.equalsIgnoreCase("Moto") ? new Moto(placa, color, modelo)
                                : tipo.equalsIgnoreCase("Carro") ? new Carro(placa, color, modelo)
                                : new Camion(placa, color, modelo);
                        
                        servicio.registrarVehiculo(cedulaCliente, vehiculo);
                    }

                    try {
                        int meses = Integer.parseInt(JOptionPane.showInputDialog("Duración (meses):"));
                        servicio.crearMembresia(vehiculo.getPlaca(), meses);
                        JOptionPane.showMessageDialog(null, "Membresía creada exitosamente");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Debe ingresar un número válido");
                    }
                }
                case "12" -> {
                    StringBuilder membresias = new StringBuilder("=== MEMBRESÍAS ===\n");
                    for (Membresia m : servicio.mostrarMembresias()) {
                        membresias.append(m.toString()).append("\n\n");
                    }
                    mostrarEnScrollPane(membresias.toString(), "Membresías Activas");
                }
                case "13" -> {
                    String[] opcionesVehiculo = {"Vehículo existente", "Nuevo vehículo"};
                    int opcionVehiculo = JOptionPane.showOptionDialog(null,
                        "¿El vehículo está registrado?",
                        "Registro de ingreso temporal",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcionesVehiculo,
                        opcionesVehiculo[0]);

                    Vehiculo vehiculo = null;

                    if (opcionVehiculo == 0) {
                        String placa = JOptionPane.showInputDialog("Placa del vehículo:");
                        vehiculo = servicio.buscarVehiculoPorPlaca(placa);
                        if (vehiculo == null) {
                            JOptionPane.showMessageDialog(null, "Vehículo no encontrado.");
                            return;
                        }
                    } else {
                        String tipo = JOptionPane.showInputDialog("Tipo (Moto/Carro/Camion):");
                        String placa = JOptionPane.showInputDialog("Placa:");
                        String color = JOptionPane.showInputDialog("Color:");
                        String modelo = JOptionPane.showInputDialog("Modelo:");

                        vehiculo = tipo.equalsIgnoreCase("Moto") ? new Moto(placa, color, modelo)
                                : tipo.equalsIgnoreCase("Carro") ? new Carro(placa, color, modelo)
                                : new Camion(placa, color, modelo);
                    }

                    servicio.registrarIngresoTemporal(vehiculo);
                    JOptionPane.showMessageDialog(null, "Ingreso temporal registrado correctamente.");
                }
                case "14" -> {
                    String placa = JOptionPane.showInputDialog("Placa del vehículo:");
                    servicio.registrarSalidaTemporal(placa);
                    JOptionPane.showMessageDialog(null, "Salida temporal registrada");
                }
                case "15" -> {
                    StringBuilder ingresos = new StringBuilder("=== INGRESOS TEMPORALES ===\n");
                    for (IngresoTemporal it : servicio.mostrarIngresosTemporales()) {
                        ingresos.append(it.toString()).append("\n\n");
                    }
                    mostrarEnScrollPane(ingresos.toString(), "Ingresos Temporales");
                }
                case "16" -> {
                    StringBuilder pagos = new StringBuilder("=== HISTORIAL DE PAGOS ===\n");
                    for (Pago p : servicio.mostrarHistorialPagos()) {
                        pagos.append(p.toString()).append("\n\n");
                    }
                    mostrarEnScrollPane(pagos.toString(), "Historial de Pagos");
                }
                case "17" -> {
                    double total = servicio.calcularTotalIngresos();
                    JOptionPane.showMessageDialog(null, "Total de ingresos: $" + total);
                }
                case "18" -> {
                    String periodo = JOptionPane.showInputDialog("Periodo (dia/mes/año):");
                    double ingresos = servicio.calcularIngresosPorPeriodo(periodo);
                    JOptionPane.showMessageDialog(null, "Ingresos en el periodo: $" + ingresos);
                }
                case "19" -> {
                    String placa = JOptionPane.showInputDialog("Placa del vehículo:");
                    String factura = servicio.generarFacturaTemporal(placa);
                    mostrarEnScrollPane(factura, "Factura Temporal");
                }
                case "20" -> {
                    String placa = JOptionPane.showInputDialog("Placa del vehículo:");
                    String factura = servicio.generarFacturaMembresia(placa);
                    mostrarEnScrollPane(factura, "Factura de Membresía");
                }
                case "21" -> {
                    StringBuilder vehiculos = new StringBuilder("=== VEHÍCULOS ACTUALES ===\n");
                    for (Vehiculo v : servicio.mostrarVehiculosActuales()) {
                        vehiculos.append(v.toString()).append("\n\n");
                    }
                    mostrarEnScrollPane(vehiculos.toString(), "Vehículos en Parqueadero");
                }
                case "22" -> {
                    StringBuilder clientes = new StringBuilder("=== CLIENTES CON MEMBRESÍAS ACTIVAS ===\n");
                    for (Cliente c : servicio.mostrarClientesConMembresiasActivas()) {
                        clientes.append(c.toString()).append("\n\n");
                    }
                    mostrarEnScrollPane(clientes.toString(), "Clientes con Membresías");
                }
                case "23" -> {
                    String tipo = JOptionPane.showInputDialog("Tipo de vehículo (Moto/Carro/Camion):");
                    try {
                        double valor = Double.parseDouble(JOptionPane.showInputDialog("Nueva tarifa por hora:"));
                        servicio.configurarTarifa(tipo, valor);
                        JOptionPane.showMessageDialog(null, "Tarifa actualizada");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Debe ingresar un valor numérico");
                    }
                }
                case "24" -> {
                    try {
                        int motos = Integer.parseInt(JOptionPane.showInputDialog("Espacios para motos:"));
                        int autos = Integer.parseInt(JOptionPane.showInputDialog("Espacios para autos:"));
                        int camiones = Integer.parseInt(JOptionPane.showInputDialog("Espacios para camiones:"));
                        servicio.configurarEspacios(motos, autos, camiones);
                        JOptionPane.showMessageDialog(null, "Espacios configurados");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Debe ingresar valores numéricos");
                    }
                }
                case "25" -> {
                    String nombre = JOptionPane.showInputDialog("Nuevo nombre:");
                    String direccion = JOptionPane.showInputDialog("Nueva dirección:");
                    String representante = JOptionPane.showInputDialog("Nuevo representante:");
                    String telefono = JOptionPane.showInputDialog("Nuevo teléfono:");
                    String correo = JOptionPane.showInputDialog("Nuevo correo:");
                    servicio.actualizarDatosParqueadero(nombre, direccion, representante, telefono, correo);
                    JOptionPane.showMessageDialog(null, "Datos del parqueadero actualizados");
                }
                case "26" -> {
                    String espacios = "Espacios disponibles:\n" +
                            "Motos: " + (parqueadero.getEspaciosMotos() - parqueadero.getOcupadosMotos()) + "\n" +
                            "Carros: " + (parqueadero.getEspaciosAutos() - parqueadero.getOcupadosAutos()) + "\n" +
                            "Camiones: " + (parqueadero.getEspaciosCamiones() - parqueadero.getOcupadosCamiones());
                    JOptionPane.showMessageDialog(null, espacios);
                }
                case "27" -> {
                    JOptionPane.showMessageDialog(null, parqueadero.toString(), "Datos del Parqueadero", JOptionPane.INFORMATION_MESSAGE);
                }
                default -> JOptionPane.showMessageDialog(null, "Opción no válida");
            }
        }
               
               
        JOptionPane.showMessageDialog(null, "Sistema cerrado");
    }

    private static void mostrarEnScrollPane(String contenido, String titulo) {
        JTextArea textArea = new JTextArea(20, 40);
        textArea.setText(contenido);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    
}
