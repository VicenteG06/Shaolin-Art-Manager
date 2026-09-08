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
        for(Obra o: obras.values()){
            o.mostrarAtributos();
        }
    }
    public static Obra buscarObra(HashMap<String, Obra> obras) throws IOException{
        System.out.println("Ingrese el ID de la obra:");
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String idObra = lector.readLine();
        
        if(obras.containsKey(idObra)){
            Obra o = obras.get(idObra);
            o.mostrarAtributos();
            return o;
        }
        System.out.println("No existe esa obra");
        return null;
    }
    public static void registrarObra(HashMap<String, Obra> obras) throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese el Nombre de la Obra:");
        String nombre = lector.readLine();

        String id = IDManager.generarID(nombre);
        
        System.out.println("Ingrese el Nombre del Artista:");
        String nombreArtista = lector.readLine();
        
        int anio = Validaciones.pedirEnteroPositivo(lector, "Ingrese el año de Creación de la Obra:");
        
        Obra o = new Obra(id, nombre, null, "Disponible", anio);
        
        Artista a = new Artista(nombreArtista, o);

        o.setArtista(a);

        obras.put(id, o);
    }
    public static void menuObras(HashMap<String, Obra> obras) throws IOException, EmptyEntryException{
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
                    MenuObras.registrarObra(obras);
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