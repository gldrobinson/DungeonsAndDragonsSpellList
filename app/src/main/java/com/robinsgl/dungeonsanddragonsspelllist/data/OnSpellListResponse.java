package com.robinsgl.dungeonsanddragonsspelllist.data;

import org.json.JSONObject;

public interface OnSpellListResponse {
    void onSpellListReceived(JSONObject allSpells);
    void onSpellListError(Exception exception);
}
