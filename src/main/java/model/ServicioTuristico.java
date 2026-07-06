package model;

public abstract class  ServicioTuristico {

    protected String nombre;
    protected double duracionHoras;

    public ServicioTuristico (String nombre,double duracionHoras){
        this.nombre=nombre;
        this.duracionHoras=duracionHoras;
    }

    public abstract void mostrarInformacion();

    @Override
    public String toString(){
        return "Tour: "+nombre+"| Duración: "+duracionHoras;
    }
}
