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
    private static final int VERSION_BD = 1;
    private static final String TABLA_INVENTARIO = "CREATE TABLE INVENTARIO (ITEMS TEXT, ID INTEGER)";
    private static final String TABLA_STATS = "CREATE TABLE STATS (VIDA INTEGER, FELICIDAD INTEGER, HAMBRE INTEGER, ID INTEGER, CORRIDO INTEGER, DINERO INTEGER)";
    private static final String TABLA_FORANEO ="CREATE TABLE FORANEO (NOMBRE TEXT, SKIN INTEGER, ID INTEGER)";

    public BaseDeDatos(@Nullable Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_INVENTARIO);
        sqLiteDatabase.execSQL(TABLA_STATS);
        sqLiteDatabase.execSQL(TABLA_FORANEO);
        sqLiteDatabase.execSQL("INSERT INTO FORANEO VALUES('"+"Paquito"+"','"+700158+"','"+1+"')");
        sqLiteDatabase.execSQL("INSERT INTO STATS VALUES ('"+0+"','"+0+"','"+0+"','"+1+"','"+0+"','"+0+"')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLA_INVENTARIO + "");
        sqLiteDatabase.execSQL(TABLA_INVENTARIO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLA_STATS+ "");
        sqLiteDatabase.execSQL(TABLA_STATS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLA_FORANEO + "");
    }



    public void agregarItems(String item, int id) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            bd.execSQL("INSERT INTO INVENTARIO VALUES ('" + item + "','" + id + "')");
            bd.close();
        }
    }
    public void agregar(String item, int id) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            bd.execSQL("INSERT INTO INVENTARIO VALUES ('" + item + "','" + id + "')");
            bd.close();
        }
    }
    public int firstRun(){
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM STATS WHERE ID = 1",null);
        if(bd!=null&&cursor!=null){
            cursor.moveToFirst();

        }
        return cursor.getInt(4);
    }
    public void setCorrido(){
        SQLiteDatabase bd = getWritableDatabase();
        bd.execSQL("UPDATE STATS SET CORRIDO=1 WHERE ID = 1");
    }

    public void agregarStats(int vida, int felicidad, int hambre,int dinero){
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null){
            bd.execSQL("UPDATE STATS SET VIDA='"+vida+"', FELICIDAD ='"+felicidad+"',HAMBRE='"+hambre+"',DINERO='"+dinero+"' WHERE ID=1");
            bd.close();
        }
    }
    public void agregarForaneo(String nombre, int imagen){
        SQLiteDatabase bd = getWritableDatabase();
        if(bd!=null){
            bd.execSQL("UPDATE FORANEO SET NOMBRE='"+nombre+"', SKIN='"+imagen+"' WHERE ID=1");
        }
    }

    public int [] getStats(){
        SQLiteDatabase bd =getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM STATS WHERE ID =1", null);
        int[]arreglostats=new int[4];
       if(bd!=null && cursor!=null) {
           cursor.moveToFirst();
           arreglostats[0] = cursor.getInt(0);
           arreglostats[1] = cursor.getInt(1);
           arreglostats[2] = cursor.getInt(2);
           arreglostats[3] = cursor.getInt(5);

       }
       return arreglostats;
    }


    public String[] getForaneo(){
    SQLiteDatabase bd = getReadableDatabase();
    Cursor cursor = bd.rawQuery("SELECT * FROM FORANEO WHERE ID = 1",null);
    String[]foraneostats = new String[3];
    if(bd!=null && cursor!=null){
        cursor.moveToFirst();
        foraneostats[0]=cursor.getString(0);
        foraneostats[1]=String.valueOf(cursor.getInt(1));

    }
    return foraneostats;
    }



    public void eliminarStats(int vida, int felicidad, int hambre) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            bd.execSQL("DELETE FROM STATS WHERE VIDA='" + vida + "' AND FELICIDAD='"+felicidad+"' AND HAMBRE='"+hambre+"'");
            bd.close();
        }
    }

    public void eliminarItems(int id) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            bd.execSQL("DELETE FROM INVENTARIO WHERE ID='" + id + "'");
            bd.close();
        }
    }



    public List<listaModelo> mostrarLista() {
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM INVENTARIO ", null);
        List<listaModelo> listas = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                listas.add(new listaModelo(cursor.getString(0), cursor.getInt(1)));
            } while (cursor.moveToNext());
        }
        return listas;
    }

    public void actualizarBD(int nuevoIndex, int index1) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            bd.execSQL("UPDATE INVENTARIO SET ID = '" + nuevoIndex + "' WHERE ID='" + index1 + "'");
        }
    }
}
