package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;

import logico.Disenador;
import logico.JJDCommunications;
import logico.JefeProyecto;
import logico.Programador;
import logico.Trabajador;


public class RegTrabajador extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField textid;
    private JTextField textnombre;
    private JTextField textdireccion;
    private JTextField textsalario;
    private JTextField textField;
    private JPanel panelprogramador;
    private JPanel panelplanificador;
    private JPanel paneljefeProyecto;
    private JPanel paneldiseniador;
    private JSpinner spinnerTrabajadores;
    private JSpinner spinnerExperiencia;
    private JSpinner spinnerFrecuencia;
    private JTextField textLenguajes;
    private ArrayList<String> lenguajesEspecializados = new ArrayList<>();
    private Programador programador;


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
        setBounds(100, 100, 519, 538);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblid = new JLabel("ID:");
        lblid.setBounds(12, 32, 56, 16);
        contentPanel.add(lblid);

        textid = new JTextField();
        textid.setBounds(12, 48, 265, 22);
        contentPanel.add(textid);
        textid.setColumns(10);

        JLabel lblnombre = new JLabel("Nombre y apellidos:");
        lblnombre.setBounds(12, 83, 127, 16);
        contentPanel.add(lblnombre);

        textnombre = new JTextField();
        textnombre.setBounds(12, 112, 385, 22);
        contentPanel.add(textnombre);
        textnombre.setColumns(10);

        JLabel lbldireccion = new JLabel("Direccion:");
        lbldireccion.setBounds(12, 147, 84, 16);
        contentPanel.add(lbldireccion);

        textdireccion = new JTextField();
        textdireccion.setBounds(12, 165, 453, 22);
        contentPanel.add(textdireccion);
        textdireccion.setColumns(10);

        JLabel lblsexo = new JLabel("Sexo:");
        lblsexo.setBounds(12, 200, 56, 16);
        contentPanel.add(lblsexo);

        JRadioButton rdbtnmasculino = new JRadioButton("Masculino");
        rdbtnmasculino.setBounds(8, 218, 91, 25);
        contentPanel.add(rdbtnmasculino);

        JRadioButton rdbtnfemenino = new JRadioButton("Femenino");
        rdbtnfemenino.setBounds(101, 218, 127, 25);
        contentPanel.add(rdbtnfemenino);

        // ActionListener para los botones de radio
        rdbtnmasculino.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rdbtnmasculino.isSelected()) {
                    rdbtnfemenino.setSelected(false);
                }
            }
        });

        rdbtnfemenino.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rdbtnfemenino.isSelected()) {
                    rdbtnmasculino.setSelected(false);
                }
            }
        });

        JLabel lbledad = new JLabel("Edad:");
        lbledad.setBounds(236, 200, 56, 16);
        contentPanel.add(lbledad);

        JSpinner spinneredad = new JSpinner();
        spinneredad.setBounds(236, 219, 30, 22);
        spinneredad.setModel(new SpinnerNumberModel(1, 1, 100, 1)); // Establecer el mínimo en 1
        contentPanel.add(spinneredad);

        JLabel lblsalario = new JLabel("Salario:");
        lblsalario.setBounds(355, 200, 56, 16);
        contentPanel.add(lblsalario);

        textsalario = new JTextField();
        textsalario.setText("0");
        textsalario.setBounds(329, 219, 116, 22);
        contentPanel.add(textsalario);
        textsalario.setColumns(10);

        textField = new JTextField();
        textField.setEditable(false);
        textField.setText("$");
        textField.setBounds(309, 219, 18, 22);
        contentPanel.add(textField);
        textField.setColumns(10);

        paneljefeProyecto = new JPanel();
        paneljefeProyecto.setBorder(new TitledBorder(null, "Jefe de Proyecto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        paneljefeProyecto.setBounds(12, 348, 467, 95);
        contentPanel.add(paneljefeProyecto);
        paneljefeProyecto.setLayout(null);

        JLabel lblcantTrabajadores = new JLabel("Cant. trabajadores:");
        lblcantTrabajadores.setBounds(39, 25, 154, 16);
        paneljefeProyecto.add(lblcantTrabajadores);

        spinnerTrabajadores = new JSpinner();
        spinnerTrabajadores.setModel(new SpinnerNumberModel(0, 0, 100, 1)); // Valores mínimo, máximo e incremento
        spinnerTrabajadores.setBounds(163, 22, 80, 22);
        paneljefeProyecto.add(spinnerTrabajadores);

        paneldiseniador = new JPanel();
        paneldiseniador.setBorder(new TitledBorder(null, "Diseñador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        paneldiseniador.setBounds(12, 348, 467, 95);
        contentPanel.add(paneldiseniador);
        paneldiseniador.setLayout(null);

        JLabel lblExperiencia = new JLabel("Años de experiencia:");
        lblExperiencia.setBounds(12, 29, 160, 16);
        paneldiseniador.add(lblExperiencia);

        spinnerExperiencia = new JSpinner();
        spinnerExperiencia.setModel(new SpinnerNumberModel(0, 0, 100, 1)); // Valores mínimo, máximo e incremento
        spinnerExperiencia.setBounds(165, 26, 80, 22);
        paneldiseniador.add(spinnerExperiencia);
        
        panelprogramador = new JPanel();
        panelprogramador.setBorder(new TitledBorder(null, "Programador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelprogramador.setBounds(12, 348, 467, 95);
        contentPanel.add(panelprogramador);
        panelprogramador.setLayout(null);
        
        JLabel lblLenguajes = new JLabel("Lenguajes especializados:");
        lblLenguajes.setBounds(12, 29, 160, 16);
        panelprogramador.add(lblLenguajes);
        
        
        textLenguajes = new JTextField();
        textLenguajes.setBounds(165, 26, 202, 22);
        panelprogramador.add(textLenguajes);
        textLenguajes.setColumns(10);

        JButton btnAgregarLenguaje = new JButton("Agregar Lenguaje");
        btnAgregarLenguaje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarLenguajeEspecializado();
            }
        });
        btnAgregarLenguaje.setBounds(165, 56, 150, 25);
        panelprogramador.add(btnAgregarLenguaje);

        panelplanificador = new JPanel();
        panelplanificador.setBorder(new TitledBorder(null, "Planificador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelplanificador.setBounds(12, 348, 467, 95);
        contentPanel.add(panelplanificador);
        panelplanificador.setLayout(null);
        
        JLabel lblFrecuencia = new JLabel("Frecuencia (días):");
        lblFrecuencia.setBounds(12, 29, 160, 16);
        panelplanificador.add(lblFrecuencia);

        spinnerFrecuencia = new JSpinner();
        spinnerFrecuencia.setModel(new SpinnerNumberModel(1, 1, 365, 1)); // Valores mínimo, máximo e incremento
        spinnerFrecuencia.setBounds(165, 26, 80, 22);
        panelplanificador.add(spinnerFrecuencia);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Tipo Trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(12, 278, 477, 60);
        contentPanel.add(panel);
        panel.setLayout(null);

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"Jefe de proyecto", "Diseñador", "Programador", "Planificador"}));
        comboBox.setMaximumRowCount(4);
        comboBox.setEditable(true);
        comboBox.setBounds(12, 24, 183, 22);
        panel.add(comboBox);

        // ActionListener para el JComboBox
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBox.getSelectedItem();

                // Mostrar u ocultar los paneles según el tipo de trabajador seleccionado
                switch (selectedItem) {
                    case "Jefe de proyecto":
                        mostrarPanel(paneljefeProyecto);
                        ocultarPaneles(paneljefeProyecto);
                        break;
                    case "Diseñador":
                        mostrarPanel(paneldiseniador);
                        ocultarPaneles(paneldiseniador);
                        break;
                    case "Programador":
                        mostrarPanel(panelprogramador);
                        ocultarPaneles(panelprogramador);
                        break;
                    case "Planificador":
                        mostrarPanel(panelplanificador);
                        ocultarPaneles(panelplanificador);
                        break;
                    default:
                        break;
                }
            }
        });

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        {
            JButton okButton = new JButton("Registrar");
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Obtener los datos del trabajador desde los campos de texto u otros componentes
                    String id = textid.getText();
                    String nombre = textnombre.getText();
                    String direccion = textdireccion.getText();
                    String sexo = "";
                    if (rdbtnmasculino.isSelected()) {
                        sexo = "Masculino";
                    } else if (rdbtnfemenino.isSelected()) {
                        sexo = "Femenino";
                    }
                    int edad = (int) spinneredad.getValue();
                    double salario = Double.parseDouble(textsalario.getText());
                    
                    // Crear una instancia del tipo de trabajador correspondiente
                    Trabajador nuevoTrabajador = null;
                    String selectedItem = (String) comboBox.getSelectedItem();
                    switch (selectedItem) {
                        case "Jefe de proyecto":
                            int cantTrabajadores = (int) spinnerTrabajadores.getValue();
                            nuevoTrabajador = new JefeProyecto(id, nombre, direccion, sexo, edad, salario, cantTrabajadores);
                            break;
                        case "Diseñador":
                            int experiencia = (int) spinnerExperiencia.getValue();
                            nuevoTrabajador = new Disenador(id, nombre, direccion, sexo, edad, salario,evaluacion, experiencia);
                            break;
                        case "Programador":
                            ArrayList<String> lenguajesEspecializados = lenguajesEspecializados;
                            nuevoTrabajador = new Programador(id, nombre, direccion, sexo, edad, salario, lenguajesEspecializados);
                            break;
                        case "Planificador":
                            int frecuencia = (int) spinnerFrecuencia.getValue();
                            nuevoTrabajador = new Planificador(id, nombre, direccion, sexo, edad, salario, frecuencia);
                            break;
                        default:
                            break;
                    }
                    
                    // Insertar el trabajador en una variable auxiliar
                    JJDCommunications.getInstance().insertarTrabajadorAuxiliar(nuevoTrabajador);
                    
                    // Mostrar un mensaje de éxito al usuario
                    JOptionPane.showMessageDialog(RegTrabajador.this, "Trabajador registrado exitosamente.");
                    
                    // Limpiar los campos de texto u otros componentes si es necesario
                    // textid.setText("");
                    // textnombre.setText("");
                    // textdireccion.setText("");
                    // Limpiar los demás campos según sea necesario
                }
            });
            okButton.setActionCommand("OK");
            buttonPane.add(okButton);
            getRootPane().setDefaultButton(okButton);
        }

        {
            JButton cancelButton = new JButton("Cancelar");
            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            cancelButton.setActionCommand("Cancel");
            buttonPane.add(cancelButton);
        }
    }

    // Metodo para mostrar el panel seleccionado y ocultar los demas
    private void mostrarPanel(JPanel panel) {
        panel.setVisible(true);
    }

    private void ocultarPaneles(JPanel panelActual) {
        JPanel[] paneles = {paneljefeProyecto, paneldiseniador, panelprogramador, panelplanificador};
        for (JPanel pnl : paneles) {
            if (pnl != panelActual) {
                pnl.setVisible(false);
            }
        }
    }
    
 // Método para agregar el lenguaje especializado al ArrayList y al Programador
    private void agregarLenguajeEspecializado() {
        String lenguaje = textLenguajes.getText();
        if (!lenguaje.isEmpty()) {
            lenguajesEspecializados.add(lenguaje);
            JOptionPane.showMessageDialog(this, "Lenguaje agregado exitosamente: " + lenguaje);
            // Agregar el lenguaje también al objeto Programador si existe
            if (programador != null) {
                programador.agregarLenguajeEspecializado(lenguaje);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un lenguaje especializado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
