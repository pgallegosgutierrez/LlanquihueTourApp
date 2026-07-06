package model;



public class RutaGastronomica extends ServicioTuristico{
    private int numeroDeParadas;

public RutaGastronomica (String nombre,double duracionHoras,int numeroDeParadas){
    super (nombre,duracionHoras);
    this.numeroDeParadas = numeroDeParadas;
}

    @Override
    public void mostrarInformacion() {
        System.out.println(toString());
    }

    @Override
    public String toString(){
    return super.toString ()+"| Paradas: "+numeroDeParadas;
}
}
