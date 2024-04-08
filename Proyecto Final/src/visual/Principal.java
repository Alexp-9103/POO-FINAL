package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Font;

public class Principal extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal frame = new Principal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Principal() {
        setTitle("Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 400);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        setJMenuBar(menuBar);

        JMenu mnTrabajadore = new JMenu("Trabajadores");
        menuBar.add(mnTrabajadore);

        JMenuItem mntmRegistrarTrabajador = new JMenuItem("Registrar Trabajador");
        mntmRegistrarTrabajador.addActionListener(e -> {
            RegTrabajador registroTrabajador = new RegTrabajador();
            registroTrabajador.setModal(true);
            registroTrabajador.setVisible(true);
        });
        mnTrabajadore.add(mntmRegistrarTrabajador);

        JSeparator separator = new JSeparator();
        mnTrabajadore.add(separator);

        JMenuItem mntmListadoTrabajador = new JMenuItem("Listado de Trabajadores");
        mntmListadoTrabajador.addActionListener(e -> {
        	ListadoTrabajador ListadoTrabajador = new ListadoTrabajador();
        	ListadoTrabajador.setModal(true);
        	ListadoTrabajador.setVisible(true);
        });
        mnTrabajadore.add(mntmListadoTrabajador);

        JMenu mnProyecto = new JMenu("Proyectos");
        menuBar.add(mnProyecto);
        
        JMenuItem mntmCrearProyecto = new JMenuItem("Crear Proyecto");
        mnProyecto.add(mntmCrearProyecto);
        
        JSeparator separator_4 = new JSeparator();
        mnProyecto.add(separator_4);

        JMenuItem mntmListadoProyecto = new JMenuItem("Listado Proyecto");
        mnProyecto.add(mntmListadoProyecto);
        mntmListadoProyecto.addActionListener(e -> {
            ListadoProyecto ListadoProyecto = new ListadoProyecto();
            ListadoProyecto.setModal(true);
            ListadoProyecto.setVisible(true);
        });

        JMenu mnCliente = new JMenu("Clientes");
        menuBar.add(mnCliente);

        JMenuItem mntmRegistrarCliente = new JMenuItem("Registrar Cliente");
        mnCliente.add(mntmRegistrarCliente);
        mntmRegistrarCliente.addActionListener(e -> {
            RegCliente registroCliente = new RegCliente();
            registroCliente.setModal(true);
            registroCliente.setVisible(true);
        });

        JSeparator separator_1 = new JSeparator();
        mnCliente.add(separator_1);

        JMenuItem mntmListadoCliente = new JMenuItem("Listado de Clientes");
        mntmListadoCliente.addActionListener(e -> {
            ListadoCliente ListadoCliente = new ListadoCliente();
            ListadoCliente.setModal(true);
            ListadoCliente.setVisible(true);
        });
        mnCliente.add(mntmListadoCliente);

        JMenu mnContrato = new JMenu("Contratos");
        menuBar.add(mnContrato);

        JMenuItem mntmNuevoContrato = new JMenuItem("Nuevo Contrato");
        mntmNuevoContrato.addActionListener(e -> {
            RegContrato RegContrato = new RegContrato();
            RegContrato.setModal(true);
            RegContrato.setVisible(true);
        });
        mnContrato.add(mntmNuevoContrato);

        JMenuItem mntmListadoContrato = new JMenuItem("Listado de Contratos");
        mntmListadoContrato.addActionListener(e -> {
            ListadoContrato ListadoContrato = new ListadoContrato();
            ListadoContrato.setModal(true);
            ListadoContrato.setVisible(true);
        });
        
        JSeparator separator_2 = new JSeparator();
        mnContrato.add(separator_2);
        mnContrato.add(mntmListadoContrato);

        JMenuItem mntmProrrogar = new JMenuItem("Prorrogar Contrato");
        mntmProrrogar.addActionListener(e -> {
            ProrrogarContrato ProrrogarContrato = new ProrrogarContrato();
            ProrrogarContrato.setModal(true);
            ProrrogarContrato.setVisible(true);
        });
        
        JSeparator separator_3 = new JSeparator();
        mnContrato.add(separator_3);
        mnContrato.add(mntmProrrogar);

        JMenu mnAdmin = new JMenu("Administracion");
        menuBar.add(mnAdmin);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
    }
}
