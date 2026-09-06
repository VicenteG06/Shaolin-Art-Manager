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
    public static String generarID(String nombre){
        int id = 0;

        for(int i = 0; i < nombre.length(); i++){
            id += ((int) nombre.charAt(i)) * Math.pow(31, i);
        }
        
        String idString = "" + (id % 251);
        return idString;
    }
}
