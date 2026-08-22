/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vicente
 */

import java.io*;
import java.util.*;

public class CargarObras {
    public ArrayList<Obra> cargarDesdeCsv(String rutaArchivo){
        ArrayList<Obra> listaObras = new ArrayList<>();
        String linea = "";
        BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo));
        //Para leer el encabezado del csv
        lector.readLine();
        while((linea = lector.readLine()) != NULL){
            String[] campos = linea.split(",");

            if(campos.length >= 9){
                int id = Integer.parseInt(campos[0].trim());
                String titulo = campos[1].replace("\"", "").trim();
                String artista = campos[2].replace("\"", "").trim();
                int anio = Integer.parseInt(campos[3].trim());
                String estado = campos[8].replace("\"", "").trim();

                Obra obra = new Obra(id, titulo, artista, anio, estado);
                listaObras.add(obra);
            }
        }
        return listaObras;
    }
}
