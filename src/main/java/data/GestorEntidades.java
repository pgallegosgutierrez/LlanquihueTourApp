package data;

import model.Interface.Registrable;
import model.Personas.Cliente;
import model.Personas.Guia;
import model.Transporte;

import java.util.ArrayList;
import java.util.List;



public class GestorEntidades {

    public void gestionarServicios() {
        List<Registrable> registros = new ArrayList<>();



        for (Registrable s : registros) {
            s.mostrarResumen();

            if (s instanceof Transporte) {
                System.out.println(" ATENCIÓN: El vehiculo requiere llenado de combustible");
            } else if (s instanceof Guia) {
                System.out.println("ATENCIÓN: Todos los guias deben portar credencial");
            }
            System.out.println("-----------");
        }
    }
}




