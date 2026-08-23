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

public class CargarObras {
    private static HashMap<String, Artista> mapaArtistas = new HashMap<>();
    public static HashMap<String, Obra> cargarDesdeCsv(String rutaArchivo) {
        HashMap<String,Obra> mapaObras = new HashMap<>();
        
        try(BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))){

            String linea = "";
            //Para leer el encabezado del csv
            lector.readLine();
            while((linea = lector.readLine()) != null){
                String[] campos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                if(campos.length >= 9){
                    int id = Integer.parseInt(campos[0].trim());
                    String titulo = campos[1].replace("\"", "").trim();
                    String artista = campos[2].replace("\"", "").trim();
                    int anio = Integer.parseInt(campos[3].trim());
                    String estado = campos[8].replace("\"", "").trim();
                
                    Obra obra = new Obra(id, titulo, null, estado, anio);

                    Artista pair = mapaArtistas.get(artista.toLowerCase());
                    if(pair == null){
                        Artista nuevo_artista = new Artista();
                        nuevo_artista.setNombre(artista);
                        nuevo_artista.anadirObra(obra);
                        mapaArtistas.put(artista.toLowerCase(), nuevo_artista);
                        obra.setArtista(nuevo_artista);
                    }
                    else{
                        pair.anadirObra(obra);
                        obra.setArtista(pair);
                    
                    }
                    mapaObras.put(titulo.toLowerCase(), obra);
                }
            }
        } catch(IOException e){
            System.err.println("Error al leer el archivo csv: " + e.getMessage());
        }
        return mapaObras;
    }

    public static HashMap<String, Artista> getMapaArtistas() {
        return mapaArtistas;
    }
}
