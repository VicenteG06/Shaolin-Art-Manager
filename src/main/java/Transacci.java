/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Netaxion
 */
public class Transacci {
    protected Cliente cliente;
    protected Obra obra; 
    
    public Transacci() {
        this.cliente = null;
        this.obra = null;
    }
    
    public Transacci(Cliente cliente, Obra obra){
        this.cliente = cliente;
        this.obra = obra;
    }
    
    public void registrar() {
        System.out.println("Registrando transacción");
    }
    
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente= cliente; }
    public Obra getObra() { return obra; }
    public void setObra(Obra obra) { this.obra= obra; }
}