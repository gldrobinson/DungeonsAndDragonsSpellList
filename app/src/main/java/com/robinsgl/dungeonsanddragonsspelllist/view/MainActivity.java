package com.robinsgl.dungeonsanddragonsspelllist.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.robinsgl.dungeonsanddragonsspelllist.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView spellListView;
    ArrayList<String> spellList = new ArrayList<>();
    ArrayAdapter<String> spellArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spellListView = findViewById(R.id.spell_list_view);
        spellList.add("fireball");
        spellList.add("wish");
        spellList.add("healing word");
        spellList.add("animal friendship");
        spellList.add("lightning ball");
        spellList.add("teleport");

        spellArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spellList) {
        };

        spellListView.setAdapter(spellArrayAdapter);
    }
}