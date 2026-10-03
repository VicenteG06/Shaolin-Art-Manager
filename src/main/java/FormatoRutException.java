
/**
 * @archivo: FormatoRutException.java
 * @Proyecto: Shaolin Art Manager 
 * @Descripción: Clase para el manejo de excepciones de RUT
 * @author Antonia Avello
 * @Lenguaje: Java
*/

public class FormatoRutException extends Exception {
    public FormatoRutException(){
        super("Error: El RUT ingresado es inválido");
    }
}
