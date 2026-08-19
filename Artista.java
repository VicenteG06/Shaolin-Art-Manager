/**
 *
 * @author Vicente
 */

import java.util.*;

public class Artista {
    // Se crean los parametros de la clase
    private String nombre;
    private ArrayList<Obra> obras; 
    private ArrayList<Obra> obrasVendidas;
    private ArrayList<Obra> obrasPrestadas;
    
    // Se define el parámetro constructor de la clase para inicializar las listas y la variable "nombre".
    public Artista(){
        nombre = "Desconocido";
        obras = new ArrayList<>();
        obrasVendidas = new ArrayList<>();
        obrasPrestadas = new ArrayList<>();
    }
    
    // Se definen los metodos de la clase.
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void anadirObra(Obra obra){
        obras.add(obra);
    }
    
    public void anadirObraVendida(Obra obraVendida){
        obrasVendidas.add(obraVendida);
    }
    
    public void anadirObraPrestada(Obra obraPrestada){
        obrasPrestadas.add(obraVendida);
    }

    public void mostrarObras(){
        for(i = 0; i < obras.size(); i++){
            System.out.println(obras[i].autor);
            System.out.println(obras[i].id);
            System.out.println(obras[i].nombre);
            System.out.println(obras[i].estado);
            System.out.println(obras[i].precio);
        }
    }
    public void mostrarObrasVendidas(){
        for(i = 0; i < obrasVendidas.size(); i++){
            System.out.println(obrasVendidas[i].autor);
            System.out.println(obrasVendidas[i].id);
            System.out.println(obrasVendidas[i].nombre);
            System.out.println(obrasVendidas[i].estado);
            System.out.println(obrasVendidas[i].precio);
        }
    }
    public void mostrarObrasPrestadas(){
        for(i = 0; i < obrasPrestadas.size(); i++){
            System.out.println(obrasPrestadas[i].autor);
            System.out.println(obrasPrestadas[i].id);
            System.out.println(obrasPrestadas[i].nombre);
            System.out.println(obrasPrestadas[i].estado);
            System.out.println(obrasPrestadas[i].precio);
        }
    }
    
    
    public Sring getNombre() {return nombre}
}
