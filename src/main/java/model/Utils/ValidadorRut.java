package model.Utils;

import model.exception.RutInvalidoException;

/**
 * Clase utilitaria destinada a validar el dato Rut
 */
public class ValidadorRut {

    public static void validar(String rut) throws RutInvalidoException {
        if (rut.contains(".")) {
            throw new RutInvalidoException("El RUT no debe llevar puntos");
        }
        if (!rut.contains("-")) {
            throw new RutInvalidoException("El RUT debe incluir el guion");
        }
    }
}