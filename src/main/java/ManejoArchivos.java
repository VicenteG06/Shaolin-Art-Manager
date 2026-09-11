/**
 * @archivo: ManejoArchivos.java
 * @Proyecto: Shaolin Art Manager 
 * @Descripción: Clase para el manejo de archivos csv
 * @author Vicente Gamboa
 * @Lenguaje: Java
*/
import java.io.*;
import java.util.*;

public class ManejoArchivos {
    private static HashMap<String, Artista> mapaArtistas = new HashMap<>();
    public static HashMap<String, Obra> cargarDesdeCsv(String rutaArchivo) {
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
}
