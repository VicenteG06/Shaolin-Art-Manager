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
    private ArrayList<Obra> compras;
    private ArrayList<Obra> prestamos;
    //Constructor
    public Cliente(String rut){
        this.rut= rut; //rut sin guion
        compras = new ArrayList<>();
        prestamos = new ArrayList<>();
    }
    //Metodo GET - solo rut
    public String getRut(){ return rut }
    //Metodo SET - solo rut 
    public void setRut(String rut){ this.rut= rut }
    //Metodo ver lista de obras compradas
    public void mostrarCompras(){
        for (int i = 0 ; i < compras.size() ; i++){
            System.out.print("Obra Comprada: ");
            System.out.println(compras[i].titulo);
        }
    }
    //Metodo ver lista de obras prestadas
    public void mostrarPrestamos(){
        for (int i = 0 ; i < prestamos.size() ; i++){
            System.out.print("Obra Prestada: ");
            System.out.println(compras[i].titulo);
        }
    }
}
