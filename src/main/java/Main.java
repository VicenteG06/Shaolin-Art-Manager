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
    public static void main(String[] args) throws IOException{
        HashMap<String, Obra> obras = CargarObras.cargarDesdeCsv("data/obras_de_arte.csv");
        HashMap<String, Artista> artistas = CargarObras.getMapaArtistas();
        HashMap<String, Exposicion> exposiciones;
        HashMap<String, Cliente> clientes;
        ArrayList<Venta> registroVentas;
        ArrayList<Prestamo> registroPrestamos;
                
        Menu.menuPrincipal(obras,artistas);
        
    }
    
}