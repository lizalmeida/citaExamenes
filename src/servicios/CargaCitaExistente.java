package servicios;

import modelo.Apoderado;
import modelo.Cita;
import modelo.Examen;
import modelo.Paciente;

public class CargaCitaExistente implements ICargaCitaFactory{
    
    @Override
    public Cita CargaCita(String citaTexto, String fechaCita) throws Excepciones{
        String[] datosParticionados;
        Cita cita;
        Examen examen;
        Paciente paciente;
        Apoderado apoderado = new Apoderado();

        datosParticionados = citaTexto.split("\\|");

        examen = new Examen(datosParticionados[1], datosParticionados[2]);

        if (datosParticionados[4].equals("PMENOR")) {
            paciente = new Paciente(datosParticionados[6], // identificacion
                    datosParticionados[5], // tipoIdentificacion
                    datosParticionados[3], // nombrePaciente
                    datosParticionados[8], // fechaNacimiento
                    datosParticionados[7], // telefono
                    "@", // correoElectronico
                    datosParticionados[4], // tipoPaciente
                    datosParticionados[12]); // IdentificacionApoderado
            apoderado = new Apoderado(datosParticionados[12], // IdentificacionApoderado
                    datosParticionados[11], // tipoIdentificacionApoderado
                    datosParticionados[10], // nombreApoderado
                    datosParticionados[13], // fechaNacimientoApoderado
                    datosParticionados[7], // telefonoApoderado
                    "@", // correoElectronicoApoderado
                    "ADULTO"); // tipoPaciente
        } else {
            paciente = new Paciente(datosParticionados[6], // identificacion
                    datosParticionados[5], // tipoIdentificacion
                    datosParticionados[3], // nombrePaciente
                    datosParticionados[8], // fechaNacimiento
                    datosParticionados[7], // telefono
                    "@", // correoElectronico
                    datosParticionados[4], // tipoPaciente
                    ""); // IdentificacionApoderado
        }
        cita = new Cita(examen, paciente, apoderado, fechaCita, datosParticionados[0]);
        return cita;
    }
}