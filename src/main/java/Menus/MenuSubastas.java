/**
 * @archivo: MenuSubastas.java
 * @Proyecto: Shaolin Art Manager 
 * @Descripción: Clase para manejar el menú de Subastas
 * @author Antonia Avello
 * @Lenguaje: Java
*/

import java.io.*;
import java.util.*;
import java.time.*;

public class MenuSubastas {
     public static void mostrarMenuSubastas(){
        System.out.println("========================");
        System.out.println("           Subastas");
        System.out.println("========================");
        System.out.println("1) Mostrar Subastas Cerradas");
        System.out.println("2) Iniciar Nueva Subasta");
        System.out.println("3) Registrar Nueva Oferta en la Subasta Activa");
        System.out.println("4) Cerrar Subasta Activa");
        System.out.println("5) Salir del Menú");
    }
    public static void mostrarSubastasCerradas(ArrayList<Subasta> subastas){
        if (subastas.isEmpty()){
            System.out.println("No hay subastas finalizadas");
        } else {
            System.out.println("-> HISTORIAL DE SUBASTAS:");
            Subasta auxS;
            for (int i = 0 ; i < subastas.size() ; i++){
                auxS= (Subasta) subastas.get(i);
                auxS.mostrarAtributos();
            }
        }
    }
    public static void iniciarNuevaSubasta(ArrayList<Subasta> subastas, HashMap<String, Obra> obras) throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        if (MenuPrincipal.subastaActiva != null) { //Si hay una subastaActiva, no se abre una nueva hasta que la activa se cierre
            System.out.println("Ya hay una subasta en curso.");
            return;
        }
                
        System.out.println("Ingrese el ID de la Obra a subastar:");

        String idObra = lector.readLine(); 
        if(!obras.containsKey(idObra) ){ //Se verifica que la obra esté registrada
            System.out.println("No existe esa obra .");
            return;
        }
                
        Obra o = obras.get(idObra); //REVISAR SI LA OBRA TIENE ESTADO "DISPONIBLE"
        if (!o.getEstado().equals("Disponible")) { 
            System.out.println("La obra no está disponible para ser subastada.");
            return;
        }
     
        int precioInicial = Validaciones.pedirEnteroPositivo(lector, "Ingrese el precio inicial de la obra:"); //Se pide el método a Validaciones para ingresar un precio inicial válido
                
        //se pide el método a Validaciones para ingresar una fecha de la subasta válida
        LocalDate fSubastaObj = Validaciones.pedirFecha(lector, "Ingrese la fecha de la subasta (formato AAAA-MM-DD):");
        
        // INICIALIZAR VARIABLE GLOABL DE MENU 
        MenuPrincipal.subastaActiva = new Subasta(o, precioInicial, fSubastaObj);
        System.out.println("Subasta iniciada con éxito");
    }

    public static void registrarNuevaOferta(ArrayList<Subasta> subastas) throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        if (MenuPrincipal.subastaActiva == null) { //Si no hay una subasta activa, no se puede registrar una oferta
            System.out.println("No hay ninguna subasta activa en este momento.");
            return;
        }     
        String rutOfertador = Validaciones.pedirRut(lector, "Ingrese el RUT del cliente que oferta (Sin guión, incluyendo dígito verificador) o '0' para cancelar:");
        if (rutOfertador.equals("0")) return; //0 para retornar al menú y no quedar en loop inifinito
        
        //--------------------------------------------------------------- se hacen las validaciones correspondientes para añadir la oferta
        boolean ofertaValida = false; 
        int monto = 0;
        
        while (!ofertaValida) { // mientras no haya una oferta válida

            monto = Validaciones.pedirEnteroPositivo(lector, "Ingrese el monto de la oferta:");
            
            Oferta ofertaActual = MenuPrincipal.subastaActiva.getMejorOferta(); //se obtiene la mejor oferta
            int precioInicial = MenuPrincipal.subastaActiva.getPrecioInicial(); // se obtiene el precio inicial 
            //si es la primera oferta
            if (ofertaActual == null) { 
                if (monto < precioInicial) { //Se verifica que la oferta sea mayor al precio inicial
                    System.out.println("Error: La primera oferta debe ser mayor o igual al precio inicial ($" + precioInicial + ").");
                } else {
                    ofertaValida = true; //si lo es, se cambia el estado de Oferta Valida
                }
            } 
            // Si ya existen ofertas
            else {

                if (monto <= ofertaActual.getOferta()) {  //se valida que se ingrese una oferta mayor a la mejor oferta actual
                    System.out.println("Error: Debe ingresar un monto mayor a la oferta actual ($" + ofertaActual.getOferta() + ")");

                } else {
                    ofertaValida = true;
                }
            }
        }
        
        // si sale del ciclo, la oferta es valida y podemos añadirla
        MenuPrincipal.subastaActiva.ofertar(monto, rutOfertador);
        System.out.println("Nueva mayor oferta: $" + monto + " por Cliente RUT: " + rutOfertador);


    }

    public static void cerrarSubastaActiva(ArrayList<Subasta> subastas, HashMap<String, Cliente> clientes){
        if (MenuPrincipal.subastaActiva == null) { //verificar que haya una subasta activa
            System.out.println("No hay ninguna subasta activa para cerrar.");
            return;
        }
        System.out.println("Cerrando subasta...");
        boolean exito = MenuPrincipal.subastaActiva.cerrarSubasta(clientes); 
                
        if (exito) {//Si se logró cerrar la subasta con éxito (con al menos una oferta registrada)
            subastas.add(MenuPrincipal.subastaActiva); //se añade la subasta al ArrayList de subastas cerradas
        }
        // Reiniciamos la variable de subastaActiva
        MenuPrincipal.subastaActiva = null;
    }
    
    public static void menuSubastas(ArrayList<Subasta> subastas, HashMap<String, Obra> obras, HashMap<String, Cliente> clientes) throws IOException,EmptyEntryException{
        char opcion = ' ';
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        do{
            MenuSubastas.mostrarMenuSubastas();
            try{
                String entrada = lector.readLine();
                if(entrada == null || entrada.trim().isEmpty()){
                    throw new EmptyEntryException();
                }
                opcion = entrada.charAt(0);
                switch(opcion){
                case '1': //MOSTRAR SUBASTAS CERRADAS
                    MenuSubastas.mostrarSubastasCerradas(subastas);
                    break; 
                case '2': // iniciar subasta
                    MenuSubastas.iniciarNuevaSubasta(subastas, obras);
                    break;
                case '3': // ofertar
                    MenuSubastas.registrarNuevaOferta(subastas);
                    break;
                case '4': // CERRAR SUBASTA
                    MenuSubastas.cerrarSubastaActiva(subastas, clientes); 
                    break;
                case '5':
                    System.out.println("Saliendo del menú......");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
                    break;
                }
            } catch (EmptyEntryException e) {
                System.out.println(e.getMessage() + "\n");
            } catch (IOException e) {
                System.out.println(e.getMessage());
                break;
            }
        } while(opcion != '5');
    }
}