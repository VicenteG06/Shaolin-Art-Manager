/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Netaxion
 */
public class Oferta {
    private String rutCliente;
    private int oferta;
    
    public Oferta(int oferta, String rut){
        this.oferta=oferta;
        rutCliente = rut;
    }
    
    //MÉTODOS GET Y SET
    public void setRut(String r){ rutCliente = r;}
    public String getRut(){ return rutCliente; }

    public void setOferta(int o){ oferta = o;}
    public int getOferta() {return oferta;}
}
