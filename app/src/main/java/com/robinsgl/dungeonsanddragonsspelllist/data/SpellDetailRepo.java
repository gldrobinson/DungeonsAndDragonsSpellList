package com.robinsgl.dungeonsanddragonsspelllist.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.robinsgl.dungeonsanddragonsspelllist.app.SpellListApp;

import org.json.JSONException;
import org.json.JSONObject;

public class SpellDetailRepo {

    public void getSpellDetail(String spellUrl, OnSpellDetailResponse onSpellDetailResponse) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, spellUrl, null, response -> {

            try {
                if (onSpellDetailResponse != null) {
                    onSpellDetailResponse.onSpellDetailReceived(response);
                }

            } catch (Exception exception) {
                if (onSpellDetailResponse != null) {
                    onSpellDetailResponse.onSpellDetailError(exception);
                }
            }

        }, error -> {
            Log.i("jsonObjectRequestErr", "getSpellDetail: Error ");
        });

        SpellListApp.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
