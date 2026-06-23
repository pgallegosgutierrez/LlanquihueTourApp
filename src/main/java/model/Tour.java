package model;
/**
 * Representa un tour turístico de Llanquihue Tour.
 * Almacena el nombre, tipo y precio de cada tour ofrecido.
 * @author Paula Gallegos
 * @version 1.0
 */
public class Tour {


        private String nombre;
        private String tipo;
        private int precio;
        private Operador operador;

    /**
     * @param nombre nombre del tour
     * @param tipo tipo o categoria del tour
     * @param precio precio del tour en CLP
     * @param Operador es el objeto operador
     */

    /**
     * Consturctor que crea un nuevo tour con sus datos
     */
        public Tour(String nombre, String tipo, int precio, Operador operador) {
            this.nombre = nombre;
            this.tipo = tipo;
            this.precio = precio;
            this.operador= operador;

        }


    /**
     * Metodos Get y Set
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Operador getOperador(){
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    /**
     * Representacion en texto del tour
     * @return cadena con los datos del tour en el formato: nombre| Tipo: | Precio: $
     */
    @Override
    public String toString() {
        //return nombre+" | Tipo: "+tipo+" | Precio: $"+precio +"| "+Operador;
        return String.format("%-30s %-15s $%-10d %s", nombre, tipo, precio, operador);
    }
}


