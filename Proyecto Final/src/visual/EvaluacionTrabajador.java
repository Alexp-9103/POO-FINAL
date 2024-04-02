package visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import logico.Trabajador;

public class EvaluacionTrabajador extends JDialog {

    private Trabajador trabajador;
    private JTextField txtNombre;
    private JComboBox<String> comboBoxEvaluacion;

    /**
     * Create the dialog.
     */
    public EvaluacionTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;

        setTitle("Evaluaci�n de Trabajador");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setLayout(new GridLayout(3, 2, 10, 10));
        setContentPane(contentPanel);

        JLabel lblNombre = new JLabel("Nombre:");
        contentPanel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setEditable(false);
        txtNombre.setText(trabajador.getNombre());
        contentPanel.add(txtNombre);

        JLabel lblEvaluacion = new JLabel("Evaluacion:");
        contentPanel.add(lblEvaluacion);

        comboBoxEvaluacion = new JComboBox<>();
        comboBoxEvaluacion.setModel(new DefaultComboBoxModel<>(new String[]{"Destacado", "Cumplidor", "Incumplidor"}));
        contentPanel.add(comboBoxEvaluacion);

        JButton btnEvaluar = new JButton("Evaluar");
        btnEvaluar.addActionListener(e -> evaluarTrabajador());
        contentPanel.add(btnEvaluar);
    }

    private void evaluarTrabajador() {
        String evaluacion = (String) comboBoxEvaluacion.getSelectedItem();
        trabajador.setEvaluacion(evaluacion);
        JOptionPane.showMessageDialog(this, "Evaluaci�n registrada correctamente.", "Evaluaci�n Registrada", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    public static void main(String[] args) {
        try {
            // Crear un trabajador de ejemplo para evaluar
            Trabajador ejemploTrabajador = new Trabajador("12345678901", "Juan", "P�rez", "Calle Principal", 'M', 30, 10.0, "", 5);
            EvaluacionTrabajador dialog = new EvaluacionTrabajador(ejemploTrabajador);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
