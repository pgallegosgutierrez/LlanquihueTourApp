package data;

import model.Tour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Gestiona la carga de tours desde un archivo de texto.
 * Lee el archivo línea por línea, separa los datos y los
 * convierte en objetos Tour almacenados en una lista.
 *
 * @author Paula Gallegos
 * @version 1.0
 */

public class GestorDatos {

    /**
     * Lee el archivo de tours y convierte cada línea en un objeto Tour.
     * Cada línea debe tener el formato: nombre;tipo;precio
     *
     * @param ruta ruta del archivo de texto que contiene los tours
     * @return lista con todos los tours cargados desde el archivo
     */

    public ArrayList<Tour> cargarTours(String ruta) {
        ArrayList<Tour> tours = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");

                String nombre= partes[0].trim();
                String tipo= partes[1].trim();
                int precio= Integer.parseInt(partes[2].trim());

                Tour tour = new Tour(nombre, tipo, precio);
                tours.add(tour);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return tours;
    }
}


