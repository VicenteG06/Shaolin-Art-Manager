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
    public ArrayList<Obra> getListaCompras(){ return listaCompras; }
    public ArrayList<Obra> getListaPrestamos(){ return listaPrestamos; }
    //Metodos SET 
    public void setRut(String rut){ this.rut= rut; }
    public void setListaCompras(ArrayList<Obra> nuevaL){ listaCompras= nuevaL; }
    public void setListaPrestamos(ArrayList<Obra> nuevaL){ listaPrestamos= nuevaL; }
    //Metodo ver lista de obras compradas
    public void mostrarCompras(){
        for (int i = 0 ; i < listaCompras.size() ; i++){
            System.out.print("Obra Comprada: ");
            System.out.println(listaCompras.get(i).getTitulo());
        }
    }
    //Metodo ver lista de obras prestadas
    public void mostrarPrestamos(){
        for (int i = 0 ; i < listaPrestamos.size() ; i++){
            System.out.print("Obra Prestada: ");
            System.out.println(listaPrestamos.get(i).getTitulo());
        }
    }
    //Mostrar Atributos
    public void mostrarAtributos(){
        System.out.println("= ATRIBUTOS DEL CLIENTE =");
        System.out.println("RUT: " + rut);
        System.out.println("OBRAS COMPRADAS: " + listaCompras.size());
        System.out.println("OBRAS PRESTADAS: " + listaPrestamos.size());
    }
}
