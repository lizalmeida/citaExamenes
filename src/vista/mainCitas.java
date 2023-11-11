package vista;
import java.util.ArrayList;
import java.io.IOException;
import modelo.Cita;
import servicios.Archivo;

public class mainCitas {
	private static String rutaLectura = "C:\\Tmp\\lab_input.txt"; 
	private static String rutaEscritura = "C:\\Tmp\\lab_output.txt"; 
    public static void main(String[] args) throws Exception{
		Cita cita = new Cita();
		ArrayList<Cita> citas= new ArrayList<Cita>();
		String fechaCitaAnterior = "2020-01-01";
        //EscribeArchivo escribeCitasArchivo = new EscribeArchivo(rutaEscritura);
		try {
			ArrayList<String> lecturaArchivo = new Archivo().LeerArchivo(rutaLectura);
			citas = new Archivo().LeerCitas(lecturaArchivo);
			cita=new Archivo().LeerCita(lecturaArchivo);

			Archivo archivoEscritura = new Archivo();
			Archivo.EscribirCita escribirCita = archivoEscritura.new EscribirCita();
			Archivo.EscribirFecha escribirFecha = archivoEscritura.new EscribirFecha();

			// Llamar a los métodos desde las instancias de las clases internas
			escribirCita.EscribirArchivo(cita, rutaEscritura);
			escribirFecha.EscribirArchivo(cita, rutaEscritura);

			for(Cita c:citas){
				if(!c.getFecha().equals(fechaCitaAnterior)){
					//escribeCitasArchivo.escribir_fecha(c);
					escribirFecha.EscribirArchivo(c, rutaEscritura);
				}
				//escribeCitasArchivo.escribir_cita(c);
				escribirCita.EscribirArchivo(c, rutaEscritura);
				fechaCitaAnterior = c.getFecha();
            };
			escribirCita.EscribirArchivo(cita, rutaEscritura);
			escribirFecha.EscribirArchivo(cita, rutaEscritura);
			//escribeCitasArchivo.escribir_fecha(cita);
			//escribeCitasArchivo.escribir_cita(cita);

		} catch (Exception e){
			throw new Exception("Ha  ocurrido un error." + e);
		}

    }
}