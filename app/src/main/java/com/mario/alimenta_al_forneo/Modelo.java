package com.mario.alimenta_al_forneo;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class Modelo extends Thread {
int z= 100;
    void Hilos(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                Log.wtf("hiloModelo", z + "");
                z++;
            }
            }
        }).start();
    }
}


