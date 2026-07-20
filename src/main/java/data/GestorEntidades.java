package data;

import model.Interface.Registrable;
import model.Personas.Cliente;
import model.Personas.Guia;
import model.ServicioTuristico.ExcursionCultural;
import model.ServicioTuristico.PaseoLacustre;
import model.ServicioTuristico.RutaGastronomica;
import model.Operadores.Transporte;
import model.exception.RutInvalidoException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase encargada de cargar y guardar datos desde un archivo .txt
 * Cada línea del archivo lleva una etiqueta que indica el tipo de objeto a crear.
 *
 * @author Paula Gallegos
 */
public class GestorEntidades {

    /**
     * Lee el archivo indicado y convierte cada línea en un objeto Registrable.
     *
     * @param ruta ruta del archivo .txt a leer
     * @return lista con todas las entidades cargadas
     */
    public ArrayList<Registrable> cargarDatos(String ruta) {
        ArrayList<Registrable> registros = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                String etiqueta = partes[0].trim();

                try {
                    if (etiqueta.equals("GUIA")) {
                        String nombre = partes[1].trim();
                        String rut = partes[2].trim();
                        String correo = partes[3].trim();
                        String telefono = partes[4].trim();
                        int experiencia = Integer.parseInt(partes[5].trim());
                        registros.add(new Guia(nombre, rut, correo, telefono, experiencia));

                    } else if (etiqueta.equals("TRANSPORTE")) {
                        String tipo = partes[1].trim();
                        int capacidad = Integer.parseInt(partes[2].trim());
                        String patente = partes[3].trim();
                        registros.add(new Transporte(tipo, capacidad, patente));

                    } else if (etiqueta.equals("CLIENTE")) {
                        String nombre = partes[1].trim();
                        String rut = partes[2].trim();
                        String correo = partes[3].trim();
                        String telefono = partes[4].trim();
                        String nacionalidad = partes[5].trim();
                        registros.add(new Cliente(nombre, rut, correo, telefono, nacionalidad));

                    } else if (etiqueta.equals("RUTA GASTRONOMICA")) {
                        String nombre = partes[1].trim();
                        double duracion = Double.parseDouble(partes[2].trim());
                        int paradas = Integer.parseInt(partes[3].trim());
                        registros.add(new RutaGastronomica(nombre, duracion, paradas));

                    } else if (etiqueta.equals("PASEO LACUSTRE")) {
                        String nombre = partes[1].trim();
                        double duracion = Double.parseDouble(partes[2].trim());
                        String embarcacion = partes[3].trim();
                        registros.add(new PaseoLacustre(nombre, duracion, embarcacion));

                    } else if (etiqueta.equals("EXCURSION CULTURAL")) {
                        String nombre = partes[1].trim();
                        double duracion = Double.parseDouble(partes[2].trim());
                        String lugar = partes[3].trim();
                        registros.add(new ExcursionCultural(nombre, duracion, lugar));
                    }

                } catch (RutInvalidoException e) {
                    System.out.println("Línea omitida: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return registros;
    }
    /**
     * Guarda todas las entidades de la lista en un archivo .txt,
     * usando el mismo formato con etiquetas que lee cargarDatos().
     *
     * @param registros lista de entidades a guardar
     * @param ruta ruta del archivo destino
     */
    public void guardarDatos(ArrayList<Registrable> registros, String ruta) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {

            for (Registrable r : registros) {
                if (r instanceof Guia) {
                    Guia g = (Guia) r;
                    bw.write("GUIA;" + g.getNombre() + ";" + g.getRut() + ";"
                            + g.getCorreo() + ";" + g.getTelefono() + ";" + g.getExperiencia());
                    bw.newLine();

                } else if (r instanceof Cliente) {
                    Cliente c = (Cliente) r;
                    bw.write("CLIENTE;" + c.getNombre() + ";" + c.getRut() + ";"
                            + c.getCorreo() + ";" + c.getTelefono() + ";" + c.getNacionalidad());
                    bw.newLine();

                } else if (r instanceof Transporte) {
                    Transporte t = (Transporte) r;
                    bw.write("TRANSPORTE;" + t.getTipo() + ";" + t.getCapacidad() + ";" + t.getPatente());
                    bw.newLine();

                } else if (r instanceof RutaGastronomica) {
                    RutaGastronomica rg = (RutaGastronomica) r;
                    bw.write("RUTA GASTRONOMICA;" + rg.getNombre() + ";"
                            + rg.getDuracionHoras() + ";" + rg.getNumeroDeParadas());
                    bw.newLine();

                } else if (r instanceof PaseoLacustre) {
                    PaseoLacustre pl = (PaseoLacustre) r;
                    bw.write("PASEO LACUSTRE;" + pl.getNombre() + ";"
                            + pl.getDuracionHoras() + ";" + pl.getTipoEmbarcacion());
                    bw.newLine();

                } else if (r instanceof ExcursionCultural) {
                    ExcursionCultural ec = (ExcursionCultural) r;
                    bw.write("EXCURSION CULTURAL;" + ec.getNombre() + ";"
                            + ec.getDuracionHoras() + ";" + ec.getLugarHistorico());
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}