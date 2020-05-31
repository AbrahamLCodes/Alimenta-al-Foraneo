package com.mario.alimenta_al_forneo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CostumeDialog.CostumeDialogInterface, NavigationView.OnNavigationItemSelectedListener {
    private static ImageView foraneo;
    private ImageButton ajustes, inventario, otso, salud;
    private TextView txtdinero, txtfelicidad, txthambre, txtvida, txtnombre;
    private DrawerLayout mDrawerLayout;
    private NavigationView naviView;
    private MenuItem item_curitas, item_pepto, item_maruchan_medicinal, item_aspirinas, item_fourloko,
            item_papitas, item_caguamon, item_maruchan, item_atun, item_nito, item_loko, item_cola, item_agua;
    private ProgressBar vidaBar,felcidadBar,hambreBar;
    public SoundPool sp;
    public static int vidaPoints,felicidadPoints,dinerocount,hambrePoints;

    public int flujoDeMusia = 0;
    public boolean isRunning0 = true;
    public boolean isRunning3 =true;
    public boolean isRunning2 = true;

    private static int IMAGEN_RECURSO;

    /* Curar vida */
    public static void setVidaPoints(int vidaPoints) {
        MainActivity.vidaPoints=MainActivity.vidaPoints+vidaPoints;

    }

    /* Felicidad*/
    public static void setFelicidadPoints(int felicidadPoints){
        MainActivity.felicidadPoints=MainActivity.felicidadPoints+felicidadPoints;
    }

    /*Hambre*/
    public static void setHambrePoints(int hambrePoints){
        MainActivity.hambrePoints=MainActivity.hambrePoints+hambrePoints;
    }

    //-----------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        vidaPoints =100;
        felicidadPoints=100;
        hambrePoints =100;


        //Sonido
        sp = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        flujoDeMusia = sp.load(this, R.raw.click, 1);
        //Navaigation Drawer
        naviView = findViewById(R.id.naviVew);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        naviView.setNavigationItemSelectedListener(this);

        /* images bottons */

        ajustes = findViewById(R.id.imgbtn_ajustes);
        ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        inventario = findViewById(R.id.img_btn_Inv);
        otso = findViewById(R.id.img_btn_otso);
        salud = findViewById(R.id.img_btn_farmacia);
        salud.setOnClickListener(this);

        /* txt views */
        txtnombre = findViewById(R.id.txt_v_nombre);
        txtdinero = findViewById(R.id.txt_view_dinero);
        txtfelicidad = findViewById(R.id.txt_v_happy);
        txthambre = findViewById(R.id.txt_v_comida);
        txtvida = findViewById(R.id.txt_v_vida);

        /* progressbars */
         felcidadBar = findViewById(R.id.progressBar_felcidad);
         vidaBar = findViewById(R.id.progressBar_vida);
         hambreBar = findViewById(R.id.progressBar_hambre);

        /* images */

        foraneo = findViewById(R.id.img_foraneo);


        /*Items farmacia*/
        item_curitas = findViewById(R.id.item_curitas);
        item_pepto = findViewById(R.id.item_pepto);
        item_maruchan_medicinal = findViewById(R.id.item_maruchan_medicinal);
        item_aspirinas = findViewById(R.id.item_aspirinas);
        item_fourloko = findViewById(R.id.item_fourloko);


        /*Items tienda Otso*/
        item_papitas = findViewById(R.id.item_papitas);
        item_caguamon = findViewById(R.id.item_caguamon);
        item_maruchan = findViewById(R.id.item_maruchan);
        item_atun = findViewById(R.id.item_atun);
        item_nito = findViewById(R.id.item_nito);
        item_loko = findViewById(R.id.item_loko);
        item_cola = findViewById(R.id.item_cola);
        item_agua = findViewById(R.id.item_agua);

        new hilodinero().start();
        new hilohambre().start();
        new hilofelicidad().start();
        new hilovida().start();



        //Comprobar que si la app corrio por primera vez para asignarle los valores
         BaseDeDatos baseDeDatos = new BaseDeDatos(getApplicationContext());
        if (baseDeDatos.firstRun()==0) {
            vidaPoints=100;
            felicidadPoints=100;
            hambrePoints=100;
            dinerocount=0;
            IMAGEN_RECURSO=R.drawable.foraneoocho;

            foraneo.setImageResource(IMAGEN_RECURSO);
            txtnombre.setText("Paquito");
            baseDeDatos.setCorrido();
        }else {
            vidaPoints=baseDeDatos.getStats()[0];
            felicidadPoints=baseDeDatos.getStats()[1];
            hambrePoints=baseDeDatos.getStats()[2];
            dinerocount=baseDeDatos.getStats()[3];
            foraneo.setImageResource(Integer.parseInt(baseDeDatos.getForaneo()[1]));
            txtnombre.setText(baseDeDatos.getForaneo()[0]);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    class hilovida extends Thread{
        @Override
        public void run() {
            super.run();

            while(isRunning0){
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(vidaPoints <= 0){
                            vidaBar.setProgress(0);
                            isRunning0=false;
                            foraneo.setImageResource(R.drawable.foraneotriste);
                        }
                        else{
                            if(hambrePoints == 0){
                                vidaPoints--;
                            }
                            if(felicidadPoints < 30){
                                vidaPoints--;
                            }
                            vidaBar.setProgress(vidaPoints);
                            txtvida.setText(String.valueOf(vidaPoints+"%"));
                        }
                    }
                });


            }
        }
    }

    class hilodinero extends Thread{
        @Override
        public void run() {
            super.run();



            for(int i=0; i<=100;i++){
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();

            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dinerocount++;
                    txtdinero.setText(String.valueOf("$"+dinerocount));


                }

            });
        }


        }
    }



    class hilohambre extends Thread{
        @Override
        public void run() {
            super.run();
            final BaseDeDatos baseDeDatos = new BaseDeDatos(getApplicationContext());

            while(isRunning2){
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(hambrePoints == 0){
                            isRunning2 =false;
                            vidaPoints--;
                        }
                        else{
                            if(hambrePoints < 50){
                                felicidadPoints--;
                            }
                            hambrePoints--;
                            hambreBar.setProgress(hambrePoints);
                            txthambre.setText(String.valueOf(hambrePoints+"%"));
                        }
                    }
                });


            }
        }
    }

    class hilofelicidad extends Thread{
        @Override
        public void run() {
            super.run();
            while(isRunning3){
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(felicidadPoints == 0){
                            isRunning3 =false;
                            vidaPoints--;
                        }
                        else{
                            if(vidaPoints < 60){
                                felicidadPoints--;
                            }
                            felcidadBar.setProgress(felicidadPoints);
                            txtfelicidad.setText(String.valueOf(felicidadPoints+"%"));
                        }
                    }
                });


            }
        }
    }

    protected void onResume() {
        super.onResume();

    }

    public static void setForaneo(int f) {

        foraneo.setImageResource(f);
        IMAGEN_RECURSO=f;
    }

    private void openDialog() {
        CostumeDialog costumeDialog = new CostumeDialog();
        costumeDialog.show(getSupportFragmentManager(), "Costume Dialog");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_farmacia:
                play_sp();
                naviView.getMenu().clear();
                naviView.inflateMenu(R.menu.navigation_menu);
                naviView.removeHeaderView(naviView.getHeaderView(0));
                naviView.inflateHeaderView(R.layout.header_layout2);
                mDrawerLayout.openDrawer(naviView);
                break;
            case R.id.img_btn_Inv:
                Intent mostrarDatos = new Intent(getApplicationContext(),activityDatos.class);
                startActivity(mostrarDatos);

                break;
            case R.id.img_btn_otso:
                play_sp();
                naviView.getMenu().clear();
                naviView.inflateMenu(R.menu.navigation_menu3);
                naviView.removeHeaderView(naviView.getHeaderView(0));
                naviView.inflateHeaderView(R.layout.header_layout);
                mDrawerLayout.openDrawer(naviView);
                break;
        }
    }

    private void play_sp() {
        sp.play(flujoDeMusia, 1, 1, 0, 0, 1);
    }

    @Override
    public void apllytext(String Name) {
        txtnombre.setText(Name);

    }


    private void comprarItem(String comprado, String rechazado, int precio){

        if(dinerocount >= precio){
            Toast.makeText(this,comprado,Toast.LENGTH_SHORT).show();
            dinerocount = dinerocount - precio;
            txtdinero.setText("$"+dinerocount);
        }else{
            Toast.makeText(this, rechazado, Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public boolean onNavigationItemSelected (@NonNull MenuItem item) {
        final BaseDeDatos baseDeDatos = new BaseDeDatos(getApplicationContext());
        switch (item.getItemId()) {
            case R.id.item_curitas:
                comprarItem("Haz comprado curitas","No tienes suficiente dinero",5);
                Log.wtf("a",""+baseDeDatos.mostrarLista().size());
                baseDeDatos.agregarItems(getResources().getString(R.string.titulo_curitas),baseDeDatos.mostrarLista().size());
                return true;
            case R.id.item_pepto:
                comprarItem("Haz comprado un pepto bismol","No tienes suficiente dinero",10);
                baseDeDatos.agregarItems(getResources().getString(R.string.titulo_pepto),baseDeDatos.mostrarLista().size());
                return true;
            case R.id.item_maruchan_medicinal:
                comprarItem("Haz comprado una maruchan medicinal","No tienes suficiente dinero" ,8);
                baseDeDatos.agregarItems(getResources().getString(R.string.titulo_maruchan),baseDeDatos.mostrarLista().size());
                return true;
            case R.id.item_fourloko:
                comprarItem("Haz comprado un four loko medicinal","No tienes suficiente dinero", 12);
                baseDeDatos.agregarItems(getResources().getString(R.string.titulo_fourloko),baseDeDatos.mostrarLista().size());
                return true;
            case R.id.item_aspirinas:
                comprarItem("Haz comprado aspirinas","No tienes suficiente dinero",15);
                baseDeDatos.agregarItems(getResources().getString(R.string.titulo_aspirinas),baseDeDatos.mostrarLista().size());
                return true;
            case R.id.item_papitas:
                comprarItem("Haz comprado unas papitas","No tienes suficiente dinero",11);
                baseDeDatos.agregarItems(getResources().getString(R.string.titulo_papitas),baseDeDatos.mostrarLista().size());
                return true;
            case R.id.item_caguamon:
                comprarItem("Haz comprado un caguamon","No tienes suficiente dinero",20);
                baseDeDatos.agregarItems(getResources().getString(R.string.titulo_caguamon),baseDeDatos.mostrarLista().size());
                return true;
            case R.id.item_maruchan:
                comprarItem("Haz comprado una maruchan","No tienes suficiente dinero",6);
                baseDeDatos.agregarItems(getResources().getString(R.string.titulo_maruchan2),baseDeDatos.mostrarLista().size());
                return true;
            case R.id.item_atun:
                comprarItem("Haz comprado una lata de atun","No tienes suficiente dinero",14);
                baseDeDatos.agregarItems(getResources().getString(R.string.titulo_atun),baseDeDatos.mostrarLista().size());
                return true;
            case R.id.item_nito:
                comprarItem("Haz comprado un nito","No tienes suficiente dinero",13);
                baseDeDatos.agregarItems(getResources().getString(R.string.titulo_nito),baseDeDatos.mostrarLista().size());
                return true;
            case R.id.item_loko:
                comprarItem("Haz comprado un four loko","No tienes suficiente dinero",15);
                baseDeDatos.agregarItems(getResources().getString(R.string.titulo_loko),baseDeDatos.mostrarLista().size());
                return true;
            case R.id.item_cola:
                comprarItem("Haz comprado una coca cola","No tienes suficiente dinero",10);
                baseDeDatos.agregarItems(getResources().getString(R.string.titulo_cola),baseDeDatos.mostrarLista().size());
                return true;
            case R.id.item_agua:
                comprarItem("Haz comprado un agua","No tienes suficiente dinero",5);
                Log.wtf("a",""+baseDeDatos.mostrarLista().size());
                baseDeDatos.agregarItems(getResources().getString(R.string.titulo_agua),baseDeDatos.mostrarLista().size());
                return true;


            default:
                return false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        final BaseDeDatos baseDeDatos = new BaseDeDatos(getApplicationContext());
        baseDeDatos.agregarStats(vidaPoints,felicidadPoints,hambrePoints,dinerocount);

        baseDeDatos.agregarForaneo(txtnombre.getText().toString(),IMAGEN_RECURSO);

    }
}
