package com.robinsgl.dungeonsanddragonsspelllist.model;

public class SpellApi {
    private String spellName;
    private String spellUrl;
    private String baseUrl = "https://www.dnd5eapi.co";

    public SpellApi() {

    }
    public SpellApi(String spellName, String spellUrl) {
        this.spellName = spellName;
        this.spellUrl = baseUrl + spellUrl;
    }

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    public String getSpellUrl() {
        return spellUrl;
    }

    public void setSpellUrl(String spellUrl) {
        this.spellUrl = baseUrl + spellUrl;
    }

}
