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

public class Main {
    public static void main(String[] args) throws IOException {
        HashMap<String, Obra> obras = CargarObras.cargarDesdeCsv("data/obras_de_arte.csv");
        HashMap<String, Artista> artistas = CargarObras.getMapaArtistas();

        Main.menu(obras,artistas);
        }
   
    public static void mostrarMenu(){
        System.out.println("========================");
        System.out.println("Shaolin Art Manager");
        System.out.println("========================");
        System.out.println("1) Menú Obras");
        System.out.println("2) Menú Artistas");
        System.out.println("3) Menú Ventas");
        System.out.println("4) Menú Prestamos");
        System.out.println("5) Menú Exposiciones");
        System.out.println("6) Salir");
    }
    
    private static void menu(HashMap<String, Obra> obras, HashMap<String, Artista> artistas) throws IOException {
        char opcion;

        do{
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            Main.mostrarMenu();
            opcion = (lector.readLine()).charAt(0);
            switch(opcion){
            case '1': 
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
                System.out.println("Opción no válida, intente nuevamente");
            }
        } while(opcion != '6');
        


    }
    
}
