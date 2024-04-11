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
    private JButton btnEvaluar; // Nuevo botón para evaluar trabajador
    private DefaultTableModel tblModel;
    private ArrayList<Trabajador> trabajadores;
    
    public static void main(String[] args) {
        try {
            // Cargar datos
            JJDCommunications.getInstance().cargarDatos();
            
            // Obtener la lista de trabajadores
            ArrayList<Trabajador> trabajadores = JJDCommunications.getInstance().getListaTrabajadores();

            // Crear la ventana de consulta de evaluación de trabajadores
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

        setTitle("Consulta de Evaluación de Trabajadores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cambio aquí
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);


        // Panel superior con el campo de búsqueda
        JPanel panelSuperior = new JPanel();
        contentPane.add(panelSuperior, BorderLayout.NORTH);

        JLabel lblBuscar = new JLabel("Buscar por Nombre:");
        panelSuperior.add(lblBuscar);

        txtBuscar = new JTextField();
        panelSuperior.add(txtBuscar);
        txtBuscar.setColumns(20);

        btnBuscar = new JButton("Buscar");
        panelSuperior.add(btnBuscar);

     // Panel central con la tabla de trabajadores y el botón
        JPanel panelCentral = new JPanel();
        contentPane.add(panelCentral, BorderLayout.CENTER);
        panelCentral.setLayout(new BorderLayout(0, 10)); // Añadir espacio vertical entre la tabla y el botón

        JScrollPane scrollPane = new JScrollPane();
        panelCentral.add(scrollPane, BorderLayout.CENTER);

        tblModel = new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nombre", "Evaluación" });
        tblTrabajadores = new JTable(tblModel);
        scrollPane.setViewportView(tblTrabajadores);

        // Panel para el botón
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Usar FlowLayout para centrar el botón
        panelCentral.add(panelBoton, BorderLayout.SOUTH);

        // Acción para buscar trabajadores por nombre
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarTrabajadorPorNombre(txtBuscar.getText());
            }
        });
    }

    // Método para establecer la lista de trabajadores y cargarlos en la tabla
    public void setTrabajadores(ArrayList<Trabajador> trabajadores) {
        this.trabajadores = trabajadores;
        // Una vez que se establece la lista de trabajadores, se carga la tabla
        cargarTodosTrabajadores();
    }

    // Método para cargar todos los trabajadores en la tabla
    private void cargarTodosTrabajadores() {
        // Limpiar la tabla
        tblModel.setRowCount(0);

        // Llenar la tabla con todos los trabajadores
        for (Trabajador trabajador : trabajadores) {
            tblModel.addRow(new Object[] { trabajador.getId(), trabajador.getNombre(), trabajador.getEvaluacion() });
        }
    }

    // Método para buscar trabajadores por nombre
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
