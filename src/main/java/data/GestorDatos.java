package data;

import model.Guia;
import model.Operador;
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
                String[] partes = linea.split(",");
                String nombre_tour = partes[0].trim();
                String tipo_tour= partes[1].trim();
                int precio_tour= Integer.parseInt(partes[2].trim());
                String empresa= partes[3].trim();
                String telefono=partes[4].trim();
                String correo=partes[5].trim();
                //String nombreguia=partes[6].trim(); mas que nada por buena practica, si el nombre de una variable consta de 2 o mas palabras se usa un _ para separarlas
                String nombre_guia=partes[6].trim();
                int experiencia= Integer.parseInt(partes[7].trim());
                String contacto=partes[8].trim();

                Guia guia= new Guia (nombre_guia,experiencia,contacto);
                Operador op1= new Operador(empresa,telefono,correo,guia);
                Tour tour = new Tour(nombre_tour, tipo_tour, precio_tour,op1);
                tours.add(tour);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return tours;
    }
}


