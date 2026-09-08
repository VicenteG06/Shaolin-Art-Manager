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

public class MenuPrestamos {
    // menu prestamos
    public static void mostrarMenuPrestamos(){
        System.out.println("========================");
        System.out.println("           Préstamos");
        System.out.println("========================");
        System.out.println("1) Mostrar Préstamos");
        System.out.println("2) Buscar Préstamos");
        System.out.println("3) Registrar Préstamo");
        System.out.println("4) Eliminar Préstamo");
        System.out.println("5) Salir del Menú");
    }
    public static void mostrarPrestamos(ArrayList<Prestamo> prestamos){
        //si no hay préstamos registrados, se da un aviso y se retorna al menu 
        if (prestamos.size() == 0) {
            System.out.println("No hay préstamos registrados.");
            return;
        }
        System.out.println("-> PRÉSTAMOS:"); //está como medio feo. Arreglar
        Prestamo auxP;
        for (int i = 0 ; i < prestamos.size() ; i++){
            auxP= prestamos.get(i);
            auxP.mostrarAtributos();
        }
    }
    public static void buscarPrestamos(ArrayList<Prestamo> prestamos, HashMap<String, Obra> obras) throws IOException
{
        //si no hay prestamos, se da un aviso y se retorna al menu
        if (prestamos.size() == 0){
            System.out.println("No hay préstamos registrados.");
            return;
        }
        //se busca la obra prestada. De no existir, se da un aviso y se retorna al menu
        System.out.println("Ingrese el ID de la Obra prestada:"); 
        BufferedReader l2 = new BufferedReader(new InputStreamReader(System.in));
        String idObra2 = l2.readLine();
        if(!obras.containsKey(idObra2)){
            System.out.println("No existe esa obra");
            return;
        }
        //al obtener la obra, si no esta prestada se da un aviso y se retorna al menu
        Obra o2 = obras.get(idObra2); 
        if ( !(o2.getEstado()).equals("PRESTADA") ){
            System.out.printf("La obra '%s' no ha sido prestada.\n", o2.getTitulo());
            return;
        }
        //como se sabe que esta prestada, se busca y muestra
        Prestamo auxP2; 
        for (int i = 0 ; i < prestamos.size() ; i++ ){
            auxP2 = (Prestamo) prestamos.get(i);
            if (auxP2.getObra() == o2){
                auxP2.mostrarAtributos();
                return;
            }
        }
        //si por alguna razón no se encontró, se da aviso 
        System.out.printf("'%s' sale prestada pero no se encontró en lista préstamos.\n", o2.getTitulo());
    }
    public static void registrarPrestamo(ArrayList<Prestamo> prestamos, HashMap<String, Cliente> clientes, HashMap<String, Obra> obras) throws IOException{
        //se busca la obra que se quiere pedir prestada. De no existir, se da un aviso y se retorna al menu
        System.out.println("Ingrese el ID de la Obra que se desea pedir prestada:"); 
        BufferedReader l = new BufferedReader(new InputStreamReader(System.in));
        String idObra = l.readLine();
        if(!obras.containsKey(idObra)){
            System.out.println("No existe esa obra");
            return;
        }
        //al obtener la obra, se verifica que esta obra no este ya vendida ni prestada 
        Obra o = obras.get(idObra); 
        if ( (o.getEstado()).equals("VENDIDA") ){
            System.out.printf("La obra '%s' está vendida, por lo que no se puede prestar.\n", o.getTitulo());
            return;
        }
        else if ( (o.getEstado()).equals("PRESTADA") ){
            System.out.printf("La obra '%s' ya está actualmente prestada.\n", o.getTitulo());
            return;
        }
        //como está disponible, se puede prestar 
        

        String mensajeRut = "Ingrese el rut SIN GUIÓN Y CON DÍGITO VERIFICADOR del cliente que desea pedir prestado '" + o.getTitulo() + "':\n(Ingrese '0' y enter para cancelar la operación)";
        String rutC = Validaciones.pedirRut(l, mensajeRut);
        if (rutC.equals("0")) return;
                
        Cliente c; //CREAR CLIENTE
        if (!clientes.containsKey(rutC)){
            System.out.printf("El cliente de rut %s no se encuentra registrado. Creando nuevo cliente...\n", rutC);
            c = new Cliente(rutC);
            clientes.put(rutC, c); // Se añade al mapa de clientes
            System.out.println("¡Cliente nuevo registrado en el sistema con éxito!");
        } else {
            c = clientes.get(rutC);
            System.out.println("Cliente encontrado en el sistema.");
        }

        LocalDate fInicioObj = Validaciones.pedirFecha(l, "Ingrese la fecha de INICIO del préstamo (formato: AAAA-MM-DD):");
        
        LocalDate fRetornoObj = null;
        while (fRetornoObj == null) {
            fRetornoObj = Validaciones.pedirFecha(l, "Ingrese la fecha de RETORNO del préstamo (no debe superar el AÑO, formato: AAAA-MM-DD) :");
            if (fRetornoObj.isBefore(fInicioObj)) {
                System.out.println("Error: La fecha de retorno no puede ser anterior a la fecha de inicio.");
                fRetornoObj = null; 
            } else if (fInicioObj.plusYears(1).isBefore(fRetornoObj)) {
                System.out.println("Error: el retorno debe ser como máximo un año después.");
                fRetornoObj = null;
            }
        }
        
        String nId= IDManager.generarID(rutC); // Se genera el ID
        Prestamo n = new Prestamo(nId, c, o, fInicioObj, fRetornoObj);
        //se ingresa la obra a la lista de prestamos del cliente en el mismo metodo de registro
        n.registrar();
        prestamos.add(n); // AGREGAR AL ARRAYLIST DE PRESTAMOS
    }
    public static void eliminarPrestamo(ArrayList<Prestamo> prestamos, HashMap<String, Obra> obras) throws IOException{
        //si no hay prestamos, se da un aviso y se retorna al menu
        if (prestamos.size() == 0){
            System.out.println("No hay préstamos registrados.");
            return;
        }
        //se busca la obra prestada. De no existir, se da un aviso y se retorna al menu
        System.out.println("Ingrese el ID de la Obra prestada:"); 
        BufferedReader l = new BufferedReader(new InputStreamReader(System.in));
        String idObra = l.readLine();
        if(!obras.containsKey(idObra)){
            System.out.println("No existe esa obra");
            return;
        }
        //al obtener la obra, si no esta prestada se da un aviso y se retorna al menu
        Obra o = obras.get(idObra); 
        if ( !(o.getEstado()).equals("PRESTADA") ){
            System.out.printf("La obra '%s' no ha sido prestada.\n", o.getTitulo());
            return;
        }
        //como se sabe que esta prestada, se busca, se elimina y se da un aviso de ello
        Prestamo auxP; 
        for (int i = 0 ; i < prestamos.size() ; i++ ){
            auxP = (Prestamo) prestamos.get(i);
            if (auxP.getObra() == o){
                Cliente cl = auxP.getCliente(); //se obtiene el cliente del prestamo 
                if (!cl.elimObPrestada(o)){ //se elimina la obra de la lista de obras prestadas del cliente 
                    System.out.printf("Hubo un problema al eliminar la obra de la lista de préstamos del cliente %s (el cliente no ha pedido prestada la obra)\n", cl.getRut());
                } 
                prestamos.remove(auxP); //se elimina el prestamo de la lista de prestamos 
                System.out.printf("El préstamo de %s ha sido eliminado con éxito.\n", o.getTitulo());
                o.setEstado("Disponible");
                return;
            }
        }
        System.out.println("No se pudo encontrar el préstamo.");
    }
    
