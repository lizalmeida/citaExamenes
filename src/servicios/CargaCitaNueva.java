package servicios;

import modelo.Apoderado;
import modelo.Cita;
import modelo.Examen;
import modelo.Paciente;

public class CargaCitaNueva implements ICargaCitaFactory{

    @Override
    public Cita CargaCita(String citaTexto, String fechaCita) throws Excepciones{
        String[] datosParticionados;
        Cita cita = new Cita();
        Examen examen;
        Paciente paciente;
        Apoderado apoderado = new Apoderado();

        datosParticionados = citaTexto.split("\\|");

        examen = new Examen(datosParticionados[2], datosParticionados[3]);

        if (datosParticionados[5].equals("PMENOR")) {
            paciente = new Paciente(datosParticionados[7], // identificacion
                    datosParticionados[6], // tipoIdentificacion
                    datosParticionados[4], // nombrePaciente
                    datosParticionados[9], // fechaNacimiento
                    datosParticionados[8], // telefono
                    "@", // correoElectronico
                    datosParticionados[5], // tipoPaciente
                    datosParticionados[13]); // IdentificacionApoderado
            apoderado = new Apoderado(datosParticionados[13], // IdentificacionApoderado
                    datosParticionados[12], // tipoIdentificacionApoderado
                    datosParticionados[11], // nombreApoderado
                    datosParticionados[14], // fechaNacimientoApoderado
                    datosParticionados[8], // telefonoApoderado
                    "@", // correoElectronicoApoderado
                    "ADULTO"); // tipoPaciente
        } else {
            paciente = new Paciente(datosParticionados[7], // identificacion
                    datosParticionados[6], // tipoIdentificacion
                    datosParticionados[4], // nombrePaciente
                    datosParticionados[9], // fechaNacimiento
                    datosParticionados[8], // telefono
                    "@", // correoElectronico
                    datosParticionados[5], // tipoPaciente
                    ""); // IdentificacionApoderado
        }
        cita = new Cita(examen, paciente, apoderado, datosParticionados[0], datosParticionados[1]);
        return cita;
    }
}