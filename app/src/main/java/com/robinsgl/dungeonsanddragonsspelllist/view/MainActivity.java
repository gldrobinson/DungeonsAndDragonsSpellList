package com.robinsgl.dungeonsanddragonsspelllist.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.robinsgl.dungeonsanddragonsspelllist.R;
import com.robinsgl.dungeonsanddragonsspelllist.adapter.SpellListAdapter;
import com.robinsgl.dungeonsanddragonsspelllist.viewModel.SpellListViewModel;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SpellListAdapter spellListAdapter;
    SpellListViewModel spellListViewModel;
    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        spellListAdapter = new SpellListAdapter();
        recyclerView.setAdapter(spellListAdapter);

        spellListViewModel  = new SpellListViewModel();
        spellListViewModel.getSpells();

       spellListViewModel.spellList.observe(this, spellApis -> {
           spellListAdapter.setSpells(spellApis);
       });

       spellListAdapter.setOnItemClickListener(spellApi -> {
           Intent intent = new Intent(MainActivity.this.getApplicationContext(), SpellDetailActivity.class);
           String spellName = spellApi.getSpellName();
           String spellUrl = spellApi.getSpellUrl();
           intent.putExtra("spellName", spellName);
           intent.putExtra("spellUrl", spellUrl);
           startActivity(intent);
       });


    }
}