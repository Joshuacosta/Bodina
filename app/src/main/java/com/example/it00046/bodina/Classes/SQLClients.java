package com.example.it00046.bodina.Classes;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.it00046.bodina.R;

public class SQLClients extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "BodinaDB";
    // Nom taula
    public static final String Nom = "Clients";
    // Camps
    public static final String Camp_CodiClient = "CodiClient";
    public static final String Camp_eMail = "eMail";
    public static final String Camp_Nom = "Nom";
    public static final String Camp_Pais = "Pais";
    public static final String Camp_Contacte = "Contacte";
    public static final String Camp_DataAlta = "DataAlta";
    public static final String Camp_Idioma = "Idioma";

    public SQLClients(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_T_Clients = Globals.g_Native.getString(R.string.TaulaClients);
        db.execSQL(CREATE_T_Clients);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLClients.class.getName(), "Update database de " + oldVersion + " a " + newVersion + ".");
        // Drop Taula clients
        db.execSQL(Globals.g_Native.getString(R.string.TaulaClientsDrop));
        // create fresh books table
        this.onCreate(db);
    }

}