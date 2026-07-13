package model.ServicioTuristico;


public class RutaGastronomica extends ServicioTuristico {
    private int numeroDeParadas;

public RutaGastronomica (String nombre,double duracionHoras,int numeroDeParadas){
    super (nombre,duracionHoras);
    this.numeroDeParadas = numeroDeParadas;
}

    public int getNumeroDeParadas() {
        return numeroDeParadas;
    }

    public void setNumeroDeParadas(int numeroDeParadas) {
        this.numeroDeParadas = numeroDeParadas;
    }

    @Override
    public void mostrarResumen() {
        System.out.println(toString());
    }

    @Override
    public String toString(){
    return super.toString ()+"| Paradas: "+numeroDeParadas;
}
}
