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
    private String id;
    private String titulo;
    private LocalDate fechaInicio;
    private LocalDate fechaTermino;
    private ArrayList<Obra> obras;
    //Constructores
    public Exposicion(){
        id= null;
        titulo= "Sin Titulo";
        fechaInicio= null;
        fechaTermino= null;
        obras = new ArrayList<>();
    }
    public Exposicion(String id, String titulo, LocalDate fechaInicio,
                      LocalDate fechaTermino, Obra obra){
        this.id = id;
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        //Se añade la primera obra 
        this.obras.add(obra);
    }
    public Exposicion(String id, String titulo, String fechaInicio,
                      String fechaTermino, Obra obra){
        this.id= id;
        this.titulo= titulo;
        this.fechaInicio = LocalDate.parse(fechaInicio);
        this.fechaTermino = LocalDate.parse(fechaTermino);
        //Se añade la primera obra 
        this.obras.add(obra);
    }
    //Metodos GET - menos lista de obras
    public String getId(){ return id; }
    public String getTitulo(){ return titulo; }
    public LocalDate getfechaInicio(){ return fechaInicio; }
    public LocalDate getfechaTermino(){ return fechaTermino; }
    //Metodos SET - menos lista de obras
    public void setId(String id){ this.id= id; }
    public void setTitulo(String titulo){ this.titulo= titulo; }
    public void setFechaInicio(LocalDate fechaInicio){ this.fechaInicio= fechaInicio; }
    public void setFechaInicio(String fechaInicio){ 
        this.fechaInicio = LocalDate.parse(fechaInicio);
    }
    public void setFechaTermino(LocalDate fechaTermino){ this.fechaTermino= fechaTermino; }
    public void setFechaTermino(String fechaTermino){ 
        this.fechaTermino = LocalDate.parse(fechaTermino);
    }
    //Metodos para Lista de Obras
}
