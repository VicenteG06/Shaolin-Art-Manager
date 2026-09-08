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

public class Cliente {
    private String rut; //rut sin guion
    private ArrayList<Obra> listaCompras;
    private ArrayList<Obra> listaPrestamos;
    //Constructor
    public Cliente(String rut){
        this.rut= rut; //rut sin guion
        listaCompras = new ArrayList<>();
        listaPrestamos = new ArrayList<>();
    }
    //Metodos GET 
    public String getRut(){ return rut; }
    //Metodos SET 
    public void setRut(String rut){ this.rut= rut; }
    //Metodo ver lista de obras compradas
    public void mostrarCompras() throws IOException{
        if (listaCompras.isEmpty()){
            System.out.println("El cliente no ha comprado ninguna obra.");
            return;
        }
        System.out.println("= OBRAS COMPRADAS =");
        for (int i = 0 ; i < listaCompras.size() ; i++){
            System.out.print("Obra Comprada: " + (listaCompras.get(i)).getTitulo());
            System.out.println(" | Artista: " + ((listaCompras.get(i)).getArtista()).getNombre());
        }
        PresioneTeclaParaContinuar.ptpc();
    }
    //Metodo ver lista de obras prestadas
    public void mostrarPrestamos() throws IOException{
        if (listaPrestamos.isEmpty()){
            System.out.println("Al cliente no se le ha prestado ninguna obra.");
            return;
        }
        System.out.println("= OBRAS PRESTADAS =");
        for (int i = 0 ; i < listaPrestamos.size() ; i++){
            System.out.print("Obra Prestada: " + (listaPrestamos.get(i)).getTitulo());
            System.out.println(" | Artista: " + ((listaPrestamos.get(i)).getArtista()).getNombre());
        }
        PresioneTeclaParaContinuar.ptpc();
    }
    //Mostrar Atributos
    public void mostrarAtributos(){
        System.out.println("= ATRIBUTOS DEL CLIENTE =");
        System.out.println("RUT: " + rut);
        System.out.println("OBRAS COMPRADAS: " + listaCompras.size());
        System.out.println("OBRAS PRESTADAS: " + listaPrestamos.size());
    }
    //Metodo agregar obra a lista de compras 
    public boolean agregarCompra(Obra o){
        if (listaCompras.contains(o)) return false ; 
        o.setEstado("VENDIDA"); //se cambia el estado de la obra a vendida
        listaCompras.add(o);
        return true;
    }
    //Metodo agregar obra a lista de prestamos
    public boolean agregarPrestamo(Obra o){
        if (listaPrestamos.contains(o)) return false ; 
        o.setEstado("PRESTADA"); //se cambia el estado de la obra a prestada
        listaPrestamos.add(o);
        return true;
    }
    //Metodo borrar obra de lista de compras 
    public boolean elimObComprada(Obra o){ 
        if (!listaCompras.contains(o)) return false;
        o.setEstado("DISPONIBLE"); //se cambia el estado de la obra a disponible
        listaCompras.remove(o);
        return true;
    }
    //Metodo borrar obra de lista de prestamos
    public boolean elimObPrestada(Obra o){ 
        if (!listaPrestamos.contains(o)) return false;
        o.setEstado("DISPONIBLE"); //se cambia el estado de la obra a disponible
        listaPrestamos.remove(o);
        return true;
    }
}
