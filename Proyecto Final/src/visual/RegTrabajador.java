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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class RegTrabajador extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JPanel panelJefeProyecto, panelDiseniador, panelProgramador, panelPlanificador;
    private JTextField textid;
    private JTextField textnombre;
    private JTextField textApellido;
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
 // Ejemplo de c�mo cargar datos al iniciar la aplicaci�n
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegTrabajador dialog = new RegTrabajador();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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

        // Titulo en negrita centrado
        JLabel lblTitle = new JLabel("REGISTRO DE TRABAJADOR");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(12, 10, 479, 30);
        contentPanel.add(lblTitle);

        // Agrupacion de campos relacionados
        agruparCampos();

        // Botones de accion
        configurarBotones();

        // Paneles de trabajadores
        configurarPaneles();

        // JComboBox para seleccionar el tipo de trabajador
        configurarComboBox();
    }

    // Metodo para agrupar los campos relacionados en paneles separados
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
        JLabel lblnombre = new JLabel("Nombre:");
        lblnombre.setBounds(12, 70, 127, 16);
        datosPersonalesPanel.add(lblnombre);
        textnombre = new JTextField();
        textnombre.setBounds(12, 90, 193, 22);
        datosPersonalesPanel.add(textnombre);
        textnombre.setColumns(10);
        
     // Apellido
        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(229, 70, 84, 16);
        datosPersonalesPanel.add(lblApellido);
        textApellido = new JTextField();
        textApellido.setBounds(229, 90, 205, 22);
        datosPersonalesPanel.add(textApellido);
        textApellido.setColumns(10);

        // Direccion
        JLabel lbldireccion = new JLabel("Direccion:");
        lbldireccion.setBounds(12, 115, 84, 16);
        datosPersonalesPanel.add(lbldireccion);
        textdireccion = new JTextField();
        textdireccion.setBounds(12, 135, 422, 22);
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
        rdbtnfemenino.setBounds(98, 190, 89, 25);
        datosPersonalesPanel.add(rdbtnfemenino);
        group.add(rdbtnfemenino);

        // Edad
        JLabel lbledad = new JLabel("Edad:");
        lbledad.setBounds(208, 172, 56, 16);
        datosPersonalesPanel.add(lbledad);
        SpinnerNumberModel modeloEdad = new SpinnerNumberModel(1, 1, null, 1);
        spinneredad = new JSpinner(modeloEdad);
        spinneredad.setBounds(208, 193, 56, 22);
        datosPersonalesPanel.add(spinneredad);

        // Salario
        JLabel lblsalario = new JLabel("Salario:");
        lblsalario.setBounds(302, 172, 56, 16);
        datosPersonalesPanel.add(lblsalario);

        textsalario = new JTextField();
        textsalario.setText("0");
        textsalario.setBounds(312, 193, 122, 22);
        datosPersonalesPanel.add(textsalario);
        textsalario.setColumns(10);

        JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setText("$");
        textField.setBounds(295, 193, 18, 22);
        datosPersonalesPanel.add(textField);
        textField.setColumns(10);
    }

    // Metodo para configurar los botones de accion
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
                    JOptionPane.showMessageDialog(null, "Error: Ingrese un valor numerico para el salario.", "Error", JOptionPane.ERROR_MESSAGE);
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

        // Crear y configurar el panel para Dise�ador
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
        spinnerTrabajadores.setModel(new SpinnerNumberModel(0, 0, 100, 1)); // Valores minimo, maximo e incremento
        spinnerTrabajadores.setBounds(163, 22, 80, 22);
        panelJefeProyecto.add(spinnerTrabajadores);
    }

    private void configurarPanelDiseniador() {
        // Crear panel para Dise�ador
        panelDiseniador = new JPanel();
        panelDiseniador.setBorder(new TitledBorder(null, "Dise�ador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelDiseniador.setBounds(12, 348, 467, 95);
        contentPanel.add(panelDiseniador);
        panelDiseniador.setLayout(null);

        // Etiqueta y Spinner para a�os de experiencia
        JLabel lblExperiencia = new JLabel("A�os de experiencia:");
        lblExperiencia.setBounds(12, 29, 160, 16);
        panelDiseniador.add(lblExperiencia);

        spinnerExperiencia = new JSpinner();
        spinnerExperiencia.setModel(new SpinnerNumberModel(0, 0, 100, 1)); // Valores minimo, maximo e incremento
        spinnerExperiencia.setBounds(165, 26, 80, 22);
        panelDiseniador.add(spinnerExperiencia);
    }

    private void configurarPanelProgramador() {
        // Crear panel para Programador
        panelProgramador = new JPanel();
        panelProgramador.setBorder(new TitledBorder(null, "Programador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelProgramador.setBounds(12, 348, 467, 150); // Aumente la altura del panel
        contentPanel.add(panelProgramador);
        panelProgramador.setLayout(new BorderLayout(0, 0));
        panelProgramador.setVisible(false); // Ocultar el panel inicialmente

        // Create a table model to store the languages
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Lenguaje de Programacion");
        table = new JTable(tableModel); // CORREGIDO: asignar la tabla a la variable de clase
        JScrollPane scrollPane = new JScrollPane(table);
        panelProgramador.add(scrollPane, BorderLayout.CENTER);

        // Create a panel for text field and buttons
        JPanel inputPanel = new JPanel(new FlowLayout());
        JTextField textNuevoLenguaje = new JTextField(15);
        JButton btnAnadirLenguaje = new JButton("A�adir");
        JButton btnEliminarLenguaje = new JButton("Eliminar");
        inputPanel.add(textNuevoLenguaje);
        inputPanel.add(btnAnadirLenguaje);
        inputPanel.add(btnEliminarLenguaje);
        panelProgramador.add(inputPanel, BorderLayout.SOUTH);

        // Add ActionListener to the "A�adir" button
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
        JLabel lblFrecuencia = new JLabel("Frecuencia (dias):");
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
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccione un tipo de trabajador", "Jefe de proyecto", "Dise�ador", "Programador", "Planificador"})); // Cambie la opcion predeterminada
        comboBox.setMaximumRowCount(5); // Aumente el recuento maximo para incluir la opcion vacia
        comboBox.setBounds(12, 24, 218, 22);
        panel.add(comboBox);

        // Ocultar todos los paneles al iniciar la interfaz
        ocultarPaneles(null);

        // ActionListener para el JComboBox
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBox.getSelectedItem();

                // Mostrar u ocultar los paneles segun el tipo de trabajador seleccionado
                switch (selectedItem) {
                    case "Jefe de proyecto":
                        mostrarPanel(panelJefeProyecto);
                        ocultarPaneles(panelJefeProyecto);
                        break;
                    case "Dise�ador":
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
                        // Si se selecciona la opcion predeterminada, ocultar todos los paneles
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

    // Metodo para mostrar el panel seleccionado y ocultar los demas
    private void mostrarPanel(JPanel panel) {
        panel.setVisible(true);
    }

    
private void registrarTrabajador() {
    // Obtener los valores ingresados por el usuario
    String id = textid.getText();
    String nombre = textnombre.getText();
    String apellido = textApellido.getText(); // Obtener el valor del campo de apellido
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
        JOptionPane.showMessageDialog(this, "Por favor, ingrese un salario valido.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    String evaluacion = "Cumplidor"; // Por defecto, puedes cambiarlo segun tu logica

    // Validar campos obligatorios
    if (id.isEmpty() || nombre.isEmpty() || direccion.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar longitud de identificaci�n
    if (id.length() != 11) {
        JOptionPane.showMessageDialog(this, "El n�mero de identificaci�n debe tener 11 d�gitos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar que el n�mero de identificaci�n no se repita entre los trabajadores
    if (JJDCommunications.getInstance().existeTrabajadorConIdentificacion(id)) {
        JOptionPane.showMessageDialog(this, "El n�mero de identificaci�n ya existe para otro trabajador.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar edad dentro del rango v�lido
    if (edad < 18 || edad > 65) {
        JOptionPane.showMessageDialog(this, "La edad debe estar entre 18 y 65 a�os.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar que el salario sea positivo
    if (salarioHora <= 0) {
        JOptionPane.showMessageDialog(this, "El salario debe ser un valor num�rico positivo.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar campos especificos para cada tipo de trabajador
    String selectedItem = (String) comboBox.getSelectedItem();
    switch (selectedItem) {
        case "Jefe de proyecto":
            // Validar campos especificos del panelJefeProyecto
            int cantidadTrabajadores = (int) spinnerTrabajadores.getValue();
            if (cantidadTrabajadores < 0) {
                JOptionPane.showMessageDialog(this, "La cantidad de trabajadores debe ser mayor o igual a cero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            break;
        case "Dise�ador":
            // Validar campos especificos del panelDiseniador
            int experiencia = (int) spinnerExperiencia.getValue();
            if (experiencia < 0) {
                JOptionPane.showMessageDialog(this, "La experiencia debe ser mayor o igual a cero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            break;
        case "Programador":
            // Validar campos especificos del panelProgramador
            if (table.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese al menos un lenguaje de programaci�n.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            break;
        case "Planificador":
            // No se requieren validaciones adicionales para el panelPlanificador
            break;
        default:
            // Si no se selecciona un tipo de trabajador valido, mostrar un mensaje de error
            JOptionPane.showMessageDialog(this, "Seleccione un tipo de trabajador valido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
    }

    // Crear el objeto del trabajador
    Trabajador nuevoTrabajador = crearTrabajador(selectedItem, id, nombre, apellido, direccion, sexo, edad, salarioHora, evaluacion);

    // Insertar el trabajador a traves de la instancia de JJDCommunications
    JJDCommunications.getInstance().insertarTrabajador(nuevoTrabajador);

    // Mostrar mensaje de registro completado
    JOptionPane.showMessageDialog(this, "Trabajador registrado exitosamente.", "Registro completado", JOptionPane.INFORMATION_MESSAGE);

    // Limpiar los campos de texto despues de agregar el trabajador
    limpiarCampos();
}

    
    private Trabajador crearTrabajador(String tipo, String id, String nombre, String apellido, String direccion, char sexo, int edad, double salarioHora, String evaluacion) {
        switch (tipo) {
            case "Jefe de proyecto":
                return new JefeProyecto(id, nombre, apellido, direccion, sexo, edad, salarioHora, evaluacion, (int) spinnerTrabajadores.getValue());
            case "Dise�ador":
                return new Disenador(id, nombre, apellido, direccion, sexo, edad, salarioHora, evaluacion, (int) spinnerExperiencia.getValue());
            case "Programador":
                // Crear un ArrayList para almacenar los lenguajes especializados
                ArrayList<String> lenguajesEspecializados = new ArrayList<>();
                // Obtener los lenguajes desde la tabla
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    lenguajesEspecializados.add((String) tableModel.getValueAt(i, 0));
                }
                // Crear el objeto Programador con los datos ingresados
                return new Programador(id, nombre, apellido, direccion, sexo, edad, salarioHora, evaluacion, lenguajesEspecializados);
            case "Planificador":
                return new Planificador(id, nombre, apellido, direccion, sexo, edad, salarioHora, evaluacion, (int) spinnerFrecuencia.getValue());
            default:
                return null;
        }
    }

    private void limpiarCampos() {
        // Limpiar los campos de texto despues de agregar el trabajador
        textid.setText("");
        textnombre.setText("");
        textApellido.setText("");
        textdireccion.setText("");
        rdbtnmasculino.setSelected(false);
        rdbtnfemenino.setSelected(false);
        spinneredad.setValue(0); // Otra opcion seria establecer un valor por defecto
        textsalario.setText("0");

        // Limpiar campos especificos para cada tipo de trabajador
        limpiarCamposTipoTrabajador();
    }

    private void limpiarCamposTipoTrabajador() {
        String selectedItem = (String) comboBox.getSelectedItem();

        switch (selectedItem) {
            case "Jefe de proyecto":
                // Limpiar campos especificos del panelJefeProyecto
                spinnerTrabajadores.setValue(0);
                break;
            case "Dise�ador":
                // Limpiar campos especificos del panelDiseniador
                spinnerExperiencia.setValue(0);
                break;
            case "Programador":
                // Limpiar campos especificos del panelProgramador
                limpiarCamposProgramador();
                break;
            case "Planificador":
                // Limpiar campos especificos del panelPlanificador
                spinnerFrecuencia.setValue(1); // Otra opcion seria establecer un valor por defecto
                break;
            default:
                break;
        }
     // Establecer el valor mínimo del spinner de edad en 1
        SpinnerNumberModel modeloEdad = new SpinnerNumberModel(1, 1, null, 1);
        spinneredad.setModel(modeloEdad);

        // Establecer el valor del spinner de edad en 1
        spinneredad.setValue(1);
    }

    private void limpiarCamposProgramador() {
        // Limpiar la tabla de lenguajes de programacion
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
    }
}