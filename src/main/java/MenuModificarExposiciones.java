/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Netaxion
 */

import java.io.*;
import java.util.*;
import java.time.*;

public class MenuModificarExposiciones {
    public static void mostrarMenuModificarExposiciones(){
        System.out.println("========================");
        System.out.println("  Modificar Exposición");
        System.out.println("========================");
        System.out.println("1) Modificar Título");
        System.out.println("2) Modificar Fecha de Inicio");
        System.out.println("3) Modificar Fecha de Término");
        System.out.println("4) Salir del Menú");
    }

        // Método para pedir el ID de la exposición que se desee modificar y se busca dentro del mapa
    public static Exposicion pedirExposicion(HashMap<String, Exposicion> exposiciones) throws IOException, EmptyEntryException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String id = "";

        while(id.isEmpty()){//se pide el ID hasta que la entrada no esté vacía
            System.out.println("Ingrese el ID de la Exposición a modificar:");
            try{
                String entrada = lector.readLine();
                if(entrada == null || entrada.trim().isEmpty()){
                    throw new EmptyEntryException();
                }
                id = entrada.trim();
            } catch (EmptyEntryException ex) {
                System.out.println("Error: El ID no puede estar vacío.\n");
            }
        }

        //si la exposición no existe, se da un aviso y se retorna null
        if(!exposiciones.containsKey(id)){
            System.out.println("Esa exposición no se encuentra en el sistema");
            return null;
        }
        Exposicion e = exposiciones.get(id);
        System.out.println("Exposición seleccionada: " + e.getTitulo());
        return e;
    }

    // método para modificar el título de una exposición
    public static void modificarTitulo(HashMap<String, Exposicion> exposiciones) throws IOException, EmptyEntryException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        Exposicion e = MenuModificarExposiciones.pedirExposicion(exposiciones);
        
        if(e == null) return;//si la exposición no existe, se retorna sin modificar nada

        System.out.println("Título actual: " + e.getTitulo());
        String nuevoTitulo = "";
        
        while(nuevoTitulo.isEmpty()){//se pide el título hasta que la entrada no esté vacía
            System.out.println("Ingrese el nuevo título de la Exposición:");
            try{
                String entrada = lector.readLine();
                if(entrada == null || entrada.trim().isEmpty()){
                    throw new EmptyEntryException();
                }
                nuevoTitulo = entrada.trim();
            } catch (EmptyEntryException ex) {
                System.out.println("Error: El título no puede estar vacío.\n");
            }
        }
        if(e.cambiarTitulo(nuevoTitulo)){
            System.out.println("El título fue modificado con éxito.");
            return;
        }
        System.out.println("No se pudo modificar el título de la Exposición.");
    }

    //método para modificar la fecha de inicio de una exposición
    public static void modificarFechaInicio(HashMap<String, Exposicion> exposiciones) throws IOException, EmptyEntryException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        Exposicion e = MenuModificarExposiciones.pedirExposicion(exposiciones);
        
        if(e == null) return;//si la exposición no existe, se retorna y no se modifica nada

        System.out.println("Fecha de inicio actual: " + e.getfechaInicio());
        System.out.println("Fecha de término actual: " + e.getfechaTermino());
        LocalDate nuevaFecha = Validaciones.pedirFecha(lector, "Ingrese la nueva fecha de inicio (formato: AAAA-MM-DD):");

        if(e.cambiarFechaInicio(nuevaFecha)){
            System.out.println("La fecha de inicio fue modificada con éxito.");
            return;
        }
        System.out.println("Error: La fecha de inicio no puede ser posterior a la fecha de término.");
    }

    // Método para modificar la fecha de término de una exposición
    public static void modificarFechaTermino(HashMap<String, Exposicion> exposiciones) throws IOException, EmptyEntryException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        Exposicion e = MenuModificarExposiciones.pedirExposicion(exposiciones);

        if(e == null) return;//si la exposición no existe, se retorna y no se modifica nada

        System.out.println("Fecha de inicio actual: " + e.getfechaInicio());
        System.out.println("Fecha de término actual: " + e.getfechaTermino());
        LocalDate nuevaFecha = Validaciones.pedirFecha(lector, "Ingrese la nueva fecha de término (formato: AAAA-MM-DD):");

        if(e.cambiarFechaTermino(nuevaFecha)){
            System.out.println("La fecha de término fue modificada con éxito.");
            return;
        }
        System.out.println("Error: La fecha de término no puede ser anterior a la fecha de inicio.");
    }

    public static void menuModificarExposiciones(HashMap<String, Exposicion> exposiciones) throws IOException, EmptyEntryException{
        char opcion = ' ';

        do{
            
            if(exposiciones.isEmpty()){ // se verifica que hayan exposiciones registradas
                System.out.println("No hay exposiciones registradas para modificar.");
                return;
            }

            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            MenuModificarExposiciones.mostrarMenuModificarExposiciones();
            try{
                String entrada = lector.readLine();
                if(entrada == null || entrada.trim().isEmpty()){
                    throw new EmptyEntryException();
                }
                opcion = entrada.charAt(0);
                switch(opcion){
                case '1':
                    MenuModificarExposiciones.modificarTitulo(exposiciones);
                    break;
                case '2':
                    MenuModificarExposiciones.modificarFechaInicio(exposiciones);
                    break;
                case '3':
                    MenuModificarExposiciones.modificarFechaTermino(exposiciones);
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