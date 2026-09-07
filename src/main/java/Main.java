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

public class Main {
    public static void main(String[] args) throws IOException, EmptyEntryException{
        // Se cargan las obras del archivo .csv.
        HashMap<String, Obra> obras = ManejoArchivos.cargarDesdeCsv("data/obras_de_arte.csv");
        // Se obtiene el mapa de artistas de las obras cargadas del .csv
        HashMap<String, Artista> artistas = ManejoArchivos.getMapaArtistas();

        // Se declaran y se inicializan las colecciones 
        HashMap<String, Exposicion> exposiciones = new HashMap<>();
        HashMap<String, Cliente> clientes = new HashMap<>();
        ArrayList<Venta> registroVentas = new ArrayList<>();
        ArrayList<Prestamo> registroPrestamos = new ArrayList<>();
        ArrayList<Subasta> registroSubastas = new ArrayList<>();
        
        MenuPrincipal.menuPrincipal(obras, artistas, exposiciones, registroVentas, clientes, registroPrestamos, registroSubastas);
        
    }
    
}