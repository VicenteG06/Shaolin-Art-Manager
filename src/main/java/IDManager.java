/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vicente Gamboa
 */

import java.util.*;

public class IDManager {
    public static String generarID(String nombre, HashMap<String, Object> mapa){
        int id;
        String idString;

        do{
            for(int i = 0; i < nombre.length(); i++){
                id += ((int) nombre.charAt[0]) * (31 ** i);
            }
        
            idString = "" + (id % 251);
        } while(mapa.containsKey(idString));
        return idString;
    }
}
