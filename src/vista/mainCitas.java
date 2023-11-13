package vista;
import java.util.ArrayList;
import modelo.Cita;
import servicios.Archivo;
import servicios.Excepciones;
import servicios.ICargaCitaFactory;
import servicios.CargaCitaExistente;
import servicios.CargaCitaNueva;

//import java.io.IOException;

public class mainCitas {
	private static String rutaLectura = "C:\\Tmp\\lab_input.txt"; 
	//private static String rutaEscritura = "C:\\Tmp\\lab_output.txt"; 
    public static void main(String[] args) throws Excepciones{
		ICargaCitaFactory CargaCitaNueva = new CargaCitaNueva();
		ICargaCitaFactory CargaCitaExistente = new CargaCitaExistente();

		Cita cita = new Cita();
		ArrayList<Cita> citas= new ArrayList<Cita>();
		String fechaCitaAnterior = "2020-01-01";
		Integer lineaNuevaCita = 0;
		Cita citaNueva = new Cita();

		try {
			ArrayList<String> lecturaArchivo = new Archivo().LeerArchivo(rutaLectura);

			for (String linea : lecturaArchivo) {
				if (!linea.equals("")){
				if (linea.substring(4,5).equals("-") && lineaNuevaCita == 0){
					fechaCitaAnterior = linea;
				} else if (linea.substring(2,3).equals(":") && lineaNuevaCita == 0){
					cita = CargaCitaExistente.CargaCita(linea, fechaCitaAnterior);
					citas.add(cita);
				} else if (linea.equals("NUEVA CITA")) {
						lineaNuevaCita = 1;
				} else if (lineaNuevaCita == 1){
					cita = CargaCitaNueva.CargaCita(linea, "");
					citas.add(cita);
				}
				}
			}

			for(Cita citalazo:citas){
				if(!citalazo.getFecha().equals(fechaCitaAnterior)){
					System.out.println(citalazo.getFecha());
				}
				System.out.println(citalazo.getHora() + "|" + citalazo.toString());
				fechaCitaAnterior = citalazo.getFecha();
            };
			System.out.println(citaNueva.getFecha());
			System.out.println(citaNueva.getHora() + "|" + citaNueva.toString());

		} catch (Exception e){
			System.out.println("No se pudo agregar la cita");
			for(Cita citalazo:citas){
				if(!citalazo.getFecha().equals(fechaCitaAnterior)){
					System.out.println(citalazo.getFecha());
				}
				System.out.println(citalazo.getHora() + "|" + citalazo.toString());
				fechaCitaAnterior = citalazo.getFecha();
            };
		}
    }
}