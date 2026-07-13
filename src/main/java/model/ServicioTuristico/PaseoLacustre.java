package model.ServicioTuristico;

public class PaseoLacustre extends ServicioTuristico {
    private String tipoEmbarcacion;

    public PaseoLacustre (String nombre, double duracionHoras,String tipoEmbarcacion){
        super (nombre,duracionHoras);
        this.tipoEmbarcacion=tipoEmbarcacion;
    }

    public String getTipoEmbarcacion() {
        return tipoEmbarcacion;
    }

    public void setTipoEmbarcacion(String tipoEmbarcacion) {
        this.tipoEmbarcacion = tipoEmbarcacion;
    }

    @Override
    public void mostrarResumen() {
        System.out.println(toString());
    }

    @Override
    public String toString(){
        return super.toString()+"|Tipo de Embarcación: "+tipoEmbarcacion;
    }

}
