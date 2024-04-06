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

public class AddTrabajadorAProyecto extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField textIdCliente;
    private JTextField textNombre;
    private JTextField textCantTrabajadores;
    private JButton btnBuscar;
    private JScrollPane scrollPane;
    private JList listTrabajadoresDisp;
    private JList listTrabajadoresProyecto;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            AddTrabajadorAProyecto dialog = new AddTrabajadorAProyecto();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public AddTrabajadorAProyecto() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JJDCommunications.getInstance().setAuxiliarListTrabajadores(new String[100]);
        setBounds(100, 100, 600, 500); // Ajustado tamaño del diálogo
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JLabel lblIdCliente = new JLabel("ID Cliente:");
        lblIdCliente.setBounds(10, 11, 60, 14);
        contentPanel.add(lblIdCliente);
        
        textIdCliente = new JTextField();
        textIdCliente.setBounds(80, 8, 150, 20);
        contentPanel.add(textIdCliente);
        textIdCliente.setColumns(10);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 42, 60, 14);
        contentPanel.add(lblNombre);
        
        textNombre = new JTextField();
        textNombre.setEditable(false);
        textNombre.setBounds(80, 39, 150, 20);
        contentPanel.add(textNombre);
        textNombre.setColumns(10);
        
        JLabel lblCantTrabajadores = new JLabel("Cantidad Trabajadores:");
        lblCantTrabajadores.setBounds(10, 73, 150, 14);
        contentPanel.add(lblCantTrabajadores);
        
        textCantTrabajadores = new JTextField();
        textCantTrabajadores.setEditable(false);
        textCantTrabajadores.setBounds(159, 70, 71, 20);
        contentPanel.add(textCantTrabajadores);
        textCantTrabajadores.setColumns(10);
        
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idCliente = textIdCliente.getText();
                if(!idCliente.equals("")) {
                    Proyecto proyecto = JJDCommunications.getInstance().BuscarProyecto(idCliente);
                    if(proyecto != null) {
                        textNombre.setText(proyecto.getNombre());
                        textCantTrabajadores.setText(String.valueOf(proyecto.getCantTrabajadores()));
                    } else {
                        JOptionPane.showMessageDialog(null, "Proyecto no encontrado.", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar el ID del cliente.", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        btnBuscar.setBounds(240, 7, 89, 23);
        contentPanel.add(btnBuscar);
        
        JPanel panelTrabajadoresDisp = new JPanel();
        panelTrabajadoresDisp.setBorder(new TitledBorder(null, "Trabajadores Disponibles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelTrabajadoresDisp.setBounds(10, 98, 250, 200); // Ajustado tamaño del panel
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
        panelTrabajadoresProyecto.setBounds(320, 98, 250, 200); // Ajustado tamaño del panel
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
        btnAgregar.setBounds(270, 320, 100, 23); // Ajustado posición del botón
        contentPanel.add(btnAgregar);
        
       JButton btnEliminar = new JButton("<< Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String trabajadorSeleccionado = listTrabajadoresProyecto.getSelectedValue().toString();
                JJDCommunications.getInstance().TrabajadoresEnListaDisp(listTrabajadoresDisp.getSelectedValue().toString());
                actualizarListas();
            }
        });
        btnEliminar.setBounds(390, 320, 100, 23); // Ajustado posición del botón
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
            String[] values = JJDCommunications.getInstance().TrabajadoresEnListaPro(textIdCliente.getText());
            public int getSize() {
                return values.length;
            }
            public Object getElementAt(int index) {
                return values[index];
            }
        });
    }
}
