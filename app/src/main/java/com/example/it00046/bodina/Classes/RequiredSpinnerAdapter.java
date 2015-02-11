package com.example.it00046.bodina.Classes;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by it00046 on 11/02/2015.
 */
public class RequiredSpinnerAdapter<T> extends ArrayAdapter<T> {
    public RequiredSpinnerAdapter(Context context, int textViewResourceId,
                                  ArrayList<T> objects) {
        super(context, textViewResourceId, objects);
    }

    int textViewId = 0;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        if (view instanceof TextView) {
            textViewId = view.getId();
        }
        return view;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row = super.getView(position, convertView, parent);
        return (row);
    }

    public void setError(View v, CharSequence s) {
        if(textViewId != 0){
            TextView name = (TextView) v.findViewById(textViewId);
            name.setError(s);
        }
    }
}