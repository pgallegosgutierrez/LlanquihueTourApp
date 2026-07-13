package model.ServicioTuristico;

import model.Interface.Registrable;

public abstract class  ServicioTuristico implements Registrable {

    protected String nombre;
    protected double duracionHoras;

    public ServicioTuristico (String nombre,double duracionHoras){
        this.nombre=nombre;
        this.duracionHoras=duracionHoras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(double duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    public abstract void mostrarResumen();

    @Override
    public String toString(){
        return "Tour: "+nombre+"| Duración: "+duracionHoras;
    }
}
