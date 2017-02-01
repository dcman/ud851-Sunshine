package com.example.android.sunshine;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{

    public void setPreferenceSummary(Preference preference, Object value){
        String stringValue = value.toString();
        String key = preference.getKey();

        if (preference instanceof ListPreference) {
            /* For list preferences, look up the correct display value in */
            /* the preference's 'entries' list (since they have separate labels/values). */
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
            // For other preferences, set the summary to the value's simple string representation.
            preference.setSummary(stringValue);
        }
    }
    @Override
    public void onStop() {
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onStop();
    }

    @Override
    public void onStart() {
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        super.onStart();
    }

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preference_screen);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen prefScreen = getPreferenceScreen();
        int size = prefScreen.getPreferenceCount();
        for (int i = 0; i < size; i++) {
            Preference preference = prefScreen.getPreference(i);
            if (!(preference instanceof CheckBoxPreference)) {
                String value = sharedPreferences.getString(preference.getKey(), "");
                setPreferenceSummary(preference, value);
            }
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int size = preferenceScreen.getPreferenceCount();
        for (int i = 0; i < size; i++) {
            Preference p = preferenceScreen.getPreference(i);
            if (!(p instanceof CheckBoxPreference)){
                String value = sharedPreferences.getString(p.getKey(), "");
                setPreferenceSummary(p, value);
            }

        }
    }
}
