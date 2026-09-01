/**
 *
 * @author Vicente
 */

import java.util.*;

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
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {return nombre;}

    // Se define el método de añadir obra y solo si esta no existe.
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
        if(obras.size() == 0){
            System.out.println("Este artistas no tiene obras.");
        }

        for(int i = 0; i < obras.size(); i++){
            obras.get(i).mostrarAtributos();
        }
    }
}
