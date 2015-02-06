package com.example.it00046.bodina.Classes;

import android.content.Context;

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

    public static SQLClientsDAO g_DataBase;
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
        g_Client.CodiClient = "";
        g_Client.Idioma = "";
        g_Client.Pais = "";
        g_Client.Nom = "";
        g_Client.Contacte = "";
        g_Client.eMail = "";
        g_Client.DataAlta = new Date();
        g_NoHiHanDades = true;
    }
}