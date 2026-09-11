/**
 * @archivo: PresioneTeclaParaContinuar.java
 * @Proyecto: Shaolin Art Manager 
 * @Descripción: Clase para hacer que el programa no avance hasta que el usuario presione una tecla
 * @author Antonia Avello, Vicente Gamboa
 * @Lenguaje: Java
**/

import java.io.*;

public class PresioneTeclaParaContinuar {
    public static void ptpc(){
        try {
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Presione ENTER Para Contiunar.....");
            lector.readLine();
        } catch (IOException e) {}
    }

}


