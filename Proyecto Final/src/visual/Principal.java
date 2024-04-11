package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.JJDCommunications;
import logico.Trabajador;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Principal extends JFrame {

    private JPanel contentPane;
    private Dimension dim;
    
    public static void main(String[] args) {
        try {
            Principal dialog = new Principal(true);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the frame.
     */
    public Principal(boolean isAdmin) {
    	
    	JJDCommunications.getInstance().cargarDatos();
    	
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Guardar los datos al cerrar la ventana
                JJDCommunications.getInstance().guardarDatos();
            }
        });
    	
        setTitle("Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dim = getToolkit().getScreenSize();
        setSize(dim.width, dim.height - 50);
        setLocationRelativeTo(null);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("Arial", Font.BOLD, 16));
        setJMenuBar(menuBar);

        // Menu Usuario
        JMenu mnUsuario = new JMenu("Menu Usuario");
        menuBar.add(mnUsuario);

        // Gestion de Proyectos
        JMenu mnProyectosUsuario = new JMenu("Gestion de Proyectos");
        mnUsuario.add(mnProyectosUsuario);

        JMenuItem mntmCrearProyectoUsuario = new JMenuItem("Crear Nuevo Proyecto");
        mntmCrearProyectoUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Accion para crear un nuevo proyecto
            	abrirCrearProyecto();
            }
        });
        mnProyectosUsuario.add(mntmCrearProyectoUsuario);

        JMenuItem mntmListarProyectosUsuario = new JMenuItem("Listar Todos los Proyectos");
        mntmListarProyectosUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Accion para listar todos los proyectos
            	abrirListadoProyecto();
            }
        });
        mnProyectosUsuario.add(mntmListarProyectosUsuario);

        // Gestion de Contratos
        JMenu mnContratosUsuario = new JMenu("Gestion de Contratos");
        mnUsuario.add(mnContratosUsuario);

        JMenuItem mntmNuevoContratoUsuario = new JMenuItem("Nuevo Contrato");
        mntmNuevoContratoUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Accion para crear un nuevo contrato
            	abrirRegContrato();
            }
        });
        mnContratosUsuario.add(mntmNuevoContratoUsuario);

        JMenuItem mntmProrrogarContratoUsuario = new JMenuItem("Prorrogar Contrato");
        mntmProrrogarContratoUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Accion para prorrogar un contrato
            	abrirProrrogarContrato();
            }
        });
        mnContratosUsuario.add(mntmProrrogarContratoUsuario);

        JMenuItem mntmListarContratosUsuario = new JMenuItem("Listar Todos los Contratos");
        mntmListarContratosUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Accion para listar todos los contratos
            	abrirListadoContrato();
            }
        });
        mnContratosUsuario.add(mntmListarContratosUsuario);

        // Calculo de Costos y Penalizaciones
        JMenu mnCostosPenalizacionesUsuario = new JMenu("Calculo de Costos y Penalizaciones");
        mnUsuario.add(mnCostosPenalizacionesUsuario);

        JMenuItem mntmCalcularCostoProyectoUsuario = new JMenuItem("Calcular Costo de un Proyecto");
        mntmCalcularCostoProyectoUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Accion para calcular el costo de un proyecto
            	abrirCCostoProyecto();
            }
        });
        mnCostosPenalizacionesUsuario.add(mntmCalcularCostoProyectoUsuario);
        

        // Menu Administrativo
        JMenu mnAdministrativo = new JMenu("Menu Administrativo");
        menuBar.add(mnAdministrativo);
        
        // Codigo agregado para habilitar/deshabilitar menu "Menu Administrativo"
        if (isAdmin) {
            mnAdministrativo.setEnabled(true);
        } else {
            mnAdministrativo.setEnabled(false);
            // Cambia el tono de color del menu administrativo
            mnAdministrativo.setForeground(Color.LIGHT_GRAY);
        }

        // Gestion de Trabajadores
        JMenu mnTrabajadoresAdministrativo = new JMenu("Gestion de Trabajadores");
        mnAdministrativo.add(mnTrabajadoresAdministrativo);

        JMenuItem mntmAgregarTrabajadorAdministrativo = new JMenuItem("Agregar Nuevo Trabajador");
        mntmAgregarTrabajadorAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Accion para agregar un nuevo trabajador
            	abrirRegTrabajador();
            }
        });
        mnTrabajadoresAdministrativo.add(mntmAgregarTrabajadorAdministrativo);

        JMenuItem mntmListarTrabajadoresAdministrativo = new JMenuItem("Listar Todos los Trabajadores");
        mntmListarTrabajadoresAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Accion para listar todos los trabajadores
            	abrirListadoTrabajador();
            }
        });
        mnTrabajadoresAdministrativo.add(mntmListarTrabajadoresAdministrativo);
        
        JMenuItem mntmConsultarEvaluacionTrabajador = new JMenuItem("Consultar Evaluacion de Trabajador");
        mntmConsultarEvaluacionTrabajador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Accion para listar todos los trabajadores
            	abrirConsultarEvaluacionTrabajador(); // Asegúrate de tener una variable 'trabajador' disponible aquí
            }
        });
        mnTrabajadoresAdministrativo.add(mntmConsultarEvaluacionTrabajador);


        // Gestion de Proyectos
        JMenu mnProyectosAdministrativo = new JMenu("Gestion de Proyectos");
        mnAdministrativo.add(mnProyectosAdministrativo);

        JMenuItem mntmCrearProyectoAdministrativo = new JMenuItem("Crear Nuevo Proyecto");
        mntmCrearProyectoAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Accion para crear un nuevo proyecto
            	abrirCrearProyecto();
            }
        });
        mnProyectosAdministrativo.add(mntmCrearProyectoAdministrativo);

        JMenuItem mntmListarProyectosAdministrativo = new JMenuItem("Listar Todos los Proyectos");
        mntmListarProyectosAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Accion para listar todos los proyectos
            	abrirListadoProyecto();
            }
        });
        mnProyectosAdministrativo.add(mntmListarProyectosAdministrativo);
        
        JMenuItem mntmEntregarProyecto = new JMenuItem("Entregar Proyecto");
        mntmEntregarProyecto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Accion para listar todos los proyectos
            	abrirEntregarProyecto();
            }
        });
        mnProyectosAdministrativo.add(mntmEntregarProyecto);

        // Gestion de Clientes
        JMenu mnClientesAdministrativo = new JMenu("Gestion de Clientes");
        mnAdministrativo.add(mnClientesAdministrativo);

        JMenuItem mntmAgregarClienteAdministrativo = new JMenuItem("Agregar Nuevo Cliente");
        mntmAgregarClienteAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Accion para agregar un nuevo cliente
            	abrirRegCliente();
            }
        });
        mnClientesAdministrativo.add(mntmAgregarClienteAdministrativo);

        JMenuItem mntmListarClientesAdministrativo = new JMenuItem("Listar Todos los Clientes");
        mntmListarClientesAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Accion para listar todos los clientes
            	abrirListadoCliente();
            }
        });
        mnClientesAdministrativo.add(mntmListarClientesAdministrativo);

        // Gestion de Contratos
        JMenu mnContratosAdministrativo = new JMenu("Gestion de Contratos");
        mnAdministrativo.add(mnContratosAdministrativo);

        JMenuItem mntmNuevoContratoAdministrativo = new JMenuItem("Nuevo Contrato");
        mntmNuevoContratoAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Accion para crear un nuevo contrato
            	abrirRegContrato();
            }
        });
        mnContratosAdministrativo.add(mntmNuevoContratoAdministrativo);

        JMenuItem mntmProrrogarContratoAdministrativo = new JMenuItem("Prorrogar Contrato");
        mntmProrrogarContratoAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Accion para prorrogar un contrato
            	abrirProrrogarContrato();
            }
        });
        mnContratosAdministrativo.add(mntmProrrogarContratoAdministrativo);

        JMenuItem mntmListarContratosAdministrativo = new JMenuItem("Listar Todos los Contratos");
        mntmListarContratosAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Accion para listar todos los contratos
            	abrirListadoContrato();
            }
        });
        mnContratosAdministrativo.add(mntmListarContratosAdministrativo);

        // Calculo de Costos y Penalizaciones
        JMenu mnCostosPenalizacionesAdministrativo = new JMenu("Calculo de Costos y Penalizaciones");
        mnAdministrativo.add(mnCostosPenalizacionesAdministrativo);

        JMenuItem mntmCalcularCostoProyectoAdministrativo = new JMenuItem("Calcular Costo de un Proyecto");
        mntmCalcularCostoProyectoAdministrativo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Accion para calcular el costo de un proyecto
            	abrirCCostoProyecto();
            }
        });
        mnCostosPenalizacionesAdministrativo.add(mntmCalcularCostoProyectoAdministrativo);
       

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

    private void abrirCCostoProyecto() {
        CCostoProyecto ccostoProyecto = new CCostoProyecto();
        ccostoProyecto.setModal(true);
        ccostoProyecto.setVisible(true);
    }
    
    private void abrirEntregarProyecto() {
        EntregarProyecto entregarProyecto = new EntregarProyecto();
        entregarProyecto.setModal(true);
        entregarProyecto.setVisible(true);
    }

    private void abrirConsultarEvaluacionTrabajador() {
        ConsultarEvaluacionTrabajador consultaEvaluacionProyecto = new ConsultarEvaluacionTrabajador();
        consultaEvaluacionProyecto.setTrabajadores(JJDCommunications.getInstance().getListaTrabajadores());
        consultaEvaluacionProyecto.setVisible(true);
    }





}