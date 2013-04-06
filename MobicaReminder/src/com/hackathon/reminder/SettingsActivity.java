package com.hackathon.reminder;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.Toast;
import roboguice.activity.RoboPreferenceActivity;

public class SettingsActivity extends RoboPreferenceActivity {
    private static final String PREFS_NAME = "defaults";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Display the fragment as the main content.
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }

    public static class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            getPreferenceManager().setSharedPreferencesName(PREFS_NAME);
            PreferenceManager.setDefaultValues(getActivity(), PREFS_NAME, MODE_PRIVATE, R.xml.fragment_preferences, false);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.fragment_preferences);
        }

        @Override
        public void onResume() {
            super.onResume();
            // Set up a listener whenever a key changes
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            // Unregister the listener whenever a key changes
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Toast.makeText(getActivity(), "Test key = " + key, Toast.LENGTH_SHORT).show();
        }
    }
}
