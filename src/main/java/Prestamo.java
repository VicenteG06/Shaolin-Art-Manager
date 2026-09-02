/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vicente
 */

import java.time.*;

public class Prestamo extends Transaccion {
    private String id;
    private LocalDate fechaInicio;
    private LocalDate fechaRetorno;

    public Prestamo(){
        super();
        this.id = "No se ha ingresado ID";
        this.fechaInicio = null;
        this.fechaRetorno = null;
    }
    //Antes de crear el objeto PRESTAMO, se debe validar que la obra no esté vendida o prestada
    public Prestamo(String id, Cliente cliente, Obra obra, LocalDate fechaInicio, LocalDate fechaRetorno){
        super(cliente,obra);
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaRetorno = fechaRetorno;
    }
    @Override
    public void registrar() {
        obra.setEstado("PRESTADA");
        cliente.getListaPrestamos().add(obra);// Usa el getter exacto de tu Cliente
        System.out.println("Préstamo registrado con éxito");
    }
    // Métodos get
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaRetorno() { return fechaRetorno; }
    public String getId() { return id; }
    
    // Métodos set
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio;}
    public void setFechaRetorno(LocalDate fechaRetorno) { this.fechaRetorno = fechaRetorno;}
    
    //Mostrar Atributos del prestamo
    public void mostrarAtributos(){
        System.out.println("= ATRIBUTOS DEL PRÉSTAMO =");
        System.out.println("ID: " + id);
        System.out.println("CLIENTE: " + cliente.getRut());
        System.out.print("OBRA PRESTADA: " + obra.getTitulo() );
        System.out.println(" | ARTISTA: " + obra.getArtista().getNombre() );
        System.out.println("FECHA DE INICIO (AAAA/MM/DD): " + fechaInicio);
        System.out.println("FECHA DE RETORNO (AAAA/MM/DD): " + fechaRetorno);
    }
}
