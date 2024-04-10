package visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import logico.Disenador;
import logico.JJDCommunications;
import logico.JefeProyecto;
import logico.Planificador;
import logico.Programador;
import logico.Proyecto;
import logico.Trabajador;

public class CrearProyecto extends JFrame {
    private JTextField textIdProyecto;
    private JTextField textNombreProyecto;
    private JList<String> listTrabajadoresDisp;
    private JList<String> listTrabajadoresProyecto;
    private DefaultListModel<String> modelTrabajadoresDisp;
    private DefaultListModel<String> modelTrabajadoresProyecto;
    private JLabel labelContadorTrabajadoresDisp;
    private JLabel labelContadorTrabajadoresProyecto;
    private int cantidadTrabajadoresDisp = 0;
    private int cantidadTrabajadoresProyecto = 0;
    private static int generadorProyecto = 1;
    

    public CrearProyecto() {
    	addWindowListener(new WindowAdapter() {
    	    @Override
    	    public void windowClosing(WindowEvent e) {
    	        JJDCommunications.StartAgain();
    	    }
    	});

    	
        setTitle("Crear Proyecto");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelDatos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelDatos.add(new JLabel("ID Proyecto:"), gbc);
        textIdProyecto = new JTextField(5);
        textIdProyecto.setEditable(false);
        gbc.gridx = 1;
        panelDatos.add(textIdProyecto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelDatos.add(new JLabel("Nombre del Proyecto:"), gbc);
        textNombreProyecto = new JTextField(20);
        gbc.gridx = 1;
        panelDatos.add(textNombreProyecto, gbc);

        add(panelDatos, BorderLayout.NORTH);

        JPanel panelListas = new JPanel(new GridLayout(1, 2, 10, 10));
        panelListas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelTrabajadoresDisp = new JPanel(new BorderLayout());
        panelTrabajadoresDisp.setBorder(BorderFactory.createTitledBorder("Trabajadores Disponibles"));
        modelTrabajadoresDisp = new DefaultListModel<>();
        listTrabajadoresDisp = new JList<>(modelTrabajadoresDisp);
        JScrollPane scrollPaneDisp = new JScrollPane(listTrabajadoresDisp);
        panelTrabajadoresDisp.add(scrollPaneDisp, BorderLayout.CENTER);
        labelContadorTrabajadoresDisp = new JLabel("Cantidad: 0");
        panelTrabajadoresDisp.add(labelContadorTrabajadoresDisp, BorderLayout.SOUTH);
        panelListas.add(panelTrabajadoresDisp);

        JPanel panelTrabajadoresProyecto = new JPanel(new BorderLayout());
        panelTrabajadoresProyecto.setBorder(BorderFactory.createTitledBorder("Trabajadores en Proyecto"));
        modelTrabajadoresProyecto = new DefaultListModel<>();
        listTrabajadoresProyecto = new JList<>(modelTrabajadoresProyecto);
        JScrollPane scrollPaneProyecto = new JScrollPane(listTrabajadoresProyecto);
        panelTrabajadoresProyecto.add(scrollPaneProyecto, BorderLayout.CENTER);
        labelContadorTrabajadoresProyecto = new JLabel("Cantidad: 0");
        panelTrabajadoresProyecto.add(labelContadorTrabajadoresProyecto, BorderLayout.SOUTH);
        panelListas.add(panelTrabajadoresProyecto);

        add(panelListas, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(2, 1, 10, 10));
        JButton btnAgregar = new JButton("Agregar >>");
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedWorker = listTrabajadoresDisp.getSelectedValue();
                if (selectedWorker != null) {
                    String[] workerDetails = selectedWorker.split("\\|");
                    String tipoTrabajador = workerDetails[3].trim();
                    
                    if (JJDCommunications.puedeAgregarTrabajador(tipoTrabajador)) {
                    	JJDCommunications.agregarTrabajador(selectedWorker);
                        modelTrabajadoresDisp.removeElement(selectedWorker);
                        modelTrabajadoresProyecto.addElement(selectedWorker); 
                        actualizarContadorTrabajadores();
                    } else {
                        JOptionPane.showMessageDialog(CrearProyecto.this, "Se ha alcanzado la cantidad máxima de trabajadores de este tipo.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        panelBotones.add(btnAgregar);
        panelBotones.add(btnAgregar);

        JButton btnEliminar = new JButton("<< Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedWorker = listTrabajadoresProyecto.getSelectedValue();
                if (selectedWorker != null) {
                    String[] workerDetails = selectedWorker.split("\\|");
                    String tipoTrabajador = workerDetails[3].trim();
                    
                    JJDCommunications.removeTrabajador(selectedWorker);
                    modelTrabajadoresProyecto.removeElement(selectedWorker);
                    modelTrabajadoresDisp.addElement(selectedWorker); 
                    actualizarContadorTrabajadores();
                }
            }
        });
        panelBotones.add(btnEliminar);
        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.EAST);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarProyecto();
            }
        });
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JJDCommunications.StartAgain();
                dispose();
            }
        });
        JPanel panelBotonesGuardarCancelar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotonesGuardarCancelar.add(btnGuardar);
        panelBotonesGuardarCancelar.add(btnCancelar);
        add(panelBotonesGuardarCancelar, BorderLayout.SOUTH);

        generarIDProyecto();

        cargarTrabajadoresDisponibles();

        textNombreProyecto.setText("Proyecto-" + generadorProyecto);
    }

    private void generarIDProyecto() {
        textIdProyecto.setText("P-" + String.valueOf(generadorProyecto));
    }

    private void cargarTrabajadoresDisponibles() {
        List<Trabajador> trabajadores = JJDCommunications.getInstance().getListaTrabajadores();
        for (Trabajador trabajador : trabajadores) {
            if (trabajador.estaDisponible()) {
                cantidadTrabajadoresDisp++;
                String workerDetails = trabajador.getId() + "| " +
                        trabajador.getNombre() + "| " +
                        trabajador.getSexo() + "| ";

                if (trabajador instanceof JefeProyecto) {
                    workerDetails += "Jefe de Proyecto";
                } else if (trabajador instanceof Programador) {
                    workerDetails += "Programador";
                } else if (trabajador instanceof Disenador) {
                    workerDetails += "Disenador";
                } else if (trabajador instanceof Planificador) {
                    workerDetails += "Planificador";
                } else {
                    workerDetails += "Tipo desconocido";
                }

                modelTrabajadoresDisp.addElement(workerDetails);
            }
        }
        labelContadorTrabajadoresDisp.setText("Cantidad: " + cantidadTrabajadoresDisp);
    }
    
    private void actualizarContadorTrabajadores() {
        cantidadTrabajadoresDisp = modelTrabajadoresDisp.getSize();
        labelContadorTrabajadoresDisp.setText("Cantidad: " + cantidadTrabajadoresDisp);
        
        cantidadTrabajadoresProyecto = modelTrabajadoresProyecto.getSize();
        labelContadorTrabajadoresProyecto.setText("Cantidad: " + cantidadTrabajadoresProyecto);
    }


    private void guardarProyecto() {
        String idProyecto = textIdProyecto.getText();
        String nombreProyecto = textNombreProyecto.getText();
        ArrayList<Trabajador> trabajadoresProyecto = new ArrayList<>();
        for (int i = 0; i < modelTrabajadoresProyecto.size(); i++) {
            String workerDetails = modelTrabajadoresProyecto.getElementAt(i);
            String[] workerInfo = workerDetails.split("\\|");
            String workerId = workerInfo[0].trim();
            Trabajador trabajador = JJDCommunications.getInstance().buscarTrabajadorPorId(workerId);
            if (trabajador != null) {
            	trabajador.aumentarProyectos();
                trabajadoresProyecto.add(trabajador);
            }
        }
        
        if (!trabajadoresProyecto.isEmpty()) {
            Proyecto proyecto = new Proyecto(idProyecto, nombreProyecto, trabajadoresProyecto.size(), false, false, trabajadoresProyecto);     JJDCommunications.getInstance().insertarProyecto(proyecto);

            JOptionPane.showMessageDialog(this, "Proyecto guardado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            generadorProyecto++;
            JJDCommunications.StartAgain();
        } else {
            JOptionPane.showMessageDialog(this, "No hay trabajadores en el proyecto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CrearProyecto().setVisible(true);
            }
        });
    }
}