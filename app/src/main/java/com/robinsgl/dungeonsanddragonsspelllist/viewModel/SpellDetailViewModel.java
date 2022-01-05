package com.robinsgl.dungeonsanddragonsspelllist.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.toolbox.JsonObjectRequest;
import com.robinsgl.dungeonsanddragonsspelllist.data.OnSpellDetailResponse;
import com.robinsgl.dungeonsanddragonsspelllist.data.SpellDetailRepo;
import com.robinsgl.dungeonsanddragonsspelllist.model.FullSpellDetail;
import com.robinsgl.dungeonsanddragonsspelllist.model.SpellContent;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SpellDetailViewModel extends ViewModel {
    FullSpellDetail spellReceived;
    private SpellDetailRepo spellDetailRepo;
    public MutableLiveData<FullSpellDetail> fullSpellDetail = new MutableLiveData<>();
    private ArrayList<SpellContent> spellContents = new ArrayList<>();
    public MutableLiveData<List<SpellContent>> spellContentsList = new MutableLiveData<>();

    public SpellDetailViewModel() {
        this.spellDetailRepo = new SpellDetailRepo();
    }

    public void getSpellDetail(String spellUrl) {
        spellReceived = new FullSpellDetail();
        spellDetailRepo.getSpellDetail(spellUrl, new OnSpellDetailResponse() {
            @Override
            public void onSpellDetailReceived(JSONObject fullSpellObject) {

                parseDescription(fullSpellObject);
                parseHigherLevel(fullSpellObject);
                parseLevel(fullSpellObject);
                parseCastingTime(fullSpellObject);
                parseRange(fullSpellObject);
                parseMaterial(fullSpellObject);
                parseComponents(fullSpellObject);
                parseDuration(fullSpellObject);
                parseSchool(fullSpellObject);
                parseAttackSave(fullSpellObject);
                parseDamageEffect(fullSpellObject);
                parseRitual(fullSpellObject);
                parseConcentration(fullSpellObject);

                fullSpellDetail.setValue(spellReceived);
                spellContentsList.setValue(spellContents);

            }

            @Override
            public void onSpellDetailError(Exception exception) {
                Log.i("spellDetailApi", "onSpellDetailError: " + exception.toString());

            }
        });
    }

    private void parseDescription(JSONObject fullSpellObject) {
        if (fullSpellObject.has("desc")) {
            try {
                JSONArray jsonArray = fullSpellObject.getJSONArray("desc");
                String description = "";
                for (int i = 0; i < jsonArray.length(); i++) {
                    if (i == jsonArray.length() - 1) {
                        description += jsonArray.getString(i);
                    } else {
                        description += jsonArray.getString(i) + "\n\n";
                    }
                }
                spellReceived.setDescription(description);
            } catch (Exception e) {

            }
        } else {
            spellReceived.setDescription(null);
        }
    }

    private void parseHigherLevel(JSONObject fullSpellObject) {
        if (fullSpellObject.has("higher_level")) {
            try {
                JSONArray jsonArray = fullSpellObject.getJSONArray("higher_level");
                spellReceived.setHigherLevel(jsonArray.getString(0));
            } catch (Exception e) {

            }
        } else {
            spellReceived.setHigherLevel(null);
        }
    }

    private void parseLevel(JSONObject fullSpellObject) {
        if (fullSpellObject.has("level")) {
            try {
                int level = fullSpellObject.getInt("level");
                spellReceived.setLevel(level);
                SpellContent spellContent;
                if (level != 0) {
                    spellContent = new SpellContent("LEVEL", String.valueOf(level));
                } else {
                    spellContent = new SpellContent("LEVEL", "Cantrip");
                }
                spellContents.add(spellContent);
            } catch (Exception e) {

            }
        } else {
            spellReceived.setLevel(0);
            SpellContent spellContent = new SpellContent("LEVEL", "None");
            spellContents.add(spellContent);
        }
    }

    private void parseCastingTime(JSONObject fullSpellObject) {
        if (fullSpellObject.has("casting_time")) {
            try {
                String castingTime = fullSpellObject.getString("casting_time");
                spellReceived.setCastingTime(castingTime);
                SpellContent spellContent = new SpellContent("CASTING TIME", castingTime);
                spellContents.add(spellContent);
            } catch (Exception e) {

            }
        } else {
            spellReceived.setCastingTime("None");
            SpellContent spellContent = new SpellContent("CASTING TIME", "None");
            spellContents.add(spellContent);
        }
    }

    private void parseRange (JSONObject fullSpellObject) {
        if (fullSpellObject.has("range")) {
            try {
                String range = fullSpellObject.getString("range");
                spellReceived.setRange(range);
                SpellContent spellContent = new SpellContent("RANGE/AREA", range);
                spellContents.add(spellContent);
            } catch (Exception e) {
            }
        } else {
            spellReceived.setRange("None");
            SpellContent spellContent = new SpellContent("RANGE/AREA", "None");
            spellContents.add(spellContent);
        }
    }

    private void parseMaterial (JSONObject fullSpellObject) {
        if (fullSpellObject.has("material")) {
            try {
                String material = fullSpellObject.getString("material");
                spellReceived.setMaterial(material);
            } catch (Exception e) {
            }
        } else {
            spellReceived.setMaterial(null);
        }
    }

    private void parseComponents(JSONObject fullSpellObject) {
        if (fullSpellObject.has("components")) {
            try {
                JSONArray jsonArray = fullSpellObject.getJSONArray("components");
                String components = "";
                for (int i=0; i < jsonArray.length(); i++) {
                    if (i == jsonArray.length() -1) {
                        components += jsonArray.getString(i);
                    } else {
                        components += jsonArray.getString(i) + ", ";
                    }
                }

                if(spellReceived.getMaterial() != null) {
                    components += " *";
                }
                spellReceived.setComponents(components);

                SpellContent spellContent = new SpellContent("COMPONENTS", components);
                spellContents.add(spellContent);

            } catch (Exception e) {
            }
        } else {
            spellReceived.setComponents("None");
            SpellContent spellContent = new SpellContent("COMPONENTS", "None");
            spellContents.add(spellContent);
        }
    }

    private void parseDuration (JSONObject fullSpellObject) {
        if (fullSpellObject.has("duration")) {
            try {
                String duration = fullSpellObject.getString("duration");
                spellReceived.setDuration(duration);
                SpellContent spellContent = new SpellContent("DURATION", duration);
                spellContents.add(spellContent);
            } catch (Exception e) {

            }
        } else {
            spellReceived.setDuration("None");
            SpellContent spellContent = new SpellContent("DURATION", "None");
            spellContents.add(spellContent);
        }
    }

    private void parseSchool (JSONObject fullSpellObject) {
        if (fullSpellObject.has("school")) {
            try {
                JSONObject jsonObject = fullSpellObject.getJSONObject("school");
                String school = jsonObject.getString("name");
                spellReceived.setSchool(school);
                SpellContent spellContent = new SpellContent("SCHOOL", school);
                spellContents.add(spellContent);

            } catch (Exception e) {

            }
        } else {
            spellReceived.setSchool("None");
            SpellContent spellContent = new SpellContent("SCHOOL", "None");
            spellContents.add(spellContent);
        }
    }

    public void parseAttackSave (JSONObject fullSpellObject) {
        if (fullSpellObject.has("dc")) {
            try {
                JSONObject jsonObject = fullSpellObject.getJSONObject("dc");
                JSONObject jsonObject1 = jsonObject.getJSONObject("dc_type");
                String dc = jsonObject1.getString("name");
                spellReceived.setAttackSave(dc + " Save");
                SpellContent spellContent = new SpellContent("ATTACK SAVE", dc + " Save");
                spellContents.add(spellContent);

            } catch (Exception e) {
            }
        } else {
            spellReceived.setAttackSave("None");
            SpellContent spellContent = new SpellContent("ATTACK SAVE", "None");
            spellContents.add(spellContent);
        }
    }

    public void parseDamageEffect (JSONObject fullSpellObject) {
        if (fullSpellObject.has("damage")) {
            try {
                JSONObject jsonObject = fullSpellObject.getJSONObject("damage");
                JSONObject jsonObject1 = jsonObject.getJSONObject("damage_type");
                String damage = jsonObject1.getString("name");
                spellReceived.setDamageEffect(damage);
                SpellContent spellContent = new SpellContent("DAMAGE/EFFECT", damage);
                spellContents.add(spellContent);

            } catch (Exception e) {

            }
        } else {
            spellReceived.setDamageEffect("None");
            SpellContent spellContent = new SpellContent("DAMAGE/EFFECT", "None");
            spellContents.add(spellContent);
        }
    }

    private void parseRitual (JSONObject fullSpellObject) {
        if (fullSpellObject.has("ritual")) {
            try {
                Boolean ritual = fullSpellObject.getBoolean("ritual");
                if (ritual) {
                    spellReceived.setRitual("Yes");
                } else {
                    spellReceived.setRitual("No");
                }
            } catch (Exception e) {
            }
        } else {
            spellReceived.setRitual("None");
        }
    }

    private void parseConcentration (JSONObject fullSpellObject) {
        if (fullSpellObject.has("concentration")) {
            try {
                Boolean concentration = fullSpellObject.getBoolean("concentration");
                if (concentration) {
                    spellReceived.setConcentration("Yes");
                } else {
                    spellReceived.setConcentration("No");
                }
            } catch (Exception e) {

            }
        } else {
            spellReceived.setConcentration("None");
        }
    }


}
