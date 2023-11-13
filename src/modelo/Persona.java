package modelo;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Persona {
    private String Identificacion;
    private String TipoIdentificacion; //(C)edula , (P) Pasaporte
    private String Nombre;
    private String FechaNacimiento;
    private String Telefono;
    private String CorreoElectronico;
    private String TipoPaciente; //ADULTO, PMENOR

    public Persona () {}
    public Persona(String identificacion, String tipoIdentificacion, String nombre,
            String fechaNacimiento, String telefono, String correoElectronico, String tipoPaciente) {
        Identificacion = identificacion;
        TipoIdentificacion = tipoIdentificacion;
        Nombre = nombre;
        FechaNacimiento = fechaNacimiento;
        Telefono = telefono;
        CorreoElectronico = correoElectronico;
        TipoPaciente = tipoPaciente;
    }

    public String getIdentificacion() {
        return Identificacion;
    }

    public void setIdentificacion(String identificacion) {
        Identificacion = identificacion;
    }

    public String getTipoIdentificacion() {
        return TipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        TipoIdentificacion = tipoIdentificacion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        CorreoElectronico = correoElectronico;
    }

    public String getTipoPaciente() {
        return TipoPaciente;
    }

    public void setTipoPaciente(String tipoPaciente) {
        TipoPaciente = tipoPaciente;
    }

    public boolean esAdulto() throws ParseException{
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNacimiento = formato.parse(this.FechaNacimiento);

        Date fechaActual = new Date(System.currentTimeMillis());

        int dias = (int) ((fechaActual.getTime() - fechaNacimiento.getTime()));
        if (365*dias> 18) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "persona [Identificacion=" + Identificacion + ", TipoIdentificacion=" + TipoIdentificacion + ", Nombre="
                + Nombre + ", FechaNacimiento=" + FechaNacimiento + ", Telefono=" + Telefono + ", CorreoElectronico=" 
                + CorreoElectronico + ", TipoPaciente=" + TipoPaciente + "]";
    }
    
}

