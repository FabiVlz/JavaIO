package com.mycompany.ejemplo_io;
/**
 *
 * @author Jesus Fabian
 */
public class Ejemplo_IO {

    public static void main(String[] args) {
        ArchivoIO aio = new ArchivoIO();
        System.out.println("\n--- Usuarios ---");
        aio.escribir("676767 - TungTungSahur");
        aio.escribir("281484 - Jesus Fabian");
        aio.escribir("151781 - Emiliano");
        
        System.out.println("\n--- Actualizar ---");
        aio.actualizar("281484", "281818 - Bilsox");
        
        System.out.println("\n--- Eliminar ---");
        aio.eliminar("676767");
        
        System.out.println("\n--- Estado Final del Archivo ---");
        aio.leer();
    }
}