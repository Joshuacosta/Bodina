package com.example.it00046.bodina.Listen;

/**
 * Created by it00046 on 04/02/2015.
 */
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class OnItemSelected_Listener_SpinnerIdioma implements OnItemSelectedListener {

    @Override
    public void onItemSelected(AdapterView parent, View view, int pos, long id) {
        Toast.makeText(parent.getContext(), "Selected Language : " + parent.getSelectedItem(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView parent) {

    }
}