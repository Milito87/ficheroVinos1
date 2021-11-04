package org.izv.ad.jmunoz.ficherovinos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import org.izv.ad.jmunoz.ficherovinos.files.ReadFile;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btEdit, btAdd;
    private EditText etId;
    private TextView tvText;
    private ReadFile readFile;
    private List<Wine> wineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        //si existe información visualizala en el TextView
        if(wineList.size() > 0) {
            tvText.setText(seeList(wineList));
        }
        else {
            //no existe información lista vacia
            tvText.setText("Empty list Wine");
        }

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //si el EditText contiene caracteres
                if(etId.length() > 0) {
                    //si existe el vino que queremos editar (ID)
                    if(existWine(wineList)) {
                        //abrimos actividad de editar vinos
                        openActivity();
                    }
                    else{
                        //no existe el vino que queremos editar
                        Snackbar.make(view,"ID NOT EXIST ...", Snackbar.LENGTH_SHORT).show();
                    }
                }
            }});

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //abrimos actividad de añadir vinos
                Intent i = new Intent(MainActivity.this, AddWine.class);
                startActivity(i);
            }});

    }

    public void init(){

        tvText = findViewById(R.id.tvText);
        btEdit = findViewById(R.id.btEdit);
        btAdd = findViewById(R.id.btAdd);
        etId = findViewById(R.id.etId);

        //creamos objeto de la clase ReadFile
        readFile = new ReadFile();

        //cargamos la lista de vinos desde el archivo.csv
        wineList = readFile.readFile(getFilesDir());
    }

    //método que guarda la ID del vino a editar y pasa a la actividad de edicion
    public void openActivity(){
        Intent i = new Intent(this, EditWine.class);

        Bundle b = new Bundle();
        b.putString("valor", etId.getText().toString());
        i.putExtras(b);
        startActivity(i);
    }

    //método para visualizar una lista de vinos
    public String seeList(List<Wine> w) {
        String text = "";
        for(int i = 0;i < w.size();i++) {
            text += "\nWINE " + w.get(i).getName() + " ID " + w.get(i).getId() + '\n' +
                    "CELLAR " + w.get(i).getCellar() + " ORIGIN " + w.get(i).getOrigin() + '\n' +
                    "COLOUR " + w.get(i).getColour() + " DEGREES " + w.get(i).getDegrees() + " DATE " + w.get(i).getDate() + '\n';
        }
        return text;
    }

    //método para comprobar la existencia de un vino
    public  boolean existWine(List<Wine> w){
        for(int i=0;i <w.size();i++){
            if(w.get(i).getId() == Long.parseLong(etId.getText().toString())){
                return true;
            }
        }
        return false;
    }

}