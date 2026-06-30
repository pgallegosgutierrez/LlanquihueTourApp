package data;

import model.ExcursionCultural;
import model.PaseoLacustre;
import model.RutaGastronomica;

public class GestorServicios{

    public void mostrarServicios () {
        RutaGastronomica ruta_gastro1=new RutaGastronomica("Pasteleria en Frutillar", 3,4);
        RutaGastronomica ruta_gastro2=new RutaGastronomica("Ruta de la cerveza",3,2);

        PaseoLacustre paseo_lacustre1=new PaseoLacustre("Lago Todos los Santos",1,"Catamarán");
        PaseoLacustre paseo_lacustre2=new PaseoLacustre("Lago Llanquihue", 1.5, "Catamarán");

        ExcursionCultural excursion_cultural1=new ExcursionCultural("Museo Colonial",1.5,"Museo Colonial Alemán Frutillar");
        ExcursionCultural excursion_cultural2=new ExcursionCultural("Cultura en Frutillar",2,"Teatro el Lago");

        System.out.println(ruta_gastro1);
        System.out.println(ruta_gastro2);
        System.out.println(paseo_lacustre1);
        System.out.println(paseo_lacustre2);
        System.out.println(excursion_cultural1);
        System.out.println(excursion_cultural2);
    }

}
