package servicios;

import java.util.ArrayList;

import modelo.Apoderado;
import modelo.Calendario;
import modelo.Cita;
import modelo.Examen;
import modelo.Paciente;

public class CargaCitas {
    
    public ArrayList<Cita> LeerCitas(ArrayList<String> datos) throws Excepciones {
        ArrayList<Cita> citas = new ArrayList<Cita>();
        try {
            String fechaCita = "2020-01-01";
            String[] datosParticionados;
            
            Cita cita;
            Examen examen;
            Paciente paciente;
            Apoderado apoderado = new Apoderado();

            for (String dato : datos) {
                if (!dato.isEmpty() ) {
                    if (!dato.equals("NUEVA CITA")) {
                         if (!dato.substring(2,3).equals(":")){
                        fechaCita = dato;
                        } else {
                        datosParticionados = dato.split("\\|");

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
                        citas.add(cita);
                    }

                    }
                }
            }
        } catch (Exception  e) {
            throw new Excepciones("Ha  ocurrido un error." + e);
        } 
        return citas;
    }

    public Cita LeerCitaNueva(ArrayList<String> datos) throws Excepciones {
        Cita cita = new Cita();
        try {
            Calendario calendario = new Calendario();
            Examen examen;
            Paciente paciente;
            Apoderado apoderado = new Apoderado();
            Integer nuevaCitaenCurso = 0;
            String[] datosParticionados;
            for (String dato : datos) {
                if (dato.equals("NUEVA CITA")) {
                    nuevaCitaenCurso++;
                    continue;
                }
                if(nuevaCitaenCurso>=1){
                    datosParticionados = dato.split("\\|");

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
                        calendario.agregarCita(cita);
                        return cita;
                }
            }
            
        } catch (Exception e) {
            throw new Excepciones("Ha  ocurrido un error." + e);
        }
        return cita;
    }
}
