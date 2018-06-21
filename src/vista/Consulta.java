/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.FiltroDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Filtro;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.runnable;

/**
 *
 * @author María Lourdes
 */
public class Consulta extends JFrame {

    public JLabel lblAfp, lblNombre, lblApellidos, lblEstado;
    public JTextField afp, nombre, apellidos,edad;
    public JComboBox profesion;
    ButtonGroup estado = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;
    public JPanel table;
    public JButton eliminar, insertar, vaciar, actualizar;
    private static final int ANCHOC = 130, ALTOC = 30;
    DefaultTableModel tm;

    public Consulta() {
        super("Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblAfp);
        container.add(lblNombre);
        container.add(lblApellidos);
        container.add(lblEstado);
        container.add(afp);
        container.add(profesion);
        container.add(si);
        container.add(no);
        container.add(edad);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(vaciar);
        container.add(table);
        setSize(600, 600);
        eventos();
    }

    public final void agregarLabels() {
        lblAfp = new JLabel("N° AFP");
        lblNombre = new JLabel("Nombres");
        lblApellidos = new JLabel("Apelllidos");
        lblEstado = new JLabel("Estado");
        lblAfp.setBounds(10, 10, ANCHOC, ALTOC);
        lblNombre.setBounds(10, 60, ANCHOC, ALTOC);
        lblApellidos.setBounds(10, 100, ANCHOC, ALTOC);
        lblEstado.setBounds(10, 140, ANCHOC, ALTOC);

    }

    public final void formulario() {
        afp = new JTextField();
        profesion = new JComboBox();
        apellidos = new JTextField();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        resultados = new JTable();
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        vaciar = new JButton("Vaciar");

        table = new JPanel();
        profesion.addItem("FRAM");
        profesion.addItem("WIX");
        profesion.addItem("Luber Finer");
        profesion.addItem("OSK");
        estado = new ButtonGroup();
        estado.add(si);
        estado.add(no);

        afp.setBounds(140, 10, ANCHOC, ALTOC);
        nombre.setBounds(142, 25, ANCHOC, ALTOC);
        apellidos.setBounds(200, 25, ANCHOC, ALTOC);
        edad.setBounds(140, 60, ANCHOC, ALTOC);
        profesion.setBounds(140, 80, ANCHOC, ALTOC);
        si.setBounds(200, 100, ANCHOC, ALTOC);
        no.setBounds(225, 100, ANCHOC, ALTOC);
        resultados = new JTable();
        table.setBounds(10, 250, 500, 200);
        table.add(new JScrollPane(resultados));

    }

    public void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        tm.addColumn("Codigo");
        tm.addColumn("Marca");
        tm.addColumn("Stock");
        tm.addColumn("Stock en Sucursal");

        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros = fd.readAll;
        for (Filtro fi : filtros) {
            tm.addRow(new Object[]{fi.getAfp(), fi.getNombre(), fi.getApellido(), fi.getApellido(), fi.getEdad(),fi.getProfesion()});
        }
        resultados.setModel(tm);
    }

    public void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(afp.getText(), profesion.getSelectedItem().toString(), Integer
                        .parseInt(apellidos.getText()), true);
                if (no.isSelected()) {
                    f.setEstado(false);
                }
                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de crear el filtro");
                }
            }

        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(afp.getText(), profesion.getSelectedItem().toString(),
                        Integer
                .parseInt(apellidos.getText())
                ,true);
            if (no.isSelected()) {
                    f.setEstado(false);
                }
                if (fd.update(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro Modificado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificarlo");
                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                if (fd.delete(afp.getText())) {
                    JOptionPane.showMessageDialog(null, "Filtro Eliminado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar el filtro");
                }
            }
        });
        
        vaciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    public void limpiarCampos() {
        afp.setText("");
        profesion.setSelectedItem("");
        apellidos.setText("");
        nombre.setText("");
        edad.setText("");
              
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                     new Consulta().setVisible(true);
            }
        });
    }

}
