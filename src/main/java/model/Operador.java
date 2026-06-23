package model;

/**
 * Esta clase representa al operador involucrado en el tour
 */
public class Operador {
    private String nombre;
    private String telefono;
    private String correo;
    private Guia guia;

    /**
     *
     * @param nombre nombre del operador o de la empresa
     * @param telefono telefono del operador
     * @param correo correo del operador
     * @param guia Es el objeto Guia en una relacion de composicion de clases
     */

    /**
     *Metodo Constructor clase operadpor
     */
    public Operador(String nombre, String telefono, String correo,Guia guia) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.guia=guia;

    }

    /**
     *Metodos Getter and Setter
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Guia getGuia(){
        return guia;
    }

    public void setGuia(Guia guia) {
        this.guia = guia;
    }

    /**
     * Representacion en texto del Operador
     * @return cadena con los datos en formato: Empresa operadora, Telefono, Correo, Informacion del guia
     */
    @Override
    public String toString (){
        return "Empresa: "+nombre+" Teléfono:+569 "+telefono+" Correo: "+correo+ " Información Guía: "+guia;
    }
}
