package com.mario.alimenta_al_forneo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class activityDatos extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView recyclerViewLista;
    private AdaptadorDatos adaptadorLista;
    private BaseDeDatos base;
    private String[] arreglo;
    private int[] arregloID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        base = new BaseDeDatos(getApplicationContext());
        recyclerViewLista = findViewById(R.id.recyclerLista);


        BaseDeDatos baseDeDatos = new BaseDeDatos(getApplicationContext());

        adaptadorLista = new AdaptadorDatos(baseDeDatos.mostrarLista());
        iniciaArreglo();
        actualizarBD();
        iniciaArreglo();
        recyclerViewLista.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arreglo));

        recyclerViewLista.setOnItemClickListener(this);


    }

    private void iniciaArreglo() {
        arreglo = new String[base.mostrarLista().size()];
        arregloID = new int[base.mostrarLista().size()];

        for (int i = 0; i < arreglo.length; i++) {
            arregloID[i] = base.mostrarLista().get(i).getId();
            arreglo[i] = base.mostrarLista().get(i).getItem() + arregloID[i];
        }
    }

    private void actualizarBD() {
        for (int i = 0; i < base.mostrarLista().size(); i++) {
            base.actualizarBD(i, arregloID[i]);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.wtf("a", "" + position);
        //Para que no sea null los arreglos
        iniciaArreglo();
        //Eliminar un item segun la posicion del index
        base.eliminarItems(arregloID[position]);
        //Volver a actualizar los arreglos para actualizar la base de datos
        iniciaArreglo();
        //Actualizar la base de datos
        actualizarBD();
        //Volver a actualizar los arreglos para mostrar en pantalla
        iniciaArreglo();


        String value = (String) parent.getItemAtPosition(position);
        switch (value) {
            case "Curitas":
                Toast.makeText(this, "Haz usado curitas", Toast.LENGTH_SHORT).show();
                break;
            case "Pepto bismol":
                Toast.makeText(this, "Haz tomado un pepto", Toast.LENGTH_SHORT).show();
                break;
            case "Maruchan Medicinal":
                Toast.makeText(this, "Comiste una marucha medicinal", Toast.LENGTH_SHORT).show();
                break;
            case "Aspirinas":
                Toast.makeText(this, "Haz tomado unas aspirinas", Toast.LENGTH_SHORT).show();
                break;
            case "Fourloko Medicinal":
                Toast.makeText(this, "Haz tomado un fourloko medicinal", Toast.LENGTH_SHORT).show();
                break;
            case "Papitas":
                Toast.makeText(this, "Comiste unas papitas", Toast.LENGTH_SHORT).show();
                break;
            case "Caguamon":
                Toast.makeText(this, "Haz tomado un caguamon ", Toast.LENGTH_SHORT).show();
                break;
            case "Maruchan":
                Toast.makeText(this, "Comiste una maruchan", Toast.LENGTH_SHORT).show();
                break;
            case "Atun":
                Toast.makeText(this, "Comiste una lata de atun", Toast.LENGTH_SHORT).show();
                break;
            case "Nito bimbo":
                Toast.makeText(this, "Comiste un nito bimbo", Toast.LENGTH_SHORT).show();
                break;
            case "Fourloko":
                Toast.makeText(this, "Haz tomado un fourloko", Toast.LENGTH_SHORT).show();
                break;
            case "Coca cola":
                Toast.makeText(this, "Haz tomado una coca cola", Toast.LENGTH_SHORT).show();
                break;
            case "Agua":
                Toast.makeText(this, "Haz tomado una botella de agua", Toast.LENGTH_SHORT).show();
                break;
        }
        recyclerViewLista.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arreglo));

    }
}
