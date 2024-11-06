package guia2;

import clases.ConexionSQL;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class AppBDCaso2 extends JFrame {
    
    private JTable tb_personal;
    private JScrollPane scp;
    private DefaultTableModel modelo_tb;
    
    public AppBDCaso2() {
        super();
        
        ConfigurarVentana();
        IniciarComponentes();
    }
    
    private void ConfigurarVentana() {
        this.setTitle("Lista de Personal");
        this.setSize(600, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void IniciarComponentes() {
        tb_personal = new JTable();
        
        modelo_tb = new DefaultTableModel();
        tb_personal.setModel(modelo_tb);
        
        ConexionSQL cn = new ConexionSQL();
        
        Connection cnx = null;
        Statement stm = null;
        ResultSet rs = null;
        
        try {
            cnx = (Connection) cn.Conectar();
            stm = (Statement) cnx.createStatement();
            
            rs = stm.executeQuery("sp_ListarPersonal");
            
            int nc = rs.getMetaData().getColumnCount();
            
            for (int i = 1; i <= nc; i++)
                modelo_tb.addColumn(rs.getMetaData().getColumnName(i));
            while (rs.next()) {
                Object[] arr_filas = new Object[nc];
                
                for (int i = 0; i < nc; i++)
                    arr_filas[i] = rs.getObject(i + 1);
                
                modelo_tb.addRow(arr_filas);
            }
        } catch (SQLException e) {
            
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (cnx != null) cnx.close();
            } catch (SQLException e) {
                
            }
        }
        
        tb_personal.setRowHeight(20);
        
        DefaultTableCellRenderer alinear = new DefaultTableCellRenderer();
        alinear.setHorizontalAlignment(SwingConstants.CENTER);
        
        TableColumnModel arr_col = tb_personal.getColumnModel();
        arr_col.getColumn(0).setCellRenderer(alinear);
        arr_col.getColumn(0).setPreferredWidth(120);
        
        arr_col.getColumn(1).setPreferredWidth(300);
        
        arr_col.getColumn(2).setCellRenderer(alinear);
        arr_col.getColumn(2).setPreferredWidth(180);
        
        scp = new JScrollPane(tb_personal);
        
        this.add(scp);
    }

    public static void main(String[] args) {
        AppBDCaso2 caso2 = new AppBDCaso2();
        caso2.setVisible(true);
    }
    
}
