package com.example.it00046.bodina.Classes;

/**
 * Created by Joshua on 04/02/2015.
 */
/*
public final class ClientDAO
{
    // Aquesta funcio recupera les dades desde la BBDD client
    static public Client RecuperarDades(){
        String l_sql = "SELECT CodiClient, eMail, Nom, CodiPais, Contacte, DataAlta, CodiIdioma " +
                       "FROM Client";
        var l_stmt:SQLStatement = new SQLStatement();
        var c_client:Client = new Client();

        l_stmt.sqlConnection = BDGeneric.sqlConnection;
        l_stmt.text = l_sql;
        l_stmt.execute();
        var result:Array = l_stmt.getResult().data;
        if (result){
            if (result.length == 1){
                // Agafem la definició del client
                c_client.CodiClient = result[0].CodiClient;
                c_client.eMail = result[0].eMail;
                c_client.Nom = result[0].Nom;
                c_client.CodiPais = result[0].CodiPais;
                c_client.Contacte = result[0].Contacte;
                c_client.DataAltaTexte = result[0].DataAlta;
                c_client.CodiIdioma = result[0].CodiIdioma;
            }
        }
        return c_client;
    }

    // Aquesta funcio graba les dades a la BBDD client
    static public Boolean InserirDades(Client P_Client){
        var l_sql:String = "INSERT INTO Client (CodiClient, eMail, Nom, CodiPais, Contacte, DataAlta, CodiIdioma, Actualitzat) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        var l_stmt:SQLStatement = new SQLStatement();
        var c_client:Client;

        l_stmt.sqlConnection = BDGeneric.sqlConnection;
        l_stmt.text = l_sql;
        l_stmt.parameters[0] = P_Client.CodiClient;
        l_stmt.parameters[1] = P_Client.eMail;
        l_stmt.parameters[2] = P_Client.Nom;
        l_stmt.parameters[3] = P_Client.CodiPais;
        l_stmt.parameters[4] = P_Client.Contacte;
        l_stmt.parameters[5] = P_Client.DataAltaTexte;
        l_stmt.parameters[6] = P_Client.CodiIdioma;
        l_stmt.parameters[7] = false;
        l_stmt.execute();

        return true;
    }

    static public function UpdateDades(P_Client:Client):Boolean{
        var l_sql:String = "UPDATE Client set CodiClient=?, eMail=?, Nom=?, CodiPais=?, Contacte=?, DataAlta=?, CodiIdioma=?, Actualitzat=? ";
        var l_stmt:SQLStatement = new SQLStatement();
        var c_client:Client = new Client();

        l_stmt.sqlConnection = BDGeneric.sqlConnection;
        l_stmt.text = l_sql;
        l_stmt.parameters[0] = P_Client.CodiClient;
        l_stmt.parameters[1] = P_Client.eMail;
        l_stmt.parameters[2] = P_Client.Nom;
        l_stmt.parameters[3] = P_Client.CodiPais;
        l_stmt.parameters[4] = P_Client.Contacte;
        l_stmt.parameters[5] = P_Client.DataAltaTexte;
        l_stmt.parameters[6] = P_Client.CodiIdioma;
        l_stmt.parameters[7] = false;
        l_stmt.execute();

        return true;
    }

    static public function UpdateDadesCodiClient(P_CodiClient:String):Boolean{
        var l_sql:String = "UPDATE Client set CodiClient=?";
        var l_stmt:SQLStatement = new SQLStatement();

        l_stmt.sqlConnection = BDGeneric.sqlConnection;
        l_stmt.text = l_sql;
        l_stmt.parameters[0] = P_CodiClient;
        l_stmt.execute();

        return true;
    }

    static public function UpdateDadesActualitzat():Boolean{
        var l_sql:String = "UPDATE Client set Actualitzat=true";
        var l_stmt:SQLStatement = new SQLStatement();

        l_stmt.sqlConnection = BDGeneric.sqlConnection;
        l_stmt.text = l_sql;
        l_stmt.execute();

        return true;
    }

    static public function ActualitzarClientSiCal():Boolean{
        var l_sql:String = "SELECT Actualitzat FROM Client";
        var l_stmt:SQLStatement = new SQLStatement();
        var l_CalActualitzar:Boolean = false;

        l_stmt.sqlConnection = BDGeneric.sqlConnection;
        l_stmt.text = l_sql;
        l_stmt.execute();
        var result:Array = l_stmt.getResult().data;
        if (result){
            if (result.length == 1){
                l_CalActualitzar = result[0].Actualitzat;
            }
        }
        if (l_CalActualitzar){
            InformarDadesServidor(Globals.g_Client, Globals.k_OPE_Update);
        }
        return true;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // O P E R A T I V A     S E R V I D O R
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Aquesta funció accedeix a la BBDD del servidor i recupera la informació del client existent
    // (si no troba informació es que es la primera vegada que l'usuari accedeix al sistema).
    static public function RecuperarDadesServidor():Boolean{
        var PHP:FuncionsPHP = new FuncionsPHP();
        var l_Parametres:Object = new Object();

        // Validem que la xarxa estigui activa
        if (Globals.g_HiHaXarxa){
            // La comunicació de variables es amb vars
            l_Parametres.CodiClientIntern = Globals.g_Client.CodiClientIntern;
            l_Parametres.Operativa = Globals.k_OPE_SelectCodiClientIntern;
            PHP.FerPHP_Vars("http://bodina.virtuol.com/php/Clients.php", l_Parametres, CarregaDades);
        }
        else{
            Funcions.Avis(ResourceManager.getInstance().getString('resources','php.Avis'),
                    ResourceManager.getInstance().getString('resources','php.NoServidor'));
        }
        return true;
    }
    // Si la crida anterior ha anat be carreguem les dades globals de client
    static public function CarregaDades(event:ResultEvent):void{
        var l_Resultat:Object = event.result;
        var c_Client:Client = new Client();

        if (l_Resultat.CodiClient != Globals.k_ClientNOU){
            c_Client.CodiClient = l_Resultat.CodiClient;
            c_Client.CodiIdioma = l_Resultat.CodiIdioma;
            c_Client.CodiPais = l_Resultat.CodiPais;
            c_Client.Contacte = l_Resultat.Contacte;
            c_Client.DataAlta = Funcions.StringDateADate(l_Resultat.DataAlta);
            c_Client.DataAltaTexte = Funcions.DateALocal(c_Client.DataAlta);
            c_Client.eMail = l_Resultat.eMail;
            c_Client.Nom = l_Resultat.Nom;
            Globals.g_Client = c_Client;
            // Gravem a la BBDD Local la info rebuda si el usuari no te informació perque la ha esborrat
            if (Globals.g_NoHiHanDades){
                InserirDades(c_Client);
                // Si hi han dades
                Globals.g_NoHiHanDades = false;
            }
        }
    }
    //
    // Funcio per informar (inserir/actualitzar) les dades en el servidor
    static public function InformarDadesServidor(P_Client:Client, P_Operativa:int, P_FuncioOK:Function = null, P_FuncioError:Function = null):Boolean{
        var PHP:FuncionsPHP = new FuncionsPHP();
        var l_Parametres:Object = new Object();

        // La comunicació de variables es amb vars
        // Validem que la xarxa estigui activa
        if (Globals.g_HiHaXarxa){
            l_Parametres.CodiClient = P_Client.CodiClient;
            l_Parametres.CodiClientIntern = P_Client.CodiClientIntern;
            l_Parametres.eMail = P_Client.eMail;
            l_Parametres.Nom = P_Client.Nom;
            l_Parametres.CodiPais = P_Client.CodiPais;
            l_Parametres.Contacte = P_Client.Contacte;
            l_Parametres.CodiIdioma = P_Client.CodiIdioma;
            l_Parametres.Operativa = P_Operativa;
            PHP.FerPHP_Vars("http://bodina.virtuol.com/php/Clients.php", l_Parametres, P_FuncioOK, P_FuncioError);
        }
        else{
            Funcions.Avis(ResourceManager.getInstance().getString('resources','php.Avis'),
                    ResourceManager.getInstance().getString('resources','php.NoServidor'));
        }
        return true;
    }
}
*/

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.it00046.bodina.R;

