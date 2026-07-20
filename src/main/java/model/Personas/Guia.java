package model.Personas;

import model.exception.RutInvalidoException;

/**
 * Esta clase modela a un Guia del tour
 */
public class Guia extends Persona {
    private int experiencia;

    public Guia (String nombre, String rut, String correo, String telefono,int experiencia)throws RutInvalidoException {
        super (nombre, rut, correo, telefono);
        this.experiencia=experiencia;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    @Override
        public void mostrarResumen() {
            System.out.println("Nombre:"+ nombre);
            System.out.println("Rut:"+ rut);
            System.out.println("Correo:"+ correo);
            System.out.println("Telefono:"+ telefono);
            System.out.println("Años de experiencia: "+experiencia);
    }
}
