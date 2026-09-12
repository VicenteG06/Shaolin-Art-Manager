
/**
 * @archivo: IDManager.java
 * @Proyecto: Shaolin Art Manager 
 * @Descripción: Clase para crear IDs
 * @author Vicente Gamboa
 * @Lenguaje: Java
*/

public class IDManager {
    public static String generarID(String cadena){
        int id = 0;
        // Se genera el ID mediante una función Hash.
        for(int i = 0; i < cadena.length(); i++){
            id += ((int) cadena.charAt(i)) * Math.pow(31, i);
        }
        // Se pasa el id a String
        String idString = "" + (id % 251);
        return idString;
    }
}
