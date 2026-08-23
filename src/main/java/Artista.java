/**
 *
 * @author Vicente
 */

import java.util.*;

public class Artista {
    // Se crean los parametros de la clase
    private String nombre;
    private ArrayList<Obra> obras; 
    
    // Se define el parámetro constructor de la clase para inicializar las listas y la variable "nombre".
    public Artista(){
        nombre = "Desconocido";
        obras = new ArrayList<>();
    }
    
    // Se definen los metodos de la clase.
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void anadirObra(Obra obra){
        obras.add(obra);
    }

    public void mostrarObras(){
        for(int i = 0; i < obras.size(); i++){
            obras.get(i).mostrarAtributos();
        }
    }
    
    public String getNombre() {return nombre;}
}
