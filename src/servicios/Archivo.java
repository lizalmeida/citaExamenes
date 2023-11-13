package servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Archivo {
    //Patrón Singleton
    private static Archivo instance;

    public Archivo() {
    }

    public static synchronized Archivo getInstance() {
        if (instance == null) {
            instance = new Archivo();
        }
        return instance;
    }

    public ArrayList<String> LeerArchivo(String rutaArchivo) throws IOException {
        File file = new File(rutaArchivo);
        BufferedReader bufferReader = null;
        FileReader fileReader = null;
        String dato = "";
        ArrayList<String> datos = new ArrayList<>();
        try {
            fileReader = new FileReader(file);
            bufferReader = new BufferedReader(fileReader);
            while ((dato = bufferReader.readLine()) != null) {
                datos.add(dato);
            }
        } catch (FileNotFoundException e) {
            throw new IOException("No existe el archivo " + rutaArchivo);

        } catch (IOException e) {
            throw new IOException("Error al leer el archivo " + rutaArchivo);
        } finally {
            try {
                if (bufferReader != null) {
                    bufferReader.close();
                }
            } catch (IOException e) {
                throw new IOException("Error al cerrar el BufferReader bufferReader.");
            }
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                throw new IOException("Error al cerrar el fileReader fr.");
            }
        }
        return datos;

    }

}
