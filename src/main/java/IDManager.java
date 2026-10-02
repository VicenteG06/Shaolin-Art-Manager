
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
       // Se calcula un ID en hexadecimal, se toman los bits más significativos del número y se fuerza a ser positivo
       return String.valueOf((java.util.UUID.randomUUID().getMostSignificantBits() & 0x7FFFFFFFL) % 900 + 200);
    }
}
