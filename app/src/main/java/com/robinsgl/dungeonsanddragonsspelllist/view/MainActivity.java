package com.robinsgl.dungeonsanddragonsspelllist.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.robinsgl.dungeonsanddragonsspelllist.R;
import com.robinsgl.dungeonsanddragonsspelllist.adapter.SpellListAdapter;
import com.robinsgl.dungeonsanddragonsspelllist.viewModel.SpellListViewModel;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SpellListAdapter spellListAdapter;
    SpellListViewModel spellListViewModel;
    androidx.appcompat.widget.Toolbar toolbar;
    MenuInflater menuInflater;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search_view);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                spellListAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}