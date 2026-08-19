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

public class Obra {
    private String id;
    private String titulo;
    private Artista artista;
    private String estado; 
    private int anio; 
    //Constructor 
    public Obra(String id, String titulo, Artista artista, String estado, 
                int anio){
        this.id= id;
        this.titulo= titulo;
        this.artista= artista;
        this.anio= anio;
        this.estado= estado;
    }
    //Metodos GET
    public String getId(){ return id }
    public String getTitulo(){ return titulo }
    public Artista getArtista(){ return artista }
    public String getEstado(){ return estado }
    public int getAnio(){ return anio }
    //Metodos SET
    public void setId(String id){ this.id= id }
    public void setTitulo(String titulo){ this.titulo= titulo }
    public void setArtista(Artista artista){ this.artista= artista }
    public void setEstado(String estado){ this.estado= estado }
    public void setAnio(int anio){ 
        if (anio < 0){
            System.out.printl("El año ingresado no es válido, inténtelo de nuevo.");
            return;
        }
        this.anio= anio;
    }
    //Otros Metodos 
    //Mostrar Atributos de la obra
    public void mostrarAtributos(){
        System.out.printl("= ATRIBUTOS DE LA OBRA =");
        System.out.printl("ID: " + id);
        System.out.printl("TITULO: " + titulo);
        System.out.printl("ARTISTA: " + artista.getNombre);
        System.out.printl("ESTADO: " + estado);
        System.out.printl("AÑO: " + anio);
    }
}