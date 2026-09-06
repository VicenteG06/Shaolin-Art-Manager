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

public class Menu {
    public static void mostrarMenuPrincipal(){
        System.out.println("========================");
        System.out.println("Shaolin Art Manager");
        System.out.println("========================");
        System.out.println("1) Obras");
        System.out.println("2) Artistas");
        System.out.println("3) Ventas");
        System.out.println("4) Prestamos");
        System.out.println("5) Subastas");
        System.out.println("6) Exposiciones");
        System.out.println("7) Salir");
    }

    public static void menuPrincipal(HashMap<String, Obra> obras, HashMap<String, Artista> artistas, 
                       HashMap<String, Exposicion> exposiciones, ArrayList<Venta> ventas, HashMap<String, Cliente> clientes) throws IOException {
        char opcion;

        do{
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            Menu.mostrarMenuPrincipal();
            opcion = (lector.readLine()).charAt(0);
            switch(opcion){
            case '1': 
                Menu.menuObras(obras);
                break;
            case '2':
                Menu.menuArtistas(artistas);
                break;
            case '3':
                Menu.menuVentas(ventas, obras, clientes);
                break;
            case '4':
                break;
            case '5':
                break;
            case '6':
                Menu.menuExposiciones(exposiciones, obras);
                break;
            case '7':
                System.out.println("Saliendo del menú......");
                break;
            default:
                System.out.println("Opción no válida, intente nuevamente.");
            }
        } while(opcion != '7');
    }
    // menu exposiciones
    public static void mostrarMenuExposiciones(){
        System.out.println("========================");
        System.out.println("       Exposiciones");
        System.out.println("========================");
        System.out.println("1) Mostrar Exposiciones");
        System.out.println("2) Buscar Exposición");
        System.out.println("3) Registrar Exposición");
        System.out.println("4) Eliminar Exposición");
        System.out.println("5) Salir del Menú");
    }
    public static void menuExposiciones(HashMap<String, Exposicion> exposiciones, HashMap<String, Obra> obras) throws IOException{
        char opcion;

        do{
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            Menu.mostrarMenuExposiciones();
            opcion = (lector.readLine()).charAt(0);
            switch(opcion){
            case '1': 
                Exposicion.mostrarExposiciones(exposiciones);
                break;
            case '2':
                Exposicion.buscarExposicion(exposiciones);
                break;
            case '3':
                Exposicion.registrarExposicion(exposiciones, obras);
                break;
            case '4':
                break;
            case '5':
                System.out.println("Saliendo del menú......");
                break;
            default:
                System.out.println("Opción no válida, intente nuevamente.");
                break;
            }
        } while(opcion != '5');
    }
    // menu artistas
    public static void mostrarMenuArtistas(){
        System.out.println("========================");
        System.out.println("         Artistas");
        System.out.println("========================");
        System.out.println("1) Mostrar Artistas");
        System.out.println("2) Buscar Obras por Artista");
        System.out.println("3) Salir del Menú");
    }
    public static void menuArtistas(HashMap<String, Artista> artistas) throws IOException{ 
        char opcion;
        
        do{
            BufferedReader lector = new BufferedReader (new InputStreamReader(System.in));
            Menu.mostrarMenuArtistas();
            opcion = (lector.readLine()).charAt(0);
            
            switch(opcion){
            case '1':
                Artista.mostrarArtistas(artistas);
                break;
            case '2':
                Artista.buscarObrasArtista(artistas);
                break;
            case '3':
                System.out.println("Saliendo del menú......");
                break;
            default:
                System.out.println("Opción no válida, intente nuevamente.");
                break;
            }
        } while(opcion != '3');

    }
    // menu obras
    public static void mostrarMenuObras(){
        System.out.println("========================");
        System.out.println("           Obras");
        System.out.println("========================");
        System.out.println("1) Mostrar Obras");
        System.out.println("2) Buscar Obra");
        System.out.println("3) Registrar Obra");
        System.out.println("4) Salir del Menú");
    }
    public static void menuObras(HashMap<String, Obra> obras) throws IOException{
        char opcion;

        do{
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            Menu.mostrarMenuObras();
            opcion = (lector.readLine()).charAt(0);
            switch(opcion){
            case '1': 
                Obra.mostrarObras(obras);
                break;
            case '2':
                Obra.buscarObra(obras);
                break;
            case '3':
                // Obra.registrarObra(obras);
                break;
            case '4':
                System.out.println("Saliendo del menú......");
                break;
            default:
                System.out.println("Opción no válida, intente nuevamente.");
                break;
            }
        } while(opcion != '4');
    }
    // menu prestamos
    public static void mostrarMenuVentas(){
        System.out.println("========================");
        System.out.println("           Ventas");
        System.out.println("========================");
        System.out.println("1) Mostrar Ventas");
        System.out.println("2) Buscar Venta");
        System.out.println("3) Registrar Venta");
        System.out.println("4) Menu Subastas");
        System.out.println("5) Salir del Menú");
    }
    public static void menuVentas(ArrayList<Venta> ventas, HashMap<String, Obra> obras, HashMap<String, Cliente> clientes) throws IOException{
        char opcion;
        do{
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            Menu.mostrarMenuVentas();
            opcion = (lector.readLine()).charAt(0);
            switch(opcion){
            case '1': 
                //si no hay ventas, se da un aviso y se retorna al menu
                if (ventas.size() == 0){ 
                    System.out.println("No hay ventas registradas.");
                    break;
                }
                //como hay ventas, se recorre la lista y se muestran
                System.out.println("-> VENTAS:"); //está como medio feo. Arreglar
                Venta auxV;
                for (int i = 0 ; i < ventas.size() ; i++){
                    auxV= (Venta) ventas.get(i);
                    auxV.mostrarAtributos();
                }
                break;
            case '2': //BUSCAR VENTAS
                //si no hay ventas, se da un aviso y se retorna al menu
                if (ventas.size() == 0){
                    System.out.println("No hay ventas registradas.");
                    break;
                }
                //se busca la obra vendida. De no existir, se da un aviso y se retorna al menu
                System.out.println("Ingrese el nombre de la Obra vendida:"); 
                BufferedReader l = new BufferedReader(new InputStreamReader(System.in));
                String idObra = l.readLine();
                if(!obras.containsKey(idObra)){
                    System.out.println("No existe esa obra");
                    break;
                }
                //al obtener la obra, si no esta vendida se da un aviso y se retorna al menu
                Obra o = obras.get(idObra); 
                if ( !(o.getEstado()).equals("VENDIDA") ){
                    System.out.printf("La obra '%s' no ha sido vendida.\n", o.getTitulo());
                    break;
                }
                //como se sabe que esta vendida, se busca y muestra 
                Venta auxV2; 
                for (int i = 0 ; i < ventas.size() ; i++ ){
                    auxV2 = (Venta) ventas.get(i);
                    if (auxV2.getObra() == o){
                        auxV2.mostrarAtributos();
                        break;
                    }
                }
                //si por alguna razón no se encontró, se da aviso 
                System.out.printf("'%s' sale vendida pero no se encontró en lista ventas.\n", o.getTitulo());
                break;
            case '3': 
                //si el mapa de clientes no contiene clientes, se da un aviso y se retorna al menu
                if (clientes.isEmpty()) {
                    System.out.println("No se encuentra registrado ningún cliente. Para poder realizar una venta, el comprador debe estar PREVIAMENTE REGISTRADO.");
                    break;
                }
                //se busca la obra que se quiere comprar. De no existir, se da un aviso y se retorna al menu
                System.out.println("Ingrese el nombre de la Obra que se desea vender:"); 
                BufferedReader l2 = new BufferedReader(new InputStreamReader(System.in));
                String idObra2 = l2.readLine();
                if(!obras.containsKey(idObra2)){
                    System.out.println("No existe esa obra");
                    break;
                }
                //al obtener la obra, se verifica que esta obra no este ya vendida
                Obra o2 = obras.get(idObra2); 
                if ( (o2.getEstado()).equals("VENDIDA") ){
                    System.out.printf("La obra '%s' ya ha sido vendida.\n", o2.getTitulo());
                    break;
                }
                else if ( (o2.getEstado()).equals("PRESTADA") ){
                    System.out.printf("La obra '%s' está actualmente prestada, por lo que no se puede vender.\n", o2.getTitulo());
                    break;
                }
                //como está a la venta, se registra su venta y se da un aviso
                System.out.println("Ingrese la fecha de la venta (formato: 'AAAA-MM-DD'):");
                String fechaS = l2.readLine(); 
                //VERIFICAR SI SE INGRESO CORRECTAMENTE LA FECHA (TRY-CATCH)****
                System.out.printf("Ingrese el rut SIN GUIÓN del cliente que desea comprar '%s' : \n", o2.getTitulo());
                System.out.println("**EL CLIENTE DEBE ESTAR PREVIAMENTE REGISTRADO. DE NO ESTARLO, NO SE REALIZARÁ LA VENTA.");
                System.out.println("SI DESEA REGISTRAR UN NUEVO CLIENTE, INGRESE '0' (cero) PARA VOLVER AL MENÚ DE VENTAS.**");
                String rutC = l2.readLine();
                if (rutC.equals("0")) break;
                if (!clientes.containsKey(rutC)){
                    System.out.printf("El cliente de rut %s no se encuentra registrado.\n", rutC);
                    System.out.println("Para que la venta se realice correctamente, el cliente debe estar previamente registrado.");
                    break;
                }
                Cliente c = (Cliente) clientes.get(rutC);
                System.out.println("Ingrese el precio de la venta:");
                int p = Integer.parseInt(l2.readLine());
                do {
                    if (p >= 0) break;
                    System.out.println("Ingrese un precio válido: ");
                    p = Integer.parseInt(l2.readLine());
                } while ( p < 0);
                Venta n = new Venta(fechaS, c, o2, p);
                n.registrar();
                break;
            case '4':
                //Menu.menuSubastas(subastas); 
                break;
            case '5':
                System.out.println("Saliendo del menú......");
                break;
            default:
                System.out.println("Opción no válida, intente nuevamente.");
                break;
            }
        } while(opcion != '5');
    }
    
    
}
