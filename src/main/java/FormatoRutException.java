/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Netaxion
 */
public class FormatoRutException extends Exception {
    public FormatoRutException(){
        super("Error: El RUT ingresado es inválido");
    }
}
