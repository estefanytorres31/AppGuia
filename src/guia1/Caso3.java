package guia1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

public class Caso3 extends JFrame implements ActionListener{
    
    private JDesktopPane dsk_principal;
    private JMenuBar mb_principal;
    private JMenu mn_1;
    private JMenuItem mi_opc1,mi_opc2,mi_opc3; 
    
    private JSeparator sep_opc;
    
    
    public Caso3(){
        super();
        IniciarFormulario();
        IniciarControles();
    }
    
        public void IniciarFormulario(){
        this.setTitle("SENATI-ETI-Aplicacion Principal");
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    
        
        public void IniciarControles(){
            dsk_principal=new JDesktopPane();
            mb_principal=new JMenuBar();
            mn_1=new JMenu("Casos");
            
            mi_opc1=new JMenuItem("Ver caso 1");
            mi_opc1.addActionListener(this);
            
            mi_opc2=new JMenuItem("Ver caso 2");
            mi_opc2.addActionListener(this);
            
            sep_opc= new JSeparator();
            
            mi_opc3=new JMenuItem("Salir del sistema");
            mi_opc3.addActionListener(this);
            
            mn_1.add(mi_opc1);
            mn_1.add(mi_opc2);
            mn_1.add(sep_opc);
            mn_1.add(mi_opc3);
            
            mb_principal.add(mn_1);
            
            this.setJMenuBar(mb_principal);
            
            this.add(dsk_principal);
        }
    
        @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== mi_opc1){
            Caso1 caso1= new Caso1();
            AbrirFormulario(caso1);
            //dsk_principal.add(caso1);
            //caso1.show();
        }
        else if(e.getSource()== mi_opc2){
            Caso2 caso2= new Caso2();
            AbrirFormulario(caso2);
            //dsk_principal.add(caso1);
            //caso1.show();
        }
        else if(e.getSource()== mi_opc3){
            System.exit(0);
        }
    }
    
    
    public static void main(String[] args) {
        Caso3 caso3= new Caso3();
        caso3.setVisible(true);
    }
    
    
    public void AbrirFormulario(JInternalFrame frm){
        JInternalFrame[] arr_iframe=dsk_principal.getAllFrames();
        boolean abierto=false;
        
        for(JInternalFrame iframe: arr_iframe){
            if(frm.getClass().isInstance(iframe)){
                JOptionPane.showMessageDialog(null, "Formulario ya abierto");
                abierto=true;
                break;
            }
            
        }
        if(!abierto){
                dsk_principal.add(frm);
                frm.setLocation((dsk_principal.getWidth()-frm.getWidth())/2,
                        dsk_principal.getHeight()-frm.getHeight()/2);
                
                frm.show();
            }
        
    }
    


}
