package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.JJDCommunications;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Principal extends JFrame {

    private JPanel contentPane;
    private boolean isAdmin;
    private Dimension dim;


    /**
     * Launch the application.
     */
    
    public static void main(String[] args) {
        try {
            // Inicializar la clase controladora
        	JJDCommunications controlador = new JJDCommunications();

            // Cargar datos desde el controlador
        	JJDCommunications.cargarDatos();

            // Mostrar el di�logo de inicio de sesi�n
            Principal dialog = new Principal(true);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);

            // Guardar datos al cerrar la aplicaci�n
            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    // Guardar datos a trav�s del controlador
                	controlador.guardarDatos();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * Create the frame.
     */
    public Principal(boolean isAdmin) {
        setTitle("Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dim = getToolkit().getScreenSize();
        setSize(dim.width, dim.height - 50);
        setLocationRelativeTo(null);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("Arial", Font.BOLD, 16));
        setJMenuBar(menuBar);

        // Men� Usuario
        JMenu mnUsuario = new JMenu("Men� Usuario");
        menuBar.add(mnUsuario);

        // Gesti�n de Proyectos
        JMenu mnProyectosUsuario = new JMenu("Gesti�n de Proyectos");
        mnUsuario.add(mnProyectosUsuario);

        JMenuItem mntmCrearProyectoUsuario = new JMenuItem("Crear Nuevo Proyecto");
        mntmCrearProyectoUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acci�n para crear un nuevo proyecto
            	abrirCrearProyecto();
            }
        });
        mnProyectosUsuario.add(mntmCrearProyectoUsuario);

        JMenuItem mntmVerDetallesProyectoUsuario = new JMenuItem("Ver Detalles de un Proyecto Existente");
        mntmVerDetallesProyectoUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acci�n para ver detalles de un proyecto existente
            }
        });
        mnProyectosUsuario.add(mntmVerDetallesProyectoUsuario);

        JMenuItem mntmListarProyectosUsuario = new JMenuItem("Listar Todos los Proyectos");
        mntmListarProyectosUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acci�n para listar todos los proyectos
            	abrirListadoProyecto();
            }
        });
        mnProyectosUsuario.add(mntmListarProyectosUsuario);

        // Gesti�n de Contratos
        JMenu mnContratosUsuario = new JMenu("Gesti�n de Contratos");
        mnUsuario.add(mnContratosUsuario);

        JMenuItem mntmNuevoContratoUsuario = new JMenuItem("Nuevo Contrato");
        mntmNuevoContratoUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acci�n para crear un nuevo contrato
            	abrirRegContrato();
            }
        });
        mnContratosUsuario.add(mntmNuevoContratoUsuario);

        JMenuItem mntmProrrogarContratoUsuario = new JMenuItem("Prorrogar Contrato");
        mntmProrrogarContratoUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acci�n para prorrogar un contrato
            	abrirProrrogarContrato();
            }
        });
        mnContratosUsuario.add(mntmProrrogarContratoUsuario);

        JMenuItem mntmListarContratosUsuario = new JMenuItem("Listar Todos los Contratos");
        mntmListarContratosUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acci�n para listar todos los contratos
            	abrirListadoContrato();
            }
        });
        mnContratosUsuario.add(mntmListarContratosUsuario);

        // C�lculo de Costos y Penalizaciones
        JMenu mnCostosPenalizacionesUsuario = new JMenu("C�lculo de Costos y Penalizaciones");
        mnUsuario.add(mnCostosPenalizacionesUsuario);

        JMenuItem mntmCalcularCostoProyectoUsuario = new JMenuItem("Calcular Costo de un Proyecto");
        mntmCalcularCostoProyectoUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acci�n para calcular el costo de un proyecto
            	abrirCostoProyecto();
            }
        });
        mnCostosPenalizacionesUsuario.add(mntmCalcularCostoProyectoUsuario);

        JMenuItem mntmCalcularPenalizacionUsuario = new JMenuItem("Calcular Penalizaci�n por Retraso en Entrega");
        mntmCalcularPenalizacionUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acci�n para calcular la penalizaci�n por retraso en entrega
            	abrirPenalizacion();
            }
        });
        mnCostosPenalizacionesUsuario.add(mntmCalcularPenalizacionUsuario);
        
<<<<<<< HEAD
        JSeparator separator_4 = new JSeparator();
        mnTrabajadore.add(separator_4); 

