package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Label;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;

public class RegCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textid;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegCliente dialog = new RegCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegCliente() {
		setBounds(100, 100, 469, 378);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Inf. General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 427, 283);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblD = new JLabel("ID:");
		lblD.setBounds(12, 27, 56, 16);
		panel.add(lblD);
		
		textid = new JTextField();
		textid.setBounds(12, 46, 188, 22);
		panel.add(textid);
		textid.setColumns(10);
		
		JLabel lblnombre = new JLabel("Nombre:");
		lblnombre.setBounds(12, 105, 56, 16);
		panel.add(lblnombre);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(12, 176, 82, 16);
		panel.add(lblDireccion);
		
		textField = new JTextField();
		textField.setBounds(12, 127, 338, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(12, 205, 403, 22);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnbuscar = new JButton("Buscar");
		btnbuscar.setBounds(208, 45, 97, 25);
		panel.add(btnbuscar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
	        {
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);

			}
		}
	}
}
