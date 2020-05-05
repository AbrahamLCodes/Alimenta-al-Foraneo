package com.mario.alimenta_al_forneo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CostumeDialog.CostumeDialogInterface {
    private static ImageView foraneo;
    private ImageButton ajustes,inventario,otso,salud;
    private TextView dinero,felicidad,hambre,vida,nombre;
    private DrawerLayout mDrawerLayout;
    private NavigationView naviView;
    public SoundPool sp;
    public int flujoDeMusia = 0;
    Handler miHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle datos = msg.getData();
            String s = datos.getString("key_palabra");
            String x = datos.getString("hambre");
            String y = datos.getString("vida");
            felicidad.setText(s+"%");
            hambre.setText(x+"%");
            vida.setText(y+"%");
        }
    };
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //handler
        //Thread
        final Thread miThread = new Thread(){
            @Override
            public void run() {
                super.run();
                int i =100;
                int x= 100;
                int y = 100;
                while(true){
                    try {
                        if(i != 0)
                        Thread.sleep(500);
                        else{
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message msg = new Message();
                    Bundle datos = new Bundle();
                    datos.putString("key_palabra", String.valueOf(i));
                    datos.putString("hambre",String.valueOf(x));
                    datos.putString("vida",String.valueOf(y));
                    msg.setData(datos);
                    miHandler.sendMessage(msg);
                    Log.wtf("mihilo",i+"");
                    i--;
                    x--;
                    y--;
                }

            }
        };
        miThread.start();
        Modelo modelo = new Modelo();
        modelo.Hilos();

        //Sonido
        sp = new SoundPool(8, AudioManager.STREAM_MUSIC,0);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        flujoDeMusia = sp.load(this,R.raw.click,1);
        //Navaigation Drawer
        naviView = findViewById(R.id.naviVew);
        mDrawerLayout  = findViewById(R.id.drawerLayout);

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
        nombre = findViewById(R.id.txt_v_nombre);
        dinero = findViewById(R.id.txt_view_dinero);
        felicidad = findViewById(R.id.txt_v_happy);
        hambre = findViewById(R.id.txt_v_comida);
        vida = findViewById(R.id.txt_v_vida);

        /* images */

        foraneo = findViewById(R.id.img_foraneo);

    }
    public static void setForaneo(int f){

        foraneo.setImageResource(f);
    }

    private void openDialog() {
        CostumeDialog costumeDialog = new CostumeDialog();
        costumeDialog.show(getSupportFragmentManager(),"Costume Dialog");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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

    private void play_sp(){
        sp.play(flujoDeMusia,1,1,0,0,1);
    }
    @Override
    public void apllytext(String Name) {
        nombre.setText(Name);

    }


}
