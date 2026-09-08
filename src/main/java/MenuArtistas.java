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

public class MenuArtistas {
    public static void mostrarMenuArtistas(){
        System.out.println("========================");
        System.out.println("         Artistas");
        System.out.println("========================");
        System.out.println("1) Mostrar Artistas");
        System.out.println("2) Buscar Obras por Artista");
        System.out.println("3) Salir del Menú");
    }
    public static void mostrarArtistas(HashMap<String, Artista> artistas){
        System.out.println("========================");
        System.out.println("         ARTISTAS");
        System.out.println("========================");

        for(Artista a : artistas.values()){
            System.out.println("-> " + a.getNombre());
        }
    }

    public static void buscarObrasArtista(HashMap<String, Artista> artistas) throws IOException {
        System.out.println("Ingrese el Artista de las Obras:");
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String nombre = lector.readLine();

        //SE DEBE USAR to lowercase porque los artistas se almacenan en minúscula, para que la validación no sea case sensitive
        if(artistas.containsKey(nombre.toLowerCase())){

            Artista a = artistas.get(nombre.toLowerCase());
            a.mostrarObras();
        }
        else System.out.println("Este artista no se encuentra en el sistema");
    }
    public static void menuArtistas(HashMap<String, Artista> artistas) throws IOException, EmptyEntryException{ 
        char opcion = ' ';
        
        do{
            BufferedReader lector = new BufferedReader (new InputStreamReader(System.in));
            MenuArtistas.mostrarMenuArtistas();
            try{
                String entrada = lector.readLine();
                if(entrada == null || entrada.trim().isEmpty()){
                    throw new EmptyEntryException();
                }
                opcion = entrada.charAt(0);
                switch(opcion){
                case '1':
                    MenuArtistas.mostrarArtistas(artistas);
                    break;
                case '2':
                    MenuArtistas.buscarObrasArtista(artistas);
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