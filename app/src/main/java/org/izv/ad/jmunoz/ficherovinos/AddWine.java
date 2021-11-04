package org.izv.ad.jmunoz.ficherovinos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.snackbar.Snackbar;
import org.izv.ad.jmunoz.ficherovinos.files.ReadFile;
import org.izv.ad.jmunoz.ficherovinos.files.WriteFile;
import java.util.List;

public class AddWine extends AppCompatActivity {

    private Button btAdd;
    private Intent i;
    private WriteFile writeFile;
    private ReadFile readFile;
    private EditText etId, etName, etCellar, etColour, etOrigin, etDegrees, etDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vine);

        init();

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //si existe el vino
               if(searchWine()){
                   Snackbar.make(view, "ID already exist ...", Snackbar.LENGTH_SHORT).show();
               }
               //si hay algun campo vacio
               else if(emptyField()){
                    Snackbar.make(view, "Any field is empty ...", Snackbar.LENGTH_SHORT).show();
               }
               else {
                   //añadimos el vino al archivo.csv y pasamos a la actividad principal
                   writeFile = new WriteFile();
                   writeFile.writeFile(getFilesDir(), Wine.writeWine(createWine()));

                   startActivity(i);

               }

            }});

    }

    public void init(){

        btAdd = findViewById(R.id.btAddWine);
        etId = findViewById(R.id.etIdAdd);
        //para quitar warning puse string en los EditText y aqui los vacio
        etId.setText("");
        etName = findViewById(R.id.etNameAdd);
        etName.setText("");
        etCellar = findViewById(R.id.etCellarAdd);
        etCellar.setText("");
        etColour = findViewById(R.id.etColourAdd);
        etColour.setText("");
        etOrigin = findViewById(R.id.etOriginAdd);
        etOrigin.setText("");
        etDegrees = findViewById(R.id.etDegreesAdd);
        etDegrees.setText("");
        etDate = findViewById(R.id.etYearAdd);
        etDate.setText("");

        //intent para ir a la actividad principal
        i = new Intent(AddWine.this, MainActivity.class);
        //creamos el objeto de la clase ReadFile;
        readFile = new ReadFile();
    }

    //método que devuelve si existe algun EditText vacio
    public boolean emptyField(){
        if(
                etId.getText().length() == 0 || etName.getText().length() == 0 ||
                etCellar.getText().length() == 0 ||  etColour.getText().length() == 0 ||
                etOrigin.getText().length() == 0 || etDegrees.getText().length() == 0 ||
                etDate.getText().length() == 0
        ){
           return true;
        }
        else{
            return false;
        }
    }

    //método que crea un objeto vino con los datos de los EditText
    public Wine createWine(){

        Long id = null; Double degrees = 0.0; int date = 0;
        try {
            id = Long.parseLong(etId.getText().toString());
            degrees = Double.parseDouble(etDegrees.getText().toString());
            date = Integer.parseInt(etDate.getText().toString());
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        String name = etName.getText().toString();
        String cellar = etCellar.getText().toString();
        String colour = etColour.getText().toString();
        String origin = etOrigin.getText().toString();

        //creamos el objeto vino con los datos de las variables
        Wine w = new Wine(id,name,cellar,colour,origin,degrees,date);

        return w;
    }

    //método que busca si ya existe la ID del vino a crear
    public boolean searchWine(){

        //cargamos la lista de vinos desde el archivo
        List<Wine> w = readFile.readFile(getFilesDir());

        for(int i= 0;i < w.size();i++){
            if(Long.parseLong(etId.getText().toString()) == w.get(i).getId()){
                return true;
            }
        }
    return false;
    }

}