package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.PreferenceScreen;


public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_sunshine);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        SharedPreferences sharedPreferences = preferenceScreen.getSharedPreferences();
        int preferenceCount = preferenceScreen.getPreferenceCount();
        for(int i = 0; i < preferenceCount; i++){
            Preference p = preferenceScreen.getPreference(i);
            if(!(p instanceof  CheckBoxPreference)){
                String preferenceValue = sharedPreferences.getString(p.getKey(), "");
                setPreferenceSummary(p, preferenceValue);
            }
        }

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference p = findPreference(key);
        if(p != null && !(p instanceof CheckBoxPreference)){
            setPreferenceSummary(p, sharedPreferences.getString(key, ""));
        }

    }

    private void setPreferenceSummary(Preference p, Object value){
        String key = p.getKey();
        String prefValue = value.toString();

        if(p instanceof ListPreference){
            ListPreference unitListPreference = (ListPreference) p;
            int indexValue = unitListPreference.findIndexOfValue(prefValue);
            if(indexValue >= 0){
                unitListPreference.setSummary(unitListPreference.getEntries()[indexValue]);
            }
        } else {
            p.setSummary(prefValue);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
