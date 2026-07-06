package data;

import model.ExcursionCultural;
import model.PaseoLacustre;
import model.RutaGastronomica;
import model.ServicioTuristico;

import java.util.ArrayList;
import java.util.List;

public class GestorServicios{

    public void gestionarServicios(){
        List <ServicioTuristico>servicios=new ArrayList<>();

        servicios.add(new RutaGastronomica("Pasteleria en Frutillar", 3,4));
        servicios.add(new RutaGastronomica("Ruta de la cerveza",3,2));

        servicios.add(new PaseoLacustre("Lago Todos los Santos",1,"Catamarán"));
        servicios.add(new PaseoLacustre("Lago Llanquihue", 1.5, "Catamarán"));

        servicios.add(new ExcursionCultural("Museo Colonial",1.5,"Museo Colonial Alemán Frutillar"));
        servicios.add(new ExcursionCultural("Cultura en Frutillar",2,"Teatro el Lago"));

        for(ServicioTuristico s:servicios){
            s.mostrarInformacion();
        }
    }



}
