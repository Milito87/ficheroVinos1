package org.izv.ad.jmunoz.ficherovinos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.izv.ad.jmunoz.ficherovinos.files.DeleteFile;
import org.izv.ad.jmunoz.ficherovinos.files.ReadFile;
import org.izv.ad.jmunoz.ficherovinos.files.WriteFile;
import java.util.List;

public class EditWine extends AppCompatActivity {

    private Button btEdit, btDelete;
    private TextView tvSeeId;
    private EditText etNameEdit, etCellarEdit, etColourEdit, etOriginEdit, etDegreesEdit, etDateEdit;
    private WriteFile writeFile;
    private ReadFile readFile;
    private DeleteFile deleteFile;
    private Bundle b;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vine);

        init();

        //recibimos el ID del vino a editar desde la actividad principal mediante Bundle
        id = Integer.parseInt(b.getString("valor"));

        //visualiza el Id del vino recibido en el TextViev
        tvSeeId.setText(String.valueOf(id));
        //carga los datos del vino a editar
        loadWineInfo();



        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveList(saveWine());
                Intent i = new Intent(EditWine.this, MainActivity.class);
                startActivity(i);
            }});

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveList(deleteWine());
                Intent i = new Intent(EditWine.this, MainActivity.class);
                startActivity(i);
            }});

    }

    public void init(){

        btEdit = findViewById(R.id.btEditVine);
        btDelete = findViewById(R.id.btDelete);
        tvSeeId = findViewById(R.id.tvSeeId);
        etCellarEdit = findViewById(R.id.etCellarEdit);
        etNameEdit = findViewById(R.id.etNameEdit);
        etCellarEdit = findViewById(R.id.etCellarEdit);
        etColourEdit = findViewById(R.id.etColourEdit);
        etOriginEdit = findViewById(R.id.etOriginEdit);
        etDegreesEdit = findViewById(R.id.etDegreesEdit);
        etDateEdit = findViewById(R.id.etYearEdit);

        //Bundle que recibe informacion de la actividad principal
        b = getIntent().getExtras();

        //creamos un objeto de la clase ReadFile
        readFile = new ReadFile();
    }

    //método que carga en los EditText los datos del vino a editar
    public void loadWineInfo(){
        //creamos una lista de vinos desde el archivo.csv
        List<Wine> w = readFile.readFile(getFilesDir());
        //si existe el vino carga en la posicion [i] de la lista de vinos los datos del vino
        if(searchWine() != -1) {
            int i = searchWine();
            etNameEdit.setText(w.get(i).getName());
            etCellarEdit.setText(w.get(i).getCellar());
            etColourEdit.setText(w.get(i).getColour());
            etOriginEdit.setText(w.get(i).getOrigin());
            etDegreesEdit.setText(String.valueOf(w.get(i).getDegrees()));
            etDateEdit.setText(String.valueOf(w.get(i).getDate()));
        }
    }

    //método que actualiza los datos del vino indicado
    public List<Wine> saveWine(){
        //creamos una lista de vinos desde el archivo.csv
        List<Wine> w = readFile.readFile(getFilesDir());
        //en la lista de vinos indica la posicion del vino a actualizar
        if(searchWine() != -1) {
            int i = searchWine();
            w.get(i).setName(etNameEdit.getText().toString());
            w.get(i).setCellar(etCellarEdit.getText().toString());
            w.get(i).setColour(etColourEdit.getText().toString());
            w.get(i).setOrigin(etOriginEdit.getText().toString());
            try {
                w.get(i).setDegrees(Double.parseDouble(etDegreesEdit.getText().toString()));
                w.get(i).setDate(Integer.parseInt(etDateEdit.getText().toString()));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return w;
    }

    //método que guarda la lista de vinos en archivo.csv
    public void saveList(List<Wine> w){
        //elimina el archivo.csv
        deleteFile = new DeleteFile();
        deleteFile.deleteFile(getFilesDir());
        writeFile = new WriteFile();
        //escribe la lista de vinos en un nuevo archivo.csv
        for(int i= 0;i < w.size();i++){
            writeFile.writeFile(getFilesDir(), Wine.writeWine(w.get(i)));
        }
    }

    //elimina el vino indicado de la lista de vinos
    public List<Wine> deleteWine(){
        //cargamos la lista de vinos desde el archivo.csv
        List<Wine> w =  readFile.readFile(getFilesDir());
        //si existe el vino buscado
        if(searchWine() != -1) {
            int i = searchWine();
            w.remove(i);
        }
        return w;
    }

    //método para buscar si existe un vino en la lista de vinos
    public int searchWine(){
        //carga la lista de vinos desde el archivo
        List<Wine> w = readFile.readFile(getFilesDir());

        for(int i=0;i< w.size();i++){
            if(id == w.get(i).getId()){
                return i;
            }
        }
        //cuando no existe el vino
        return -1;
    }

}