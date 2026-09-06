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
/*
 *******************************************************************************
 CUANDO SE HAGA LA FUNCIÓN DE CREAR UNA SUBASTA:
    antes de crear el objeto Subasta con el id de la obra vinculada a la subasta,
    verificar que el id de la obra a vincular esté en el mapa de obras, y que la obra esté DISPONIBLE, y entregarle el objeto obra al constructor de Subasta
*/

public class Subasta extends Venta {
    private int precioInicial;
    private Oferta mejorOferta;

//public Venta(LocalDate fechaVenta, Cliente cliente, Obra obraVendida, int precio){
    //crear subasta con fecha tipo LocalDate
    public Subasta(Obra obra, int precioInicial, LocalDate fechaVenta){
        super(fechaVenta, null, obra, precioInicial);
        this.precioInicial = precioInicial;
        mejorOferta = null;
    }
    public int getPrecioInicial() { return precioInicial; }
    public Oferta getMejorOferta() { return mejorOferta; }


    public void ofertar(int monto, String rut){
        if (mejorOferta == null || monto > (mejorOferta.getOferta()) ){
            if ( mejorOferta == null && monto < precioInicial){
                System.out.println("La primera oferta debe ser mayor o igualal precio inicial");
                return;
            }
            Oferta nueva = new Oferta(monto,rut);
            mejorOferta = nueva;
            System.out.println("Nueva mayor oferta:" + monto + "por Cliente: RUT " +rut);
        } else {
            System.out.println("Error. ingresar monto mayor a la oferta actual");
        }
    }




    //public Venta(LocalDate fechaVenta, Cliente cliente, Obra obraVendida, int precio){
    public boolean cerrarSubasta(HashMap<String, Cliente> clientes){
        if(mejorOferta != null){ 
            String rutGanador = mejorOferta.getRut();
            
            Cliente clienteGanador = clientes.get(rutGanador);
            if (clienteGanador == null){
                clienteGanador = new Cliente(rutGanador);
                clientes.put(rutGanador, clienteGanador); 
            } 

            this.setCliente(clienteGanador); 
            this.setPrecio(mejorOferta.getOferta()); 

            super.registrar(); // Cambia el estado y agrega al cliente
            
            System.out.println("Subasta cerrada exitosamente.");
            return true; // Si la subasta se cerró exitosamente, se reotrna true
            
        } else {
            System.out.println("La subasta se cerró sin ninguna oferta.");
            return false; //Si la subasta no tuvo ninguna oferta, se retorna False
        }
    }
}
