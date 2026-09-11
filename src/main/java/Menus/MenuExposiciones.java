/**
 * @archivo: MenuExposiciones.java
 * @Proyecto: Shaolin Art Manager 
 * @Descripción: Clase para manejar el menú de Exposiciones
 * @author Vicente Gamboa
 * @Lenguaje: Java
*/

import java.io.*;
import java.util.*;
import java.time.*;

public class MenuExposiciones {
    // menu exposiciones
    public static void mostrarMenuExposiciones(){
        System.out.println("========================");
        System.out.println("       Exposiciones");
        System.out.println("========================");
        System.out.println("1) Mostrar Exposiciones");
        System.out.println("2) Buscar Exposición");
        System.out.println("3) Registrar Exposición");
        System.out.println("4) Eliminar Exposición");
        System.out.println("5) Añadir Obra a Exposición");
        System.out.println("6) Eliminar Obra de Exposición");
        System.out.println("7) Salir del Menú");
    }
    public static void mostrarExposiciones(HashMap<String, Exposicion> exposiciones) throws IOException{

        if(exposiciones.isEmpty()){
            System.out.println("No hay exposiciones actuales.");
            return;
        }
        for(Exposicion e: exposiciones.values()){
            e.mostrarAtributos();
        }
        PresioneTeclaParaContinuar.ptpc();
    }

    public static Exposicion buscarExposicion(HashMap<String, Exposicion> exposiciones) throws IOException{
        //PARA QUE SI el mapa está vacío, se muestre un mensaje y no se busque la exposición 
        if (exposiciones.isEmpty()) {
            System.out.println("No hay exposiciones registradas en el sistema");
            return null;
        }
        
        System.out.println("Ingrese el ID de la exposición:");
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        String id = lector.readLine();
        if(exposiciones.containsKey(id)){
            Exposicion e = exposiciones.get(id);
            e.mostrarAtributos();
            return e;
        }
        System.out.println("Esta obra no se encuentra en el sistema");
        return null;
    }
    public static void registrarExposicion(HashMap<String, Exposicion> exposiciones, HashMap<String, Obra> obras) throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese el nombre de la Exposición:");
        String nombre = lector.readLine();
        
        String id = IDManager.generarID(nombre);


        LocalDate fInicio = Validaciones.pedirFecha(lector, "Ingrese la fecha de inicio de la exposición (formato: AAAA-MM-DD):");
        
        //validacvión para que la fecha de termino sea después de la de inicio
        LocalDate fTermino = null;
        while (fTermino == null) {
            fTermino = Validaciones.pedirFecha(lector, "Ingrese la fecha de termino de la exposición (formato: AAAA-MM-DD) :");
            if (fTermino.isBefore(fInicio)) {
                System.out.println("Error: La fecha de término no puede ser anterior a la de inicio");
                fTermino = null; // """"reiniciar""" para que vuelva a preguntar
            }
        }
        
        Obra o = MenuObras.buscarObra(obras);
        
        //------------------- CAMBIO: Se envían directo los toString() de las LocalDate si tu clase Exposicion recibe Strings
        Exposicion e = new Exposicion(id, nombre, fInicio.toString(), fTermino.toString(), o);
        exposiciones.put(id, e);
    }
    public static void eliminarExposicion(HashMap<String, Exposicion> exposiciones) throws IOException{
        System.out.println("Ingrese el id de la Exposición a Eliminar:");
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String id = lector.readLine();
        if(exposiciones.containsKey(id)){
            exposiciones.remove(id);
        }
        System.out.println("No existe esa Exposición.");
    }

    public static void anadirObraExposicion(HashMap<String, Exposicion> exposiciones, HashMap<String, Obra> obras) throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese el ID de la Exposición a la que desea añadir una obra:");
        String idExpo = lector.readLine();
        if(!exposiciones.containsKey(idExpo)){
            System.out.println("Esa Exposición no existe.");
            return;
        }
        Exposicion e = exposiciones.get(idExpo);
        System.out.println("Ingrese el ID de la Obra a añadir a la Exposición:");
        String idObra = lector.readLine();
        if(obras.containsKey(idObra)){
            Obra o = obras.get(idObra);
            if(e.anadirObra(o)){
                System.out.println("La obra: " + o.getTitulo() + " ha sido añadida correctamente.");
                return;
            }
            System.out.println("La obra ya se encuentra en esta exposición.");
            return;
        }
        System.out.println("La obra no existe dentro del sistema.");
        return;
    }

    public static void eliminarObraExposicion(HashMap<String, Exposicion> exposiciones, HashMap<String, Obra> obras) throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese el ID de la Exposición:");
        String idExpo = lector.readLine();
        if(!exposiciones.containsKey(idExpo)){
            System.out.println("Esa Exposición no existe.");
            return;
        }
        Exposicion e = exposiciones.get(idExpo);
        System.out.println("Ingrese el ID de la Obra que desea eliminar la Exposición:");
        String idObra = lector.readLine();
        if(obras.containsKey(idObra)){
            Obra o = obras.get(idObra);
            if(e.eliminarObra(o)){
                System.out.println("La obra: " + o.getTitulo() + " ha sido eliminada correctamente.");
                return;
            }
        }
        System.out.println("La obra no existe dentro del sistema.");
        return;


    }

    public static void menuExposiciones(HashMap<String, Exposicion> exposiciones, HashMap<String, Obra> obras) throws IOException, EmptyEntryException{
        char opcion = ' ';

        do{
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            MenuExposiciones.mostrarMenuExposiciones();
            try{
                String entrada = lector.readLine();
                if(entrada == null || entrada.trim().isEmpty()){
                    throw new EmptyEntryException();
                }
                opcion = entrada.charAt(0);
                switch(opcion){
                case '1': 
                    MenuExposiciones.mostrarExposiciones(exposiciones);
                    break;
                case '2':
                    MenuExposiciones.buscarExposicion(exposiciones);
                    break;
                case '3':
                    MenuExposiciones.registrarExposicion(exposiciones, obras);
                    break;
                case '4':
                    MenuExposiciones.eliminarExposicion(exposiciones);
                    break;
                case '5': 
                    MenuExposiciones.anadirObraExposicion(exposiciones, obras);
                    break;
                case '6':
                    MenuExposiciones.eliminarObraExposicion(exposiciones, obras);
                    break;
                case '7':
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
        } while(opcion != '7');
    }
}