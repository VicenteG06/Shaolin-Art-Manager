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

public class PresioneTeclaParaContinuar {
    public static void ptpc(){
        try {
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Presione ENTER Para Contiunar.....");
            lector.readLine();
        } catch (IOException e) {}
    }

}


