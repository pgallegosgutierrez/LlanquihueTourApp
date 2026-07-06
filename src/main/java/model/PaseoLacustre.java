package model;

public class PaseoLacustre extends ServicioTuristico {
    private String tipoEmbarcacion;

    public PaseoLacustre (String nombre, double duracionHoras,String tipoEmbarcacion){
        super (nombre,duracionHoras);
        this.tipoEmbarcacion=tipoEmbarcacion;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(toString());
    }

    @Override
    public String toString(){
        return super.toString()+"|Tipo de Embarcación: "+tipoEmbarcacion;
    }

}
