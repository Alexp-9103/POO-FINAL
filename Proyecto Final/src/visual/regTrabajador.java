package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;

public class regTrabajador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textid;
	private JTextField textnombre;
	private JTextField textdireccion;
	private JTextField textsalario;
	private JTextField textField;

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
		setBounds(100, 100, 519, 538);
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
			rdbtnmasculino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnmasculino.setSelected(false);
					//rdbtnfemenino.setSelected(false);
					//pnlQCilind.setVisible(false);
					//pnlQEsf.setVisible(true);
					
				}
			});
			rdbtnmasculino.setBounds(8, 218, 91, 25);
			panel.add(rdbtnmasculino);
			
			JRadioButton rdbtnfemenino = new JRadioButton("Femenino");
			rdbtnfemenino.setBounds(101, 218, 127, 25);
			panel.add(rdbtnfemenino);
			{
				JLabel lbledad = new JLabel("Edad:");
				lbledad.setBounds(236, 200, 56, 16);
				panel.add(lbledad);
			}
			
			JSpinner spinneredad = new JSpinner();
			spinneredad.setBounds(236, 219, 30, 22);
			panel.add(spinneredad);
			
			JLabel lblsalario = new JLabel("Salario:");
			lblsalario.setBounds(355, 200, 56, 16);
			panel.add(lblsalario);
			
			textsalario = new JTextField();
			textsalario.setText("0");
			textsalario.setBounds(329, 219, 116, 22);
			panel.add(textsalario);
			textsalario.setColumns(10);
			{
				textField = new JTextField();
				textField.setEditable(false);
				textField.setText("$");
				textField.setBounds(309, 219, 18, 22);
				panel.add(textField);
				textField.setColumns(10);
			}
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Tipo Trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 278, 477, 60);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setEditable(true);
			comboBox.setBounds(12, 24, 183, 22);
			panel.add(comboBox);
		}
		{
			JPanel paneljefeProyecto = new JPanel();
			paneljefeProyecto.setBounds(22, 348, 19, 95);
			contentPanel.add(paneljefeProyecto);
			paneljefeProyecto.setLayout(null);
		}
		{
			JPanel paneldiseniador = new JPanel();
			paneldiseniador.setBounds(22, 348, 467, 95);
			contentPanel.add(paneldiseniador);
			paneldiseniador.setLayout(null);
		}
		{
			JPanel panelprogramador = new JPanel();
			panelprogramador.setBounds(22, 348, 467, 95);
			contentPanel.add(panelprogramador);
			panelprogramador.setLayout(null);
		}
		{
			JPanel panelplanificador = new JPanel();
			panelplanificador.setBounds(22, 348, 467, 95);
			contentPanel.add(panelplanificador);
			panelplanificador.setLayout(null);
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
