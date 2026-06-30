package model;

public class ExcursionCultural extends ServicioTuristico {
    private String lugarHistorico;

    public ExcursionCultural (String nombre,double duracionHoras, String lugarHistorico){
        super(nombre,duracionHoras);
        this.lugarHistorico=lugarHistorico;
    }

    @Override
    public String toString(){
        return super.toString()+ "| Lugar Histórico: "+lugarHistorico;
    }


}
