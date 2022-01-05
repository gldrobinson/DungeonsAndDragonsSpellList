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

    SpellDetailViewModel spellDetailViewModel;
    TextView spellNameTextView;
    View divisorViewTop;
    GridView gridView;
    View divisorViewBottom;
    TextView materialTextView;
    TextView spellDescriptionTextView;
    TextView spellHigherLevelTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_detail);

        spellDetailViewModel = new SpellDetailViewModel();

        spellNameTextView = findViewById(R.id.spell_name_text_view);
        divisorViewTop = findViewById(R.id.divisor_view_top);
        divisorViewBottom = findViewById(R.id.divisor_view_bottom);
        gridView = findViewById(R.id.grid_view);
        materialTextView = findViewById(R.id.material_text_view);
        spellDescriptionTextView = findViewById(R.id.spell_description_text_view);
        spellHigherLevelTextView = findViewById(R.id.spell_higher_level_text_view);

        Intent intent = getIntent();
        String spellNameReceived = intent.getStringExtra("spellName");
        String spellUrlReceived = intent.getStringExtra("spellUrl");

        spellNameTextView.setText(spellNameReceived);

        spellDetailViewModel.getSpellDetail(spellUrlReceived);

        spellDetailViewModel.spellContentsList.observe(this, spellContents -> {
            SpellContentAdapter spellContentAdapter = new SpellContentAdapter(getApplicationContext(), R.layout.spell_detail_item, spellContents);
            gridView.setAdapter(spellContentAdapter);
        });

        spellDetailViewModel.fullSpellDetail.observe(this, fullSpellDetail -> {
            String spellDescription = fullSpellDetail.getDescription();
            String higherLevelString = "At Higher Levels. ";
            String higherLevel = fullSpellDetail.getHigherLevel();
            String materials = fullSpellDetail.getMaterial();

            spellDescriptionTextView.setText(spellDescription);

            if (higherLevel != null) {
                spellHigherLevelTextView.setText(higherLevelString + higherLevel);
            } else {
                spellHigherLevelTextView.setText("");
            }


            if (materials != null) {
                materialTextView.setText("* " +materials);
            } else {
                materialTextView.setText("");
            }

        });

    }
}
