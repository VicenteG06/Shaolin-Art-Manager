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
    public static void guardarClientesCsv(HashMap<String, Cliente> mapaClientes, String rutaArchivo) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(rutaArchivo))) {
            escritor.println("rut,obrasCompradas,obrasPrestadas");
            for (Cliente cliente : mapaClientes.values()) {
            
                // Unir IDs de obras compradas separadas por coma
                StringBuilder comprasStr = new StringBuilder();
                if (cliente.getListaCompras() != null) {
                    for (int i = 0; i < cliente.getListaCompras().size(); i++) {
                        comprasStr.append(cliente.getListaCompras().get(i).getId());
                        if (i < cliente.getListaCompras().size() - 1) {
                        comprasStr.append(",");
                        }
                    }
                }

                // Unir IDs de obras prestadas separadas por coma
                StringBuilder prestamosStr = new StringBuilder();
                if (cliente.getListaPrestamos() != null) {
                    for (int i = 0; i < cliente.getListaPrestamos().size(); i++) {
                        prestamosStr.append(cliente.getListaPrestamos().get(i).getId());
                        if (i < cliente.getListaPrestamos().size() - 1) {
                            prestamosStr.append(",");
                        }
                    }
                }

                // Se guardan entre comillas para que las comas internas no rompan el formato de las columnas del CSV
                escritor.println(
                    cliente.getRut() + "," +
                    "\"" + comprasStr.toString() + "\"," +
                    "\"" + prestamosStr.toString() + "\""
                );
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo csv de clientes: " + e.getMessage());
        }
    }

    public static HashMap<String, Cliente> cargarClientesDesdeCsv(String rutaArchivo, HashMap<String, Obra> mapaObras) {
        HashMap<String, Cliente> mapaClientes = new HashMap<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            lector.readLine(); // Saltar encabezado
            String linea = "";
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (campos.length >= 3) {
                    String rut = campos[0].trim();
                    String idsCompras = campos[1].replace("\"", "").trim();
                    String idsPrestamos = campos[2].replace("\"", "").trim();

                    Cliente cliente = new Cliente(rut);

                    // Reconstruir lista de compras buscando por ID en el mapa de obras
                    if (!idsCompras.isEmpty()) {
                        String[] arrayIds = idsCompras.split(",");
                        for (String idObra : arrayIds) {
                            Obra obra = mapaObras.get(idObra.trim());
                            if (obra != null) {
                                cliente.getListaCompras().add(obra);
                            }
                        }
                    }

                    // Reconstruir lista de préstamos buscando por ID en el mapa de obras
                    if (!idsPrestamos.isEmpty()) {
                        String[] arrayIds = idsPrestamos.split(",");
                        for (String idObra : arrayIds) {
                            Obra obra = mapaObras.get(idObra.trim());
                            if (obra != null) {
                                cliente.getListaPrestamos().add(obra);
                            }
                        }
                    }

                    mapaClientes.put(rut, cliente);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo csv de clientes: " + e.getMessage());
        }
        return mapaClientes;
    }
    public static void guardarExposicionesCsv(HashMap<String, Exposicion> mapaExposiciones, String rutaArchivo) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(rutaArchivo))) {
            escritor.println("id,titulo,fechaInicio,fechaTermino,obras");
            for (Exposicion expo : mapaExposiciones.values()) {
            
                // Unir IDs de obras de la exposición separadas por coma
                StringBuilder obrasStr = new StringBuilder();
                if (expo.getListaObras() != null) {
                    for (int i = 0; i < expo.getListaObras().size(); i++) {
                        obrasStr.append(expo.getListaObras().get(i).getId());
                        if (i < expo.getListaObras().size() - 1) {
                            obrasStr.append(",");
                        }
                    }
                }

                escritor.println(
                    expo.getId() + "," +
                    "\"" + expo.getTitulo() + "\"," +
                    expo.getfechaInicio() + "," +
                    expo.getfechaTermino() + "," +
                    "\"" + obrasStr.toString() + "\""
                );
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo csv de exposiciones: " + e.getMessage());
        }
    }

    public static HashMap<String, Exposicion> cargarExposicionesDesdeCsv(String rutaArchivo, HashMap<String, Obra> mapaObras) {
        HashMap<String, Exposicion> mapaExposiciones = new HashMap<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            lector.readLine(); // Saltar encabezado
            String linea = "";
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (campos.length >= 5) {
                    String id = campos[0].trim();
                    String titulo = campos[1].replace("\"", "").trim();
                    LocalDate fechaInicio = LocalDate.parse(campos[2].trim());
                    LocalDate fechaTermino = LocalDate.parse(campos[3].trim());
                    String idsObras = campos[4].replace("\"", "").trim();

                    // Creamos la exposición vacía o con la estructura de fechas iniciales
                    Exposicion expo = new Exposicion(id, titulo, fechaInicio, fechaTermino, null);

                    // Recorrer los IDs de la celda, buscarlos en el mapa y añadirlos a la exposición
                    if (!idsObras.isEmpty()) {
                        String[] arrayIds = idsObras.split(",");
                        for (String idObra : arrayIds) {
                            Obra obra = mapaObras.get(idObra.trim());
                            if (obra != null) {
                                expo.anadirObra(obra); // Utiliza el método de tu clase Exposicion
                            }
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
