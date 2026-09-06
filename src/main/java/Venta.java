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

public class Venta extends Transacci {
    private LocalDate fechaVenta;
    private int precio;
    public Venta(){
        super();
        this.fechaVenta = null;
        this.precio = 0;
    }
    
    // Antes de crear el objeto VENTA, se debe validar que la obra a vender esté "DISPONIBLE" en el menú
    public Venta(LocalDate fechaVenta, Cliente cliente, Obra obra, int precio){
        super(cliente, obra);
        this.fechaVenta = fechaVenta;
        this.precio = precio;
        // La obra cambia de estado dentro de registrar()
    }
    
    //Constructor que recibe la fecha como String y la convierte en LocalDate
    public Venta(String fechaVenta, Cliente cliente, Obra obra, int precio){
        super(cliente, obra);
        this.fechaVenta = LocalDate.parse(fechaVenta);
        this.precio = precio;
        // La obra cambia de estado dentro de registrar()
    }

    @Override
    public void registrar() {
        if (!cliente.agregarCompra(obra)){
            System.out.println("Hubo un error al registrar la compra de la obra");
            return;
        }
        System.out.println("Venta registrada con éxito.");
    }
    
    // Métodos get
    public LocalDate getFechaVenta(){return fechaVenta; }
    public int getPrecio(){return precio;}
    
    // Métodos set
    public void setFechaVenta(LocalDate fechaVenta){ this.fechaVenta = fechaVenta;}
    public void setFechaVenta(String fechaVenta){ 
        this.fechaVenta = LocalDate.parse(fechaVenta);
    }

    public void setPrecio(int precio){ 
        if (precio < 0){
            System.out.println("El precio ingresado no es válido.");
            return;
        }
        this.precio = precio;
    }

    // Mostrar los atributos de la venta
    public void mostrarAtributos(){
        System.out.println("= DETALLES DE LA VENTA =");
        if (cliente != null) {
            System.out.println("CLIENTE COMPRADOR RUT: " + cliente.getRut());
        } else {
            System.out.println("CLIENTE COMPRADOR RUT: Ninguno");
        }
        
        System.out.print("OBRA VENDIDA: " + obra.getTitulo());
        System.out.println(" | ARTISTA: " + obra.getArtista().getNombre());
        System.out.println("PRECIO DE VENTA: $" + precio);
        System.out.println("FECHA DE VENTA (AAAA-MM-DD): " + fechaVenta);
        System.out.println("-------------------------");
    }
}