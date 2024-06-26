package visual;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import logico.Proyecto;
import logico.Trabajador;
import logico.JJDCommunications;
import java.awt.Dimension;

public class DetallesProyecto extends JDialog {
    private JLabel lblIdProyecto;
    private JLabel lblNombreProyecto;
    private JLabel lblCantidadTrabajadores;
    private JLabel lblContratoActivo;
    private JTable tableTrabajadores;
    private DefaultTableModel modelTrabajadores;

    /**
     * Create the dialog.
     */
    public DetallesProyecto(String nombreProyecto) {
        setTitle("Detalles del Proyecto");
        setBounds(100, 100, 800, 600);
        setModal(true);
        getContentPane().setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setBorder(new TitledBorder(null, "Detalles del Proyecto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPanel.add(detailsPanel, BorderLayout.WEST);
        GridBagLayout gbl_detailsPanel = new GridBagLayout();
        gbl_detailsPanel.columnWidths = new int[]{0, 0};
        gbl_detailsPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_detailsPanel.columnWeights = new double[]{0.0, 1.0};
        gbl_detailsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        detailsPanel.setLayout(gbl_detailsPanel);

        JLabel lblIdLabel = new JLabel("ID Proyecto:");
        GridBagConstraints gbc_lblIdLabel = new GridBagConstraints();
        gbc_lblIdLabel.anchor = GridBagConstraints.WEST;
        gbc_lblIdLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblIdLabel.gridx = 0;
        gbc_lblIdLabel.gridy = 0;
        detailsPanel.add(lblIdLabel, gbc_lblIdLabel);

        lblIdProyecto = new JLabel();
        GridBagConstraints gbc_lblIdProyecto = new GridBagConstraints();
        gbc_lblIdProyecto.insets = new Insets(0, 0, 5, 0);
        gbc_lblIdProyecto.gridx = 1;
        gbc_lblIdProyecto.gridy = 0;
        detailsPanel.add(lblIdProyecto, gbc_lblIdProyecto);

        JLabel lblNombreLabel = new JLabel("Nombre Proyecto:");
        GridBagConstraints gbc_lblNombreLabel = new GridBagConstraints();
        gbc_lblNombreLabel.anchor = GridBagConstraints.WEST;
        gbc_lblNombreLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNombreLabel.gridx = 0;
        gbc_lblNombreLabel.gridy = 1;
        detailsPanel.add(lblNombreLabel, gbc_lblNombreLabel);

        lblNombreProyecto = new JLabel();
        GridBagConstraints gbc_lblNombreProyecto = new GridBagConstraints();
        gbc_lblNombreProyecto.insets = new Insets(0, 0, 5, 0);
        gbc_lblNombreProyecto.gridx = 1;
        gbc_lblNombreProyecto.gridy = 1;
        detailsPanel.add(lblNombreProyecto, gbc_lblNombreProyecto);

        JLabel lblCantidadLabel = new JLabel("Cantidad Trabajadores:");
        GridBagConstraints gbc_lblCantidadLabel = new GridBagConstraints();
        gbc_lblCantidadLabel.anchor = GridBagConstraints.WEST;
        gbc_lblCantidadLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblCantidadLabel.gridx = 0;
        gbc_lblCantidadLabel.gridy = 2;
        detailsPanel.add(lblCantidadLabel, gbc_lblCantidadLabel);

        lblCantidadTrabajadores = new JLabel();
        GridBagConstraints gbc_lblCantidadTrabajadores = new GridBagConstraints();
        gbc_lblCantidadTrabajadores.insets = new Insets(0, 0, 5, 0);
        gbc_lblCantidadTrabajadores.gridx = 1;
        gbc_lblCantidadTrabajadores.gridy = 2;
        detailsPanel.add(lblCantidadTrabajadores, gbc_lblCantidadTrabajadores);

        JLabel lblContratoLabel = new JLabel("Contrato Activo:");
        GridBagConstraints gbc_lblContratoLabel = new GridBagConstraints();
        gbc_lblContratoLabel.anchor = GridBagConstraints.WEST;
        gbc_lblContratoLabel.insets = new Insets(0, 0, 0, 5);
        gbc_lblContratoLabel.gridx = 0;
        gbc_lblContratoLabel.gridy = 3;
        detailsPanel.add(lblContratoLabel, gbc_lblContratoLabel);

        lblContratoActivo = new JLabel();
        GridBagConstraints gbc_lblContratoActivo = new GridBagConstraints();
        gbc_lblContratoActivo.gridx = 1;
        gbc_lblContratoActivo.gridy = 3;
        detailsPanel.add(lblContratoActivo, gbc_lblContratoActivo);

        JPanel trabajadoresPanel = new JPanel();
        trabajadoresPanel.setPreferredSize(new Dimension(10, 200));
        trabajadoresPanel.setBorder(new TitledBorder(null, "Trabajadores del Proyecto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPanel.add(trabajadoresPanel, BorderLayout.CENTER);
        trabajadoresPanel.setLayout(new BorderLayout(0, 0));

        String[] columnNames = {"ID", "Nombre", "Sexo", "Tipo"};
        modelTrabajadores = new DefaultTableModel(columnNames, 0);
        tableTrabajadores = new JTable(modelTrabajadores);
        JScrollPane scrollPane = new JScrollPane(tableTrabajadores);
        trabajadoresPanel.add(scrollPane, BorderLayout.CENTER);

        loadProyectoDetails(nombreProyecto);
    }

    private void loadProyectoDetails(String nombreProyecto) {
        Proyecto proyecto = JJDCommunications.getInstance().buscarProyecto(nombreProyecto);
        if (proyecto != null) {
            lblIdProyecto.setText(proyecto.getIdProyecto());
            lblNombreProyecto.setText(proyecto.getNombre());
            lblCantidadTrabajadores.setText(String.valueOf(proyecto.getCantTrabajadores()));
            lblContratoActivo.setText(proyecto.isContratoActivo() ? "Sí" : "No");
            System.out.println("Detalles del proyecto cargados exitosamente.");
            loadTrabajadoresInvolucrados(proyecto);
        } else {
            System.out.println("No se encontró el proyecto con nombre: " + nombreProyecto);
        }
    }

    private void loadTrabajadoresInvolucrados(Proyecto proyecto) {
        ArrayList<Trabajador> trabajadores = proyecto.getLosTrabajadores();
        modelTrabajadores.setRowCount(0);
        for (Trabajador trabajador : trabajadores) {
            String tipoTrabajador = getTipoTrabajador(trabajador);
            modelTrabajadores.addRow(new Object[]{
                    trabajador.getId(),
                    trabajador.getNombre(),
                    trabajador.getSexo(),
                    tipoTrabajador
            });
            System.out.println("Trabajador " + trabajador.getId() + " añadido al proyecto.");
        }
    }

    private String getTipoTrabajador(Trabajador trabajador) {
        if (trabajador instanceof logico.JefeProyecto) {
            return "Jefe de Proyecto";
        } else if (trabajador instanceof logico.Programador) {
            return "Programador";
        } else if (trabajador instanceof logico.Disenador) {
            return "Diseñador";
        } else if (trabajador instanceof logico.Planificador) {
            return "Planificador";
        } else {
            return "Tipo desconocido";
        }
    }
}
