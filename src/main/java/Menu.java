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

    public static Subasta subastaActiva =null; //VARIABLE PARA QUE LA SUBASTA ACTIVA SE MANTENGA ACTIVA AUNQUE SE CIERRE EL MENU DE SUBASTAS

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
                       HashMap<String, Exposicion> exposiciones, ArrayList<Venta> ventas, 
                       HashMap<String, Cliente> clientes, ArrayList<Prestamo> prestamos, ArrayList<Subasta> subastas) throws IOException {
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
                Menu.menuPrestamos(prestamos, obras, clientes);
                break;
            case '5':
                Menu.menuSubastas(subastas, obras, clientes);
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
                //Exposicion.buscarExposicion(exposiciones);
                break;
            case '3':
                //Exposicion.registrarExposicion(exposiciones, obras);
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
    // menu ventas
    public static void mostrarMenuVentas(){
        System.out.println("========================");
        System.out.println("           Ventas");
        System.out.println("========================");
        System.out.println("1) Mostrar Ventas");
        System.out.println("2) Buscar Venta");
        System.out.println("3) Registrar Venta");
        System.out.println("4) Eliminar Venta");
        System.out.println("5) Menu Subastas");
        System.out.println("6) Salir del Menú");
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
                Venta auxV1;
                for (int i = 0 ; i < ventas.size() ; i++){
                    auxV1= (Venta) ventas.get(i);
                    auxV1.mostrarAtributos();
                }
                break;
            case '2': //BUSCAR VENTAS
                //si no hay ventas, se da un aviso y se retorna al menu
                if (ventas.size() == 0){
                    System.out.println("No hay ventas registradas.");
                    break;
                }
                //se busca la obra vendida. De no existir, se da un aviso y se retorna al menu
                System.out.println("Ingrese el ID de la Obra vendida:"); 
                BufferedReader l = new BufferedReader(new InputStreamReader(System.in));
                String idObra2 = l.readLine();
                if(!obras.containsKey(idObra2)){
                    System.out.println("No existe esa obra");
                    break;
                }
                //al obtener la obra, si no esta vendida se da un aviso y se retorna al menu
                Obra o2 = obras.get(idObra2); 
                if ( !(o2.getEstado()).equals("VENDIDA") ){
                    System.out.printf("La obra '%s' no ha sido vendida.\n", o2.getTitulo());
                    break;
                }
                //como se sabe que esta vendida, se busca y muestra 
                Venta auxV2; 
                for (int i = 0 ; i < ventas.size() ; i++ ){
                    auxV2 = (Venta) ventas.get(i);
                    if (auxV2.getObra() == o2){
                        auxV2.mostrarAtributos();
                        break;
                    }
                }
                //si por alguna razón no se encontró, se da aviso 
                System.out.printf("'%s' sale vendida pero no se encontró en lista ventas.\n", o2.getTitulo());
                break;
            case '3': 
                //se busca la obra que se quiere comprar. De no existir, se da un aviso y se retorna al menu
                System.out.println("Ingrese el ID de la Obra que se desea vender:"); 
                BufferedReader l3 = new BufferedReader(new InputStreamReader(System.in));
                String idObra3 = l3.readLine();
                if(!obras.containsKey(idObra3)){
                    System.out.println("No existe esa obra");
                    break;
                }
                //al obtener la obra, se verifica que esta obra no este ya vendida ni prestada
                Obra o3 = obras.get(idObra3); 
                if ( (o3.getEstado()).equals("VENDIDA") ){
                    System.out.printf("La obra '%s' ya ha sido vendida.\n", o3.getTitulo());
                    break;
                }
                else if ( (o3.getEstado()).equals("PRESTADA") ){
                    System.out.printf("La obra '%s' está actualmente prestada, por lo que no se puede vender.\n", o3.getTitulo());
                    break;
                }
                //como está a la venta, se registra su venta 
                System.out.println("Ingrese la fecha de la venta (formato: 'AAAA-MM-DD'):");
                String fechaS = l3.readLine(); 
                //VERIFICAR SI SE INGRESO CORRECTAMENTE LA FECHA (TRY-CATCH)****
                System.out.printf("Ingrese el rut SIN GUIÓN Y CON DÍGITO VERIFICADOR del cliente que desea comprar '%s' : \n", o3.getTitulo());
                
                String rutC = l3.readLine();
                
                // Crear cliente
                Cliente c;
                if (!clientes.containsKey(rutC)){
                    System.out.printf("El cliente de rut %s no se encuentra registrado. Creando nuevo cliente...\n", rutC);
                    c = new Cliente(rutC);
                    clientes.put(rutC, c); // Se añade al mapa global inmediatamente
                    System.out.println("¡Cliente nuevo registrado en el sistema con éxito!");
                } else {
                    c = clientes.get(rutC);
                    System.out.println("Cliente encontrado en el sistema.");
                }

                System.out.println("Ingrese el precio de la venta:");
                int p = Integer.parseInt(l3.readLine());
                do {
                    if (p >= 0) break;
                    System.out.println("Ingrese un precio válido: ");
                    p = Integer.parseInt(l3.readLine());
                } while ( p < 0);
                
                Venta n = new Venta(fechaS, c, o3, p);
                //se ingresa la obra a la lista de compras del cliente en el mismo metodo de registro
                n.registrar();
                ventas.add(n); // AGREGA AL ARRAYLIS DE VENTAS
                break;
            case '4':
                //si no hay ventas, se da un aviso y se retorna al menu
                if (ventas.size() == 0){ 
                    System.out.println("No hay ventas registradas.");
                    break;
                }
                //se busca la obra vendida. De no existir, se da un aviso y se retorna al menu
                System.out.println("Ingrese el ID de la Obra vendida:"); 
                BufferedReader l4 = new BufferedReader(new InputStreamReader(System.in));
                String idObra4 = l4.readLine();
                if(!obras.containsKey(idObra4)){
                    System.out.println("No existe esa obra");
                    break;
                }
                //al obtener la obra, si no esta vendida se da un aviso y se retorna al menu
                Obra o4 = obras.get(idObra4); 
                if ( !(o4.getEstado()).equals("VENDIDA") ){
                    System.out.printf("La obra '%s' no ha sido vendida.\n", o4.getTitulo());
                    break;
                }
                //como se sabe que esta vendida, se busca, se elimina y se da un aviso de ello
                Venta auxV4; 
                for (int i = 0 ; i < ventas.size() ; i++ ){
                    auxV4= (Venta) ventas.get(i);
                    if (auxV4.getObra() == o4){
                        Cliente cl = auxV4.getCliente(); //se obtiene el cliente de la venta 
                        if (!cl.elimObComprada(o4)){ //se elimina la obra de la lista de obras compradas del cliente 
                            System.out.printf("Hubo un problema al eliminar la obra de la lista de compras del cliente %s (el cliente no es dueño de la obra)\n", cl.getRut());
                        } 
                        ventas.remove(auxV4); //se elimina la venta de la lista de ventas 
                        System.out.printf("La venta de %s ha sido eliminada con éxito.\n", o4.getTitulo());
                        break;
                    }
                }
                System.out.println("No se pudo encontrar la venta.");
                break;
            case '5':
                //Menu.menuSubastas();
            case '6':
                System.out.println("Saliendo del menú......");
                break;
            default:
                System.out.println("Opción no válida, intente nuevamente.");
                break;
            }
        } while(opcion != '6');
    }
    //menu subastas
     public static void mostrarMenuSubastas(){
        System.out.println("========================");
        System.out.println("           Subastas");
        System.out.println("========================");
        System.out.println("1) Mostrar subastas cerradas");
        System.out.println("2) Iniciar nueva subasta");
        System.out.println("3) Registrar nueva oferta en la subasta activa");
        System.out.println("4) Cerrar subasta activa");
        System.out.println("5) Salir del Menú");
        
        
    }
    public static void menuSubastas(ArrayList<Subasta> subastas, HashMap<String, Obra> obras, HashMap<String, Cliente> clientes) throws IOException{
        char opcion;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        do{
            Menu.mostrarMenuSubastas();
            opcion = (lector.readLine()).charAt(0);
            
            switch(opcion){
            case '1': //MOSTRAR SUBASTAS CERRADAS
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
                break; 
            case '2': // iniciar subasta
                
                if (Menu.subastaActiva != null) {

                    System.out.println("Ya hay una subasta en curso.");
                    break;
                }
                
                System.out.println("Ingrese el ID de la Obra a subastar:");

                String idObra = lector.readLine();
                if(!obras.containsKey(idObra) ){
                    System.out.println("No existe esa obra .");
                    break;
                }
                
                Obra o = obras.get(idObra); //REVISAR SI LA OBRA TIENE ESTADO "DISPONIBLE"
                if (!o.getEstado().equals("Disponible")) { 

                    System.out.println("La obra no está disponible para ser subastada.");
                    break;
                }
                
                System.out.println("Ingrese el precio inicial de la obra:");
                int precioInicial = Integer.parseInt(lector.readLine()); 
                
                System.out.println("Ingrese la fecha de la subasta (formato AAAA-MM-DD):");
                String fechaS = (lector.readLine());
                
                // INICIALIZAR VARIABLE GLOABL DE MENU 
                Menu.subastaActiva = new Subasta(o, precioInicial, fechaS);
                System.out.println("Subasta iniciada con éxito");
                break;
            case '3': // ofertar
                if (Menu.subastaActiva == null) {
                    System.out.println("No hay ninguna subasta activa en este momento.");
                    break;
                }
                
                System.out.println("Ingrese el RUT del cliente que oferta (Sin guión, incluyendo dígito verificador):");
                String rutOfertador = lector.readLine();
                
                System.out.println("Ingrese el monto de la oferta:");
                int monto = Integer.parseInt(lector.readLine());
                
                Menu.subastaActiva.ofertar(monto, rutOfertador);
                break;
            case '4': // CERRAR SUBASTA
                if (Menu.subastaActiva == null) { //verificar que haya una subasta activa
                    System.out.println("No hay ninguna subasta activa para cerrar.");
                    break;
                }
                System.out.println("Cerrando subasta...");
                boolean exito = Menu.subastaActiva.cerrarSubasta(clientes);
                
                if (exito) {
                    subastas.add(Menu.subastaActiva);
                }
                
                // Reiniciamos la variable de subastaActiva
                Menu.subastaActiva = null; 
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
    public static void menuPrestamos(ArrayList<Prestamo> prestamos, HashMap<String, Obra> obras, HashMap<String, Cliente> clientes) throws IOException{
        char opcion;
        do{
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            Menu.mostrarMenuPrestamos();
            opcion = (lector.readLine()).charAt(0);
            switch(opcion){
            case '1': //mostrar 
                //si no hay préstamos registrados, se da un aviso y se retorna al menu 
                if (prestamos.size() == 0) {
                    System.out.println("No hay préstamos registrados.");
                    break;
                }
                System.out.println("-> PRÉSTAMOS:"); //está como medio feo. Arreglar
                Prestamo auxP;
                for (int i = 0 ; i < prestamos.size() ; i++){
                    auxP= prestamos.get(i);
                    auxP.mostrarAtributos();
                }
                break;
            case '2': //Buscar por obra
                //si no hay prestamos, se da un aviso y se retorna al menu
                if (prestamos.size() == 0){
                    System.out.println("No hay préstamos registrados.");
                    break;
                }
                //se busca la obra prestada. De no existir, se da un aviso y se retorna al menu
                System.out.println("Ingrese el ID de la Obra prestada:"); 
                BufferedReader l2 = new BufferedReader(new InputStreamReader(System.in));
                String idObra2 = l2.readLine();
                if(!obras.containsKey(idObra2)){
                    System.out.println("No existe esa obra");
                    break;
                }
                //al obtener la obra, si no esta prestada se da un aviso y se retorna al menu
                Obra o2 = obras.get(idObra2); 
                if ( !(o2.getEstado()).equals("PRESTADA") ){
                    System.out.printf("La obra '%s' no ha sido prestada.\n", o2.getTitulo());
                    break;
                }
                //como se sabe que esta prestada, se busca y muestra
                Prestamo auxP2; 
                for (int i = 0 ; i < prestamos.size() ; i++ ){
                    auxP2 = (Prestamo) prestamos.get(i);
                    if (auxP2.getObra() == o2){
                        auxP2.mostrarAtributos();
                        break;
                    }
                }
                //si por alguna razón no se encontró, se da aviso 
                System.out.printf("'%s' sale prestada pero no se encontró en lista préstamos.\n", o2.getTitulo());
                break;
            case '3': //registrar

                //se busca la obra que se quiere pedir prestada. De no existir, se da un aviso y se retorna al menu
                System.out.println("Ingrese el ID de la Obra que se desea pedir prestada:"); 
                BufferedReader l3 = new BufferedReader(new InputStreamReader(System.in));
                String idObra3 = l3.readLine();
                if(!obras.containsKey(idObra3)){
                    System.out.println("No existe esa obra");
                    break;
                }
                //al obtener la obra, se verifica que esta obra no este ya vendida ni prestada 
                Obra o3 = obras.get(idObra3); 
                if ( (o3.getEstado()).equals("VENDIDA") ){
                    System.out.printf("La obra '%s' está vendida, por lo que no se puede prestar.\n", o3.getTitulo());
                    break;
                }
                else if ( (o3.getEstado()).equals("PRESTADA") ){
                    System.out.printf("La obra '%s' ya está actualmente prestada.\n", o3.getTitulo());
                    break;
                }
                //como está disponible, se puede prestar 
                System.out.printf("Ingrese el rut SIN GUIÓN Y CON DÍGITO VERIFICADOR del cliente que desea pedir prestado '%s' : \n", o3.getTitulo());
                System.out.println("**EL CLIENTE DEBE ESTAR PREVIAMENTE REGISTRADO. DE NO ESTARLO, NO SE REALIZARÁ EL PRÉSTAMO.");
                System.out.println("SI DESEA REGISTRAR UN NUEVO CLIENTE, INGRESE '0' (cero) PARA VOLVER AL MENÚ DE VENTAS.**");
                String rutC = l3.readLine();
                if (rutC.equals("0")) break;
                
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


                //se pide la fecha de inicio y de retorno del prestamo
                System.out.println("Ingrese la fecha de INICIO del préstamo:");
                String fechaI = l3.readLine();
                //VERIFICAR SI SE INGRESO CORRECTAMENTE LA FECHA (TRY-CATCH)****
                System.out.println("Ingrese la fecha de RETORNO del préstamo (no debe superar el AÑO):");
                String fechaR = l3.readLine();
                //VERIFICAR SI SE INGRESO CORRECTAMENTE LA FECHA (TRY-CATCH)**** 
                String nId= "000"; //hay q generar un id lolz**********************
                Prestamo n = new Prestamo(nId, c, o3, fechaI, fechaR);
                //se ingresa la obra a la lista de prestamos del cliente en el mismo metodo de registro
                n.registrar();
                prestamos.add(n); // AGREGAR AL ARRAYLIST DE PRESTAMOS
                break;
            case '4': //eliminar 

                //si no hay prestamos, se da un aviso y se retorna al menu
                if (prestamos.size() == 0){
                    System.out.println("No hay préstamos registrados.");
                    break;
                }
                //se busca la obra prestada. De no existir, se da un aviso y se retorna al menu
                System.out.println("Ingrese el ID de la Obra prestada:"); 
                BufferedReader l4 = new BufferedReader(new InputStreamReader(System.in));
                String idObra4 = l4.readLine();
                if(!obras.containsKey(idObra4)){
                    System.out.println("No existe esa obra");
                    break;
                }
                //al obtener la obra, si no esta prestada se da un aviso y se retorna al menu
                Obra o4 = obras.get(idObra4); 
                if ( !(o4.getEstado()).equals("PRESTADA") ){
                    System.out.printf("La obra '%s' no ha sido prestada.\n", o4.getTitulo());
                    break;
                }
                //como se sabe que esta prestada, se busca, se elimina y se da un aviso de ello
                Prestamo auxP4; 
                for (int i = 0 ; i < prestamos.size() ; i++ ){
                    auxP4= (Prestamo) prestamos.get(i);
                    if (auxP4.getObra() == o4){
                        Cliente cl = auxP4.getCliente(); //se obtiene el cliente del prestamo 
                        if (!cl.elimObPrestada(o4)){ //se elimina la obra de la lista de obras prestadas del cliente 
                            System.out.printf("Hubo un problema al eliminar la obra de la lista de préstamos del cliente %s (el cliente no ha pedido prestada la obra)\n", cl.getRut());
                        } 
                        prestamos.remove(auxP4); //se elimina el prestamo de la lista de prestamos 
                        System.out.printf("El préstamo de %s ha sido eliminado con éxito.\n", o4.getTitulo());
                        break;
                    }
                }
                System.out.println("No se pudo encontrar el préstamo.");
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