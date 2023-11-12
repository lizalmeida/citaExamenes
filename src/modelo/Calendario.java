package modelo;

import java.util.ArrayList;
import java.util.List;

import servicios.Excepciones;

import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Calendario {
    ArrayList<Date> listaFeriado = new ArrayList<>();
    ArrayList<Date> listaHorario = new ArrayList<>();

    private List<Cita> citas = new ArrayList<>();
    public List<Cita> getCitas() {
        return citas;
    }
    
    public void agregarCita(Cita cita) throws Excepciones{
        if (validarHorario(cita) && 
            validarCitaSimultanea(cita) &&
            validarFechaMayorActual(cita) &&
            validarFinSemana(cita) &&
            validarFeriado(cita)) {
                citas.add(cita);
        } else {
                throw new Excepciones ("No se pudo agregar la cita");
        }
    }

    public void mostrarCitas() {
        for (Cita cita : citas) {
            cita.mostrarDetalleCita();
        }
    }

    public ArrayList<Date> listaHorario (){
        Calendar calendar = Calendar.getInstance();
        Calendar calendar_fin = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("HH:mm").parse("07:00"));
            calendar_fin.setTime(new SimpleDateFormat("HH:mm").parse("16:00"));

            while (calendar.getTime().compareTo(calendar_fin.getTime())<1){
                listaHorario.add(calendar.getTime());
                calendar.add(Calendar.MINUTE, 20);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listaHorario;
    }

    public ArrayList<Date> listaFeriado (){
        try {
            listaFeriado.add(new SimpleDateFormat("yyyy-MM-dd").parse("2023-08-10"));
            listaFeriado.add(new SimpleDateFormat("yyyy-MM-dd").parse("2023-11-03"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listaFeriado;
    }

    private boolean validarHorario(Cita cita) {
        String horarioCita = cita.getHora();
        final ArrayList<Date> listaHorario = this.listaHorario();
        for (Date d : listaHorario){
            String horaValida = new SimpleDateFormat("HH:mm").format(d);
            if (horarioCita.equals(horaValida)) {
                return true;
            }
        }
        System.out.println("Error: La hora de la cita debe ser entre las 07h00 y las 16h00.");
        return false;
    }

    private boolean validarCitaSimultanea(Cita cita) {
        int citaSimultanea = 0;
        for (Cita c : citas) {
            if (c.getFecha().equals(cita.getFecha()) && c.getHora().equals(cita.getHora())) {
                citaSimultanea++;
            }
            if (citaSimultanea == 2){
                break;
            }
        }

        if (citaSimultanea == 0) {
            return true;
        } else {
            System.out.println("Error: No se pueden tener más de dos citas simultáneas.");
            return false;
        }
    }

    private boolean validarFechaMayorActual(Cita cita) {
        //final DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActual = new Date();

        Calendar calen = Calendar.getInstance();
        calen.setTime(fechaActual);
        
        Date fechaCita;
        try {
            fechaCita = new SimpleDateFormat("yyyy-MM-dd").parse(cita.getFecha());
            if (fechaActual.compareTo (fechaCita) < 0) {
                return true;
            } else {
                System.out.println("Error: La fecha/hora de la cita debe ser posterior a la fecha/hora actual.");
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean validarFinSemana(Cita cita) {
        Calendar calen = Calendar.getInstance();

        try {
            calen.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(cita.getFecha()));
            
            if (calen.get(Calendar.DAY_OF_WEEK) == 1 || calen.get(Calendar.DAY_OF_WEEK) == 7) {
                System.out.println("Error: La fecha de la cita no puede ser sábado ni domingo.");
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean validarFeriado(Cita cita) {
        String fechaCita = cita.getFecha();

        SimpleDateFormat formato = new SimpleDateFormat("MM-dd");

        final ArrayList<Date> listaFeriado = this.listaFeriado();
        for (Date d : listaFeriado){
            String fechaFeriado = new SimpleDateFormat("MM-dd").format(d);

            try {
                if (formato.parse(fechaCita).equals(formato.parse(fechaFeriado))) {
                    System.out.println("Error: La fecha de la cita no puede ser un feriado.");
                    return false;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}

