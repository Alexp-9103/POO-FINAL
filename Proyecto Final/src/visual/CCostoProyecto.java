package visual;

import logico.Cliente;
import logico.JJDCommunications;
import logico.Proyecto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CCostoProyecto extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JComboBox<Proyecto> comboBoxProyectos;
    private JLabel lblCostoProyecto;

    private JJDCommunications jjd;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            CCostoProyecto dialog = new CCostoProyecto();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public CCostoProyecto() {
        setTitle("Calcular Costo de Proyecto");
        setBounds(100, 100, 450, 200);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblProyectoACalcular = new JLabel("Proyecto a Calcular:");
        lblProyectoACalcular.setBounds(10, 32, 150, 14);
        contentPanel.add(lblProyectoACalcular);

        comboBoxProyectos = new JComboBox<>();
        comboBoxProyectos.setBounds(170, 29, 250, 20);
        contentPanel.add(comboBoxProyectos);

        JButton btnCalcular = new JButton("Calcular Costo");
        btnCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularCostoProyecto();
            }
        });
        btnCalcular.setBounds(150, 70, 150, 23);
        contentPanel.add(btnCalcular);

        lblCostoProyecto = new JLabel("");
        lblCostoProyecto.setBounds(10, 110, 400, 14);
        contentPanel.add(lblCostoProyecto);

        jjd = JJDCommunications.getInstance();

        cargarProyectosEntregados();
    }

    private void cargarProyectosEntregados() {
        comboBoxProyectos.removeAllItems();

        JJDCommunications jjd = JJDCommunications.getInstance();

        for (Cliente cliente : jjd.getListaClientes()) {
            for (Proyecto proyecto : cliente.getMisProyectos()) {
                if (!(proyecto.isContratoActivo())) {
                    comboBoxProyectos.addItem(proyecto);
                }
            }
        }
    }

    private void calcularCostoProyecto() {
        Proyecto proyectoSeleccionado = (Proyecto) comboBoxProyectos.getSelectedItem();
        if (proyectoSeleccionado != null) {
            double costoProyecto = jjd.calcularCostoProyecto(proyectoSeleccionado);
            double penalizacion = jjd.calcularPenalizacion(proyectoSeleccionado);
            lblCostoProyecto.setText("El costo del proyecto es: $" + costoProyecto + ". Penalización por retraso: $" + penalizacion);
        } else {
            lblCostoProyecto.setText("Seleccione un proyecto entregado antes de calcular el costo.");
        }
    }
}
