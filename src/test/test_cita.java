package test;

import modelo.Cita;
import modelo.Calendario;
import modelo.Examen;
import modelo.Paciente;
import servicios.Excepciones;
import modelo.Apoderado;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class test_cita {
    @Test
    public void test_cita_falla_horario(){
        
        Examen examen = new Examen("JUAN PEREZ1","EARLY");
        Paciente paciente = new Paciente("1234567890", "C", "PEDRO PACIENTE", "2020-01-01", "02216545", "@", "ADULTO", "");
        Apoderado apoderado = new Apoderado();

        Cita cita = new Cita(examen, paciente, apoderado, "2023-12-31", "20:00");
        Calendario calen = new Calendario();
        try {
            calen.agregarCita(cita);
        } catch (Excepciones e) {
            System.out.println("No se pudo agregar la cita");
            e.printStackTrace();
        }

        assertEquals("2023-12-31", cita.getFecha());
    }

    @Test
    public void test_cita_falla_fechaMayorActual(){
        
        Examen examen = new Examen("JUAN PEREZ1","EARLY");
        Paciente paciente = new Paciente("1234567890", "C", "PEDRO PACIENTE", "2020-01-01", "02216545", "@", "ADULTO", "");
        Apoderado apoderado = new Apoderado();

        Cita cita = new Cita(examen, paciente, apoderado, "2023-10-01", "12:00");
        Calendario calen = new Calendario();
        try {
            calen.agregarCita(cita);
        } catch (Excepciones e) {
            System.out.println("No se pudo agregar la cita");
            e.printStackTrace();
        }

        assertEquals("2023-10-01", cita.getFecha());
    }

    @Test
    public void test_cita_falla_feriado(){
        
        Examen examen = new Examen("JUAN PEREZ1","EARLY");
        Paciente paciente = new Paciente("1234567890", "C", "PEDRO PACIENTE", "2020-01-01", "02216545", "@", "ADULTO", "");
        Apoderado apoderado = new Apoderado();

        Cita cita = new Cita(examen, paciente, apoderado, "2023-12-31", "12:00");
        Calendario calen = new Calendario();
        try {
            calen.agregarCita(cita);
        } catch (Excepciones e) {
            System.out.println("No se pudo agregar la cita");
            e.printStackTrace();
        }

        assertEquals("2023-12-31", cita.getFecha());
    }

    @Test
    public void test_cita_pasa(){
        
        Examen examen = new Examen("JUAN PEREZ1","EARLY");
        Paciente paciente = new Paciente("1234567890", "C", "PEDRO PACIENTE", "2020-01-01", "02216545", "@", "ADULTO", "");
        Apoderado apoderado = new Apoderado();

        Cita cita = new Cita(examen, paciente, apoderado, "2023-12-29", "12:00");
        Calendario calen = new Calendario();
        try {
            calen.agregarCita(cita);
        } catch (Excepciones e) {
            System.out.println("No se pudo agregar la cita");
            e.printStackTrace();
        }

        assertEquals("2023-12-29", cita.getFecha());
        
    }

}
