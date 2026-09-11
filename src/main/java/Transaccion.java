/**
 * @archivo: Transaccion.java
 * @Proyecto: Shaolin Art Manager 
 * @Descripción: Clase para declarar los atributos y métodos del objeto Transaccion
 * @author Antonia Avello
 * @Lenguaje: Java
*/

public class Transaccion {
    //cambio de variables protected a private
    private Cliente cliente;
    private Obra obra; 
    
    public Transaccion() {
        this.cliente = null;
        this.obra = null;
    }
    
    public Transaccion(Cliente cliente, Obra obra){
        this.cliente = cliente;
        this.obra = obra;
    }
    
    public boolean registrar() {
        System.out.println("Registrando transacción");
        return true;
    }
    
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente= cliente; }
    public Obra getObra() { return obra; }
    public void setObra(Obra obra) { this.obra= obra; }
}