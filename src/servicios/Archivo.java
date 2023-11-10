package servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import modelo.Apoderado;
import modelo.Cita;
import modelo.Examen;
import modelo.Paciente;

public class Archivo {

    public List<String> LeerArchivo(String rutaArchivo) throws Exception{
		File file = new File(rutaArchivo);
		BufferedReader bufferReader = null;
		FileReader fileReader = null;
		String dato = "";
		List<String> datos = new ArrayList<>();
		try {
			fileReader = new FileReader(file);
			bufferReader = new BufferedReader(fileReader);
			while((dato = bufferReader.readLine()) != null){
				datos.add(dato);
			}
		} catch (FileNotFoundException e) {
			throw new Exception("No existe el archivo " + rutaArchivo);
			
		} catch (IOException e) {
			throw new Exception("Error al leer el archivo " + rutaArchivo);
		} finally {
			try {
				if (bufferReader != null) {
					bufferReader.close();
				}
			} catch (IOException e) {
				throw new Exception("Error al cerrar el BufferReader bufferReader.");
			}
			try {
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
				throw new Exception("Error al cerrar el fileReader fr.");
			}
		}
		return datos;
		
	}

    public ArrayList<Cita> LeerCitas() throws IOException {

		File file = new File(rutaArchivo);
		
		BufferedReader bufferReader = null;
		FileReader fileReader = null;
		ArrayList<Cita> citas = new ArrayList<Cita>();
		
		String 		linea = "";
		String[] 	partes;
		Examen 		examen;
		Paciente 	paciente;
		Apoderado 	apoderado = new Apoderado();
		Cita 		cita;
		String 		fechaCita = "2020-01-01";
		try {
			fileReader = new FileReader(file);
			bufferReader = new BufferedReader(fileReader);
			
			while ((linea = bufferReader.readLine()) != null) {
				if (linea.equals("") || linea.equals("NUEVA CITA")) {
					break;
				}
                if (linea.equals("2023-10-23") || linea.equals("2023-10-24")) { //PENDIENTE: Quitar fechas quemadas
                    fechaCita = linea;
                } else {
					partes=linea.split("\\|");

					examen = new Examen (partes[1], partes[2]);

					if (partes[4].equals("PMENOR")){
						paciente = new Paciente(partes[6], 		//identificacion
												partes[5], 		//tipoIdentificacion
												partes[3], 		//nombrePaciente
												partes[8], 		//fechaNacimiento
												partes[7], 		//telefono
												"@", //correoElectronico
												partes[4], 		//tipoPaciente
												partes[12]); 	//IdentificacionApoderado
						apoderado = new Apoderado(partes[12], 	//IdentificacionApoderado
												  partes[11], 	//tipoIdentificacionApoderado
												  partes[10], 	//nombreApoderado
												  partes[13], 	//fechaNacimientoApoderado
												  partes[7], 	//telefonoApoderado
												  "@", //correoElectronicoApoderado
												  "ADULTO"); //tipoPaciente
					} else {
						paciente = new Paciente(partes[6], 		//identificacion
												partes[5], 		//tipoIdentificacion
												partes[3], 		//nombrePaciente
												partes[8], 		//fechaNacimiento
												partes[7], 		//telefono
												"@", //correoElectronico
												partes[4], 		//tipoPaciente
												""); //IdentificacionApoderado
					}
					cita = new Cita(examen, paciente, apoderado, fechaCita, partes[0]);
					citas.add(cita);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("No existe el archivo " + rutaArchivo);
			
		} catch (IOException e) {
			System.out.println("Error al leer el archivo " + rutaArchivo);
		} finally {
			try {
				if (bufferReader != null) {
					bufferReader.close();
				}
			} catch (IOException e) {
				System.out.println("Error al cerrar el BufferReader bufferReader.");
			}
			try {
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
				System.out.println("Error al cerrar el fileReader fr.");
			}
		}
		return citas;
	}

	public Cita LeerCita() throws IOException {
		File file = new File(rutaArchivo);
		
		BufferedReader bufferReader = null;
		FileReader fileReader = null;
		
		String 		linea = "";
		String[] 	partes;
		Examen 		examen;
		Paciente 	paciente;
		Apoderado 	apoderado = new Apoderado();
		Cita 		cita = new Cita();
		Integer 	nuevaCitaenCurso = 0;
		try {
			fileReader = new FileReader(file);
			bufferReader = new BufferedReader(fileReader);
			
			while ((linea = bufferReader.readLine()) != null) {
			if (linea.equals("NUEVA CITA") || nuevaCitaenCurso == 1) {
				nuevaCitaenCurso = 1;
				linea = bufferReader.readLine();
				
				partes=linea.split("\\|");

					examen = new Examen (partes[2], partes[3]);

					if (partes[5].equals("PMENOR")){
						paciente = new Paciente(partes[7], 		//identificacion
												partes[6], 		//tipoIdentificacion
												partes[4], 		//nombrePaciente
												partes[9], 		//fechaNacimiento
												partes[8], 		//telefono
												"@", //correoElectronico
												partes[5], 		//tipoPaciente
												partes[13]); 	//IdentificacionApoderado
						apoderado = new Apoderado(partes[13], 	//IdentificacionApoderado
												  partes[12], 	//tipoIdentificacionApoderado
												  partes[11], 	//nombreApoderado
												  partes[14], 	//fechaNacimientoApoderado
												  partes[8], 	//telefonoApoderado
												  "@", //correoElectronicoApoderado
												  "ADULTO"); //tipoPaciente
					} else {
						paciente = new Paciente(partes[7], 		//identificacion
												partes[6], 		//tipoIdentificacion
												partes[4], 		//nombrePaciente
												partes[9], 		//fechaNacimiento
												partes[8], 		//telefono
												"@", //correoElectronico
												partes[5], 		//tipoPaciente
												""); 	//IdentificacionApoderado
					}
					cita = new Cita(examen, paciente, apoderado, partes[0], partes[1]);
					calendario.agregarCita(cita);
					return cita;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: No existe el archivo " + rutaArchivo);
			
		} catch (IOException e) {
			System.out.println("Error al leer el archivo " + rutaArchivo);
		} finally {
			try {
				if (bufferReader != null) {
					bufferReader.close();
				}
			} catch (IOException e) {
				System.out.println("Error al cerrar el BufferReader bufferReader.");
			}
			try {
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
				System.out.println("Error al cerrar el fileReader fr.");
			}
		}
		return cita;
	}
	
 
    public class EscribirFecha implements IArchivos {
        public void EscribirArchivo(Cita cita,String rutaArchivo) {
            File file= new File(rutaArchivo);
            FileWriter fileWriter;
            PrintWriter printWriter;
            
            if (file.exists()) {
            try {
                fileWriter = new FileWriter(file,true);
                printWriter = new PrintWriter(fileWriter);
                printWriter.println(cita.getFecha());
                if (printWriter != null) {
                    printWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
            try {
                fileWriter = new FileWriter(file);
                printWriter = new PrintWriter(fileWriter);
                printWriter.println(cita.getFecha());
                if (printWriter != null) {
                    printWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
	    }
    }

    public class EscribirCita implements IArchivos {
        public void EscribirArchivo(Cita cita,String rutaArchivo) {
            File file= new File(rutaArchivo);
            FileWriter fileWriter;
            PrintWriter printWriter;
            
            if (file.exists()) {
            try {
                fileWriter = new FileWriter(file,true);
                printWriter = new PrintWriter(fileWriter);
                printWriter.println(cita.getHora() + "|" + cita.toString());
                if (printWriter != null) {
                    printWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
            try {
                fileWriter = new FileWriter(file);
                printWriter = new PrintWriter(fileWriter);
                printWriter.println(cita.getHora() + "|" + cita.toString());
                if (printWriter != null) {
                    printWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
	    }
    }
}
