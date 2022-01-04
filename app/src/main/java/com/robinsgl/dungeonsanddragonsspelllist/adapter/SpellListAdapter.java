package com.robinsgl.dungeonsanddragonsspelllist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.robinsgl.dungeonsanddragonsspelllist.R;
import com.robinsgl.dungeonsanddragonsspelllist.model.SpellApi;

import java.util.ArrayList;
import java.util.List;

public class SpellListAdapter extends RecyclerView.Adapter<SpellListAdapter.SpellHolder>  {
    private List<SpellApi> spells = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public SpellHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.spell_item, parent, false);
        return new SpellHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpellHolder holder, int position) {
        SpellApi spellApi = spells.get(position);
        holder.spellTextView.setText(spellApi.getSpellName());

    }

    @Override
    public int getItemCount() {
        return spells.size();
    }

    public void setSpells(List<SpellApi> spells) {
        this.spells = spells;
        notifyDataSetChanged();
    }

    class SpellHolder extends RecyclerView.ViewHolder {
        private TextView spellTextView;

        public SpellHolder(@NonNull View itemView) {
            super(itemView);
            spellTextView = itemView.findViewById(R.id.spell_text_view);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onSpellClick(spells.get(position));
                }

            });
        }

    }

    public interface OnItemClickListener  {
        void onSpellClick(SpellApi spellApi);
    }

    public void setOnItemClickListener  (OnItemClickListener listener) {
         this.listener = listener;
    }

}


