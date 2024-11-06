package clases;
import java.sql.Date;

public class Personal {
    private String dni;
    private String ap_paterno;
    private String ap_materno;
    private String nombre;
    private char genero;
    private Date fecha_nacimiento;
    private float sueldo;
    private String codigo_cargo;
    
    public Personal() {
        
    }
     
    public Personal(String dni, String app, String apm, String nom, char gen, Date fn, float sue, String cod_car ) {
        this.dni = dni;
        this.ap_paterno = app;
        this.ap_materno = apm;
        this.nombre = nom;
        this.genero = gen;
        this.fecha_nacimiento = fn;
        this.sueldo = sue;
        this.codigo_cargo = cod_car;
    }

    public String getDNI() {
        return dni;
    }

    public void setDNI(String dni) {
        this.dni = dni;
    }

    public String getApPaterno() {
        return ap_paterno;
    }

    public void setApPaterno(String ap_paterno) {
        this.ap_paterno = ap_paterno;
    }

    public String getApMaterno() {
        return ap_materno;
    }

    public void setApMaterno(String ap_materno) {
        this.ap_materno = ap_materno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public Date getFechaNacimiento() {
        return fecha_nacimiento;
    }

    public void setFechaNacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public String getCodigoCargo() {
        return codigo_cargo;
    }

    public void setCodigoCargo(String codigo_cargo) {
        this.codigo_cargo = codigo_cargo;
    }
     
}
