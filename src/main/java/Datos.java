/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anto
 */
import java.util.*;

public class Datos {
    // Se guardan las mismas colecciones que se cargan en el main
    public static HashMap<String, Obra> obras;
    public static HashMap<String, Artista> artistas;
    public static HashMap<String, Exposicion> exposiciones;
    public static HashMap<String, Cliente> clientes;
    public static ArrayList<Venta> ventas;
    public static ArrayList<Prestamo> prestamos;
    public static ArrayList<Subasta> subastas;

    // Método que se le agrega a cada ventana para que, al cerrarla con la X,
    // se cierre la subasta activa y se guarden los archivos (lo mismo que hace el main al terminar)
    public static void guardarAlCerrar(javax.swing.JFrame ventana){
        ventana.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                // si los datos no se cargaron (por ejemplo, si se ejecutó la ventana sola), no se guarda nada
                if (obras == null) return;
                MenuSubastas.cerrarSubastaActiva(subastas, clientes);
                ManejoArchivos.guardarArchivos(prestamos, ventas, clientes, exposiciones, obras);
            }
        });
    }
}
