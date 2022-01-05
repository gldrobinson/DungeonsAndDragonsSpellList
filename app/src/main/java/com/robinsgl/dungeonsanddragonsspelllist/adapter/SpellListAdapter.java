package com.robinsgl.dungeonsanddragonsspelllist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.robinsgl.dungeonsanddragonsspelllist.R;
import com.robinsgl.dungeonsanddragonsspelllist.model.SpellApi;

import java.util.ArrayList;
import java.util.List;

public class SpellListAdapter extends RecyclerView.Adapter<SpellListAdapter.SpellHolder> implements Filterable  {
    private List<SpellApi> spells = new ArrayList<>();
    private List<SpellApi> spellsFullList;
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
        this.spellsFullList = new ArrayList<>(spells);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<SpellApi> filteredList = new ArrayList<>();

                String charString = charSequence.toString().toLowerCase().trim();

                if (charString.isEmpty()) {
                    filteredList.addAll(spellsFullList);
                } else {

                    for (SpellApi spell : spellsFullList) {
                        if (spell.getSpellName().toLowerCase().startsWith(charString)) {
                            filteredList.add(spell);
                        }
                    }

                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                spells.clear();
                spells.addAll((ArrayList<SpellApi>) filterResults.values);
                notifyDataSetChanged();
            }
        };
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


