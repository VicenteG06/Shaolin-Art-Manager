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

public class MenuModificarObras {
    public static void mostrarMenuModificarObras(){
        System.out.println("========================");
        System.out.println("  Modificar Obra");
        System.out.println("========================");
        System.out.println("1) Modificar Título");
        System.out.println("2) Modificar Año");
        System.out.println("3) Salir del Menú");
    }

    // Método para pedir el ID de la obra que se desee modificar y se busca dentro del mapa
    public static Obra pedirObra(HashMap<String, Obra> obras) throws IOException, EmptyEntryException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String id = "";

        while(id.isEmpty()){//se pide el ID hasta que la entrada no esté vacía
            System.out.println("Ingrese el ID de la obra a modificar:");
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

        //si la obra no existe, se da un aviso y se retorna null
        if(!obras.containsKey(id)){
            System.out.println("Esa obra no se encuentra en el sistema");
            return null;
        }
        Obra o = obras.get(id);
        System.out.println("Obra seleccionada: " + o.getTitulo());
        return o;
    }


    // método para modificar el título de una obra
    public static void modificarTitulo(HashMap<String, Obra> obras) throws IOException, EmptyEntryException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        Obra o = MenuModificarObras.pedirObra(obras);
        
        if(o == null) return;//si la obra no existe, se retorna sin modificar nada

        System.out.println("Título actual: " + o.getTitulo());
        String nuevoTitulo = "";
        
        while(nuevoTitulo.isEmpty()){//se pide el título hasta que la entrada no esté vacía
            System.out.println("Ingrese el nuevo título de la obra:");
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
        o.setTitulo(nuevoTitulo);
        System.out.println("El título fue modificado con éxito.");

    }

    //método para modificar el año de una obra
    public static void modificarAnio(HashMap<String, Obra> obras) throws IOException, EmptyEntryException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        Obra o = MenuModificarObras.pedirObra(obras);
        
        if(o == null) return;//si la obra no existe, se retorna sin modificar nada

        System.out.println("Año registrado actual: " + o.getAnio());
        int nuevoAnio = Validaciones.pedirEnteroPositivo(lector, "Ingrese el nuevo año de la obra:");

        o.setAnio(nuevoAnio);
        System.out.println("Año de la obra modificado con éxito, nuevo año: "+ o.getAnio());
    }

    public static void menuModificarObras(HashMap<String, Obra> obras) throws IOException, EmptyEntryException{
        char opcion = ' ';

        do{
            
            if(obras.isEmpty()){ // se verifica que hayan obras registradas
                System.out.println("No hay obras registradas para modificar.");
                return;
            }

            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            MenuModificarObras.mostrarMenuModificarObras();
            try{
                String entrada = lector.readLine();
                if(entrada == null || entrada.trim().isEmpty()){
                    throw new EmptyEntryException();
                }
                opcion = entrada.charAt(0);
                switch(opcion){
                case '1':
                    MenuModificarObras.modificarTitulo(obras);
                    break;
                case '2':
                    MenuModificarObras.modificarAnio(obras);
                    break;
                case '3':
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
        } while(opcion != '3');
    }
}

