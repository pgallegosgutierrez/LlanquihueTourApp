package model;

/**
 * Esta clase modela a un Guia del tour
 */
public class Guia {
    private String nombre;
    private int experiencia;
    private String contacto;

    /**
     * @param nombre
     * @param experiencia
     * @param contacto
     */

    public Guia (String nombre, int experiencia,String contacto){
        this.nombre=nombre;
        this.experiencia=experiencia;
        this.contacto=contacto;
    }

    public String getNombre() {
        return nombre;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public String getContacto() {
        return contacto;
    }

    public void setNombre(String nombre) {
        this.nombre= nombre;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @Override
    public String toString(){
        return "Nombre Guia: "+nombre+"| Experiencia: "+experiencia+" años"+"| Contacto:+569 "+contacto;
    }
}
