/**
 * @archivo: Exposicion.java
 * @Proyecto: Shaolin Art Manager 
 * @Descripción: Clase para declarar los atributos y métodos del objeto Exposición
 * @author Alexia Gallardo
 * @Lenguaje: Java
*/

import java.util.*;
import java.time.*;
import javax.swing.table.DefaultTableModel;

public class Exposicion {
    //Atributos Exposicion
    private String id;
    private String titulo;
    private LocalDate fechaInicio;
    private LocalDate fechaTermino;
    private ArrayList<Obra> listaObras;
    //  Constructores
    public Exposicion(){
        id= null;
        titulo= "Sin Titulo";
        fechaInicio= null;
        fechaTermino= null;
        listaObras = new ArrayList<>();
    }
    public Exposicion(String id, String titulo, LocalDate fechaInicio,
                      LocalDate fechaTermino, Obra obra){
        this.id = id;
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        listaObras = new ArrayList<>();
        if(obra != null){
        //Se añade la primera obra 
            this.listaObras.add(obra);
        }
    }
    public Exposicion(String id, String titulo, String fechaInicio,
                      String fechaTermino, Obra obra){
        this.id= id;
        this.titulo= titulo;
        this.fechaInicio = LocalDate.parse(fechaInicio);
        this.fechaTermino = LocalDate.parse(fechaTermino);
        listaObras = new ArrayList<>();
        if(obra != null){
        //Se añade la primera obra 
        this.listaObras.add(obra);
        }
    }
    //  Metodos GET 
    public String getId(){ return id; }
    public String getTitulo(){ return titulo; }
    public LocalDate getfechaInicio(){ return fechaInicio; }
    public LocalDate getfechaTermino(){ return fechaTermino; }
    //  Metodos SET 
    public void setId(String id){ this.id= id; }
    public void setTitulo(String titulo){ this.titulo= titulo; }
    //Si se ingresa una variable de tipo LocalDate para fechaInicio
    public void setFechaInicio(LocalDate fechaInicio){ this.fechaInicio= fechaInicio; }
    //Si se ingresa una variable de tipo String para fechaInicio
    public void setFechaInicio(String fechaInicio){ 
        this.fechaInicio = LocalDate.parse(fechaInicio);
    }
    //Si se ingresa una variable de tipo LocalDate para fechaTermino
    public void setFechaTermino(LocalDate fechaTermino){ this.fechaTermino= fechaTermino; }
    //Si se ingresa una variable de tipo String para fechaTermino
    public void setFechaTermino(String fechaTermino){ 
        this.fechaTermino = LocalDate.parse(fechaTermino);
    }
    //Añadir obra a la lista
    public boolean anadirObra(Obra obra){
        //Si la obra ya se encuentra en la exposicion, se da un aviso y se retorna
        if ( listaObras.contains(obra) == true){
            return false;
        }
        listaObras.add(obra);
        return true;
    }
    //Eliminar obra de la lista
    public boolean eliminarObra(Obra obra){
        //Si la obra NO se encuentra en la exposicion, se da un aviso y se retorna
        if ( listaObras.contains(obra) == false){
            return false;
        }
        listaObras.remove(obra);
        return true;
    }
    //Vaciar lista 
    public boolean vaciarLista(){
        if ( listaObras.isEmpty() == true ){
            return false;
        }
        listaObras.clear(); 
        return true;
    }
    //Buscar una obra (saber si la obra se encuentra en la lista)   
    public boolean obraEstaEnLista(Obra obra){
        if ( listaObras.contains(obra) == true) return true;
        return false;
    }
    //Mostrar atributos
    public void mostrarAtributos(){
        System.out.printf("= ATRIBUTOS DE LA EXPOSICION '%s' =\n", titulo);
        System.out.println("ID: " + id);
        System.out.println("FECHA DE INICIO: " + fechaInicio);
        System.out.println("FECHA DE TERMINO: " + fechaTermino);
        //Mostrar obras
        System.out.println("OBRAS: ");
        if ( listaObras.isEmpty() == true ){
            System.out.printf("%s NO contiene obras.\n", titulo);
            return;
        }
        for (int i = 0 ; i < listaObras.size() ; i++){
            System.out.printf("%d. TITULO OBRA: '%s' | ARTISTA: %s \n", i+1, (listaObras.get(i)).getTitulo(), ((listaObras.get(i)).getArtista()).getNombre());
        }
        PresioneTeclaParaContinuar.ptpc();
    }
    
        //Se verifica que el rango de fechas sea correcto: el termino despues del inicio y con maximo un año de diferencia
    public static boolean rangoDeFechasValido(LocalDate inicio, LocalDate termino){
        if (inicio == null || termino == null) return false;
        if (termino.isBefore(inicio)) return false;
        //si al sumarle un año al inicio el resultado queda antes del termino, el rango supera el año límite por el que se puede mantener una exposición
        if (inicio.plusYears(1).isBefore(termino)) return false;
        return true;
    }
    //Cambiar el titulo de la exposicion validando que no sea vacio
    public boolean cambiarTitulo(String nuevoTitulo){
        if (nuevoTitulo == null || nuevoTitulo.trim().isEmpty()){
            return false;
        }
        this.titulo = nuevoTitulo.trim();
        return true;
    }
    //Cambiar la fecha de inicio validando el rango contra la fecha de termino actual
    public boolean cambiarFechaInicio(LocalDate nuevaFecha){
        if (!rangoDeFechasValido(nuevaFecha, fechaTermino)){
            return false;
        }
        this.fechaInicio = nuevaFecha;
        return true;
    }
    //Cambiar la fecha de termino validando el rango contra la fecha de inicio actual
    public boolean cambiarFechaTermino(LocalDate nuevaFecha){
        if (!rangoDeFechasValido(fechaInicio, nuevaFecha)){
            return false;
        }
        this.fechaTermino = nuevaFecha;
        return true;
    }
    
        //función usada en ventana para cambiar ambas fechas a la vez
    public boolean cambiarFechas(LocalDate nuevaInicio, LocalDate nuevaTermino){
        if (!rangoDeFechasValido(nuevaInicio, nuevaTermino)){
            return false;
        }
        this.fechaInicio = nuevaInicio;
        this.fechaTermino = nuevaTermino;
        return true;
    }
   
    public ArrayList<String> getIdsObras(){
        ArrayList<String> ids = new ArrayList<>();
        if(listaObras.size() == 0){
            return null;
        }
        for(int i =0; i < listaObras.size(); i++){
            Obra o = listaObras.get(i);
            ids.add(o.getId());        
        }
        return ids;
    }
        // Se crea una tabla con las obras de la exposición para ser mostrada por una Ventana
    public DefaultTableModel obtenerObras(){
        String[] columnas = {"ID", "TITULO", "ARTISTA", "ESTADO", "AÑO"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        for(int i = 0; i < listaObras.size(); i++){
            Object[] fila = {(listaObras.get(i)).getId(), (listaObras.get(i)).getTitulo(), ((listaObras.get(i)).getArtista()).getNombre(), (listaObras.get(i)).getEstado(), (listaObras.get(i)).getAnio()};
            modeloTabla.addRow(fila);
        }
        return modeloTabla;
    }
}
