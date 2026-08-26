/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ale
 */
import java.util.*;
import java.time.*;

public class Exposicion {
    //Atributos Exposicion
    private String id;
    private String titulo;
    private LocalDate fechaInicio;
    private LocalDate fechaTermino;
    private ArrayList<Obra> listaObras;
    //  Constructores
    public Exposicion(){
        id= null;
        titulo= "Sin Titulo";
        fechaInicio= null;
        fechaTermino= null;
        listaObras = new ArrayList<>();
    }
    public Exposicion(String id, String titulo, LocalDate fechaInicio,
                      LocalDate fechaTermino, Obra obra){
        this.id = id;
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        //Se añade la primera obra 
        this.listaObras.add(obra);
    }
    public Exposicion(String id, String titulo, String fechaInicio,
                      String fechaTermino, Obra obra){
        this.id= id;
        this.titulo= titulo;
        this.fechaInicio = LocalDate.parse(fechaInicio);
        this.fechaTermino = LocalDate.parse(fechaTermino);
        //Se añade la primera obra 
        this.listaObras.add(obra);
    }
    //  Metodos GET 
    public String getId(){ return id; }
    public String getTitulo(){ return titulo; }
    public LocalDate getfechaInicio(){ return fechaInicio; }
    public LocalDate getfechaTermino(){ return fechaTermino; }
    public ArrayList<Obra> getListaObras(){ return listaObras; }
    //  Metodos SET 
    public void setId(String id){ this.id= id; }
    public void setTitulo(String titulo){ this.titulo= titulo; }
    //Si se ingresa una variable de tipo LocalDate para fechaInicio
    public void setFechaInicio(LocalDate fechaInicio){ this.fechaInicio= fechaInicio; }
    //Si se ingresa una variable de tipo String para fechaInicio
    public void setFechaInicio(String fechaInicio){ 
        this.fechaInicio = LocalDate.parse(fechaInicio);
    }
    //Si se ingresa una variable de tipo LocalDate para fechaTermino
    public void setFechaTermino(LocalDate fechaTermino){ this.fechaTermino= fechaTermino; }
    //Si se ingresa una variable de tipo String para fechaTermino
    public void setFechaTermino(String fechaTermino){ 
        this.fechaTermino = LocalDate.parse(fechaTermino);
    }
    public void setListaObras(ArrayList<Obra> nuevaL){ listaObras= nuevaL; }
    //Añadir obra a la lista
    public void anadirObra(Obra obra){
        //Si la obra ya se encuentra en la exposicion, se da un aviso y se retorna
        if ( listaObras.contains(obra) == true){
            System.out.println("La obra ya se encuentra en la exposición.");
            return;
        }
        listaObras.add(obra);
        System.out.printf("La obra '%s' ha sido añadida exitosamente.\n", obra.getTitulo());
    }
    //Eliminar obra de la lista
    public void eliminarObra(Obra obra){
        //Si la obra NO se encuentra en la exposicion, se da un aviso y se retorna
        if ( listaObras.contains(obra) == false){
            System.out.println("La obra NO se encuentra en la exposición.");
            return;
        }
        listaObras.remove(obra);
        System.out.printf("La obra '%s' ha sido eliminada exitosamente.\n", obra.getTitulo());
    }
    //Vaciar lista 
    public void vaciarLista(){
        if ( listaObras.isEmpty() == true ){
            System.out.println("La lista de obras ya se encuentra vacía. ");
            return;
        }
        listaObras.clear(); 
        System.out.println("Se ha vaciado la lista de obras exitosamente.");
    }
    //Buscar una obra (saber si la obra se encuentra en la lista)   
    public boolean obraEstaEnLista(Obra obra){
        if ( listaObras.contains(obra) == true) return true;
        else return false;
    }
    //Mostrar atributos
    public void mostrarAtributos(){
        System.out.printf("= ATRIBUTOS DE LA EXPOSICION '%s' =\n", titulo);
        System.out.println("ID: " + id);
        System.out.println("FECHA DE INICIO: " + fechaInicio);
        System.out.println("FECHA DE TERMINO: " + fechaTermino);
        //Mostrar obras
        System.out.println("OBRAS: ");
        if ( listaObras.isEmpty() == true ){
            System.out.println("%s NO contiene obras.");
            return;
        }
        for (int i = 0 ; i < listaObras.size() ; i++){
            System.out.printf("%d. TITULO OBRA: '%s' | ARTISTA: %s \n", i+1, listaObras.get(i).getTitulo(), listaObras.get(i).getArtista());
        }
    }
}
