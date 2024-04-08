package visual;

import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;

public class Penalizacion extends JDialog {

    /**
     * Launch the application.
     */
	public static void main(String[] args) {
		try {
			Penalizacion dialog = new Penalizacion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
