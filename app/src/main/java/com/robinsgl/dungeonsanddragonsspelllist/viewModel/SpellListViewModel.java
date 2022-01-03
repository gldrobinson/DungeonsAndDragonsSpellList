package com.robinsgl.dungeonsanddragonsspelllist.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.robinsgl.dungeonsanddragonsspelllist.data.OnSpellListResponse;
import com.robinsgl.dungeonsanddragonsspelllist.data.SpellListRepo;
import com.robinsgl.dungeonsanddragonsspelllist.model.SpellApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SpellListViewModel extends ViewModel {
    private SpellListRepo spellListRepo;
    private ArrayList<SpellApi> spellApiList = new ArrayList<>();
    public MutableLiveData<ArrayList<SpellApi>> spellList = new MutableLiveData<>();

    public SpellListViewModel() {
        this.spellListRepo = new SpellListRepo();
    }

    public void getSpells() {
        spellListRepo.getAllSpells(new OnSpellListResponse() {
            @Override
            public void onSpellListReceived(JSONObject allSpells) {
                if (allSpells.has("results")) {
                    try {
                        JSONArray spellsJSONArray = allSpells.getJSONArray("results");

                        for (int i=0; i < spellsJSONArray.length(); i++) {
                            JSONObject jsonObject = spellsJSONArray.getJSONObject(i);
                            String spellName = jsonObject.getString("name");
                            String spellUrl = jsonObject.getString("url");
                            SpellApi spellApi = new SpellApi(spellName, spellUrl);
                            spellApiList.add(spellApi);
                        }

                    } catch (JSONException jsonException) {

                    }

                    spellList.setValue(spellApiList);
                }
            }

            @Override
            public void onSpellListError(Exception exception) {
                // return null list.
                spellList.setValue(spellApiList);

            }
        });
    }
}