public class SQLClientsDAO {

    // Database fields
    private SQLiteDatabase database;
    private SQLClients dbHelper;
    private String[] allColumns = Globals.g_Native.getResources().getStringArray(R.array.TaulaClientCamps);

    public SQLClientsDAO(Context context) {
        dbHelper = new SQLClients(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createClient(Client client) {
        ContentValues values = new ContentValues();
        values.put(SQLClients.Camp_CodiClient, client.CodiClient);
        values.put(SQLClients.Camp_Contacte, client.Contacte);
        values.put(SQLClients.Camp_DataAlta, client.DataAltaTexte);
        values.put(SQLClients.Camp_eMail, client.eMail);
        values.put(SQLClients.Camp_Idioma, client.Idioma);
        values.put(SQLClients.Camp_Nom, client.Nom);
        values.put(SQLClients.Camp_Pais, client.Pais);
        long insertId = database.insert(SQLClients.Nom, null, values);

        /*
            Aquest codi serveix per donar un identificador a la insercio i
            despres com tornem lo inserit sabem quin valor s'ens ha donat

        Cursor cursor = database.query(SQLClients.TABLE_COMMENTS,
                allColumns, SQLClients.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Client newClient = cursorToComment(cursor);
        cursor.close();
        return newClient;
        */
    }

    public void deleteClient(Client client) {
        String id = client.CodiClient;
        database.delete(SQLClients.Nom, SQLClients.Camp_CodiClient + " = " + id, null);
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<Client>();

        Cursor cursor = database.query(SQLClients.Nom,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Client client = cursorToClient(cursor);
            clients.add(client);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return clients;
    }

    // Client nomès tenim un, el recuperem
    public Client RecuperaClient(){
        Client client = new Client();

        Cursor cursor = database.query(SQLClients.Nom, // a. table
                                        allColumns, // b. column names
                                        null, // c. selections
                                        null, // d. selections args
                                        null, // e. group by
                                        null, // f. having
                                        null, // g. order by
                                        null); // h. limit
        if (cursor != null)
            cursor.moveToFirst();

        return cursorToClient(cursor);
    }

    //
    // Funcions privades
    private Client cursorToClient(Cursor cursor) {
        Client client = new Client();

        client.CodiClient = cursor.getString(0);
        client.eMail = cursor.getString(1);
        client.Nom = cursor.getString(2);
        client.Pais = cursor.getString(3);
        client.Contacte = cursor.getString(4);
        client.DataAltaTexte = cursor.getString(5);
        client.Idioma = cursor.getString(6);

        return client;
    }
}
