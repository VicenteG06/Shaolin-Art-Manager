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

public class Exposicion {
    private String id;
    private String titulo;
    private String fechaInicio;
    private String fechaTermino;
    private ArrayList<Obra> obras;
    //Constructores
    public Exposicion(){
        id= "000000";
        titulo= "Sin Titulo";
        fechaInicio= "Sin F Inicio";
        fechaTermino= "Sin F Final";
        obras = new ArrayList<>();
    }
    public Exposicion(String id, String titulo, String fechaInicio,
                      String fechaTermino, Obra obra){
        this.id= id;
        this.titulo= titulo;
        this.fechaInicio= fechaInicio;
        this.fechaTermino= fechaTermino;
        //Se añade la primera obra 
        this.obras.add(obra);
    }
    //Metodos GET - menos lista de obras
    public String getId(){ return id }
    public String getTitulo(){ return titulo }
    public String getfechaInicio(){ return fechaInicio }
    public String getfechaTermino(){ return fechaTermino }
    //Metodos SET - menos lista de obras
    public void setId(String id){ this.id= id }
    public void setTitulo(String titulo){ this.titulo= titulo }
    public void setFechaInicio(String fechaInicio){ this.fechaInicio= fechaInicio }
    public void setFechaTermino(String fechaTermino){ this.fechaTermino= fechaTermino }
    //Metodos para Lista de Obras
}
