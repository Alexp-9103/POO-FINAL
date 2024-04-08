package visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import logico.Contrato;

public class ProrrogarContrato extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JComboBox<Contrato> comboBoxContratos;
    private JSpinner spinnerDiasProrroga;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ProrrogarContrato dialog = new ProrrogarContrato();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Create the dialog.
     */
    public ProrrogarContrato() {
        setTitle("Prorrogar Contrato");
        setBounds(100, 100, 400, 200);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblContratoAProrrogar = new JLabel("Contrato a Prorrogar:");
        lblContratoAProrrogar.setBounds(10, 32, 150, 14);
        contentPanel.add(lblContratoAProrrogar);

        comboBoxContratos = new JComboBox<>();
        comboBoxContratos.setBounds(170, 29, 200, 20);
        // Aquí deberías cargar los contratos disponibles en el comboBox
        // cargarContratosDisponibles();
        contentPanel.add(comboBoxContratos);

        JLabel lblDiasDeProrroga = new JLabel("Días de Prórroga:");
        lblDiasDeProrroga.setBounds(10, 70, 150, 14);
        contentPanel.add(lblDiasDeProrroga);

        spinnerDiasProrroga = new JSpinner();
        spinnerDiasProrroga.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        spinnerDiasProrroga.setBounds(170, 67, 86, 20);
        contentPanel.add(spinnerDiasProrroga);

        JButton btnProrrogar = new JButton("Prorrogar");
        btnProrrogar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prorrogarContrato();
            }
        });
        btnProrrogar.setBounds(192, 110, 100, 23);
        contentPanel.add(btnProrrogar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancelar.setBounds(85, 110, 89, 23);
        contentPanel.add(btnCancelar);
    }

    private void prorrogarContrato() {
        try {
            int diasProrroga = (int) spinnerDiasProrroga.getValue();
            Contrato contratoSeleccionado = (Contrato) comboBoxContratos.getSelectedItem();
            if (contratoSeleccionado != null) {
                // Obtener la fecha de entrega actual del contrato
                Date fechaEntregaActual = contratoSeleccionado.getFechaEntrega();
                // Calcular la nueva fecha de entrega sumando los días de prorroga
                long tiempoEnMilisegundos = fechaEntregaActual.getTime() + (diasProrroga * 24L * 60 * 60 * 1000);
                Date nuevaFechaEntrega = new Date(tiempoEnMilisegundos);
                // Actualizar la fecha de entrega del contrato
                contratoSeleccionado.setFechaEntrega(nuevaFechaEntrega);
                JOptionPane.showMessageDialog(this, "Contrato prorrogado exitosamente.\nNueva fecha de entrega: " + nuevaFechaEntrega, "Prórroga Exitosa", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un contrato antes de continuar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido de días de prorroga.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
