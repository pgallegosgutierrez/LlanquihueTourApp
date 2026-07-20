package model.exception;
/**
 * Clase encargada de validar el Rut ingresado por el usuario desde la ventana gráfica y desde la carga de un archivo .txt.
 */
    public class RutInvalidoException extends Exception {
        public RutInvalidoException(String mensaje) {
            super(mensaje);
        }
    }

