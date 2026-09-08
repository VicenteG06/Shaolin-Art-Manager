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
        super(cliente, obra);
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaRetorno = fechaRetorno;
        // La obra cambia de estado dentro de registrar()
    }
    //Constructor que recibe la fecha como String y la convierte en LocalDate
    public Prestamo(String id, Cliente cliente, Obra obra, String fechaInicio, String fechaRetorno){
        super(cliente, obra);
        this.id = id;
        this.fechaInicio = LocalDate.parse(fechaInicio);
        this.fechaRetorno = LocalDate.parse(fechaRetorno);        
        // La obra cambia de estado dentro de registrar()
    }
    
    @Override
    public void registrar() {
        //getCliente y getObra por cambio de variables en clase padre a private
        if (!getCliente().agregarPrestamo(getObra())){
            System.out.println("Hubo un error al registrar el préstamo de la obra");
            return;
        }
        System.out.println("Préstamo registrado con éxito.");
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
        // getCliente y getObra por cambio de variables en clase padre a private
        System.out.println("CLIENTE: " + getCliente().getRut());
        System.out.print("OBRA PRESTADA: " + getObra().getTitulo() );
        System.out.println(" | ARTISTA: " + getObra().getArtista().getNombre() );
        System.out.println("FECHA DE INICIO (AAAA/MM/DD): " + fechaInicio);
        System.out.println("FECHA DE RETORNO (AAAA/MM/DD): " + fechaRetorno);
    }
}