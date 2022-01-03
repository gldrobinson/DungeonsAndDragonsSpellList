package com.robinsgl.dungeonsanddragonsspelllist.model;

public class SpellUrl {
    private String spellName;
    private String spellUrl;

    public SpellUrl() {

    }
    public SpellUrl(String spellName, String spellUrl) {
        this.spellName = spellName;
        this.spellUrl = spellUrl;
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
        this.spellUrl = spellUrl;
    }
}
