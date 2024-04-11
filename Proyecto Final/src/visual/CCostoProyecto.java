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

    public static void main(String[] args) {
        try {
            CCostoProyecto dialog = new CCostoProyecto();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CCostoProyecto() {
        setTitle("Calcular Costo de Proyecto");
        setBounds(100, 100, 600, 250); // Ajustar el tamaño de la ventana
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Ajustar los márgenes internos
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblProyectoACalcular = new JLabel("Proyecto a Calcular:");
        lblProyectoACalcular.setBounds(20, 20, 150, 20); // Ajustar posición y tamaño
        contentPanel.add(lblProyectoACalcular);

        comboBoxProyectos = new JComboBox<>();
        comboBoxProyectos.setBounds(180, 20, 350, 20); // Ajustar posición y tamaño
        contentPanel.add(comboBoxProyectos);

        JButton btnCalcular = new JButton("Calcular Costo");
        btnCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularCostoProyecto();
            }
        });
        btnCalcular.setBounds(250, 60, 150, 30); // Ajustar posición y tamaño
        contentPanel.add(btnCalcular);

        lblCostoProyecto = new JLabel("");
        lblCostoProyecto.setBounds(20, 100, 550, 20); // Ajustar posición y tamaño
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

