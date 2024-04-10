package visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logico.JJDCommunications;
import logico.Proyecto;

public class EntregarProyecto extends JFrame {
    private JComboBox<Proyecto> proyectosComboBox;
    private JButton entregarButton;

    public EntregarProyecto() {
        setTitle("Entregar Proyecto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblSeleccioneProyecto = new JLabel("Seleccione el proyecto a entregar:");
        panel.add(lblSeleccioneProyecto, BorderLayout.NORTH);

        proyectosComboBox = new JComboBox<>();
        cargarProyectos();
        panel.add(proyectosComboBox, BorderLayout.CENTER);

        entregarButton = new JButton("Entregar Proyecto");
        entregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarEntregaProyecto();
            }
        });
        panel.add(entregarButton, BorderLayout.SOUTH);

        add(panel);
    }

    private void cargarProyectos() {
        JJDCommunications jjd = JJDCommunications.getInstance();
        for (Proyecto proyecto : jjd.getListaProyectos()) {
            proyectosComboBox.addItem(proyecto);
        }
    }

    private void confirmarEntregaProyecto() {
        Proyecto proyectoSeleccionado = (Proyecto) proyectosComboBox.getSelectedItem();
        if (proyectoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "No hay proyectos disponibles para entregar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombreProyecto = proyectoSeleccionado.getNombre();

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que deseas entregar el proyecto \"" + nombreProyecto + "\"?",
                "Confirmar Entrega de Proyecto", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "¡El proyecto \"" + nombreProyecto + "\" ha sido entregado con éxito!",
                    "Proyecto Entregado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EntregarProyecto().setVisible(true);
            }
        });
    }
}

