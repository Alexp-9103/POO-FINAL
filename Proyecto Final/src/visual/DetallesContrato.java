package visual;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Contrato;
import logico.JJDCommunications;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class DetallesContrato extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtIdContrato;
    private JTextField txtIdCliente;
    private JTextField txtNombreProyecto;
    private JTextField txtFechaInicio;
    private JTextField txtFechaEntrega;
    private JTextField txtProrroga;

    /**
     * Create the dialog.
     */
    public DetallesContrato(String idContrato) {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(null);
        contentPanel.setBounds(0, 0, 434, 221);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel);
        contentPanel.setLayout(null);
        
        JLabel lblIdContrato = new JLabel("ID Contrato:");
        lblIdContrato.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblIdContrato.setBounds(32, 30, 70, 14);
        contentPanel.add(lblIdContrato);
        
        txtIdContrato = new JTextField();
        txtIdContrato.setEditable(false);
        txtIdContrato.setBounds(120, 28, 189, 20);
        contentPanel.add(txtIdContrato);
        txtIdContrato.setColumns(10);
        
        JLabel lblIdCliente = new JLabel("ID Cliente:");
        lblIdCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblIdCliente.setBounds(32, 67, 70, 14);
        contentPanel.add(lblIdCliente);
        
        txtIdCliente = new JTextField();
        txtIdCliente.setEditable(false);
        txtIdCliente.setColumns(10);
        txtIdCliente.setBounds(120, 65, 189, 20);
        contentPanel.add(txtIdCliente);
        
        JLabel lblNombreProyecto = new JLabel("Nombre Proyecto:");
        lblNombreProyecto.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNombreProyecto.setBounds(32, 104, 96, 14);
        contentPanel.add(lblNombreProyecto);
        
        txtNombreProyecto = new JTextField();
        txtNombreProyecto.setEditable(false);
        txtNombreProyecto.setColumns(10);
        txtNombreProyecto.setBounds(120, 102, 189, 20);
        contentPanel.add(txtNombreProyecto);
        
        JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
        lblFechaInicio.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblFechaInicio.setBounds(32, 141, 70, 14);
        contentPanel.add(lblFechaInicio);
        
        txtFechaInicio = new JTextField();
        txtFechaInicio.setEditable(false);
        txtFechaInicio.setColumns(10);
        txtFechaInicio.setBounds(120, 139, 189, 20);
        contentPanel.add(txtFechaInicio);
        
        JLabel lblFechaEntrega = new JLabel("Fecha Entrega:");
        lblFechaEntrega.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblFechaEntrega.setBounds(32, 178, 87, 14);
        contentPanel.add(lblFechaEntrega);
        
        txtFechaEntrega = new JTextField();
        txtFechaEntrega.setEditable(false);
        txtFechaEntrega.setColumns(10);
        txtFechaEntrega.setBounds(120, 176, 189, 20);
        contentPanel.add(txtFechaEntrega);
        
        JLabel lblProrroga = new JLabel("Prorroga:");
        lblProrroga.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblProrroga.setBounds(32, 215, 57, 14);
        contentPanel.add(lblProrroga);
        
        txtProrroga = new JTextField();
        txtProrroga.setEditable(false);
        txtProrroga.setColumns(10);
        txtProrroga.setBounds(120, 213, 189, 20);
        contentPanel.add(txtProrroga);
        
        JPanel buttonPane = new JPanel();
        buttonPane.setBounds(0, 221, 434, 29);
        getContentPane().add(buttonPane);
        buttonPane.setLayout(null);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(335, 5, 89, 23);
        buttonPane.add(btnCerrar);
        
        // Cargar detalles del contrato
        cargarDetallesContrato(idContrato);
    }

    private void cargarDetallesContrato(String idContrato) {
        Contrato contrato = JJDCommunications.getInstance().BuscarContrato(idContrato);
        if (contrato != null) {
            txtIdContrato.setText(contrato.getIdContrato());
            txtIdCliente.setText(contrato.getIdCliente());
            txtNombreProyecto.setText(contrato.getNombreProyecto());
            txtFechaInicio.setText(contrato.getFechaInicio().toString());
            txtFechaEntrega.setText(contrato.getFechaEntrega().toString());
            txtProrroga.setText(contrato.isProrroga() ? "Si" : "No");
        }
    }
}
