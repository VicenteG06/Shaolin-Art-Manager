/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vicente
 */

import java.time.LocalDate;

public class Prestamo {
    private String id;
    private Cliente cliente;
    private Obra obraPrestada;
    private LocalDate fechaInicio;
    private LocalDate fechaRetorno;

    public Prestamo(){
        id = "No se ha ingresado ID";
        cliente = null;
        obraPrestada = null;
        fechaInicio = null;
        fechaRetorno = null;
    }
    public Prestamo(String id, Cliente cliente, Obra obraPrestada, LocalDate fechaInicio, localDate fechaRetorno){
        this.id = id;
        this.cliente = cliente;
        this.obraPrestada = obraPrestada;
        this.fechaInicio = fechaInicio;
        this.fechaRetorno = fechaRetorno;
    }
    
    // Métodos get
    public LocalDate getFechaInicio() { return fechaInicio }
    public LocalDate getFechaRetorno() { return fechaRetorno }
    public Obra getObraPrestada() { return obraPrestada }
    public String getId() { return id }
    
    // Métodos set
    public void setObraPrestada(Obra obraPrestada) { this.obraPrestada = obraPrestada }
    public void setCliente(Cliente cliente) { this.cliente = cliente }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio}
    public void setFechaRetorno(LocalDate fechaRetorno) { this.fechaRetorno = fechaRetorno}
}
