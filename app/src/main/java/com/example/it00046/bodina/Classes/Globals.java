package com.example.it00046.bodina.Classes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

/**
 * Created by it00046 on 04/02/2015.
 */
public final class Globals
{
    // Variables
    public static Boolean g_NoHiHanDades = false;
    public static Client  g_Client = new Client();
    public static Context g_Native;
    public static Boolean g_HiHaXarxa = false;

    public static SQLClientsDAO g_DB_DAO;
    public static SQLiteDatabase g_DB;
    // Constants
    // Operatives
    public static final int k_OPE_Alta = 0;
    public static final int k_OPE_Update = 1;
    public static final int k_OPE_Select = 2;
    public static final int k_OPE_SelectCodiClientIntern = 21;
    // - Treball
    public static final String k_ClientNOU = "NOU";

    public void Globals()
    {
    }
}