=======

        // Men� Administrativo
        JMenu mnAdministrativo = new JMenu("Men� Administrativo");
        menuBar.add(mnAdministrativo);
        
        // C�digo agregado para habilitar/deshabilitar men� "Men� Administrativo"
        if (isAdmin) {
            mnAdministrativo.setEnabled(true);
        } else {
            mnAdministrativo.setEnabled(false);
            // Cambia el tono de color del men� administrativo
            mnAdministrativo.setForeground(Color.LIGHT_GRAY);
        }

        // Gesti�n de Trabajadores
        JMenu mnTrabajadoresAdministrativo = new JMenu("Gesti�n de Trabajadores");
        mnAdministrativo.add(mnTrabajadoresAdministrativo);

        JMenuItem mntmAgregarTrabajadorAdministrativo = new JMenuItem("Agregar Nuevo Trabajador");
        mntmAgregarTrabajadorAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acci�n para agregar un nuevo trabajador
            	abrirRegTrabajador();
            }
        });
        mnTrabajadoresAdministrativo.add(mntmAgregarTrabajadorAdministrativo);
>>>>>>> branch 'master' of https://github.com/Alexp-9103/POO-FINAL.git

        JMenuItem mntmListarTrabajadoresAdministrativo = new JMenuItem("Listar Todos los Trabajadores");
        mntmListarTrabajadoresAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acci�n para listar todos los trabajadores
            	abrirListadoTrabajador();
            }
        });
        mnTrabajadoresAdministrativo.add(mntmListarTrabajadoresAdministrativo);

<<<<<<< HEAD
        JMenuItem mntmCrearProyecto = new JMenuItem("Crear Proyecto");
        mnProyecto.add(mntmCrearProyecto);
        mntmCrearProyecto.addActionListener(e -> {
            CrearProyecto crearProyecto = new CrearProyecto();
            crearProyecto.setModal(true);
            crearProyecto.setVisible(true);
        });

        JMenu mnCliente = new JMenu("Clientes");
        menuBar.add(mnCliente);
=======
        // Gesti�n de Proyectos
        JMenu mnProyectosAdministrativo = new JMenu("Gesti�n de Proyectos");
        mnAdministrativo.add(mnProyectosAdministrativo);
