package visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Disenador;
import logico.JJDCommunications;
import logico.JefeProyecto;
import logico.Planificador;
import logico.Programador;
import logico.Trabajador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegTrabajador extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JPanel panelJefeProyecto, panelDiseniador, panelProgramador, panelPlanificador;
    private JTextField textid;
    private JTextField textnombre;
    private JTextField textdireccion;
    private JTextField textsalario;
    private JSpinner spinnerTrabajadores;
    private JSpinner spinnerExperiencia;
    private JSpinner spinnerFrecuencia;
    private JSpinner spinneredad;
    private JRadioButton rdbtnmasculino;
    private JRadioButton rdbtnfemenino;
    private JComboBox<String> comboBox;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            RegTrabajador dialog = new RegTrabajador();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public RegTrabajador() {
        setTitle("Registro De Trabajador");
        setBounds(100, 100, 519, 580);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        // Título en negrita centrado
        JLabel lblTitle = new JLabel("REGISTRO DE TRABAJADOR");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(12, 10, 479, 30);
        contentPanel.add(lblTitle);

        // Agrupación de campos relacionados
        agruparCampos();

        // Botones de acción
        configurarBotones();

        // Paneles de trabajadores
        configurarPaneles();

        // JComboBox para seleccionar el tipo de trabajador
        configurarComboBox();
    }

    // Método para agrupar los campos relacionados en paneles separados
    private void agruparCampos() {
        JPanel datosPersonalesPanel = new JPanel();
        datosPersonalesPanel.setBorder(new TitledBorder(null, "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        datosPersonalesPanel.setBounds(12, 50, 479, 232);
        contentPanel.add(datosPersonalesPanel);
        datosPersonalesPanel.setLayout(null);

        // ID
        JLabel lblid = new JLabel("ID:");
        lblid.setBounds(12, 25, 56, 16);
        datosPersonalesPanel.add(lblid);
        textid = new JTextField();
        textid.setBounds(12, 45, 127, 22);
        datosPersonalesPanel.add(textid);
        textid.setColumns(10);

        // Nombre
        JLabel lblnombre = new JLabel("Nombre Completo:");
        lblnombre.setBounds(12, 70, 127, 16);
        datosPersonalesPanel.add(lblnombre);
        textnombre = new JTextField();
        textnombre.setBounds(12, 90, 385, 22);
        datosPersonalesPanel.add(textnombre);
        textnombre.setColumns(10);

        // Dirección
        JLabel lbldireccion = new JLabel("Dirección:");
        lbldireccion.setBounds(12, 115, 84, 16);
        datosPersonalesPanel.add(lbldireccion);
        textdireccion = new JTextField();
        textdireccion.setBounds(12, 135, 385, 22);
        datosPersonalesPanel.add(textdireccion);
        textdireccion.setColumns(10);

        // Sexo
        JLabel lblsexo = new JLabel("Sexo:");
        lblsexo.setBounds(18, 172, 56, 16);
        datosPersonalesPanel.add(lblsexo);

        ButtonGroup group = new ButtonGroup(); // Agrupar los botones de radio
        rdbtnmasculino = new JRadioButton("Masculino");
        rdbtnmasculino.setBounds(12, 190, 89, 25);
        datosPersonalesPanel.add(rdbtnmasculino);
        group.add(rdbtnmasculino);

        rdbtnfemenino = new JRadioButton("Femenino");
        rdbtnfemenino.setBounds(103, 190, 89, 25);
        datosPersonalesPanel.add(rdbtnfemenino);
        group.add(rdbtnfemenino);

        // Edad
        JLabel lbledad = new JLabel("Edad:");
        lbledad.setBounds(198, 173, 56, 16);
        datosPersonalesPanel.add(lbledad);
        spinneredad = new JSpinner(); // Inicializa la variable de instancia spinneredad
        spinneredad.setBounds(198, 193, 56, 22);
        datosPersonalesPanel.add(spinneredad);

        // Salario
        JLabel lblsalario = new JLabel("Salario:");
        lblsalario.setBounds(302, 172, 56, 16);
        datosPersonalesPanel.add(lblsalario);

        textsalario = new JTextField();
        textsalario.setText("0");
        textsalario.setBounds(312, 193, 116, 22);
        datosPersonalesPanel.add(textsalario);
        textsalario.setColumns(10);

        JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setText("$");
        textField.setBounds(295, 193, 18, 22);
        datosPersonalesPanel.add(textField);
        textField.setColumns(10);
    }

    // Método para configurar los botones de acción
    private void configurarBotones() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("Registrar");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    registrarTrabajador();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Ingrese un valor numérico para el salario.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }

    private void configurarPaneles() {
        // Crear y configurar el panel para Jefe de Proyecto
        configurarPanelJefeProyecto();

        // Crear y configurar el panel para Diseñador
        configurarPanelDiseniador();

        // Crear y configurar el panel para Programador
        configurarPanelProgramador();

        // Crear y configurar el panel para Planificador
        configurarPanelPlanificador();
    }

    private void configurarPanelJefeProyecto() {
        // Crear panel para Jefe de Proyecto
        panelJefeProyecto = new JPanel();
        panelJefeProyecto.setBorder(new TitledBorder(null, "Jefe de Proyecto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelJefeProyecto.setBounds(12, 348, 467, 95);
        contentPanel.add(panelJefeProyecto);
        panelJefeProyecto.setLayout(null);

        // Etiqueta y Spinner para cantidad de trabajadores
        JLabel lblCantTrabajadores = new JLabel("Cant. trabajadores:");
        lblCantTrabajadores.setBounds(39, 25, 154, 16);
        panelJefeProyecto.add(lblCantTrabajadores);

        spinnerTrabajadores = new JSpinner();
        spinnerTrabajadores.setModel(new SpinnerNumberModel(0, 0, 100, 1)); // Valores mínimo, máximo e incremento
        spinnerTrabajadores.setBounds(163, 22, 80, 22);
        panelJefeProyecto.add(spinnerTrabajadores);
    }

    private void configurarPanelDiseniador() {
        // Crear panel para Diseñador
        panelDiseniador = new JPanel();
        panelDiseniador.setBorder(new TitledBorder(null, "Diseñador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelDiseniador.setBounds(12, 348, 467, 95);
        contentPanel.add(panelDiseniador);
        panelDiseniador.setLayout(null);

        // Etiqueta y Spinner para años de experiencia
        JLabel lblExperiencia = new JLabel("Años de experiencia:");
        lblExperiencia.setBounds(12, 29, 160, 16);
        panelDiseniador.add(lblExperiencia);

        spinnerExperiencia = new JSpinner();
        spinnerExperiencia.setModel(new SpinnerNumberModel(0, 0, 100, 1)); // Valores mínimo, máximo e incremento
        spinnerExperiencia.setBounds(165, 26, 80, 22);
        panelDiseniador.add(spinnerExperiencia);
    }

    private void configurarPanelProgramador() {
        // Crear panel para Programador
        panelProgramador = new JPanel();
        panelProgramador.setBorder(new TitledBorder(null, "Programador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelProgramador.setBounds(12, 348, 467, 150); // Aumenté la altura del panel
        contentPanel.add(panelProgramador);
        panelProgramador.setLayout(new BorderLayout(0, 0));
        panelProgramador.setVisible(false); // Ocultar el panel inicialmente

        // Create a table model to store the languages
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Lenguaje de Programación");
        table = new JTable(tableModel); // CORREGIDO: asignar la tabla a la variable de clase
        JScrollPane scrollPane = new JScrollPane(table);
        panelProgramador.add(scrollPane, BorderLayout.CENTER);

        // Create a panel for text field and buttons
        JPanel inputPanel = new JPanel(new FlowLayout());
        JTextField textNuevoLenguaje = new JTextField(15);
        JButton btnAnadirLenguaje = new JButton("Añadir");
        JButton btnEliminarLenguaje = new JButton("Eliminar");
        inputPanel.add(textNuevoLenguaje);
        inputPanel.add(btnAnadirLenguaje);
        inputPanel.add(btnEliminarLenguaje);
        panelProgramador.add(inputPanel, BorderLayout.SOUTH);

        // Add ActionListener to the "Añadir" button
        btnAnadirLenguaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevoLenguaje = textNuevoLenguaje.getText();
                if (!nuevoLenguaje.isEmpty()) {
                    tableModel.addRow(new Object[]{nuevoLenguaje}); // Add new language to the table
                    textNuevoLenguaje.setText(""); // Clear the text field after adding
                }
            }
        });

        // Add ActionListener to the "Eliminar" button
        btnEliminarLenguaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.removeRow(selectedRow); // Remove selected row from the table
                }
            }
        });
    }

    private void configurarPanelPlanificador() {
        // Create the panel for Planner
        panelPlanificador = new JPanel();
        panelPlanificador.setBorder(new TitledBorder(null, "Planificador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelPlanificador.setBounds(12, 348, 467, 95);
        contentPanel.add(panelPlanificador);
        panelPlanificador.setLayout(null);

        // Label for frequency
        JLabel lblFrecuencia = new JLabel("Frecuencia (días):");
        lblFrecuencia.setBounds(12, 29, 160, 16);
        panelPlanificador.add(lblFrecuencia);

        // Spinner to set frequency in days
        spinnerFrecuencia = new JSpinner();
        spinnerFrecuencia.setModel(new SpinnerNumberModel(1, 1, 365, 1)); // Set minimum, maximum, and increment values
        spinnerFrecuencia.setBounds(165, 26, 80, 22);
        panelPlanificador.add(spinnerFrecuencia);
    }

    private void configurarComboBox() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Tipo De Trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(12, 290, 477, 60);
        contentPanel.add(panel);
        panel.setLayout(null);

        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccione un tipo de trabajador", "Jefe de proyecto", "Diseñador", "Programador", "Planificador"})); // Cambié la opción predeterminada
        comboBox.setMaximumRowCount(5); // Aumenté el recuento máximo para incluir la opción vacía
        comboBox.setBounds(12, 24, 218, 22);
        panel.add(comboBox);

        // Ocultar todos los paneles al iniciar la interfaz
        ocultarPaneles(null);

        // ActionListener para el JComboBox
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBox.getSelectedItem();

                // Mostrar u ocultar los paneles según el tipo de trabajador seleccionado
                switch (selectedItem) {
                    case "Jefe de proyecto":
                        mostrarPanel(panelJefeProyecto);
                        ocultarPaneles(panelJefeProyecto);
                        break;
                    case "Diseñador":
                        mostrarPanel(panelDiseniador);
                        ocultarPaneles(panelDiseniador);
                        break;
                    case "Programador":
                        mostrarPanel(panelProgramador);
                        ocultarPaneles(panelProgramador);
                        break;
                    case "Planificador":
                        mostrarPanel(panelPlanificador);
                        ocultarPaneles(panelPlanificador);
                        break;
                    default:
                        // Si se selecciona la opción predeterminada, ocultar todos los paneles
                        ocultarPaneles(null);
                        break;
                }
            }
        });
    }

    private void ocultarPaneles(JPanel panelActual) {
        JPanel[] paneles = {panelJefeProyecto, panelDiseniador, panelProgramador, panelPlanificador};
        for (JPanel panel : paneles) {
            if (panel != panelActual && panel != null) {  // Verificar si es diferente de panelActual y si no es nulo
                panel.setVisible(false);
            }
        }
    }

    // Método para mostrar el panel seleccionado y ocultar los demás
    private void mostrarPanel(JPanel panel) {
        panel.setVisible(true);
    }

    private void registrarTrabajador() {
        // Obtener los valores ingresados por el usuario
        String id = textid.getText();
        String nombre = textnombre.getText();
        String direccion = textdireccion.getText();
        char sexo;
        if (rdbtnmasculino.isSelected()) {
            sexo = 'M';
        } else if (rdbtnfemenino.isSelected()) {
            sexo = 'F';
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un sexo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int edad = (int) spinneredad.getValue();
        double salarioHora = 0;
        try {
            salarioHora = Double.parseDouble(textsalario.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un salario válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String evaluacion = "Cumplidor"; // Por defecto, puedes cambiarlo según tu lógica

        // Validar campos obligatorios
        if (id.isEmpty() || nombre.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar campos específicos para cada tipo de trabajador
        String selectedItem = (String) comboBox.getSelectedItem();
        switch (selectedItem) {
            case "Jefe de proyecto":
                // Validar campos específicos del panelJefeProyecto
                int cantidadTrabajadores = (int) spinnerTrabajadores.getValue();
                if (cantidadTrabajadores < 0) {
                    JOptionPane.showMessageDialog(this, "La cantidad de trabajadores debe ser mayor o igual a cero.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;
            case "Diseñador":
                // Validar campos específicos del panelDiseniador
                int experiencia = (int) spinnerExperiencia.getValue();
                if (experiencia < 0) {
                    JOptionPane.showMessageDialog(this, "La experiencia debe ser mayor o igual a cero.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;
            case "Programador":
                // Validar campos específicos del panelProgramador
                if (table.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese al menos un lenguaje de programación.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;
            case "Planificador":
                // No se requieren validaciones adicionales para el panelPlanificador
                break;
            default:
                // Si no se selecciona un tipo de trabajador válido, mostrar un mensaje de error
                JOptionPane.showMessageDialog(this, "Seleccione un tipo de trabajador válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        // Crear el objeto del trabajador
        Trabajador nuevoTrabajador = crearTrabajador(selectedItem, id, nombre, direccion, sexo, edad, salarioHora, evaluacion);

        // Insertar el trabajador a través de la instancia de JJDCommunications
        JJDCommunications.getInstance().insertarTrabajador(nuevoTrabajador);

        // Mostrar mensaje de registro completado
        JOptionPane.showMessageDialog(this, "Trabajador registrado exitosamente.", "Registro completado", JOptionPane.INFORMATION_MESSAGE);

        // Limpiar los campos de texto después de agregar el trabajador
        limpiarCampos();
    }

    private Trabajador crearTrabajador(String tipo, String id, String nombre, String direccion, char sexo, int edad, double salarioHora, String evaluacion) {
        switch (tipo) {
            case "Jefe de proyecto":
                return new JefeProyecto(id, nombre, direccion, sexo, edad, salarioHora, evaluacion, (int) spinnerTrabajadores.getValue());
            case "Diseñador":
                return new Disenador(id, nombre, direccion, sexo, edad, salarioHora, evaluacion, (int) spinnerExperiencia.getValue());
            case "Programador":
                // Crear un ArrayList para almacenar los lenguajes especializados
                ArrayList<String> lenguajesEspecializados = new ArrayList<>();
                // Obtener los lenguajes desde la tabla
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    lenguajesEspecializados.add((String) tableModel.getValueAt(i, 0));
                }
                // Crear el objeto Programador con los datos ingresados
                return new Programador(id, nombre, direccion, sexo, edad, salarioHora, evaluacion, lenguajesEspecializados);
            case "Planificador":
                return new Planificador(id, nombre, direccion, sexo, edad, salarioHora, evaluacion, (int) spinnerFrecuencia.getValue());
            default:
                return null;
        }
    }

    private void limpiarCampos() {
        // Limpiar los campos de texto después de agregar el trabajador
        textid.setText("");
        textnombre.setText("");
        textdireccion.setText("");
        rdbtnmasculino.setSelected(false);
        rdbtnfemenino.setSelected(false);
        spinneredad.setValue(0); // Otra opción sería establecer un valor por defecto
        textsalario.setText("0");

        // Limpiar campos específicos para cada tipo de trabajador
        limpiarCamposTipoTrabajador();
    }

    private void limpiarCamposTipoTrabajador() {
        String selectedItem = (String) comboBox.getSelectedItem();

        switch (selectedItem) {
            case "Jefe de proyecto":
                // Limpiar campos específicos del panelJefeProyecto
                spinnerTrabajadores.setValue(0);
                break;
            case "Diseñador":
                // Limpiar campos específicos del panelDiseniador
                spinnerExperiencia.setValue(0);
                break;
            case "Programador":
                // Limpiar campos específicos del panelProgramador
                limpiarCamposProgramador();
                break;
            case "Planificador":
                // Limpiar campos específicos del panelPlanificador
                spinnerFrecuencia.setValue(1); // Otra opción sería establecer un valor por defecto
                break;
            default:
                break;
        }
    }

    private void limpiarCamposProgramador() {
        // Limpiar la tabla de lenguajes de programación
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
    }
}
