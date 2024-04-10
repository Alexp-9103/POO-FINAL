package visual;
 
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.JOptionPane;
 
import logico.Cliente;
import logico.Contrato;
import logico.JJDCommunications;
import logico.Proyecto;
 
public class RegContrato extends JDialog {
 
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldIdContrato;
    private JComboBox<String> comboBoxClientes;
    private JComboBox<String> comboBoxProyectos;
    private Date fechaInicio;
    private Date fechaEntrega;
    private int ultimoIdContrato = 0;
    private javax.swing.JSpinner spinnerFechaInicio;
    private javax.swing.JSpinner spinnerFechaEntrega;
 
    public static void main(String[] args) {
        try {
            RegContrato dialog = new RegContrato();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public RegContrato() {
        setTitle("Registrar Contrato");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(null);
        contentPanel.setBorder(new TitledBorder(null, "Inf. General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPanel.setBounds(10, 11, 414, 204);
        getContentPane().add(contentPanel);
        contentPanel.setLayout(null);
 
        JLabel lblIdContrato = new JLabel("ID Contrato:");
        lblIdContrato.setBounds(12, 27, 73, 14);
        contentPanel.add(lblIdContrato);
 
        textFieldIdContrato = new JTextField();
        textFieldIdContrato.setBounds(127, 24, 254, 20);
        contentPanel.add(textFieldIdContrato);
        textFieldIdContrato.setColumns(10);
        textFieldIdContrato.setEditable(false);
 
        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(12, 61, 73, 14);
        contentPanel.add(lblCliente);
 
        comboBoxClientes = new JComboBox<String>();
        comboBoxClientes.setBounds(127, 58, 254, 20);
        contentPanel.add(comboBoxClientes);
 
        JLabel lblProyecto = new JLabel("Proyecto:");
        lblProyecto.setBounds(12, 95, 73, 14);
        contentPanel.add(lblProyecto);
 
        comboBoxProyectos = new JComboBox<String>();
        comboBoxProyectos.setBounds(127, 92, 254, 20);
        contentPanel.add(comboBoxProyectos);
 
        JLabel lblFechaInicio = new JLabel("Fecha de Inicio:");
        lblFechaInicio.setBounds(12, 129, 91, 14);
        contentPanel.add(lblFechaInicio);
 
        SpinnerDateModel modelFechaInicio = new SpinnerDateModel();
        modelFechaInicio.setCalendarField(java.util.Calendar.DAY_OF_MONTH);
        spinnerFechaInicio = new javax.swing.JSpinner(modelFechaInicio);
        spinnerFechaInicio.setBounds(127, 126, 254, 20);
        contentPanel.add(spinnerFechaInicio);
 
        JLabel lblFechaEntrega = new JLabel("Fecha de Entrega:");
        lblFechaEntrega.setBounds(12, 163, 103, 14);
        contentPanel.add(lblFechaEntrega);
 
        SpinnerDateModel modelFechaEntrega = new SpinnerDateModel();
        modelFechaEntrega.setCalendarField(java.util.Calendar.DAY_OF_MONTH);
        spinnerFechaEntrega = new javax.swing.JSpinner(modelFechaEntrega);
        spinnerFechaEntrega.setBounds(127, 160, 254, 20);
        contentPanel.add(spinnerFechaEntrega);
 
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarContrato();
            }
        });
        btnRegistrar.setBounds(309, 228, 115, 23);
        getContentPane().add(btnRegistrar);
 
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancelar.setBounds(184, 228, 115, 23);
        getContentPane().add(btnCancelar);
 
        // Generar ID automÃ¡tico para el contrato
        generarIdContrato();
 
        // Obtener la instancia de JJDCommunications
        JJDCommunications jjd = JJDCommunications.getInstance();
 
        // Obtener la lista de clientes y agregarlos al ComboBox de clientes
        for (Cliente cliente : jjd.getListaClientes()) {
            comboBoxClientes.addItem(cliente.getId() + " - " + cliente.getNombre());
        }
 
        // Obtener la lista de proyectos y agregarlos al ComboBox de proyectos
        for (Proyecto proyecto : jjd.getListaProyectos()) {
            comboBoxProyectos.addItem(proyecto.getNombre());
        }
    }
 
    private void generarIdContrato() {
        ultimoIdContrato++;
        String idContrato = String.format("CT-%03d", ultimoIdContrato);
        textFieldIdContrato.setText(idContrato);
    }
 
    private void registrarContrato() {
        String idContrato = textFieldIdContrato.getText();
        String clienteSeleccionado = (String) comboBoxClientes.getSelectedItem();
        String proyectoSeleccionado = (String) comboBoxProyectos.getSelectedItem();
        fechaInicio = (Date) spinnerFechaInicio.getValue();
        fechaEntrega = (Date) spinnerFechaEntrega.getValue();

        JJDCommunications jjd = JJDCommunications.getInstance();

        // Obtener el ID del cliente seleccionado
        String clienteId = clienteSeleccionado.split(" - ")[0]; // Obtener el ID antes del primer espacio
        Cliente cliente = jjd.BuscarCliente(clienteId);

        // Obtener el proyecto seleccionado por su nombre
        Proyecto proyecto = jjd.buscarProyecto(proyectoSeleccionado);

        if (cliente != null && proyecto != null) {
            // Modificar la creación del contrato para adaptarse al nuevo constructor
            Contrato contrato = new Contrato(idContrato, cliente.getId(), proyecto.getIdProyecto(), proyecto.getNombre(), fechaInicio, fechaEntrega, false);
            jjd.insertarContrato(contrato);

            // Agregar el proyecto asociado al cliente
            cliente.agregarProyecto(proyecto);

            JOptionPane.showMessageDialog(this, "Contrato registrado exitosamente.");
            generarIdContrato();
        } else {
            JOptionPane.showMessageDialog(this, "Error: Cliente o proyecto seleccionado no encontrado.");
        }
        
        dispose();
    }


    
}