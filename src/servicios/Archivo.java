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

import modelo.Cita;

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
