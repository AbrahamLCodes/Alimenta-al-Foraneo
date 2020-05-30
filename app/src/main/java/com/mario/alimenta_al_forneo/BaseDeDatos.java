package com.mario.alimenta_al_forneo;

import android.content.ClipData;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.CaseMap;
import android.view.MenuItem;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BaseDeDatos extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "basededatos.bd";
    private static final int VERSION_BD=1;
    private static final String TABLA_INVENTARIO="CREATE TABLE INVENTARIO (ITEMS TEXT)";

    public BaseDeDatos(@Nullable Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
     sqLiteDatabase.execSQL(TABLA_INVENTARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLA_INVENTARIO+"");
    sqLiteDatabase.execSQL(TABLA_INVENTARIO);
    }
    public void agregarItems(String item){
        SQLiteDatabase bd=getWritableDatabase();
        if (bd!=null){
            bd.execSQL("INSERT INTO INVENTARIO VALUES ('"+item+"')");
            bd.close();
        }
    }
    public void eliminarItems(int item){
        SQLiteDatabase bd=getWritableDatabase();
        if (bd!=null){
            bd.execSQL("DELETE FROM INVENTARIO WHERE ITEMS='"+item+"'");
            bd.close();
        }
    }
    public List<listaModelo> mostrarLista(){
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor=bd.rawQuery("SELECT * FROM INVENTARIO ", null);
        List<listaModelo> listas = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                listas.add(new listaModelo(cursor.getString(0)));
            }while(cursor.moveToNext());
        }
        return listas;
    }



}
