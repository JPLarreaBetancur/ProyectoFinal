package View;


import Model.*;
import Services.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ParqueaderoGUI extends JFrame {
    private IParqueaderoService servicio;

    public ParqueaderoGUI(IParqueaderoService servicio) {
        this.servicio = servicio;
        setTitle("Sistema de Parqueadero");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        // Establecer un diseño de panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Crear panel de encabezado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(98, 180, 70));
        JLabel titleLabel = new JLabel("Sistema de Parqueadero");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerPanel.add(titleLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Crear panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 2, 10, 10)); // Dos columnas
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Bordes

        // Crear botones para cada opción del menú
        JButton btnAgregarCliente = createStyledButton("Agregar Cliente");
        JButton btnMostrarClientes = createStyledButton("Mostrar Clientes");
        JButton btnBuscarClienteCedula = createStyledButton("Buscar Cliente por Cédula");
        JButton btnBuscarClienteNombre = createStyledButton("Buscar Cliente por Nombre");
        JButton btnBuscarClienteTelefono = createStyledButton("Buscar Cliente por Teléfono");
        JButton btnActualizarCliente = createStyledButton("Actualizar Cliente");
        JButton btnEliminarCliente = createStyledButton("Eliminar Cliente");
        JButton btnRegistrarVehiculo = createStyledButton("Registrar Vehículo a Cliente");
        JButton btnMostrarVehiculos = createStyledButton("Mostrar Vehículos de Cliente");
        JButton btnActualizarVehiculo = createStyledButton("Actualizar Vehículo");
        JButton btnCrearMembresia = createStyledButton("Crear Membresía");
        JButton btnMostrarMembresias = createStyledButton("Mostrar Membresías");
        JButton btnRegistrarIngresoTemporal = createStyledButton("Registrar Ingreso Temporal");
        JButton btnRegistrarSalidaTemporal = createStyledButton("Registrar Salida Temporal");
        JButton btnMostrarIngresos = createStyledButton("Mostrar Ingresos Temporales");
        JButton btnMostrarHistorialPagos = createStyledButton("Mostrar Historial de Pagos");
        JButton btnCalcularTotalIngresos = createStyledButton("Mostrar Total de Ingresos");
        JButton btnCalcularIngresosPeriodo = createStyledButton("Mostrar Ingresos por Periodo");
        JButton btnGenerarFacturaTemporal = createStyledButton("Generar Factura por Ingreso Temporal");
        JButton btnGenerarFacturaMembresia = createStyledButton("Generar Factura por Membresía");
        JButton btnMostrarVehiculosActuales = createStyledButton("Mostrar Vehículos Actuales");
        JButton btnMostrarClientesConMembresias = createStyledButton("Mostrar Clientes con Membresías Activas");
        JButton btnConfigurarTarifas = createStyledButton("Configurar Tarifas");
        JButton btnConfigurarEspacios = createStyledButton("Configurar Espacios Disponibles");
        JButton btnActualizarDatosParqueadero = createStyledButton("Actualizar Datos del Parqueadero");
        JButton btnMostrarEspaciosDisponibles = createStyledButton("Mostrar Espacios Disponibles y Ocupados");
        JButton btnMostrarDatosParqueadero = createStyledButton("Mostrar Datos del Parqueadero");
        JButton btnSalir = createStyledButton("Salir");

        // Agregar ActionListeners a los botones
        btnAgregarCliente.addActionListener(e -> agregarCliente());
        btnMostrarClientes.addActionListener(e -> mostrarClientes());
        btnBuscarClienteCedula.addActionListener(e -> buscarClientePorCedula());
        btnBuscarClienteNombre.addActionListener(e -> buscarClientePorNombre());
        btnBuscarClienteTelefono.addActionListener(e -> buscarClientePorTelefono());
        btnActualizarCliente.addActionListener(e -> actualizarCliente());
        btnEliminarCliente.addActionListener(e -> eliminarCliente());
        btnRegistrarVehiculo.addActionListener(e -> registrarVehiculo());
        btnMostrarVehiculos.addActionListener(e -> mostrarVehiculos());
        btnActualizarVehiculo.addActionListener(e -> actualizarVehiculo());
        btnCrearMembresia.addActionListener(e -> crearMembresia());
        btnMostrarMembresias.addActionListener(e -> mostrarMembresias());
        btnRegistrarIngresoTemporal.addActionListener(e -> registrarIngresoTemporal());
        btnRegistrarSalidaTemporal.addActionListener(e -> registrarSalidaTemporal());
        btnMostrarIngresos.addActionListener(e -> mostrarIngresos());
        btnMostrarHistorialPagos.addActionListener(e -> mostrarHistorialPagos());
        btnCalcularTotalIngresos.addActionListener(e -> calcularTotalIngresos());
        btnCalcularIngresosPeriodo.addActionListener(e -> calcularIngresosPeriodo());
        btnGenerarFacturaTemporal.addActionListener(e -> generarFacturaTemporal());
        btnGenerarFacturaMembresia.addActionListener(e -> generarFacturaMembresia());
        btnMostrarVehiculosActuales.addActionListener(e -> mostrarVehiculosActuales());
        btnMostrarClientesConMembresias.addActionListener(e -> mostrarClientesConMembresias());
        btnConfigurarTarifas.addActionListener(e -> configurarTarifas());
        btnConfigurarEspacios.addActionListener(e -> configurarEspacios());
        btnActualizarDatosParqueadero.addActionListener(e -> actualizarDatosParqueadero());
        btnMostrarEspaciosDisponibles.addActionListener(e -> mostrarEspaciosDisponibles());
        btnMostrarDatosParqueadero.addActionListener(e -> mostrarDatosParqueadero());
        btnSalir.addActionListener(e -> System.exit(0));

        // Agregar botones al panel
        buttonPanel.add(btnAgregarCliente);
        buttonPanel.add(btnMostrarClientes);
        buttonPanel.add(btnBuscarClienteCedula);
        buttonPanel.add(btnBuscarClienteNombre);
        buttonPanel.add(btnBuscarClienteTelefono);
        buttonPanel.add(btnActualizarCliente);
        buttonPanel.add(btnEliminarCliente);
        buttonPanel.add(btnRegistrarVehiculo);
        buttonPanel.add(btnMostrarVehiculos);
        buttonPanel.add(btnActualizarVehiculo);
        buttonPanel.add(btnCrearMembresia);
        buttonPanel.add(btnMostrarMembresias);
        buttonPanel.add(btnRegistrarIngresoTemporal);
        buttonPanel.add(btnRegistrarSalidaTemporal);
        buttonPanel.add(btnMostrarIngresos);
        buttonPanel.add(btnMostrarHistorialPagos);
        buttonPanel.add(btnCalcularTotalIngresos);
        buttonPanel.add(btnCalcularIngresosPeriodo);
        buttonPanel.add(btnGenerarFacturaTemporal);
        buttonPanel.add(btnGenerarFacturaMembresia);
        buttonPanel.add(btnMostrarVehiculosActuales);
        buttonPanel.add(btnMostrarClientesConMembresias);
        buttonPanel.add(btnConfigurarTarifas);
        buttonPanel.add(btnConfigurarEspacios);
        buttonPanel.add(btnActualizarDatosParqueadero);
        buttonPanel.add(btnMostrarEspaciosDisponibles);
        buttonPanel.add(btnMostrarDatosParqueadero);
        buttonPanel.add(btnSalir);

        // Agregar panel de botones al panel principal
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(30, 144, 255)); // Color de fondo del botón
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaciado interno
        return button;
    }



    private void mostrarEnScrollPane(String contenido, String titulo) {
        JTextArea textArea = new JTextArea(20, 40);
        textArea.setText(contenido);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(this, scrollPane, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    private void agregarCliente() {
        String nombre = JOptionPane.showInputDialog("Nombre:");
        String cedula = JOptionPane.showInputDialog("Cédula:");
        String tel = JOptionPane.showInputDialog("Teléfono:");
        String correo = JOptionPane.showInputDialog("Correo:");
        servicio.agregarCliente(new Cliente(nombre, cedula, tel, correo));
        JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente");
    }

    private void mostrarClientes() {
        StringBuilder clientes = new StringBuilder("=== CLIENTES ===\n");
        for (Cliente c : servicio.getCliente()) {
            clientes.append(c.toString()).append("\n\n");
        }
        mostrarEnScrollPane(clientes.toString(), "Lista de Clientes");
    }

    private void buscarClientePorCedula() {
        String cedula = JOptionPane.showInputDialog("Cédula:");
        Cliente c = servicio.buscarClientePorCedula(cedula);
        if (c != null) {
            JOptionPane.showMessageDialog(this, c.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
        }
    }
    private void buscarClientePorNombre() {
        String nombre = JOptionPane.showInputDialog("Nombre:");
        Cliente c = servicio.buscarClientePorNombre(nombre);
        if (c != null) {
            JOptionPane.showMessageDialog(this, c.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
        }
    }

    private void buscarClientePorTelefono() {
        String telefono = JOptionPane.showInputDialog("Teléfono:");
        Cliente c = servicio.buscarClientePorTelefono(telefono);
        if (c != null) {
            JOptionPane.showMessageDialog(this, c.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
        }
    }

    private void actualizarCliente() {
        String cedula = JOptionPane.showInputDialog("Cédula:");
        String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:");
        String nuevoTel = JOptionPane.showInputDialog("Nuevo teléfono:");
        String nuevoCorreo = JOptionPane.showInputDialog("Nuevo correo:");
        servicio.actualizarCliente(cedula, nuevoNombre, nuevoTel, nuevoCorreo);
        JOptionPane.showMessageDialog(this, "Cliente actualizado");
    }

    private void eliminarCliente() {
        String cedula = JOptionPane.showInputDialog("Cédula:");
        servicio.eliminarCliente(cedula);
        JOptionPane.showMessageDialog(this, "Cliente eliminado");
    }

    private void registrarVehiculo() {
        String cedula = JOptionPane.showInputDialog("Cédula cliente:");
        String tipo = JOptionPane.showInputDialog("Tipo (Moto/Carro/Camion):");
        String placa = JOptionPane.showInputDialog("Placa:");
        String color = JOptionPane.showInputDialog("Color:");
        String modelo = JOptionPane.showInputDialog("Modelo:");

        Vehiculo v = tipo.equalsIgnoreCase("Moto") ? new Moto(placa, color, modelo)
                : tipo.equalsIgnoreCase("Carro") ? new Carro(placa, color, modelo)
                : new Camion(placa, color, modelo);

        servicio.registrarVehiculo(cedula, v);
        JOptionPane.showMessageDialog(this, "Vehículo registrado");
    }

    private void mostrarVehiculos() {
        String cedula = JOptionPane.showInputDialog("Cédula cliente:");
        List<Vehiculo> vehiculos = servicio.mostrarVehiculosDeCliente(cedula);
        StringBuilder sb = new StringBuilder("=== VEHÍCULOS ===\n");
        for (Vehiculo v : vehiculos) {
            sb.append(v.toString()).append("\n\n");
        }
        mostrarEnScrollPane(sb.toString(), "Vehículos del Cliente");
    }

    private void actualizarVehiculo() {
        String cedula = JOptionPane.showInputDialog("Cédula cliente:");
        String placa = JOptionPane.showInputDialog("Placa del vehículo:");
        String color = JOptionPane.showInputDialog("Nuevo color:");
        String modelo = JOptionPane.showInputDialog("Nuevo modelo:");
        servicio.actualizarVehiculo(cedula, placa, color, modelo);
        JOptionPane.showMessageDialog(this, "Vehículo actualizado");
    }

    private void crearMembresia() {
        // Implementar la lógica para crear membresía
    }

    private void mostrarMembresias() {
        // Implementar la lógica para mostrar membresías
    }

    private void registrarIngresoTemporal() {
        // Implementar la lógica para registrar ingreso temporal
    }

    private void registrarSalidaTemporal() {
        // Implementar la lógica para registrar salida temporal
    }

    private void mostrarIngresos() {
        // Implementar la lógica para mostrar ingresos temporales
    }

    private void mostrarHistorialPagos() {
        // Implementar la lógica para mostrar historial de pagos
    }

    private void calcularTotalIngresos() {
        double total = servicio.calcularTotalIngresos();
        JOptionPane.showMessageDialog(this, "Total de ingresos: $" + total);
    }

    private void calcularIngresosPeriodo() {
        String periodo = JOptionPane.showInputDialog("Periodo (dia/mes/año):");
        double ingresos = servicio.calcularIngresosPorPeriodo(periodo);
        JOptionPane.showMessageDialog(this, "Ingresos en el periodo: $" + ingresos);
    }

    private void generarFacturaTemporal() {
        String placa = JOptionPane.showInputDialog("Placa del vehículo:");
        String factura = servicio.generarFacturaTemporal(placa);
        mostrarEnScrollPane(factura, "Factura Temporal");
    }

    private void generarFacturaMembresia() {
        String placa = JOptionPane.showInputDialog("Placa del vehículo:");
        String factura = servicio.generarFacturaMembresia(placa);
        mostrarEnScrollPane(factura, "Factura de Membresía");
    }

    private void mostrarVehiculosActuales() {
        StringBuilder vehiculos = new StringBuilder("=== VEHÍCULOS ACTUALES ===\n");
        for (Vehiculo v : servicio.mostrarVehiculosActuales()) {
            vehiculos.append(v.toString()).append("\n\n");
        }
        mostrarEnScrollPane(vehiculos.toString(), "Vehículos en Parqueadero");
    }

    private void mostrarClientesConMembresias() {
        StringBuilder clientes = new StringBuilder("=== CLIENTES CON MEMBRESÍAS ACTIVAS ===\n");
        for (Cliente c : servicio.mostrarClientesConMembresiasActivas()) {
            clientes.append(c.toString()).append("\n\n");
        }
        mostrarEnScrollPane(clientes.toString(), "Clientes con Membresías");
    }

    private void configurarTarifas() {
        String tipo = JOptionPane.showInputDialog("Tipo de vehículo (Moto/Carro/Camion):");
        try {
            double valor = Double.parseDouble(JOptionPane.showInputDialog("Nueva tarifa por hora:"));
            servicio.configurarTarifa(tipo, valor);
            JOptionPane.showMessageDialog(this, "Tarifa actualizada");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un valor numérico");
        }
    }

    private void configurarEspacios() {
        try {
            int motos = Integer.parseInt(JOptionPane.showInputDialog("Espacios para motos:"));
            int autos = Integer.parseInt(JOptionPane.showInputDialog("Espacios para autos:"));
            int camiones = Integer.parseInt(JOptionPane.showInputDialog("Espacios para camiones:"));
            servicio.configurarEspacios(motos, autos, camiones);
            JOptionPane.showMessageDialog(this, "Espacios configurados");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Debe ingresar valores numéricos");
        }
    }

    private void actualizarDatosParqueadero() {
        String nombre = JOptionPane.showInputDialog("Nuevo nombre:");
        String direccion = JOptionPane.showInputDialog("Nueva dirección:");
        String representante = JOptionPane.showInputDialog("Nuevo representante:");
        String telefono = JOptionPane.showInputDialog("Nuevo teléfono:");
        String correo = JOptionPane.showInputDialog("Nuevo correo:");
        servicio.actualizarDatosParqueadero(nombre, direccion, representante, telefono, correo);
        JOptionPane.showMessageDialog(this, "Datos del parqueadero actualizados");
    }

    private void mostrarEspaciosDisponibles() {
        String espacios = "Espacios disponibles:\n" +
                "Motos: " + (servicio.getEspaciosMotos() - servicio.getOcupadosMotos()) + "\n" +
                "Carros: " + (servicio.getEspaciosAutos() - servicio.getOcupadosAutos()) + "\n" +
                "Camiones: " + (servicio.getEspaciosCamiones() - servicio.getOcupadosCamiones());
        JOptionPane.showMessageDialog(this, espacios);
    }

    private void mostrarDatosParqueadero() {
        JOptionPane.showMessageDialog(this, servicio.getParqueadero().toString(), "Datos del Parqueadero", JOptionPane.INFORMATION_MESSAGE);
    }


    public static void main(String[] args) {
        Parqueadero parqueadero = new Parqueadero("Mi Parqueadero", "Calle 13", "Admin", "3121234567", "contacto@parqueadero.com");
        IParqueaderoService servicio = new ParqueaderoService(parqueadero);

        // Configurar tarifas y espacios
        servicio.configurarTarifa("Moto", 1000);
        servicio.configurarTarifa("Carro", 2000);
        servicio.configurarTarifa("Camion", 3000);
        servicio.configurarEspacios(10, 10, 5);

        SwingUtilities.invokeLater(() -> {
            ParqueaderoGUI gui = new ParqueaderoGUI(servicio);
            gui.setVisible(true);
        });
    }
}
