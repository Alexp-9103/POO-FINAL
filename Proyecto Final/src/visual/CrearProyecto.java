package visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import logico.JJDCommunications;
import logico.Trabajador;

public class CrearProyecto extends JFrame {
    private JTextField textIdProyecto;
    private JTextField textNombreProyecto;
    private JTextField textContratoActivo;
    private JList<String> listTrabajadoresDisp;
    private JList<String> listTrabajadoresProyecto;
    private DefaultListModel<String> modelTrabajadoresDisp;
    private DefaultListModel<String> modelTrabajadoresProyecto;
    private static int generadorProyecto = 1;

    public CrearProyecto() {
        setTitle("Crear Proyecto");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(new BorderLayout());

        JPanel panelDatos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelDatos.add(new JLabel("ID Proyecto:"), gbc);
        textIdProyecto = new JTextField(5);
        textIdProyecto.setEditable(false);
        gbc.gridx = 1;
        panelDatos.add(textIdProyecto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelDatos.add(new JLabel("Nombre del Proyecto:"), gbc);
        textNombreProyecto = new JTextField(20);
        gbc.gridx = 1;
        panelDatos.add(textNombreProyecto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelDatos.add(new JLabel("Contrato Activo:"), gbc);
        textContratoActivo = new JTextField(10);
        gbc.gridx = 1;
        panelDatos.add(textContratoActivo, gbc);

        add(panelDatos, BorderLayout.NORTH);

        JPanel panelListas = new JPanel(new GridLayout(1, 2, 10, 10));
        modelTrabajadoresDisp = new DefaultListModel<>();
        listTrabajadoresDisp = new JList<>(modelTrabajadoresDisp);
        JScrollPane scrollPaneDisp = new JScrollPane(listTrabajadoresDisp);
        panelListas.add(scrollPaneDisp);

        modelTrabajadoresProyecto = new DefaultListModel<>();
        listTrabajadoresProyecto = new JList<>(modelTrabajadoresProyecto);
        JScrollPane scrollPaneProyecto = new JScrollPane(listTrabajadoresProyecto);
        panelListas.add(scrollPaneProyecto);

        add(panelListas, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(2, 1, 10, 10));
        JButton btnAgregar = new JButton("Agregar >>");
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedWorker = listTrabajadoresDisp.getSelectedValue();
                if (selectedWorker != null) {
                    modelTrabajadoresProyecto.addElement(selectedWorker);
                    modelTrabajadoresDisp.removeElement(selectedWorker);
                }
            }
        });
        panelBotones.add(btnAgregar);

        JButton btnEliminar = new JButton("<< Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedWorker = listTrabajadoresProyecto.getSelectedValue();
                if (selectedWorker != null) {
                    modelTrabajadoresDisp.addElement(selectedWorker);
                    modelTrabajadoresProyecto.removeElement(selectedWorker);
                }
            }
        });
        panelBotones.add(btnEliminar);

        add(panelBotones, BorderLayout.EAST);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarProyecto();
            }
        });
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel panelBotonesGuardarCancelar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotonesGuardarCancelar.add(btnGuardar);
        panelBotonesGuardarCancelar.add(btnCancelar);
        add(panelBotonesGuardarCancelar, BorderLayout.SOUTH);

        generarIDProyecto();

        // Cargar trabajadores disponibles
        cargarTrabajadoresDisponibles();

        textNombreProyecto.setText("Proyecto-" + generadorProyecto);
        generadorProyecto++;
    }


	private void generarIDProyecto() {
        textIdProyecto.setText("P-" + String.valueOf(generadorProyecto));
    }

	private void cargarTrabajadoresDisponibles() {
	    List<Trabajador> trabajadores = JJDCommunications.getInstance().getListaTrabajadores();
	    for (Trabajador trabajador : trabajadores) {
	        if (trabajador.estaDisponible()) {
	            modelTrabajadoresDisp.addElement(trabajador.getNombre());
	        }
	    }
	}

    private void guardarProyecto() {
        JOptionPane.showMessageDialog(this, "Proyecto guardado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CrearProyecto().setVisible(true);
            }
        });
    }

}