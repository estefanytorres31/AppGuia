package guia1;

import clases.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Caso2 extends JInternalFrame implements ActionListener{
    
    private JLabel lbl1,lbl2,lbl3,lbl4,lbl5;
    private JTextField txt_x,txt_y;
    private JTextArea txt_rs;
    private JScrollPane scr_rs;
    private JButton btn_aceptar,btn_cerra;

    public Caso2() {
        super();
        
        IniciarFormulario();
        IniciarControles();        
    }
    
    public void IniciarFormulario(){
        this.setTitle("SENATI - ETI");
        this.setSize(400,400);
        //this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
        this.getContentPane().setBackground(new Color(197, 242, 231));
    }
    public void IniciarControles(){
        lbl1 = new JLabel();
        lbl1.setText("VECTORES");
        lbl1.setForeground(new Color(68, 148, 180));
        lbl1.setFont(new Font("Arial", Font.BOLD, 18));
        lbl1.setBounds(20,20,120,25);
        
        lbl5 = new JLabel();
        lbl5.setText("Grupo 1 ");
        lbl5.setForeground(new Color(48, 104, 126));
        lbl5.setFont(new Font("Arial", Font.BOLD, 18));
        lbl5.setBounds(290,20,120,25);
        
        
        lbl2 = new JLabel();
        lbl2.setText("Valor x");
        lbl2.setBounds(20,60,120,25);
        
        txt_x = new JTextField();
        txt_x.setBounds(90,60,80,25);
        
        lbl3 = new JLabel();
        lbl3.setText("Valor y");
        lbl3.setBounds(20,100,120,25);
        
        txt_y = new JTextField();
        txt_y.setBounds(90,100,80,25);
        
        lbl4 = new JLabel();
        lbl4.setText("Resultados");
        lbl4.setBounds(20,140,90,25);
        
        txt_rs = new JTextArea();
        txt_rs.setFont(new Font("Consolas", Font.PLAIN, 14));
        txt_rs.setEditable(false);
        
        
        
        scr_rs= new JScrollPane(txt_rs,
                                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scr_rs.setBounds(20,170,260,60);
        
        
        btn_aceptar = new JButton();
        btn_aceptar.setText("ACEPTAR");
        btn_aceptar.setBounds(40,250,120,30);
        btn_aceptar.addActionListener(this);
        
        btn_cerra = new JButton();
        btn_cerra.setText("CERRAR");
        btn_cerra.setBounds(190,250,120,30);
        btn_cerra.addActionListener(this);
        
        
        this.add(lbl1);
        this.add(lbl5);
        this.add(lbl2);
        this.add(txt_x);
        this.add(lbl3);
        this.add(txt_y);
        this.add(lbl4);
        this.add(scr_rs);
        this.add(btn_aceptar);
        this.add(btn_cerra);
        

        
    }
    

    
    public static void main(String[] args) {
        Caso2 caso2 = new Caso2();
        caso2.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       DecimalFormat df = new DecimalFormat("0.00");
       
       if(e.getSource()== btn_aceptar){
           if( txt_x.getText().length()== 0){
               JOptionPane.showMessageDialog(null, "Completa el campo X");
               txt_x.requestFocus();
               return;
           }
           
           if( txt_y.getText().length()== 0){
               JOptionPane.showMessageDialog(null, "Completa el campo Y");
               txt_y.requestFocus();
               return;
           }
           
           float vX = Float.parseFloat(txt_x.getText());
           float vY = Float.parseFloat(txt_y.getText());
           
           
           Vector vector = new Vector(vX,vY);
           
           
           String rs = "";
           rs += "Vector: " + vector.MostrarVector()+"\n";
           rs += "Módulo: " + df.format(vector.CalcularModulo())+"\n";
           rs += "Angulo: " + df.format(vector.CalcularAngulo())+"º";
           
           txt_rs.setText(rs);
           
       }
       else if(e.getSource()== btn_cerra)
       {
           int op= JOptionPane.showConfirmDialog( null, "Seguro de Cerrar .-.??", "SENATI", JOptionPane.YES_NO_OPTION);
           
           if(op==JOptionPane.YES_OPTION){
              dispose();
           }
       }
       
    }

 
    
}
