package visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.JJDCommunications;
import logico.Trabajador;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ConsultarEvaluacionTrabajador extends JFrame {

    private JPanel contentPane;
    private JTable tblTrabajadores;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnEvaluar; // Nuevo bot�n para evaluar trabajador
    private DefaultTableModel tblModel;
    private ArrayList<Trabajador> trabajadores;
    
    public static void main(String[] args) {
        try {
            // Cargar datos
            JJDCommunications.getInstance().cargarDatos();
            
            // Obtener la lista de trabajadores
            ArrayList<Trabajador> trabajadores = JJDCommunications.getInstance().getListaTrabajadores();

            // Crear la ventana de consulta de evaluaci�n de trabajadores
            ConsultarEvaluacionTrabajador dialog = new ConsultarEvaluacionTrabajador();
            dialog.setTrabajadores(trabajadores); // Pasar la lista de trabajadores a la ventana
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Create the frame.
     */
    public ConsultarEvaluacionTrabajador() {

        setTitle("Consulta de Evaluaci�n de Trabajadores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cambio aqu�
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);


        // Panel superior con el campo de b�squeda
        JPanel panelSuperior = new JPanel();
        contentPane.add(panelSuperior, BorderLayout.NORTH);

        JLabel lblBuscar = new JLabel("Buscar por Nombre:");
        panelSuperior.add(lblBuscar);

        txtBuscar = new JTextField();
        panelSuperior.add(txtBuscar);
        txtBuscar.setColumns(20);

        btnBuscar = new JButton("Buscar");
        panelSuperior.add(btnBuscar);

     // Panel central con la tabla de trabajadores y el bot�n
        JPanel panelCentral = new JPanel();
        contentPane.add(panelCentral, BorderLayout.CENTER);
        panelCentral.setLayout(new BorderLayout(0, 10)); // A�adir espacio vertical entre la tabla y el bot�n

        JScrollPane scrollPane = new JScrollPane();
        panelCentral.add(scrollPane, BorderLayout.CENTER);

        tblModel = new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nombre", "Evaluaci�n" });
        tblTrabajadores = new JTable(tblModel);
        scrollPane.setViewportView(tblTrabajadores);

        // Panel para el bot�n
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Usar FlowLayout para centrar el bot�n
        panelCentral.add(panelBoton, BorderLayout.SOUTH);

        // Acci�n para buscar trabajadores por nombre
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarTrabajadorPorNombre(txtBuscar.getText());
            }
        });
    }

    // M�todo para establecer la lista de trabajadores y cargarlos en la tabla
    public void setTrabajadores(ArrayList<Trabajador> trabajadores) {
        this.trabajadores = trabajadores;
        // Una vez que se establece la lista de trabajadores, se carga la tabla
        cargarTodosTrabajadores();
    }

    // M�todo para cargar todos los trabajadores en la tabla
    private void cargarTodosTrabajadores() {
        // Limpiar la tabla
        tblModel.setRowCount(0);

        // Llenar la tabla con todos los trabajadores
        for (Trabajador trabajador : trabajadores) {
            tblModel.addRow(new Object[] { trabajador.getId(), trabajador.getNombre(), trabajador.getEvaluacion() });
        }
    }

    // M�todo para buscar trabajadores por nombre
    private void buscarTrabajadorPorNombre(String nombre) {
        // Limpiar la tabla
        tblModel.setRowCount(0);

        // Llenar la tabla con los trabajadores que coincidan con el nombre buscado
        for (Trabajador trabajador : trabajadores) {
            if (trabajador.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                tblModel.addRow(new Object[] { trabajador.getId(), trabajador.getNombre(), trabajador.getEvaluacion() });
            }
        }
    }

}
