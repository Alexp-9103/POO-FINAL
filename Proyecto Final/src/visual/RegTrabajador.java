package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;

public class RegTrabajador extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField textid;
    private JTextField textnombre;
    private JTextField textdireccion;
    private JTextField textsalario;
    private JTextField textField;

    private JPanel paneljefeProyecto;
    private JPanel paneldiseniador;
    private JPanel panelprogramador;
    private JPanel panelplanificador;

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

        // Crear paneles para cada tipo de trabajador
        paneljefeProyecto = new JPanel();
        paneljefeProyecto.setBorder(new TitledBorder(null, "Jefe de Proyecto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        paneljefeProyecto.setBounds(12, 348, 467, 95);
        contentPanel.add(paneljefeProyecto);
        paneljefeProyecto.setLayout(null);
        
        JLabel lblcantTrabajadores = new JLabel("Cant. trabajadores:");
        lblcantTrabajadores.setBounds(39, 25, 154, 16);
        paneljefeProyecto.add(lblcantTrabajadores);
        
        JSpinner spinner = new JSpinner();
        spinner.setBounds(163, 22, 30, 22);
        paneljefeProyecto.add(spinner);

        paneldiseniador = new JPanel();
        paneldiseniador.setBorder(new TitledBorder(null, "Dise�ador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        paneldiseniador.setBounds(12, 348, 467, 95);
        contentPanel.add(paneldiseniador);
        paneldiseniador.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Anios de Experiencia:");
        lblNewLabel.setBounds(166, 26, 129, 16);
        paneldiseniador.add(lblNewLabel);
        
        JSpinner spinner_1 = new JSpinner();
        spinner_1.setBounds(293, 23, 30, 22);
        paneldiseniador.add(spinner_1);

        panelprogramador = new JPanel();
        panelprogramador.setBorder(new TitledBorder(null, "Programador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelprogramador.setBounds(12, 348, 467, 95);
        contentPanel.add(panelprogramador);
        panelprogramador.setLayout(null);

        panelplanificador = new JPanel();
        panelplanificador.setBorder(new TitledBorder(null, "Planificador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelplanificador.setBounds(12, 348, 467, 95);
        contentPanel.add(panelplanificador);
        panelplanificador.setLayout(null);

        JLabel lblid = new JLabel("ID:");
        lblid.setBounds(12, 32, 56, 16);
        contentPanel.add(lblid);

        textid = new JTextField();
        textid.setBounds(12, 48, 265, 22);
        contentPanel.add(textid);
        textid.setColumns(10);

        JLabel lblnombre = new JLabel("Nombre:");
        lblnombre.setBounds(12, 83, 56, 16);
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

        JLabel lbledad = new JLabel("Edad:");
        lbledad.setBounds(236, 200, 56, 16);
        contentPanel.add(lbledad);

        JSpinner spinneredad = new JSpinner();
        spinneredad.setBounds(236, 219, 30, 22);
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
        for (JPanel panel : paneles) {
            if (panel != panelActual) {
                panel.setVisible(false);
            }
        }
    }
}
