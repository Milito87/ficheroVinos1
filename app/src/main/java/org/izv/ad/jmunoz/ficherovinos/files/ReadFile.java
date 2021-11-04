package org.izv.ad.jmunoz.ficherovinos.files;

import org.izv.ad.jmunoz.ficherovinos.Wine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class ReadFile{

    //obtiene una lista de vinos desde el archivo.csv
    public List<Wine> readFile(File file){
        String linea = "";
        List<Wine> winelist = new ArrayList<>();
        File f = new File(file, WriteFile.fileName);
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            while ((linea = br.readLine()) != null) {
                winelist.add(Wine.readWine(linea));
            }
            br.close();
        } catch(IOException e) {
        }
        return winelist;
    }
}





