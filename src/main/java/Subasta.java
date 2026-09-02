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

public class Subasta {
    private Obra obraASubastar;
    //private ArrayList<Cliente> listaClientes;
    //private ArrayList<Oferta> listaOfertas;
    private Oferta mejorOferta;
    private int precioInicial;
    private LocalDate fechaVenta;

    //crear subasta con fecha tipo LocalDate
    public Subasta(Obra obra, int precioInicial, LocalDate fechaVenta){
        this.precioInicial = precioInicial;
        mejorOferta = null;
        this.fechaVenta = fechaVenta;
        obraASubastar = obra;
    }
    
    //crear subasta con fecha String (se convierte a LocalDate)
    public Subasta(Obra obra, int precioInicial, String fechaVenta){
        this.precioInicial = precioInicial;
        mejorOferta = null;
        this.fechaVenta = LocalDate.parse(fechaVenta);
        obraASubastar = obra;
    }
    


    public void ofertar(int monto, String rut){
        if (mejorOferta == null || monto > (mejorOferta.getOferta()) ){
            Oferta nueva = new Oferta(monto,rut);
            mejorOferta = nueva;
            System.out.println("Nueva mayor oferta:" + monto + "por Cliente: RUT " + rut);
        } else {
            System.out.println("Error. ingresar monto mayor a la oferta actual");
        }
    }
    //public Venta(LocalDate fechaVenta, Cliente cliente, Obra obraVendida, int precio){
    public void cerrarSubasta(ArrayList<Venta> registroVentas, HashMap<String, Cliente> clientes){
        if(mejorOferta != null){ 
            String rutGanador = mejorOferta.getRut();
            
            Cliente clienteGanador = clientes.get(rutGanador);
            if (clienteGanador == null){
                clienteGanador = new Cliente(rutGanador);
                clientes.put(rutGanador, clienteGanador); 
            } 

            Venta nuevaVenta = new Venta(fechaVenta, clienteGanador, obraASubastar, mejorOferta.getOferta());
            nuevaVenta.registrar();
            registroVentas.add(nuevaVenta);
            
            System.out.println("Subasta cerrada exitosamente.");
            
        } else {
            System.out.println("La subasta se cerró sin ninguna oferta.");
        }
    }
}
