/**
 * @archivo: Main.java
 * @Proyecto: Shaolin Art Manager 
 * @Descripción: Clase Main del Proyecto
 * @author Vicente Gamboa
 * @Lenguaje: Java
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, EmptyEntryException{
        // Se cargan las obras del archivo .csv.
        HashMap<String, Obra> obras = ManejoArchivos.cargarObrasDesdeCsv("data/obras_de_arte.csv");
        // Se obtiene el mapa de artistas de las obras cargadas del .csv
        HashMap<String, Artista> artistas = ManejoArchivos.getMapaArtistas();

        // Se declaran y se inicializan las colecciones 
        HashMap<String, Exposicion> exposiciones = ManejoArchivos.cargarExposicionesDesdeCsv("data/exposiciones.csv", obras);
        HashMap<String, Cliente> clientes = ManejoArchivos.cargarClientesDesdeCsv("data/clientes.csv", obras);
        ArrayList<Venta> registroVentas = ManejoArchivos.cargarVentasDesdeCsv("data/ventas.csv", clientes, obras);
        ArrayList<Prestamo> registroPrestamos = ManejoArchivos.cargarPrestamosDesdeCsv("data/prestamos.csv", clientes, obras);
        ArrayList<Subasta> registroSubastas = new ArrayList<>();
        
        // Se imprime por pantalla y se declara el menú de selección de modo de ejecución
        char opcion = ' ';
       
        BufferedReader lector = new BufferedReader (new InputStreamReader(System.in));
        System.out.println("========================");
        System.out.println("Shaolin Art Manager");
        System.out.println("========================");
        System.out.println("Seleccione el modo de ejecución:");
        System.out.println("1) Modo Consola");
        System.out.println("2) Modo Ventana");
        try{
            String entrada = lector.readLine();
            if(entrada == null || entrada.trim().isEmpty()){
                throw new EmptyEntryException();
            }
            opcion = entrada.charAt(0);
            switch(opcion){
            case '1':
                MenuPrincipal.menuPrincipal(obras, artistas, exposiciones, registroVentas, clientes, registroPrestamos, registroSubastas);
                break;
            case '2':
                new MenuPrincipalVentana(obras, artistas, exposiciones, registroVentas, clientes, registroPrestamos, registroSubastas).setVisible(true);
                break;
            default:
                System.out.println("Opción no válida, intente nuevamente.");
                break;
            }
        } catch (EmptyEntryException e) {
            // Excepción por si el usuario no ingresó nada por pantalla
            System.out.println(e.getMessage() + "\n");
        } catch (IOException e) {
            // Excepción por si ocurió un error de lectura
            System.out.println("Error de lectura: " + e.getMessage());
            return;
        }
        MenuSubastas.cerrarSubastaActiva(registroSubastas, clientes);
        ManejoArchivos.guardarPrestamosCsv(registroPrestamos, "data/prestamos.csv");
        ManejoArchivos.guardarVentasCsv(registroVentas, "data/ventas.csv");
        ManejoArchivos.guardarClientesCsv(clientes, "data/clientes.csv");
        ManejoArchivos.guardarExposicionesCsv(exposiciones, "data/exposiciones.csv");
    }
}