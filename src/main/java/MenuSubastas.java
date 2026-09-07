/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vicente
 */

import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

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
        if (MenuPrincipal.subastaActiva != null) {
            System.out.println("Ya hay una subasta en curso.");
            return;
        }
                
        System.out.println("Ingrese el ID de la Obra a subastar:");

        String idObra = lector.readLine();
        if(!obras.containsKey(idObra) ){
            System.out.println("No existe esa obra .");
            return;
        }
                
        Obra o = obras.get(idObra); //REVISAR SI LA OBRA TIENE ESTADO "DISPONIBLE"
        if (!o.getEstado().equals("Disponible")) { 
            System.out.println("La obra no está disponible para ser subastada.");
            return;
        }
                
        System.out.println("Ingrese el precio inicial de la obra:");
        int precioInicial = Integer.parseInt(lector.readLine()); 
                
        System.out.println("Ingrese la fecha de la subasta (formato AAAA-MM-DD):");
        String fechaS = (lector.readLine());
                
        // INICIALIZAR VARIABLE GLOABL DE MENU 
        MenuPrincipal.subastaActiva = new Subasta(o, precioInicial, fechaS);
        System.out.println("Subasta iniciada con éxito");
    }
    public static void registrarNuevaOferta(ArrayList<Subasta> subastas) throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        if (MenuPrincipal.subastaActiva == null) {
            System.out.println("No hay ninguna subasta activa en este momento.");
            return;
        }     
        System.out.println("Ingrese el RUT del cliente que oferta (Sin guión, incluyendo dígito verificador):");
        String rutOfertador = lector.readLine();
                
        System.out.println("Ingrese el monto de la oferta:");
        int monto = Integer.parseInt(lector.readLine());
                
        MenuPrincipal.subastaActiva.ofertar(monto, rutOfertador);
    }
    public static void cerrarSubastaActiva(ArrayList<Subasta> subastas, HashMap<String, Cliente> clientes){
        if (MenuPrincipal.subastaActiva == null) { //verificar que haya una subasta activa
            System.out.println("No hay ninguna subasta activa para cerrar.");
            return;
        }
        System.out.println("Cerrando subasta...");
        boolean exito = MenuPrincipal.subastaActiva.cerrarSubasta(clientes);
                
        if (exito) {
            subastas.add(MenuPrincipal.subastaActiva);
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
