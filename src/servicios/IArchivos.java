package servicios;
import java.util.ArrayList;
import java.util.List;

import modelo.Cita;

public interface IArchivos {

    void EscribirArchivo(Cita cita,String rutaArchivo) throws Exception;
} 
