package model.gui;


import data.GestorEntidades;
import model.Interface.Registrable;
import model.Personas.Guia;
import model.Personas.Cliente;
import model.Operadores.Transporte;

import model.ServicioTuristico.RutaGastronomica;
import model.ServicioTuristico.PaseoLacustre;
import model.ServicioTuristico.ExcursionCultural;
import model.exception.RutInvalidoException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Interfaz gráfica (Swing) para el sistema Llanquihue Tour.
 * Permite ingresar guías, clientes, transportes y servicios turísticos,
 * y visualizar los resúmenes de todas las entidades registradas.
 *
 * Todas las entidades implementan la interfaz Registrable, por lo que
 * conviven en una misma colección polimórfica.
 *
 * @author Paula Gallegos
 * @version 8.0
 */
public class VentanaLlanquihueTour extends JFrame {

    // Ruta del archivo de datos (carga y guardado)
    private static final String RUTA = "src/main/resources/registros.txt";

    // Colección polimórfica: guarda TODAS las entidades porque todas son Registrable.
    private final List<Registrable> registros = new ArrayList<>();

    // Gestor encargado de leer y escribir el archivo de datos
    private final GestorEntidades gestor = new GestorEntidades();

    // Áreas de texto (una por pestaña) donde se listan los datos ingresados
    private JTextArea areaServicios;
    private JTextArea areaClientes;
    private JTextArea areaGuias;
    private JTextArea areaTransportes;
    private JTextArea areaTodos;

    public VentanaLlanquihueTour() {
        super("Sistema Llanquihue Tour");

        List<Registrable> datosArchivo = gestor.cargarDatos(RUTA);
        registros.addAll(datosArchivo);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 550);
        setLocationRelativeTo(null); // centra la ventana en la pantalla

