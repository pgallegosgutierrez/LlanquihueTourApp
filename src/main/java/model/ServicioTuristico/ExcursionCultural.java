package model.ServicioTuristico;

public class ExcursionCultural extends ServicioTuristico {
    private String lugarHistorico;

    public ExcursionCultural (String nombre,double duracionHoras, String lugarHistorico){
        super(nombre,duracionHoras);
        this.lugarHistorico=lugarHistorico;
    }

    public String getLugarHistorico() {
        return lugarHistorico;
    }

    public void setLugarHistorico(String lugarHistorico) {
        this.lugarHistorico = lugarHistorico;
    }

    @Override
    public void mostrarResumen() {
        System.out.println(toString());
    }

    @Override
    public String toString(){
        return super.toString()+ "| Lugar Histórico: "+lugarHistorico;
    }


}
