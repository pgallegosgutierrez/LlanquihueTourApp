package ui;

import data.GestorDatos;
import model.Tour;
import java.util.ArrayList;

/**
 * Clase principal de la aplicación Llanquihue Tour.
 *
 * @author Paula Gallegos
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {

        GestorDatos gd = new GestorDatos();
        ArrayList<Tour> listaTours = gd.cargarTours("src/main/resources/registros.csv");

        // Tabla completa
        System.out.println("=================================================================================================================================================================================");

        System.out.printf(
                "%-53s | %-69s | %-35s%n",
                "              DATOS DEL TOUR",
                "                       EMPRESA OPERADORA",
                "              DATOS DEL GUIA"
        );

        System.out.println("=================================================================================================================================================================================");

        System.out.printf(
                "%-28s %-15s %-9s|%-26s %-12s %-30s | %-20s %-10s %-20s%n",
                "TOUR",
                "TIPO",
                "PRECIO",
                "EMPRESA",
                "TELEFONO",
                "CORREO",
                "GUIA",
                "EXPER.",
                "CONTACTO"
        );

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Tour tour : listaTours) {

            System.out.printf("%-28s %-15s $%-7d | %-25s %-12s %-30s | %-20s %-10d %-12s%n",
            tour.getNombre(),
            tour.getTipo(),
            tour.getPrecio(),
            tour.getOperador().getNombre(),
            tour.getOperador().getTelefono(),
            tour.getOperador().getCorreo(),
            tour.getOperador().getGuia().getNombre(),
            tour.getOperador().getGuia().getExperiencia(),
            tour.getOperador().getGuia().getContacto());

        }

        System.out.println("=================================================================================================================================================================================");

        // Bucle 2: solo culturales (SEPARADO, después del primero)
        System.out.println("\n------TOURS CULTURALES------");
        for (Tour tour :listaTours){
            if (tour.getTipo().equalsIgnoreCase("cultural"))
                System.out.println(tour);
        }
    }



}
