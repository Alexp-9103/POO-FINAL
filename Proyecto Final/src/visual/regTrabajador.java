package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class regTrabajador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textid;
	private JTextField textnombre;
	private JTextField textdireccion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			regTrabajador dialog = new regTrabajador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public regTrabajador() {
		setBounds(100, 100, 519, 462);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Inf. General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(12, 13, 477, 252);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblid = new JLabel("ID:");
				lblid.setBounds(12, 32, 56, 16);
				panel.add(lblid);
			}
			{
				textid = new JTextField();
				textid.setBounds(12, 48, 265, 22);
				panel.add(textid);
				textid.setColumns(10);
			}
			{
				JLabel lblnombre = new JLabel("Nombre:");
				lblnombre.setBounds(12, 83, 56, 16);
				panel.add(lblnombre);
			}
			{
				textnombre = new JTextField();
				textnombre.setBounds(12, 112, 385, 22);
				panel.add(textnombre);
				textnombre.setColumns(10);
			}
			{
				JLabel lbldireccion = new JLabel("Direccion:");
				lbldireccion.setBounds(12, 147, 84, 16);
				panel.add(lbldireccion);
			}
			{
				textdireccion = new JTextField();
				textdireccion.setBounds(12, 165, 453, 22);
				panel.add(textdireccion);
				textdireccion.setColumns(10);
			}
			{
				JLabel lblsexo = new JLabel("Sexo:");
				lblsexo.setBounds(12, 200, 56, 16);
				panel.add(lblsexo);
			}
			
			JRadioButton rdbtnmasculino = new JRadioButton("Masculino");
			rdbtnmasculino.setBounds(5, 225, 91, 25);
			panel.add(rdbtnmasculino);
			
			JRadioButton rdbtnfemenino = new JRadioButton("Femenino");
			rdbtnfemenino.setBounds(97, 225, 127, 25);
			panel.add(rdbtnfemenino);
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Tipo Trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(22, 278, 467, 89);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(12, 24, 183, 22);
			panel.add(comboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
