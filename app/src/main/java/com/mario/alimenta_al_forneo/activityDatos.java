package com.mario.alimenta_al_forneo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class activityDatos extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView recyclerViewLista;
    private AdaptadorDatos adaptadorLista;
    private BaseDeDatos base;
    private String [] arreglo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        base=new BaseDeDatos(getApplicationContext());
        recyclerViewLista = findViewById(R.id.recyclerLista);


        BaseDeDatos baseDeDatos = new BaseDeDatos (getApplicationContext());

        adaptadorLista = new AdaptadorDatos(baseDeDatos.mostrarLista());
        iniciaArreglo();
        recyclerViewLista.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arreglo));

        recyclerViewLista.setOnItemClickListener(this);

    }
    private void iniciaArreglo(){
        arreglo=new String [base.mostrarLista().size()];
        for(int i = 0;i<arreglo.length;i++){
            arreglo[i]=base.mostrarLista().get(i).getItem();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        base.eliminarItems((String)parent.getItemAtPosition(position));
        iniciaArreglo();
        recyclerViewLista.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arreglo));

    }
}
