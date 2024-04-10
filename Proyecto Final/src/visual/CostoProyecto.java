package visual;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;

import logico.JJDCommunications;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

public class CostoProyecto extends JDialog {
	
	private JJDCommunications jjdCommunications;
	
	 /**
     * Launch the application.
     */
	public static void main(String[] args) {
		try {
			CostoProyecto dialog = new CostoProyecto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public CostoProyecto() {
		
		setTitle("Costo de Proyectos");
		setSize(900, 500); 
        setLocationRelativeTo(null);
        
        jjdCommunications = JJDCommunications.getInstance();
        
		JPanel contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel paneltipoproyecto = new JPanel();
		paneltipoproyecto.setBounds(12, 13, 482, 38);
		contentPanel.add(paneltipoproyecto);
		paneltipoproyecto.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tipo de proyecto:");
		lblNewLabel.setBounds(12, 16, 101, 16);
		paneltipoproyecto.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Todos>", "Activos", "Inactivos"}));
		comboBox.setBounds(116, 13, 127, 22);
		paneltipoproyecto.add(comboBox);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 64, 482, 376);
		contentPanel.add(panel);
	}

   
}
