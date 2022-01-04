package com.robinsgl.dungeonsanddragonsspelllist.data;

import org.json.JSONObject;

public interface OnSpellDetailResponse {
    void onSpellDetailReceived(JSONObject fullSpellObject);
    void onSpellDetailError(Exception exception);
}
