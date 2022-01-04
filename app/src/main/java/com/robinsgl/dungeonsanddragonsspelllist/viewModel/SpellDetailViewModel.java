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
    private SpellDetailRepo spellDetailRepo;
    public MutableLiveData<FullSpellDetail> fullSpellDetail = new MutableLiveData<>();
    private ArrayList<SpellContent> spellContents = new ArrayList<>();
    public MutableLiveData<List<SpellContent>> spellContentsList = new MutableLiveData<>();

    public SpellDetailViewModel() {
        this.spellDetailRepo = new SpellDetailRepo();
    }

    public void getSpellDetail(String spellUrl) {
        FullSpellDetail spellReceived = new FullSpellDetail();
        spellDetailRepo.getSpellDetail(spellUrl, new OnSpellDetailResponse() {
            @Override
            public void onSpellDetailReceived(JSONObject fullSpellObject) {
                Log.i("spellDetailApi", "onSpellDetailReceived: " + fullSpellObject.toString());

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
                        Log.d("Failed: ", "to received desc.");

                    }
                } else {
                    spellReceived.setDescription(null);
                }

                if (fullSpellObject.has("higher_level")) {
                    try {
                        JSONArray jsonArray = fullSpellObject.getJSONArray("higher_level");
                        spellReceived.setHigherLevel(jsonArray.getString(0));
                    } catch (Exception e) {
                        Log.d("Failed: ", "to received higher_level.");

                    }
                } else {
                    spellReceived.setHigherLevel(null);
                }

                if (fullSpellObject.has("level")) {
                    try {
                        int level = fullSpellObject.getInt("level");
                        spellReceived.setLevel(level);
                        SpellContent spellContent = new SpellContent("LEVEL", String.valueOf(level));
                        spellContents.add(spellContent);
                    } catch (Exception e) {
                        Log.d("Failed: ", "to received level.");
                    }
                } else {
                    spellReceived.setLevel(0);
                    SpellContent spellContent = new SpellContent("LEVEL", "None");
                    spellContents.add(spellContent);
                }

                if (fullSpellObject.has("casting_time")) {
                    try {
                        String castingTime = fullSpellObject.getString("casting_time");
                        spellReceived.setCastingTime(castingTime);
                        SpellContent spellContent = new SpellContent("CASTING TIME", castingTime);
                        spellContents.add(spellContent);
                    } catch (Exception e) {
                        Log.d("Failed: ", "to received casting_time.");
                    }
                } else {
                    spellReceived.setCastingTime("None");
                    SpellContent spellContent = new SpellContent("CASTING TIME", "None");
                    spellContents.add(spellContent);
                }

                if (fullSpellObject.has("range")) {
                    try {
                        String range = fullSpellObject.getString("range");
                        spellReceived.setRange(range);
                        SpellContent spellContent = new SpellContent("RANGE/AREA", range);
                        spellContents.add(spellContent);
                    } catch (Exception e) {

                        Log.d("Failed: ", "to received range.");

                    }
                } else {
                    spellReceived.setRange("None");
                    SpellContent spellContent = new SpellContent("RANGE/AREA", "None");
                    spellContents.add(spellContent);
                }

                if (fullSpellObject.has("material")) {
                    try {
                        String material = fullSpellObject.getString("material");
                        spellReceived.setMaterial(material);
                    } catch (Exception e) {
                        spellReceived.setMaterial(null);
                        Log.d("Failed: ", "to received material.");

                    }
                }

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
                        Log.i("components", "onSpellDetailReceived: " + components);
                    } catch (Exception e) {
                        Log.d("Failed: ", "to received range.");

                    }
                } else {
                    spellReceived.setComponents("None");
                    SpellContent spellContent = new SpellContent("COMPONENTS", "None");
                    spellContents.add(spellContent);
                }

                if (fullSpellObject.has("duration")) {
                    try {
                        String duration = fullSpellObject.getString("duration");
                        spellReceived.setDuration(duration);
                        SpellContent spellContent = new SpellContent("DURATION", duration);
                        spellContents.add(spellContent);
                    } catch (Exception e) {
                        Log.d("Failed: ", "to received duration.");

                    }
                } else {
                    spellReceived.setDuration("None");
                    SpellContent spellContent = new SpellContent("DURATION", "None");
                    spellContents.add(spellContent);
                }

                if (fullSpellObject.has("school")) {
                    try {
                        JSONObject jsonObject = fullSpellObject.getJSONObject("school");
                        String school = jsonObject.getString("name");
                        spellReceived.setSchool(school);
                        SpellContent spellContent = new SpellContent("SCHOOL", school);
                        spellContents.add(spellContent);

                    } catch (Exception e) {
                        spellReceived.setSchool("None");
                        Log.d("Failed: ", "to received dc.");
                    }
                } else {
                    spellReceived.setSchool("None");
                    SpellContent spellContent = new SpellContent("SCHOOL", "None");
                    spellContents.add(spellContent);
                }


                if (fullSpellObject.has("dc")) {
                    try {
                        JSONObject jsonObject = fullSpellObject.getJSONObject("dc");
                        JSONObject jsonObject1 = jsonObject.getJSONObject("dc_type");
                        String dc = jsonObject1.getString("name");
                        spellReceived.setAttackSave(dc + " Save");
                        SpellContent spellContent = new SpellContent("ATTACK SAVE", dc + " Save");
                        spellContents.add(spellContent);

                    } catch (Exception e) {
                        Log.d("Failed: ", "to received dc.");
                    }
                } else {
                    spellReceived.setAttackSave("None");
                    SpellContent spellContent = new SpellContent("ATTACK SAVE", "None");
                    spellContents.add(spellContent);
                }

                if (fullSpellObject.has("damage")) {
                    try {
                        JSONObject jsonObject = fullSpellObject.getJSONObject("damage");
                        JSONObject jsonObject1 = jsonObject.getJSONObject("damage_type");
                        String damage = jsonObject1.getString("name");
                        spellReceived.setDamageEffect(damage);
                        SpellContent spellContent = new SpellContent("DAMAGE/EFFECT", damage);
                        spellContents.add(spellContent);

                    } catch (Exception e) {
                        Log.d("Failed: ", "to received damage.");
                    }
                } else {
                    spellReceived.setDamageEffect("None");
                    SpellContent spellContent = new SpellContent("DAMAGE/EFFECT", "None");
                    spellContents.add(spellContent);
                }


                if (fullSpellObject.has("ritual")) {
                    try {
                        Boolean ritual = fullSpellObject.getBoolean("ritual");
                        if (ritual) {
                            spellReceived.setRitual("Yes");
                        } else {
                            spellReceived.setRitual("No");
                        }
                    } catch (Exception e) {
                        spellReceived.setRitual("None");
                        Log.d("Failed: ", "to received ritual.");

                    }
                }


                if (fullSpellObject.has("concentration")) {
                    try {
                        Boolean concentration = fullSpellObject.getBoolean("concentratino");
                        if (concentration) {
                            spellReceived.setConcentration("Yes");
                        } else {
                            spellReceived.setConcentration("No");
                        }
                    } catch (Exception e) {
                        spellReceived.setConcentration("None");
                        Log.d("Failed: ", "to received concentration.");

                    }
                }


                fullSpellDetail.setValue(spellReceived);
                spellContentsList.setValue(spellContents);


            }

            @Override
            public void onSpellDetailError(Exception exception) {
                Log.i("spellDetailApi", "onSpellDetailError: " + exception.toString());

            }
        });
    }


}
