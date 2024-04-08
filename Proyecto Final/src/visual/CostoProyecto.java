package visual;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;

public class CostoProyecto extends JDialog {

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

}