    public static void menuPrestamos(ArrayList<Prestamo> prestamos, HashMap<String, Obra> obras, HashMap<String, Cliente> clientes) throws IOException, EmptyEntryException{
        char opcion = ' ';
        do{
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            MenuPrestamos.mostrarMenuPrestamos();
            try{
                String entrada = lector.readLine();
                if(entrada == null || entrada.trim().isEmpty()){
                    throw new EmptyEntryException();
                }
                opcion = entrada.charAt(0);

                switch(opcion){
                case '1': //mostrar 
                    MenuPrestamos.mostrarPrestamos(prestamos);
                    break;
                case '2': //Buscar por obra
                    MenuPrestamos.buscarPrestamos(prestamos, obras);
                    break;
                case '3': //registrar
                    MenuPrestamos.registrarPrestamo(prestamos, clientes, obras);
                    break;
                case '4': //eliminar 
                    MenuPrestamos.eliminarPrestamo(prestamos, obras);
                    break;
                case '5':
                    System.out.println("Saliendo del menú......");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
                    break;
                }
            } catch (EmptyEntryException e) {
                System.out.println("Error: " + e.getMessage() + "\n");
            } catch (IOException e) {
                System.out.println("Error de lectura: " + e.getMessage());
                break;
            }
        } while(opcion != '5');
    } 
}