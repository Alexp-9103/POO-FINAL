package visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.*;

import java.awt.*;
import java.util.ArrayList;

public class DetallesTrabajador extends JDialog {

    private JTable proyectosTable;

    public DetallesTrabajador(String idTrabajador) {
        setTitle("Detalles del Trabajador");
        setBounds(100, 100, 800, 600);
        setModal(true);
        getContentPane().setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout());

        // Panel para mostrar los detalles del trabajador
        JPanel detallesPanel = new JPanel();
        detallesPanel.setBorder(new TitledBorder(null, "Detalles del Trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        detallesPanel.setLayout(new GridLayout(7, 2, 10, 5)); // 7 filas, 2 columnas, con espaciado de 10 y 5
        contentPanel.add(detallesPanel, BorderLayout.NORTH);

        // Obtener el trabajador
        Trabajador trabajador = JJDCommunications.getInstance().BuscarTrabajador(idTrabajador);

        // Agregar detalles del trabajador
        agregarDetalle(detallesPanel, "ID:", trabajador.getId());
        agregarDetalle(detallesPanel, "Nombre:", trabajador.getNombre());
        agregarDetalle(detallesPanel, "Dirección:", trabajador.getDireccion());
        agregarDetalle(detallesPanel, "Sexo:", String.valueOf(trabajador.getSexo()));
        agregarDetalle(detallesPanel, "Edad:", String.valueOf(trabajador.getEdad()));
        agregarDetalle(detallesPanel, "Salario:", String.valueOf(trabajador.getSalarioHora()));
        agregarDetalle(detallesPanel, "Evaluación:", trabajador.getEvaluacion());

        // Panel para mostrar los proyectos asociados al trabajador
        JPanel proyectosPanel = new JPanel();
        proyectosPanel.setBorder(new TitledBorder(null, "Proyectos Asociados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        proyectosPanel.setLayout(new BorderLayout());
        contentPanel.add(proyectosPanel, BorderLayout.CENTER);

        // Crear la tabla de proyectos
        proyectosTable = new JTable();
        proyectosTable.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"ID Proyecto", "Nombre", "Fecha Inicio", "Fecha Fin"}
        ));
        proyectosPanel.add(new JScrollPane(proyectosTable), BorderLayout.CENTER);

        // Obtener los proyectos asociados al trabajador
        ArrayList<Proyecto> proyectosAsociados = obtenerProyectosAsociados(trabajador);

        // Llenar la tabla con los proyectos asociados
        DefaultTableModel model = (DefaultTableModel) proyectosTable.getModel();
        for (Proyecto proyecto : proyectosAsociados) {
            Contrato contrato = JJDCommunications.getInstance().obtenerContratoPorProyecto(proyecto.getIdProyecto());
            if (contrato != null) {
                model.addRow(new Object[]{
                        proyecto.getIdProyecto(),
                        proyecto.getNombre(),
                        contrato.getFechaInicio(),
                        contrato.getFechaEntrega()
                });
            }
        }
    }

    // Método para obtener los proyectos asociados a un trabajador
    private ArrayList<Proyecto> obtenerProyectosAsociados(Trabajador trabajador) {
        ArrayList<Proyecto> proyectosAsociados = new ArrayList<>();
        for (Proyecto proyecto : JJDCommunications.getInstance().getListaProyectos()) {
            if (proyecto.getLosTrabajadores().contains(trabajador.getId())) {
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
