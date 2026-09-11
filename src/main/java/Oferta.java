/**
 * @archivo: Oferta.java
 * @Proyecto: Shaolin Art Manager 
 * @Descripción: Clase para declarar los atributos y métodos del objeto Oferta
 * @author Antonia Avello
 * @Lenguaje: Java
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
