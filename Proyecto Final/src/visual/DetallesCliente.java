package visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.*;

import java.awt.*;
import java.util.ArrayList;

public class DetallesCliente extends JDialog {

    private JTable proyectosTable;

    public DetallesCliente(String idCliente) {
        setTitle("Detalles del Cliente");
        setBounds(100, 100, 800, 600);
        setModal(true);
        getContentPane().setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout());

        // Panel para mostrar los detalles del cliente
        JPanel detallesPanel = new JPanel();
        detallesPanel.setBorder(new TitledBorder(null, "Detalles del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        detallesPanel.setLayout(new GridLayout(5, 2, 10, 5)); // 5 filas, 2 columnas, con espaciado de 10 y 5
        contentPanel.add(detallesPanel, BorderLayout.NORTH);

        // Obtener el cliente
        Cliente cliente = JJDCommunications.getInstance().buscarCliente(idCliente);

        // Agregar detalles del cliente
        agregarDetalle(detallesPanel, "ID:", cliente.getId());
        agregarDetalle(detallesPanel, "Nombre:", cliente.getNombre());
        agregarDetalle(detallesPanel, "Dirección:", cliente.getDireccion());

        // Panel para mostrar los proyectos asociados al cliente
        JPanel proyectosPanel = new JPanel();
        proyectosPanel.setBorder(new TitledBorder(null, "Proyectos Asociados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        proyectosPanel.setLayout(new BorderLayout());
        contentPanel.add(proyectosPanel, BorderLayout.CENTER);

        // Crear la tabla de proyectos
        proyectosTable = new JTable();
        proyectosTable.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"ID Proyecto", "Nombre", "Estado", "Fecha Inicio", "Fecha Fin"}
        ));
        proyectosPanel.add(new JScrollPane(proyectosTable), BorderLayout.CENTER);

        // Obtener los proyectos asociados al cliente
        ArrayList<Proyecto> proyectosAsociados = obtenerProyectosAsociados(cliente);

        // Llenar la tabla con los proyectos asociados
        DefaultTableModel model = (DefaultTableModel) proyectosTable.getModel();
        for (Proyecto proyecto : proyectosAsociados) {
            Contrato contrato = JJDCommunications.getInstance().obtenerContratoPorProyecto(proyecto.getIdProyecto());
            if (contrato != null) {
                String estado = proyecto.isContratoActivo() ? "En Progreso" : "Finalizado";
                model.addRow(new Object[]{
                        proyecto.getIdProyecto(),
                        proyecto.getNombre(),
                        estado,
                        contrato.getFechaInicio(),
                        contrato.getFechaEntrega()
                });
            }
        }
    }

    // Método para obtener los proyectos asociados a un cliente
    private ArrayList<Proyecto> obtenerProyectosAsociados(Cliente cliente) {
        ArrayList<Proyecto> proyectosAsociados = new ArrayList<>();
        for (Proyecto proyecto : JJDCommunications.getInstance().getListaProyectos()) {
            if (proyecto.getIdCliente().equals(cliente.getId())) {
                proyectosAsociados.add(proyecto);
            }
        }
        return proyectosAsociados;
    }

    // Método para agregar detalles al panel
    private void agregarDetalle(JPanel panel, String labelText, String value) {
        JLabel label = new JLabel(labelText);
        panel.add(label);

        JTextArea textArea = new JTextArea(value);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        panel.add(new JScrollPane(textArea));
    }
}
