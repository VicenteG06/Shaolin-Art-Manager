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

public class Menu {
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

    public static void menuPrincipal(HashMap<String, Obra> obras, HashMap<String, Artista> artistas) throws IOException {
        char opcion;

        do{
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            Menu.mostrarMenuPrincipal();
            opcion = (lector.readLine()).charAt(0);
            switch(opcion){
            case '1': 
                Menu.menuObras(obras);
                break;
            case '2':
                break;
            case '3':
                break;
            case '4':
                break;
            case '5':
                break;
            case '6':
                System.out.println("Saliendo del menú......");
                break;
            default:
                System.out.println("Opción no válida, intente nuevamente.");
            }
        } while(opcion != '6');
    }
    
    public static void mostrarMenuExposiciones(){
        System.out.println("========================");
        System.out.println("       Exposiciones");
        System.out.println("========================");
        System.out.println("1) Mostrar Exposiciones");
        System.out.println("2) Buscar Exposición");
        System.out.println("3) Registrar Exposición");
        System.out.println("4) Eliminar Exposición");
    }
    

    public static void mostarMenuArtistas(){
        System.out.println("========================");
        System.out.println("         Artistas");
        System.out.println("========================");
        System.out.println("1) Mostrar Artistas");
        System.out.println("2) Buscar Obras por Artista");
    }
    public static void mostrarMenuObras(){
        System.out.println("========================");
        System.out.println("           Obras");
        System.out.println("========================");
        System.out.println("1) Mostrar Obras");
        System.out.println("2) Buscar Obra");
        System.out.println("3) Registrar Obra");
        System.out.println("4) Salir del Menú");
    }

    public static void menuObras(HashMap<String, Obra> obras) throws IOException{
        char opcion;

        do{
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            Menu.mostrarMenuObras();
            opcion = (lector.readLine()).charAt(0);
            switch(opcion){
            case '1': 
                Obra.mostrarObras(obras);
                break;
            case '2':
                Obra.buscarObra(obras);
                break;
            case '3':
                break;
            case '4':
                System.out.println("Saliendo del menú......");
                break;
            default:
                System.out.println("Opción no válida, intente nuevamente.");
                return;
            }
        } while(opcion != '4');
    }
    
    
}
