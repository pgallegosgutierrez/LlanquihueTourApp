package ui;

import data.GestorDatos;
import model.Tour;
import java.util.ArrayList;

/**
 * Clase principal de la aplicación Llanquihue Tour.
 * Carga los tours desde un archivo de texto, muestra el listado
 * completo y filtra los tours de tipo cultural.
 *
 * @author Paula Gallegos
 * @version 1.0
 */
public class Main {
    public static void main(String[] args){

        GestorDatos gd = new GestorDatos();
        ArrayList<Tour> listaTours = gd.cargarTours("src/main/resources/tours.txt");

        // Bucle 1: listado completo
        System.out.println("-----Listado completo de los tours------");
        for (Tour tour : listaTours){
            System.out.println(tour);
        }

        // Bucle 2: solo culturales (SEPARADO, después del primero)
        System.out.println("\n------Tours Culturales------");
        for (Tour tour : listaTours) {
            if (tour.getTipo().equalsIgnoreCase("cultural")) {
                System.out.println(tour);
            }
        }
    }
}