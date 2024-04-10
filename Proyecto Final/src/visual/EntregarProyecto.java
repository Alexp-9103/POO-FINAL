package visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logico.Cliente;
import logico.JJDCommunications;
import logico.Proyecto;

public class EntregarProyecto extends JDialog {
    private JComboBox<Proyecto> proyectosComboBox;
    private JButton entregarButton;

    public EntregarProyecto() {
        setTitle("Entregar Proyecto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblSeleccioneProyecto = new JLabel("Seleccione el proyecto a entregar:");
        panel.add(lblSeleccioneProyecto, BorderLayout.NORTH);

        proyectosComboBox = new JComboBox<>();
        cargarProyectos();
        panel.add(proyectosComboBox, BorderLayout.CENTER);

        entregarButton = new JButton("Entregar Proyecto");
        entregarButton.addActionListener(e -> confirmarEntregaProyecto());
        panel.add(entregarButton, BorderLayout.SOUTH);

        add(panel);
    }

    private void cargarProyectos() {
        JJDCommunications jjd = JJDCommunications.getInstance();

        for (Cliente cliente : jjd.getListaClientes()) {
            for (Proyecto proyecto : cliente.getMisProyectos()) {
                if (proyecto.isContratoActivo()) {
                    proyectosComboBox.addItem(proyecto);
                }
            }
        }
    }

    private void confirmarEntregaProyecto() {
        Proyecto proyectoSeleccionado = (Proyecto) proyectosComboBox.getSelectedItem();

        if (proyectoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un proyecto.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener el nombre del proyecto seleccionado
        String nombreProyecto = proyectoSeleccionado.getNombre();

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que deseas entregar el proyecto \"" + nombreProyecto + "\"?",
                "Confirmar Entrega de Proyecto", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Cambiar el estado del contrato a inactivo
            proyectoSeleccionado.setContratoActivo(false);
            JOptionPane.showMessageDialog(this, "¡El proyecto \"" + nombreProyecto + "\" ha sido entregado con éxito!",
                    "Proyecto Entregado", JOptionPane.INFORMATION_MESSAGE);
            // Actualizar la lista de proyectos
            proyectosComboBox.removeItem(proyectoSeleccionado);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EntregarProyecto().setVisible(true));
    }
}
