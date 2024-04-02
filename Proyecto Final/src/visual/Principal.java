package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Panel;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnRegistro = new JMenu("Registro");
		menuBar.add(mnRegistro);
		
		JMenuItem mntmRegistrarTrabajador = new JMenuItem("Registar Trabajador");
		mntmRegistrarTrabajador.addActionListener(e -> {
            regTrabajador registroTrabajador = new regTrabajador();
            registroTrabajador.setModal(true);
            registroTrabajador.setVisible(true);
        });
		mnRegistro.add(mntmRegistrarTrabajador);
		
		JMenuItem mntmRegistrarCliente = new JMenuItem("Registar Cliente");
		mntmRegistrarCliente.addActionListener(e -> {
            regCliente registroCliente = new regCliente();
            registroCliente.setModal(true);
            registroCliente.setVisible(true);
        });
		mnRegistro.add(mntmRegistrarCliente);
		
		JMenu mnProyecto = new JMenu("Proyecto");
		menuBar.add(mnProyecto);
		
		JMenu mnAdmin = new JMenu("Administracion");
		menuBar.add(mnAdmin);
		
		JMenuItem mntmListadoCliente = new JMenuItem("Listado Cliente");
		mntmListadoCliente.addActionListener(e -> {
            ListadoCliente ListadoCliente = new ListadoCliente();
            ListadoCliente.setModal(true);
            ListadoCliente.setVisible(true);
        });
		mnAdmin.add(mntmListadoCliente);
		
		JMenuItem mntmListadoProyecto = new JMenuItem("Listado Proyecto");
		mntmListadoProyecto.addActionListener(e -> {
			ListadoProyecto ListadoProyecto = new ListadoProyecto();
			ListadoProyecto.setModal(true);
			ListadoProyecto.setVisible(true);
        });
		mnAdmin.add(mntmListadoProyecto);
		
		JMenuItem mntmListadoTrabajador = new JMenuItem("Listado Trabajador");
		mntmListadoTrabajador.addActionListener(e -> {
            ListadoTrabajador ListadoTrabajador = new ListadoTrabajador();
            ListadoTrabajador.setModal(true);
            ListadoTrabajador.setVisible(true);
        });
		mnAdmin.add(mntmListadoTrabajador);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Panel panel = new Panel();
		contentPane.add(panel, BorderLayout.CENTER);
	}

}