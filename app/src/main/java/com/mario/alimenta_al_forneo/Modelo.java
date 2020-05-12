package com.mario.alimenta_al_forneo;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class Modelo {
    int hambreProgress =10;
    int vidaProgress;
    int felcidadProgress;
    public Modelo(int felcidadProgress, int hambreProgress, int vidaProgress){
        this.felcidadProgress = felcidadProgress;
        this.hambreProgress = hambreProgress;
        this.vidaProgress = hambreProgress;

    }

    public int getFelcidadProgress() {
        return felcidadProgress;
    }

    public int getHambreProgress() {
        return hambreProgress;
    }
}


