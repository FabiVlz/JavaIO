package com.mycompany.ejemplo_io;

import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Jesus Fabian
 */
public class ArchivoIO {
   File archivo = new File("Alumnos_io.txt");
    List<String> lineas = new ArrayList<>();
    
    public void escribir(String Linea){
        leer();//Mantener actualizado
        lineas.add(Linea);
        // ESCRITURA
        try (BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))){
            for(String linea_saved : lineas){
                escritor.write(linea_saved);
                escritor.newLine();
            } 
            System.out.println("Archivo guardado con exito");
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }
    }
    
    public void leer(){
        //Limpiamos Arreglos para evitar duplicados
         // LECTURA
         lineas = new ArrayList();
        try (BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                lineas.add(linea);
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
    }
    
    public void eliminar(String idBuscado) {
        leer();

        if (idBuscado == null || idBuscado.length() != 6) {
            System.out.println("El ID debe contener exactamente 6 digitos");
            return;
        }
        boolean encontrado = false;
        encontrado = lineas.removeIf(linea -> {
            if (linea.length() >= 6) {
                String idLinea = linea.substring(0, 6);
                return idLinea.equals(idBuscado);
            }
            return false;
        });

        if (encontrado) {
            try (BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
                for (String lineaSaved : lineas) {
                    escritor.write(lineaSaved);
                    escritor.newLine();
                }
                System.out.println(idBuscado + " eliminado con exito.");
            } catch (IOException e) {
                System.out.println("Error al actualizar el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("No se encontro ningún registro con el ID: " + idBuscado);
        }
     }
        
    public void actualizar(String idBuscado, String nuevaLinea) {
        leer();

        if (idBuscado == null || idBuscado.length() != 6) {
            System.out.println("El ID debe contener exactamente 6 digitos");
            return;
        }

        boolean encontrado = false;
        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            if (linea.length() >= 6) {
                String idLinea = linea.substring(0, 6);
                if (idLinea.equals(idBuscado)) {
                    lineas.set(i, nuevaLinea); // Reemplaza la línea vieja por la nueva
                    encontrado = true;
                    break;
                }
            }
        }

        if (encontrado) {
            try (BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
                for (String lineaSaved : lineas) {
                    escritor.write(lineaSaved);
                    escritor.newLine();
                }
                System.out.println(idBuscado + " actualizado con exito.");
            } catch (IOException e) {
                System.out.println("Error al actualizar el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("No se encontro ningún registro con el ID: " + idBuscado);
        }
    }
}