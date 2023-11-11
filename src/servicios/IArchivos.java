package servicios;

import modelo.Cita;

public interface IArchivos {

    void EscribirArchivo(Cita cita,String rutaArchivo) throws Exception;
} 
