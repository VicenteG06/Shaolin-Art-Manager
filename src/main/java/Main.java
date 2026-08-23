/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vicente
 */

import java.util.*;

public class Main {
    public static void main(String[] args){
        HashMap<String, Obra> obras = CargarObras.cargarDesdeCsv("data/obras_de_arte.csv");
        HashMap<String, Artista> artistas = CargarObras.getMapaArtistas();

        System.out.println("Total de obras en el sistema: " + obras.size());
        
        String nombreBuscado = "Mona Lisa";

        Obra obraEncontrada = obras.get(nombreBuscado.toLowerCase());

        if(obraEncontrada != null){
            obraEncontrada.mostrarAtributos();
        }
    }
}
