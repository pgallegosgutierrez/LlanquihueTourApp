package model;

public class RutaGastronomica extends ServicioTuristico{
    private int numeroDeParadas;

public RutaGastronomica (String nombre,double duracionHoras,int numeroDeParadas){
    super (nombre,duracionHoras);
    this.numeroDeParadas = numeroDeParadas;
}

@Override
    public String toString(){
    return super.toString ()+"| Paradas: "+numeroDeParadas;
}


}
