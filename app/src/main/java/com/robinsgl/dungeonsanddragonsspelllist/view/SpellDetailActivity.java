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
import com.robinsgl.dungeonsanddragonsspelllist.model.SpellContent;

import java.util.ArrayList;

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

        Log.i("spellData", "onCreate: " + spellNameReceived + " " + spellUrlReceived);

        ArrayList<SpellContent> spellContents = new ArrayList<>();
        spellContents.add(new SpellContent("LEVEL", "3rd"));
        spellContents.add(new SpellContent("RANGE/AREA", "150ft"));
        spellContents.add(new SpellContent("DURATION", "Instantaneous"));
        spellContents.add(new SpellContent("ATTACK SAVE", "Dex Save"));
        spellContents.add(new SpellContent("CASTING TIME", "1 Action"));
        spellContents.add(new SpellContent("COMPONENTS", "V,S,M"));
        spellContents.add(new SpellContent("DAMAGE/EFFECT", "Fire"));

        SpellContentAdapter spellContentAdapter = new SpellContentAdapter(this, R.layout.spell_detail_item, spellContents);
        gridView.setAdapter(spellContentAdapter);

    }
}
