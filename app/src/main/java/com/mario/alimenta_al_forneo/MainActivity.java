package com.mario.alimenta_al_forneo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    private int vidaPoints,felicidadPoints,dinerocount, valordinero, precioitem;
    private int hambrePoints;
    public int flujoDeMusia = 0;
    public boolean isRunning0 = true;
    public boolean isRunning3 =true;
    public boolean isRunning2 = true;






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
                    Thread.sleep(1000);
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
            dinerocount=0;


            for(int i=0; i<=100;i++){
            try {
                Thread.sleep(2000);

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
            while(isRunning2){
                try {
                    Thread.sleep(500);
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
                    Thread.sleep(500);
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
                play_sp();
                naviView.getMenu().clear();
                naviView.inflateMenu(R.menu.navigation_menu2);
                naviView.removeHeaderView(naviView.getHeaderView(0));
                naviView.inflateHeaderView(R.layout.header_layout3);
                mDrawerLayout.openDrawer(naviView);
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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_curitas:
                if(dinerocount>=5){
                    Toast.makeText(this, "Has comprado curitas", Toast.LENGTH_SHORT).show();
                    dinerocount=dinerocount-5;
                    txtdinero.setText(String.valueOf("$"+dinerocount));


                }else{
                    Toast.makeText(this, "No tienes suficiente dinero", Toast.LENGTH_SHORT).show();
                }
                txtdinero.setText(String.valueOf("$"+dinerocount));
                return true;
            case R.id.item_pepto:
                if(dinerocount>=10){
                    Toast.makeText(this, "Has comprado un pepto bismol", Toast.LENGTH_SHORT).show();
                    dinerocount=dinerocount-10;
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }else{
                    Toast.makeText(this, "No tienes suficiente dinero", Toast.LENGTH_SHORT).show();
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }
                return true;
            case R.id.item_maruchan_medicinal:
                if(dinerocount>=8){
                    Toast.makeText(this, "Has comprado maruchan medicinal", Toast.LENGTH_SHORT).show();
                    dinerocount=dinerocount-8;
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }else{
                    Toast.makeText(this, "No tienes suficiente dinero", Toast.LENGTH_SHORT).show();
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }
                return true;
            case R.id.item_fourloko:
                if(dinerocount>=12){
                    Toast.makeText(this, "Has comprado four loko", Toast.LENGTH_SHORT).show();
                    dinerocount=dinerocount-12;
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }else{
                    Toast.makeText(this, "No tienes suficiente dinero", Toast.LENGTH_SHORT).show();
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }
                return true;
            case R.id.item_aspirinas:
                if(dinerocount>=15){
                    Toast.makeText(this, "Has comprado aspirinas", Toast.LENGTH_SHORT).show();
                    dinerocount=dinerocount-15;
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }else{
                    Toast.makeText(this, "No tienes suficiente dinero", Toast.LENGTH_SHORT).show();
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }
                return true;
            case R.id.item_papitas:
                if(dinerocount>=11){
                    Toast.makeText(this, "Has comprado papitas", Toast.LENGTH_SHORT).show();
                    dinerocount=dinerocount-11;
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }else{
                    Toast.makeText(this, "No tienes sufieciente dinero", Toast.LENGTH_SHORT).show();
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }
                return true;
            case R.id.item_caguamon:
                if(dinerocount>=20){
                    Toast.makeText(this, "Has comprado un caguamon", Toast.LENGTH_SHORT).show();
                    dinerocount=dinerocount-20;
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }else{
                    Toast.makeText(this, "No tienes suficiente dinero", Toast.LENGTH_SHORT).show();
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }
                return true;
            case R.id.item_maruchan:
                if(dinerocount>=6){
                    Toast.makeText(this, "Has comprado maruchan", Toast.LENGTH_SHORT).show();
                    dinerocount=dinerocount-6;
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }else{
                    Toast.makeText(this, "No tienes suficiente dinero", Toast.LENGTH_SHORT).show();
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }
                return true;
            case R.id.item_atun:
                if(dinerocount>=14){
                    Toast.makeText(this, "Has comprado atun", Toast.LENGTH_SHORT).show();
                    dinerocount=dinerocount-14;
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }else{
                    Toast.makeText(this, "No tienes suficiente dinero", Toast.LENGTH_SHORT).show();
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }
                return true;
            case R.id.item_nito:
                if(dinerocount>=13){
                    Toast.makeText(this, "Has comprado un nito", Toast.LENGTH_SHORT).show();
                    dinerocount=dinerocount-13;
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }else{
                    Toast.makeText(this, "No tienes suficiente dinero", Toast.LENGTH_SHORT).show();
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }
                return true;
            case R.id.item_loko:
                if(dinerocount>=15){
                    Toast.makeText(this, "Has comprado un four loko", Toast.LENGTH_SHORT).show();
                    dinerocount=dinerocount-15;
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }else{
                    Toast.makeText(this, "No tienes suficiente dinero", Toast.LENGTH_SHORT).show();
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }
                return true;
            case R.id.item_cola:
                if(dinerocount>=10){
                    Toast.makeText(this, "Has comprado una coca cola", Toast.LENGTH_SHORT).show();
                    dinerocount=dinerocount-10;
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }else{
                    Toast.makeText(this, "No tienes suficiente dinero", Toast.LENGTH_SHORT).show();
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }
                return true;
            case R.id.item_agua:
                if(dinerocount>=5){
                    Toast.makeText(this, "Has comprado un agua", Toast.LENGTH_SHORT).show();
                    dinerocount=dinerocount-5;
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }else{
                    Toast.makeText(this, "No tienes suficiente dinero", Toast.LENGTH_SHORT).show();
                    txtdinero.setText(String.valueOf("$"+dinerocount));
                }
                return true;


            default:
                return false;
        }
    }

}
