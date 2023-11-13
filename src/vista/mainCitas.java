package vista;
import java.util.ArrayList;
import modelo.Cita;
import servicios.Archivo;
import servicios.Excepciones;
//import java.io.IOException;

public class mainCitas {
	private static String rutaLectura = "C:\\Tmp\\lab_input.txt"; 
	//private static String rutaEscritura = "C:\\Tmp\\lab_output.txt"; 
    public static void main(String[] args) throws Excepciones{
		Cita citanueva = new Cita();
		ArrayList<Cita> citas= new ArrayList<Cita>();
		String fechaCitaAnterior = "2020-01-01";

		try {
			ArrayList<String> lecturaArchivo = new Archivo().LeerArchivo(rutaLectura);
			citas = new Archivo().LeerCitas(lecturaArchivo);
			citanueva = new Archivo().LeerCitaNueva(lecturaArchivo);

			for(Cita cita:citas){
				if(!cita.getFecha().equals(fechaCitaAnterior)){
					System.out.println(cita.getFecha());
				}
				System.out.println(cita.getHora() + "|" + cita.toString());
				fechaCitaAnterior = cita.getFecha();
            };
			System.out.println(citanueva.getFecha());
			System.out.println(citanueva.getHora() + "|" + citanueva.toString());

		} catch (Exception e){
			System.out.println("No se pudo agregar la cita");
			for(Cita cita:citas){
				if(!cita.getFecha().equals(fechaCitaAnterior)){
					System.out.println(cita.getFecha());
				}
				System.out.println(cita.getHora() + "|" + cita.toString());
				fechaCitaAnterior = cita.getFecha();
            };
		}
    }
}