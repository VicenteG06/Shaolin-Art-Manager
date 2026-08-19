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
import java.date.*;

public class Venta {
    private LocalDate fechaVenta;
    private Cliente cliente;
    private Obra obraVendida; 
    private int precio;
    public Venta(){
        this.fechaVenta = null;
        this.cliente = null;
        this.obraVendida = null;
        this.precio = 0;
    }
    public Venta(LocalDate fechaVenta, Cliente cliente, Obra obraVendida, int precio){
        this.fechaVenta = fechaVenta;
        this.cliente = Cliente;
        this.obraVendida = obraVendida;
        this.precio = precio;
    }
    //Metodos get
    public LocalDate getFechaVenta(){return fechaVenta}
    public Cliente getCliente(){return cliente}
    public Obra getObraVendida(){return fechaVenta}
    public int getPrecio(){return precio}
    //Metodos set
    public void setFechaVenta(LocalDate fechaVenta){ this.fechaVenta = fechaVenta }
    public void setCliente(Cliente cliente){ this.cliente = cliente }
    public void setObraVendida(Obra obraVendida){ this.obraVendida = obraVendida }
    public void setPrecio(int precio){ this.precio = precio }
}
