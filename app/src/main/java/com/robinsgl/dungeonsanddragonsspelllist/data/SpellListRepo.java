package com.robinsgl.dungeonsanddragonsspelllist.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.robinsgl.dungeonsanddragonsspelllist.app.SpellListApp;

public class SpellListRepo {
    public void getAllSpells(OnSpellListResponse onSpellListResponse) {
        String url = "https://www.dnd5eapi.co/api/spells";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                if (onSpellListResponse != null) {
                    onSpellListResponse.onSpellListReceived(response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                onSpellListResponse.onSpellListError(e);
            }

        }, error -> {
            Log.i("jsonObjectRequestErr", "getAllSpells: Error " );

        });

        SpellListApp.getInstance().addToRequestQueue(jsonObjectRequest);


    }
}
