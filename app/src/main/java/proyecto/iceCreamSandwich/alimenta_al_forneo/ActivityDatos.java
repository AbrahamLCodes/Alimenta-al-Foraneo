package proyecto.iceCreamSandwich.alimenta_al_forneo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mario.alimenta_al_forneo.R;

public class ActivityDatos extends AppCompatActivity implements AdapterView.OnItemClickListener {
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

    @Override
    protected void onResume() {
        super.onResume();
    }



    private void iniciaArreglo() {
        arreglo = new String[base.mostrarLista().size()];
        arregloID = new int[base.mostrarLista().size()];

        for (int i = 0; i < arreglo.length; i++) {
            arregloID[i] = base.mostrarLista().get(i).getId();
            arreglo[i] = base.mostrarLista().get(i).getItem();
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
            //items de salud
            case "Curitas":
                if(MainActivity.vidaPoints >=100){
                Toast.makeText(this, "vida maxima", Toast.LENGTH_SHORT).show();
                break;
            }else{
                MainActivity.setVidaPoints(5);
                Toast.makeText(this, "Haz usado curitas", Toast.LENGTH_SHORT).show();
                break;
            }
            case "Pepto bismol":
                if(MainActivity.vidaPoints >=100){
                    Toast.makeText(this, "vida maxima", Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    MainActivity.setVidaPoints(8);
                    Toast.makeText(this, "Haz tomado un pepto", Toast.LENGTH_SHORT).show();
                    if(MainActivity.vidaPoints >100){
                        MainActivity.setVidaPoints(-8);
                    }
                    break;
                }
            case "Maruchan Medicinal":
                Toast.makeText(this, "Comiste una marucha medicinal", Toast.LENGTH_SHORT).show();
                MainActivity.setVidaPoints(10);
                MainActivity.setHambrePoints(10);
                break;
            case "Aspirinas":
                if(MainActivity.vidaPoints >=100){
                    Toast.makeText(this, "vida maxima", Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    MainActivity.setVidaPoints(12);
                    Toast.makeText(this, "Haz tomado unas aspirinas", Toast.LENGTH_SHORT).show();
                    break;
                }
            case "Fourloko Medicinal":
                if(MainActivity.hambrePoints >100){
                    Toast.makeText(this, "vida al maximo", Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    Toast.makeText(this, "Haz tomado un fourloko medicinal", Toast.LENGTH_SHORT).show();
                    MainActivity.setVidaPoints(15);
                    MainActivity.setHambrePoints(5);
                    break;
                }
                //items de hambre
            case "Papitas":
            if(MainActivity.hambrePoints >=100){
                Toast.makeText(this, "hambre al maximo", Toast.LENGTH_SHORT).show();
                break;
            }else{
                MainActivity.setHambrePoints(15);
                Toast.makeText(this, "has comido papitas ", Toast.LENGTH_SHORT).show();
                break;
            }
            case "Caguamon":
                Toast.makeText(this, "Haz tomado un caguamon ", Toast.LENGTH_SHORT).show();
                MainActivity.setHambrePoints(10);
                MainActivity.setFelicidadPoints(15);
                MainActivity.setVidaPoints(-5);
                break;
            case "Maruchan":
                if(MainActivity.hambrePoints >=100){
                    Toast.makeText(this, "hambre al maximo", Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    MainActivity.setHambrePoints(11);
                    Toast.makeText(this, "Comiste una maruchan", Toast.LENGTH_SHORT).show();
                    break;
                }
            case "Atun":
                if(MainActivity.hambrePoints >=100){
                    Toast.makeText(this, "hambre al maximo", Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    MainActivity.setHambrePoints(9);
                    Toast.makeText(this, "Comiste una lata de atun", Toast.LENGTH_SHORT).show();
                    break;
                }
            case "Nito bimbo":
                if(MainActivity.hambrePoints < 100){
                    MainActivity.setHambrePoints(14);
                    MainActivity.setFelicidadPoints(5);
                    Toast.makeText(this, "comiste 1 negrito", Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    Toast.makeText(this, "hambre al maximo", Toast.LENGTH_SHORT).show();
                    break;
                }
            case "Fourloko":
                Toast.makeText(this, "Haz tomado un zuculento fourloko", Toast.LENGTH_SHORT).show();
                MainActivity.setHambrePoints(8);
                MainActivity.setFelicidadPoints(10);
                break;
            case "Coca cola":
                Toast.makeText(this, "Haz tomado una coca cola", Toast.LENGTH_SHORT).show();
                MainActivity.setHambrePoints(12);
                MainActivity.setFelicidadPoints(5);
                break;
            case "Agua":
                if (MainActivity.hambrePoints > 100){
                    Toast.makeText(this, "hambre al maximo", Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    Toast.makeText(this, "Haz tomado una botella de agua", Toast.LENGTH_SHORT).show();
                    MainActivity.setHambrePoints(5);
                    break;
                }

        }
        recyclerViewLista.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arreglo));

    }

}
