/**
 * @archivo: Validaciones.java
 * @Proyecto: Shaolin Art Manager 
 * @Descripción: Clase para manejar excepciones
 * @author Antonia Avello
 * @Lenguaje: Java
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Validaciones {

    //pedir y validar ruts
    public static String pedirRut(BufferedReader lector, String mensaje) throws IOException {
        String rut = "";
        boolean rutValido = false;
        while (!rutValido) {
            System.out.println(mensaje);
            rut = lector.readLine().trim();
            
            // Si el usuario presiona 0 y enter, se termina el ciclo y se devuelve al menú para no quedar en loop infinito
            if (rut.equals("0")) {
                return rut; 
            }
            
            try {
                if (rut.isEmpty() || rut.length() < 7 || rut.length() > 8) {
                    throw new FormatoRutException();
                }
                Integer.parseInt(rut);
                rutValido = true;
            } catch (FormatoRutException e) {
                System.out.println(e.getMessage());
            } catch(NumberFormatException e){  
                System.out.println("Ingrese un RUT compuesto únicamente por números");
            }
        }
        char dv = ' ';
        int suma = 0;
        int multiplicador = 2;
        int rutInt= Integer.parseInt(rut);
        // Recorrer los dígitos del RUT de derecha a izquierda
        while (rutInt > 0) {
            int digito = rutInt % 10;
            suma += digito * multiplicador;
            rutInt /= 10;
            
            multiplicador++;
            if (multiplicador > 7) {
                multiplicador = 2; // Reiniciar el ciclo de factores (2 al 7)
            }
        }
        
        // Aplicar la fórmula del módulo 11
        int resto = suma % 11;
        int resultado = 11 - resto;
        
        // Casos especiales
        if (resultado == 11) {
            dv=  '0';
        } else if (resultado == 10) {
            dv = 'K';
        } else {
            dv= Character.forDigit(resultado, 10);
        }
        
        return rut+dv ;
    }

    // Pedir fechas y validar errores
    public static LocalDate pedirFecha(BufferedReader lector, String mensaje) throws IOException {
        LocalDate fecha = null; //Se inicia la fecha en NULL, para cambiarla solo si se ingresa una fecha válida en el formato válido
        while (fecha == null) { 
            System.out.println(mensaje);
            String entrada = lector.readLine().trim(); 
            try { 
                fecha = LocalDate.parse(entrada); 
            } catch(DateTimeParseException | NullPointerException e){
                System.out.println("Error: Ingrese una fecha válida respetando el formato AAAA-MM-DD");
            }
        }
        return fecha;
    }

    // Método para pedir números enteros POSITIVOS (precios, años) al usuario y validarlos
    public static int pedirEnteroPositivo(BufferedReader lector, String mensaje) throws IOException {
        int numero = -1;
        while (numero < 0) {
            System.out.println(mensaje);
            try {
                numero = Integer.parseInt(lector.readLine().trim());
                if (numero < 0) {
                    System.out.println("Error: El número no puede ser negativo. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese solo números enteros válidos. No se aceptan letras ni símbolos.");
            }
        }
        return numero;
    }
}