
/**
 * @archivo: IDManager.java
 * @Proyecto: Shaolin Art Manager 
 * @Descripción: Clase para crear IDs
 * @author Vicente Gamboa
 * @Lenguaje: Java
*/

import java.util.*;

public class IDManager {
    public static String generarID(String cadena){
        long id = 0;
        // Se genera el ID mediante una función Hash.
        for(int i = 0; i < cadena.length(); i++){
            id += ((long) (cadena).charAt(i)) * Math.pow(31, i);
        }

        // Se pasa el id a String
        String idString = "" + (id % 9973);
        return idString;
    }
    public static String generarID(String cadena, HashMap<String, ?> mapa){
        long id = 0;
        int intento = 0;
        for(int i = 0; i < cadena.length(); i++){
            id += ((long) (cadena).charAt(i)) * Math.pow(31, i);
        }
        while(mapa.containsKey(id)){
            // Se genera el ID mediante una función Hash.
            for(int i = 0; i < cadena.length(); i++){
                id += ((long) (cadena + intento).charAt(i)) * Math.pow(31, i);
            }
            intento ++;
        }

        // Se pasa el id a String
        String idString = "" + (id % 9973);
        return idString;
    }
}
