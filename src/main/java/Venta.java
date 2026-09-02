/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Netaxion
 */

import java.util.*;
import java.time.*;

public class Venta extends Transaccion {
    private LocalDate fechaVenta;
    private int precio;

    public Venta(){
        super();
        this.fechaVenta = null;
        this.precio = 0;
    }
    //Antes de crear el objeto VENTA, se debe validar que la obra a vender no esté vendida o prestada con getEstado()
    public Venta(LocalDate fechaVenta, Cliente cliente, Obra obra, int precio){
        super(cliente,obra);
        this.fechaVenta = fechaVenta;
        obra.setEstado("VENDIDO");
        this.precio = precio;
        
    }

    @Override
    public void registrar() {
        obra.setEstado("VENDIDO");
        cliente.getListaCompras().add(obra);
        System.out.println("Venta registrada con éxito");
    }
    //Metodos get
    public LocalDate getFechaVenta(){return fechaVenta;}
    public int getPrecio(){return precio;}
    //Metodos set
    public void setFechaVenta(LocalDate fechaVenta){ this.fechaVenta = fechaVenta; }
    public void setFechaVenta(String fechaVenta){ 
        this.fechaVenta = LocalDate.parse(fechaVenta);
    }

    public void setPrecio(int precio){ 
        if (precio < 0){
            System.out.println("El precio ingresado no es válido, inténtelo de nuevo.");
            return;
        }
        this.precio= precio;
    }
}
