package com.robinsgl.dungeonsanddragonsspelllist.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.robinsgl.dungeonsanddragonsspelllist.R;
import com.robinsgl.dungeonsanddragonsspelllist.adapter.SpellAdapter;
import com.robinsgl.dungeonsanddragonsspelllist.data.OnSpellListResponse;
import com.robinsgl.dungeonsanddragonsspelllist.data.SpellListRepo;
import com.robinsgl.dungeonsanddragonsspelllist.model.SpellApi;
import com.robinsgl.dungeonsanddragonsspelllist.viewModel.SpellListViewModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SpellAdapter spellAdapter;
    SpellListViewModel spellListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        spellAdapter = new SpellAdapter();
        recyclerView.setAdapter(spellAdapter);

        spellListViewModel  = new SpellListViewModel();
        spellListViewModel.getSpells();

       spellListViewModel.spellList.observe(this, spellApis -> {
           spellAdapter.setSpells(spellApis);
       });


    }
}