/**
 * @archivo: EmptyEntryException.java
 * @Proyecto: Shaolin Art Manager 
 * @Descripción: Clase para el manejo de excepciones por entrada vacía
 * @author Vicente Gamboa
 * @Lenguaje: Java
*/

public class EmptyEntryException extends Exception {
    public EmptyEntryException(){
        // Se pide que se ingrese una opción válida por teclado
        super("Ingrese una opción válida.");
    }
}
