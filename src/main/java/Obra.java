/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ale
 */

import java.io.*;
import java.util.*;

public class Obra {
    //Atributos Obra
    private int id;
    private String titulo;
    private Artista artista;
    private String estado; 
    private int anio; 
    //Constructor 
    public Obra(int id, String titulo, Artista artista, String estado, 
                int anio){
        this.id= id;
        this.titulo= titulo;
        this.artista= artista;
        this.estado= estado;
        this.anio= anio;
    }
    //Metodos GET
    public int getId(){ return id; }
    public String getTitulo(){ return titulo; }
    public Artista getArtista(){ return artista; }
    public String getEstado(){ return estado; }
    public int getAnio(){ return anio; }
    //Metodos SET
    public void setId(int id){ this.id= id; }
    public void setTitulo(String titulo){ this.titulo= titulo; }
    public void setArtista(Artista artista){ this.artista= artista; }
    public void setEstado(String estado){ this.estado= estado; }
    public void setAnio(int anio){ 
        if (anio < 0){
            System.out.println("El año ingresado no es válido, inténtelo de nuevo.");
            return;
        }
        this.anio= anio;
    }
    //Mostrar Atributos de la obra
    public void mostrarAtributos(){
        System.out.println("= ATRIBUTOS DE LA OBRA =");
        System.out.println("ID: " + id);
        System.out.println("TITULO: " + titulo);
        System.out.println("ARTISTA: " + artista.getNombre());
        System.out.println("ESTADO: " + estado);
        System.out.println("AÑO: " + anio);
    }

    public static void mostrarObras(HashMap<String, Obra> obras){
        for(Obra o: obras.values()){
            o.mostrarAtributos();
        }
    }

    public static void buscarObra(HashMap<String, Obra> obras) throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String idObra = lector.readLine();
        if(obras.containsKey(idObra)){
            Obra o = obras.get(idObra);
            o.mostrarAtributos();
        }
        else System.out.println("No existe esa obra");
    }
}