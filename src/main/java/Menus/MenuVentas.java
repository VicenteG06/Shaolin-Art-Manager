/**
 * @archivo: MenuVentas.java
 * @Proyecto: Shaolin Art Manager 
 * @Descripción: Clase para manejar el menú de Ventas
 * @author Alexia Gallardo
 * @Lenguaje: Java
*/

import java.io.*;
import java.util.*;
import java.time.*;

public class MenuVentas {
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

    public static void mostrarVentas(ArrayList<Venta> ventas) throws IOException{
        //si no hay ventas, se da un aviso y se retorna al menu
        if (ventas.size() == 0){ 
            System.out.println("No hay ventas registradas.");
            return;
        }
        //como hay ventas, se recorre la lista y se muestran
        System.out.println("-> VENTAS:"); //está como medio feo. Arreglar
        Venta auxV1;
        for (int i = 0 ; i < ventas.size() ; i++){
            auxV1= (Venta) ventas.get(i);
            auxV1.mostrarAtributos();
        }
        PresioneTeclaParaContinuar.ptpc();
    }
    
    public static void buscarVenta(ArrayList<Venta> ventas, HashMap<String, Obra> obras) throws IOException{
        //si no hay ventas, se da un aviso y se retorna al menu
        if (ventas.size() == 0){
            System.out.println("No hay ventas registradas.");
            return;
        }
        //se busca la obra vendida. De no existir, se da un aviso y se retorna al menu
        System.out.println("Ingrese el ID de la Obra vendida:"); 
        BufferedReader l = new BufferedReader(new InputStreamReader(System.in));
        String idObra = l.readLine();
        if(!obras.containsKey(idObra)){
            System.out.println("No existe esa obra");
            return;
        }
        //al obtener la obra, si no esta vendida se da un aviso y se retorna al menu
        Obra o = obras.get(idObra); 
        if ( !(o.getEstado()).equals("VENDIDA") ){
            System.out.printf("La obra '%s' no ha sido vendida.\n", o.getTitulo());
            return;
        }
        //como se sabe que esta vendida, se busca y muestra 
        Venta auxV; 
        for (int i = 0 ; i < ventas.size() ; i++ ){
            auxV = (Venta) ventas.get(i);
            if (auxV.getObra() == o){
                auxV.mostrarAtributos();
                return;
            }
        }   
        //si por alguna razón no se encontró, se da aviso 
        System.out.printf("'%s' sale vendida pero no se encontró en lista ventas.\n", o.getTitulo());
    }
    public static void registrarVenta(ArrayList<Venta> ventas, HashMap<String, Obra> obras, HashMap<String, Cliente> clientes) throws IOException{
        //se busca la obra que se quiere comprar. De no existir, se da un aviso y se retorna al menu
        System.out.println("Ingrese el ID de la Obra que se desea vender:"); 
        BufferedReader l = new BufferedReader(new InputStreamReader(System.in));
        String idObra = l.readLine();
        if(!obras.containsKey(idObra)){
            System.out.println("No existe esa obra");
            return;
        }
        //al obtener la obra, se verifica que esta obra no este ya vendida ni prestada
        Obra o = obras.get(idObra); 
        if ( (o.getEstado()).equals("VENDIDA") ){
            System.out.printf("La obra '%s' ya ha sido vendida.\n", o.getTitulo());
            return;
        }
        else if ( (o.getEstado()).equals("PRESTADA") ){
            System.out.printf("La obra '%s' está actualmente prestada, por lo que no se puede vender.\n", o.getTitulo());
            return;
        }
        //como está a la venta, se registra su venta 
        LocalDate fechaVentaObj = Validaciones.pedirFecha(l, "Ingrese la fecha de la venta (formato: AAAA-MM-DD):");
        
        String mensajeRut = "Ingrese el rut SIN GUIÓN Y CON DÍGITO VERIFICADOR del cliente que desea comprar '" + o.getTitulo() + "':\n(Ingrese '0' para cancelar la operación)";
        String rutC = Validaciones.pedirRut(l, mensajeRut);
        if (rutC.equals("0")) return; // si usiario ingresa "0", se devuelve al menú para no quedar en loop infinito
                
        // Crear cliente
        Cliente c;
        if (!clientes.containsKey(rutC)){
            System.out.printf("El cliente de rut %s no se encuentra registrado. Creando nuevo cliente...\n", rutC);
            c = new Cliente(rutC);
            clientes.put(rutC, c); // Se añade al mapa global inmediatamente
            System.out.println("Cliente nuevo registrado en el sistema con éxito!");
        } else {
            c = clientes.get(rutC);
            System.out.println("Cliente encontrado en el sistema.");
        }

        int p = Validaciones.pedirEnteroPositivo(l, "Ingrese el precio de la venta:");
                
        Venta n = new Venta(fechaVentaObj, c, o, p);
        //se ingresa la obra a la lista de compras del cliente en el mismo metodo de registro
        if(n.registrar()){
            System.out.println("Venta registrada con éxito");
        }
        else { System.out.println("Hubo un error al registrar la compra de la obra"); }
        ventas.add(n); // AGREGA AL ARRAYLIS DE VENTAS
        return;
    }
    public static void eliminarVenta(ArrayList<Venta> ventas, HashMap<String, Obra> obras) throws IOException{
        //si no hay ventas, se da un aviso y se retorna al menu
        if (ventas.size() == 0){ 
            System.out.println("No hay ventas registradas.");
             return;
        }
        //se busca la obra vendida. De no existir, se da un aviso y se retorna al menu
        System.out.println("Ingrese el ID de la Obra vendida:"); 
        BufferedReader l = new BufferedReader(new InputStreamReader(System.in));
        String idObra = l.readLine();
        if(!obras.containsKey(idObra)){
            System.out.println("No existe esa obra");
            return;
        }
        //al obtener la obra, si no esta vendida se da un aviso y se retorna al menu
        Obra o = obras.get(idObra); 
        if ( !(o.getEstado()).equals("VENDIDA") ){
            System.out.printf("La obra '%s' no ha sido vendida.\n", o.getTitulo());
            return;
        }
        //como se sabe que esta vendida, se busca, se elimina y se da un aviso de ello
        Venta auxV; 
        for (int i = 0 ; i < ventas.size() ; i++ ){
            auxV = (Venta) ventas.get(i);
            if (auxV.getObra() == o){
                Cliente cl = auxV.getCliente(); //se obtiene el cliente de la venta 
                if (!cl.elimObComprada(o)){ //se elimina la obra de la lista de obras compradas del cliente 
                    System.out.printf("Hubo un problema al eliminar la obra de la lista de compras del cliente %s (el cliente no es dueño de la obra)\n", cl.getRut());
                } 
                ventas.remove(auxV); //se elimina la venta de la lista de ventas 
                System.out.printf("La venta de %s ha sido eliminada con éxito.\n", o.getTitulo());
                o.setEstado("Disponible");
                return;
            }
        }
        System.out.println("No se pudo encontrar la venta.");
    }
    public static void menuVentas(ArrayList<Venta> ventas, HashMap<String, Obra> obras, HashMap<String, Cliente> clientes, ArrayList<Subasta> subastas) throws IOException, EmptyEntryException{
        char opcion = ' ';
        do{
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            MenuVentas.mostrarMenuVentas();
            try{
                String entrada = lector.readLine();
                if(entrada == null || entrada.trim().isEmpty()){
                    throw new EmptyEntryException();
                }
                opcion = entrada.charAt(0);
                switch(opcion){
                case '1': 
                    MenuVentas.mostrarVentas(ventas);
                    break;
                case '2': //BUSCAR VENTAS
                    MenuVentas.buscarVenta(ventas, obras);
                    break;
                case '3': 
                    MenuVentas.registrarVenta(ventas, obras, clientes);
                    break;
                case '4':
                    MenuVentas.eliminarVenta(ventas, obras);
                    break;
                case '5':
                    MenuSubastas.menuSubastas(subastas, obras, clientes);
                case '6':
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
        } while(opcion != '6');
    }
}