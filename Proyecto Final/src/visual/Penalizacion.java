package visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Penalizacion extends JDialog {

    private JTextField textFieldFechaFin;
    private JTextField textFieldMonto;
    private JTextArea textAreaResultado;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Penalizacion dialog = new Penalizacion();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Penalizacion() {
        setTitle("Calculadora de Penalización");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        JButton btnCalcular = new JButton("Calcular Penalización");
        btnCalcular.setBounds(229, 191, 153, 25);
        btnCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularPenalizacion();
            }
        });

        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(null);
        panelBoton.add(btnCalcular);

        textAreaResultado = new JTextArea();
        textAreaResultado.setEditable(false);
        getContentPane().add(panelBoton, BorderLayout.CENTER);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 13, 382, 128);
        panelBoton.add(panel);
        panel.setLayout(null);
        
                JLabel labelMonto = new JLabel("Monto original:");
                labelMonto.setBounds(12, 77, 98, 22);
                panel.add(labelMonto);
                
                        textFieldMonto = new JTextField();
                        textFieldMonto.setBounds(99, 93, 191, 22);
                        panel.add(textFieldMonto);
                        
                                textFieldFechaFin = new JTextField();
                                textFieldFechaFin.setBounds(179, 42, 191, 22);
                                panel.add(textFieldFechaFin);
                                
                                        JLabel labelFechaFin = new JLabel("Fecha de fin del contrato (yyyy-MM-dd):");
                                        labelFechaFin.setBounds(12, 13, 278, 22);
                                        panel.add(labelFechaFin);

        getContentPane().add(new JScrollPane(textAreaResultado), BorderLayout.SOUTH);
    }

    private void calcularPenalizacion() {
        try {
            double montoOriginal = Double.parseDouble(textFieldMonto.getText());
            System.out.println("Monto original: " + montoOriginal);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaFinContrato = dateFormat.parse(textFieldFechaFin.getText());
            System.out.println("Fecha de fin del contrato: " + fechaFinContrato);

            Date fechaActual = new Date();
            System.out.println("Fecha actual: " + fechaActual);

            if (fechaFinContrato.before(fechaActual)) {
                long diferenciaMilisegundos = fechaActual.getTime() - fechaFinContrato.getTime();
                long diasDeRetraso = diferenciaMilisegundos / (1000 * 60 * 60 * 24);
                System.out.println("Días de retraso: " + diasDeRetraso);

                double penalizacion = montoOriginal * 0.01 * diasDeRetraso;
                System.out.println("Penalización calculada: $" + penalizacion);
                textAreaResultado.setText("Penalización: $" + penalizacion);
            } else {
                System.out.println("No hay días de retraso, por lo que no se aplicará penalización.");
                textAreaResultado.setText("No hay penalización");
            }
        } catch (NumberFormatException | ParseException ex) {
            textAreaResultado.setText("Error: Ingrese datos válidos");
        }
    }
}