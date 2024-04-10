package visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import logico.Cliente;
import logico.JJDCommunications;
import logico.Proyecto;

public class EntregarProyecto extends JDialog {
    private JComboBox<Proyecto> proyectosComboBox;
    private JButton entregarButton;

    public EntregarProyecto() {
        setTitle("Entregar Proyecto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
    }
        


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EntregarProyecto().setVisible(true);
            }
        });
    }
}

