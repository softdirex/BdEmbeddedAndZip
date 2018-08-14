
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jlleivas
 */
public class MainTest {
    public static void main(String[] args) throws IOException {
        compressAll();
    }
    
    private static void compressAll() throws FileNotFoundException, IOException{
        // cadena que contiene la ruta donde están los archivos a comprimir
        String directorioZip = "."+File.separator+"RSP";
        // ruta completa donde están los archivos a comprimir
        File carpetaComprimir = new File(directorioZip);

        // valida si existe el directorio
        if (carpetaComprimir.exists()) {
                // lista los archivos que hay dentro del directorio
                File[] ficheros = carpetaComprimir.listFiles();
                System.out.println("Número de ficheros encontrados: " + ficheros.length);

                // ciclo para recorrer todos los archivos a comprimir
                System.out.println("Nombre del fichero: " + directorioZip);
                // crea un buffer temporal para ir poniendo los archivos a comprimir
                ZipOutputStream zous = new ZipOutputStream(new FileOutputStream(carpetaComprimir.getName()+".zip"));

                //nombre con el que se va guardar el archivo dentro del zip
                ZipEntry entrada = new ZipEntry(carpetaComprimir.getName());
                zous.putNextEntry(entrada);
                System.out.println("Comprimiendo.....");
                //obtiene el archivo para irlo comprimiendo
                FileInputStream fis = new FileInputStream("."+File.separator+entrada);
                int leer;
                byte[] buffer = new byte[1024];
                while (0 < (leer = fis.read(buffer))) {
                        zous.write(buffer, 0, leer);
                }
                fis.close();
                zous.closeEntry();
                zous.close();		
                System.out.println("Directorio de salida: " + directorioZip);
        } else {
                System.out.println("No se encontró el directorio..");
        }
    }
    
    private static void compressByOne(){
        // cadena que contiene la ruta donde están los archivos a comprimir
        String directorioZip = "."+File.separator+"RSP"+File.separator;
        // ruta completa donde están los archivos a comprimir
        File carpetaComprimir = new File(directorioZip);

        // valida si existe el directorio
        if (carpetaComprimir.exists()) {
                // lista los archivos que hay dentro del directorio
                File[] ficheros = carpetaComprimir.listFiles();
                System.out.println("Número de ficheros encontrados: " + ficheros.length);

                // ciclo para recorrer todos los archivos a comprimir
                for (int i = 0; i < ficheros.length; i++) {
                        System.out.println("Nombre del fichero: " + ficheros[i].getName());
                        String extension="";
                        for (int j = 0; j < ficheros[i].getName().length(); j++) {
                                //obtiene la extensión del archivo
                                if (ficheros[i].getName().charAt(j)=='.') {
                                        extension=ficheros[i].getName().substring(j, (int)ficheros[i].getName().length());
                                        //System.out.println(extension);
                                }
                        }
                        try {
                                // crea un buffer temporal para ir poniendo los archivos a comprimir
                                ZipOutputStream zous = new ZipOutputStream(new FileOutputStream(directorioZip + ficheros[i].getName().replace(extension, ".zip")));

                                //nombre con el que se va guardar el archivo dentro del zip
                                ZipEntry entrada = new ZipEntry(ficheros[i].getName());
                                zous.putNextEntry(entrada);

                                        //System.out.println("Nombre del Archivo: " + entrada.getName());
                                        System.out.println("Comprimiendo.....");
                                        //obtiene el archivo para irlo comprimiendo
                                        FileInputStream fis = new FileInputStream(directorioZip+entrada.getName());
                                        int leer;
                                        byte[] buffer = new byte[1024];
                                        while (0 < (leer = fis.read(buffer))) {
                                                zous.write(buffer, 0, leer);
                                        }
                                        fis.close();
                                        zous.closeEntry();
                                zous.close();					
                        } catch (FileNotFoundException e) {
                                e.printStackTrace();
                        } catch (IOException ex) {				
                        Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
                    }				
                }
                System.out.println("Directorio de salida: " + directorioZip);
        } else {
                System.out.println("No se encontró el directorio..");
        }
    }
}
