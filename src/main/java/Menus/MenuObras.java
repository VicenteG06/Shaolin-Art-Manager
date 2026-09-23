/**
 * @archivo: MenuObras.java
 * @Proyecto: Shaolin Art Manager 
 * @Descripción: Clase para manejar el menú de Obras
 * @author Vicente Gamboa
 * @Lenguaje: Java
*/
 

import java.io.*;
import java.util.*;
 
public class MenuObras {
    public static void mostrarMenuObras(){
        System.out.println("========================");
        System.out.println("           Obras");
        System.out.println("========================");
        System.out.println("1) Mostrar Obras");
        System.out.println("2) Buscar Obra");
        System.out.println("3) Registrar Obra");
        System.out.println("4) Salir del Menú");
    }
    public static void mostrarObras(HashMap<String, Obra> obras){
        if (obras.isEmpty()){
            System.out.println("No hay obras registradas.");
            return;
        }
        for(Obra o: obras.values()){
            o.mostrarAtributos();
        }
    }
    public static Obra buscarObra(HashMap<String, Obra> obras) throws IOException{
        System.out.println("Ingrese el ID de la obra:");
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String idObra = lector.readLine();
        
        if(idObra != null && obras.containsKey(idObra.trim())){
            Obra o = obras.get(idObra.trim());
            o.mostrarAtributos();
            return o;
        }
        System.out.println("No existe esa obra");
        return null;
    }
    
    //------------------- CAMBIO: Se añadió HashMap<String, Artista> artistas como parámetro
    public static void registrarObra(HashMap<String, Obra> obras, HashMap<String, Artista> artistas) throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese el Nombre de la Obra:");
        String nombre = lector.readLine();
        if (nombre == null || nombre.trim().isEmpty()){
            System.out.println("El nombre de la obra no puede estar vacío.");
            return;
        }
        nombre = nombre.trim();
 
        String id = IDManager.generarID(nombre);
        
        System.out.println("Ingrese el Nombre del Artista:");
        String nombreArtista = lector.readLine();
        if (nombreArtista == null || nombreArtista.trim().isEmpty()){
            System.out.println("El nombre del artista no puede estar vacío.");
            return;
        }
        nombreArtista = nombreArtista.trim();
        
        int anio = Validaciones.pedirEnteroPositivo(lector, "Ingrese el año de Creación de la Obra:");
        
        Obra o = new Obra(id, nombre, null, "Disponible", anio);
        
        String nombreArtistaLower = nombreArtista.toLowerCase(); 
        Artista a;
        
        if (artistas.containsKey(nombreArtistaLower)) {
            // Como el artista ya existe, lo buscamos en el mapa
            a = artistas.get(nombreArtistaLower);
            a.anadirObra(o); // y añadimos la obra a su lista de obras asociada
            System.out.println("Artista existente encontrado. Obra añadida a su registro.");
        } else {
            // Como el artista no existe, lo creamos
            a = new Artista(nombreArtista, o);
            artistas.put(nombreArtistaLower, a); // y lo metemos al mapa global
            System.out.println("Nuevo artista registrado en el sistema con éxito.");
        }
        
        o.setArtista(a);
 
        obras.put(id, o);
        System.out.println("La obra " + nombre + " fue registrada con éxito (ID: " + id + ").") ;
    }
    
    public static void menuObras(HashMap<String, Obra> obras, HashMap<String, Artista> artistas) throws IOException, EmptyEntryException{
        char opcion = ' ';
 
        do{
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            MenuObras.mostrarMenuObras();
            try{
                String entrada = lector.readLine();
                if(entrada == null || entrada.trim().isEmpty()){
                    throw new EmptyEntryException();
                }
                opcion = entrada.charAt(0);
                switch(opcion){
                case '1': 
                    MenuObras.mostrarObras(obras);
                    break;
                case '2':
                    MenuObras.buscarObra(obras);
                    break;
                case '3':
                    MenuObras.registrarObra(obras, artistas);
                    break;
                case '4':
                    System.out.println("Saliendo del menú......");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
                    break;
                }
            } catch (EmptyEntryException e) {
                System.out.println("Error: " + e.getMessage() + "\n");
            } catch (IOException e) {
                System.out.println("Error de lectura: " + e.getMessage());
                break;
            }
        } while(opcion != '4');
    }
}