        // Una pestaña por tipo de entidad, más una que muestra todas juntas
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Servicios", crearPanelServicios());
        tabs.addTab("Clientes", crearPanelClientes());
        tabs.addTab("Guías", crearPanelGuias());
        tabs.addTab("Transportes", crearPanelTransportes());
        tabs.addTab("Todos", crearPanelTodos());
        add(tabs);
    }

    // Crea un área de texto de solo lectura, usada para mostrar listados
    private JTextArea crearArea() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        return area;
    }

    // Guarda el estado actual de la colección en el archivo de datos
    private void guardar() {
        gestor.guardarDatos(new ArrayList<>(registros), RUTA);
    }

    // ══════════════════════════════════════════════════════════════════
    //  SERVICIOS TURÍSTICOS  (3 tipos con un campo genérico según el tipo)
    // ══════════════════════════════════════════════════════════════════
    private JPanel crearPanelServicios() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextField txtNombre = new JTextField();
        JTextField txtDuracion = new JTextField();

        JComboBox<String> comboTipo = new JComboBox<>(new String[]{
                "Ruta Gastronómica", "Paseo Lacustre", "Excursión Cultural"
        });

        JTextField txtDatoEspecifico = new JTextField();
        JLabel lblDatoEspecifico = new JLabel("N° de paradas:");

        // Al cambiar el tipo, cambia la etiqueta del campo específico
        comboTipo.addActionListener(e -> {
            String tipo = (String) comboTipo.getSelectedItem();
            if ("Ruta Gastronómica".equals(tipo)) {
                lblDatoEspecifico.setText("N° de paradas:");
            } else if ("Paseo Lacustre".equals(tipo)) {
                lblDatoEspecifico.setText("Tipo de embarcación:");
            } else {
                lblDatoEspecifico.setText("Lugar histórico:");
            }
        });

        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
        form.add(new JLabel("Nombre:"));
        form.add(txtNombre);
        form.add(new JLabel("Duración (horas):"));
        form.add(txtDuracion);
        form.add(new JLabel("Tipo de servicio:"));
        form.add(comboTipo);
        form.add(lblDatoEspecifico);
        form.add(txtDatoEspecifico);

        JButton btnAgregar = new JButton("Agregar servicio");
        btnAgregar.addActionListener(e -> {
            if (txtNombre.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "El nombre es obligatorio.");
                return;
            }
            double duracion;
            try {
                duracion = Double.parseDouble(txtDuracion.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La duración debe ser un número (ej: 2.5).");
                return;
            }

            String tipo = (String) comboTipo.getSelectedItem();
            String extra = txtDatoEspecifico.getText();

            if ("Ruta Gastronómica".equals(tipo)) {
                int paradas;
                try {
                    paradas = Integer.parseInt(extra);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El N° de paradas debe ser un número.");
                    return;
                }
                registros.add(new RutaGastronomica(txtNombre.getText(), duracion, paradas));
            } else if ("Paseo Lacustre".equals(tipo)) {
                registros.add(new PaseoLacustre(txtNombre.getText(), duracion, extra));
            } else { // Excursión Cultural
                registros.add(new ExcursionCultural(txtNombre.getText(), duracion, extra));
            }

            txtNombre.setText("");
            txtDuracion.setText("");
            txtDatoEspecifico.setText("");
            actualizarServicios();
            guardar();
            JOptionPane.showMessageDialog(this, "Servicio agregado exitosamente.");
        });

        JPanel norte = new JPanel(new BorderLayout());
        norte.add(form, BorderLayout.CENTER);
        norte.add(btnAgregar, BorderLayout.SOUTH);

        areaServicios = crearArea();
        panel.add(norte, BorderLayout.NORTH);
        panel.add(new JScrollPane(areaServicios), BorderLayout.CENTER);
        actualizarServicios();
        return panel;
    }

    // Listado resumido de servicios
    private void actualizarServicios() {
        StringBuilder sb = new StringBuilder();
        boolean hay = false;
        for (Registrable r : registros) {
            if (r instanceof RutaGastronomica) {
                RutaGastronomica rg = (RutaGastronomica) r;
                sb.append("[RUTA] ").append(rg.getNombre())
                        .append(" | Paradas: ").append(rg.getNumeroDeParadas())
                        .append("\n");
                hay = true;
            } else if (r instanceof PaseoLacustre) {
                PaseoLacustre pl = (PaseoLacustre) r;
                sb.append("[PASEO] ").append(pl.getNombre())
                        .append(" | Embarcación: ").append(pl.getTipoEmbarcacion())
                        .append("\n");
                hay = true;
            } else if (r instanceof ExcursionCultural) {
                ExcursionCultural ec = (ExcursionCultural) r;
                sb.append("[EXCURSIÓN] ").append(ec.getNombre())
                        .append(" | Lugar: ").append(ec.getLugarHistorico())
                        .append("\n");
                hay = true;
            }
        }
        if (!hay) sb.append("No hay servicios registrados.");
        areaServicios.setText(sb.toString());
    }

    // ══════════════════════════════════════════════════════════════════
    //  CLIENTES
    // ══════════════════════════════════════════════════════════════════
    private JPanel crearPanelClientes() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextField txtNombre = new JTextField();
        JTextField txtRut = new JTextField();
        JTextField txtCorreo = new JTextField();
        JTextField txtTelefono = new JTextField();
        JTextField txtNacionalidad = new JTextField();

        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));
        form.add(new JLabel("Nombre:"));
        form.add(txtNombre);
        form.add(new JLabel("RUT:"));
        form.add(txtRut);
        form.add(new JLabel("Correo:"));
        form.add(txtCorreo);
        form.add(new JLabel("Teléfono:"));
        form.add(txtTelefono);
        form.add(new JLabel("Nacionalidad:"));
        form.add(txtNacionalidad);

        JButton btnAgregar = new JButton("Agregar cliente");
        btnAgregar.addActionListener(e -> {
            if (txtNombre.getText().isBlank() || txtRut.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Nombre y RUT son obligatorios.");
                return;
            }
            try{
                registros.add(new Cliente(txtNombre.getText(), txtRut.getText(), txtCorreo.getText(),
                        txtTelefono.getText(), txtNacionalidad.getText()));
            } catch (RutInvalidoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
                return;
            }

            txtNombre.setText("");
            txtRut.setText("");
            txtCorreo.setText("");
            txtTelefono.setText("");
            txtNacionalidad.setText("");
            actualizarClientes();
            guardar();
            JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente.");
        });

        JPanel norte = new JPanel(new BorderLayout());
        norte.add(form, BorderLayout.CENTER);
        norte.add(btnAgregar, BorderLayout.SOUTH);

        areaClientes = crearArea();
        panel.add(norte, BorderLayout.NORTH);
        panel.add(new JScrollPane(areaClientes), BorderLayout.CENTER);
        actualizarClientes();
        return panel;
    }

    // Listado resumido de clientes
    private void actualizarClientes() {
        StringBuilder sb = new StringBuilder();
        boolean hay = false;
        for (Registrable r : registros) {
            if (r instanceof Cliente) {
                Cliente c = (Cliente) r;
                sb.append("Nombre: ").append(c.getNombre())
                        .append(" | Nacionalidad: ").append(c.getNacionalidad())
                        .append("\n");
                hay = true;
            }
        }
        if (!hay) sb.append("No hay clientes registrados.");
        areaClientes.setText(sb.toString());
    }

    // ══════════════════════════════════════════════════════════════════
    //  GUÍAS
    // ══════════════════════════════════════════════════════════════════
    private JPanel crearPanelGuias() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextField txtNombre = new JTextField();
        JTextField txtRut = new JTextField();
        JTextField txtCorreo = new JTextField();
        JTextField txtTelefono = new JTextField();
        JTextField txtExperiencia = new JTextField();

        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));
        form.add(new JLabel("Nombre:"));
        form.add(txtNombre);
        form.add(new JLabel("RUT:"));
        form.add(txtRut);
        form.add(new JLabel("Correo:"));
        form.add(txtCorreo);
        form.add(new JLabel("Teléfono:"));
        form.add(txtTelefono);
        form.add(new JLabel("Años de experiencia:"));
        form.add(txtExperiencia);

        JButton btnAgregar = new JButton("Agregar guía");
        btnAgregar.addActionListener(e -> {
            if (txtNombre.getText().isBlank() || txtRut.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Nombre y RUT son obligatorios.");
                return;
            }
            int experiencia;
            try {
                experiencia = Integer.parseInt(txtExperiencia.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La experiencia debe ser un número.");
                return;
            }
            try {
                registros.add(new Guia(txtNombre.getText(), txtRut.getText(), txtCorreo.getText(),
                        txtTelefono.getText(), experiencia));
            } catch (RutInvalidoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
                return;
            }
            txtNombre.setText("");
            txtRut.setText("");
            txtCorreo.setText("");
            txtTelefono.setText("");
            txtExperiencia.setText("");
            actualizarGuias();
            guardar();
            JOptionPane.showMessageDialog(this, "Guía agregado exitosamente.");
        });

        JPanel norte = new JPanel(new BorderLayout());
        norte.add(form, BorderLayout.CENTER);
        norte.add(btnAgregar, BorderLayout.SOUTH);

        areaGuias = crearArea();
        panel.add(norte, BorderLayout.NORTH);
        panel.add(new JScrollPane(areaGuias), BorderLayout.CENTER);
        actualizarGuias();
        return panel;
    }

    // Listado resumido de guías
    private void actualizarGuias() {
        StringBuilder sb = new StringBuilder();
        boolean hay = false;
        for (Registrable r : registros) {
            if (r instanceof Guia) {
                Guia g = (Guia) r;
                sb.append("Nombre: ").append(g.getNombre())
                        .append(" | Experiencia: ").append(g.getExperiencia()).append(" años")
                        .append("\n");
                hay = true;
            }
        }
        if (!hay) sb.append("No hay guías registrados.");
        sb.append("   *ATENCIÓN: Todos los guías deben portar credencial\n");

        areaGuias.setText(sb.toString());
    }



    // ══════════════════════════════════════════════════════════════════
    //  TRANSPORTES
    // ══════════════════════════════════════════════════════════════════
    private JPanel crearPanelTransportes() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextField txtTipo = new JTextField();
        JTextField txtCapacidad = new JTextField();
        JTextField txtPatente = new JTextField();

        JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));
        form.add(new JLabel("Tipo de vehículo:"));
        form.add(txtTipo);
        form.add(new JLabel("Capacidad:"));
        form.add(txtCapacidad);
        form.add(new JLabel("Patente:"));
        form.add(txtPatente);

        JButton btnAgregar = new JButton("Agregar transporte");
        btnAgregar.addActionListener(e -> {
            if (txtTipo.getText().isBlank() || txtPatente.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Tipo y patente son obligatorios.");
                return;
            }
            int capacidad;
            try {
                capacidad = Integer.parseInt(txtCapacidad.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La capacidad debe ser un número.");
                return;
            }
            registros.add(new Transporte(txtTipo.getText(), capacidad, txtPatente.getText()));
            txtTipo.setText("");
            txtCapacidad.setText("");
            txtPatente.setText("");
            actualizarTransportes();
            guardar();
            JOptionPane.showMessageDialog(this, "Transporte agregado exitosamente.");
        });

        JPanel norte = new JPanel(new BorderLayout());
        norte.add(form, BorderLayout.CENTER);
        norte.add(btnAgregar, BorderLayout.SOUTH);

        areaTransportes = crearArea();
        panel.add(norte, BorderLayout.NORTH);
        panel.add(new JScrollPane(areaTransportes), BorderLayout.CENTER);
        actualizarTransportes();
        return panel;
    }

    // Listado resumido de transportes
    private void actualizarTransportes() {
        StringBuilder sb = new StringBuilder();
        boolean hay = false;
        for (Registrable r : registros) {
            if (r instanceof Transporte) {
                Transporte t = (Transporte) r;
                sb.append("Tipo: ").append(t.getTipo())
                        .append(" | Patente: ").append(t.getPatente())
                        .append("\n");
                hay = true;
            }
        }
        if (!hay) sb.append("No hay transportes registrados.");
        sb.append("   *ATENCIÓN: El vehículo requiere llenado de combustible\n");

        areaTransportes.setText(sb.toString());
    }


    // ══════════════════════════════════════════════════════════════════
    //  TODOS  (colección polimórfica completa, diferenciada con instanceof)
    // ══════════════════════════════════════════════════════════════════
    private JPanel crearPanelTodos() {
        JPanel panel = new JPanel(new BorderLayout());
        areaTodos = crearArea();

        JButton btnActualizar = new JButton("Actualizar listado completo");
        btnActualizar.addActionListener(e -> actualizarTodos());

        panel.add(new JScrollPane(areaTodos), BorderLayout.CENTER);
        panel.add(btnActualizar, BorderLayout.SOUTH);
        actualizarTodos();
        return panel;
    }

    // Listado con el detalle completo de cada entidad
    private void actualizarTodos() {
        StringBuilder sb = new StringBuilder();
        if (registros.isEmpty()) {
            sb.append("No hay entidades registradas.");
        } else {
            for (Registrable r : registros) {
                if (r instanceof Guia) {
                    Guia g = (Guia) r;
                    sb.append("[GUÍA] ").append(g.getNombre())
                            .append(" | RUT: ").append(g.getRut())
                            .append(" | Correo: ").append(g.getCorreo())
                            .append(" | Tel: ").append(g.getTelefono())
                            .append(" | Experiencia: ").append(g.getExperiencia()).append(" años");

                } else if (r instanceof Cliente) {
                    Cliente c = (Cliente) r;
                    sb.append("[CLIENTE] ").append(c.getNombre())
                            .append(" | RUT: ").append(c.getRut())
                            .append(" | Correo: ").append(c.getCorreo())
                            .append(" | Tel: ").append(c.getTelefono())
                            .append(" | Nacionalidad: ").append(c.getNacionalidad());

                } else if (r instanceof Transporte) {
                    Transporte t = (Transporte) r;
                    sb.append("[TRANSPORTE] ").append(t.getTipo())
                            .append(" | Capacidad: ").append(t.getCapacidad())
                            .append(" | Patente: ").append(t.getPatente());

                } else if (r instanceof RutaGastronomica) {
                    RutaGastronomica rg = (RutaGastronomica) r;
                    sb.append("[RUTA] ").append(rg.getNombre())
                            .append(" | Duración: ").append(rg.getDuracionHoras()).append(" hrs")
                            .append(" | Paradas: ").append(rg.getNumeroDeParadas());

                } else if (r instanceof PaseoLacustre) {
                    PaseoLacustre pl = (PaseoLacustre) r;
                    sb.append("[PASEO] ").append(pl.getNombre())
                            .append(" | Duración: ").append(pl.getDuracionHoras()).append(" hrs")
                            .append(" | Embarcación: ").append(pl.getTipoEmbarcacion());

                } else if (r instanceof ExcursionCultural) {
                    ExcursionCultural ec = (ExcursionCultural) r;
                    sb.append("[EXCURSIÓN] ").append(ec.getNombre())
                            .append(" | Duración: ").append(ec.getDuracionHoras()).append(" hrs")
                            .append(" | Lugar: ").append(ec.getLugarHistorico());
                }
                sb.append("\n");
            }
        }
        areaTodos.setText(sb.toString());
    }
}