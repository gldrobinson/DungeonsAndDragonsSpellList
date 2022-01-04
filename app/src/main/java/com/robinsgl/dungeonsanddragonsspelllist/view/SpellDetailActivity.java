package com.robinsgl.dungeonsanddragonsspelllist.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.robinsgl.dungeonsanddragonsspelllist.R;
import com.robinsgl.dungeonsanddragonsspelllist.adapter.SpellContentAdapter;
import com.robinsgl.dungeonsanddragonsspelllist.viewModel.SpellDetailViewModel;


public class SpellDetailActivity extends AppCompatActivity {

    TextView spellNameTextView;
    View divisorView;
    GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_detail);

        spellNameTextView = findViewById(R.id.spell_name_text_view);
        divisorView = findViewById(R.id.divisor_view);
        gridView = findViewById(R.id.grid_view);

        Intent intent = getIntent();
        String spellNameReceived = intent.getStringExtra("spellName");
        String spellUrlReceived = intent.getStringExtra("spellUrl");

        spellNameTextView.setText(spellNameReceived);

        SpellDetailViewModel spellDetailViewModel = new SpellDetailViewModel();

        spellDetailViewModel.getSpellDetail(spellUrlReceived);

        spellDetailViewModel.spellContentsList.observe(this, spellContents -> {
            for (int i = 0; i < spellContents.size(); i++) {
                Log.i(spellContents.get(i).getTitle(), "onChanged: " + spellContents.get(i).getContent());
            }
            SpellContentAdapter spellContentAdapter = new SpellContentAdapter(getApplicationContext(), R.layout.spell_detail_item, spellContents);
            gridView.setAdapter(spellContentAdapter);
        });

        spellDetailViewModel.fullSpellDetail.observe(this, fullSpellDetail -> {

                });

    }
}