>>>>>>> branch 'master' of https://github.com/Alexp-9103/POO-FINAL.git

        JMenuItem mntmCrearProyectoAdministrativo = new JMenuItem("Crear Nuevo Proyecto");
        mntmCrearProyectoAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acci�n para crear un nuevo proyecto
            	abrirCrearProyecto();
            }
        });
        mnProyectosAdministrativo.add(mntmCrearProyectoAdministrativo);

        JMenuItem mntmVerDetallesProyectoAdministrativo = new JMenuItem("Ver Detalles de un Proyecto Existente");
        mntmVerDetallesProyectoAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acci�n para ver detalles de un proyecto existente
            	abrirDetallesProyecto();
            }
        });
        mnProyectosAdministrativo.add(mntmVerDetallesProyectoAdministrativo);

        JMenuItem mntmListarProyectosAdministrativo = new JMenuItem("Listar Todos los Proyectos");
        mntmListarProyectosAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acci�n para listar todos los proyectos
            	abrirListadoProyecto();
            }
        });
        mnProyectosAdministrativo.add(mntmListarProyectosAdministrativo);

        // Gesti�n de Clientes
        JMenu mnClientesAdministrativo = new JMenu("Gesti�n de Clientes");
        mnAdministrativo.add(mnClientesAdministrativo);

        JMenuItem mntmAgregarClienteAdministrativo = new JMenuItem("Agregar Nuevo Cliente");
        mntmAgregarClienteAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acci�n para agregar un nuevo cliente
            	abrirRegCliente();
            }
        });
        mnClientesAdministrativo.add(mntmAgregarClienteAdministrativo);

        JMenuItem mntmListarClientesAdministrativo = new JMenuItem("Listar Todos los Clientes");
        mntmListarClientesAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acci�n para listar todos los clientes
            	abrirListadoCliente();
            }
        });
        mnClientesAdministrativo.add(mntmListarClientesAdministrativo);

        // Gesti�n de Contratos
        JMenu mnContratosAdministrativo = new JMenu("Gesti�n de Contratos");
        mnAdministrativo.add(mnContratosAdministrativo);

        JMenuItem mntmNuevoContratoAdministrativo = new JMenuItem("Nuevo Contrato");
        mntmNuevoContratoAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acci�n para crear un nuevo contrato
            	abrirRegContrato();
            }
        });
        mnContratosAdministrativo.add(mntmNuevoContratoAdministrativo);

        JMenuItem mntmProrrogarContratoAdministrativo = new JMenuItem("Prorrogar Contrato");
        mntmProrrogarContratoAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acci�n para prorrogar un contrato
            	abrirProrrogarContrato();
            }
        });
        mnContratosAdministrativo.add(mntmProrrogarContratoAdministrativo);

        JMenuItem mntmListarContratosAdministrativo = new JMenuItem("Listar Todos los Contratos");
        mntmListarContratosAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acci�n para listar todos los contratos
            	abrirListadoContrato();
            }
        });
        mnContratosAdministrativo.add(mntmListarContratosAdministrativo);

        // C�lculo de Costos y Penalizaciones
        JMenu mnCostosPenalizacionesAdministrativo = new JMenu("C�lculo de Costos y Penalizaciones");
        mnAdministrativo.add(mnCostosPenalizacionesAdministrativo);

        JMenuItem mntmCalcularCostoProyectoAdministrativo = new JMenuItem("Calcular Costo de un Proyecto");
        mntmCalcularCostoProyectoAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acci�n para calcular el costo de un proyecto
            	abrirCostoProyecto();
            }
        });
        mnCostosPenalizacionesAdministrativo.add(mntmCalcularCostoProyectoAdministrativo);

        JMenuItem mntmCalcularPenalizacionAdministrativo = new JMenuItem("Calcular Penalizaci�n por Retraso en Entrega");
        mntmCalcularPenalizacionAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acci�n para calcular la penalizaci�n por retraso en entrega
            	abrirPenalizacion();
            }
        });
        mnCostosPenalizacionesAdministrativo.add(mntmCalcularPenalizacionAdministrativo);
        


        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
    }

    private void abrirRegTrabajador() {
        RegTrabajador registroTrabajador = new RegTrabajador();
        registroTrabajador.setModal(true);
        registroTrabajador.setVisible(true);
    }

    private void abrirListadoTrabajador() {
        ListadoTrabajador listadoTrabajador = new ListadoTrabajador();
        listadoTrabajador.setModal(true);
        listadoTrabajador.setVisible(true);
    }

    private void abrirCrearProyecto() {
        CrearProyecto crearProyecto = new CrearProyecto();
        crearProyecto.setModal(true);
        crearProyecto.setVisible(true);
    }

    private void abrirListadoProyecto() {
        ListadoProyecto listadoProyecto = new ListadoProyecto();
        listadoProyecto.setModal(true);
        listadoProyecto.setVisible(true);
    }

    private void abrirRegCliente() {
        RegCliente registroCliente = new RegCliente();
        registroCliente.setModal(true);
        registroCliente.setVisible(true);
    }

    private void abrirListadoCliente() {
        ListadoCliente listadoCliente = new ListadoCliente();
        listadoCliente.setModal(true);
        listadoCliente.setVisible(true);
    }

    private void abrirRegContrato() {
        RegContrato regContrato = new RegContrato();
        regContrato.setModal(true);
        regContrato.setVisible(true);
    }

    private void abrirListadoContrato() {
        ListadoContrato listadoContrato = new ListadoContrato();
        listadoContrato.setModal(true);
        listadoContrato.setVisible(true);
    }
    
    private void abrirProrrogarContrato() {
        ProrrogarContrato prorrogarContrato = new ProrrogarContrato();
        prorrogarContrato.setModal(true);
        prorrogarContrato.setVisible(true);
    }

    private void abrirDetallesProyecto() {
        DetallesProyecto detallesProyecto = new DetallesProyecto();
        detallesProyecto.setModal(true);
        detallesProyecto.setVisible(true);
    }

    private void abrirCostoProyecto() {
        CostoProyecto costoProyecto = new CostoProyecto();
        costoProyecto.setModal(true);
        costoProyecto.setVisible(true);
    }

    private void abrirPenalizacion() {
        Penalizacion penalizacion = new Penalizacion();
        penalizacion.setModal(true);
        penalizacion.setVisible(true);
    }

}
