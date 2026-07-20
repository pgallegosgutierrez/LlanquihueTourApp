package model.Personas;


import model.Interface.Registrable;
import model.Utils.ValidadorRut;
import model.exception.RutInvalidoException;


public abstract class Persona implements Registrable {
        protected String nombre;
        protected String rut;
        protected String correo;
        protected String telefono;

        public Persona(String nombre, String rut, String correo, String telefono) throws RutInvalidoException {
            ValidadorRut.validar(rut);
            this.nombre = nombre;
            this.rut = rut;
            this.correo = correo;
            this.telefono = telefono;
        }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public abstract void mostrarResumen();
    }

