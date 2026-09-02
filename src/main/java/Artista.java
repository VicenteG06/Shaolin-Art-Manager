/**
 *
 * @author Vicente
 */

import java.util.*;
import java.io.*;

public class Artista {
    // Se crean los parametros de la clase.
    private String nombre;
    private ArrayList<Obra> obras; 
    
    // Se define el parámetro constructor de la clase para inicializar las listas y la variable "nombre".
    public Artista(){
        nombre = "Desconocido";
        obras = new ArrayList<>();
    }
    
    // Se define la sobrecarga del constructor.
    public Artista(String nombre, Obra obra){
        this.nombre = nombre;
        obras = new ArrayList<>();
        obras.add(obra);
    }

    // Se definen los setters y getters.
    public void setNombre(String nombre){ this.nombre = nombre;}
    public String getNombre() {return nombre;}

    // Se define el método de añadir obra y solo si esta no existe en la lista.
    public boolean anadirObra(Obra obra){
        for(int i = 0; i < obras.size(); i++){
            if((obras.get(i)).getId() == obra.getId()){
                return false;
            }
        }
        obras.add(obra);
        return true;
    }

    // Se define el método para mostrar las obras del artista.
    public void mostrarObras(){
        //Si no hay obras en la lista, se da un aviso y se retorna.
        if(obras.size() == 0){
            System.out.println("Este artista no tiene obras.");
            return;
        }
        //Si hay obras en la lista, se imprimen los atributos de cada obra.
        for(int i = 0; i < obras.size(); i++){
            obras.get(i).mostrarAtributos();
        }
    }

    public static void mostrarArtistas(HashMap<String, Artista> artistas){
        System.out.println("========================");
        System.out.println("        ARTISTAS");
        System.out.println("========================");

        for(Artista a : artistas.values()){
            System.out.println("-> " + a.getNombre());
        }
    }

    public static void buscarObrasArtista(HashMap<String, Artista> artistas) throws IOException {
        System.out.println("Ingrese el Artista de las Obras:");
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String nombre = lector.readLine();

        if(artistas.containsKey(nombre)){
            Artista a = artistas.get(nombre);
            a.mostrarObras();
        }
        else System.out.println("Este artista no se encuentra en el sistema");
    }
}
