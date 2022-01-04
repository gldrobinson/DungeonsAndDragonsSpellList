package com.robinsgl.dungeonsanddragonsspelllist.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.robinsgl.dungeonsanddragonsspelllist.R;
import com.robinsgl.dungeonsanddragonsspelllist.model.SpellContent;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;


public class SpellContentAdapter extends ArrayAdapter<SpellContent> {

    private List<SpellContent> spellContents = new ArrayList<>();
    private int custom_layout_id;


    public SpellContentAdapter(@NonNull Context context, int resource, @NonNull List<SpellContent> objects) {
        super(context, resource, objects);
        this.spellContents = objects;
        this.custom_layout_id = resource;
    }

    @Override
    public int getCount() {
        return spellContents.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(custom_layout_id, null);
        }

        TextView titleTextView = v.findViewById(R.id.spell_detail_title_text_view);
        TextView contentTextView = v.findViewById(R.id.spell_detail_content_text_view);

        titleTextView.setText(spellContents.get(position).getTitle());
        contentTextView.setText(spellContents.get(position).getContent());

        return v;
    }
}
