
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Properties;
import javax.swing.event.ListSelectionListener;
import controller.FormsController;
import org.jdatepicker.impl.*;

public class Forms extends JFrame {

    private JDatePickerImpl datePicker;
    private JComboBox<String> comboServicio;
    private JTextField txtComentario;
    private JRadioButton leve, medio, grave;
    private JButton btnRegistrar, btnModificar, btnEliminar;
    private JTable tablaDatos;
    private DefaultTableModel modeloTabla;
    private int filaSeleccionada = -1;
    private JPanel panelFormulario;
    private JScrollPane scrollPane;
    private JScrollPane scrollPanePrincipal;
    private FormsController controller; // Referencia al controlador
    private JTextField txtPrecio; // Nuevo campo para el precio

    public Forms() {
        setTitle("Registro de Tipo de Servicio - Vulcanizadora");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Tipo de Servicio", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(titulo, BorderLayout.NORTH);

        // Panel principal para los campos de entrada
        panelFormulario = new JPanel();
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        panelFormulario.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(new JLabel("Fecha del servicio:"), gbc);

        gbc.gridx = 1;
        panelFormulario.add(datePicker = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel(), new Properties()), new DateLabelFormatter()), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(new JLabel("Tipo de servicio:"), gbc);

        gbc.gridx = 1;
        panelFormulario.add(comboServicio = new JComboBox<>(new String[]{"Revisión", "Cambio de llanta", "Parche", "Venta de cámara", "Otros"}), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFormulario.add(new JLabel("Comentario adicional:"), gbc);

        gbc.gridx = 1;
        panelFormulario.add(txtComentario = new JTextField(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelFormulario.add(new JLabel("Nivel de urgencia:"), gbc);

        gbc.gridx = 1;
        JPanel urgenciaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leve = new JRadioButton("Leve");
        medio = new JRadioButton("Medio");
        grave = new JRadioButton("Grave");
        leve.setBackground(Color.YELLOW);
        medio.setBackground(Color.ORANGE);
        grave.setBackground(Color.RED);
        ButtonGroup urgenciaGroup = new ButtonGroup();
        urgenciaGroup.add(leve);
        urgenciaGroup.add(medio);
        urgenciaGroup.add(grave);
        urgenciaPanel.add(leve);
        urgenciaPanel.add(medio);
        urgenciaPanel.add(grave);
        panelFormulario.add(urgenciaPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4; // El precio estará en la fila 4
        panelFormulario.add(new JLabel("Precio:"), gbc);

        gbc.gridx = 1;
        panelFormulario.add(txtPrecio = new JTextField(), gbc);  // Agregamos el campo de texto para el precio

        // Botones de acción
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(new Color(0, 153, 204));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnModificar = new JButton("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.setBackground(new Color(255, 153, 0));
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setFocusPainted(false);
        btnModificar.setFont(new Font("Arial", Font.BOLD, 14));
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.setBackground(new Color(255, 0, 0));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 14));
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);

        gbc.gridx = 1;
        gbc.gridy = 5; // Los botones estarán en la fila 5
        gbc.gridwidth = 2;
        panelFormulario.add(panelBotones, gbc);

        // Tabla para mostrar los datos
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Servicio");
        modeloTabla.addColumn("Comentario");
        modeloTabla.addColumn("Urgencia");
        modeloTabla.addColumn("Precio"); // Agregamos la columna Precio
        tablaDatos = new JTable(modeloTabla);
        scrollPane = new JScrollPane(tablaDatos);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 30));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Agregar formulario y tabla a un panel contenedor
        JPanel contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.add(panelFormulario);
        contenedor.add(scrollPane);

        // Agregar el panel contenedor al scroll principal
        scrollPanePrincipal = new JScrollPane(contenedor);
        scrollPanePrincipal.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPanePrincipal, BorderLayout.CENTER);

        // Hacer la ventana resizable
        setResizable(true);

        // Inicializar el controlador
        controller = new FormsController(this);
        // Asignar action listeners a los botones a través del controlador
        btnRegistrar.addActionListener(controller);
        btnModificar.addActionListener(controller);
        btnEliminar.addActionListener(controller);

        // Manejar selección de fila en la tabla
        tablaDatos.getSelectionModel().addListSelectionListener(controller);

    }

    public JDatePickerImpl getDatePicker() {
        return datePicker;
    }

    public JComboBox<String> getComboServicio() {
        return comboServicio;
    }

    public JTextField getTxtComentario() {
        return txtComentario;
    }

    public JRadioButton getLeve() {
        return leve;
    }

    public JRadioButton getMedio() {
        return medio;
    }

    public JRadioButton getGrave() {
        return grave;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JTable getTablaDatos() {
        return tablaDatos;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public int getFilaSeleccionada() {
        return filaSeleccionada;
    }

    public void setFilaSeleccionada(int filaSeleccionada) {
        this.filaSeleccionada = filaSeleccionada;
    }

    public void setController(FormsController controller) {
        this.controller = controller;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    // Formateador para la fecha
    public static class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
        private final java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("dd/MM/yyyy");

        @Override
        public Object stringToValue(String text) throws java.text.ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws java.text.ParseException {
            if (value != null) {
                java.util.Calendar cal = (java.util.Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }
    }
}