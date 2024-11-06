
package guia1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Caso1 extends JInternalFrame implements ActionListener {
    private JLabel lbl1,lbl2,lbl3;
    private JTextField txt_nombre,txt_apellido;
    private JComboBox cbo_programa; 
    private JButton btn_aceptar, btn_cerrar;
    
    
    public Caso1(){
        super();
        
        IniciarFormulario();
        IniciarControles();
    }
    
    public void IniciarFormulario(){
        this.setTitle("SENATI");
        this.setSize(400, 300);
        //this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public void IniciarControles() {
        lbl1 = new JLabel();
        lbl1.setText("Ingrese nombre");
        lbl1.setBounds(20, 20, 120, 25);
        
        txt_nombre = new JTextField();
        txt_nombre.setBounds(20, 50, 140, 25);
        
        lbl2 = new JLabel();
        lbl2.setText("Ingrese apellido");
        lbl2.setBounds(20, 80, 120, 25);
        
        txt_apellido = new JTextField();
        txt_apellido.setBounds(20, 110, 140, 25);
        
        lbl3 = new JLabel();
        lbl3.setText("Seleccione programa");
        lbl3.setBounds(20, 140, 140, 25);
        
        cbo_programa = new JComboBox();
        cbo_programa.setBounds(20, 170, 140, 25);
        
        String[] arr_programa = {"Ofimática", "Base de Datos", "Ensamblaje", "UML", "Arduino", "Desarrollo móvil"};
        
        for(String programa: arr_programa)
            cbo_programa.addItem(programa);
        
        cbo_programa.setSelectedIndex(-1);
        
        btn_aceptar = new JButton();
        btn_aceptar.setText("ACEPTAR");
        btn_aceptar.setBounds(40, 220, 120, 30);
        btn_aceptar.addActionListener(this);
        
        btn_cerrar = new JButton();
        btn_cerrar.setText("CERRAR");
        btn_cerrar.setBounds(190, 220, 120, 30);
        btn_cerrar.addActionListener(this);
        
        this.add(lbl1);
        this.add(txt_nombre);
        this.add(lbl2);
        this.add(txt_apellido);
        this.add(lbl3);
        this.add(cbo_programa);
        this.add(btn_aceptar);
        this.add(btn_cerrar);
    }
    
    

    public static void main(String[] args) {
        Caso1 caso1=new Caso1();
        caso1.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btn_aceptar){
            if(txt_nombre.getText().length()==0){
                JOptionPane.showMessageDialog(null, "Escriba su nombre");
                txt_nombre.requestFocus();
                return;
            }
            if(txt_apellido.getText().length()==0){
                JOptionPane.showMessageDialog(null, "Escriba su apellido");
                txt_apellido.requestFocus();
                return;
            }
            if(cbo_programa.getSelectedIndex()==-1){
                JOptionPane.showMessageDialog(null, "Elija programa");
                cbo_programa.requestFocus();
                return;
            }
            String nombre=txt_nombre.getText();
            String apellido=txt_apellido.getText();
            String programa=(String) cbo_programa.getSelectedItem();
            
            float[] arr_costo={220,280,190,230,300,450};
            float costo= arr_costo[cbo_programa.getSelectedIndex()];
            
            float soles=(float) (costo*3.78);
            
            String mensaje="";
            mensaje += "BIENVENIDO(A)"+"\n";
            mensaje += "Nombre: "+nombre+" "+apellido+"\n";
            mensaje += "Programa: "+programa+"\n";
            mensaje += "Costo $: "+costo+"\n";
            mensaje += "Costo S/.: "+soles+"\n";
            
            JOptionPane.showMessageDialog(null, mensaje);
        } else if(e.getSource()==btn_cerrar){
            int op=JOptionPane.showConfirmDialog(null,
                    "¿Seguro de cerrrar?",
                    "SENATI",
                    JOptionPane.YES_NO_OPTION);
            
            if(op==JOptionPane.YES_OPTION){
                dispose();
            }
        }
    }
    
}
