package visual;

import javax.swing.JDialog;

public class DetallesProyecto extends JDialog {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
        	DetallesProyecto dialog = new DetallesProyecto();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */

}
