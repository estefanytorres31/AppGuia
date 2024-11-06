package guia2;

import clases.ConexionMySQL;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.SQLException;

public class AppBDCaso1 {
    
    public static String UnirFecha(int d, int m, int a) {
        String dd = (d < 10 ? "0" + String.valueOf(d) : String.valueOf(d));
        String mm = (m < 10 ? "0" + String.valueOf(m) : String.valueOf(m));
        String aa = String.valueOf(a);
        
        return dd + "/" + mm + "/" + aa;
    }

    public static void main(String[] args) {
        ConexionMySQL cn = new ConexionMySQL();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DecimalFormat df = new DecimalFormat("#,##0.00");

        Connection cnx = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            cnx = (Connection) cn.Conectar();
            stm = (Statement) cnx.createStatement();
            rs = stm.executeQuery("select * from tb_personal order by ap_paterno asc");

            String formato = "%-10s %-30s %-6s %-12s %-10s";

            System.out.println(String.format(formato, "DNI", "Apellidos y Nombre", "Género", "Fec. Nac", "Sueldo"));
            System.out.println(String.format(formato, "----------", "------------------------------", "------", "----------", "----------"));

            while (rs.next()) {
                String dni = rs.getString(1);
                String nom = rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4);
                char gen = rs.getString(5).charAt(0);

                LocalDate fn = LocalDate.parse(rs.getString(6), dtf);
                String fec_nac = String.format("%02d/%02d/%04d", fn.getDayOfMonth(), fn.getMonthValue(), fn.getYear());

                float sue = rs.getFloat(7);

                System.out.println(String.format(formato, dni, nom, gen, fec_nac, df.format(sue)));
        }

        } catch (SQLException e1) {

        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (cnx != null) cnx.close();
            } catch (SQLException e2) {
                
            }
        }
}

    
}
