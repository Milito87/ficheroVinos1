package org.izv.ad.jmunoz.ficherovinos.files;

import java.io.File;

public class DeleteFile{

    private File file;

    public void deleteFile(File file) {

        //elimina el archivo.csv
        File f = new File(file, WriteFile.fileName);
        f.delete();
    }
}
