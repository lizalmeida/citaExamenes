package servicios;

import modelo.Cita;

public interface IArchivos {

    void LeerArchivo(Cita cita, String rutaArchivo) throws Exception;
} 
