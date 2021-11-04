package org.izv.ad.jmunoz.ficherovinos.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile{
    //constante con el nombre del archivo que guarda la información
    public static final String fileName = "archivo.csv";

    private File file;
    private String wine;

    public void writeFile(File file, String wine) {

        //escribe en el archivo.csv una linea que contiene los datos del vino
        File f = new File(file, fileName);
        FileWriter fw = null;

        try {
            fw = new FileWriter(f, true);
            fw.write(wine);
            fw.write("\n");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}




