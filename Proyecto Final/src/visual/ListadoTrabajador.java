package visual;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;


import logico.Disenador;
import logico.JefeProyecto;
import logico.Planificador;
import logico.Programador;
import logico.Trabajador;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListadoTrabajador extends JDialog {

 private DefaultTableModel model;
 private JComboBox<String> comboBox;
 private ArrayList<Trabajador> trabajadores;

 String[] headers = {"ID", "Nombre", "Apellido", "Direcci�n", "Sexo", "Edad", "Salario por Hora", "Evaluaci�n"};

 public static void main(String[] args) {
     try {
         ListadoTrabajador dialog = new ListadoTrabajador();
         dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         dialog.setVisible(true);
     } catch (Exception e) {
         e.printStackTrace();
     }
 }

 public ListadoTrabajador() {
     setSize(841, 400);
     setLocationRelativeTo(null);

     JPanel contentPanel = new JPanel();
     contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
     contentPanel.setLayout(new BorderLayout());
     getContentPane().add(contentPanel, BorderLayout.CENTER);

     JPanel panel = new JPanel();
     panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 15));
     contentPanel.add(panel, BorderLayout.NORTH);

     JLabel lblTipoDeTrabajador = new JLabel("Tipo de Trabajador:");
     panel.add(lblTipoDeTrabajador);

     comboBox = new JComboBox<>();
     comboBox.addActionListener(e -> loadTrabajadores(comboBox.getSelectedIndex()));
     comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"<Todos>", "Jefe de Proyecto", "Disenador", "Programador", "Planificador"}));
     panel.add(comboBox);

     JPanel tablePanel = new JPanel();
     tablePanel.setLayout(new BorderLayout(0, 0));
     contentPanel.add(tablePanel, BorderLayout.CENTER);

     JScrollPane scrollPane = new JScrollPane();
     tablePanel.add(scrollPane, BorderLayout.CENTER);

     model = new DefaultTableModel() {
         @Override
         public boolean isCellEditable(int row, int column) {
             return false;
         }
     };
     model.setColumnIdentifiers(headers);
     JTable table = new JTable(model);
     table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
     table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
     table.getTableHeader().setReorderingAllowed(false);
     table.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
             ordenarTabla(table.columnAtPoint(e.getPoint()));
         }
     });
     scrollPane.setViewportView(table);

     // Ajuste del ancho de las columnas y permitir redimensionarlas
     for (int i = 0; i < headers.length; i++) {
         table.getColumnModel().getColumn(i).setPreferredWidth(100);
     }
     table.getTableHeader().setResizingAllowed(true);

     // Inicializar la lista de trabajadores
     trabajadores = new ArrayList<>();
     // Agregar algunos trabajadores de ejemplo
     // Aqu� deber�as cargar tus trabajadores desde tu base de datos o fuente de datos
     cargarTrabajadoresEjemplo();
     // Cargar todos los trabajadores por defecto
     loadTrabajadores(0);
 }

 private void loadTrabajadores(int index) {
     // Limpiar modelo de tabla antes de cargar nuevos datos
     model.setRowCount(0);

     // Cargar datos de trabajadores seg�n el tipo seleccionado
     for (Trabajador trabajador : trabajadores) {
         boolean agregar = false;
         switch (index) {
             case 0: // Todos
                 agregar = true;
                 break;
             case 1: // Jefe de Proyecto
                 agregar = trabajador instanceof JefeProyecto;
                 break;
             case 2: // Disenador
                 agregar = trabajador instanceof Disenador;
                 break;
             case 3: // Programador
                 agregar = trabajador instanceof Programador;
                 break;
             case 4: // Planificador
                 agregar = trabajador instanceof Planificador;
                 break;
         }
         if (agregar) {
             model.addRow(new Object[]{
                     trabajador.getId(),
                     trabajador.getNombre(),
                     trabajador.getApellido(),
                     trabajador.getDireccion(),
                     trabajador.getSexo(),
                     trabajador.getEdad(),
                     trabajador.getSalarioHora(),
                     trabajador.getEvaluacion()
             });
         }
     }
 }


 private void ordenarTabla(int columnIndex) {
     TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
     sorter.setModel(model);
     sorter.setSortsOnUpdates(true);
     sorter.toggleSortOrder(columnIndex);
 }

 // M�todo para cargar algunos trabajadores de ejemplo (simulaci�n)
 private void cargarTrabajadoresEjemplo() {
     // Trabajadores de ejemplo para cada tipo
     trabajadores.add(new JefeProyecto("1", "Juan", "Perez", "Calle 123", 'M', 35, 100, "Excelente", 10, 10)); // Jefe de Proyecto
     trabajadores.add(new Disenador("2", "Maria", "Gomez", "Avenida 456", 'F', 28, 80, "Bueno", 5)); // Disenador
     trabajadores.add(new Programador("3", "Carlos", "Lopez", "Calle Principal", 'M', 30, 90, "Muy Bueno", new ArrayList<>(Arrays.asList("Java", "Python")), 3)); // Programador
     trabajadores.add(new Planificador("4", "Ana", "Martinez", "Avenida Central", 'F', 40, 95, "Excelente", 7, 15)); // Planificador
 }
}
