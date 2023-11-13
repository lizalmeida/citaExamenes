package servicios;

import modelo.Cita;

public interface ICargaCitaFactory{

    Cita CargaCita(String citaTexto, String fechaCita) throws Excepciones;

} 
