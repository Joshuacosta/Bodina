package com.example.it00046.bodina;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.it00046.bodina.Classes.Client;
import com.example.it00046.bodina.Classes.Globals;
import com.example.it00046.bodina.Classes.SQLClientsDAO;
import com.example.it00046.bodina.Listen.OnItemSelected_Listener_SpinnerIdioma;


public class Configuracio extends ActionBarActivity{

    private Spinner lSPN_Idioma, lSPN_Paissos;
    private EditText lTXT_Name, lTXT_eMail, lTXT_Contacte;
    private SQLClientsDAO sqlclientsDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracio);

        // Codi per tractar el spinner del idioma
        lSPN_Idioma = (Spinner)findViewById(R.id.spinnerIdioma);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Idioma,android.R.layout.simple_spinner_item);
        //Añadimos el layout para el menú
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Le indicamos al spinner el adaptador a usar
        lSPN_Idioma.setAdapter(adapter);

        lSPN_Idioma.setOnItemSelectedListener(new OnItemSelected_Listener_SpinnerIdioma());

        // Codi per tractar el spinner de paissos
        lSPN_Paissos = (Spinner)findViewById(R.id.spinnerPais);
        lSPN_Paissos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View view, int pos, long id) {
                Toast.makeText(parent.getContext(), "Selected pais : " + parent.getSelectedItem(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView parent) {

            }
        });        // Informem les dades si es necessari
        if (Globals.g_Client.CodiClient != ""){
            // Mostrem dades
            lTXT_Name = (EditText) findViewById(R.id.TextName);
            lTXT_Name.setText(Globals.g_Client.Nom);
            lTXT_eMail = (EditText) findViewById(R.id.TexteMail);
            lTXT_eMail.setText(Globals.g_Client.eMail);
            lTXT_Contacte = (EditText) findViewById(R.id.TexteContacte);
            lTXT_Contacte.setText(Globals.g_Client.Contacte);

            lSPN_Idioma = (Spinner) findViewById(R.id.spinnerIdioma);
            SimpleCursorAdapter adapter_pais = (SimpleCursorAdapter) lSPN_Idioma.getAdapter();
            for (int position = 0; position < adapter_pais.getCount(); position++)
            {
                if(adapter_pais.getItem(position).toString() == Globals.g_Client.Pais)
                {
                    lSPN_Idioma.setSelection(position);
                    break;
                }
            }
        }
    }

    public void btnAcceptarOnClick(View view){
        Client client = new Client();

        // Validem que els camps estiguin informats
        if (1==1) {

            client.Nom = lTXT_Name.getText().toString();
            client.eMail = lTXT_eMail.getText().toString();
            client.Contacte = lTXT_Contacte.getText().toString();
            client.Pais = lSPN_Paissos.getSelectedItem().toString();
            client.Idioma = lSPN_Idioma.getSelectedItem().toString();

            Globals.g_DB_DAO.createClient(client);
            // Gravem les dades del client i tornem enrera
            Globals.g_Client = client;
            this.finish();
        }
        else{

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_configuracio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
