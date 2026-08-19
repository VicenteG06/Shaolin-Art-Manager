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
    private String nombre;
    private String autor;
    private String estado; 
    private int anio;
    private int precio; 
    
    //Constructor 
    public Obra(){
        id= "000000";
        nombre= "Sin Nombre";
        autor= "Sin Autor";
        anio= 0;
        estado= "Sin Estado"; 
        precio= 0;
    }
    public Obra(String id, String nombre, String autor, String estado, int anio, 
                int precio){
        this.id= id;
        this.nombre= nombre;
        this.autor= autor;
        this.anio= anio;
        this.estado= estado; 
        this.precio= precio;
    }
    //Metodos GET
    public String getId(){ return id }
    public String getNombre(){ return nombre }
    public String getAutor(){ return autor }
    public String getEstado(){ return estado }
    public int getAnio(){ return anio }
    public int getPrecio(){ return precio }
    //Metodos SET
    public void setId(String id){ this.id= id }
    public void setNombre(String nombre){ this.nombre= nombre }
    public void setAutor(String autor){ this.autor= autor }
    public void setEstado(String estado){ this.estado= estado }
    public void setAnio(int anio){ this.anio= anio }
    public void setPrecio(int precio){ this.precio= precio }
}
