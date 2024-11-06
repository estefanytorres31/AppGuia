package guia3;


import clases.ConexionMySQL;
import clases.Personal;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class AppPersonal extends JFrame implements ActionListener {
    private JLabel lbl_titulo, lbl_1, lbl_2, lbl_3, lbl_4, lbl_5, lbl_6, lbl_7, lbl_8, lbl_9;
    private JTextField txt_dni, txt_nombre, txt_app, txt_apm, txt_sueldo, txt_search;

    private JDateChooser dc_fn;

    private JRadioButton rbt_genero_f, rbt_genero_m;
    private ButtonGroup bg_genero;

    private JComboBox cbo_cargo;

    private JButton btn_nuevo, btn_agregar, btn_editar, btn_borrar, btn_cerrar, btn_search;

    private JTable tb_personal;
    private JScrollPane scr_personal;
   


    private final ConexionMySQL cn = new ConexionMySQL();

    private ArrayList<String[]> arr_cargo;

    public AppPersonal() {
        super();

        IniciarFormulario();
        IniciarControles();
        LimpiarDatos();
        MostrarDatos();
    }

    private void IniciarFormulario() {
        this.setTitle("SENATI - ETI");
        this.setSize(430, 540);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void IniciarControles() {
        lbl_titulo = new JLabel();
        lbl_titulo.setText("PERSONAL");
        lbl_titulo.setFont(new Font("Calibri", Font.BOLD, 24));
        lbl_titulo.setForeground(Color.BLUE);
        lbl_titulo.setBounds(10, 15, 300, 25);

        lbl_1 = new JLabel();
        lbl_1.setText("Número DNI");
        lbl_1.setBounds(20, 50, 120, 25);

        txt_dni = new JTextField();
        txt_dni.setHorizontalAlignment(JTextField.CENTER);
        txt_dni.setBounds(150, 50, 80, 25);

        lbl_2 = new JLabel();
        lbl_2.setText("Apellido Paterno");
        lbl_2.setBounds(20, 80, 110, 25);

        txt_app = new JTextField();
        txt_app.setBounds(20, 110, 110, 25);

        lbl_3 = new JLabel();
        lbl_3.setText("Apellido Materno");
        lbl_3.setBounds(150, 80, 110, 25);

        txt_apm = new JTextField();
        txt_apm.setBounds(150, 110, 110, 25);

        lbl_4 = new JLabel();
        lbl_4.setText("Nombre");
        lbl_4.setBounds(280, 80, 110, 25);

        txt_nombre = new JTextField();
        txt_nombre.setBounds(280, 110, 110, 25);

        lbl_5 = new JLabel();
        lbl_5.setText("Género");
        lbl_5.setBounds(20, 140, 110, 25);

        bg_genero = new ButtonGroup();

        rbt_genero_f = new JRadioButton("Femenino");
        rbt_genero_f.setBounds(150, 140, 110, 25);
        bg_genero.add(rbt_genero_f);
        rbt_genero_f.addActionListener(this);

        rbt_genero_m = new JRadioButton("Masculino");
        rbt_genero_m.setBounds(280, 140, 110, 25);
        bg_genero.add(rbt_genero_m);
        rbt_genero_m.addActionListener(this);

        lbl_6 = new JLabel();
        lbl_6.setText("Fecha Nacimiento");
        lbl_6.setBounds(20, 170, 110, 25);

        dc_fn = new JDateChooser();
        dc_fn.setDateFormatString("dd/MM/yyyy");
        Calendar calendar  = Calendar.getInstance();
        calendar.set(2006,Calendar.NOVEMBER, 5);
        Date fecha_maxima = calendar.getTime();
        dc_fn.setMaxSelectableDate(fecha_maxima);
        dc_fn.setBounds(20, 200, 110, 25);

        lbl_7 = new JLabel();
        lbl_7.setText("Sueldo");
        lbl_7.setBounds(150, 170, 110, 25);

        txt_sueldo = new JTextField();
        txt_sueldo.setHorizontalAlignment(JTextField.RIGHT);
        txt_sueldo.setBounds(150, 200, 110, 25);

        lbl_8 = new JLabel();
        lbl_8.setText("Cargo");
        lbl_8.setBounds(280, 170, 110, 25);

        cbo_cargo = new JComboBox();
        cbo_cargo.setBounds(280, 200, 110, 25);

        arr_cargo = this.ObtenerCargo();
        
        for (String[] cargo: arr_cargo) {
            cbo_cargo.addItem(cargo[1]);
        }

        btn_nuevo = new JButton();
        btn_nuevo.setText("NUEVO");
        btn_nuevo.setBounds(10, 240, 90, 25);
        btn_nuevo.addActionListener(this);

        btn_agregar = new JButton();
        btn_agregar.setText("AGREGAR");
        btn_agregar.setBounds(110, 240, 90, 25);
        btn_agregar.addActionListener(this);

        btn_editar = new JButton();
        btn_editar.setText("EDITAR");
        btn_editar.setBounds(210, 240, 90, 25);
        btn_editar.addActionListener(this);

        btn_borrar = new JButton();
        btn_borrar.setText("BORRAR");
        btn_borrar.setBounds(310, 240, 90, 25);
        btn_borrar.addActionListener(this);
        
        lbl_9 = new JLabel("Buscar:");
        lbl_9.setBounds(20, 280, 60, 25);  

        txt_search = new JTextField();
        txt_search.setBounds(80, 280, 150, 25); 

        btn_search = new JButton("Filtrar");
        btn_search.setBounds(240, 280, 90, 25); 
        btn_search.addActionListener(this);

        btn_cerrar = new JButton();
        btn_cerrar.setText("CERRAR");
        btn_cerrar.setFont(new Font("Consolas", Font.BOLD, 14));
        btn_cerrar.setBackground(Color.RED);
        btn_cerrar.setForeground(Color.WHITE);
        btn_cerrar.setBounds(110, 510, 180, 30);
        btn_cerrar.addActionListener(this);

        tb_personal = new JTable();

        tb_personal.setRowHeight(20);
        tb_personal.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        scr_personal = new JScrollPane(tb_personal);
        scr_personal.setBounds(10, 310, 390, 150);

        // Agregar los controles al JFrame
        this.add(lbl_titulo);
        this.add(lbl_1);
        this.add(txt_dni);
        this.add(lbl_2);
        this.add(txt_app);
        this.add(lbl_3);
        this.add(txt_apm);
        this.add(lbl_4);
        this.add(txt_nombre);
        this.add(lbl_5);
        this.add(rbt_genero_f);
        this.add(rbt_genero_m);
        this.add(lbl_6);
        this.add(dc_fn);
        this.add(lbl_7);
        this.add(txt_sueldo);
        this.add(lbl_8);
        this.add(cbo_cargo);
        this.add(btn_nuevo);
        this.add(btn_agregar);
        this.add(btn_editar);
        this.add(btn_borrar);
        this.add(btn_cerrar);
        this.add(scr_personal);
        this.add(lbl_9);
        this.add(txt_search);
        this.add(btn_search);

        ControladorTxt ctxt = new ControladorTxt();

        txt_dni.addKeyListener(ctxt);
        txt_app.addKeyListener(ctxt);
        txt_apm.addKeyListener(ctxt);
        txt_nombre.addKeyListener(ctxt);

        ControladorClick click = new ControladorClick();

        tb_personal.addMouseListener(click);
    }

    private ArrayList<String[]> ObtenerCargo() {
        ArrayList<String[]> arr_lista = new ArrayList<>();

        String cad_sql = "call sp_listar_cargo();";
        
        Connection cnx;

        try {
            cnx = cn.Conectar();

            java.sql.PreparedStatement pstm;
            pstm = cnx.prepareStatement(cad_sql);

            try (ResultSet rs = pstm.executeQuery()) {

                arr_lista.add(new String[]{"", "Seleccione cargo"});

                while (rs.next()) {
                    String codigo = rs.getString("codigo_cargo");
                    String nombre = rs.getString("cargo");
                    
                    arr_lista.add(new String[]{codigo, nombre});
                }
            }

            pstm.close();
            cnx.close();
        } catch (SQLException ex) {
        }
        
        return arr_lista;
    }

    private void MostrarDatos() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        modelo.setRowCount(0);

        Connection cnx = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            cnx = cn.Conectar();
            stm = cnx.createStatement();
            
            rs = stm.executeQuery("call sp_listar_personal();");
            
            int nc = rs.getMetaData().getColumnCount();

            for (int i = 1; i <= nc; i++) {
                modelo.addColumn(rs.getMetaData().getColumnName(i));
            }

            while (rs.next()) {
                Object[] arr_filas = new Object[nc];

                for (int i = 0; i < nc; i++) {
                    arr_filas[i] = rs.getObject(i + 1);
                }

                modelo.addRow(arr_filas);
            }

        } catch (SQLException e1) {

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (cnx != null) {
                    cnx.close();
                }
            } catch (SQLException e2) {

            }
        }

        tb_personal.setModel(modelo);
        tb_personal.setRowHeight(22);
        
        DefaultTableCellRenderer alinearCentro = new DefaultTableCellRenderer();
        
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
        
        TableColumnModel arr_col = tb_personal.getColumnModel();
        
        arr_col.getColumn(0).setPreferredWidth(50);
        arr_col.getColumn(0).setCellRenderer(alinearCentro);
        
        arr_col.getColumn(1).setPreferredWidth(120);
    }

    private class ControladorTxt implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getSource() == txt_dni && txt_dni.getText().length() == 8) {
                e.consume();
            }
            if (e.getSource() == txt_app && txt_app.getText().length() == 25) {
                e.consume();
            }
            if (e.getSource() == txt_apm && txt_apm.getText().length() == 25) {
                e.consume();
            }
            if (e.getSource() == txt_nombre && txt_nombre.getText().length() == 25) {
                e.consume();
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    private class ControladorClick extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            int registro = tb_personal.getSelectedRow();

            txt_dni.setEditable(false);

            String dni = (String) tb_personal.getValueAt(registro, 0);

            String cad_sql = "call sp_buscar_personal(?);";
            
            Connection cnx;

            try {
                cnx = cn.Conectar();

                java.sql.PreparedStatement pstm;

                pstm = cnx.prepareStatement(cad_sql);
                pstm.setString(1, dni);

                ResultSet rs = pstm.executeQuery();

                if (rs.next()) {
                    String app = rs.getString("ap_paterno");
                    String apm = rs.getString("ap_materno");
                    String nom = rs.getString("nombre");
                    char genero = rs.getString("genero").charAt(0); 
                    
                    
                    Date fn = rs.getDate("fecha_nacimiento");
                    float sue = rs.getFloat("sueldo");
                    String crgnom = rs.getString("codigo_cargo");
                    
                    txt_dni.setText(dni);
                    txt_app.setText(app);
                    txt_apm.setText(apm);
                    txt_nombre.setText(nom);
                    
                     if (genero == 'F') {
                    rbt_genero_f.setSelected(true);
                    } else if (genero == 'M') {
                    rbt_genero_m.setSelected(true);
                    }

                    dc_fn.setDate(fn);

                    txt_sueldo.setText(String.valueOf(sue));
                    for (String[] cargo : arr_cargo) {
                    if (cargo[0].equals(crgnom)) {
                    cbo_cargo.setSelectedItem(cargo[1]); 
                    break;
                }
            }
                }
            } catch (SQLException ex) {
            }
        }
    }

    @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btn_search) {
        FiltrarPersonal();
    }
    if (e.getSource() == btn_cerrar) {
        dispose();
        return;
    }

    if (e.getSource() == btn_nuevo) {
        LimpiarDatos();
        return;
    }

    if (e.getSource() == btn_agregar || e.getSource() == btn_editar || e.getSource() == btn_borrar) {
        if (txt_dni.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese número de DNI");
            txt_dni.requestFocus();
            return;
        }

        if (txt_app.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el Apellido Paterno");
            txt_app.requestFocus();
            return;
        }

        if (txt_apm.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el Apellido Materno");
            txt_apm.requestFocus();
            return;
        }

        if (txt_nombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el Nombre");
            txt_nombre.requestFocus();
            return;
        }

        if (!rbt_genero_f.isSelected() && !rbt_genero_m.isSelected()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Genero");
            return;
        }

        if (dc_fn.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Ingrese la fecha de nacimiento");
            dc_fn.requestFocus();
            return;
        } else {
            Date selectedDate = dc_fn.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);
            int year = calendar.get(Calendar.YEAR);
            if (year >= 2007) {
                JOptionPane.showMessageDialog(null, "La persona que intenta insertar es menor de edad");
                dc_fn.setDate(null);
                return;
            }
        }

        if (txt_sueldo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el Sueldo");
            txt_sueldo.requestFocus();
            return;
        }

        if (cbo_cargo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione el Cargo");
            cbo_cargo.requestFocus();
            return;
        }

        Personal personal = new Personal();

        personal.setDNI(txt_dni.getText());
        personal.setApPaterno(txt_app.getText());
        personal.setApMaterno(txt_apm.getText());
        personal.setNombre(txt_nombre.getText());

        if (rbt_genero_f.isSelected()) {
            personal.setGenero('F');
        }
        if (rbt_genero_m.isSelected()) {
            personal.setGenero('M');
        }

        Date fn_1 = dc_fn.getDate();
        long fn_2 = fn_1.getTime();
        java.sql.Date fn_3 = new java.sql.Date(fn_2);

        personal.setFechaNacimiento(fn_3);
        personal.setSueldo(Float.parseFloat(txt_sueldo.getText()));

        String cc = "", nc = cbo_cargo.getSelectedItem().toString();
        for (String[] cargo : arr_cargo) {
            if (nc.equals(cargo[1])) {
                cc = cargo[0];
                break;
            }
        }
        personal.setCodigoCargo(cc);

        Connection cnx = null;
        java.sql.PreparedStatement pstm = null;

        try {
            cnx = cn.Conectar();
            String cad_sql;

            if (e.getSource() == btn_agregar) {
                cad_sql = "call sp_registrar_personal(?, ?, ?, ?, ?, ?, ?, ?)";
                pstm = cnx.prepareStatement(cad_sql);

                pstm.setString(1, personal.getDNI());
                pstm.setString(2, personal.getApPaterno());
                pstm.setString(3, personal.getApMaterno());
                pstm.setString(4, personal.getNombre());
                pstm.setString(5, Character.toString(personal.getGenero()));
                pstm.setDate(6, (java.sql.Date) personal.getFechaNacimiento());
                pstm.setFloat(7, personal.getSueldo());
                pstm.setString(8, personal.getCodigoCargo());

                pstm.executeUpdate();

                JOptionPane.showMessageDialog(null, "Personal Registrado");
            } else if (e.getSource() == btn_editar) {
                cad_sql = "call sp_editar_personal (?, ?, ?, ?, ?, ?, ?, ?);";
                pstm = cnx.prepareStatement(cad_sql);

                pstm.setString(1, personal.getApPaterno());
                pstm.setString(2, personal.getApMaterno());
                pstm.setString(3, personal.getNombre());
                pstm.setString(4, Character.toString(personal.getGenero()));
                pstm.setDate(5, (java.sql.Date) personal.getFechaNacimiento());
                pstm.setFloat(6, personal.getSueldo());
                pstm.setString(7, personal.getCodigoCargo());
                pstm.setString(8, personal.getDNI());

                pstm.executeUpdate();

                JOptionPane.showMessageDialog(null, "Personal Actualizado");
            } else if (e.getSource() == btn_borrar) {
                int opc = JOptionPane.showConfirmDialog(null,
                        "¿Seguro de borrar el registro?",
                        "SENATI", JOptionPane.YES_NO_OPTION);

                if (opc == JOptionPane.YES_OPTION) {
                    cad_sql = "call sp_borrar_personal(?);";
                    pstm = cnx.prepareStatement(cad_sql);

                    pstm.setString(1, personal.getDNI());

                    pstm.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Personal Eliminado");
                }
            }

            MostrarDatos();
        } catch (SQLException e1) {
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (cnx != null) cnx.close();
            } catch (SQLException e2) {
            }
        }

        LimpiarDatos();
    }
}
    

    private void LimpiarDatos() {
        txt_dni.setEditable(true);

        tb_personal.clearSelection();

        txt_dni.setText("");
        txt_nombre.setText("");
        txt_app.setText("");
        txt_apm.setText("");
        bg_genero.clearSelection();
        dc_fn.setDate(null);
        txt_sueldo.setText("");
        cbo_cargo.setSelectedIndex(0);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        Date fecha;
        try {
            fecha = sdf.parse("2000-01-01");
            dc_fn.setDate(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(AppPersonal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cbo_cargo.setSelectedIndex(0);

        txt_dni.requestFocus();
    }
    private void FiltrarPersonal() {
    DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };

    modelo.setRowCount(0);

    String filtro = txt_search.getText().trim();

    if (filtro.isEmpty()) {
        MostrarDatos();
    }

    Connection cnx = null;
    java.sql.PreparedStatement pstm = null;
    ResultSet rs = null;

    try {
        cnx = cn.Conectar();
        String sql = "CALL sp_filtrar_personal(?);";
        pstm = cnx.prepareStatement(sql);
        pstm.setString(1, filtro);

        rs = pstm.executeQuery();

        int columnCount = rs.getMetaData().getColumnCount();

        // Agregar las columnas al modelo
        for (int i = 1; i <= columnCount; i++) {
            modelo.addColumn(rs.getMetaData().getColumnName(i));
        }

        // Agregar las filas de datos
        while (rs.next()) {
            Object[] fila = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                fila[i] = rs.getObject(i + 1);
            }
            modelo.addRow(fila);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (cnx != null) {
                cnx.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Actualizar la tabla con los datos filtrados
    tb_personal.setModel(modelo);
}
    
    public static void main(String[] args) {
        AppPersonal frm = new AppPersonal();
        frm.setVisible(true);
    }
}
