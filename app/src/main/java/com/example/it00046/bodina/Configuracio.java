package com.example.it00046.bodina;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.it00046.bodina.Classes.Client;
import com.example.it00046.bodina.Classes.Globals;
import com.example.it00046.bodina.Classes.SQLClients;
import com.example.it00046.bodina.Classes.SQLClientsDAO;
import com.example.it00046.bodina.Listen.OnItemSelected_Listener_SpinnerIdioma;


public class Configuracio extends ActionBarActivity{

    private Spinner l_SpinnerIdioma;
    private EditText l_TextName, l_TexteMail, l_TexteContacte;
    private SQLClientsDAO sqlclientsDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracio);
        // Codi per tractar el spinner del idioma
        l_SpinnerIdioma = (Spinner)findViewById(R.id.spinnerIdioma);

        //ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,
        //                                                     R.array.Idioma);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Idioma,android.R.layout.simple_spinner_item);
        //Añadimos el layout para el menú
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Le indicamos al spinner el adaptador a usar
        l_SpinnerIdioma.setAdapter(adapter);

        l_SpinnerIdioma.setOnItemSelectedListener(new OnItemSelected_Listener_SpinnerIdioma());

        /*
        // Codi btnAceptar com classe buida
        final Button button = (Button) findViewById(R.id.btnAceptar);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

            }
        });
        */

        if (Globals.g_Client.CodiClient != ""){
            // Mostrem dades
            l_TextName = (EditText) findViewById(R.id.TextName);
            l_TexteMail = (EditText) findViewById(R.id.TexteMail);
            l_TexteContacte = (EditText) findViewById(R.id.TexteContacte);

            l_TextName.setText(Globals.g_Client.Nom);
            l_TexteMail.setText(Globals.g_Client.eMail);
            l_TexteContacte.setText(Globals.g_Client.Contacte);
        }
    }

    public void btnAcceptarOnClick(View view){
        // Codi
        Client client = new Client();
        client.Nom = l_TextName.getText().toString();
        client.eMail = l_TexteMail.getText().toString();
        client.Contacte = l_TexteContacte.getText().toString();

        Globals.g_DataBase.createClient(client);
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
