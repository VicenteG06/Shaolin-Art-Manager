/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vicente
 */

import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

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
        System.out.println("5) Salir del Menú");
    }
    public static void mostrarExposiciones(HashMap<String, Exposicion> exposiciones){

        if(exposiciones.isEmpty()){
            System.out.println("No hay exposiciones actuales.");
            return;
        }
        for(Exposicion e: exposiciones.values()){
            e.mostrarAtributos();
        }
    }

    public static Exposicion buscarExposicion(HashMap<String, Exposicion> exposiciones) throws IOException{
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

        System.out.println("Ingrese la Fecha de Inicio de la Exposición:");
        String fechaInicio = lector.readLine();
        try{
            LocalDate.parse(fechaInicio);
        } catch(DateTimeParseException | NullPointerException e){
            System.out.println("Ingrese una fecha válida");
            return;
        }
        System.out.println("Ingrese la Fecha de Termino de la Exposición:");
        String fechaTermino = lector.readLine();
        try{
            LocalDate.parse(fechaTermino);
        } catch(DateTimeParseException | NullPointerException e){
            System.out.println("Ingrese una fecha válida");
            return;
        }
        Obra o = MenuObras.buscarObra(obras);
        Exposicion e = new Exposicion(id, nombre, fechaInicio, fechaTermino, o);
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
        } while(opcion != '5');
    }
}
