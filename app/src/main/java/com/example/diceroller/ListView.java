package com.example.diceroller;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ListView extends AppCompatActivity {

    android.widget.ListView listView;
    HashMap<String, ArrayList<Integer>> hashmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listView = findViewById(R.id.lstView);
        MainActivity main = new MainActivity();
        hashmap = main.getListItem();

        Collection<ArrayList<Integer>> values = hashmap.values();

        ArrayList<ArrayList<Integer>> listOfValues = new ArrayList<>(values);

        ArrayAdapter<ArrayList<Integer>> adapter = new ArrayAdapter<>(this, R.layout.activity_list_view, listOfValues);

        listView.setAdapter(adapter);
    }

}
