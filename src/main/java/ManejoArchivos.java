/**
 * @archivo: ManejoArchivos.java
 * @Proyecto: Shaolin Art Manager 
 * @Descripción: Clase para el manejo de archivos csv
 * @author Vicente Gamboa
 * @Lenguaje: Java
*/
import java.io.*;
import java.util.*;
import java.time.*;

public class ManejoArchivos {
    private static HashMap<String, Artista> mapaArtistas = new HashMap<>();

    public static HashMap<String, Obra> cargarObrasDesdeCsv(String rutaArchivo) {
        HashMap<String,Obra> mapaObras = new HashMap<>();
        
        try(BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))){

            String linea = "";
            //Para leer el encabezado del csv.
            lector.readLine();
            while((linea = lector.readLine()) != null){
                // Se separa la linea del csv por los campos de este
                String[] campos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                
                // Se leen los campos del csv y se guardan en variables
                if(campos.length >= 9){
                    String id = campos[0].trim();
                    String titulo = campos[1].replace("\"", "").trim();
                    String artista = campos[2].replace("\"", "").trim();
                    int anio = Integer.parseInt(campos[3].trim());
                    String estado = campos[8].replace("\"", "").trim();
                    // Se crea el objeto Obra
                    Obra obra = new Obra(id, titulo, null, estado, anio);
                    // Se verifica si el artista existe o no dentro del mapa de artistas
                    Artista pair = mapaArtistas.get(artista.toLowerCase());
                    if(pair == null){
                        // Si no existe se crea el objeto artista y se guarda la obra dentro de su lista de obras y en el mapa de obras
                        Artista nuevo_artista = new Artista();
                        nuevo_artista.setNombre(artista);
                        nuevo_artista.anadirObra(obra);
                        mapaArtistas.put(artista.toLowerCase(), nuevo_artista);
                        obra.setArtista(nuevo_artista);
                    }
                    else{
                        // Si existe, se guarda la obra dentro de su lista de obras
                        pair.anadirObra(obra);
                        obra.setArtista(pair);
                    
                    }
                    mapaObras.put(campos[0], obra);
                }
            }
        } catch(IOException e){
            // Excepción por si hay un error al leer el archivo
            System.err.println("Error al leer el archivo csv: " + e.getMessage());
        }
        // Se retorna el mapa de obras
        return mapaObras;
    }
    // Método para obtener el mapa de artistas y poder ser manejado en las demás clases
    public static HashMap<String, Artista> getMapaArtistas() {
        return mapaArtistas;
    }

    public static void guardarObrasPrestadasCliente(HashMap<String, Cliente> mapaClientes, String rutaArchivo){
        try(PrintWriter escritor = new PrintWriter(new FileWriter(rutaArchivo))){
            escritor.println("rut,obras prestadas");
            for(Cliente cliente : mapaClientes.values()){
                // Unir IDs de obras compradas separadas por coma
                StringBuilder prestamosStr = new StringBuilder();
                ArrayList<String> listaIdsPrestamos = cliente.getIdsPrestamos();
                if (listaIdsPrestamos != null) {
                    for (int i = 0; i < listaIdsPrestamos.size(); i++) {
                        prestamosStr.append(listaIdsPrestamos.get(i));
                        if (i < listaIdsPrestamos.size() - 1) {
                        prestamosStr.append(",");
                        }
                    }
                }
                escritor.println(cliente.getRut() + "," + prestamosStr.toString());
            }
        } catch(IOException e){
            System.err.println("Error al escribir el archivo csv de obras prestadas: " + e.getMessage());
        }
    }

    public static void guardarObrasCompradasCliente(HashMap<String, Cliente> mapaClientes, String rutaArchivo){
        try(PrintWriter escritor = new PrintWriter(new FileWriter(rutaArchivo))){
            escritor.println("rut,obras compradas");
            for(Cliente cliente : mapaClientes.values()){
                // Unir IDs de obras compradas separadas por coma
                StringBuilder comprasStr = new StringBuilder();
                ArrayList<String> listaIdsCompras = cliente.getIdsCompras();
                if (listaIdsCompras != null) {
                    for (int i = 0; i < listaIdsCompras.size(); i++) {
                        comprasStr.append(listaIdsCompras.get(i));
                        if (i < listaIdsCompras.size() - 1) {
                        comprasStr.append(",");
                        }
                    }
                }
                escritor.println(cliente.getRut() + ","  + comprasStr.toString());
            }
        } catch(IOException e){
            System.err.println("Error al escribir el archivo csv de obras compradas: " + e.getMessage());
        }
    }

    public static void guardarClientesCsv(HashMap<String, Cliente> mapaClientes, String rutaArchivo) {
        ManejoArchivos.guardarObrasPrestadasCliente(mapaClientes, "data/obras_prestadas_clientes.csv");
        ManejoArchivos.guardarObrasCompradasCliente(mapaClientes, "data/obras_compradas_clientes.csv");

        try (PrintWriter escritor = new PrintWriter(new FileWriter(rutaArchivo))) {
            escritor.println("rut");
            for (Cliente cliente : mapaClientes.values()) {
                // Se guarda el rut del cliente
                escritor.println(cliente.getRut());
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo csv de clientes: " + e.getMessage());
        }
    }

    public static HashMap<String, ArrayList<String>> obtenerMapaIdsObrasCompradas(String rutaArchivo){
        HashMap<String, ArrayList<String>> mapaIdsObrasCompradas = new HashMap<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))){
            lector.readLine(); // Saltar Encabezado
            String linea = "";
            while ((linea = lector.readLine()) != null){
                String[] campos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                String rut = campos[0].trim();
                ArrayList<String> idsObrasCompradas = new ArrayList<>();
                if (campos.length >= 1){
                    for(int i = 1; i < campos.length; i ++){
                        idsObrasCompradas.add(campos[i]);
                    }
                }
                mapaIdsObrasCompradas.put(rut, idsObrasCompradas);
            }
            
        } catch(IOException e) {
            System.err.println("Error al leer el archivo csv de obras compradas: " + e.getMessage());
        }
        return mapaIdsObrasCompradas;
    }

    public static HashMap<String, ArrayList<String>> obtenerMapaIdsObrasPrestamos(String rutaArchivo){
        HashMap<String, ArrayList<String>> mapaIdsObrasPrestamos = new HashMap<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))){
            lector.readLine(); // Saltar Encabezado
            String linea = "";
            while ((linea = lector.readLine()) != null){
                String[] campos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                String rut = campos[0].trim();
                ArrayList<String> idsObrasPrestamos = new ArrayList<>();
                if (campos.length >= 1){
                    for(int i = 1; i < campos.length; i ++){
                        idsObrasPrestamos.add(campos[i]);
                    }
                }
                mapaIdsObrasPrestamos.put(rut, idsObrasPrestamos);
            }
        } catch(IOException e) {
            System.err.println("Error al leer el archivo csv de obras prestadas: " + e.getMessage());
        }
        return mapaIdsObrasPrestamos;
    }

    public static HashMap<String, Cliente> cargarClientesDesdeCsv(String rutaArchivo, HashMap<String, Obra> mapaObras) {
        HashMap<String, ArrayList<String>> mapaIdsObrasCompradas = ManejoArchivos.obtenerMapaIdsObrasCompradas("data/obras_compradas_clientes.csv");
        HashMap<String, ArrayList<String>> mapaIdsObrasPrestadas = ManejoArchivos.obtenerMapaIdsObrasPrestamos("data/obras_prestadas_clientes.csv");

        HashMap<String, Cliente> mapaClientes = new HashMap<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            lector.readLine(); // Saltar encabezado
            String linea = "";
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (campos.length == 1) {
                    String rut = campos[0].trim();

                    Cliente cliente = new Cliente(rut);
                    
                    ArrayList<String> idsObrasCompradas = mapaIdsObrasCompradas.get(rut);
                    ArrayList<String> idsObrasPrestamos = mapaIdsObrasPrestadas.get(rut);
                    
                    for(int i = 0; i < idsObrasCompradas.size(); i++) {
                        cliente.agregarCompra(mapaObras.get(idsObrasCompradas.get(i)));                        
                    }

                    for(int i = 0; i < idsObrasPrestamos.size(); i++) {
                        cliente.agregarPrestamo(mapaObras.get(idsObrasPrestamos.get(i)));
                    }

                    mapaClientes.put(rut, cliente);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo csv de clientes: " + e.getMessage());
        }
        return mapaClientes;
    }

    public static void guardarObrasExpo(HashMap<String, Exposicion> mapaExposiciones, String rutaArchivo){
        try(PrintWriter escritor = new PrintWriter(new FileWriter(rutaArchivo))){
            escritor.println("id,obras");
            for(Exposicion expo : mapaExposiciones.values()){
                // Unir IDs de obras separadas por coma
                StringBuilder obrasStr = new StringBuilder();
                ArrayList<String> listaIds = expo.getIdsObras();
                if (listaIds != null) {
                    for (int i = 0; i < listaIds.size(); i++) {
                        obrasStr.append(listaIds.get(i));
                        if (i < listaIds.size() - 1) {
                        obrasStr.append(",");
                        }
                    }
                }
                escritor.println(expo.getId() + "," + obrasStr.toString());
            }
        } catch(IOException e){
            System.err.println("Error al escribir el archivo csv de obras de exposiciones: " + e.getMessage());
        }
    }
    
    public static void guardarExposicionesCsv(HashMap<String, Exposicion> mapaExposiciones, String rutaArchivo) {
        ManejoArchivos.guardarObrasExpo(mapaExposiciones, "data/obras_exposiciones.csv");
        try (PrintWriter escritor = new PrintWriter(new FileWriter(rutaArchivo))) {
            escritor.println("id,titulo,fechaInicio,fechaTermino");
            for (Exposicion expo : mapaExposiciones.values()) {
                escritor.println(expo.getId() + "," + expo.getTitulo() + "," + expo.getfechaInicio() + "," + expo.getfechaTermino());
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo csv de exposiciones: " + e.getMessage());
        }
    }

    public static HashMap<String, ArrayList<String>> obtenerMapaIdsObrasExpo(String rutaArchivo){
        HashMap<String, ArrayList<String>> mapaIdsObras = new HashMap<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))){
            lector.readLine(); // Saltar Encabezado
            String linea = "";
            while ((linea = lector.readLine()) != null){
                String[] campos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                String id = campos[0].trim();
                ArrayList<String> idsObras = new ArrayList<>();
                if (campos.length >= 1){
                    for(int i = 1; i < campos.length; i ++){
                        idsObras.add(campos[i]);
                    }
                }
                mapaIdsObras.put(id, idsObras);
            }
        } catch(IOException e) {
            System.err.println("Error al leer el archivo csv de obras de exposiciones: " + e.getMessage());
        }
        return mapaIdsObras;
    }

    public static HashMap<String, Exposicion> cargarExposicionesDesdeCsv(String rutaArchivo, HashMap<String, Obra> mapaObras) {
        HashMap<String, ArrayList<String>> mapaIdsObras = ManejoArchivos.obtenerMapaIdsObrasExpo("data/obras_exposiciones.csv");
        HashMap<String, Exposicion> mapaExposiciones = new HashMap<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            lector.readLine(); // Saltar encabezado
            String linea = "";
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (campos.length >= 4) {
                    String id = campos[0].trim();
                    String titulo = campos[1].trim();
                    LocalDate fechaInicio = LocalDate.parse(campos[2].trim());
                    LocalDate fechaTermino = LocalDate.parse(campos[3].trim());

                    // Creamos la exposición vacía o con la estructura de fechas iniciales
                    Exposicion expo = new Exposicion(id, titulo, fechaInicio, fechaTermino, null);

                    ArrayList<String> idsObras = mapaIdsObras.get(id);

                    for(int i = 0; i < idsObras.size(); i++) {
                        if(idsObras.get(i) != null){
                            expo.anadirObra(mapaObras.get(idsObras.get(i)));      
                        }
                    }
                    
                    mapaExposiciones.put(id, expo);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo csv de exposiciones: " + e.getMessage());
        }
        return mapaExposiciones;
    }

    public static void guardarPrestamosCsv(ArrayList<Prestamo> listaPrestamos, String rutaArchivo) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(rutaArchivo))) {
            escritor.println("id,rutCliente,idObra,fechaInicio,fechaRetorno");
            for (Prestamo prestamo : listaPrestamos) {
                escritor.println(
                    prestamo.getId() + "," +
                    prestamo.getCliente().getRut() + "," +
                    prestamo.getObra().getId() + "," +
                    prestamo.getFechaInicio() + "," +
                    prestamo.getFechaRetorno()
                );
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo csv de prestamos: " + e.getMessage());
        }
    }

    public static ArrayList<Prestamo> cargarPrestamosDesdeCsv(String rutaArchivo, HashMap<String, Cliente> mapaClientes, HashMap<String, Obra> mapaObras) {
        ArrayList<Prestamo> listaPrestamos = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            lector.readLine();
            String linea = "";
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (campos.length >= 3) {
                    String id = campos[0].trim();
                    String rutCliente = campos[1].trim();
                    String idObra = campos[2].trim();
                    String fechaInicio = campos[3].trim();
                    String fechaRetorno = campos[4].trim();
                
                    Cliente cliente = mapaClientes.get(rutCliente);
                    Obra obra = mapaObras.get(idObra);
                
                    if (cliente != null && obra != null) {
                        Prestamo prestamo = new Prestamo(id, cliente, obra, fechaInicio, fechaRetorno);
                        listaPrestamos.add(prestamo);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo csv de prestamos: " + e.getMessage());
        }
        return listaPrestamos;
    }

    public static void guardarVentasCsv(ArrayList<Venta> listaVentas, String rutaArchivo) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(rutaArchivo))) {
            escritor.println("FechaVenta,rutCliente,idObra,precio");
            for (Venta venta : listaVentas) {
                escritor.println(
                    venta.getFechaVenta() + "," +
                    venta.getCliente().getRut() + "," +
                    venta.getObra().getId() + "," +
                    venta.getPrecio()
                );
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo csv de ventas: " + e.getMessage());
        }
    }

    public static ArrayList<Venta> cargarVentasDesdeCsv(String rutaArchivo, HashMap<String, Cliente> mapaClientes, HashMap<String, Obra> mapaObras) {
        ArrayList<Venta> listaVentas = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            lector.readLine();
            String linea = "";
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (campos.length >= 4) {
                    String fechaVenta = campos[0].trim();
                    String rutCliente = campos[1].trim();
                    String idObra = campos[2].trim();
                    int precio = Integer.parseInt(campos[3].trim());
                
                    Cliente cliente = mapaClientes.get(rutCliente);
                    Obra obra = mapaObras.get(idObra);
                
                    if (cliente != null && obra != null) {
                        Venta venta = new Venta(fechaVenta, cliente, obra, precio);
                        listaVentas.add(venta);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo csv de ventas: " + e.getMessage());
        }
        return listaVentas;
    }
}
