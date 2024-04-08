package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.AbstractListModel;

import logico.Proyecto;
import logico.JJDCommunications;

public class CrearProyecto extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField textNombreProyecto;
    private JTextField textIdProyecto;
    private JTextField textContratoActivo;
    private JScrollPane scrollPane;
    private JList listTrabajadoresDisp;
    private JList listTrabajadoresProyecto;
    private static int generadorProyecto = 1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            CrearProyecto dialog = new CrearProyecto();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public CrearProyecto() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JJDCommunications.getInstance().setAuxiliarListTrabajadores(new String[100]);
        setBounds(100, 100, 600, 500);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblIdProyecto = new JLabel("ID Proyecto:");
        lblIdProyecto.setBounds(10, 11, 80, 14);
        contentPanel.add(lblIdProyecto);

        textIdProyecto = new JTextField();
        textIdProyecto.setEditable(false);
        textIdProyecto.setBounds(100, 8, 60, 20); // Reducido a la mitad el ancho del cuadro de texto del ID
        contentPanel.add(textIdProyecto);
        textIdProyecto.setColumns(10);

        JLabel lblNombreProyecto = new JLabel("Nombre:");
        lblNombreProyecto.setBounds(10, 42, 80, 14);
        contentPanel.add(lblNombreProyecto);

        textNombreProyecto = new JTextField();
        textNombreProyecto.setBounds(100, 39, 225, 20); // Aumentado tamaño del cuadro de texto del nombre
        contentPanel.add(textNombreProyecto);
        textNombreProyecto.setColumns(10);

        JLabel lblContratoActivo = new JLabel("Contrato Activo:");
        lblContratoActivo.setBounds(10, 73, 100, 14);
        contentPanel.add(lblContratoActivo);

        textContratoActivo = new JTextField();
        textContratoActivo.setBounds(120, 70, 100, 20);
        contentPanel.add(textContratoActivo);
        textContratoActivo.setColumns(10);

        JPanel panelTrabajadoresDisp = new JPanel();
        panelTrabajadoresDisp.setBorder(new TitledBorder(null, "Trabajadores Disponibles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelTrabajadoresDisp.setBounds(10, 98, 250, 200);
        contentPanel.add(panelTrabajadoresDisp);
        panelTrabajadoresDisp.setLayout(new BorderLayout(0, 0));

        scrollPane = new JScrollPane();
        panelTrabajadoresDisp.add(scrollPane, BorderLayout.CENTER);

        listTrabajadoresDisp = new JList();
        listTrabajadoresDisp.setModel(new AbstractListModel() {
            String[] values = JJDCommunications.getInstance().TrabajadoresEnLista();
            public int getSize() {
                return values.length;
            }
            public Object getElementAt(int index) {
                return values[index];
            }
        });
        scrollPane.setViewportView(listTrabajadoresDisp);

        JPanel panelTrabajadoresProyecto = new JPanel();
        panelTrabajadoresProyecto.setBorder(new TitledBorder(null, "Trabajadores en Proyecto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelTrabajadoresProyecto.setBounds(320, 98, 250, 200);
        contentPanel.add(panelTrabajadoresProyecto);
        panelTrabajadoresProyecto.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane_1 = new JScrollPane();
        panelTrabajadoresProyecto.add(scrollPane_1, BorderLayout.CENTER);

        listTrabajadoresProyecto = new JList();
        scrollPane_1.setViewportView(listTrabajadoresProyecto);

        JButton btnAgregar = new JButton("Agregar >>");
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String trabajadorSeleccionado = listTrabajadoresDisp.getSelectedValue().toString();
                JJDCommunications.getInstance().TrabajadoresEnListaPro(listTrabajadoresDisp.getSelectedValue().toString());
                actualizarListas();
            }
        });
        btnAgregar.setBounds(270, 320, 100, 23);
        contentPanel.add(btnAgregar);

       JButton btnEliminar = new JButton("<< Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String trabajadorSeleccionado = listTrabajadoresProyecto.getSelectedValue().toString();
                JJDCommunications.getInstance().TrabajadoresEnListaDisp(listTrabajadoresDisp.getSelectedValue().toString());
                actualizarListas();
            }
        });
        btnEliminar.setBounds(390, 320, 100, 23);
        contentPanel.add(btnEliminar);

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);                

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí podrías agregar la lógica para guardar los cambios en los trabajadores del proyecto
                JOptionPane.showMessageDialog(null, "Cambios guardados exitosamente.", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
        btnGuardar.setActionCommand("OK");
        buttonPane.add(btnGuardar);
        getRootPane().setDefaultButton(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancelar.setActionCommand("Cancel");
        buttonPane.add(btnCancelar);

        textIdProyecto.setText("P-" + generadorProyecto);
        generadorProyecto++;
    }

    private void actualizarListas() {
        listTrabajadoresDisp.setModel(new AbstractListModel() {
            String[] values = JJDCommunications.getInstance().TrabajadoresEnLista();
            public int getSize() {
                return values.length;
            }
            public Object getElementAt(int index) {
                return values[index];
            }
        });
        
        listTrabajadoresProyecto.setModel(new AbstractListModel() {
            String[] values = JJDCommunications.getInstance().TrabajadoresEnListaPro(textIdProyecto.getText());
            public int getSize() {
                return values.length;
            }
            public Object getElementAt(int index) {
                return values[index];
            }
        });
    }
}
