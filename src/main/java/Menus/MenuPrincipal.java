/**
 * @archivo: MenuPrincipal.java
 * @Proyecto: Shaolin Art Manager 
 * @Descripción: Clase para manejar el menú principal
 * @author Vicente Gamboa
 * @Lenguaje: Java
*/

import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

public class MenuPrincipal {

    public static Subasta subastaActiva = null; //VARIABLE PARA QUE LA SUBASTA ACTIVA SE MANTENGA ACTIVA AUNQUE SE CIERRE EL MENU DE SUBASTAS

    public static void mostrarMenuPrincipal(){
        System.out.println("========================");
        System.out.println("Shaolin Art Manager");
        System.out.println("========================");
        System.out.println("1) Obras");
        System.out.println("2) Artistas");
        System.out.println("3) Ventas");
        System.out.println("4) Prestamos");
        System.out.println("5) Exposiciones");
        System.out.println("6) Salir");
    }

    public static void menuPrincipal(HashMap<String, Obra> obras, HashMap<String, Artista> artistas, 
                       HashMap<String, Exposicion> exposiciones, ArrayList<Venta> ventas, 
                       HashMap<String, Cliente> clientes, ArrayList<Prestamo> prestamos, ArrayList<Subasta> subastas) throws IOException, EmptyEntryException {
        
        char opcion = ' ';
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        do{
            MenuPrincipal.mostrarMenuPrincipal();
            String entrada = lector.readLine();
            try{
                if(entrada == null || entrada.trim().isEmpty()){
                    throw new EmptyEntryException();
                }
                opcion = entrada.charAt(0);
                switch(opcion){
                case '1': 
                    MenuObras.menuObras(obras, artistas);
                    break;
                case '2':
                    MenuArtistas.menuArtistas(artistas);
                    break;
                case '3':
                    MenuVentas.menuVentas(ventas, obras, clientes, subastas);
                    break;
                case '4':
                    MenuPrestamos.menuPrestamos(prestamos, obras, clientes);
                    break;
                case '5':
                    MenuExposiciones.menuExposiciones(exposiciones, obras);
                    break;
                case '6':
                    System.out.println("Saliendo del menú......");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
                }
            } catch (EmptyEntryException e) {
                System.out.println("Error: " + e.getMessage() + "\n");
            } catch (IOException e) {
                System.out.println("Error de lectura: " + e.getMessage());
                break;
            }
        } while(opcion != '6');
    }
}